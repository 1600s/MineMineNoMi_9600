package xyz.pixelatedw.MineMineNoMi3.api.abilities;

import java.util.Arrays;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketAbilitySync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketShounenScream;

public class Ability 
{
	
	protected AbilityProjectile projectile;
	protected String originalDisplayName = "n/a";
	protected AbilityAttribute attr;
	protected boolean isOnCooldown = false, isCharging = false, isRepeating = false, passiveActive = false, isDisabled = false;
	private int ticksForCooldown, ticksForCharge, ticksForRepeater, ticksForRepeaterFreq, currentSpawn = 0;

	public Ability(AbilityAttribute attr)
	{
		this.attr = new AbilityAttribute(attr);
		this.ticksForCooldown = this.attr.getAbilityCooldown();
		this.ticksForCharge = this.attr.getAbilityCharges();
		this.ticksForRepeater = this.attr.getAbilityCooldown();
		this.ticksForRepeaterFreq = this.attr.getAbilityRepeaterFrequency();
		this.originalDisplayName = this.attr.getAttributeName();
	}

	public AbilityAttribute getAttribute() { return attr; }
	
	public void use(EntityPlayer player)
	{
		if(!isOnCooldown)
		{
			
			if(projectile != null)
			{
				if(this.attr.isRepeater())
					startRepeater(player);
				else
					player.worldObj.spawnEntityInWorld(projectile);
			}
			
			if(this.attr.getPotionEffectsForUser() != null)
				for(PotionEffect p : this.attr.getPotionEffectsForUser())				
					player.addPotionEffect(new PotionEffect(p));

			if(this.attr.getPotionEffectsForAoE() != null) 
				for(PotionEffect p : this.attr.getPotionEffectsForAoE())
					for(EntityLivingBase l : WyHelper.getEntitiesNear(player, this.attr.getEffectRadius())) 
						l.addPotionEffect(new PotionEffect(p));

			if(!(this.attr.getAbilityCharges() > 0) && this.attr.getAbilityExplosionPower() > 0)
				player.worldObj.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower(), this.attr.canAbilityExplosionSetFire(), MainConfig.enableGriefing ? this.attr.canAbilityExplosionDestroyBlocks() : false);		
			
	    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
	    		WyTelemetry.addStat("abilityUsed_" + this.getAttribute().getAttributeName(), 1);
	    	
	    	ExtendedEntityStats props = ExtendedEntityStats.get(player);
	    	AbilityProperties abilityProps = AbilityProperties.get(player);
	    	props.setTempPreviousAbility(WyHelper.getFancyName(this.attr.getAttributeName()));

	    	if(!this.attr.isPassive())
	    		this.sendShounenScream(player);
				
	    	duringRepeater(player);
			startCooldown();
			WyNetworkHelper.sendTo(new PacketAbilitySync(abilityProps), (EntityPlayerMP) player);
			(new Update(player, attr)).start();
		}
	}
	
	public void duringRepeater(EntityPlayer player)
	{
		if(isRepeating)
		{			
			try 
			{
				if(!player.worldObj.isRemote && currentSpawn % ticksForRepeaterFreq == 0)
					player.worldObj.spawnEntityInWorld(projectile.getClass().getDeclaredConstructor(World.class, EntityLivingBase.class, AbilityAttribute.class).newInstance(player.worldObj, player, attr));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			currentSpawn++;
		}
	}
	
	protected void startRepeater(EntityPlayer player)
	{
		this.isRepeating = true;
	}
	
	public boolean isRepeating()
	{
		return this.isRepeating;
	}
	
	public void passive(EntityPlayer player)
	{
		if(!isOnCooldown)
		{
			if(passiveActive)
			{
				passiveActive = false;
				WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(PotionEffect p : this.attr.getPotionEffectsForUser())	
						player.removePotionEffect(p.getPotionID());
				
				endPassive(player);
			}
			else
			{
				passiveActive = true;
				WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(PotionEffect p : this.attr.getPotionEffectsForUser())				
						player.addPotionEffect(new PotionEffect(p.getPotionID(), Integer.MAX_VALUE, p.getAmplifier(), true));
				
				this.sendShounenScream(player);
				
				startPassive(player);
				(new Update(player, attr)).start();
			}			
		}
	}
	
	public boolean isDisabled()
	{
		return isDisabled;
	}
	
	public void disable(EntityPlayer player, boolean bool) 
	{
		if(bool)
			(new ResetDisable(player, attr)).start();
		isDisabled = bool;
		WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
	}
	
	/** Only use super. if the ability is also using passive potion effects, otherwise there's really no plus */
	public void endPassive(EntityPlayer player) 
	{
		if(this.attr.getPotionEffectsForUser() != null)
			for(PotionEffect p : this.attr.getPotionEffectsForUser())	
				player.removePotionEffect(p.getPotionID());
	}
	
	public void startPassive(EntityPlayer player) {}
		
	public void duringPassive(EntityPlayer player, int passiveTimer) {}
	
	public boolean isPassiveActive()
	{
		return this.passiveActive;
	}

	public void setPassiveActive(boolean b)
	{
		this.passiveActive = b;
	}
	
	public void setChargeActive(boolean b)
	{
		this.isCharging = b;
	}
	
	public void setCooldownActive(boolean b)
	{
		this.isOnCooldown = b;
	}
	
	
	public void duringCharging(EntityPlayer player, int currentCharge) {}
	
	public void startCharging(EntityPlayer player)
	{
		if(!isOnCooldown)
		{
			this.sendShounenScream(player, 1);
			
			isCharging = true;
			WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
			(new Update(player, attr)).start();
		}
	}
	
	public void endCharging(EntityPlayer player)
	{
		isCharging = false;
		isOnCooldown = true;
		WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
		
		if(projectile != null)
			player.worldObj.spawnEntityInWorld(projectile);
		
		this.sendShounenScream(player, 2);
		
		if(this.attr.getAbilityExplosionPower() > 0)
			player.worldObj.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower(), this.attr.canAbilityExplosionSetFire(), MainConfig.enableGriefing ? this.attr.canAbilityExplosionDestroyBlocks() : false);		
				
    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
    		WyTelemetry.addStat("abilityUsed_" + this.getAttribute().getAttributeName(), 1);

		(new Update(player, attr)).start();
	}
	
	public boolean isCharging()
	{
		return isCharging;
	}
	
	public boolean isOnCooldown()
	{
		return isOnCooldown;
	}
	
	public void duringCooldown(EntityPlayer player, int currentCooldown) {}
	
	public void hitEntity(EntityPlayer player, EntityLivingBase target) 
	{
		if(this.attr.getPotionEffectsForHit() != null)
			for(PotionEffect p : this.attr.getPotionEffectsForHit())				
				target.addPotionEffect(new PotionEffect(p.getPotionID(), p.getDuration(), p.getAmplifier(), true)); 

		if(this.attr.getAbilityExplosionPower() > 0)
			player.worldObj.newExplosion(target, target.posX, target.posY, target.posZ, this.attr.getAbilityExplosionPower(), this.attr.canAbilityExplosionSetFire(), MainConfig.enableGriefing ? this.attr.canAbilityExplosionDestroyBlocks() : false);		

		passiveActive = false;
		startCooldown();
		WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);

		target.attackEntityFrom(DamageSource.causePlayerDamage(player), this.attr.getPunchDamage());
		
		(new Update(player, attr)).start();
	}
	
	protected void startCooldown()
	{
		isOnCooldown = true;
	}
	
	protected void startExtUpdate(EntityPlayer player)
	{
		WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
		(new Update(player, attr)).start();
	}
	
	protected void sendShounenScream(EntityPlayer player)
	{
		this.sendShounenScream(player, 0);
	}
	
	protected void sendShounenScream(EntityPlayer player, int part)
	{
		if(MainConfig.enableAnimeScreaming)
		{
    		WyNetworkHelper.sendToAllAround(new PacketShounenScream(player.getCommandSenderName(), WyHelper.getFancyName(this.attr.getAttributeName()), part), player.dimension, player.posX, player.posY, player.posZ, 15);
			if(!this.originalDisplayName.equalsIgnoreCase("n/a") && !this.attr.getAttributeName().equalsIgnoreCase(this.originalDisplayName))
				this.attr.setAttributeName(originalDisplayName);
		}
	}
	
	public void reset()
	{
		isOnCooldown = false;
		isCharging = false;
		isRepeating = false;
		passiveActive = false;			
	}
	
	class ResetDisable extends Thread
	{
		private EntityPlayer player;
		private ExtendedEntityStats props;
		private AbilityAttribute attr;
		
		public ResetDisable(EntityPlayer user, AbilityAttribute attribute)
		{
			this.player = user;
			this.props = ExtendedEntityStats.get(player);
			this.attr = attribute;
			this.setName("ResetThread Thread for " + attr.getAttributeName());
		}
		
		public void run()
		{
			while(isDisabled)
			{
				if( (!player.isInsideOfMaterial(Material.water) && !player.isWet() && player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) != Blocks.water 
						&& player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) != Blocks.flowing_water 
						&& !WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) && !DevilFruitsHelper.hasKairosekiItem(player) && !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiBlock))
						&& !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)))  )
				{
			    	disable(player, false);
					setCooldownActive(false);
					try
					{
						this.join();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				try 
				{
					Thread.sleep(24);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		private boolean abilityCounterpart(String ablNameForCheck)
		{
			return WyHelper.getFancyName(this.attr.getAttributeName()).equals(WyHelper.getFancyName(ablNameForCheck));
		}
	}
	
	
	class Update extends Thread
	{
		private EntityPlayer player;
		private AbilityAttribute attr;
		
		public Update(EntityPlayer user, AbilityAttribute attribute)
		{
			this.player = user;
			this.attr = attribute;
			this.setName("Update Thread for " + attr.getAttributeName());
			ticksForCooldown = this.attr.getAbilityCooldown();
			ticksForCharge = this.attr.getAbilityCharges();
		}
		
		public void run()
		{
			if(passiveActive)
			{
				int passiveTimer = 0;
				while(passiveActive)
				{
					duringPassive(player, passiveTimer++);
					try 
					{
						Thread.sleep(20);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			if(isOnCooldown)
			{
				while(isOnCooldown)
				{
					if(ticksForCooldown > 0)
					{
						ticksForCooldown--;
						if(isRepeating)
						{
							ticksForRepeater--;
							if(ticksForRepeater > this.attr.getAbilityCooldown() - (this.attr.getAbilityCooldown() / this.attr.getAbilityRepeaterTime()) && projectile != null)
							{
								
							}
							else
							{
								isRepeating = false;
								ticksForRepeater = attr.getAbilityCooldown();
							}
						}
						duringCooldown(player, ticksForCooldown);
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCooldown = this.attr.getAbilityCooldown();
						currentSpawn = 0;
						isOnCooldown = false;
						WyNetworkHelper.sendTo(new PacketAbilitySync(AbilityProperties.get(player)), (EntityPlayerMP) player);
						return;
					}
				}	
			}
			else if(isCharging)
			{
				while(isCharging)
				{
					if(ticksForCharge > 0)
					{
						ticksForCharge--;	
						duringCharging(player, ticksForCharge);
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCharge = this.attr.getAbilityCharges();
						endCharging(player);
					}
				}
			}
		}
	}
}

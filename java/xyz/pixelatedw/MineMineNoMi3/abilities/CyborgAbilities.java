package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.CyborgProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class CyborgAbilities 
{
	public static Ability FRESHFIRE = new FreshFire();
	public static Ability COLAOVERDRIVE = new ColaOverdrive();
	public static Ability RADICALBEAM = new RadicalBeam();
	public static Ability STRONGRIGHT = new StrongRight();
	public static Ability COUPDEVENT = new CoupDeVent();
	
	public static Ability[] abilitiesArray = new Ability[] {FRESHFIRE, COLAOVERDRIVE, RADICALBEAM, STRONGRIGHT, COUPDEVENT};

	
	public static class CoupDeVent extends Ability
	{
		public CoupDeVent() 
		{
			super(ListAttributes.COUPDEVENT); 
		}
		
		public void startCharging(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(!isOnCooldown && props.getCola() >= 25)
				super.startCharging(player);
			else if(props.getCola() < 25)
				WyHelper.sendMsgToPlayer(player, "Not enough Cola !");					
		}
		
		public void duringCharging(EntityPlayer player, int currentCharge)
		{		
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 1000));
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 10, 1000));	
		}
		
		public void endCharging(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.alterCola(-25);
			isCharging = false;
			isOnCooldown = true;	
					
			for (int i = 0; i < 100; i++)
			{
				double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
				double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
				double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
						
				this.projectile = new CyborgProjectiles.CoupDeVent(player.worldObj, player, attr);
				this.projectile.setLocationAndAngles(player.posX + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.cameraPitch, player.cameraYaw);
				player.worldObj.spawnEntityInWorld(projectile);
			}
				
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			startCooldown();
			startExtUpdate(player);
		}	
	}
	
	public static class StrongRight extends Ability
	{
		public StrongRight() 
		{
			super(ListAttributes.STRONGRIGHT); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!player.worldObj.isRemote)
			{				
				if(!this.isOnCooldown && props.getCola() >= 10)
				{					
					this.projectile = new CyborgProjectiles.StrongRight(player.worldObj, player, attr);
					player.worldObj.spawnEntityInWorld(projectile);

					props.alterCola(-10);					
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					super.use(player);
				}
				else if(props.getCola() < 10)
					WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			}
		}			
	}
	
	public static class RadicalBeam extends Ability
	{
		public RadicalBeam() 
		{
			super(ListAttributes.RADICALBEAM); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!player.worldObj.isRemote)
			{
				if(!this.isOnCooldown && props.getCola() >= 15)
				{
					this.projectile = new CyborgProjectiles.RadicalBeam(player.worldObj, player, attr);
					player.worldObj.spawnEntityInWorld(projectile);

					props.alterCola(-15);
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					super.use(player);
				}
				else if(props.getCola() < 15)
					WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			}
		}			
	}
	
	public static class FreshFire extends Ability
	{
		public FreshFire() 
		{
			super(ListAttributes.FRESHFIRE); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(!this.isOnCooldown && props.getCola() >= 5)
			{
				for (int i = 0; i < 100; i++)
				{
					double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
					double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
					double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
						
					this.projectile = new CyborgProjectiles.FreshFire(player.worldObj, player, attr);
					this.projectile.setLocationAndAngles(player.posX + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.cameraPitch, player.cameraYaw);
					player.worldObj.spawnEntityInWorld(projectile);
				}
					
				props.alterCola(-5);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.use(player);
			}
			else if(props.getCola() < 5)
				WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
		}	
	}
	
	public static class ColaOverdrive extends Ability
	{
		public ColaOverdrive() 
		{
			super(ListAttributes.COLAOVERDRIVE); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!isOnCooldown && props.getCola() > 0)
			{
				double r = (props.getCola() / props.getMaxCola()) * 100;
				
				props.setCola(0);
				
				player.setHealth((float) (player.getHealth() + ((r / 100) * player.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue()) ));	
				
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.use(player);
			}
			else if(props.getCola() <= 0)
				WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
		}		
	}
}

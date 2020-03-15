package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.abilities.ExtraAbilities.HybridPoint;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ToriPhoenixProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class ToriPhoenixAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new PhoenixPoint(), new HybridPoint(), new BlueFlamesOfResurrection(), new FlameOfRestoration(), new PhoenixGoen(), new TenseiNoSoen()};

	
	public static class TenseiNoSoen extends Ability
	{
		private int particlesSpawned = 0;
		
		public TenseiNoSoen() 
		{
			super(ListAttributes.TENSEINOSOEN); 
		}
		
		public void startCharging(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			particlesSpawned = 0;
			
			if((props.getZoanPoint().equals(ID.ZOANMORPH_PHOENIX)) && !this.isOnCooldown)
			{
				if(!player.onGround)
				{
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_TENSEINOSOEN1, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
					super.startCharging(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while airborne !");
			}
			else if(!props.getZoanPoint().equals(ID.ZOANMORPH_PHOENIX))
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Phoenix Point is active !");
		}
		
		public void endCharging(EntityPlayer player)
		{
			player.fallDistance = 0;
			motion("=", 0, -100, 0, player);
			super.endCharging(player);
		}		
		
	    public void duringCooldown(EntityPlayer player, int currentCooldown)
	    {
			if(currentCooldown > 28 * 20)
			{
				if(player.onGround && particlesSpawned < 10)
				{
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_TENSEINOSOEN2, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
					particlesSpawned++;
				}			
				
				for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 20))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 30);
				}
			}
	    }
	}
	
	public static class PhoenixGoen extends Ability
	{
		public PhoenixGoen() 
		{
			super(ListAttributes.PHOENIXGOEN); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if((props.getZoanPoint().equals(ID.ZOANMORPH_PHOENIX) || props.getZoanPoint().equals(ID.ZOANMORPH_HYBRID)) && !this.isOnCooldown)
			{
				for (int i = 0; i < 100; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 5.0D) / 5.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 20.0D) / 10.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 5.0D) / 5.0D;
						
					this.projectile = new ToriPhoenixProjectiles.PhoenixGoen(player.worldObj, player, attr);
					this.projectile.setLocationAndAngles(player.posX - 1 + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.cameraPitch, player.cameraYaw);
					player.worldObj.spawnEntityInWorld(projectile);
				}
					
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.use(player);
			}
			else if(!props.getZoanPoint().equals(ID.ZOANMORPH_PHOENIX) && !props.getZoanPoint().equals(ID.ZOANMORPH_HYBRID))
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Phoenix Point or Hybrid Point is active !");
		}	
	}
	
	public static class FlameOfRestoration extends Ability
	{
		public FlameOfRestoration() 
		{
			super(ListAttributes.FLAMEOFRESTORATION); 
		}
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			super.hitEntity(player, target);
			
			if(target.getHealth() + 4 < target.getMaxHealth())
				target.setHealth(target.getHealth() + 4);
			else
				target.setHealth(target.getHealth());
			
			WyNetworkHelper.sendTo(new PacketParticles(ID.PARTICLEFX_BLUEFLAMES, player), (EntityPlayerMP) player);
		}
	}	
	
	public static class BlueFlamesOfResurrection extends Ability
	{
		public BlueFlamesOfResurrection() 
		{
			super(ListAttributes.BLUEFLAMESOFRESURRECTION); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!isOnCooldown)
			{
				WyNetworkHelper.sendTo(new PacketParticles(ID.PARTICLEFX_BLUEFLAMES, player), (EntityPlayerMP) player);
			}
			super.use(player);
		}
	}	
	
	public static class PhoenixPoint extends Ability
	{
		public PhoenixPoint()
		{
			super(ListAttributes.PHOENIXPOINT);
		}

		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if (!this.isOnCooldown)
			{				
				if (props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");

				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_PHOENIX))
				{
					props.setZoanPoint("n/a");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				} 
				else
				{
					if(player.onGround)
					{
						WyHelper.sendMsgToPlayer(player, "You cannot use this form while on ground !");
						return;
					}
					
					props.setZoanPoint(ID.ZOANMORPH_PHOENIX);
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BLUEFLAMES, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				}

				super.use(player);
			}
		}
	}
	
	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
}

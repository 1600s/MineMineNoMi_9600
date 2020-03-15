package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class ExtraProjectiles 
{
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {NormalBullet.class, ListExtraAttributes.NORMALBULLET});
		abilitiesClassesArray.add(new Object[] {KairosekiBullet.class, ListExtraAttributes.KAIROSEKIBULLET});
		abilitiesClassesArray.add(new Object[] {AxeDialProjectile.class, ListExtraAttributes.DIALAXE});
		abilitiesClassesArray.add(new Object[] {PopGreen.class, ListExtraAttributes.POPGREEN});
		abilitiesClassesArray.add(new Object[] {KujaArrow.class, ListExtraAttributes.KUJAARROW});
	}
	
	public static class EntityCloud extends Entity
	{
		private int life = 100;
		private EntityPlayer thrower;
		
		public EntityCloud(World world)
		{super(world);}
		
		public void onUpdate()
		{
			super.onUpdate();
			if(!this.worldObj.isRemote)
			{
				if(life <= 0)
					this.setDead();
								
				life--;
			}
			
			WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KEMURIBOSHI, this.posX, this.posY, this.posZ), this.dimension, this.posX, this.posY, this.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
		}
		
		public void setThrower(EntityPlayer player)
		{
			this.thrower = player;
		}
		
		public void setLife(int life)
		{
			this.life = life;
		}
		
		protected void entityInit() {}
		protected void readEntityFromNBT(NBTTagCompound nbt) {}
		protected void writeEntityToNBT(NBTTagCompound nbt) {}
	}	
	
	public static class MilkyDialProjectile extends AbilityProjectile
	{
		public MilkyDialProjectile(World world)
		{super(world);}
		
		public MilkyDialProjectile(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public MilkyDialProjectile(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 1, (int)this.posZ, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ + 1, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ - 1, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ + 1, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 1, (int)this.posZ + 1, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 1, (int)this.posZ - 1, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ - 1, ListMisc.SkyBlock);
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 2, (int)this.posZ, ListMisc.SkyBlock);
			
			super.onUpdate();
		}
	}	
	
	public static class AxeDialProjectile extends AbilityProjectile
	{
		public AxeDialProjectile(World world)
		{super(world);}
		
		public AxeDialProjectile(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public AxeDialProjectile(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			WyHelper.explosion(this, this.posX, this.posY, this.posZ, 4f);
		}	
	}	
	
	public static class PopGreen extends AbilityProjectile
	{
		public PopGreen(World world)
		{super(world);}
		
		public PopGreen(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public PopGreen(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
	public static class KujaArrow extends AbilityProjectile
	{
		public KujaArrow(World world)
		{super(world);}
		
		public KujaArrow(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public KujaArrow(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
	public static class NormalBullet extends AbilityProjectile
	{
		public NormalBullet(World world)
		{super(world);}
		
		public NormalBullet(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public NormalBullet(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
	public static class KairosekiBullet extends AbilityProjectile
	{
		public KairosekiBullet(World world)
		{super(world);}
		
		public KairosekiBullet(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public KairosekiBullet(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
}

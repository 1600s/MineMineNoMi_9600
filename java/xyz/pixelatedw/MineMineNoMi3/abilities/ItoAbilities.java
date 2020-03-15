package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ItoProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class ItoAbilities 
{
	
	public static Ability[] abilitiesArray = new Ability[] {new Parasite(), new SoraNoMichi(), new Overheat(), new Tamaito(), new KumoNoSugaki(), new Torikago()};
	
	
	public static class Torikago extends Ability
	{
		private boolean canSpawnTorikago = true;
		
		public Torikago() 
		{
			super(ListAttributes.TORIKAGO); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown && canSpawnTorikago)
			{
				final World world = player.worldObj;
				Sphere.generate((int)player.posX, (int)player.posY, (int)player.posZ, 20, new ISphere()
				{
					public void call(int x, int y, int z)
					{
						DevilFruitsHelper.placeIfCanReplaceBlock(world, x, y , z, ListMisc.StringWall);
					}
				});
				player.worldObj.setBlock((int) player.posX, (int) player.posY, (int) player.posZ, ListMisc.StringMid);
				
				canSpawnTorikago = false;
				super.use(player);
			}
			else if(!canSpawnTorikago)
			{
				if(!WyHelper.isBlockNearby(player, 30, ListMisc.StringMid))
					canSpawnTorikago = true;
				else
				{
					for(int x = -50; x < 50; x++)
					for(int y = -50; y < 50; y++)
					for(int z = -50; z < 50; z++)
					{
						if(player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == ListMisc.StringWall || player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == ListMisc.StringMid)
							player.worldObj.setBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z, Blocks.air);
					}					
					canSpawnTorikago = true;
				}
			}
		} 
		
		public void alterSpawnFlag(boolean flag)
		{
			canSpawnTorikago = flag;
		}
	}
	
	public static class KumoNoSugaki extends Ability
	{
		public KumoNoSugaki() 
		{
			super(ListAttributes.KUMONOSUGAKI); 
		}
		
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{		
			WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KUMONOSUGAKI, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			
			if(passiveTimer >= 300)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
				super.endPassive(player);
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}
	}
	
	public static class Tamaito extends Ability
	{
		public Tamaito() 
		{
			super(ListAttributes.TAMAITO); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new ItoProjectiles.Tamaito(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class Overheat extends Ability
	{
		public Overheat() 
		{
			super(ListAttributes.OVERHEAT); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new ItoProjectiles.Overheat(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class SoraNoMichi extends Ability
	{
		public SoraNoMichi() 
		{
			super(ListAttributes.SORANOMICHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				Direction dir = WyHelper.get8Directions(player);
				
				double mX = 0;
				double mY = 0;
				double mZ = 0;
				
				if(player.onGround)
					mY += 1.8;
				else
					mY += 1.96;

				if(dir == WyHelper.Direction.NORTH) mZ -= 1;
				if(dir == WyHelper.Direction.NORTH_WEST) {mZ -= 1; mX -= 1;}
				if(dir == WyHelper.Direction.SOUTH) mZ += 1;
				if(dir == WyHelper.Direction.NORTH_EAST) {mZ -= 1; mX += 1;}
				if(dir == WyHelper.Direction.WEST) mX -= 1;
				if(dir == WyHelper.Direction.SOUTH_WEST) {mZ += 1; mX -= 1;}
				if(dir == WyHelper.Direction.EAST) mX += 1;
				if(dir == WyHelper.Direction.SOUTH_EAST) {mZ += 1; mX += 1;}
				
				motion("=", mX, mY, mZ, player);
				
				super.use(player);
			}
		}
	}
	
	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
	
	public static class Parasite extends Ability
	{
		public Parasite() 
		{
			super(ListAttributes.PARASITE); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!isOnCooldown)
			{			
				for(EntityLivingBase l : WyHelper.getEntitiesNear(player, 20))
				{
					System.out.println(l);

					l.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 10));
					l.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 10));
					
					l.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200, 10));
					l.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 10));
				}
					
				super.use(player);
			}
		}
	}

}

package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockBarrier;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BariProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.Heart;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class BariAbilities 
{
	public static Ability[] abilitiesArray = new Ability[] {new Barrier(), new BarrierBall(), new BarrierCrash(), new BariBariNoPistol(), new BarrierbilityStairs()};
	
	
	public static class BarrierbilityStairs extends Ability
	{
		public BarrierbilityStairs() 
		{
			super(ListAttributes.BARRIERBILITYSTAIRS); 
		}	
		
		public void use(EntityPlayer player)
		{
			this.projectile = new BariProjectiles.BarrierbilityStairs(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class BariBariNoPistol extends Ability
	{
		public BariBariNoPistol() 
		{
			super(ListAttributes.BARIBARINOPISTOL); 
		}	
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			if(!this.isOnCooldown)
			{				
				super.hitEntity(player, target);
				
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
			}		
		}
	}
	
	public static class BarrierCrash extends Ability
	{
		public BarrierCrash() 
		{
			super(ListAttributes.BARRIERCRASH); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new BariProjectiles.BarrierCrash(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class BarrierBall extends Ability
	{
		public BarrierBall() 
		{
			super(ListAttributes.BARRIERBALL); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				MovingObjectPosition rtr = WyHelper.rayTraceBlocks(player);

				final World world = player.worldObj;
				Sphere.generate((int)player.posX, (int)player.posY, (int)player.posZ, 5, new ISphere()
				{
					public void call(int x, int y, int z)
					{
						if(world.getBlock(x, y ,z) == Blocks.air)
							world.setBlock(x, y ,z, ListMisc.Barrier);
					}
				});
				
				super.use(player);
			}
		} 
	}
	
	public static class Barrier extends Ability
	{
		public Barrier() 
		{
			super(ListAttributes.BARRIER); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
				{
					for(int x = -3; x < 3; x++)
					for(int y = 0; y <= 3; y++)
					for(int z = -1; z <= 1; z++)
						if(player.worldObj.getBlock((int) player.posX - x, (int) player.posY + y, ((int) player.posZ - 3) - z) == Blocks.air)
							player.worldObj.setBlock((int) player.posX - x, (int) player.posY + y, ((int) player.posZ - 3) - z, ((BlockBarrier)ListMisc.Barrier).setTimer(2));
				}
				if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
				{
					for(int x = -3; x < 3; x++)
					for(int y = 0; y <= 3; y++)
					for(int z = -1; z <= 1; z++)
						if(player.worldObj.getBlock((int) player.posX - x, (int) player.posY + y, ((int) player.posZ + 2) - z) == Blocks.air)
							player.worldObj.setBlock((int) player.posX - x, (int) player.posY + y, ((int) player.posZ + 2) - z, ((BlockBarrier)ListMisc.Barrier).setTimer(2));
				}
				if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
				{
					for(int x = -1; x < 1; x++)
					for(int y = 0; y <= 3; y++)
					for(int z = -3; z <= 3; z++)
						if(player.worldObj.getBlock(((int) player.posX + 2) - x, (int) player.posY + y, (int) player.posZ - z) == Blocks.air)
							player.worldObj.setBlock(((int) player.posX + 2) - x, (int) player.posY + y, (int) player.posZ - z, ((BlockBarrier)ListMisc.Barrier).setTimer(2));
				}
				if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
				{
					for(int x = -1; x < 1; x++)
					for(int y = 0; y <= 3; y++)
					for(int z = -3; z <= 3; z++)
						if(player.worldObj.getBlock(((int) player.posX - 3) - x, (int) player.posY + y, (int) player.posZ - z) == Blocks.air)
							player.worldObj.setBlock(((int) player.posX - 3) - x, (int) player.posY + y, (int) player.posZ - z, ((BlockBarrier)ListMisc.Barrier).setTimer(2));
				}
				
				super.use(player);
			}
		} 
	}
}

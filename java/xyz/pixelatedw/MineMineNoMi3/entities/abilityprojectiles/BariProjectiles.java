package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockBarrier;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class BariProjectiles 
{
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {BarrierCrash.class, ListAttributes.BARRIERCRASH});
		abilitiesClassesArray.add(new Object[] {BarrierbilityStairs.class, ListAttributes.BARRIERBILITYSTAIRS});
	}
	
	public static class BarrierbilityStairs extends AbilityProjectile
	{
		public BarrierbilityStairs(World world)
		{super(world);}
		
		public BarrierbilityStairs(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public BarrierbilityStairs(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{	
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 1, (int)this.posZ, ((BlockBarrier)ListMisc.Barrier).setTimer(60));		
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ, ((BlockBarrier)ListMisc.Barrier).setTimer(60));
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ, ((BlockBarrier)ListMisc.Barrier).setTimer(60));
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ + 1, ((BlockBarrier)ListMisc.Barrier).setTimer(60));	
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ - 1, ((BlockBarrier)ListMisc.Barrier).setTimer(60));	
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ + 1, ((BlockBarrier)ListMisc.Barrier).setTimer(60));			
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 1, (int)this.posZ + 1, ((BlockBarrier)ListMisc.Barrier).setTimer(60));		
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 1, (int)this.posZ - 1, ((BlockBarrier)ListMisc.Barrier).setTimer(60));			
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ - 1, ((BlockBarrier)ListMisc.Barrier).setTimer(60));				
			DevilFruitsHelper.placeIfCanReplaceBlock(this.worldObj, (int)this.posX, (int)this.posY - 2, (int)this.posZ, ((BlockBarrier)ListMisc.Barrier).setTimer(60));

			super.onUpdate();
		}
	}	
	
	public static class BarrierCrash extends AbilityProjectile
	{
		public BarrierCrash(World world)
		{super(world);}
		
		public BarrierCrash(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public BarrierCrash(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
}

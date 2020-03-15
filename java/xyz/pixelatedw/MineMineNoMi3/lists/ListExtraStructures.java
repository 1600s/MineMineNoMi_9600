package xyz.pixelatedw.MineMineNoMi3.lists;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityCustomSpawner;

public class ListExtraStructures
{
	
	public static void buildDojo(int posX, int posY, int posZ, World world)
	{			
		TileEntityCustomSpawner spw1 = new TileEntityCustomSpawner().setSpawnerMob(ID.PROJECT_ID + ".Dojo Sensei").setSpawnerLimit(1);
		
		world.setBlock(posX + 5, posY + 2, posZ + 11, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 5, posY + 2, posZ + 11, spw1);		
	}
	
	public static void buildCamp(int posX, int posY, int posZ, World world)
	{
		String toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
		String toSpawn2 = ID.PROJECT_ID + ".Marine with Gun";
		String toSpawnCpt = ID.PROJECT_ID + ".Marine with Sword";
		
		world.setBlock(posX - 2, posY, posZ + 15, Blocks.bedrock );
		world.setBlock(posX - 15, posY, posZ - 2, Blocks.bedrock );
	}
	
	public static void buildLargeShip(int posX, int posY, int posZ, World world, String faction)
	{
		String toSpawn1;
		String toSpawn2;
		String toSpawnCpt;
		int x1 = 0, x2 = 0, x3 = 0;
		
		Item swordToSpawn;
		if(faction.equals("marineLargeShip"))
		{
			toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Marine with Gun";
			toSpawnCpt = ID.PROJECT_ID + ".Marine Captain";
			swordToSpawn = ListMisc.MarineSword;
			x1 = 13;
			x2 = 6;
			x3 = 10;
		}
		else
		{
			toSpawn1 = ID.PROJECT_ID + ".Pirate with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Pirate with Gun";	
			toSpawnCpt = ID.PROJECT_ID + ".Pirate Captain";
			swordToSpawn = ListMisc.PirateCutlass;
			x1 = 16;
			x2 = 9;
			x3 = 13;
		}
		
		
		TileEntityCustomSpawner spw1 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw2 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw3 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw4 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw5 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw6 = new TileEntityCustomSpawner().setSpawnerMob(toSpawnCpt).setSpawnerLimit(1);
		
		world.setBlock(posX + 10, posY + 2, posZ + 27, ListMisc.CustomSpawner.setSpawnerMob(toSpawn1).setSpawnerLimit(5) );
		world.setTileEntity(posX + 10, posY + 2, posZ + 27, spw1);
		world.setBlock(posX + 14, posY + 2, posZ + 32, ListMisc.CustomSpawner.setSpawnerMob(toSpawn2).setSpawnerLimit(5) );
		world.setTileEntity(posX + 14, posY + 2, posZ + 32, spw2);
		
		world.setBlock(posX + 12, posY + 8, posZ + 17, ListMisc.CustomSpawner.setSpawnerMob(toSpawn1).setSpawnerLimit(5) );
		world.setTileEntity(posX + 12, posY + 8, posZ + 17, spw3);
		world.setBlock(posX + 12, posY + 8, posZ + 11, ListMisc.CustomSpawner.setSpawnerMob(toSpawn1).setSpawnerLimit(5) );
		world.setTileEntity(posX + 12, posY + 8, posZ + 11, spw4);
		
		world.setBlock(posX + 14, posY + 8, posZ + 42, ListMisc.CustomSpawner.setSpawnerMob(toSpawn1).setSpawnerLimit(5) );
		world.setTileEntity(posX + 14, posY + 8, posZ + 42, spw5);
		world.setBlock(posX + 8, posY + 8, posZ + 42, ListMisc.CustomSpawner.setSpawnerMob(toSpawnCpt).setSpawnerLimit(1) );
		world.setTileEntity(posX + 8, posY + 8, posZ + 42, spw6);
		
		TileEntityChest chest1 = new TileEntityChest();
		world.setTileEntity(posX + x1, posY + 2, posZ + 39, chest1);
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{ 
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(5, 10), 0));	
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(1, 5), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
		{
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(swordToSpawn, (int) WyMathHelper.randomWithRange(0, 1), 0));	
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Flintlock, (int) WyMathHelper.randomWithRange(0, 1), 0));	
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 20)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.KairosekiBullets, (int) WyMathHelper.randomWithRange(1, 5), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 60)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.boat, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 5)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box1, 1, 0));
		
		TileEntityChest chest2 = new TileEntityChest();
		world.setTileEntity(posX + x2, posY + 2, posZ + 39, chest2);
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(1, 5), 0));	
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(1, 2), 0));
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.melon, (int) WyMathHelper.randomWithRange(5, 10), 0));	
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 55)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.baked_potato, (int) WyMathHelper.randomWithRange(0, 5), 0));	
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.apple, (int) WyMathHelper.randomWithRange(1, 10), 0));	
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.melon, (int) WyMathHelper.randomWithRange(5, 10), 0));				
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 20)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 3), 0));	
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			{
				if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
					chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_fished, (int) WyMathHelper.randomWithRange(1, 4), 0));
				else
					chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_fished, (int) WyMathHelper.randomWithRange(1, 4), 1));
			}
			else
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_chicken, (int) WyMathHelper.randomWithRange(1, 2), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 5), 0));	
		
		TileEntityChest chest3 = new TileEntityChest();
		world.setTileEntity(posX + x3, posY + 2, posZ + 42, chest3);
		
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 75)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 35)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 25)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BlackMetal, (int) WyMathHelper.randomWithRange(1, 2), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 70)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.KairosekiBullets, (int) WyMathHelper.randomWithRange(5, 10), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.boat, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box1, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.UltraCola, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 7)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box2, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 5)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box3, 1, 0));
	}
	
	public static void buildSmallShip(int posX, int posY, int posZ, World world, String faction)
	{
		String toSpawn1;
		String toSpawn2;
		Item swordToSpawn;
		if(faction.equals("marineShip"))
		{
			toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Marine with Gun";
			swordToSpawn = ListMisc.MarineSword;
		}
		else
		{
			toSpawn1 = ID.PROJECT_ID + ".Pirate with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Pirate with Gun";	
			swordToSpawn = ListMisc.PirateCutlass;
		}
		
		
		TileEntityCustomSpawner spw1 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw2 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw3 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(2);
		
		world.setBlock(posX + 10, posY + 2, posZ + 32, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 2, posZ + 32, spw1);
		world.setBlock(posX + 10, posY + 2, posZ + 43, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 2, posZ + 43, spw2);
		world.setBlock(posX + 10, posY + 7, posZ + 45, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 7, posZ + 45, spw3);

		world.setBlock(posX + 12, posY + 2, posZ + 32, Blocks.torch);
		world.setBlock(posX + 12, posY + 2, posZ + 38, Blocks.torch);
		world.setBlock(posX + 12, posY + 2, posZ + 43, Blocks.torch);
		
		TileEntityChest chest1 = new TileEntityChest();
		world.setTileEntity(posX + 11, posY + 2, posZ + 25, chest1);
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(5, 10), 0));	
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(1, 5), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
		{
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(swordToSpawn, (int) WyMathHelper.randomWithRange(0, 1), 0));	
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Flintlock, (int) WyMathHelper.randomWithRange(0, 1), 0));	
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, (int) WyMathHelper.randomWithRange(0, 1), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 15)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.KairosekiBullets, (int) WyMathHelper.randomWithRange(1, 5), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.boat, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 5)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box1, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 1)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box2, 1, 0));
	
		TileEntityChest chest2 = new TileEntityChest();
		world.setTileEntity(posX + 8, posY + 2, posZ + 25, chest2);
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(1, 3), 0));	
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(0, 1), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.baked_potato, (int) WyMathHelper.randomWithRange(0, 5), 0));	
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.apple, (int) WyMathHelper.randomWithRange(1, 4), 0));
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 70)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.melon, (int) WyMathHelper.randomWithRange(5, 10), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 3), 0));	
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_chicken, (int) WyMathHelper.randomWithRange(0, 2), 0));	
			else
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_beef, (int) WyMathHelper.randomWithRange(0, 2), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 5), 0));	
		
		world.setBlock(posX + 11, posY + 2, posZ + 24, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 24, Blocks.air);
		
		world.setBlock(posX + 11, posY + 2, posZ + 22, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 22, Blocks.air);
		world.setBlock(posX + 11, posY + 2, posZ + 21, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 21, Blocks.air);
		
	}


}
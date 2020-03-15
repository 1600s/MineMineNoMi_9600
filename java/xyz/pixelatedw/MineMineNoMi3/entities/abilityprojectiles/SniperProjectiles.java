package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ExtraProjectiles.EntityCloud;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class SniperProjectiles
{
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {KaenBoshi.class, ListAttributes.KAENBOSHI});
		abilitiesClassesArray.add(new Object[] {KemuriBoshi.class, ListAttributes.KEMURIBOSHI});
		abilitiesClassesArray.add(new Object[] {RenpatsuNamariBoshi.class, ListAttributes.RENPATSUNAMARIBOSHI});
		abilitiesClassesArray.add(new Object[] {SakuretsuSabotenBoshi.class, ListAttributes.SAKURETSUSABOTENBOSHI});

	}
	
	public static class SakuretsuSabotenBoshi extends AbilityProjectile
	{
		public SakuretsuSabotenBoshi(World world)
		{super(world);}
		
		public SakuretsuSabotenBoshi(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public SakuretsuSabotenBoshi(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			for(int i = 0; i < 8; i++)
			{
				int a1 = worldObj.rand.nextInt(10) - 5;
				int a2 = worldObj.rand.nextInt(10) - 5;
				
				DevilFruitsHelper.placeIfCanReplaceBlock(worldObj, (int)posX + a1, (int)posY , (int)posZ + a2, Blocks.cactus);		
				DevilFruitsHelper.placeIfCanReplaceBlock(worldObj, (int)posX + a1, (int)posY + 1, (int)posZ + a2, Blocks.cactus);		
				DevilFruitsHelper.placeIfCanReplaceBlock(worldObj, (int)posX + a1, (int)posY + 2, (int)posZ + a2, Blocks.cactus);	
				DevilFruitsHelper.placeIfCanReplaceBlock(worldObj, (int)posX + a1, (int)posY + 3, (int)posZ + a2, Blocks.cactus);
			}
		}
	}
	
	public static class RenpatsuNamariBoshi extends AbilityProjectile
	{
		public RenpatsuNamariBoshi(World world)
		{super(world);}
		
		public RenpatsuNamariBoshi(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public RenpatsuNamariBoshi(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}
	
	public static class KemuriBoshi extends AbilityProjectile
	{
		public KemuriBoshi(World world)
		{super(world);}
		
		public KemuriBoshi(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public KemuriBoshi(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}

		public void tasksImapct(MovingObjectPosition hit)
		{
			if(hit.entityHit != null)
			{
				((EntityLivingBase)hit.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
				((EntityLivingBase)hit.entityHit).addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
			}
			
			EntityCloud smokeCloud = new EntityCloud(worldObj);
			smokeCloud.setLocationAndAngles(hit.blockX, (hit.blockY + 1), hit.blockZ, 0, 0);
			smokeCloud.motionX = 0;
			smokeCloud.motionZ = 0;
			smokeCloud.motionY = 0;	
			smokeCloud.setThrower((EntityPlayer) this.getThrower());
			this.worldObj.spawnEntityInWorld(smokeCloud);		
		}	
	}
	
	public static class KaenBoshi extends AbilityProjectile
	{
		public KaenBoshi(World world)
		{super(world);}
		
		public KaenBoshi(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public KaenBoshi(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{								
			this.setFire(999);
			super.onUpdate();
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			if(hit.entityHit != null)
			{
				hit.entityHit.setFire(100);
			}
		}	
	}
}

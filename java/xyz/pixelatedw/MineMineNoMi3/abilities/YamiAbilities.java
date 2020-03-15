package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import scala.reflect.api.Internals.ReificationSupportApi.SyntacitcSingletonTypeExtractor;
import scala.tools.nsc.backend.icode.BasicBlocks.BasicBlock.SuccessorList;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockBarrier;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.YamiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class YamiAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new DarkMatter(), new Liberation(), new BlackHole(), new BlackWorld()};	
	
	public static class BlackWorld extends Ability
	{
		public BlackWorld() 
		{
			super(ListAttributes.BLACKWORLD); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				if(MainConfig.enableGriefing)
				{
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BLACKWORLD, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
	
					for(int i = 0; i < 8; i++)
					{
						int a1 = player.getRNG().nextInt(20) - 10;
						int a2 = player.getRNG().nextInt(20) - 10;
						
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY , (int)player.posZ + a2, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 1, (int)player.posZ + a2, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 2, (int)player.posZ + a2, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 3, (int)player.posZ + a2, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 4, (int)player.posZ + a2, ListMisc.Darkness);
						
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY, (int)player.posZ + a2, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 1, (int)player.posZ + a2, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 2, (int)player.posZ + a2, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 3, (int)player.posZ + a2, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 4, (int)player.posZ + a2, ListMisc.Darkness);	
						
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY, (int)player.posZ + a2 + 1, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 1, (int)player.posZ + a2 + 1, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 2, (int)player.posZ + a2 + 1, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 3, (int)player.posZ + a2 + 1, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1, (int)player.posY + 4, (int)player.posZ + a2 + 1, ListMisc.Darkness);	
	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY, (int)player.posZ + a2 + 1, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 1, (int)player.posZ + a2 + 1, ListMisc.Darkness);		
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 2, (int)player.posZ + a2 + 1, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 3, (int)player.posZ + a2 + 1, ListMisc.Darkness);	
						DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX + a1 + 1, (int)player.posY + 4, (int)player.posZ + a2 + 1, ListMisc.Darkness);	
	
					}
				}
				
				super.use(player);
			}
		}
	}
	
	public static class BlackHole extends Ability
	{
		public BlackHole() 
		{
			super(ListAttributes.BLACKHOLE); 
		}
		
		public void use(final EntityPlayer player)
		{				
			if(!this.isOnCooldown)
			{
				if(MainConfig.enableGriefing)
				{
					for(int i = -7; i < 7; i++)
					for(int j = -5; j < 5; j++)
					for(int k = -7; k < 7; k++)
					{
						if(!player.worldObj.isAirBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) && player.worldObj.getBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) != ListMisc.Ope
								&& player.worldObj.getBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) != ListMisc.OpeMid && player.worldObj.getBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) != Blocks.bedrock)
							player.worldObj.setBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k, ListMisc.Darkness);				
					}
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BLACKHOLE, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
				}
				
				super.use(player);
			}		
		}
	}
	
	public static class Liberation extends Ability
	{
		public int liberationCount = 0;
		
		public Liberation() 
		{
			super(ListAttributes.LIBERATION); 
		}
		
		/** TODO Explosion particles */
		public void use(final EntityPlayer player)
		{
			int libCount = 0;
			
			if(!this.isOnCooldown)
			{
				if(liberationCount > 0)
				{
					MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);	
					
					if(mop != null)
					{
						double x = mop.blockX;
						double y = mop.blockY;
						double z = mop.blockZ;

						while(liberationCount > 0)
						{
							AbilityProjectile proj = new YamiProjectiles.Liberation(player.worldObj, player, ListExtraAttributes.LIBERATIONBLOCK);	
							proj.setLocationAndAngles(x + WyMathHelper.randomWithRange(-3, 3), (y + 14) + WyMathHelper.randomWithRange(0, 4), z + WyMathHelper.randomWithRange(-3, 3), 0, 0);
							proj.motionX = 0;
							proj.motionZ = 0;
							proj.motionY = -0.7 - player.worldObj.rand.nextDouble();
							player.worldObj.spawnEntityInWorld(proj);
							liberationCount--;	
						}					
					}
				}
				else
				{
					for(int x = -20; x < 20; x++)
					for(int y = -20; y < 20; y++)
					for(int z = -20; z < 20; z++)
					{
						if( player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == ListMisc.Darkness)
						{
							player.worldObj.setBlockToAir((int)(int) player.posX + x, (int)(int) player.posY + y, (int)(int) player.posZ + z);
							libCount++;
							if(libCount % 10 == 0)
								liberationCount++;
						}
					}
				}
				
				super.use(player);
			}		
		}
	}
	
	public static class Kurouzu extends Ability
	{
		public Kurouzu() 
		{
			super(ListAttributes.KUROUZU); 
		}
		
		public void use(final EntityPlayer player)
		{				
			
			//super.use(player);
		}
	}
	
	public static class DarkMatter extends Ability
	{
		public DarkMatter() 
		{
			super(ListAttributes.DARKMATTER);
		}
		
		public void use(final EntityPlayer player)
		{				
			this.projectile = new YamiProjectiles.DarkMatter(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
}

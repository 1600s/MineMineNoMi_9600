package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SunaProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class SunaAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new Barjan(), new Sables(), new GroundDeath(), new DesertSpada(), new DesertEncierro(), new DesertGirasole()};

	public static class DesertGirasole extends Ability
	{
		public DesertGirasole() 
		{
			super(ListAttributes.DESERTGIRASOLE); 
		}
		
		public void startCharging(EntityPlayer player)
		{
			if(!this.isOnCooldown)
				WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DESERTGIRASOLE, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			super.startCharging(player);
		}
		
		public void endCharging(EntityPlayer player)
		{		
			if(!this.isOnCooldown)
			{
				if(MainConfig.enableGriefing)
				{
					for(int i = -15; i < 15; i++)
					for(int j = -5; j < 5; j++)
					for(int k = -15; k < 15; k++)
					{
						int posX = (int) (player.posX + i + (i < -WyMathHelper.randomWithRange(8, 12) || i > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						int posY = (int) player.posY + j;
						int posZ = (int) (player.posZ + k + (k < -WyMathHelper.randomWithRange(8, 12) || k > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						
						if(!player.worldObj.isAirBlock(posX, posY, posZ) && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope
								&& player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
							player.worldObj.setBlock(posX, posY, posZ, ListMisc.SunaSand);				
					}
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DESERTGIRASOLE2, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
				}

				super.endCharging(player);
			}	
		}
	}
	
	public static class DesertEncierro extends Ability
	{
		public DesertEncierro() 
		{
			super(ListAttributes.DESERTENCIERRO); 
		}
	}
	
	public static class Barjan extends Ability
	{
		public Barjan() 
		{
			super(ListAttributes.BARJAN); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new SunaProjectiles.Barjan(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class Sables extends Ability
	{
		public Sables() 
		{
			super(ListAttributes.SABLES); 
		}	
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{	
			double newPosX = 0, newPosY = 0, newPosZ = 0;
			
			newPosY += 10;
			target.addPotionEffect(new PotionEffect(Potion.hunger.id, 500, 1));
			Direction dir = WyHelper.get4Directions(player);
			if(dir == WyHelper.Direction.SOUTH)
				newPosX += WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.EAST)
				newPosX -= WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.NORTH)
				newPosZ += WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.WEST)  
				newPosZ -= WyMathHelper.randomWithRange(-10, 10);

			WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_SABLES, target), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			target.setPositionAndUpdate(target.posX + newPosX, target.posY + newPosY, target.posZ + newPosZ);
			
			super.hitEntity(player, target);
		}
	}
	
	public static class GroundDeath extends Ability
	{
		public GroundDeath() 
		{
			super(ListAttributes.GROUNDDEATH); 
		}
		
		public void use(EntityPlayer player)
		{	
			if(!this.isOnCooldown())
			{
				if(MainConfig.enableGriefing)
				{
					for(EntityLivingBase l : WyHelper.getEntitiesNear(player, 25))
					{
						for(int i = -2; i < 2; i++)
						for(int j = -3; j < 3; j++)
						for(int k = -2; k < 2; k++)
						{
							l.worldObj.setBlock((int) l.posX + k, (int) l.posY - j, (int) l.posZ + i, Blocks.air);
							l.worldObj.setBlock((int) l.posX + k, (int) l.posY + j, (int) l.posZ + i, Blocks.sand);
						}				
					}	
					
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GROUNDDEATH, player.posX, player.posY, player.posZ), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);			
				}
				
				super.use(player);
			}
		}
	}
	
	public static class DesertSpada extends Ability
	{
		public DesertSpada() 
		{
			super(ListAttributes.DESERTSPADA); 
		}
		
		public void use(EntityPlayer player)
		{		
			if(!isOnCooldown)
			{
				if(MainConfig.enableGriefing)
				{
					if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
					{
						for(int i = -4; i < 6; i++)
						for(int j = 0; j < 5; j++)
						for(int k = 0; k < 30; k++)		
						{
							int posX = (int) player.posX + i;
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ - (k + 2);
							
							if(player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
							{
								player.worldObj.setBlock(posX, posY, posZ, Blocks.air);
								player.worldObj.setBlock(posX, posY, posZ, ListMisc.SunaSand);
							}
						}
					}
					else if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
					{
						for(int i = -4; i < 6; i++)
						for(int j = 0; j < 5; j++)
						for(int k = 0; k < 30; k++)		
						{
							int posX = (int) player.posX + i;
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ + (k + 2);
							
							if(player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
							{
								player.worldObj.setBlock(posX, posY, posZ, Blocks.air);
								player.worldObj.setBlock(posX, posY, posZ, ListMisc.SunaSand);
							}
						}
					}
					else if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
					{
						for(int i = 0; i < 30; i++)
						for(int j = 0; j < 5; j++)
						for(int k = -4; k < 6; k++)		
						{
							int posX = (int) player.posX + (i + 2);
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ + k;
							
							if(player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
							{
								player.worldObj.setBlock(posX, posY, posZ, Blocks.air);
								player.worldObj.setBlock(posX, posY, posZ, ListMisc.SunaSand);
							}
						}
					}
					else if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
					{
						for(int i = 0; i < 30; i++)
						for(int j = 0; j < 5; j++)
						for(int k = -4; k < 6; k++)
						{
							int posX = (int) player.posX - (i + 2);
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ + k;
							
							if(player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
							{
								player.worldObj.setBlock(posX, posY, posZ, Blocks.air);
								player.worldObj.setBlock(posX, posY, posZ, ListMisc.SunaSand);
							}
						}	
					}		
				}
				super.use(player);
			}
		}
	}
	
}

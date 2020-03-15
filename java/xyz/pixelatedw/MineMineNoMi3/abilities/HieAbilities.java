package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.Circle;
import xyz.pixelatedw.MineMineNoMi3.api.math.ICircle;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HieProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class HieAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new IceBlockPartisan(), new IceSaber(), new IceAge(), new IceBall(), new IceTimeCapsule(), new IceBlockPheasant()};
	
	public static class IceSaber extends Ability
	{
		public IceSaber()
		{
			super(ListAttributes.ICESABER); 
		}
		
		public void startPassive(EntityPlayer player) 
		{
			if(player.inventory.getCurrentItem() == null)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ListMisc.IceSaber));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			player.inventory.clearInventory(ListMisc.IceSaber, -1);
		}
	}
		
	
	public static class IceBlockPartisan extends Ability
	{
		public IceBlockPartisan() 
		{
			super(ListAttributes.ICEBLOCKPARTISAN); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new HieProjectiles.IceBlockPartisan(player.worldObj, player, ListAttributes.ICEBLOCKPARTISAN);
			super.use(player);
		};	
	}

	public static class IceAge extends Ability
	{
		public IceAge() 
		{
			super(ListAttributes.ICEAGE); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{		
				final World world = player.worldObj;
				if(MainConfig.enableGriefing)
				{
					for(int i = -15; i < 15; i++)
					for(int j = -10; j < 10; j++)
					for(int k = -15; k < 15; k++)
					{
						int posX = (int) (player.posX + i + (i < -WyMathHelper.randomWithRange(8, 12) || i > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						int posY = (int) player.posY + j;
						int posZ = (int) (player.posZ + k + (k < -WyMathHelper.randomWithRange(8, 12) || k > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						
						if(!player.worldObj.isAirBlock(posX, posY, posZ) && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope
								&& player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
							player.worldObj.setBlock(posX, posY, posZ, Blocks.packed_ice);				
					}
					
					/*Sphere.generateFilled((int) player.posX, (int) player.posY, (int) player.posZ, 20, new ISphere()
					{
						public void call(int x, int y, int z)
						{	
							int posX = x;
							int posY = y;
							int posZ = z;
							
							if(!player.worldObj.isAirBlock(posX, posY, posZ) && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
								player.worldObj.setBlock(posX, posY, posZ, Blocks.packed_ice);
						}
					});*/
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ICEAGE, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
				}
				
				super.use(player);
			}
		}
	}
	
	public static class IceBall extends Ability
	{
		public IceBall() 
		{
			super(ListAttributes.ICEBALL); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new HieProjectiles.IceBall(player.worldObj, player, ListAttributes.ICEBALL);
			super.use(player);
		};	
	}
	
	public static class IceTimeCapsule extends Ability
	{
		public IceTimeCapsule() 
		{
			super(ListAttributes.ICETIMECAPSULE); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown())
			{
				if(MainConfig.enableGriefing)
				{
					for(EntityLivingBase l : WyHelper.getEntitiesNear(player, 25))
					{
						WyHelper.createCube(l, new int[] {2, 4, 2}, Blocks.packed_ice);
					}	
				}
			
				super.use(player);
			}
		};	
	}
	
	public static class IceBlockPheasant extends Ability
	{
		public IceBlockPheasant() 
		{
			super(ListAttributes.ICEBLOCKPHEASANT); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new HieProjectiles.IceBlockPheasant(player.worldObj, player, ListAttributes.ICEBLOCKPHEASANT);
			super.use(player);
		};		
	}
	
}

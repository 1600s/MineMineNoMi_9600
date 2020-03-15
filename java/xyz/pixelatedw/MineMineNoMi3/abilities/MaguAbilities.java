package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MaguProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class MaguAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new BakuretsuKazan(), new RyuseiKazan(), new Meigo(), new DaiFunka()};

	public static class BakuretsuKazan extends Ability
	{
		public BakuretsuKazan() 
		{
			super(ListAttributes.BAKURETSUKAZAN); 
		}
		
		public void use(EntityPlayer player)
		{		
			if(!this.isOnCooldown)
			{				
				final World world = player.worldObj;
				if(MainConfig.enableGriefing)
				{
					Sphere.generateFilled((int) player.posX, (int) player.posY, (int) player.posZ, 10, new ISphere()
					{
						public void call(int x, int y, int z)
						{	
							int posX = x;
							int posY = y;
							int posZ = z;
							
							if(!player.worldObj.isAirBlock(posX, posY, posZ) && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope && player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid && player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
								player.worldObj.setBlock(posX, posY, posZ, Blocks.lava);
						}
					});
				}

				super.use(player);
			}
		} 
	}
	
	public static class RyuseiKazan extends Ability
	{
		public RyuseiKazan() 
		{
			super(ListAttributes.RYUSEIKAZAN); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new MaguProjectiles.DaiFunka(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Meigo extends Ability
	{
		public Meigo() 
		{
			super(ListAttributes.MEIGO); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new MaguProjectiles.Meigo(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class DaiFunka extends Ability
	{
		public DaiFunka() 
		{
			super(ListAttributes.DAIFUNKA); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new MaguProjectiles.DaiFunka(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
}

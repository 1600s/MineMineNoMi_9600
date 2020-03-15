package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.NikyuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class NikyuAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new PadHo(), new TsuppariPadHo(), new Hanpatsu(), new UrsusShock()};
	
	public static class Hanpatsu extends Ability
	{
		public Hanpatsu() 
		{
			super(ListAttributes.HANPATSU); 
		}
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{			
			double newPosX = 0, newPosY = 0, newPosZ = 0;
			
			newPosY += 55;
			Direction dir = WyHelper.get4Directions(player);
			if(dir == WyHelper.Direction.SOUTH)
				newPosX += WyMathHelper.randomWithRange(-200, 200);
			else if(dir == WyHelper.Direction.EAST)
				newPosX -= WyMathHelper.randomWithRange(-200, 200);
			else if(dir == WyHelper.Direction.NORTH)
				newPosZ += WyMathHelper.randomWithRange(-200, 200);
			else if(dir == WyHelper.Direction.WEST)  
				newPosZ -= WyMathHelper.randomWithRange(-200, 200);

			target.setPositionAndUpdate(target.posX + newPosX, target.posY + newPosY, target.posZ + newPosZ);

			super.hitEntity(player, target);
		}
	}
	
	public static class TsuppariPadHo extends Ability
	{
		public TsuppariPadHo() 
		{
			super(ListAttributes.TSUPPARIPADHO); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new NikyuProjectiles.PadHo(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class PadHo extends Ability
	{
		public PadHo() 
		{
			super(ListAttributes.PADHO); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new NikyuProjectiles.PadHo(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class UrsusShock extends Ability
	{
		public UrsusShock() 
		{
			super(ListAttributes.URSUSSHOCK); 
		}
		
		public void endCharging(EntityPlayer player)
		{		
			this.projectile = new NikyuProjectiles.UrsusShock(player.worldObj, player, attr);
			super.endCharging(player);
		}
	}
	
	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
}

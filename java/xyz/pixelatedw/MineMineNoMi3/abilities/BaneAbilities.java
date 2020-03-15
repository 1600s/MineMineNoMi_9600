package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BaneProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class BaneAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new SpringDeathKnock(), new SpringSnipe(), new SpringHopper()};
	 
	public static class SpringDeathKnock extends Ability
	{
		public SpringDeathKnock() 
		{
			super(ListAttributes.SPRINGDEATHKNOCK); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new BaneProjectiles.SpringDeathKnock(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class SpringSnipe extends Ability
	{
		public SpringSnipe() 
		{
			super(ListAttributes.SPRINGSNIPE); 
		}	
		
		public void endCharging(EntityPlayer player)
		{	
			double mX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
			double mZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
			double mY = (double)(-MathHelper.sin((player.rotationPitch + 0) / 180.0F * (float)Math.PI) * 0.4);		        
				
			double f2 = MathHelper.sqrt_double(mX * mX + mY * mY + mZ * mZ);
			mX /= (double)f2;
			mY /= (double)f2;
			mZ /= (double)f2;
			mX += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
			mY += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
			mZ += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
			mX *= 8;
			mY *= 3;
			mZ *= 8;

			motion("=", mX, mY, mZ, player);
			
			super.endCharging(player);
	    }
		
	    public void duringCooldown(EntityPlayer player, int currentCooldown)
	    {
			if((currentCooldown / 20) > (ListAttributes.SPRINGSNIPE.getAbilityCooldown() / 20) - 3)
				for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 8);
	    }
	}
	
	public static class SpringHopper extends Ability
	{
		public SpringHopper() 
		{
			super(ListAttributes.SPRINGHOPPER); 
		}

	    public void endCharging(EntityPlayer player)
	    {
			Direction dir = WyHelper.get8Directions(player);

			if(player.onGround)
				motion("+", 0, 1.2 + (double)1/2, 0, (EntityPlayerMP) player);
			else
				motion("+", 0, 1.36 + (double)1/7, 0, (EntityPlayerMP) player);
	
			if(dir == WyHelper.Direction.NORTH) 		motion("-", 0, 0, 1.4 + (double)1/2, player);
			if(dir == WyHelper.Direction.NORTH_WEST) {	motion("-", 1.4 + (double)1/2, 0, 1.4 + (double)1/2, player);}
			if(dir == WyHelper.Direction.SOUTH)			motion("+", 0, 0, 1.4 + (double)1/2, player);
			if(dir == WyHelper.Direction.NORTH_EAST) {	motion("-", 0, 0, 1.4 + (double)1/2, player);motion("+", 1.4 + (double)1/2, 0, 0, player);}
			if(dir == WyHelper.Direction.WEST) 			motion("-", 1.4 + (double)1/2, 0, 0, player);
			if(dir == WyHelper.Direction.SOUTH_WEST) {	motion("+", 0, 0, 1.4 + (double)1/2, player);motion("-", 1.4 + (double)1/2, 0, 0, player);}
			if(dir == WyHelper.Direction.EAST) 			motion("+", 1.4 + (double)1/2, 0, 0, player);
			if(dir == WyHelper.Direction.SOUTH_EAST) {	motion("+", 1.4 + (double)1/2, 0, 1.4 + (double)1/2, player);}
			
			super.endCharging(player);
	    }
	}

	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
}

package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.GoroAbilities.ElThor;
import xyz.pixelatedw.MineMineNoMi3.abilities.GoroAbilities.Kari;
import xyz.pixelatedw.MineMineNoMi3.abilities.GoroAbilities.Raigo;
import xyz.pixelatedw.MineMineNoMi3.abilities.GoroAbilities.Sango;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MokuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class MokuAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new WhiteOut(), new WhiteSnake(), new WhiteLauncher(), new WhiteStrike()};
	
	public static class WhiteStrike extends Ability
	{
		public WhiteStrike() 
		{
			super(ListAttributes.WHITESTRIKE); 
		}
		
		public void use(EntityPlayer player)
		{	
			if(!this.isOnCooldown())
				WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_WHITESTRIKE, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			super.use(player);
		}
	}
	
	public static class WhiteLauncher extends Ability
	{
		public WhiteLauncher() 
		{
			super(ListAttributes.WHITELAUNCHER); 
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
			mX *= 5;
			mY *= 1.5;
			mZ *= 5;
		
			motion("=", mX, mY, mZ, player);

			super.endCharging(player);
	    }
		
	    public void duringCooldown(EntityPlayer player, int currentCooldown)
	    {
			if((currentCooldown / 20) > (ListAttributes.WHITELAUNCHER.getAbilityCooldown() / 20) - 3)
			{
				for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 2);
		    	WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_WHITELAUNCHER, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			}
	    }
	}
	
	public static class WhiteSnake extends Ability
	{
		public WhiteSnake() 
		{
			super(ListAttributes.WHITESNAKE); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new MokuProjectiles.WhiteSnake(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class WhiteOut extends Ability
	{
		public WhiteOut() 
		{
			super(ListAttributes.WHITEOUT); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new MokuProjectiles.WhiteOut(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
	
}

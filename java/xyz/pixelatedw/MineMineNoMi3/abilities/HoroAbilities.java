package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities.Barrier;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities.BarrierBall;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities.BarrierCrash;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class HoroAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new NegativeHollow(), new MiniHollow(), new TokuHollow()};
	
	public static class TokuHollow extends Ability
	{
		public TokuHollow() 
		{
			super(ListAttributes.TOKUHOLLOW); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new HoroProjectiles.TokuHollow(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class MiniHollow extends Ability
	{
		public MiniHollow() 
		{
			super(ListAttributes.MINIHOLLOW); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new HoroProjectiles.MiniHollow(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class NegativeHollow extends Ability
	{
		public NegativeHollow() 
		{
			super(ListAttributes.NEGATIVEHOLLOW); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new HoroProjectiles.NegativeHollow(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	
}

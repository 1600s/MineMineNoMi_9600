package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BomuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class BomuAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new KickBomb(), new NoseFancyCannon()};

	public static class KickBomb extends Ability
	{
		public KickBomb() 
		{
			super(ListAttributes.KICKBOMB); 
		}
		
		public void use(EntityPlayer player)
		{			
			super.use(player);
		} 
	}
	
	public static class NoseFancyCannon extends Ability
	{
		public NoseFancyCannon() 
		{
			super(ListAttributes.NOSEFANCYCANNON); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new BomuProjectiles.NoseFancyCannon(player.worldObj, player, attr);
			super.use(player);
		} 
	}
}

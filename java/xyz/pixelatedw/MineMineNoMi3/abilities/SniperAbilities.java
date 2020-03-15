package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SniperProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class SniperAbilities
{

	public static Ability KAENBOSHI = new KaenBoshi();
	public static Ability KEMURIBOSHI = new KemuriBoshi();
	public static Ability RENPATSUNAMARIBOSHI = new RenpatsuNamariBoshi();
	public static Ability SAKURETSUSABOTENBOSHI = new SakuretsuSabotenBoshi();
	
	public static Ability[] abilitiesArray = new Ability[] {KAENBOSHI, KEMURIBOSHI, RENPATSUNAMARIBOSHI, SAKURETSUSABOTENBOSHI};
	
	public static class SakuretsuSabotenBoshi extends Ability
	{
		public SakuretsuSabotenBoshi() 
		{
			super(ListAttributes.SAKURETSUSABOTENBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.SakuretsuSabotenBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class RenpatsuNamariBoshi extends Ability
	{
		public RenpatsuNamariBoshi() 
		{
			super(ListAttributes.RENPATSUNAMARIBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.RenpatsuNamariBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class KemuriBoshi extends Ability
	{
		public KemuriBoshi() 
		{
			super(ListAttributes.KEMURIBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.KemuriBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class KaenBoshi extends Ability
	{
		public KaenBoshi() 
		{
			super(ListAttributes.KAENBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.KaenBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
}

package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BaneProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SukeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class SukeAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new ShishaNoTe(), new Skatting()};
	
	public static class ShishaNoTe extends Ability
	{
		public ShishaNoTe() 
		{
			super(ListAttributes.SHISHANOTE); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new SukeProjectiles.ShishaNoTe(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Skatting extends Ability
	{
		public Skatting() 
		{
			super(ListAttributes.SKATTING); 
		}	
	}

}

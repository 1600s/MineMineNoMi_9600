package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GuraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.Heart;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class GuraAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new Kaishin(), new Kabutowari(), new ShimaYurashi(), new Gekishin()};

	public static class Gekishin extends Ability
	{
		public Gekishin() 
		{
			super(ListAttributes.GEKISHIN); 
		}
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{			
			super.hitEntity(player, target);
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), 100);
			WyHelper.explosion(player, target.posX, target.posY, target.posZ, 3);
			WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GEKISHIN, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
		}
	}
	
	public static class Kaishin extends Ability
	{
		public Kaishin() 
		{
			super(ListAttributes.KAISHIN); 
		}
		
		public void use(EntityPlayer player)
		{			
			this.projectile = new GuraProjectiles.Kaishin(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Kabutowari extends Ability
	{
		public Kabutowari() 
		{
			super(ListAttributes.KABUTOWARI); 
		}
		
		public void use(EntityPlayer player)
		{			
			super.use(player);
		} 
	}
	
	public static class ShimaYurashi extends Ability
	{
		public ShimaYurashi() 
		{
			super(ListAttributes.SHIMAYURASHI); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new GuraProjectiles.ShimaYurashi(player.worldObj, player, attr);
			super.use(player);
		} 
	}
}

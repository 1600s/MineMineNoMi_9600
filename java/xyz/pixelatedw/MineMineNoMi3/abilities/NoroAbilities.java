package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.NoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.Heart;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class NoroAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new NoroNoroBeam(), new NoroNoroBeamSword(), new KyubiRush()};
	
	public static class NoroNoroBeamSword extends Ability
	{
		public NoroNoroBeamSword()
		{
			super(ListAttributes.NORONOROBEAMSWORD); 
		}
		
		public void startPassive(EntityPlayer player) 
		{
			if(player.inventory.getCurrentItem() == null)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ListMisc.NoroNoroBeamSword));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			player.inventory.clearInventory(ListMisc.NoroNoroBeamSword, -1);
		}
	}
		
	
	public static class KyubiRush extends Ability
	{
		public KyubiRush() 
		{
			super(ListAttributes.KYUBIRUSH); 
		}
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			float damageFromSlowness = 0;
			
			if(target.isPotionActive(Potion.moveSlowdown.id))
			{
				damageFromSlowness = (float) (Math.sqrt(target.getActivePotionEffect(Potion.moveSlowdown).getDuration()) / 2);
				
				int newTime = target.getActivePotionEffect(Potion.moveSlowdown).getDuration() / 2;
				int newAmplifier = target.getActivePotionEffect(Potion.moveSlowdown).getAmplifier() - 5;
				
				target.removePotionEffect(Potion.moveSlowdown.id);
				target.removePotionEffect(Potion.digSlowdown.id);			
				target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, newTime, newAmplifier));
				target.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, newTime, newAmplifier));
			}
			else
				damageFromSlowness = 2;

			super.hitEntity(player, target);
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), damageFromSlowness);
		}
	}	
	
	public static class NoroNoroBeam extends Ability
	{
		public NoroNoroBeam() 
		{
			super(ListAttributes.NORONOROBEAM); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new NoroProjectiles.NoroNoroBeam(player.worldObj, player, attr);
			super.use(player);
		} 
	}

}

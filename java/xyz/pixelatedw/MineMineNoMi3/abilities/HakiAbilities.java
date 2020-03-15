package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class HakiAbilities 
{		
	public static Ability KENBUNSHOKUHAKI = new KenbunshokuHaki();
	public static Ability BUSOSHOKUHAKI = new BusoshokuHaki();
	
	public static Ability[] abilitiesArray = new Ability[] {KENBUNSHOKUHAKI, BUSOSHOKUHAKI};
	
	public static class KenbunshokuHaki extends Ability
	{
		public KenbunshokuHaki() 
		{
			super(ListAttributes.KENBUNSHOKUHAKI); 
		}
		
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.triggerActiveHaki(true);
			props.triggerKenHaki(true);
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.triggerActiveHaki(false);
			props.triggerKenHaki(false);
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
	}
	
	public static class BusoshokuHaki extends Ability
	{
		public BusoshokuHaki() 
		{
			super(ListAttributes.BUSOSHOKUHAKI); 			
		}
		
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.triggerActiveHaki(true);
			props.triggerBusoHaki(true);

			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.triggerActiveHaki(false);
			props.triggerBusoHaki(false);
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}

		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			ExtendedEntityStats propz = ExtendedEntityStats.get(target);
			int powerDifference = props.getDoriki() - propz.getDoriki();
			float damageFromDoriki = 2;
			if(powerDifference > 0)
				damageFromDoriki = (float) (Math.sqrt(powerDifference) / 2);
			super.hitEntity(player, target);
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), damageFromDoriki);
		}
	}
}

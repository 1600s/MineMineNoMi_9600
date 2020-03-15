package xyz.pixelatedw.MineMineNoMi3.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class UltraCola extends ItemFood
{

	public UltraCola()
	{
		super(0, false);
		this.maxStackSize = 16;  
	} 
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		player.setItemInUse(itemStack, itemUseDuration);
		return itemStack;
	}
	
	
	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) 
	{
		if(!world.isRemote)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(props.getRace().equals(ID.RACE_CYBORG))
			{
				if(props.getUltraColaConsumed() <= Values.MAX_ULTRACOLA - 1)
				{
					props.addUltraCola();
					props.setMaxCola(props.getMaxCola() + 15);
					if(props.getCola() + 10 > props.getMaxCola())
						props.setCola(props.getMaxCola());
					else						
						props.alterCola(10);
				}
				else
				{
					player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 0));
					player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0));
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0));
					if(props.getCola() <= props.getMaxCola() - 30) 
						props.alterCola(30);
					else 
						props.setCola(props.getMaxCola());
				}
			}
			else
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 250, 0));
				
	    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
	    		WyTelemetry.addStat("bottlesOfUltraColaDrank", 1);
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}			
	}
}

package xyz.pixelatedw.MineMineNoMi3.items.dials;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.MainKeys;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class DialImpact extends Item
{
	
	public DialImpact()
	{
		this.setMaxStackSize(16);
		this.setMaxDamage(1);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{		
		if(!world.isRemote)
		{
	    	if(!player.isSneaking())
	    	{
				player.addPotionEffect(new PotionEffect(Potion.resistance.id, 1, 100));
				world.newExplosion(player, player.posX, player.posY, player.posZ, 3, false, MainConfig.enableGriefing);
				
		    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
		    		WyTelemetry.addStat("impactDialsUsed", 1);
				
				itemStack.damageItem(2, player);
	    	}	    
		}
		
		return itemStack;
	}
	
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int i1, int i2, int i3, int i4, float f1, float f2, float f3)
    {
    	if(!world.isRemote && player.isSneaking())
    	{
	    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
	    		WyTelemetry.addStat("axeDialsPlaced", 1);
    		
	    	world.setBlock(i1, i2 + 1, i3, ListMisc.DialImpactBlock);
	    	itemStack.stackSize--;
    	}
        return false;
    }

}

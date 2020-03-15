package xyz.pixelatedw.MineMineNoMi3.items.dials;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainKeys;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ExtraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ExtraProjectiles.AxeDialProjectile;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class DialAxe extends Item
{
	
	public DialAxe()
	{
		this.setMaxStackSize(16);
		this.setMaxDamage(2);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{		
		if(!world.isRemote)
		{
	    	if(!player.isSneaking()) //GuiScreen.isShiftKeyDown()
	    	{
	    		AxeDialProjectile proj = new AxeDialProjectile(player.worldObj, player, ListExtraAttributes.DIALAXE);
				
	    		world.spawnEntityInWorld(proj);
	    		
		    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
		    		WyTelemetry.addStat("axeDialsUsed", 1);
	    		
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
    		
	    	world.setBlock(i1, i2 + 1, i3, ListMisc.DialAxeBlock);
	    	itemStack.stackSize--;
    	}
        return false;
    }

}

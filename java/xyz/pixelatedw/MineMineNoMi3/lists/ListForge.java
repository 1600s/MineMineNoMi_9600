package xyz.pixelatedw.MineMineNoMi3.lists;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.MainKeys;
import xyz.pixelatedw.MineMineNoMi3.events.EventExtendedProperties;
import xyz.pixelatedw.MineMineNoMi3.events.EventsDrops;
import xyz.pixelatedw.MineMineNoMi3.events.EventsEnchantments;
import xyz.pixelatedw.MineMineNoMi3.events.EventsMorphs;
import xyz.pixelatedw.MineMineNoMi3.events.EventsPersistence;
import xyz.pixelatedw.MineMineNoMi3.events.EventsQuestsProgress;
import xyz.pixelatedw.MineMineNoMi3.gui.GUICombatMode;

public class ListForge 
{

	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new EventExtendedProperties());
		MinecraftForge.EVENT_BUS.register(new EventsDrops());
		MinecraftForge.EVENT_BUS.register(new EventsEnchantments());
		MinecraftForge.EVENT_BUS.register(new EventsPersistence());
		MinecraftForge.EVENT_BUS.register(new EventsQuestsProgress());
		
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			MinecraftForge.EVENT_BUS.register(new GUICombatMode(Minecraft.getMinecraft()));
			MinecraftForge.EVENT_BUS.register(new EventsMorphs(Minecraft.getMinecraft()));
			FMLCommonHandler.instance().bus().register(new MainKeys());
			MainKeys.init();
		}
	}
	
}

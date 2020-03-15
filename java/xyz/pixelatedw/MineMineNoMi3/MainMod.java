package xyz.pixelatedw.MineMineNoMi3;

import org.apache.logging.log4j.LogManager;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandBelly;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandBounty;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandDoriki;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandExtol;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandFG;
import xyz.pixelatedw.MineMineNoMi3.commands.CommandRemoveDF;
import xyz.pixelatedw.MineMineNoMi3.gui.GUIHandler;
import xyz.pixelatedw.MineMineNoMi3.lists.ListBiomes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListCraftingRecipes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListCreativeTabs;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;
import xyz.pixelatedw.MineMineNoMi3.lists.ListEffects;
import xyz.pixelatedw.MineMineNoMi3.lists.ListEntities;
import xyz.pixelatedw.MineMineNoMi3.lists.ListForge;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.lists.ListPackets;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.proxy.CommonProxy;
import xyz.pixelatedw.MineMineNoMi3.world.MainWorldGen;

@Mod(modid = ID.PROJECT_ID, name = ID.PROJECT_NAME, version = ID.PROJECT_VERSION, acceptedMinecraftVersions = "[1.7.10]")
public class MainMod
{
	@Instance(ID.PROJECT_ID)
	private static MainMod instance;
	@SidedProxy(clientSide = "xyz.pixelatedw.MineMineNoMi3.proxy.ClientProxy", serverSide = "xyz.pixelatedw.MineMineNoMi3.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static SimpleNetworkWrapper dispatcher;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("mineminenomi");
		MainConfig.init(event.getSuggestedConfigurationFile());

		ListPackets.init();
		ListEntities.init();
		ListBiomes.init();

		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		GameRegistry.registerWorldGenerator(new MainWorldGen(), 1);

		ListDevilFruits.init();
		ListMisc.init();
		ListEffects.init();
		ListCreativeTabs.init();
		ListCraftingRecipes.init();
		ListForge.init();

		ListQuests.init();

		proxy.init();

		if(WyDebug.isDebug())
		{
			WyHelper.generateLangFiles();
			WyHelper.generateExtraTypScriptFiles();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
/*		if (!WyDebug.isDebug())
		{
			WyTelemetry.addStat(ID.PROJECT_MCVERSION.replace(".", "") + "onlinePlayers", 1);
			Runtime.getRuntime().addShutdownHook(new Thread()
			{
				public void run()
				{
					try
					{
						WyTelemetry.addStat(ID.PROJECT_MCVERSION.replace(".", "") + "onlinePlayers", -1);
						this.sleep(100);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			});
		}*/
	}

	@EventHandler
	public void serverInit(FMLServerStartingEvent event)
	{
		// event.registerServerCommand(new CommandAbility());
		if (ID.DEV_EARLYACCESS || WyDebug.isDebug())
			event.registerServerCommand(new CommandFG());
		event.registerServerCommand(new CommandDoriki());
		event.registerServerCommand(new CommandBelly());
		event.registerServerCommand(new CommandBounty());
		event.registerServerCommand(new CommandExtol());
		event.registerServerCommand(new CommandRemoveDF());
	}

	public static MainMod getMineMineNoMi()
	{
		return instance;
	}
}

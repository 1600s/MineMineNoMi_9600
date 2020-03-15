package xyz.pixelatedw.MineMineNoMi3.lists;

import cpw.mods.fml.relauncher.Side;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketAbilitySync;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketQuestExtra;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketShounenScream;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorld;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorldData;

public class ListPackets 
{
	public static void init()
	{
		WyNetworkHelper.registerMessage(PacketSync.ServerHandler.class, PacketSync.class, 1, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketSync.ClientHandler.class, PacketSync.class, 4, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketPlayer.ServerHandler.class, PacketPlayer.class, 2, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketPlayer.ClientHandler.class, PacketPlayer.class, 5, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketWorld.ServerHandler.class, PacketWorld.class, 3, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketParticles.ClientHandler.class, PacketParticles.class, 6, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketSyncInfo.ClientHandler.class, PacketSyncInfo.class, 7, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketQuestSync.ClientHandler.class, PacketQuestSync.class, 8, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketQuestSync.ServerHandler.class, PacketQuestSync.class, 9, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketWorldData.ClientHandler.class, PacketWorldData.class, 10, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketAbilitySync.ClientHandler.class, PacketAbilitySync.class, 11, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketAbilitySync.ServerHandler.class, PacketAbilitySync.class, 12, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketQuestExtra.ServerHandler.class, PacketQuestExtra.class, 13, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketQuestExtra.ClientHandler.class, PacketQuestExtra.class, 14, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketShounenScream.ClientHandler.class, PacketShounenScream.class, 15, Side.CLIENT);
	} 
	
}

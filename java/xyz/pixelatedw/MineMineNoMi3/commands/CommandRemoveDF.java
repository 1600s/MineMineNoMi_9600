package xyz.pixelatedw.MineMineNoMi3.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketAbilitySync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class CommandRemoveDF extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		EntityPlayer target = null;
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());
		
		if(str.length >= 1)
		{
			if(MainConfig.commandPermissionRemoveDF != 1)		
				target = MinecraftServer.getServer().getConfigurationManager().func_152612_a(str[0]);
			else
			{
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.RED + "You don't have permission to use this command !");
				return;
			}
		}
		else
		{
			target = senderEntity;
		}
		
		ExtendedEntityStats props = ExtendedEntityStats.get(target);
		AbilityProperties abilityProps = AbilityProperties.get(target);

		props.setUsedFruit("N/A");
		props.setYamiPower(false);
		props.setIsLogia(false);
		
		abilityProps.clearHotbar();
		abilityProps.clearDevilFruitAbilities();
		target.clearActivePotions();

		props.setZoanPoint("n/a");
		
		target.clearActivePotions();
		
		WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP)target);	
		WyNetworkHelper.sendToAll(new PacketSyncInfo(target.getDisplayName(), props));	
		WyNetworkHelper.sendTo(new PacketAbilitySync(abilityProps), (EntityPlayerMP)target);	
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{		
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());
		
		if( (MainConfig.commandPermissionRemoveDF == 2 && flag) || MainConfig.commandPermissionRemoveDF < 2 )
			return true;
		else	
			return false;		
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/removedf [player]";
	}

	public String getCommandName() 
	{
		return "removedf";
	}
}


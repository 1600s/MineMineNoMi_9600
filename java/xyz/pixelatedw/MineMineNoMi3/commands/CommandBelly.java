package xyz.pixelatedw.MineMineNoMi3.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class CommandBelly extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length >= 2)
		{
			EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
			EntityPlayer target = null;
			int plusBelly = 0;		
			
			if(!str[1].equals("INF"))
				plusBelly = Integer.decode(str[1]);
			
			if(str.length == 2)
				target = this.getCommandSenderAsPlayer(sender);
			if(str.length == 3)
			{
				if(MainConfig.commandPermissionBelly != 1)				
					target = MinecraftServer.getServer().getConfigurationManager().func_152612_a(str[2]);
				else
				{
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.RED + "You don't have permission to use this command !");
					return;
				}
			}
			
			ExtendedEntityStats props = ExtendedEntityStats.get(target);

			if(str[0].equals("+"))
			{
				if(plusBelly + props.getBelly() <= Values.MAX_GENERAL)
				{
					props.alterBelly(plusBelly);
					if(props.getBellyFromCommand() + plusBelly <= Values.MAX_GENERAL)
						props.alterBellyFromCommand(plusBelly);
				}
				else
				{
					props.setBelly(Values.MAX_GENERAL);
					props.alterBellyFromCommand( Values.MAX_GENERAL - plusBelly );
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Added " + plusBelly + " doriki to " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("-"))
			{
				if(props.getBelly() - plusBelly <= 0)
				{			
					props.setBelly(0);
					if(props.getBellyFromCommand() - plusBelly > 0)
						props.alterBellyFromCommand( -(props.getBellyFromCommand() - plusBelly) );
					else
						props.setBellyFromCommand(0);
				}
				else
				{
					props.alterBelly(-plusBelly);	
					if(props.getBellyFromCommand() - plusBelly > 0)
						props.alterBellyFromCommand( -(props.getBellyFromCommand() - plusBelly) );
					else
						props.setBellyFromCommand(0);
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Subtracted " + plusBelly + " doriki from " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("="))
			{
				if(str[1].equals("INF"))
				{
					props.setBellyFromCommand( Values.MAX_GENERAL );
					props.setBelly( Values.MAX_GENERAL );
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + Values.MAX_GENERAL + " doriki"); 
				}
				else if(plusBelly <= Values.MAX_GENERAL)
				{
					props.setBellyFromCommand(plusBelly);
					props.setBelly(plusBelly);
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + plusBelly + " doriki"); 
				}				
			}
			 
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP)target);
		}	
		else
			throw new WrongUsageException("/belly <+|-|=> <amount> [player]", new Object[0]);
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{		
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());
		
		if( (MainConfig.commandPermissionBelly == 2 && flag) || MainConfig.commandPermissionBelly < 2 )
			return true;
		else	
			return false;		
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/belly <+|-|=> <amount> [player]";
	}

	public String getCommandName() 
	{
		return "belly";
	}

}

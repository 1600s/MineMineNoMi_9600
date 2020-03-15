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

public class CommandExtol extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length >= 2)
		{
			EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
			EntityPlayer target = null;
			int plusExtol = 0;		
			
			if(!str[1].equals("INF"))
				plusExtol = Integer.decode(str[1]);
			
			if(str.length == 2)
				target = this.getCommandSenderAsPlayer(sender);
			if(str.length == 3)
			{
				if(MainConfig.commandPermissionExtol != 1)				
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
				if(plusExtol + props.getExtol() <= Values.MAX_GENERAL)
				{
					props.alterExtol(plusExtol);
					if(props.getExtolFromCommand() + plusExtol <= Values.MAX_GENERAL)
						props.alterExtolFromCommand(plusExtol);
				}
				else
				{
					props.setExtol(Values.MAX_GENERAL);
					props.alterExtolFromCommand( Values.MAX_GENERAL - plusExtol );
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Added " + plusExtol + " doriki to " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("-"))
			{
				if(props.getExtol() - plusExtol <= 0)
				{			
					props.setExtol(0);
					if(props.getExtolFromCommand() - plusExtol > 0)
						props.alterExtolFromCommand( -(props.getExtolFromCommand() - plusExtol) );
					else
						props.setExtolFromCommand(0);
				}
				else
				{
					props.alterExtol(-plusExtol);	
					if(props.getExtolFromCommand() - plusExtol > 0)
						props.alterExtolFromCommand( -(props.getExtolFromCommand() - plusExtol) );
					else
						props.setExtolFromCommand(0);
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Subtracted " + plusExtol + " doriki from " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("="))
			{
				if(str[1].equals("INF"))
				{
					props.setExtolFromCommand( Values.MAX_GENERAL );
					props.setExtol( Values.MAX_GENERAL );
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + Values.MAX_GENERAL + " doriki"); 
				}
				else if(plusExtol <= Values.MAX_GENERAL)
				{
					props.setExtolFromCommand(plusExtol);
					props.setExtol(plusExtol);
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + plusExtol + " doriki"); 
				}				
			}
			 
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP)target);
		}		
		else
			throw new WrongUsageException("/extol <+|-|=> <amount> [player]", new Object[0]);
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());
		
		if( (MainConfig.commandPermissionExtol == 2 && flag) || MainConfig.commandPermissionExtol < 2 )
			return true;
		else	
			return false;	
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/extol <+|-|=> <amount> [player]";
	}

	public String getCommandName() 
	{
		return "extol";
	}

}

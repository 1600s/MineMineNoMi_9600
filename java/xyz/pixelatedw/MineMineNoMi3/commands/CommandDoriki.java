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
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class CommandDoriki extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length >= 2)
		{
			EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
			EntityPlayer target = null;
			int plusDoriki = 0;	
			
			if(!str[1].equals("INF"))
				plusDoriki = Integer.decode(str[1]);
			
			if(str.length == 2)
				target = this.getCommandSenderAsPlayer(sender);
			if(str.length == 3)
			{
				if(MainConfig.commandPermissionDoriki != 1)				
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
				if(plusDoriki + props.getDoriki() <= Values.MAX_DORIKI)
				{
					props.alterDoriki(plusDoriki);
					if(props.getDorikiFromCommand() + plusDoriki <= Values.MAX_DORIKI)
						props.alterDorikiFromCommand(plusDoriki);
				}
				else
				{
					props.setDoriki(Values.MAX_DORIKI);
					props.alterDorikiFromCommand( Values.MAX_DORIKI - plusDoriki );
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Added " + plusDoriki + " doriki to " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("-"))
			{
				if(props.getDoriki() - plusDoriki <= 0)
				{			
					props.setDoriki(0);
					if(props.getDorikiFromCommand() - plusDoriki > 0)
						props.alterDorikiFromCommand( -(props.getDorikiFromCommand() - plusDoriki) );
					else
						props.setDorikiFromCommand(0);
				}
				else
				{
					props.alterDoriki(-plusDoriki);	
					if(props.getDorikiFromCommand() - plusDoriki > 0)
						props.alterDorikiFromCommand( -(props.getDorikiFromCommand() - plusDoriki) );
					else
						props.setDorikiFromCommand(0);
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Subtracted " + plusDoriki + " doriki from " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("="))
			{
				if(str[1].equals("INF"))
				{
					props.setDorikiFromCommand( Values.MAX_DORIKI );
					props.setDoriki( Values.MAX_DORIKI );
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + Values.MAX_DORIKI + " doriki"); 
				}
				else if(plusDoriki <= Values.MAX_DORIKI)
				{
					props.setDorikiFromCommand(plusDoriki);
					props.setDoriki(plusDoriki);
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + plusDoriki + " doriki"); 
				}				
			}
			 
			DorikiEvent e = new DorikiEvent(target);
			if (MinecraftForge.EVENT_BUS.post(e))
				return;
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP)target);
		}	
		else
			throw new WrongUsageException("/doriki <+|-|=> <amount> [player]", new Object[0]);
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());
		
		if( (MainConfig.commandPermissionDoriki == 2 && flag) || MainConfig.commandPermissionDoriki < 2 )
			return true;
		else	
			return false;	
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/doriki <+|-|=> <amount> [player]";
	}

	public String getCommandName() 
	{
		return "doriki";
	}

}

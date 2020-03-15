package xyz.pixelatedw.MineMineNoMi3.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestManager;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityArlong;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityChew;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityKuroobi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityDonKrieg;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityGin;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityPearl;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMorgan;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityBazooka;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityBrickBat;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityFist;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityHydra;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityMeigo;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityNoroBeam;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPaw;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPheasant;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPhoenixFull;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPhoenixHybrid;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityShark;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntitySpear;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityTrident;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityYukiRabi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityBlueno;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityFukuro;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityJabra;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityJabraL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKaku;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKakuL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKalifa;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKumadori;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityLucci;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityLucciL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntitySpandam;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.world.TeleporterScenarioArena;

public class CommandFG extends CommandBase
{	
	
	private Quest[] questsPool = new Quest[] {ListQuests.bountyLowLevel01, ListQuests.bountyLowLevel02, ListQuests.bountyLowLevel03};
	
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length >= 1)
		{
			EntityPlayer player = this.getCommandSenderAsPlayer(sender);
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			QuestProperties questProps = QuestProperties.get(player);
			Entity toSpawn = null;
			
			if(str[0].equalsIgnoreCase("arlong"))
				toSpawn = new EntityArlong(player.worldObj);
			else if(str[0].equalsIgnoreCase("chew"))
				toSpawn = new EntityChew(player.worldObj);
			else if(str[0].equalsIgnoreCase("kuroobi"))
				toSpawn = new EntityKuroobi(player.worldObj);
			else if(str[0].equalsIgnoreCase("krieg"))
				toSpawn = new EntityDonKrieg(player.worldObj);
			else if(str[0].equalsIgnoreCase("gin"))
				toSpawn = new EntityGin(player.worldObj);
			else if(str[0].equalsIgnoreCase("pearl"))
				toSpawn = new EntityPearl(player.worldObj);
			else if(str[0].equalsIgnoreCase("lucci"))
				toSpawn = new EntityLucci(player.worldObj);
			else if(str[0].equalsIgnoreCase("luccil"))
				toSpawn = new EntityLucciL(player.worldObj);
			else if(str[0].equalsIgnoreCase("jabra"))
				toSpawn = new EntityJabra(player.worldObj);
			else if(str[0].equalsIgnoreCase("jabral"))
				toSpawn = new EntityJabraL(player.worldObj);
			else if(str[0].equalsIgnoreCase("kalifa"))
				toSpawn = new EntityKalifa(player.worldObj);
			else if(str[0].equalsIgnoreCase("kaku"))
				toSpawn = new EntityKaku(player.worldObj);
			else if(str[0].equalsIgnoreCase("kakul"))
				toSpawn = new EntityKakuL(player.worldObj);
			else if(str[0].equalsIgnoreCase("spandam"))
				toSpawn = new EntitySpandam(player.worldObj);
			else if(str[0].equalsIgnoreCase("morgan"))
				toSpawn = new EntityMorgan(player.worldObj);	
			else if(str[0].equalsIgnoreCase("fukuro"))
				toSpawn = new EntityFukuro(player.worldObj); //COMPLETED
			else if(str[0].equalsIgnoreCase("kumadori"))
				toSpawn = new EntityKumadori(player.worldObj); //COMPLETED
			else if(str[0].equalsIgnoreCase("blueno"))
				toSpawn = new EntityBlueno(player.worldObj); //COMPLETED
			
			
			else if(str[0].equalsIgnoreCase("brickbat"))
				toSpawn = new TempEntityBrickBat(player.worldObj);
			else if(str[0].equalsIgnoreCase("fist"))
				toSpawn = new TempEntityFist(player.worldObj);
			else if(str[0].equalsIgnoreCase("bazooka"))
				toSpawn = new TempEntityBazooka(player.worldObj);			
			else if(str[0].equalsIgnoreCase("hydra"))
				toSpawn = new TempEntityHydra(player.worldObj);
			else if(str[0].equalsIgnoreCase("meigo"))
				toSpawn = new TempEntityMeigo(player.worldObj);	
			else if(str[0].equalsIgnoreCase("noro"))
				toSpawn = new TempEntityNoroBeam(player.worldObj);
			else if(str[0].equalsIgnoreCase("paw"))
				toSpawn = new TempEntityPaw(player.worldObj);			
			else if(str[0].equalsIgnoreCase("pheasant"))
				toSpawn = new TempEntityPheasant(player.worldObj);
			else if(str[0].equalsIgnoreCase("shark"))
				toSpawn = new TempEntityShark(player.worldObj);				
			else if(str[0].equalsIgnoreCase("spear"))
				toSpawn = new TempEntitySpear(player.worldObj);			
			else if(str[0].equalsIgnoreCase("trident"))
				toSpawn = new TempEntityTrident(player.worldObj);
			else if(str[0].equalsIgnoreCase("yukirabi"))
				toSpawn = new TempEntityYukiRabi(player.worldObj);		
			else if(str[0].equalsIgnoreCase("phoenixfull"))
				toSpawn = new TempEntityPhoenixFull(player.worldObj);
			else if(str[0].equalsIgnoreCase("phoenixhybrid"))
				toSpawn = new TempEntityPhoenixHybrid(player.worldObj);
			
			
			else if(str[0].equalsIgnoreCase("scenario"))
			{
				if(str[1].equalsIgnoreCase("start"))
				{
					if(!player.worldObj.isRemote)
						new TeleporterScenarioArena((WorldServer) player.worldObj).teleport(player, ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN);
				}
				else if(str[1].equalsIgnoreCase("end"))
				{
					if(!player.worldObj.isRemote)
						new TeleporterScenarioArena((WorldServer) player.worldObj).endScenario(player, ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN);
				}
			}
			else if(str[0].equalsIgnoreCase("maxcola"))
			{
				if(!props.hasColaBackpack())
					props.setMaxCola(250);
				else
					props.setMaxCola(450);
			}
			else if(str[0].equalsIgnoreCase("fillcola"))
				props.setCola(props.getMaxCola());
			else if(str[0].equalsIgnoreCase("rngquest"))
			{
				//QuestManager.instance().startQuest(player, questsPool[player.getRNG().nextInt(questsPool.length)]);
				QuestManager.instance().startQuest(player, ListQuests.bountyLowLevel01);
				WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
			}
			else if(str[0].equalsIgnoreCase("resetquests"))
			{
				questProps.clearQuestTracker();
				questProps.clearCompletedQuests();
				WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
			}
											
	
			if(toSpawn != null)
			{
				toSpawn.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
				player.worldObj.spawnEntityInWorld(toSpawn);
			}
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender cmd)
	{
		return true;
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/fg <entity>";
	}

	public String getCommandName() 
	{
		return "fg";
	}

}

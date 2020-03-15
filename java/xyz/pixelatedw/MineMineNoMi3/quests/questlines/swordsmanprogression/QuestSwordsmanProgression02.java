package xyz.pixelatedw.MineMineNoMi3.quests.questlines.swordsmanprogression;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;
import xyz.pixelatedw.MineMineNoMi3.quests.IProgressionQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.ITimedQuest;

public class QuestSwordsmanProgression02 extends Quest implements ITimedQuest, IProgressionQuest
{

	public String getQuestID()
	{
		return "swordsmanprogression02";	
	}
	
	public String getQuestName()
	{
		return "Staying Alive";
	}
	
	public String[] getQuestDescription()
	{
		return new String[] 
				{
					" He agreed to train me, the following days will ",
					"be spent training my strength, stamina and how to ",
					"focus my power in my blade.",
					"But first...I need to survive this night.",
					"",
					"",
					""
				};
	}
	
	public void startQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, I18n.format("quest." + this.getQuestID() + ".started"));	
		
		this.extraData = new NBTTagCompound();	
		this.extraData.setLong("currentDays", (int) (player.worldObj.getWorldTime()));

		super.startQuest(player);
	}

	public void finishQuest(EntityPlayer player)
	{
		boolean extraDays = (int) (player.worldObj.getWorldTime()) >= (this.extraData.getLong("currentDays") + 72000) ;
		
		WyHelper.sendMsgToPlayer(player, I18n.format("quest." + this.getQuestID() + ".completed"));	

		/*
		if(extraDays)
			WyHelper.sendMsgToPlayer(player, "<Swordsman Master> Almost thought you died there kid, I'm glad that you survived but there's no time to rest, hope you're ready for your next trial !");
		else
			WyHelper.sendMsgToPlayer(player, "<Swordsman Master> Seems like it was too easy for you ?");
		*/
		super.finishQuest(player);
	}

	public boolean canStart(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		
		boolean flag1 = !props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN) || !questProps.hasQuestCompleted(ListQuests.swordsmanProgression01);
		
		if(flag1)
			return false;
		
		if(!player.worldObj.isDaytime())
		{
			WyHelper.sendMsgToPlayer(player, "<Swordsman Master> There is no point in starting this trial now it's too late, come back in the morning and we'll talk then.");
			return false;
		}
		
		return true;
	}

	public double getMaxProgress()
	{
		return 24000;
	}
	
	public boolean isFinished(EntityPlayer player)
	{	
		try
		{
			if((int) (player.worldObj.getWorldTime()) >= (this.extraData.getLong("currentDays") + 24000)) 
				return true;
		}
		catch(Exception e)
		{
			WyHelper.sendMsgToPlayer(player, "There was a major problem with this quest, please contact the mod owner asap (WITHOUT CLOSING THE GAME), it has been completed however so enjoy the rest of the storyline !");
			System.err.println("Checking different objects to check for nulls \n"
					+ "Extra Data, Stored as NBT - " + this.extraData + "\n"
					+ "Player - " + player.getDisplayName() + "\n"
					+ "Logic done on - " + (player.worldObj.isRemote ? "Client" : "Server") + "\n");
			e.printStackTrace();
			return true;
		}
		
		return false;
	}
	
	public void alterProgress(EntityPlayer player, double progress) 
	{
		super.alterProgress(player, progress);
		
		if(this.isFinished(player))
			this.finishQuest(player);	
	}

	public boolean isPrimary()
	{
		return true;
	}

	public EnumQuestlines getQuestLine()
	{
		return EnumQuestlines.SWORDSMANPROGRESSION;
	}

	public boolean isRepeatable()
	{
		return false;
	}

	@Override
	public void onTimePassEvent(EntityPlayer player)
	{
		if(!this.isFinished(player))
			this.setProgress(player, player.worldObj.getWorldTime() - this.extraData.getLong("currentDays") );
	}

}

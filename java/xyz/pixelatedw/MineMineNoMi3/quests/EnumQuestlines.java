package xyz.pixelatedw.MineMineNoMi3.quests;

import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;

public enum EnumQuestlines
{

	ARTOFWEATHERPROGRESSION("artofweatherprogression"),
	MEDICPROGRESSION("medicprogression"),
	SNIPERPROGRESSION("sniperprogression"),
	SWORDSMANPROGRESSION("swordsmanprogression",
			ListQuests.swordsmanProgression01, ListQuests.swordsmanProgression02, ListQuests.swordsmanProgression03, ListQuests.swordsmanProgression04);
	
	String questlineName;
	Quest[] quests;
	
	private EnumQuestlines(String questlineName, Quest... quests)
	{
		this.questlineName = questlineName;
		this.quests = quests;
	}
	
	public String getQuestlineName()
	{
		return this.questlineName;
	}
	
	public Quest[] getQuests()
	{
		return this.quests;
	}
}

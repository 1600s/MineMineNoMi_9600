package xyz.pixelatedw.MineMineNoMi3;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainConfig 
{
	
	private static Configuration config;
	
	public static String enableKeepIEEPAfterDeath;
	public static boolean enableShips;
	public static boolean enableCamps;
	public static boolean enableDFtoDrop;
	public static boolean enableLogiaInvulnerability;	
	public static boolean enableExtraHearts;
	public static boolean enableMobRewards;
	public static boolean enableQuestProgression;
	public static boolean enableQuests;
	public static boolean enableGriefing;
	public static boolean enableAnimeScreaming;
	public static boolean enableSpecialFlying;
	public static double rateDFDrops;
	public static double rateShipsSpawn;
	public static int maxDojoSpawn;
	
	public static boolean enableTelemetry;
	public static boolean enableUpdateMsg;
	public static boolean enableFOVModifier;	
	
	public static int enchantmentDialImpactId = 169;
	public static int enchantmentKairosekiId = 170;
	
	public static int commandPermissionRemoveDF = 2;
	public static int commandPermissionDoriki = 2;
	public static int commandPermissionBelly = 2;
	public static int commandPermissionBounty = 2;
	public static int commandPermissionExtol = 2;
	public static String[] abilityRestrictions;
	
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		config.load();
		
		enableKeepIEEPAfterDeath = config.get(Configuration.CATEGORY_GENERAL, "Keep stats after death", "auto").getString();
		
		enableShips = config.get(Configuration.CATEGORY_GENERAL, "Allow Ships to Spawn", true).getBoolean();
		rateShipsSpawn = config.get(Configuration.CATEGORY_GENERAL, "Modifier for Spawning Ships", 1).getDouble();
		//enableCamps = config.get(Configuration.CATEGORY_GENERAL, "Allow Camps to Spawn", true).getBoolean();
		enableGriefing = config.get(Configuration.CATEGORY_GENERAL, "Allow Griefing in Worlds", true).getBoolean();
		enableAnimeScreaming  = config.get(Configuration.CATEGORY_GENERAL, "Anime Screaming", false).getBoolean();
		enableSpecialFlying  = config.get(Configuration.CATEGORY_GENERAL, "Allow Special Flying", false).getBoolean();

		enableDFtoDrop = config.get(Configuration.CATEGORY_GENERAL, "Allow Devil Fruits to drop from leaves", false).getBoolean();
		rateDFDrops = config.get(Configuration.CATEGORY_GENERAL, "Rate at which Devil Fruits drop from leaves", 1).getDouble();

		enableLogiaInvulnerability = config.get(Configuration.CATEGORY_GENERAL, "Allow Logia Invulnerability", true).getBoolean();
		enableExtraHearts = config.get(Configuration.CATEGORY_GENERAL, "Receive Extra Hearts", true).getBoolean();
		enableMobRewards = config.get(Configuration.CATEGORY_GENERAL, "Allow Mob Rewards", true).getBoolean();
		
		enableQuests = config.get(Configuration.CATEGORY_GENERAL, "Allow Quests", true).getBoolean();
		enableQuestProgression = config.get(Configuration.CATEGORY_GENERAL, "Allow Quest Progression", false).getBoolean();
		maxDojoSpawn = config.get(Configuration.CATEGORY_GENERAL, "Max Dojos to Spawn per World", 5).getInt();
		
		enchantmentDialImpactId = config.get("ids", "Enchantment ID : Dial Impact", 100).getInt();
		enchantmentKairosekiId = config.get("ids", "Enchantment ID : Kairoseki", 101).getInt();
		
		enableTelemetry = config.get("system", "Allow Telemetry", true).getBoolean();
		enableUpdateMsg = config.get("system", "Allow Update Message", true).getBoolean();	
		enableFOVModifier = config.get("system", "Allow FOV Modifiers", false).getBoolean();
		
		commandPermissionRemoveDF = config.get("permissions", "Permission : /removedf", 2).getInt();
		commandPermissionDoriki = config.get("permissions", "Permission : /doriki", 2).getInt();
		commandPermissionBelly = config.get("permissions", "Permission : /belly", 2).getInt();
		commandPermissionBounty = config.get("permissions", "Permission : /bounty", 2).getInt();
		commandPermissionExtol = config.get("permissions", "Permission : /extol", 2).getInt();
		abilityRestrictions = config.get("permissions", "Ability Restrictions", new String[] {"example1", "example2"}).getStringList();		
		
		
		config.save();
	}
	 
	public static Configuration getConfig() { return config; }

}

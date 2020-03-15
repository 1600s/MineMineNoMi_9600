package xyz.pixelatedw.MineMineNoMi3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SniperAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;

public class DevilFruitsHelper
{ 

	public static String[][] zoanModels = new String[][]
	{
		{"ushiushibison", "bison"},
		{"toritoriphoenix", "phoenix"}
	};
	
	public static String[] flyingFruits = new String[]
	{
		"gasugasu", "sunasuna", "mokumoku"
	};
	
	public static Block[] nonBreakableBlocks = new Block[] { Blocks.bedrock, ListMisc.OpeMid, ListMisc.Ope, ListMisc.StringMid, ListMisc.StringWall };
	
	public static Block[] replaceableBlocks = new Block[] { Blocks.air, Blocks.tallgrass, Blocks.snow_layer, Blocks.red_flower, Blocks.yellow_flower, Blocks.water, Blocks.flowing_water, Blocks.lava, 
			Blocks.flowing_lava, Blocks.waterlily, Blocks.redstone_wire, Blocks.double_plant, Blocks.wheat, Blocks.carrots, Blocks.carpet, Blocks.cake, Blocks.sapling, Blocks.deadbush, Blocks.web,
			Blocks.wooden_pressure_plate, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.carrots, Blocks.carpet, Blocks.vine,
			ListMisc.Poison, ListMisc.DemonPoison, Blocks.torch, Blocks.redstone_torch};
	
    public static boolean setBlock(World world, int posX, int posY, int posZ, Block block)
    {
    	if(MainConfig.enableGriefing)
        	return world.setBlock(posX, posY, posZ, block);
    	
    	return false;
    }
    
    public static boolean hasBusoHakiAquired(EntityPlayer player)
    {
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		
		if(props.getRace().equalsIgnoreCase(ID.RACE_HUMAN) && props.getDoriki() >= 9000)
			return true;
			
		if(props.getRace().equalsIgnoreCase(ID.RACE_FISHMAN) && props.getDoriki() >= 9000)
			return true;
		
		if(props.getRace().equalsIgnoreCase(ID.RACE_CYBORG) && props.getDoriki() >= 8500)
			return true;
    	
    	return false;
    }
	
    public static boolean isSword(ItemStack itemStack)
    {
    	if(itemStack.getItem() instanceof ItemSword)
    		return true;
    	
    	Multimap multimap = itemStack.getAttributeModifiers();
    	if(multimap.containsKey(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()))
    		return true;	
    	
    	return false;
    }
    
	public static boolean verifyIfAbilityIsBanned(Ability a)
	{
		for(String str : MainConfig.abilityRestrictions)
		{
			if(WyHelper.getFancyName(str).contains(WyHelper.getFancyName(a.getAttribute().getAttributeName())))
				return true;
		}
		
		return false;
	}
	
	public static void validateRacialMoves(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);

		DorikiEvent e = new DorikiEvent(player);
		if (MinecraftForge.EVENT_BUS.post(e))
			return;
		
		List<Ability> tempAblList = new ArrayList<Ability>();
		
		if(props.getRace().equals(ID.RACE_HUMAN))
			for(Ability a : RokushikiAbilities.abilitiesArray)
				if(abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);
		
		if(props.getRace().equals(ID.RACE_FISHMAN))
			for(Ability a : FishKarateAbilities.abilitiesArray)
				if(abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		
		if(props.getRace().equals(ID.RACE_CYBORG))
			for(Ability a : CyborgAbilities.abilitiesArray)
				if(abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);
		
		abilityProps.clearRacialAbilities();
		
		for(Ability a : tempAblList)
			abilityProps.addRacialAbility(a);
		
		tempAblList.clear();

		for(Ability a : HakiAbilities.abilitiesArray)
			if(abilityProps.hasHakiAbility(a) && !verifyIfAbilityIsBanned(a))
				tempAblList.add(a);
		
		abilityProps.clearHakiAbilities();
		
		for(Ability a : tempAblList)
			abilityProps.addHakiAbility(a);
	}
	
	
	public static void validateStyleMoves(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);
		
		if(props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN))
		{
			if(!verifyIfAbilityIsBanned(SwordsmanAbilities.SHISHISHISONSON))
				abilityProps.addRacialAbility(SwordsmanAbilities.SHISHISHISONSON);
		
			if(MainConfig.enableQuestProgression)
			{
				if(questProps.hasQuestCompleted(ListQuests.swordsmanProgression04) && !verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO))
					abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
			}
			else
			{
				if(!verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO))
					abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
				if(!verifyIfAbilityIsBanned(SwordsmanAbilities.YAKKODORI))
					abilityProps.addRacialAbility(SwordsmanAbilities.YAKKODORI);
				if(!verifyIfAbilityIsBanned(SwordsmanAbilities.OTATSUMAKI))
					abilityProps.addRacialAbility(SwordsmanAbilities.OTATSUMAKI);
			}
		}
		else if(props.getFightStyle().equals(ID.FSTYLE_SNIPER))
		{
			if(!verifyIfAbilityIsBanned(SniperAbilities.KAENBOSHI))
				abilityProps.addRacialAbility(SniperAbilities.KAENBOSHI);
			
			if(MainConfig.enableQuestProgression)
			{

			}
			else
			{
				if(!verifyIfAbilityIsBanned(SniperAbilities.KEMURIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.KEMURIBOSHI);
				if(!verifyIfAbilityIsBanned(SniperAbilities.RENPATSUNAMARIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.RENPATSUNAMARIBOSHI);
				if(!verifyIfAbilityIsBanned(SniperAbilities.SAKURETSUSABOTENBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.SAKURETSUSABOTENBOSHI);	
			}
		}
	}
	
	public static boolean isSniperAbility(Ability abl)
	{
		for(Ability a : SniperAbilities.abilitiesArray)
		{
			if(abl.getAttribute().getAttributeName().equalsIgnoreCase(a.getAttribute().getAttributeName())) return true;
		}
		
		return false;
	}
	
	public static boolean canBreakBlock(World world, int posX, int posY, int posZ)
	{
		return canBreakBlock(world.getBlock(posX, posY, posZ));
	}
	
	public static boolean canBreakBlock(Block b)
	{
		for(Block blk : nonBreakableBlocks)
		{
			if(b == blk)
				return false;
		}
		return true;
	}
	
	public static boolean canReplaceBlock(Block b)
	{
		for(Block blk : replaceableBlocks)
		{
			if(b == blk)
				return true;
		}
		
		return false;
	}
	
	public static void placeIfCanReplaceBlock(World world, int posX, int posY, int posZ, Block toPlace)
	{
		Block b = world.getBlock((int)posX, (int)posY, (int)posZ);
		
		for(Block blk : replaceableBlocks)
		{
			if(b == blk)
			{
				world.setBlock(posX, posY, posZ, toPlace, 0, 2);
				break;
			}
		}
	}
	
	public static ItemStack getDevilFruitItem(String fullName)
	{
		String model = "";
		String fullModel = "";
		
		for(String[] s : zoanModels)
		{
			if(fullName.equals(s[0]))
			{
				model = s[1];
				fullModel = "model" + model;
				break;
			}
		}

		if(fullName.equals("yamidummy"))
			fullName = "yamiyami";
		
		return new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, fullName.replace(model, "") + "nomi" + fullModel));
	}
	
	public static boolean isEntityInRoom(EntityLivingBase entity)
	{	
		for(int i = -20; i < 20; i++)
		for(int j = -20; j < 20; j++)
		for(int k = -20; k < 20; k++)
		{
			if(entity.worldObj.getBlock((int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k) == ListMisc.OpeMid)
				return true;
		}
		
		return false;
	}
	
	public static int getRegenFromPoision(EntityLivingBase entity)
	{
		return entity.getActivePotionEffect(Potion.poison).getAmplifier() / 5;
	}
	
	public static boolean hasKairosekiItem(EntityPlayer player)
	{
		for(Item itm : Values.KAIROSEKI_ITEMS)
		{
			if(player.inventory.hasItem(itm))
			{
				return true;
			}
		}
		
		return false;
	}
}

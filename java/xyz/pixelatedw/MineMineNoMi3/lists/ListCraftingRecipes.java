package xyz.pixelatedw.MineMineNoMi3.lists;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ListCraftingRecipes 
{
	
	public static void init()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.ColaBackpack), new Object[]
				{ "X.X", "XOX", "X.X", 'X', ListMisc.UltraCola, 'O', Items.iron_ingot });	
		
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.Key), new Object[]
				{ ".X.", ".X.", ".X.", 'X', Items.gold_ingot });	
		
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.Cola), new Object[]
				{ ".O.", ".O.", ".X.", 'O', Items.sugar, 'X', Items.glass_bottle });
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.UltraCola), new Object[]
				{ "OOO", "OOO", "OXO", 'O', Items.sugar, 'X', ListMisc.Cola });
		
		GameRegistry.addShapelessRecipe(new ItemStack(ListMisc.Kairoseki, 9), ListMisc.KairosekiBlock);
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.KairosekiBlock), new Object[]
				{ "XXX", "XXX", "XXX", 'X', ListMisc.Kairoseki });	    
		
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ "#$.", "$#.", "...", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ "$#.", "#$.", "...", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ ".$#", ".#$", "...", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ ".#$", ".$#", "...", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ "...", "$#.", "#$.", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ "...", "#$.", "$#.", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ "...", ".#$", ".$#", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });  
	    GameRegistry.addShapedRecipe(new ItemStack(ListMisc.DenseKairoseki), new Object[] 
	    		{ "...", ".$#", ".#$", '#', ListMisc.Kairoseki, '$', Items.iron_ingot });
	    
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.BlackMetal), new Object[]
				{ "...", "XXX", "XXX", 'X', ListMisc.DenseKairoseki });		
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.BlackMetal), new Object[]
				{ "XXX", "XXX", "...", 'X', ListMisc.DenseKairoseki });	
		
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.Bullets, 16), new Object[]
				{ "XX.", "XX.", "...", 'X', Items.iron_ingot });	
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.Bullets, 16), new Object[]
				{ "...", "XX.", "XX.", 'X', Items.iron_ingot });	
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.Bullets, 16), new Object[]
				{ ".XX", ".XX", "...", 'X', Items.iron_ingot });	
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.Bullets, 16), new Object[]
				{ "...", ".XX", ".XX", 'X', Items.iron_ingot });	
	    
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.KairosekiBullets, 8), new Object[]
				{ "XX.", "XX.", "...", 'X', ListMisc.DenseKairoseki });	
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.KairosekiBullets, 8), new Object[]
				{ "...", "XX.", "XX.", 'X', ListMisc.DenseKairoseki });	
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.KairosekiBullets, 8), new Object[]
				{ ".XX", ".XX", "...", 'X', ListMisc.DenseKairoseki });	
		GameRegistry.addShapedRecipe(new ItemStack(ListMisc.KairosekiBullets, 8), new Object[]
				{ "...", ".XX", ".XX", 'X', ListMisc.DenseKairoseki });	
	}

}

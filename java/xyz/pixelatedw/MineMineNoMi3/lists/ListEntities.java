package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraftforge.common.BiomeDictionary.Type;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.WyRegistry;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityArlong;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityChew;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityKuroobi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.bandits.EntityBandit;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityDonKrieg;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityGin;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityPearl;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarineCaptain;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMorgan;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDojoSensei;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirate;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirateWithGun;
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

public class ListEntities 
{
	
	public static void init()
	{
		int ids = 0;
		for(int i = 0; i < ListDevilFruits.ALL_ENTITIES.length; i++)
		{
			for(int j = 0; j < ((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).size(); j++)
			{
				EntityRegistry.registerModEntity(((Class)((Object[])((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).get(j))[0]), ((AbilityAttribute)((Object[])((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).get(j))[1]).getAttributeName(), ids++, MainMod.getMineMineNoMi(), 64, 10, true);
			}
		}
		
		Type[] generalBiomes = new Type[]
				{Type.BEACH, Type.JUNGLE, Type.SWAMP, Type.SAVANNA, Type.FOREST, Type.HILLS, Type.CONIFEROUS};
		
		//Bandits
		WyRegistry.registerMob("Bandit with Sword", EntityBandit.class, 0x5B2929, 0xFFFFFF);
		WyRegistry.registerSpawnBiomesFor(EntityBandit.class, 30, 2, 5, generalBiomes);
		
		//Marines
		WyRegistry.registerMob("Marine with Sword", EntityMarine.class, 0x02258e, 0xFFFFFF);
		WyRegistry.registerSpawnBiomesFor(EntityMarine.class, 20, 3, 6, generalBiomes);
		WyRegistry.registerMob("Marine with Gun", EntityMarineWithGun.class, 0x02258e, 0xFFFFFF);
		WyRegistry.registerSpawnBiomesFor(EntityMarineWithGun.class, 20, 3, 6, generalBiomes);
		WyRegistry.registerMob("Marine Captain", EntityMarineCaptain.class, 0x02258e, 0xFFFFFF);
		WyRegistry.registerMob("Captain Morgan", EntityMorgan.class);
		 
		//W.GOV
		WyRegistry.registerMob("Lucci", EntityLucci.class);
		WyRegistry.registerMob("LucciL", EntityLucciL.class);
		WyRegistry.registerMob("Kaku", EntityKaku.class);
		WyRegistry.registerMob("KakuL", EntityKakuL.class);
		WyRegistry.registerMob("Jabra", EntityJabra.class);
		WyRegistry.registerMob("JabraL", EntityJabraL.class);
		WyRegistry.registerMob("Fukuro", EntityFukuro.class);
		WyRegistry.registerMob("Kalifa", EntityKalifa.class);
		WyRegistry.registerMob("Kumadori", EntityKumadori.class);
		WyRegistry.registerMob("Blueno", EntityBlueno.class);
		WyRegistry.registerMob("Spandam", EntitySpandam.class);
		
		//Pirates
		WyRegistry.registerMob("Pirate with Sword", EntityPirate.class, 0x960606, 0xFFFFFF);
		WyRegistry.registerSpawnBiomesFor(EntityPirate.class, 20, 3, 6, generalBiomes);
		WyRegistry.registerMob("Pirate with Gun", EntityPirateWithGun.class, 0x960606, 0xFFFFFF);
		WyRegistry.registerSpawnBiomesFor(EntityPirateWithGun.class, 20, 3, 6, generalBiomes);
		WyRegistry.registerMob("Pirate Captain", EntityPirateCaptain.class, 0x960606, 0xFFFFFF);
		//Arlong Pirates
		WyRegistry.registerMob("Arlong", EntityArlong.class);
		WyRegistry.registerMob("Chew", EntityChew.class);
		WyRegistry.registerMob("Kuroobi", EntityKuroobi.class);
		//Krieg Pirates
		WyRegistry.registerMob("Don Krieg", EntityDonKrieg.class);
		WyRegistry.registerMob("Gin", EntityGin.class);
		WyRegistry.registerMob("Pearl", EntityPearl.class);
				
		//Others
		WyRegistry.registerMob("Doppelman", EntityDoppelman.class);
		WyRegistry.registerMob("Den Den Mushi", EntityDenDenMushi.class, 0xFF00FF, 0x00FF00);
		WyRegistry.registerMob("Dojo Sensei", EntityDojoSensei.class, 0xFF00FF, 0x00FF00);
		
		/*
		//Temp
		WyRegistry.registerMob("TEMP_Fist", TempEntityFist.class);
		WyRegistry.registerMob("TEMP_Bazooka", TempEntityBazooka.class);
		WyRegistry.registerMob("TEMP_BrickBat", TempEntityBrickBat.class);
		WyRegistry.registerMob("TEMP_Hydra", TempEntityHydra.class);
		WyRegistry.registerMob("TEMP_Meigo", TempEntityMeigo.class);
		WyRegistry.registerMob("TEMP_NoroBeam", TempEntityNoroBeam.class);	
		WyRegistry.registerMob("TEMP_Paw", TempEntityPaw.class);
		WyRegistry.registerMob("TEMP_Pheasant", TempEntityPheasant.class);
		WyRegistry.registerMob("TEMP_Shark", TempEntityShark.class);
		WyRegistry.registerMob("TEMP_Spear", TempEntitySpear.class);
		WyRegistry.registerMob("TEMP_Trident", TempEntityTrident.class);
		WyRegistry.registerMob("TEMP_YukiRabi", TempEntityYukiRabi.class);
		WyRegistry.registerMob("TEMP_PhoenixFull", TempEntityPhoenixFull.class);
		WyRegistry.registerMob("TEMP_PhoenixHybrid", TempEntityPhoenixHybrid.class);*/
	}
	
}

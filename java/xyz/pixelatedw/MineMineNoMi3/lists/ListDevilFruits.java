package xyz.pixelatedw.MineMineNoMi3.lists;

import xyz.pixelatedw.MineMineNoMi3.EnumFruitType;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.BakuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.BaneAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.BomuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.DokuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.DoruAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.GasuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.GoeAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.GomuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.GoroAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.GuraAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HieAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HoroAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.ItoAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.JuryoAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.KageAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.KiloAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.MaguAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.MeraAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.MokuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.NikyuAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.NoroAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.OpeAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.PikaAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SniperAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SukeAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SunaAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.ToriPhoenixAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.UshiBisonAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.YamiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.YukiAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.WyRegistry;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BakuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BaneProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BariProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BomuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.CyborgProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.DokuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.DoruProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ExtraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.FishKarateProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GasuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GomuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GuraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HieProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ItoProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.JuryoProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.KageProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MaguProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MokuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.NikyuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.NoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.OpeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.PikaProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.RokushikiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SniperProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SukeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SunaProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SwordsmanProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ToriPhoenixProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.YamiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.YukiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;

public class ListDevilFruits 
{
	
	public static AkumaNoMi  MeraMeraNoMi, HieHieNoMi, BaneBaneNoMi, PikaPikaNoMi, GomuGomuNoMi, SukeSukeNoMi,
			OpeOpeNoMi, NoroNoroNoMi, GoroGoroNoMi, MokuMokuNoMi, NikyuNikyuNoMi, BomuBomuNoMi, GuraGuraNoMi,
			KageKageNoMi, SunaSunaNoMi, MaguMaguNoMi, DoruDoruNoMi, DokuDokuNoMi, BariBariNoMi, GasuGasuNoMi,
			YukiYukiNoMi, JuryoJuryoNoMi, YamiYamiNoMi, ItoItoNoMi, HoroHoroNoMi, SupaSupaNoMi, OriOriNoMi, 
			MeroMeroNoMi, GoeGoeNoMi, KiloKiloNoMi, HanaHanaNoMi, HoruHoruNoMi, BetaBetaNoMi, IshiIshiNoMi, 
			PamuPamuNoMi, UshiUshiNoMiBison, ToriToriNoMiPhoenix, BakuBakuNoMi;

	private static final Ability[][] EVERY_FRUIT = 
		{MeraAbilities.abilitiesArray, HieAbilities.abilitiesArray, BaneAbilities.abilitiesArray, PikaAbilities.abilitiesArray, SukeAbilities.abilitiesArray, 
				OpeAbilities.abilitiesArray, GoroAbilities.abilitiesArray, MokuAbilities.abilitiesArray, NikyuAbilities.abilitiesArray, BomuAbilities.abilitiesArray, GuraAbilities.abilitiesArray,
				KageAbilities.abilitiesArray, SunaAbilities.abilitiesArray, MaguAbilities.abilitiesArray, DoruAbilities.abilitiesArray, DokuAbilities.abilitiesArray, GasuAbilities.abilitiesArray,
				YukiAbilities.abilitiesArray, ItoAbilities.abilitiesArray, BariAbilities.abilitiesArray, HoroAbilities.abilitiesArray, GoeAbilities.abilitiesArray,
				NoroAbilities.abilitiesArray, YamiAbilities.abilitiesArray, GomuAbilities.abilitiesArray, UshiBisonAbilities.abilitiesArray, ToriPhoenixAbilities.abilitiesArray,
				RokushikiAbilities.abilitiesArray, FishKarateAbilities.abilitiesArray, CyborgAbilities.abilitiesArray, HakiAbilities.abilitiesArray, SwordsmanAbilities.abilitiesArray, 
<<<<<<< HEAD
				SniperAbilities.abilitiesArray, JuryoAbilities.abilitiesArray, KiloAbilities.abilitiesArray};
=======
				SniperAbilities.abilitiesArray, JuryoAbilities.abilitiesArray, BakuAbilities.abilitiesArray};
>>>>>>> New textures, almost completed Juryo and Baku but still particleless, also some api methods slightly changed for better use
	
	public static final Object[] ALL_ENTITIES = new Object[] 
		{RokushikiProjectiles.abilitiesClassesArray, MeraProjectiles.abilitiesClassesArray, HieProjectiles.abilitiesClassesArray, BaneProjectiles.abilitiesClassesArray, PikaProjectiles.abilitiesClassesArray, 
				NoroProjectiles.abilitiesClassesArray, SukeProjectiles.abilitiesClassesArray, OpeProjectiles.abilitiesClassesArray, GoroProjectiles.abilitiesClassesArray, MokuProjectiles.abilitiesClassesArray, 
				NikyuProjectiles.abilitiesClassesArray, BomuProjectiles.abilitiesClassesArray, GuraProjectiles.abilitiesClassesArray, KageProjectiles.abilitiesClassesArray, SunaProjectiles.abilitiesClassesArray,
				MaguProjectiles.abilitiesClassesArray, DoruProjectiles.abilitiesClassesArray, DokuProjectiles.abilitiesClassesArray, GasuProjectiles.abilitiesClassesArray, YukiProjectiles.abilitiesClassesArray,
				ItoProjectiles.abilitiesClassesArray, FishKarateProjectiles.abilitiesClassesArray, CyborgProjectiles.abilitiesClassesArray, ExtraProjectiles.abilitiesClassesArray, BariProjectiles.abilitiesClassesArray,
				HoroProjectiles.abilitiesClassesArray, GoeProjectiles.abilitiesClassesArray, NoroProjectiles.abilitiesClassesArray, YamiProjectiles.abilitiesClassesArray, GomuProjectiles.abilitiesClassesArray,
				SwordsmanProjectiles.abilitiesClassesArray, ToriPhoenixProjectiles.abilitiesClassesArray, SniperProjectiles.abilitiesClassesArray, JuryoProjectiles.abilitiesClassesArray, BakuProjectiles.abilitiesClassesArray};
	
	public static void init() 
	{
		int totalFruits = 0, totalAbilities = 0;
		
		BakuBakuNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, BakuAbilities.abilitiesArray);
		addITEM(BakuBakuNoMi, "Baku Baku no Mi");
		ToriToriNoMiPhoenix = new AkumaNoMi(EnumFruitType.MYTHICALZOAN, ToriPhoenixAbilities.abilitiesArray);
		addITEM(ToriToriNoMiPhoenix, "Tori Tori no Mi, Model Phoenix");
		UshiUshiNoMiBison = new AkumaNoMi(EnumFruitType.ZOAN, UshiBisonAbilities.abilitiesArray);
		addITEM(UshiUshiNoMiBison, "Ushi Ushi no Mi, Model Bison");
		JuryoJuryoNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, JuryoAbilities.abilitiesArray);
		addITEM(JuryoJuryoNoMi, "Juryo Juryo no Mi");
		YamiYamiNoMi = new AkumaNoMi(EnumFruitType.LOGIA, YamiAbilities.abilitiesArray);
		addITEM(YamiYamiNoMi, "Yami Yami no Mi");
		/*HoruHoruNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, HORU);
		addITEM(HoruHoruNoMi, "Horu Horu no Mi");
		MeroMeroNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, MERO);
		addITEM(MeroMeroNoMi, "Mero Mero no Mi");
		GoeGoeNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, GoeAbilities.abilitiesArray);
		addITEM(GoeGoeNoMi, "Goe Goe no Mi");
		/*SupaSupaNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, SUPA);
		addITEM(SupaSupaNoMi, "Supa Supa no Mi");*/
		HoroHoroNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, HoroAbilities.abilitiesArray);
		addITEM(HoroHoroNoMi, "Horo Horo no Mi");
		BariBariNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, BariAbilities.abilitiesArray);
		addITEM(BariBariNoMi, "Bari Bari no Mi");
		ItoItoNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, ItoAbilities.abilitiesArray);
		addITEM(ItoItoNoMi, "Ito Ito no Mi");
		YukiYukiNoMi = new AkumaNoMi(EnumFruitType.LOGIA, YukiAbilities.abilitiesArray);
		addITEM(YukiYukiNoMi, "Yuki Yuki no Mi");
		GasuGasuNoMi = new AkumaNoMi(EnumFruitType.LOGIA, GasuAbilities.abilitiesArray);
		addITEM(GasuGasuNoMi, "Gasu Gasu no Mi");
		DokuDokuNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, DokuAbilities.abilitiesArray);
		addITEM(DokuDokuNoMi, "Doku Doku no Mi");
		DoruDoruNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, DoruAbilities.abilitiesArray);
		addITEM(DoruDoruNoMi, "Doru Doru no Mi");
		MaguMaguNoMi = new AkumaNoMi(EnumFruitType.LOGIA, MaguAbilities.abilitiesArray);
		addITEM(MaguMaguNoMi, "Magu Magu no Mi");
		SunaSunaNoMi = new AkumaNoMi(EnumFruitType.LOGIA, SunaAbilities.abilitiesArray);
		addITEM(SunaSunaNoMi, "Suna Suna no Mi");
		KageKageNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, KageAbilities.abilitiesArray);
		addITEM(KageKageNoMi, "Kage Kage no Mi");
		GuraGuraNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, GuraAbilities.abilitiesArray);
		addITEM(GuraGuraNoMi, "Gura Gura no Mi");
		BomuBomuNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, BomuAbilities.abilitiesArray);
		addITEM(BomuBomuNoMi, "Bomu Bomu no Mi");
		NikyuNikyuNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, NikyuAbilities.abilitiesArray);
		addITEM(NikyuNikyuNoMi, "Nikyu Nikyu no Mi");
		MokuMokuNoMi = new AkumaNoMi(EnumFruitType.LOGIA, MokuAbilities.abilitiesArray);
		addITEM(MokuMokuNoMi, "Moku Moku no Mi");
		GoroGoroNoMi = new AkumaNoMi(EnumFruitType.LOGIA, GoroAbilities.abilitiesArray);
		addITEM(GoroGoroNoMi, "Goro Goro no Mi");
		OpeOpeNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, OpeAbilities.abilitiesArray);
		addITEM(OpeOpeNoMi, "Ope Ope no Mi");
		NoroNoroNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, NoroAbilities.abilitiesArray);
		addITEM(NoroNoroNoMi, "Noro Noro no Mi");
		SukeSukeNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, SukeAbilities.abilitiesArray);
		addITEM(SukeSukeNoMi, "Suke Suke no Mi");
		PikaPikaNoMi = new AkumaNoMi(EnumFruitType.LOGIA, PikaAbilities.abilitiesArray);
		addITEM(PikaPikaNoMi, "Pika Pika no Mi");
		GomuGomuNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, GomuAbilities.abilitiesArray);
		addITEM(GomuGomuNoMi, "Gomu Gomu no Mi");
		BaneBaneNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, BaneAbilities.abilitiesArray);
		addITEM(BaneBaneNoMi, "Bane Bane no Mi");
		HieHieNoMi = new AkumaNoMi(EnumFruitType.LOGIA, HieAbilities.abilitiesArray);
		addITEM(HieHieNoMi, "Hie Hie no Mi");
		MeraMeraNoMi = new AkumaNoMi(EnumFruitType.LOGIA, MeraAbilities.abilitiesArray);
		addITEM(MeraMeraNoMi, "Mera Mera no Mi");
		KiloKiloNoMi = new AkumaNoMi(EnumFruitType.PARAMECIA, KiloAbilities.abilitiesArray);
		addITEM(KiloKiloNoMi, "Kilo Kilo no Mi");

		for (int i = 0; i < EVERY_FRUIT.length; i++)
		{		
			totalFruits++;
			for (Ability a : EVERY_FRUIT[i])
				if (a != null)
				{
					totalAbilities++;
					AbilityManager.instance().registerAbility(a);
				}
		}
		
		WyDebug.info("A total of " + totalFruits + " Devil Fruits have been registered");
		WyDebug.info("A total of " + totalAbilities + " abilities have been registered");
	}

	public static void addITEM(AkumaNoMi item, String localizedName) 
	{
		if (((AkumaNoMi) item).type == EnumFruitType.LOGIA)
			Values.logias.add(item);
		Values.devilfruits.add(item);
		WyRegistry.registerItem(item, localizedName, ListCreativeTabs.tabDevilFruits);
	}

}

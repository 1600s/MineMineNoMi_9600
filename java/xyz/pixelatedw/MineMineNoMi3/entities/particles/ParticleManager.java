package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.baku.ParticleEffectBakuMunch;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.doku.ParticleEffectDokuGumo;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.doku.ParticleEffectVenomDemon;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.fishkarate.ParticleEffectSamehada;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.gomu.ParticleEffectGearSecond;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.goro.ParticleEffectElThor;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.goro.ParticleEffectKari;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.goro.ParticleEffectRaigo;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.gura.ParticleEffectGekishin;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.hie.ParticleEffectIceAge;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ito.ParticleEffectKumoNoSugaki;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.mera.ParticleEffectDaiEnkai;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.mera.ParticleEffectDaiEnkai2;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.moku.ParticleEffectWhiteLauncher;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.moku.ParticleEffectWhiteStrike;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.pika.ParticleEffectAmaterasu;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.pika.ParticleEffectFlash;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.pika.ParticleEffectYataNoKagami;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.rokushiki.ParticleEffectGeppo;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.sniper.ParticleEffectKemuriBoshi;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.suna.ParticleEffectDesertGirasole;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.suna.ParticleEffectDesertGirasole2;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.suna.ParticleEffectGroundDeath;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.suna.ParticleEffectSables;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.toriphoenix.ParticleEffectBlueFlames;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.toriphoenix.ParticleEffectTenseiNoSoen;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.toriphoenix.ParticleEffectTenseiNoSoen2;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ushibison.ParticleEffectKokuteiCross;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.yami.ParticleEffectBlackHole;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.yami.ParticleEffectBlackWorld;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.yami.ParticleEffectDarkMatter;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.yuki.ParticleEffectFubuki;

public class ParticleManager
{

	private static ParticleManager instance;
	
	public static ParticleManager getInstance()
	{
		if(instance == null)
			instance = new ParticleManager();
		
		return instance;
	}
	
	private HashMap<String, ParticleEffect> particleEffects = createMap();
	
	private HashMap<String, ParticleEffect> createMap()
	{
		HashMap<String, ParticleEffect> map = new HashMap<String, ParticleEffect>();
		
		// Baku
		map.put(ID.PARTICLEFX_BAKUMUNCH, new ParticleEffectBakuMunch());
		
		// Mera
		map.put(ID.PARTICLEFX_DAIENKAI1, new ParticleEffectDaiEnkai());
		map.put(ID.PARTICLEFX_DAIENKAI2, new ParticleEffectDaiEnkai2());
		
		// Doku
		map.put(ID.PARTICLEFX_DOKUGOMU, new ParticleEffectDokuGumo());
		map.put(ID.PARTICLEFX_VENOMDEMON, new ParticleEffectVenomDemon());
		
		// Yuki
		map.put(ID.PARTICLEFX_FUBUKI, new ParticleEffectFubuki());
		
		// Moku
		map.put(ID.PARTICLEFX_WHITELAUNCHER, new ParticleEffectWhiteLauncher());
		map.put(ID.PARTICLEFX_WHITESTRIKE, new ParticleEffectWhiteStrike());
		
		// Fishman
		map.put(ID.PARTICLEFX_SAMEHADA, new ParticleEffectSamehada());	
		
		// Suna
		map.put(ID.PARTICLEFX_SABLES, new ParticleEffectSables());
		map.put(ID.PARTICLEFX_GROUNDDEATH, new ParticleEffectGroundDeath());	
		map.put(ID.PARTICLEFX_DESERTGIRASOLE, new ParticleEffectDesertGirasole());
		map.put(ID.PARTICLEFX_DESERTGIRASOLE2, new ParticleEffectDesertGirasole2());

		// Ushi Bison
		map.put(ID.PARTICLEFX_KOKUTEICROSS, new ParticleEffectKokuteiCross());	
		
		// Gomu
		map.put(ID.PARTICLEFX_GEARSECOND, new ParticleEffectGearSecond());	
		
		// Tori Phoenix
		map.put(ID.PARTICLEFX_TENSEINOSOEN1, new ParticleEffectTenseiNoSoen());
		map.put(ID.PARTICLEFX_TENSEINOSOEN2, new ParticleEffectTenseiNoSoen2());
		map.put(ID.PARTICLEFX_BLUEFLAMES, new ParticleEffectBlueFlames());
		
		// Yami
		map.put(ID.PARTICLEFX_BLACKHOLE, new ParticleEffectBlackHole());
		map.put(ID.PARTICLEFX_BLACKWORLD, new ParticleEffectBlackWorld());
		map.put(ID.PARTICLEFX_DARKMATTER, new ParticleEffectDarkMatter());	
		
		// Pika 
		map.put(ID.PARTICLEFX_YATANOKAGAMI, new ParticleEffectYataNoKagami());
		map.put(ID.PARTICLEFX_AMATERASU, new ParticleEffectAmaterasu());
		map.put(ID.PARTICLEFX_FLASH, new ParticleEffectFlash());
		
		// Ito
		map.put(ID.PARTICLEFX_KUMONOSUGAKI, new ParticleEffectKumoNoSugaki());
		
		// Gura
		map.put(ID.PARTICLEFX_GEKISHIN, new ParticleEffectGekishin());
	
		// Goro
		map.put(ID.PARTICLEFX_ELTHOR, new ParticleEffectElThor());
		map.put(ID.PARTICLEFX_KARI, new ParticleEffectKari());
		map.put(ID.PARTICLEFX_RAIGO, new ParticleEffectRaigo());
		
		// Sniper
		map.put(ID.PARTICLEFX_KEMURIBOSHI, new ParticleEffectKemuriBoshi());
		
		// Rokushiki
		map.put(ID.PARTICLEFX_GEPPO, new ParticleEffectGeppo());		

		// Hie
		map.put(ID.PARTICLEFX_ICEAGE, new ParticleEffectIceAge());	
		
		
		return map;
	}
	
	public boolean spawnFX(EntityPlayer player, double posX, double posY, double posZ, String id)
	{
	
		if(this.particleEffects.containsKey(id))
		{
			this.particleEffects.get(id).spawn(player, posX, posY, posZ);
			return true;
		}
		
		return false;
	}
	
	public HashMap<String, ParticleEffect> getParticleEffectsMap()
	{
		return particleEffects;
	}
	
}

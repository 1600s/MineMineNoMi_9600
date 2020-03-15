package xyz.pixelatedw.MineMineNoMi3.lists;

import java.awt.Color;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelCube;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelSphere;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.EffectType;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelArrow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelBazooka;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelFist;

public class ListExtraAttributes
{

	public static AbilityAttribute LIBERATIONBLOCK = new AbilityAttribute("Liberation Block").setProjectileModel(new ModelCube()).setProjectileColor(Color.BLACK).setProjectileDamage(20).setProjectileSize(3, 3, 3);
	 
	public static AbilityAttribute METEOR = new AbilityAttribute("Meteor").setProjectileTicks(500).setProjectileModel(new ModelSphere()).setProjectileSize(20, 20, 20).setProjectileColor("56494B").setProjectileExplosion(13);
	
	public static AbilityAttribute ELTHORTHUNDER = new AbilityAttribute("El Thor Thunder").setProjectileTicks(500).setProjectileModel(new ModelCube()).setProjectileSize(60, 10, 10).setProjectileColor("77abff").setProjectileExplosion(2);
	
	public static AbilityAttribute TSUNOTOKAGEPILLAR = new AbilityAttribute("Tsuno-Tokage Pillar").setProjectileDamage(30).setProjectileModel(new ModelCube()).setProjectileSize(4, 4, 20).setProjectileColor(Color.BLACK).setProjectileTicks(5);

	public static AbilityAttribute GOMUGOMUNOPISTOL = new AbilityAttribute("Gomu Gomu no Pistol").setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunopistol").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileDamage(6);
	public static AbilityAttribute GOMUGOMUNOJETPISTOL = new AbilityAttribute("Gomu Gomu no Jet Pistol").setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunojetpistol").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileSpeed(4).setProjectileDamage(12);
	public static AbilityAttribute GOMUGOMUNOELEPHANTGUN = new AbilityAttribute("Gomu Gomu no Elephant Gun").setProjectileTicks(40).setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunoelephantgun").setProjectileSize(15, 15, 13).setModelOffsets(-1, 5, 0).setProjectileDamage(20).setProjectileMoveThroughBlocks(true).setProjectileNewExplosion(2);
	public static AbilityAttribute GOMUGOMUNOKONGGUN = new AbilityAttribute("Gomu Gomu no Kong Gun").setProjectileTicks(30).setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunokonggun").setProjectileSize(15, 15, 13).setModelOffsets(-1, 5, 0).setProjectileDamage(30).setProjectileMoveThroughBlocks(true).setProjectileNewExplosion(2);
	
	public static AbilityAttribute GOMUGOMUNOBAZOOKA = new AbilityAttribute("Gomu Gomu no Bazooka").setProjectileModel(new ModelBazooka()).setProjectileTexture("gomugomunobazooka").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileDamage(10);
	public static AbilityAttribute GOMUGOMUNOJETBAZOOKA = new AbilityAttribute("Gomu Gomu no Jet Bazooka").setProjectileModel(new ModelBazooka()).setProjectileTexture("gomugomunojetbazooka").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileSpeed(4).setProjectileDamage(16);
	public static AbilityAttribute GOMUGOMUNOGRIZZLYMAGNUM = new AbilityAttribute("Gomu Gomu no Grizzly Magnum").setProjectileTicks(40).setProjectileModel(new ModelBazooka()).setProjectileTexture("gomugomunogrizzlymagnum").setProjectileSize(15, 15, 13).setModelOffsets(-1, 5, 0).setProjectileDamage(30);
	public static AbilityAttribute GOMUGOMUNOLEOBAZOOKA = new AbilityAttribute("Gomu Gomu no Leo Bazooka").setProjectileTicks(30).setProjectileModel(new ModelBazooka()).setProjectileTexture("gomugomunoleobazooka").setProjectileSize(15, 15, 13).setModelOffsets(-1, 5, 0).setProjectileDamage(50);
	
	public static AbilityAttribute GOMUGOMUNOGATLING = new AbilityAttribute("Gomu Gomu no Gatling").setProjectileTicks(5).setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunopistol").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileDamage(6);
	public static AbilityAttribute GOMUGOMUNOJETGATLING = new AbilityAttribute("Gomu Gomu no Jet Gatling").setProjectileTicks(5).setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunojetpistol").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileSpeed(4).setProjectileDamage(12);
	public static AbilityAttribute GOMUGOMUNOELEPHANTGATLING = new AbilityAttribute("Gomu Gomu no Elephant Gatling").setProjectileTicks(5).setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunoelephantgun").setProjectileSize(15, 15, 13).setModelOffsets(-1, 5, 0).setProjectileDamage(20).setProjectileMoveThroughBlocks(true).setProjectileNewExplosion(2);
	public static AbilityAttribute GOMUGOMUNOKONGORGAN = new AbilityAttribute("Gomu Gomu no Kong Organ").setProjectileTicks(5).setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunokonggun").setProjectileSize(15, 15, 13).setModelOffsets(-1, 5, 0).setProjectileDamage(30).setProjectileMoveThroughBlocks(true).setProjectileNewExplosion(2);
	
	public static AbilityAttribute VOLTVARI5MILLION = new AbilityAttribute("5 Million Volt Vari").setProjectileTicks(10).setProjectileModel(new ModelSphere()).setProjectileModel(new ModelSphere()).setProjectileSize(1, 1, 1).setProjectileDamage(2).setProjectileColor("92c1e5");
	public static AbilityAttribute VOLTVARI20MILLION = new AbilityAttribute("20 Million Volt Vari").setProjectileTicks(20).setProjectileModel(new ModelSphere()).setProjectileModel(new ModelSphere()).setProjectileSize(3, 3, 3).setProjectileDamage(5).setProjectileColor("7CB9E8");
	public static AbilityAttribute VOLTVARI60MILLION = new AbilityAttribute("60 Million Volt Vari").setProjectileTicks(30).setProjectileModel(new ModelSphere()).setProjectileModel(new ModelSphere()).setProjectileSize(5, 5, 5).setProjectileDamage(7).setProjectileColor("6bb0e5");
	public static AbilityAttribute VOLTVARI200MILLION = new AbilityAttribute("200 Million Volt Vari").setProjectileTicks(50).setProjectileModel(new ModelSphere()).setProjectileModel(new ModelSphere()).setProjectileSize(7, 7, 7).setProjectileDamage(10).setProjectileExplosion(3, false).setProjectileColor("3170a0");
	
	public static AbilityAttribute GRAVITO = new AbilityAttribute("Gravito").setProjectileModel(new ModelCube()).setProjectileDamage(5).setProjectileSize(6, 0.4, 1.5).setProjectileTicks(100).setProjectileColor("E590DF").setProjectileAlpha(50).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 550, 1), new PotionEffect(Potion.weakness.id, 550, 1));                       
	
	public static AbilityAttribute NORMALBULLET = new AbilityAttribute("Bullet").setProjectileModel(new ModelSphere()).setProjectileDamage(4).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#878787");
	public static AbilityAttribute KAIROSEKIBULLET = new AbilityAttribute("Kairoseki Bullet").setProjectileModel(new ModelSphere()).setProjectileDamage(6).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#F3F3F3");
	public static AbilityAttribute POPGREEN = new AbilityAttribute("Pop Green").setProjectileModel(new ModelSphere()).setProjectileDamage(2).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#7ccc6a");
	public static AbilityAttribute KUJAARROW = new AbilityAttribute("Kuja Arrow").setProjectileModel(new ModelArrow()).setProjectileTexture("kujaarrow").setProjectileDamage(4).setProjectileSize(1.25, 1.25, 1.25).setProjectileTicks(100);

	public static AbilityAttribute DIALAXE = new AbilityAttribute("Axe Dial").setProjectileModel(new ModelCube()).setProjectileDamage(5).setProjectileSize(6, 0.4, 1.5).setProjectileTicks(100).setProjectileColor("69E3FF");
	public static AbilityAttribute DIALMILKY = new AbilityAttribute("Milky Dial").setProjectileModel(new ModelSphere()).setProjectileSize(.1, .1, .1).setProjectileTicks(40).setProjectileColor("69E3FF").setProjectileMoveThroughBlocks(true);

}

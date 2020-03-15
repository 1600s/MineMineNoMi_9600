 package xyz.pixelatedw.MineMineNoMi3.api.abilities;

import java.awt.Color;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.EffectType;

public class AbilityAttribute 
{	
	private String attributeName = "N/A";
	private boolean projectileExplosionHasFire = true, projectileExplosionHasSmoke = true, canBeCharged = false, isRepeater = false, itemExplosionHasFire = true, itemExplosionHasSmoke = true, isPassive = false, isPunch = false, entityMoveThroughBlocks = false;
	private int itemTicks = 0, entityTicks = 60, entitySpeed = 1, entityExplosion = 0, entityNewExplosion = 0, potionEffectAoeRadius = 0, itemMaxCharge = 0, itemExplosion = 0, itemRepeaterTime = 6, itemRepeaterFreq = 1;
	private float projectileAlpha = 255, entityDamage = 1, punchDamage = 1;
	private double entityXRotation = 0, entityYRotation = 0, entityZRotation = 0;
	private Color entityColor = Color.decode("#FFFFFF");
	private double[] entityScale = new double[] {1, 1, 1}, entityPos = new double[] {0, 0, 0}, entityMotion = new double[] {0, 0, 0}, entityCollisionSize = new double[] {1, 1}, modelOffset = new double[] {0, 0, 0};
	private ModelBase entityModel = null;
	private PotionEffect[] potionEffectsForProjectile = null, potionEffectsForUser = null, potionEffectsForAoE = null, potionEffectsForHit = null;
	private ResourceLocation entityTexture = null;
	
	public AbilityAttribute() {}	
	public AbilityAttribute(String name) {this.attributeName = name;}
	
	public AbilityAttribute(AbilityAttribute attr) 
	{
		this.attributeName = attr.attributeName;
		
		this.projectileExplosionHasFire = attr.projectileExplosionHasFire;
		this.projectileExplosionHasSmoke = attr.projectileExplosionHasSmoke;
		this.canBeCharged = attr.canBeCharged;
		this.isRepeater = attr.isRepeater;
		this.itemExplosionHasFire = attr.itemExplosionHasFire;
		this.itemExplosionHasSmoke = attr.itemExplosionHasSmoke;
		this.isPassive = attr.isPassive;
		this.isPunch = attr.isPunch;
		
		this.itemTicks = attr.itemTicks;
		this.entityTicks = attr.entityTicks;
		this.entitySpeed = attr.entitySpeed;
		this.entityExplosion = attr.entityExplosion;
		this.entityNewExplosion = attr.entityNewExplosion;
		this.potionEffectAoeRadius = attr.potionEffectAoeRadius;
		this.itemMaxCharge = attr.itemMaxCharge;
		this.itemExplosion = attr.itemExplosion;
		this.itemRepeaterTime = attr.itemRepeaterTime;
		this.itemRepeaterFreq = attr.itemRepeaterFreq;
		this.entityMoveThroughBlocks = attr.entityMoveThroughBlocks;
		
		this.projectileAlpha = attr.projectileAlpha;
		this.entityDamage = attr.entityDamage;
		
		this.entityXRotation = attr.entityXRotation;
		this.entityYRotation = attr.entityYRotation;
		this.entityZRotation = attr.entityZRotation;
		this.punchDamage = attr.punchDamage;
		
		this.entityColor = attr.entityColor;
		
		this.entityScale = attr.entityScale;
		this.entityPos = attr.entityPos;
		this.entityMotion = attr.entityMotion;
		this.entityCollisionSize = attr.entityCollisionSize;
		this.modelOffset = attr.modelOffset;
		
		this.entityModel = attr.entityModel;
		this.potionEffectsForProjectile = attr.potionEffectsForProjectile;
		this.potionEffectsForUser = attr.potionEffectsForUser;
		this.potionEffectsForAoE = attr.potionEffectsForAoE;
		this.potionEffectsForHit = attr.potionEffectsForHit;
		
		this.entityTexture = attr.entityTexture;		
	}
	
	
	public AbilityAttribute setAttributeName(String name) { this.attributeName = name; return this; }
		//Item
	public AbilityAttribute setAbilityCooldown(double seconds) { this.itemTicks = MathHelper.ceiling_double_int(seconds * 20); return this; }	
	public AbilityAttribute setAbilityCharges(int ticks) { this.canBeCharged = true; this.itemMaxCharge = ticks; return this; }
	public AbilityAttribute setAbilityExplosion(int i, boolean fire, boolean explosion) { this.itemExplosion = i; this.itemExplosionHasFire = fire; this.itemExplosionHasSmoke = explosion; return this; }
	public AbilityAttribute setAbilityExplosion(int i, boolean fire) { this.itemExplosion = i; this.itemExplosionHasFire = fire; return this; }
	public AbilityAttribute setAbilityExplosion(int i) { this.itemExplosion = i; return this; }
	public AbilityAttribute setAbilityPassive() { this.isPassive = true; return this;}
	public AbilityAttribute setAbilityPunch() { this.isPassive = true; this.isPunch = true; return this; }
	public AbilityAttribute setAbilityPunch(float damage) { this.isPunch = true; this.punchDamage = damage; return this; }
	public AbilityAttribute setAbilityRepeater() { this.isRepeater = true; this.itemRepeaterTime = 6; this.itemRepeaterFreq = 1; return this; }
	public AbilityAttribute setAbilityRepeater(int time) { this.isRepeater = true; this.itemRepeaterTime = time; this.itemRepeaterFreq = 1; return this; }
	public AbilityAttribute setAbilityRepeater(int time, int frequency) { this.isRepeater = true; this.itemRepeaterTime = time; this.itemRepeaterFreq = frequency; return this; }
		//Projectile
	public AbilityAttribute setProjectileTicks(int i) {this.entityTicks = i;return this;}
	public AbilityAttribute setProjectileDamage(float i) {this.entityDamage = i;return this;}
	public AbilityAttribute setProjectileModel(ModelBase i) {this.entityModel = i;return this;}
	public AbilityAttribute setProjectileColor(Color i) {this.entityColor = i;return this;}
	public AbilityAttribute setProjectileColor(int i) {this.entityColor = new Color(i);return this;}
	public AbilityAttribute setProjectileColor(String color) {if(color.contains("#")){this.entityColor = Color.decode(color);}else{this.entityColor = Color.decode("#" + color);}return this;}
	public AbilityAttribute setProjectileAlpha(float alpha) { this.projectileAlpha = alpha; return this; }
	public AbilityAttribute setProjectileSize(double x, double y, double z) { this.entityScale = new double[] {x, y, z}; return this; }
	public AbilityAttribute setProjectileSize(double i[]) { this.entityScale = i; return this; }
	public AbilityAttribute setProjectilePosition(double i[]) { this.entityPos = i; return this; }
	public AbilityAttribute setProjectileExplosion(int i, boolean fire, boolean explosion) {this.entityExplosion = i;this.projectileExplosionHasFire = fire;this.itemExplosionHasSmoke = explosion;return this;}
	public AbilityAttribute setProjectileExplosion(int i, boolean fire) {this.entityExplosion = i;this.projectileExplosionHasFire = fire;return this;}
	public AbilityAttribute setProjectileExplosion(int i) {this.entityExplosion = i;return this;}
	public AbilityAttribute setProjectileNewExplosion(int i) {this.entityNewExplosion = i; return this;}
	public AbilityAttribute setProjectileSpeed(int i) {this.entitySpeed = i;return this;}
	public AbilityAttribute setProjectileTexture(String textureName) {this.entityTexture = new ResourceLocation(ID.PROJECT_ID + ":textures/models/projectiles/" + textureName +".png"); return this;}
	public AbilityAttribute setProjectileXRotation(double angle) { entityXRotation = angle; return this;}
	public AbilityAttribute setProjectileYRotation(double angle) { entityYRotation = angle; return this;}
	public AbilityAttribute setProjectileZRotation(double angle) { entityZRotation = angle; return this;}
	public AbilityAttribute setProjectileMoveThroughBlocks(boolean flag) { entityMoveThroughBlocks = flag; return this; }
	public AbilityAttribute setProjectileCollisionSizes(double i, double j) { this.entityCollisionSize = new double[] {i, j}; return this; }
	public AbilityAttribute setModelOffsets(double i, double j, double k) { this.modelOffset = new double[] {i, j, k}; return this; }
		//Potion Effects
	public AbilityAttribute addEffects(EffectType type, PotionEffect... e) 
	{
		if(type == EffectType.PROJECTILE) this.potionEffectsForProjectile = e;
		if(type == EffectType.USER) this.potionEffectsForUser = e;
		if(type == EffectType.AOE) this.potionEffectsForAoE = e;
		if(type == EffectType.HIT) this.potionEffectsForHit = e;
		return this;
	}
	public AbilityAttribute setEffectRadius(int i) { this.potionEffectAoeRadius = i;return this;}


		//Item
	public int getAbilityCooldown() { return itemTicks; }
	public boolean isRepeater() { return this.isRepeater; }
	public boolean canAbilityBeCharged() { return this.canBeCharged;}
	public int getAbilityExplosionPower() { return this.itemExplosion; }
	public int getAbilityCharges() {return this.itemMaxCharge;}	
	public boolean canAbilityExplosionSetFire() { return this.itemExplosionHasFire; }
	public boolean canAbilityExplosionDestroyBlocks() { return this.itemExplosionHasSmoke; }
	public boolean isPassive() { return this.isPassive; }
	public int getAbilityRepeaterTime() { return this.itemRepeaterTime; }
	public int getAbilityRepeaterFrequency() { return this.itemRepeaterFreq; }
	public boolean isPunch() { return this.isPunch; }
	public float getPunchDamage() { return this.punchDamage; }
		//Projectile
	public boolean hasProjectile() { return this.entityTicks > 0 && this.entityModel != null; }
	public int getProjectileTicks() { return entityTicks; }
	public float getProjectileDamage() { return entityDamage; }
	public Color getProjectileColor() { return entityColor; }
	public ModelBase getProjectileModel() { return entityModel; }
	public double[] getProjectileSize() { return entityScale; }
	public double[] getProjectilePosition() { return entityPos; }
	public int getProjectileSpeed() { return entitySpeed; }
	public int getProjectileExplosionPower() { return entityExplosion; }		
	public int getProjectileNewExplosionPower() { return entityNewExplosion; }
	public boolean canExplosionSetFire() { return projectileExplosionHasFire; }
	public boolean canExplosionDestroyBlocks() { return itemExplosionHasSmoke; }	
	public float getProjectileAlpha() { return this.projectileAlpha; }
	public ResourceLocation getProjectileTexture() { return this.entityTexture; }
	public double getProjectileXRotation() { return this.entityXRotation; }
	public double getProjectileYRotation() { return this.entityYRotation; }
	public double getProjectileZRotation() { return this.entityZRotation; }
	public boolean canProjectileMoveThroughBlocks() { return this.entityMoveThroughBlocks; }
	public double[] getProjectileCollisionSizes() { return this.entityCollisionSize; }
	public double[] getModelOffsets() { return this.modelOffset; }
		//Potion Effects
	public PotionEffect[] getPotionEffectsForHit() {return this.potionEffectsForHit;}
	public PotionEffect[] getPotionEffectsForProjectile() {return this.potionEffectsForProjectile;}
	public PotionEffect[] getPotionEffectsForUser() {return this.potionEffectsForUser;}
	public PotionEffect[] getPotionEffectsForAoE() {return this.potionEffectsForAoE;}
	public int getEffectRadius() {return this.potionEffectAoeRadius;}
		//Misc
	public String getAttributeName() {return this.attributeName;}
	
	
}

package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.BusoshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.KenbunshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketAbilitySync;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;
import xyz.pixelatedw.MineMineNoMi3.items.ItemCoreArmor;
import xyz.pixelatedw.MineMineNoMi3.lists.ListEffects;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;
import xyz.pixelatedw.MineMineNoMi3.quests.ITimedQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.QuestLogicHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class EventsPersistence
{

	/*
	 * onEntityUpdate 
	 * > Job boosts 
	 * > Fall damage nullification for Gomu & Bane 
	 * > Poison placement for Doku 
	 * > Doku nullification of poison damage 
	 * > Extra HP 
	 * > Fishman swimming boost 
	 * > Nullification of swimming for DF users 
	 * > Kilo gliding & falling
	 * > Buso Haki Timer
	 * 
	 * onEntityDeath 
	 * > Removing abilities when the user dies 
	 * > Doriki, Bounty & Belly rewards for killing players/mobs
	 * 
	 * onEntityAttackEvent 
	 * > Lava/Fire damage nullification for Mera & Magu 
	 * > Logia protection
	 * 
	 * onEntityJoinWorld 
	 * > Syncing between dimensions 
	 * > Gives a creation book for users without job/race/faction
	 * 
	 * onDorikiGained 
	 * > Rewards the user with rokushiki/fishman karate based on doriki
	 */	

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			ItemStack heldItem = player.getHeldItem();				

			QuestProperties questProps = QuestProperties.get(player);
			
			if(QuestLogicHelper.checkForITimedQuests(questProps))
			{
				for(int i = 0; i < questProps.questsInProgress(); i++)
				{
					if(questProps.getQuestIndexFromTracker(i) != null && questProps.getQuestIndexFromTracker(i) instanceof ITimedQuest)
					{
						((ITimedQuest)questProps.getQuestIndexFromTracker(i)).onTimePassEvent(player);					
					}
				}
			}
			
			if (heldItem != null)
			{
				
				/*if(props.getUsedFruit().equals("kilokilo") && heldItem.getItem() == ListAbilities.KILOPRESS)
				{
					if (props.getKilo())
					{
						player.motionY -= 3;
						player.fallDistance = 0;
					}
					if ( !props.getKilo() && !heldItem.getTagCompound().getBoolean("use") )
						player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 20, true));
				}*/
			}
			
			if(!props.getUsedFruit().equals("N/A") && !player.worldObj.isRemote)
			{
				if( WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) || DevilFruitsHelper.hasKairosekiItem(player) || player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock))
						|| player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)) || (player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water 
						|| player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water) && !player.isRiding()  )) )
				{
					if(DevilFruitsHelper.hasKairosekiItem(player))
						player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 48, 0));
					else
						player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 0));
					for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{
						if(abilityProps.getAbilityFromSlot(i) != null && !abilityProps.getAbilityFromSlot(i).isDisabled())
						{
							abilityProps.getAbilityFromSlot(i).setCooldownActive(true);
							abilityProps.getAbilityFromSlot(i).disable(player, true);
						}
					}
				}
				else
				{
					for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{
						if(abilityProps.getAbilityFromSlot(i) != null && abilityProps.getAbilityFromSlot(i).isDisabled())
						{
							abilityProps.getAbilityFromSlot(i).setCooldownActive(false);
							abilityProps.getAbilityFromSlot(i).disable(player, false);
						}
					}
				}
			}
			
			if(!player.capabilities.isCreativeMode)
			{
				if(MainConfig.enableSpecialFlying)
				{
					if(player.isInWater())
						player.capabilities.isFlying = false;
					
					player.capabilities.allowFlying = Arrays.stream(DevilFruitsHelper.flyingFruits).anyMatch(p ->
					{
						if(props.getUsedFruit().equalsIgnoreCase("toritoriphoenix") && !props.getZoanPoint().toLowerCase().equals("n/a"))
							return true;
						
						return props.getUsedFruit().equalsIgnoreCase(p);
					});
					
					if(!player.capabilities.allowFlying)
						player.capabilities.isFlying = false;
					
					if(player.capabilities.isFlying && !player.worldObj.isRemote)
					{
						ResourceLocation particleToUse = null;
						if(props.getUsedFruit().equalsIgnoreCase("mokumoku") )
							particleToUse = ID.PARTICLE_ICON_MOKU;
						else if(props.getUsedFruit().equalsIgnoreCase("gasugasu") )
							particleToUse = ID.PARTICLE_ICON_GASU;
						else if(props.getUsedFruit().equalsIgnoreCase("sunasuna") )
							particleToUse = ID.PARTICLE_ICON_SUNA2;

						
<<<<<<< HEAD:src/main/java/xyz/pixelatedw/MineMineNoMi3/events/EventsPersistence.java
						if(particleToUse != null)
						{
							for (int i = 0; i < 10; i++)
							{							
								double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
								double offsetY = (new Random().nextInt(13) + 1.0D - 10.0D) / 10.0D;
								double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
									
								MainMod.proxy.spawnCustomParticles(player, 
										new EntityParticleFX(player.worldObj, particleToUse, 
												player.posX + offsetX, 
												player.posY + 0.5 + offsetY, 
												player.posZ + offsetZ, 
												0, 0, 0)
										.setParticleScale(1.3F).setParticleGravity(0).setParticleAge(5));
							}
=======
						for (int i = 0; i < 10; i++)
						{							
							double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
							double offsetY = (new Random().nextInt(13) + 1.0D - 10.0D) / 10.0D;
							double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
								
							MainMod.proxy.spawnCustomParticles(player,
									new EntityParticleFX(player.worldObj, particleToUse,
											player.posX + offsetX, 
											player.posY + 0.5 + offsetY, 
											player.posZ + offsetZ, 
											0, 0, 0)
									.setParticleScale(1.3F).setParticleGravity(0).setParticleAge(5));
>>>>>>> Fixed Folders:java/xyz/pixelatedw/MineMineNoMi3/events/EventsPersistence.java
						}
					}
				}
			}
			else
				player.capabilities.allowFlying = true;
			
			if(props.getUsedFruit().equals("ushiushibison"))
			{
				if(props.getZoanPoint().equals(ID.ZOANMORPH_SPEED))
				{
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2 * 20, 1, true));
					
					double mX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
					double mZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
						
					double f2 = MathHelper.sqrt_double(mX * mX + player.motionY * player.motionY + mZ * mZ);
					mX /= (double)f2;
					mZ /= (double)f2;
					mX += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
					mZ += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
					mX *= 2;
					mZ *= 2;
					
					for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 0.5))
					{
						e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 2);
						e.motionX = mX;
						e.motionZ = mZ;
					}
				}
				else
				{
					player.removePotionEffect(Potion.moveSpeed.id);
				}
			}
						
			if(props.getUsedFruit().equals("hiehie"))
			{
				if( !WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) && !DevilFruitsHelper.hasKairosekiItem(player) 
						&& !player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) && !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)) 
						&& !player.isInsideOfMaterial(Material.water) )
				{
					final EntityLivingBase finalPlayer = player;
					for (int x1 = -1; x1 < 2; x1++) 
					for (int y1 = -1; y1 < 0; y1++) 
					for (int z1 = -1; z1 < 2; z1++) 
					{
						Sphere.generate((int) player.posX - 1 + x1, (int) player.posY + y1, (int) player.posZ + z1, 1, new ISphere()
						{						
							public void call(int x, int y, int z)
							{
								if(finalPlayer.worldObj.getBlock(x, y, z) == Blocks.water)
									finalPlayer.worldObj.setBlock(x, y ,z, Blocks.ice);
							}
						});
					}
				}
			}
			
			if( !WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) && !DevilFruitsHelper.hasKairosekiItem(player) 
					&& !player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) && !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)) 
					&& !player.isInsideOfMaterial(Material.water) )
			{
				for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
				{					
					if(abilityProps.getAbilityFromSlot(i) != null && abilityProps.getAbilityFromSlot(i).isRepeating())
					{ 					
						abilityProps.getAbilityFromSlot(i).duringRepeater(player);
					}				
				}
			}
			
			if (props.getUsedFruit().equals("gomugomu") || props.getUsedFruit().equals("banebane") || props.isLogia())
				player.fallDistance = 0;

			if (props.getUsedFruit().equals("dokudoku"))
			{
				if (player.isPotionActive(Potion.poison.id))
					player.removePotionEffect(Potion.poison.id);
			}
			
			if ( (player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water || player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water) && !player.isRiding())))
			{
				if (!props.getUsedFruit().equals("N/A"))
				{
					if(!player.capabilities.isCreativeMode)
						player.motionY -= 5;				
				}				

				if (props.getRace().equals(ID.RACE_FISHMAN) && props.getUsedFruit().equals("N/A"))
				{
					player.setAir(300);
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 1));

					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.2D;	
						player.motionZ /= 1.2D;
					}
					else
					{
						player.motionX *= 1.2D;
						player.motionZ *= 1.2D;
					}
				}
			}
			
			if(player.isInsideOfMaterial(Material.lava) && !player.capabilities.isCreativeMode)
			{
				if (props.getUsedFruit().equals("magumagu"))
				{
					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.9D;
						player.motionZ /= 1.9D;
					}
					else
					{
						player.motionX *= 1.9D;
						player.motionZ *= 1.9D;
					}
				}
			}	
			
			boolean hasColaBackpack = false;
			
			for(ItemStack armorStack : player.inventory.armorInventory)
			{
				if(armorStack != null && armorStack.getItem() instanceof ItemCoreArmor && ((ItemCoreArmor)armorStack.getItem()).getName().equals("colabackpack") )
				{
					hasColaBackpack = true;
				}
			}
			
			if(props.getRace().equals(ID.RACE_CYBORG))
			{
				if(hasColaBackpack && !props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() + 200);
					props.setColaBackpack(true);
					
			    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
			    		WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", 1);
			    	
					if(!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}
				else if(!hasColaBackpack && props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() - 200);
					
					if(props.getCola() > props.getMaxCola())
						props.setCola(props.getMaxCola());
					
					props.setColaBackpack(false);
					
			    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
			    		WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", -1);
			    	
					if(!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}
				
			}
			
			if(props.getTempPreviousAbility().equals("geppo") || props.getTempPreviousAbility().equals("soranomichi"))
			{
				if(!player.onGround && player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) == Blocks.air)
					player.fallDistance = 0;
				else
				{
					props.setTempPreviousAbility("N/A");
				}
			}
			
			if(props.hasHakiActive())
				props.addHakiTimer();
			else
			{
				if(props.getHakiTimer() > 0)
					props.decHakiTimer();
			}
			
			if(props.getHakiTimer() > 2400)
			{
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 0));
				if(props.getHakiTimer() > 3600 + (props.getDoriki() / 15))
				{
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 5));
					//player.attackEntityFrom(DamageSource.generic, Integer.MAX_VALUE);
				}
			}
		}
		else if(event.entityLiving instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) event.entityLiving;
			ExtendedEntityStats propz = ExtendedEntityStats.get(entity);

			if(!entity.worldObj.isRemote)
			{
				if(propz.isCandleLocked() && !entity.isPotionActive(Potion.moveSlowdown.id))
				{				
					propz.setIsCandleLocked(false);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(event.entityLiving.getEntityId(), propz));
				}
			}
		}
	}	

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);


			props.setYamiPower(false);

			for(int i = 0; i < 8; i++)
			{
				if(abilityProps.getAbilityFromSlot(i) != null)
					abilityProps.getAbilityFromSlot(i).reset();
			}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}

		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			EntityLivingBase target = event.entityLiving;

			IAttributeInstance attrAtk = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage);
			IAttributeInstance attrHP = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);

			int rng = player.worldObj.rand.nextInt(3) + 1;
			int plusBounty = 0, plusBelly = 0;		
			double plusDoriki = 0;
			
			boolean targetPlayer = false;
			
			if (target instanceof EntityPlayer)
			{
				ExtendedEntityStats targetprops = ExtendedEntityStats.get(target);

				plusDoriki = (targetprops.getDoriki() / 4) + rng;
				plusBounty = (targetprops.getBounty() / 2) + rng;
				plusBelly = targetprops.getBelly();
				
				targetPlayer = true;
			}
			else
			{
				if (props.getFaction().equals(ID.FACTION_MARINE) && target instanceof MarineData)
					return;
				
				if(target instanceof EntityNewMob)
				{				
					if((props.getDoriki() / 100) > ((EntityNewMob) target).getDorikiPower())
					{
						int x = (props.getDoriki() / 100) - ((EntityNewMob) target).getDorikiPower();
						if(x <= 0)
							x = 1;

						plusDoriki = 1 / x;
						if(plusDoriki < 1)
							plusDoriki = 1;
					}
					else
						plusDoriki = ((EntityNewMob) target).getDorikiPower();
							
					plusBounty = (((EntityNewMob) target).getDorikiPower() * 2) + rng;	
					plusBelly = ((EntityNewMob) target).getBellyInPockets() + rng;	
					
			    	if(!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			    		WyTelemetry.addStat("defeated_" + WyHelper.getFancyName(target.getClass().getSimpleName()).replace("entity", ""), 1);
				}
				else
				{
					if (attrAtk != null && attrHP != null)
					{	
						double i = attrAtk.getAttributeValue();
						double j = attrHP.getAttributeValue();		

						plusDoriki = (int) Math.round(((i + j) / 10) / Math.PI) + rng;
						plusBounty = (int) Math.round((i + j) / 10) + rng;
						plusBelly = 1;
					
					}
					else
					{
						plusDoriki = 0;
						plusBounty = 0;
						plusBelly = 1;
					}
				}
					
				if (plusDoriki > 0)
				{
					if (props.getDoriki() + plusDoriki < Values.MAX_DORIKI )
					{
						props.alterDoriki((int) Math.round(plusDoriki));
						DorikiEvent e = new DorikiEvent(player);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}
					
				if (props.getFaction().equals(ID.FACTION_PIRATE) || props.getFaction().equals(ID.FACTION_REVOLUTIONARY))
					if (plusBounty > 0)
						if (props.getBounty() + plusBounty < Values.MAX_GENERAL)
							props.alterBounty(plusBounty);
				
				if(props.getBelly() + plusBelly < Values.MAX_GENERAL)
					props.alterBelly(plusBelly);
						
			}
			
	    	if(!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
	    	{
	    		if(!targetPlayer && MainConfig.enableMobRewards)
	    		{
		    		WyTelemetry.addStat("dorikiEarnedFromEntities", (int) Math.round(plusDoriki));
		    		WyTelemetry.addStat("bellyEarnedFromEntities", plusBelly);
		    		WyTelemetry.addStat("bountyEarnedFromEntities", plusBounty);
	    		}
	    		else
	    		{
		    		WyTelemetry.addStat("dorikiEarnedFromPlayers", (int) Math.round((ExtendedEntityStats.get(target).getDoriki() - ExtendedEntityStats.get(target).getDorikiFromCommand()) / 4));
		    		WyTelemetry.addStat("bellyEarnedFromPlayers", plusBelly - ExtendedEntityStats.get(target).getBellyFromCommand());
		    		WyTelemetry.addStat("bountyEarnedFromPlayers", (int) Math.round((ExtendedEntityStats.get(target).getBounty() - ExtendedEntityStats.get(target).getBountyFromCommand()) / 2));
	    		}
	    	}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
	}

	@SubscribeEvent
	public void onEntityAttackEvent(LivingAttackEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		DamageSource damageSource  = event.source;
		Entity sourceOfDamage = event.source.getSourceOfDamage();	
		ItemStack heldItem = null;
		
		boolean entityIsLogia = false;
		String entityUsedFruit = "n/a";
		
		if(entity instanceof EntityPlayer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(entity);
			entityIsLogia = props.isLogia();
			entityUsedFruit = props.getUsedFruit();
		}
		else if(entity instanceof EntityNewMob)
		{
			entityIsLogia = ((EntityNewMob) entity).isLogia();
			entityUsedFruit = ((EntityNewMob) entity).getDevilFruitUsed();
		}
		
		boolean attackerHasKairosekiWeapon = false;
		boolean attackerHasHaki = false;
		String attackerUsedFruit = "n/a";
		
		if(sourceOfDamage instanceof EntityPlayer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get((EntityPlayer) sourceOfDamage);
			attackerHasHaki = props.hasBusoHakiActive();
			attackerUsedFruit = props.getUsedFruit();
			heldItem = ((EntityPlayer) sourceOfDamage).getHeldItem();
			//TODO Add natural kairoseki weapons like the Jitte
			if(heldItem != null)
				attackerHasKairosekiWeapon = heldItem.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ListEffects.kairoseki.effectId, heldItem) > 0;		
		}
		else if(sourceOfDamage instanceof EntityNewMob)
		{
			attackerHasHaki = ((EntityNewMob) sourceOfDamage).hasBusoHaki();
			heldItem = ((EntityNewMob) sourceOfDamage).getHeldItem();
			attackerUsedFruit = ((EntityNewMob) sourceOfDamage).getDevilFruitUsed();
			//TODO Check if mobs have kairoseki weapons
			if(heldItem != null)
				attackerHasKairosekiWeapon = false;
		}

		if(sourceOfDamage instanceof EntityLivingBase)
		{
			if(entityIsLogia && !attackerHasHaki)
			{
				if(entityUsedFruit.equalsIgnoreCase("gorogoro") && attackerUsedFruit.equalsIgnoreCase("gomugomu"))
					return;
				
				event.setCanceled(true);
				WyNetworkHelper.sendToAllAround(new PacketParticles("logiaEffect_" + entityUsedFruit, entity), entity.dimension, entity.posX, entity.posY, entity.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			}
			else
			{
				if(sourceOfDamage instanceof EntityPlayer)
				{
					AbilityProperties abilityProps = AbilityProperties.get((EntityPlayer) sourceOfDamage);

					if(!sourceOfDamage.worldObj.isRemote && heldItem == null)
					{		
						for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
						{	
							if(abilityProps.getAbilityFromSlot(i) != null && !abilityProps.getAbilityFromSlot(i).isOnCooldown() 
									&& abilityProps.getAbilityFromSlot(i).getAttribute().isPassive() && abilityProps.getAbilityFromSlot(i).isPassiveActive())
							{							
								if(abilityProps.getAbilityFromSlot(i).getAttribute().isPunch())
								{							
									abilityProps.getAbilityFromSlot(i).hitEntity((EntityPlayer) sourceOfDamage, entity);
								}
							}
						}
					}
				}
			}
		}

		if(sourceOfDamage instanceof EntityArrow && entityIsLogia && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
			event.setCanceled(true);
		
		if(sourceOfDamage instanceof AbilityProjectile && ((AbilityProjectile)sourceOfDamage).getAttribute().getAttributeName().equals("Bullet") && entityIsLogia && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
			event.setCanceled(true);
		
		if(event.source.isExplosion() && entityIsLogia && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
			event.setCanceled(true);
		
		if(entityUsedFruit.equalsIgnoreCase("meramera") && damageSource.equals(DamageSource.inFire) || damageSource.equals(DamageSource.onFire))
		{
			entity.extinguish();
			event.setCanceled(true);
		}
		
		if(entityUsedFruit.equalsIgnoreCase("magumagu") && damageSource.equals(DamageSource.inFire) || damageSource.equals(DamageSource.onFire) || event.source.equals(DamageSource.lava))
		{
			entity.extinguish();
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onEntityHurtEvent(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		Entity sourceOfDamage = event.source.getSourceOfDamage();
		
		if (sourceOfDamage instanceof EntityPlayer)
		{	
			ExtendedEntityStats props = ExtendedEntityStats.get((EntityLivingBase) sourceOfDamage);
			if(props.getUsedFruit() != null && !props.getUsedFruit().equalsIgnoreCase("n/a"))
			{
				if(props.getUsedFruit().equalsIgnoreCase("ushiushibison") && props.getZoanPoint().equalsIgnoreCase(ID.ZOANMORPH_POWER))
					event.ammount += 3;
				
				if(props.getUsedFruit().equalsIgnoreCase("dokudoku") && props.getZoanPoint().equalsIgnoreCase(ID.ZOANMORPH_DOKU))
					entity.addPotionEffect(new PotionEffect(Potion.poison.id, 60, 0));
			}
			
			if(props.hasBusoHakiActive())
			{
				double power = props.getDoriki() / 500;
				event.ammount += power;
			}
			
			//System.out.println("Damage Recieved from " + sourceOfDamage.getCommandSenderName() + " : " + event.ammount);
		}
	}
	
	private boolean kairosekiChecks(EntityLivingBase entity)
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer entityP = (EntityPlayer) entity;
			return WyHelper.isBlockNearby(entityP, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) 
					|| DevilFruitsHelper.hasKairosekiItem(entityP) || entityP.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) 
					|| entityP.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre));
		}
		else
		{
			return WyHelper.isBlockNearby(entity, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre);
		}
	}
	
	@SubscribeEvent
	public void onEntityShootProjectile(ArrowLooseEvent event)
	{
		if(event.entityPlayer != null)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(event.entityPlayer);
			AbilityProperties abilityProps = AbilityProperties.get(event.entityPlayer);

			for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
			{						
				if(abilityProps.getAbilityFromSlot(i) != null && !abilityProps.getAbilityFromSlot(i).isOnCooldown()
						&& abilityProps.getAbilityFromSlot(i).getAttribute().isPassive() && abilityProps.getAbilityFromSlot(i).isPassiveActive()
						&& DevilFruitsHelper.isSniperAbility(abilityProps.getAbilityFromSlot(i)))
				{		
					abilityProps.getAbilityFromSlot(i).use(event.entityPlayer);
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			QuestProperties questProps = QuestProperties.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			
			if (!player.worldObj.isRemote)
			{
				if(ID.DEV_EARLYACCESS && !WyDebug.isDebug())
				{
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/cs2cv9ezaatzgd3/earlyaccess.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						boolean flag = false;
						
						while(scanner.hasNextLine())
						{
							String uuid = scanner.nextLine();
							System.out.println(uuid);
							if(uuid.startsWith("$"))
								continue;
							
							if(player.getUniqueID().toString().equals(uuid) || (uuid.startsWith("&") && player.getDisplayName().equals(uuid.substring(0, 2))))
							{
								flag = true;
								break;
							}													
						}
						
						if(!flag)
							((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");														
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");						
						e.printStackTrace();
					}				
				}
				
				if (props.getRace().equals("N/A") && props.getFaction().equals("N/A") && props.getFightStyle().equals("N/A") && !player.inventory.hasItemStack(new ItemStack(ListMisc.CharacterCreator)))
					player.inventory.addItemStackToInventory(new ItemStack(ListMisc.CharacterCreator, 1));
				
				if(props.getUsedFruit() != null && !props.getUsedFruit().equals("N/A"))
				{					
					ItemStack df = DevilFruitsHelper.getDevilFruitItem(props.getUsedFruit());
					
					abilityProps.clearDevilFruitAbilities();
					props.setGear(1);
					props.setZoanPoint("n/a");
					
					for(Ability a : ((AkumaNoMi)df.getItem()).abilities)
						if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a))
							abilityProps.addDevilFruitAbility(a);

				}
				
				DevilFruitsHelper.validateRacialMoves(player);
				DevilFruitsHelper.validateStyleMoves(player);
				
				for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
				{
					if(abilityProps.getAbilityFromSlot(i) != null)
					{
						if(DevilFruitsHelper.verifyIfAbilityIsBanned(abilityProps.getAbilityFromSlot(i)))
							abilityProps.setAbilityInSlot(i, null);
					}
				}			
				
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
				WyNetworkHelper.sendTo(new PacketAbilitySync(abilityProps), (EntityPlayerMP) player);
				
				if(MainConfig.enableUpdateMsg)
				{
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/3io0vaqiqaoabnh/version.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						
						while(scanner.hasNextLine())
						{
							String[] parts = scanner.nextLine().split("\\-");
	
							if(ID.PROJECT_MCVERSION.equals(parts[0]))
							{
								String cloudVersion = parts[1].replace(".", "");
								String localVersion = ID.PROJECT_VERSION.replace(".", "");
								
								if(Integer.parseInt(localVersion) < Integer.parseInt(cloudVersion))
								{
									ChatStyle updateStyle = new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/builds.php"));
									
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "[UPDATE]" + EnumChatFormatting.RED + " Mine Mine no Mi " + parts[1] + " is now available !").setChatStyle(updateStyle) );
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/builds.php]").setChatStyle(updateStyle) );
								}
							}					
						}
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				
			}		
		}
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone e) 
	{
		if(e.wasDeath) 
		{
			if(MainConfig.enableKeepIEEPAfterDeath.equals("full"))
			{
				NBTTagCompound compound = new NBTTagCompound();
				ExtendedEntityStats.get(e.original).saveNBTData(compound);
				ExtendedEntityStats props = ExtendedEntityStats.get(e.entityPlayer);
				props.loadNBTData(compound);
				
				compound = new NBTTagCompound();
				AbilityProperties.get(e.original).saveNBTData(compound);
				AbilityProperties abilityProps = AbilityProperties.get(e.entityPlayer);
				abilityProps.loadNBTData(compound);
				
				if(e.entityPlayer != null && MainConfig.enableExtraHearts)		
				{
					IAttributeInstance maxHp = e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
								
					if(props.getDoriki() / 100 <= 20)
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					else
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
				}
			}
			else if(MainConfig.enableKeepIEEPAfterDeath.equals("auto"))
			{
				ExtendedEntityStats props = ExtendedEntityStats.get(e.original);
				NBTTagCompound compound = new NBTTagCompound();
				
				String faction = props.getFaction();
				String race = props.getRace();
				String fightStyle = props.getFightStyle();
				String crew = props.getCrew();			
				
				props.resetNBTData(compound);
				props.loadNBTData(compound);
								
				props.setFaction(faction);
				props.setRace(race);
				props.setFightStyle(fightStyle);
				props.setCrew(crew);
				
				props.setMaxCola(100);
				props.setCola(props.getMaxCola());
				
				props.saveNBTData(compound);
								
				ExtendedEntityStats.get(e.entityPlayer).loadNBTData(compound);
			}
			
			NBTTagCompound compound = new NBTTagCompound();
			QuestProperties.get(e.original).saveNBTData(compound);
			QuestProperties questProps = QuestProperties.get(e.entityPlayer);
			questProps.loadNBTData(compound);
		}
	}

	@SubscribeEvent
	public void onDorikiGained(DorikiEvent event)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(event.player);
		
		if (event.props.getRace().equals(ID.RACE_HUMAN))
		{			
			ability(event.player, 500, RokushikiAbilities.SORU);
			ability(event.player, 1500, RokushikiAbilities.TEKKAI);
			ability(event.player, 3000, RokushikiAbilities.SHIGAN);
			ability(event.player, 4500, RokushikiAbilities.GEPPO);
			ability(event.player, 5000, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 6000, RokushikiAbilities.KAMIE);
			ability(event.player, 8500, RokushikiAbilities.RANKYAKU);
			ability(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
			//HAOSHOKU - 9000 + other			
		}
		else if (event.props.getRace().equals(ID.RACE_FISHMAN))
		{
			ability(event.player, 800, FishKarateAbilities.UCHIMIZU);
			ability(event.player, 2000, FishKarateAbilities.MURASAME);
			ability(event.player, 2500, FishKarateAbilities.KACHIAGEHAISOKU);
			ability(event.player, 3000, FishKarateAbilities.SAMEHADASHOTEI);
			ability(event.player, 4000, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 7500, FishKarateAbilities.KARAKUSAGAWARASEIKEN);
			ability(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
		}
		else if(event.props.getRace().equals(ID.RACE_CYBORG))
		{
			ability(event.player, 0, CyborgAbilities.FRESHFIRE);
			ability(event.player, 0, CyborgAbilities.COLAOVERDRIVE);
			ability(event.player, 0, CyborgAbilities.STRONGRIGHT);
			ability(event.player, 0, CyborgAbilities.RADICALBEAM);
			ability(event.player, 0, CyborgAbilities.COUPDEVENT);
			ability(event.player, 5500, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 8500, HakiAbilities.BUSOSHOKUHAKI);
		}
		
		if(event.player != null && MainConfig.enableExtraHearts)		
		{
			IAttributeInstance maxHp = event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
						
			if(props.getDoriki() / 100 <= 20)
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
			else
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
		}
	}	

	private void ability(EntityPlayer player, int doriki, Ability ability)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);

		if(ability instanceof KenbunshokuHaki || ability instanceof BusoshokuHaki)
		{
			if (props.getDoriki() >= doriki && !abilityProps.hasHakiAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
				abilityProps.addHakiAbility(ability);
			if ((props.getDoriki() < doriki || DevilFruitsHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasHakiAbility(ability))
				abilityProps.removeHakiAbility(ability);
		}
		else
		{
			if (props.getDoriki() >= doriki && !abilityProps.hasRacialAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
				abilityProps.addRacialAbility(ability);
			if ((props.getDoriki() < doriki || DevilFruitsHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasRacialAbility(ability))
				abilityProps.removeRacialAbility(ability);	
		}
	}	
	
}

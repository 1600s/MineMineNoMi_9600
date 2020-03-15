package xyz.pixelatedw.MineMineNoMi3.api;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;

public class WyHelper
{

	public enum Direction
	{
		SOUTH, SOUTH_EAST, EAST, NORTH, NORTH_EAST, NORTH_WEST, WEST, SOUTH_WEST;
	}

	public static AxisAlignedBB NULL_AABB = AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);

	public static AbilityExplosion explosion(Entity entity, double posX, double posY, double posZ, float size)
	{
		AbilityExplosion explosion = new AbilityExplosion(entity, posX, posY, posZ, size);
		explosion.doExplosionA();
		return explosion;
	}

	public static boolean isBlockNearby(EntityLivingBase player, int radius, Block... blocks)
	{
		for (Block b : blocks)
		{
			for (int x = -radius; x < radius; x++)
				for (int y = -radius; y < radius; y++)
					for (int z = -radius; z < radius; z++)
					{
						if (player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == b)
						{
							return true;
						}
					}
		}

		return false;
	}

	public static Block getBlockNearby(EntityLivingBase entity, int radius, Block block)
	{
		for (int x = -radius; x < radius; x++)
			for (int y = -radius; y < radius; y++)
				for (int z = -radius; z < radius; z++)
				{
					if (entity.worldObj.getBlock((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z) == block)
					{
						return entity.worldObj.getBlock((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z);
					}
				}

		return null;
	}

	public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void generateLangFiles()
	{
		Map<String, String> sorted = sortAlphabetically(WyRegistry.getLangMap());
		Set set = sorted.entrySet();
		Iterator i = set.iterator();

		Map.Entry prevEntry = null;

		File langFolder = new File(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/lang/");
		langFolder.mkdirs();

		if (langFolder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/lang/en_US.lang"), "UTF-8")))
			{
				while (i.hasNext())
				{
					Map.Entry entry = (Map.Entry) i.next();

					if (prevEntry != null)
					{
						if (!((String) prevEntry.getKey()).substring(0, 2).equals(((String) entry.getKey()).substring(0, 2)))
						{
							writer.write("\n");
						}
					}

					writer.write(entry.getKey() + "=" + entry.getValue() + "\n");

					prevEntry = entry;
				}
				writer.close();
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}
		}
	}

	public static void generateExtraTypScriptFiles()
	{
		getFancyAbilitiesList();
		
		Map<String, Ability> sorted = AbilityManager.instance().getHashMap();
		Set set = sorted.entrySet();
		Iterator i = set.iterator();

		File folder = new File(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/");
		folder.mkdirs();

		if (folder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/abilities.txt"), "UTF-8")))
			{
				while (i.hasNext())
				{
					Map.Entry entry = (Map.Entry) i.next();

					Ability abl = ((Ability) entry.getValue());

					String desc = Values.abilityDesc.entrySet().stream().filter(p -> WyHelper.getFancyName(abl.getAttribute().getAttributeName()).equals(p.getKey())).map(p -> p.getValue()).collect(Collectors.joining());

					if (desc.isEmpty())
						System.out.println(abl.getAttribute().getAttributeName() + " has no description! Blame FG!");

					writer.write("export var " 
							+ WyHelper.getFancyNameNoLowerCase(abl.getAttribute().getAttributeName()) + ": Ability = {" 
								+ "name: \'" + abl.getAttribute().getAttributeName() + "\', " 
								+ "desc: \'" + desc + "\', " 
								+ (abl.getAttribute().getAbilityCooldown() > 0 ? "cooldown: " + String.format("%.1f", ((double) abl.getAttribute().getAbilityCooldown() / 20)) + ", " : "") 
								+ (abl.getAttribute().getAbilityCharges() > 0 ? "chargeTime: " + String.format("%.1f", ((double) abl.getAttribute().getAbilityCharges() / 20)) + ", " : "")
								+ (abl.getAttribute().getProjectileDamage() > 1 ? "projectileDamage: " + abl.getAttribute().getProjectileDamage() + ", " : "") 
								+ ((abl.getAttribute().hasProjectile() && abl.getAttribute().isRepeater()) ? "projectileNumber: " + ((abl.getAttribute().getAbilityCooldown() / abl.getAttribute().getAbilityRepeaterTime()) / abl.getAttribute().getAbilityRepeaterTime()) + ", " : "") 
								+ (abl.getAttribute().getProjectileExplosionPower() > 0 ? "projectileExplosion: " + abl.getAttribute().getProjectileExplosionPower() + ", " : "")
								
								+ ((abl.getAttribute().getPotionEffectsForAoE() != null && abl.getAttribute().getPotionEffectsForAoE().length > 0) ? "aoeEffects: [" 
									+ getPotionEffectsFor(abl.getAttribute().getPotionEffectsForAoE()) + "], " : "") 
								+ ((abl.getAttribute().getPotionEffectsForProjectile() != null && abl.getAttribute().getPotionEffectsForProjectile().length > 0) ? "onHitEffects: [" 
									+ getPotionEffectsFor(abl.getAttribute().getPotionEffectsForProjectile()) + "], " : "") 
								+ ((abl.getAttribute().getPotionEffectsForUser() != null && abl.getAttribute().getPotionEffectsForUser().length > 0) ? "selfEffects: [" 
									+ getPotionEffectsFor(abl.getAttribute().getPotionEffectsForUser()) + "], " : "") + "};\n");

				}
				writer.close();
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}

			try (Writer writer = new
					BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/fruits.txt"), "UTF-" +
					"8")))
			{
				for (Item f : Values.devilfruits)
				{
					AkumaNoMi fruit = (AkumaNoMi) f;

					ItemStack itemStack = new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, fruit.getUnlocalizedName().substring(5)));

					writer.write("export var " + WyHelper.getFancyNameNoLowerCase(itemStack.getDisplayName()) + ": DevilFruit = {" + "name: \'" + itemStack.getDisplayName() + "\', " + "type: \'" + fruit.getType().getName() + "\', " + "abilities: [" + getAbilitiesFor(fruit) + "] " + "};\n");

				}
				writer.close();
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}
		}
	}

	private static void getFancyAbilitiesList()
	{
		File folder = new File(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/");
		folder.mkdirs();

		if (folder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/fancylist.txt"), "UTF-8")))
			{
				for (AkumaNoMi devilFruit : Values.devilfruits)
				{
					writer.write(devilFruit.getItemStackDisplayName(new ItemStack(devilFruit)) + "\n");
					for(Ability ability : devilFruit.abilities)
					{
						writer.write("> " + ability.getAttribute().getAttributeName() + "\n");
					}
					writer.write("\n");
				}
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}
		}
	}
	
	private static String getAbilitiesFor(AkumaNoMi df)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < df.abilities.length; i++)
		{
			if (i < df.abilities.length - 1)
				builder.append("" + WyHelper.getFancyNameNoLowerCase(df.abilities[i].getAttribute().getAttributeName()) + ", ");
			else
				builder.append("" + WyHelper.getFancyNameNoLowerCase(df.abilities[i].getAttribute().getAttributeName()) + "");
		}
		String abilitiesList = builder.toString();

		return abilitiesList;
	}

	private static String getPotionEffectsFor(PotionEffect[] pe)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < pe.length; i++)
		{
			double d = Math.ceil(((double) pe[i].getDuration() / 24));
			
			if (i < pe.length - 1)
				builder.append("\'" + I18n.format(pe[i].getEffectName()) + " " + String.format("%.0f", d) + " " + (d == 1 ? "second" : "seconds") + " (" + (pe[i].getAmplifier() > 0 ? "+" : "-") + ")\', ");
			else
				builder.append("\'" + I18n.format(pe[i].getEffectName()) + " " + String.format("%.0f", d) + " " + (d == 1 ? "second" : "seconds") + " (" + (pe[i].getAmplifier() > 0 ? "+" : "-") + ")\'");
		}
		String potionList = builder.toString();

		return potionList;
	}

	/*
	 * LEGACY METHOD TO GENERATE 1.8+ JSON MODELS public void
	 * generateIngameModels() { Set set =
	 * WyPI.apiInstance.getItemsMap().entrySet(); Iterator i = set.iterator();
	 * 
	 * while (i.hasNext()) { Map.Entry entry = (Map.Entry) i.next(); if
	 * (entry.getKey() instanceof Item) { Item item = (Item) entry.getKey();
	 * Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
	 * item, 0, new
	 * ModelResourceLocation(WyPI.apiInstance.getParentMod().getParentModID() +
	 * ":" + item.getUnlocalizedName().substring(5), "inventory")); } if
	 * (entry.getKey() instanceof Block) { Block block = (Block) entry.getKey();
	 * Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
	 * Item.getItemFromBlock(block), 0, new
	 * ModelResourceLocation(WyPI.apiInstance.getParentMod().getParentModID() +
	 * ":" + block.getUnlocalizedName().substring(5), "inventory")); } } }
	 */

	/**
	 * 0) is burning; 1) is sneaking; 2) is riding something; 3) is sprinting;
	 * 4) is eating; 5) is invisible
	 */
	public static void setEntityFlag(Entity player, int id, boolean bool)
	{
		byte b0 = player.getDataWatcher().getWatchableObjectByte(0);

		if (bool)
		{
			player.getDataWatcher().updateObject(0, Byte.valueOf((byte) (b0 | 1 << id)));
		}
		else
		{
			player.getDataWatcher().updateObject(0, Byte.valueOf((byte) (b0 & ~(1 << id))));
		}
	}

	public static Color hslToColor(float h, float s, float l)
	{
		float[] hsl = new float[]
		{
				h, s, l
		};

		if (s < 0.0f || s > 100.0f)
		{
			String message = "Color parameter outside of expected range - Saturation";
			throw new IllegalArgumentException(message);
		}

		if (l < 0.0f || l > 100.0f)
		{
			String message = "Color parameter outside of expected range - Luminance";
			throw new IllegalArgumentException(message);
		}

		h = h % 360.0f;
		h /= 360f;
		s /= 100f;
		l /= 100f;

		float q = 0;

		if (l < 0.5)
			q = l * (1 + s);
		else
			q = (l + s) - (s * l);

		float p = 2 * l - q;

		float r = Math.max(0, hueToRGB(p, q, h + (1.0f / 3.0f)));
		float g = Math.max(0, hueToRGB(p, q, h));
		float b = Math.max(0, hueToRGB(p, q, h - (1.0f / 3.0f)));

		r = Math.min(r, 1.0f);
		g = Math.min(g, 1.0f);
		b = Math.min(b, 1.0f);

		return new Color(r, g, b);
	}

	private static float hueToRGB(float p, float q, float h)
	{
		if (h < 0)
			h += 1;

		if (h > 1)
			h -= 1;

		if (6 * h < 1)
			return p + ((q - p) * 6 * h);

		if (2 * h < 1)
			return q;

		if (3 * h < 2)
			return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));

		return p;
	}

	public static Color hexToRGB(String hexColor)
	{
		if (hexColor.startsWith("#"))
			return Color.decode(hexColor);
		else
			return Color.decode("#" + hexColor);
	}

	public static void sendMsgToPlayer(EntityPlayer player, String text)
	{
		player.addChatComponentMessage(new ChatComponentText(text));
	}

	public static String getFancyName(String text)
	{
		return text.replaceAll("\\s+", "").toLowerCase().replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}

	public static String getFancyNameNoLowerCase(String text)
	{
		return text.replaceAll("\\s+", "").replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}

	public static List<EntityLivingBase> getEntitiesNear(int x, int y, int z, World world, double radius)
	{
		return getEntitiesNear(x, y, z, world, radius, EntityLivingBase.class);
	}

	public static List<EntityLivingBase> getEntitiesNear(int x, int y, int z, World world, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(radius, radius, radius);
		List list = world.getEntitiesWithinAABB(classEntity, aabb);
		return list;
	}

	public static List<EntityLivingBase> getEntitiesNear(Entity e, double radius)
	{
		return getEntitiesNear(e, radius, EntityLivingBase.class);
	}

	public static List<EntityLivingBase> getEntitiesNear(Entity e, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(e.posX, e.posY, e.posZ, e.posX + 1, e.posY + 1, e.posZ + 1).expand(radius, radius, radius);
		List list = e.worldObj.getEntitiesWithinAABB(classEntity, aabb);
		list.remove(e);
		return list;
	}

	public static List<EntityLivingBase> getEntitiesNear(TileEntity e, double radius)
	{
		return getEntitiesNear(e, radius, EntityLivingBase.class);
	}

	public static List<EntityLivingBase> getEntitiesNear(TileEntity e, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double) e.xCoord, (double) e.yCoord, (double) e.zCoord, (double) (e.xCoord + 1), (double) (e.yCoord + 1), (double) (e.zCoord + 1)).expand(radius, radius, radius);
		List list = e.getWorldObj().getEntitiesWithinAABB(classEntity, aabb);
		return list;
	}

	public static Direction get4Directions(Entity e)
	{
		switch (MathHelper.floor_double(e.rotationYaw * 4.0F / 360.0F + 0.5D) & 3)
		{
			case 0:
				return Direction.SOUTH;
			case 1:
				return Direction.WEST;
			case 2:
				return Direction.NORTH;
			case 3:
				return Direction.EAST;
		}
		return null;
	}

	public static Direction get8Directions(Entity e)
	{
		switch (MathHelper.floor_double(e.rotationYaw * 8.0F / 360.0F + 0.5D) & 7)
		{
			case 0:
				return Direction.SOUTH;
			case 1:
				return Direction.SOUTH_WEST;
			case 2:
				return Direction.WEST;
			case 3:
				return Direction.NORTH_WEST;
			case 4:
				return Direction.NORTH;
			case 5:
				return Direction.NORTH_EAST;
			case 6:
				return Direction.EAST;
			case 7:
				return Direction.SOUTH_EAST;
		}
		return null;
	}

	public static MovingObjectPosition rayTraceBlocks(Entity e)
	{
		float f = 1.0F;
		float f1 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * f;
		float f2 = e.prevRotationYaw + (e.rotationYaw - e.prevRotationYaw) * f;
		double d = e.prevPosX + (e.posX - e.prevPosX) * (double) f;
		double d1 = (e.prevPosY + (e.posY - e.prevPosY) * (double) f + 1.6200000000000001D) - (double) e.getYOffset();
		double d2 = e.prevPosZ + (e.posZ - e.prevPosZ) * (double) f;
		Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5000D;

		Vec3 vec3 = vec3d.addVector((double) f7 * d3, (double) f6 * d3, (double) f9 * d3);
		MovingObjectPosition ray = e.worldObj.rayTraceBlocks(vec3d, vec3, false);

		return ray;
	}

	public static void createCube(Entity e, int i, Block b, Block... bannedBlocks)
	{
		createCube(e, new int[] {i, i, i}, b, bannedBlocks);
	}

	public static void createCube(Entity e, int[] s, Block b, Block... bannedBlocks)
	{
		for (int x = (s[0] * 0) - s[0]; x < s[0]; x++)
		for (int y = (s[1] * 0) - s[1]; y < s[1]; y++)
		for (int z = (s[2] * 0) - s[2]; z < s[2]; z++)
		{
			if(!Arrays.stream(bannedBlocks).anyMatch(p -> p == b))
				e.worldObj.setBlock((int) e.posX + x, (int) e.posY + y, (int) e.posZ + z, b);
		}
	}
	
	public static void createSphere(Object e, int size, final Block b, Block... bannedBlocks)
	{
		int x = 0, y = 0, z = 0;
		if (e instanceof Entity)
		{
			x = (int) ((Entity) e).posX;
			y = (int) ((Entity) e).posY;
			z = (int) ((Entity) e).posZ;
		}
		else if (e instanceof TileEntity)
		{
			x = (int) ((TileEntity) e).xCoord;
			y = (int) ((TileEntity) e).yCoord;
			z = (int) ((TileEntity) e).zCoord;
		}

		createSphere(e, x, y, z, size, b, bannedBlocks);
	}

	public static void createSphere(Object e, int x, int y, int z, int size, final Block b, Block... bannedBlocks)
	{
		final World world;
		if (e instanceof Entity)
			world = ((Entity) e).worldObj;
		else if (e instanceof TileEntity)
			world = ((TileEntity) e).getWorldObj();
		else
			world = null;
		
		Sphere.generate(x, y, z, size, new ISphere()
		{
			public void call(int x, int y, int z)
			{
				//for(Block bannedBlock : bannedBlocks)
				//{
					//if(world.getBlock(x, y, z) == bannedBlock)
					//	continue;
					
					world.setBlock(x, y, z, b);
				//}
			}
		});
	}
	
	public static void removeStackFromInventory(EntityPlayer player, ItemStack stack)
	{
		for (int i = 0; i < player.inventory.mainInventory.length; i++)
		{
			if (stack == player.inventory.getStackInSlot(i))
			{
				player.inventory.setInventorySlotContents(i, null);
				break;
			}
		}
	}

	public static void removeStackFromSlot(EntityPlayer player, int index)
	{
		if (player.inventory.mainInventory[index] != null)
			player.inventory.mainInventory[index] = null;
	}
}

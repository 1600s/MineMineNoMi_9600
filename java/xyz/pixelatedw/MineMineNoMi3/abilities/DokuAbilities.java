package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Random;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.DokuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class DokuAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new Hydra(), new ChloroBall(), new DokuFugu(), new VenomRoad(), new DokuGumo(), new VenomDemon()};	
		
	
	public static class DokuGumo extends Ability
	{
		public DokuGumo() 
		{
			super(ListAttributes.DOKUGUMO); 
		}

		public void duringPassive(EntityPlayer player, int passiveTimer) 
		{
			if(passiveTimer > 400)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
			}

			WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DOKUGOMU, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);

			for(EntityLivingBase enemy : WyHelper.getEntitiesNear(player, 10))
			{
				if(!enemy.isPotionActive(Potion.blindness.id))
					enemy.addPotionEffect(new PotionEffect(Potion.blindness.id, 10 * 20, 0));
				if(!enemy.isPotionActive(Potion.poison.id))
					enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 10 * 20, 1));
				if(!enemy.isPotionActive(Potion.weakness.id))
					enemy.addPotionEffect(new PotionEffect(Potion.weakness.id, 10 * 20, 1));
			}	
		}	
		
		public void endPassive(EntityPlayer player) 
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}

	}
	
	public static class VenomDemon extends Ability
	{
		public VenomDemon() 
		{
			super(ListAttributes.VENOMDEMON); 
		}
		
		public void startPassive(EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");

			props.setZoanPoint(ID.ZOANMORPH_DOKU);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
		
		public void duringPassive(EntityPlayer player, int passiveTimer) 
		{
			if(passiveTimer >= 800)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}
			
			if(!WyHelper.isBlockNearby(player, 2, Blocks.water, Blocks.flowing_water, ListMisc.KairosekiOre, ListMisc.KairosekiBlock))
			{
				if (player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != Blocks.air
				&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != ListMisc.Poison
				&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != ListMisc.DemonPoison)
					DevilFruitsHelper.placeIfCanReplaceBlock(player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ, ListMisc.DemonPoison);
			}
			
			WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_VENOMDEMON, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
		}		
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			if(!WyHelper.isBlockNearby(player, 2, Blocks.water, Blocks.flowing_water, ListMisc.KairosekiOre, ListMisc.KairosekiBlock))
			{
				target.addPotionEffect(new PotionEffect(Potion.poison.id, 400, 2));
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_DOKU))
			{
				props.setZoanPoint("n/a");	
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
			}
			
			this.startCooldown();
			this.startExtUpdate(player);
		}
	}
	
	public static class DokuFugu extends Ability
	{
		public DokuFugu() 
		{
			super(ListAttributes.DOKUFUGU); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.ChloroBall(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class VenomRoad extends Ability
	{
		public VenomRoad() 
		{
			super(ListAttributes.VENOMROAD); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.VenomRoad(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class ChloroBall extends Ability
	{
		public ChloroBall() 
		{
			super(ListAttributes.CHLOROBALL); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.ChloroBall(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Hydra extends Ability
	{
		public Hydra() 
		{
			super(ListAttributes.HYDRA); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.Hydra(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
}

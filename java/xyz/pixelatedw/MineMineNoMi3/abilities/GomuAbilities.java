package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GomuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.YamiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class GomuAbilities 
{
	public static Ability[] abilitiesArray = new Ability[] {new GomuGomuNoPistol(), new GomuGomuNoBazooka(), new GomuGomuNoRocket(), new GomuGomuNoGatling(), new GearSecond(), new GearThird(), new GearForth()};

	public static class GearForth extends Ability
	{
		public GearForth() 
		{
			super(ListAttributes.GEARFOURTH); 
		}
		
		public void passive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(DevilFruitsHelper.hasBusoHakiAquired(player))
				super.passive(player);
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be activated while Busoshoku Haki is active !");
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.setGear(4);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
			
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			player.addPotionEffect(new PotionEffect(Potion.jump.id, 25, 2, false));
			
			if(passiveTimer >= 600)
			{
				props.setGear(1);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.setPassiveActive(false);
				super.use(player);
			}
		}
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setGear(1);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			super.use(player);
		} 
	}	
	
	public static class GearThird extends Ability
	{
		public GearThird() 
		{
			super(ListAttributes.GEARTHIRD); 
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.setGear(3);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		} 
			
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(passiveTimer >= 1200)
			{
				props.setGear(1);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setGear(1);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			super.use(player);
		} 
	}		
	
	
	public static class GearSecond extends Ability
	{
		public GearSecond() 
		{
			super(ListAttributes.GEARSECOND); 
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.setGear(2);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		} 
			
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 25, 1, false));
			if(!player.worldObj.isRemote)
	    		WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GEARSECOND, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			
			if(passiveTimer >= 1200)
			{
				props.setGear(1);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setGear(1);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			super.use(player);
		} 
	}
	
	public static class GomuGomuNoGatling extends Ability
	{
		public GomuGomuNoGatling() 
		{
			super(ListAttributes.GOMUGOMUNOGATLING); 
		}
		
		public void use(EntityPlayer player)
		{		
			if(!this.isOnCooldown)
			{
				ExtendedEntityStats props = ExtendedEntityStats.get(player);
				
				int type = 0;
				int projectileSpace = 3;
				
				switch(props.getGear())
				{
					case 1:
						type = 0;
						if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Gatling");
						this.attr.setAbilityCooldown(3.5);
						break;
					case 2:
						type = 1;
						if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Jet Gatling");
						this.attr.setAbilityCooldown(1.5);
						break;
					case 3:
						type = 2;
						projectileSpace = 7;
						if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Elephant Gatling");
						this.attr.setAbilityCooldown(5.5);
						break;
					case 4:
						type = 3;
						projectileSpace = 7;
						if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Kong Organ");
						this.attr.setAbilityCooldown(7);
						break;
				}
	
				for(int j = 0; j < 25; j++)
				{
					AbilityProjectile proj = null;
					if(type == 0)
						proj = new GomuProjectiles.GomuGomuNoGatling(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOGATLING);
					else if(type == 1)
						proj = new GomuProjectiles.GomuGomuNoJetGatling(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOJETGATLING);
					else if(type == 2)
						proj = new GomuProjectiles.GomuGomuNoElephantGatling(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOELEPHANTGATLING);
					else if(type == 3)
						proj = new GomuProjectiles.GomuGomuNoKongOrgan(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOKONGORGAN);
					
					proj.setLocationAndAngles(
							player.posX + WyMathHelper.randomWithRange(-projectileSpace, projectileSpace) + player.worldObj.rand.nextDouble(), 
							(player.posY + 0.3) + WyMathHelper.randomWithRange(0, projectileSpace) + player.worldObj.rand.nextDouble(), 
							player.posZ + WyMathHelper.randomWithRange(-projectileSpace, projectileSpace) + player.worldObj.rand.nextDouble(), 
							0, 0);
					player.worldObj.spawnEntityInWorld(proj);
				}
				
				super.use(player);
			}
		} 
	}
	
	public static class GomuGomuNoRocket extends Ability
	{
		public GomuGomuNoRocket() 
		{
			super(ListAttributes.GOMUGOMUNOROCKET); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new GomuProjectiles.GomuGomuNoRocket(player.worldObj, player, ListAttributes.GOMUGOMUNOROCKET);
			super.use(player);
		} 
	}
	
	public static class GomuGomuNoBazooka extends Ability
	{
		public GomuGomuNoBazooka() 
		{
			super(ListAttributes.GOMUGOMUNOBAZOOKA); 
		}

		public void endCharging(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			switch(props.getGear())
			{
				case 1:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Bazooka");
					this.projectile = new GomuProjectiles.GomuGomuNoBazooka(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOBAZOOKA);
					this.attr.setAbilityCooldown(12);
					this.attr.setAbilityCharges(20);
					break;
				case 2:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Jet Bazooka");
					this.projectile = new GomuProjectiles.GomuGomuNoJetBazooka(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOJETBAZOOKA);
					this.attr.setAbilityCooldown(6);
					this.attr.setAbilityCharges(10);
					break;
				case 3:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Grizzly Magnum");
					this.projectile = new GomuProjectiles.GomuGomuNoGrizzlyMagnum(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOGRIZZLYMAGNUM);
					this.attr.setAbilityCooldown(20);
					this.attr.setAbilityCharges(40);
					break;
				case 4:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Leo Bazooka");
					this.projectile = new GomuProjectiles.GomuGomuNoLeoBazooka(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOLEOBAZOOKA);
					this.attr.setAbilityCooldown(30);
					this.attr.setAbilityCharges(40);
					break;
			}
			
			super.endCharging(player);
		} 
	}
	
	public static class GomuGomuNoPistol extends Ability
	{
		public GomuGomuNoPistol() 
		{
			super(ListAttributes.GOMUGOMUNOPISTOL); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);		
			switch(props.getGear())
			{
				case 1:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Pistol");
					this.projectile = new GomuProjectiles.GomuGomuNoPistol(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOPISTOL);
					this.attr.setAbilityCooldown(10);
					break;
				case 2:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Jet Pistol");
					this.projectile = new GomuProjectiles.GomuGomuNoJetPistol(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOJETPISTOL);
					this.attr.setAbilityCooldown(5);
					break;
				case 3:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Elephant Gun");
					this.projectile = new GomuProjectiles.GomuGomuNoElephantGun(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOELEPHANTGUN);
					this.attr.setAbilityCooldown(15);
					break;
				case 4:
					if(MainConfig.enableAnimeScreaming) this.attr.setAttributeName("Gomu Gomu no Kong Gun");
					this.projectile = new GomuProjectiles.GomuGomuNoKongGun(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOKONGGUN);
					this.attr.setAbilityCooldown(30);
					break;
			}

			super.use(player);
		} 
	}
}

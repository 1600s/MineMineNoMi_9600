package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.OpeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.Heart;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.ItemCoreWeapon;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OpeAbilities
{

	public static Ability[] abilitiesArray = new Ability[]
	{
			new Room(), new Mes(), new CounterShock(), new GammaKnife(), new Takt(), new Shambles(), new InjectionShot()
	};

	public static class InjectionShot extends Ability
	{
		public InjectionShot()
		{
			super(ListAttributes.INJECTIONSHOT);
		}

		public void use(EntityPlayer player)
		{
			if (DevilFruitsHelper.isEntityInRoom(player))
			{
				if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemCoreWeapon)
				{
					if (!this.isOnCooldown)
					{
						double mX = (double) (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) * 0.4);
						double mZ = (double) (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) * 0.4);

						double f2 = MathHelper.sqrt_double(mX * mX + player.motionY * player.motionY + mZ * mZ);
						mX /= (double) f2;
						mZ /= (double) f2;
						mX += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
						mZ += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
						mX *= 3;
						mZ *= 3;

						motion("=", mX, player.motionY, mZ, player);

						if (player.worldObj instanceof WorldServer)
							((WorldServer) player.worldObj).getEntityTracker().func_151248_b(player, new S0BPacketAnimation(player, 0));
					}

					super.use(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			}
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
		}

		public void duringCooldown(EntityPlayer player, int currentCooldown)
		{
			if (currentCooldown > 13 * 20)
			{
				for (EntityLivingBase e : WyHelper.getEntitiesNear(player, 1.6))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 20);

					e.addPotionEffect(new PotionEffect(Potion.poison.id, 10 * 20, 0));
					e.addPotionEffect(new PotionEffect(Potion.confusion.id, 10 * 20, 0));
				}
			}
		}
	}

	public static class Takt extends Ability
	{
		private HashMap<EntityLivingBase, Double> entitiesInRoom = new HashMap<EntityLivingBase, Double>();

		public Takt()
		{
			super(ListAttributes.TAKT);
		}

		public void passive(EntityPlayer player)
		{
			if (!this.isPassiveActive())
			{
				if (DevilFruitsHelper.isEntityInRoom(player))
				{
					for (EntityLivingBase entity : WyHelper.getEntitiesNear(player, 40))
					{
						if (DevilFruitsHelper.isEntityInRoom(entity) && !entity.equals(player))
						{
							entitiesInRoom.put(entity, entity.posY + 3);
						}
					}
					super.passive(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
			}
			else
			{
				super.passive(player);
			}
		}

		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			if (!DevilFruitsHelper.isEntityInRoom(player))
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}

			if (passiveTimer >= 160)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}

			Iterator it = entitiesInRoom.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<EntityLivingBase, Double> pair = (Map.Entry)it.next();
				pair.getKey().setPositionAndUpdate(pair.getKey().posX, pair.getValue(), pair.getKey().posZ);
				pair.getKey().fallDistance = 0;
			}
		}

		public void endPassive(EntityPlayer player)
		{
			this.startCooldown();
			this.startExtUpdate(player);
			this.entitiesInRoom.clear();
		}
	}

	public static class Shambles extends Ability
	{
		public Shambles()
		{
			super(ListAttributes.SHAMBLES);
		}

		public void use(EntityPlayer player)
		{
			if (DevilFruitsHelper.isEntityInRoom(player))
			{
				if (!this.isOnCooldown)
				{
					int sphereSize = 0;
					while (sphereSize < 40) {
						List<EntityLivingBase> entityList = WyHelper.getEntitiesNear(player,sphereSize);
						if (entityList.size() > 0) {
							EntityLivingBase entity = entityList.get( (int) WyMathHelper.randomWithRange(0,entityList.size()-1));
							if(DevilFruitsHelper.isEntityInRoom(entity)){
								System.out.println("Ok");
								double[] beforeCoords = new double[]{player.posX,player.posY,player.posZ};
								player.setPositionAndRotation(entity.posX,entity.posY,entity.posZ,entity.rotationYaw,entity.rotationPitch);
								player.setPositionAndUpdate(entity.posX,entity.posY,entity.posZ);
								entity.setPositionAndUpdate(beforeCoords[0],beforeCoords[1],beforeCoords[2]);
								break;
						}
						}
						sphereSize += 1;
					}
					}
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
		}
	}

	public static class GammaKnife extends Ability
	{
		public GammaKnife()
		{
			super(ListAttributes.GAMMAKNIFE);
		}

		public void use(EntityPlayer player)
		{
			if (DevilFruitsHelper.isEntityInRoom(player))
			{
				this.projectile = new OpeProjectiles.GammaKnife(player.worldObj, player, attr);
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
		}
	}

	public static class Mes extends Ability
	{
		public Mes()
		{
			super(ListAttributes.MES);
		}

		public void hitEntity(EntityPlayer player, EntityLivingBase target)
		{
			ExtendedEntityStats targetprops = ExtendedEntityStats.get(target);

			if (targetprops.hasHeart())
			{
				ItemStack heart = new ItemStack(ListMisc.Heart);
				((Heart) heart.getItem()).setHeartOwner(heart, target);
				heart.setStackDisplayName(target.getCommandSenderName() + "'s Heart");

				player.inventory.addItemStackToInventory(heart);

				targetprops.setHasHeart(false);
			}

			super.hitEntity(player, target);
		}
	}

	public static class CounterShock extends Ability
	{
		public CounterShock()
		{
			super(ListAttributes.COUNTERSHOCK);
		}

		public void hitEntity(EntityPlayer player, EntityLivingBase target)
		{
			super.hitEntity(player, target);
			WyNetworkHelper.sendTo(new PacketParticles(ID.PARTICLEFX_ELTHOR, target.posX, target.posY, target.posZ), (EntityPlayerMP) player);
		}
	}


	public static class Room extends Ability
	{
		private boolean canSpawnRoom = true;

		public Room()
		{
			super(ListAttributes.ROOM);
		}

		public void use(EntityPlayer player)
		{
			if (!this.isOnCooldown && canSpawnRoom)
			{
				final World world = player.worldObj;
				Sphere.generate((int) player.posX, (int) player.posY, (int) player.posZ, 20, new ISphere()
				{
					public void call(int x, int y, int z)
					{
						DevilFruitsHelper.placeIfCanReplaceBlock(world, x, y, z, ListMisc.Ope);
					}
				});
				player.worldObj.setBlock((int) player.posX, (int) player.posY, (int) player.posZ, ListMisc.OpeMid);

				canSpawnRoom = false;
				super.use(player);
			}
			else if (!canSpawnRoom)
			{
				if (!WyHelper.isBlockNearby(player, 30, ListMisc.OpeMid))
					canSpawnRoom = true;
				else
				{
					for(int x = -50; x < 50; x++)
					for(int y = -50; y < 50; y++)
					for(int z = -50; z < 50; z++)
					{
						if(player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == ListMisc.Ope || player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == ListMisc.OpeMid)
							player.worldObj.setBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z, Blocks.air);
					}	
					canSpawnRoom = true;
				}
			}
		}

		public void alterSpawnFlag(boolean flag)
		{
			canSpawnRoom = flag;
		}
	}

	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
}

package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class ExtraAbilities
{
	
	
	public static class HybridPoint extends Ability
	{
		public HybridPoint()
		{
			super(ListAttributes.HYBRIDPOINT);
		}

		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if (!this.isOnCooldown)
			{
				if (props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");

				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_HYBRID))
				{
					props.setZoanPoint("n/a");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				} 
				else
				{
					props.setZoanPoint(ID.ZOANMORPH_HYBRID);
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				}

				super.use(player);
			}
		}
	}

	public static class PowerPoint extends Ability
	{
		public PowerPoint()
		{
			super(ListAttributes.POWERPOINT);
		}

		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if (!this.isOnCooldown)
			{
				if (props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");

				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_POWER))
				{
					props.setZoanPoint("n/a");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				} 
				else
				{
					props.setZoanPoint(ID.ZOANMORPH_POWER);
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				}

				super.use(player);
			}
		}
	}

	public static class SpeedPoint extends Ability
	{
		public SpeedPoint()
		{
			super(ListAttributes.SPEEDPOINT);
		}

		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if (!this.isOnCooldown)
			{
				if (props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");

				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_SPEED))
				{
					props.setZoanPoint("n/a");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				} 
				else
				{
					props.setZoanPoint(ID.ZOANMORPH_SPEED);
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				}

				super.use(player);
			}
		}
	}
}

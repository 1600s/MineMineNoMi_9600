package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class DoruProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {DoruDoruArtsMori.class, ListAttributes.DORUDORUARTSMORI});
		abilitiesClassesArray.add(new Object[] {CandleLock.class, ListAttributes.CANDLELOCK});
	}
	
	public static class DoruDoruArtsMori extends AbilityProjectile
	{
		public DoruDoruArtsMori(World world)
		{super(world);}
		
		public DoruDoruArtsMori(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public DoruDoruArtsMori(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}
	
	public static class CandleLock extends AbilityProjectile
	{
		public CandleLock(World world)
		{super(world);}
		
		public CandleLock(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public CandleLock(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{			
			if(hit.entityHit != null && hit.entityHit instanceof EntityLivingBase)
			{
				EntityLivingBase target = ((EntityLivingBase)hit.entityHit);
				ExtendedEntityStats propz = ExtendedEntityStats.get(target);
				
				propz.setIsCandleLocked(true);
				WyNetworkHelper.sendToAll(new PacketSyncInfo(target.getEntityId(), propz));
			}
		}
	}
	
}

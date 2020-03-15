package xyz.pixelatedw.MineMineNoMi3.blocks.tileentities;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.abilities.OpeAbilities.Room;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class TileEntityOpe extends TileEntity
{
	
	public TileEntityOpe()
	{

	}

    public void updateEntity()
    {
    	if(!this.worldObj.isRemote)
    	{
	    	List<EntityLivingBase> nearbyPlayers = WyHelper.getEntitiesNear(this, 28).stream().filter(x -> 
	    		{ 
	    			if(x instanceof EntityPlayer && ExtendedEntityStats.get(x).getUsedFruit().equalsIgnoreCase("opeope"))
	    				return true;
	
	    			return false; 
	    		})
	    		.collect(Collectors.toList());
	    	
	    	for(EntityLivingBase elb : nearbyPlayers)
	    	{
	    		EntityPlayer user = (EntityPlayer) elb;
	    		
	    		if(!WyHelper.isBlockNearby(user, 28, ListMisc.OpeMid))
	    		{
		    		for(int i = 0; i < AbilityProperties.get(user).countAbilitiesInHotbar() - 1; i++)
		    		{
		    			Ability abl = AbilityProperties.get(user).getAbilityFromSlot(i);
		    			if(abl != null && abl.getAttribute().getAttributeName().equalsIgnoreCase("room"))
		    				((Room)abl).alterSpawnFlag(true);
		    		}
	    		}
	    	}
	    	
	    	if(nearbyPlayers.isEmpty())
	    		clearRoom();    
    	}
	}
    
    public void clearRoom()
    {
    	World world = this.worldObj;
    	
		for(int i = -22; i < 22; i++)
		for(int k = -21; k < 21; k++)
		for(int j = -22; j < 22; j++)
			if(world.getBlock((int) this.xCoord + i, (int) this.yCoord + k, (int) this.zCoord + j) == ListMisc.Ope)
				world.setBlock((int) this.xCoord + i, (int) this.yCoord + k, (int) this.zCoord + j, Blocks.air);
		world.setBlock((int) this.xCoord, (int) this.yCoord, (int) this.zCoord, Blocks.air);
    }
	
}


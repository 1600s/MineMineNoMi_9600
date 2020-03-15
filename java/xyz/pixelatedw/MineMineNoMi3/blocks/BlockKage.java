package xyz.pixelatedw.MineMineNoMi3.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorld;


public class BlockKage extends Block
{	
	private int ticks = 200;
	private int maxTicks = 200;
	
	public BlockKage()
	{
		super(Material.iron);
	} 

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) 
    { 
    	if(entity instanceof EntityLivingBase)
    	{
	    	ExtendedEntityStats props = ExtendedEntityStats.get((EntityLivingBase) entity);	    	

	    	if(!props.getUsedFruit().equals("kagekage") || !props.hasYamiPower())
	    		entity.setInWeb(); 
    	}
    	else 
    		entity.setInWeb();
    }
	
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
    	if(entity instanceof EntityLivingBase)
    	{
	    	ExtendedEntityStats props = ExtendedEntityStats.get((EntityLivingBase) entity);	    	

	    	if(props.getUsedFruit().equals("kagekage") || props.hasYamiPower())
	    	{
	    		this.setBlockBounds(0, 0, 0, 1, 1, 1);
	    		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
	    	}
    	}
	}
    
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random rand)
    {
    	if(ticks > 0)
    		ticks--;
    	else
    	{
    		WyNetworkHelper.sendToServer(new PacketWorld(x, y, z, Block.getIdFromBlock(Blocks.air)));
    		ticks = maxTicks;
    	}
	}
	
}

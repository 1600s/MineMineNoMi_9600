package xyz.pixelatedw.MineMineNoMi3.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorld;

public class BlockBarrier extends Block
{	
	private int ticks = 2;
	private int maxTicks = 2;
	
	public BlockBarrier()
	{
		super(Material.iron);
	} 
	
	public BlockBarrier setTimer(int i)
	{
		this.ticks = i;
		this.maxTicks = i;
		return this;
	}
	
	public boolean isOpaqueCube() {return false;}

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() { return 1; }
    
    public boolean renderAsNormalBlock() { return false; }
	
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

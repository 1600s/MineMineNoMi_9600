package xyz.pixelatedw.MineMineNoMi3.blocks.dials;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorld;

public class BlockDialFire extends Block
{
	protected static final AxisAlignedBB CARPET_AABB = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
	
	public BlockDialFire()
	{
		super(Material.iron);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.125F, 0.75F);
	}  

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) { return CARPET_AABB; }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) { return CARPET_AABB; } 
	
	public boolean isOpaqueCube() {return false;}

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() { return 1; }

    public boolean renderAsNormalBlock() { return false; }
	
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
    	return true;
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    	if(entity instanceof EntityLivingBase)
    	{
    		entity.setFire(240);
    		world.setBlock(x, y, z, Blocks.air);
    	}
    }
    
    public Item getItemDropped(int i1, Random rand, int fortune)
    {
        return ListMisc.DialFire;
    }
    
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return true;
    }
    
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
		this.blockIcon = par1.registerIcon("mineminenomi:dialblock");
		this.topIcon = par1.registerIcon("mineminenomi:flamedialblock_top");
	}
	
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.topIcon : par1 == 0 ? this.topIcon : this.blockIcon;
	}

}

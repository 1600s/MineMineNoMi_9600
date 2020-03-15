package xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityMarine extends MarineData
{ 

	public EntityMarine(World world) 
	{
		super(world, new String[] {"marine1", "marine2", "marine3", "marine4", "marine5"});	
 	}
	
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}
	
    protected void addRandomArmor()
    {
        this.setCurrentItemOrArmor(0, new ItemStack(ListMisc.MarineSword));
    }
    
	public double[] itemOffset() 
	{
		return new double[] {0, 0, -0.1};
	}
	
	public int getDorikiPower() { return this.worldObj.rand.nextInt(3) + 10; }
	public int getBellyInPockets() { return this.worldObj.rand.nextInt(10) + 5; }
	
    protected void dropRareDrop(int i)
    {
        switch (this.rand.nextInt(4))
        {
            case 0:
                this.dropItem(ListMisc.MarineHelm, 1); break;
            case 1:
                this.dropItem(ListMisc.MarineChestplate, 1); break;
            case 2:
                this.dropItem(ListMisc.MarineLeggings, 1); break;
            case 3:
                this.dropItem(ListMisc.MarineBoots, 1); break;
        }
    }
}

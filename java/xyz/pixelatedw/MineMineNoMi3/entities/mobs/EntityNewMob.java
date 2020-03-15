package xyz.pixelatedw.MineMineNoMi3.entities.mobs;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityNewMob extends EntityMob implements IDynamicRenderer
{

	private boolean hasHaki = false, isLogia = false;
	private String texture = "null", model = "n/a";
	private int state, textureId;
	private String[] textures;

	public EntityNewMob(World worldIn) 
	{
		this(worldIn, null);
	}
	
	public EntityNewMob(World worldIn, String[] textures) 
	{
		super(worldIn);
		addRandomArmor();
		this.textures = textures;
	}
		
	public String getTexture() { return textures[this.getDataWatcher().getWatchableObjectInt(28)]; }
	public int getTextureId() { return this.getDataWatcher().getWatchableObjectInt(28); }
	protected void setTexture(int texture) { this.getDataWatcher().updateObject(28, texture); }
	
	public boolean hasBusoHaki() { return false; }
	public boolean hasHaoHaki() { return false; }
	public boolean isLogia() { return false; }	
	public String getDevilFruitUsed() { return "n/a"; }
	public int getDorikiPower() { return 1; }
	public int getBellyInPockets() { return 1; }
	
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);	
		nbt.setInteger("Texture", getTextureId());
	}
	
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		setTexture(nbt.getInteger("Texture"));
	}
	
    protected void addRandomArmor() {}
	
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
    {
        super.onSpawnWithEgg(data);
        addRandomArmor();
		if(textures != null && textures.length > 0)
			this.setTexture(this.rand.nextInt(textures.length));
        return data;
    }
	
    public void setState(int i) { this.getDataWatcher().updateObject(27, i); }
    public int getState() { return this.getDataWatcher().getWatchableObjectInt(27); }
    
	protected void entityInit()
	{
		this.getDataWatcher().addObject(27, state);
		this.getDataWatcher().addObject(28, textureId);
		super.entityInit();
	}
	
	protected boolean isValidLightLevel()
	{return true;} 
    
	protected boolean canDespawn()
	{return true;}
    
	public boolean isAIEnabled()
	{return true;}
	
	public boolean getCanSpawnHere()
	{return true;}

	public String getMobTexture()
	{ return this.getTexture(); }

	public double[] itemOffset() 
	{
		return new double[] {0, 0, 0};
	}

	public double[] itemScale() 
	{
		return new double[] {1, 1, 1};
	}

}

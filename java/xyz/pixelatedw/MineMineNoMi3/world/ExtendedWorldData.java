package xyz.pixelatedw.MineMineNoMi3.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorldData;

public class ExtendedWorldData extends WorldSavedData
{

	private static final String IDENTIFIER = "mineminenomi";

	private boolean isSwordsmanDojoSpawned = false;
	private int totalDojosSpawned;
		
	public ExtendedWorldData()
	{
		super(IDENTIFIER);
	}

	public ExtendedWorldData(String identifier)
	{
		super(identifier);
	}

	public static ExtendedWorldData get(World world)
	{
		ExtendedWorldData data = (ExtendedWorldData) world.loadItemData(ExtendedWorldData.class, IDENTIFIER);
		if (data == null)
		{
			data = new ExtendedWorldData();
			world.setItemData(IDENTIFIER, data);
		}
		return data;
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		 this.isSwordsmanDojoSpawned = nbt.getBoolean("isSwordsmanDojoSpawned");
		 this.totalDojosSpawned = nbt.getInteger("totalDojosSpawned");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setBoolean("isSwordsmanDojoSpawned",  this.isSwordsmanDojoSpawned);
		nbt.setInteger("totalDojosSpawned", this.totalDojosSpawned);
	}
	
	public int getTotalDojosSpawned()
	{
		return this.totalDojosSpawned;
	}
	
	public void countUpDojoSpawned()
	{
		this.totalDojosSpawned++;
		if(this.totalDojosSpawned >= MainConfig.maxDojoSpawn)
			this.setSwordsmanDojoSpawned(true);
		markDirty();
	}
	
	public void setDojoSpawned(int value)
	{
		this.totalDojosSpawned = value;
		if(this.totalDojosSpawned >= MainConfig.maxDojoSpawn)
			this.setSwordsmanDojoSpawned(true);
		markDirty();		
	}
	
	public boolean isSwordsmanDojoSpawned() 
	{ 
		return this.isSwordsmanDojoSpawned; 
	}
	
	public void setSwordsmanDojoSpawned(boolean value) 
	{ 
		this.isSwordsmanDojoSpawned = value; 
		markDirty();
	}
	
}

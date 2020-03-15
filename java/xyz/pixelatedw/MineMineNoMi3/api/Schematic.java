package xyz.pixelatedw.MineMineNoMi3.api;

import net.minecraft.nbt.NBTTagList;

public class Schematic
{
	private  NBTTagList tileentities;
	private  short width, height, length;
	private byte[] blocks, data;
	private String name = "N/A";

	public Schematic(String name, NBTTagList tiles, short width, short height, short length, byte[] blocks, byte[] data)
	{
		this.name = name;
		this.tileentities = tiles;
		this.width = width;
		this.height = height;
		this.length = length;
		this.blocks = blocks;
		this.data = data;
	}	 

	public int getWidth()
	{
		return width;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public String getName()
	{
		return name;
	}

	public byte[] getBlocks() 
	{
		return blocks;
	}

	public byte[] getData() 
	{
		return data;
	}
	
}

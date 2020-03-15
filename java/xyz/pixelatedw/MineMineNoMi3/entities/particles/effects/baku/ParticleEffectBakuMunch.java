package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.baku;

import java.util.Random;
import java.util.Timer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks.ParticleTaskCharge;

public class ParticleEffectBakuMunch extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = player.worldObj.rand.nextDouble();
			double offsetY = 1;
			double offsetZ = player.worldObj.rand.nextDouble();

			player.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK.getParticleName() + "_" + Block.getIdFromBlock(player.worldObj.getBlock((int)posX, (int)posY, (int)posZ))  + "_" + player.worldObj.getBlockMetadata((int)posX, (int)posY, (int)posZ), posX + offsetX, posY + offsetY, posZ + offsetZ, 0, 0, 0);
		}
	}

}

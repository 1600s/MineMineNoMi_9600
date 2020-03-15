package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.sniper;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;

public class ParticleEffectKemuriBoshi extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 512; i++)
		{
			double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 2.0D;
			double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 5.0D;
			double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 2.0D;
	      
			player.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), posX + offsetX + WyMathHelper.randomWithRange(-7, 7), (posY + 0.5) + offsetY + WyMathHelper.randomWithRange(-1, 3), posZ + offsetZ + WyMathHelper.randomWithRange(-7, 7), 0.0D, 0.1D, 0.0D);
		}	
	}

}

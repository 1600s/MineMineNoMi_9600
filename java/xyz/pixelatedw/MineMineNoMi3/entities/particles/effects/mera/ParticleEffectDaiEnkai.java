package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.mera;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;

public class ParticleEffectDaiEnkai extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			
			MainMod.proxy.spawnCustomParticles(player, 
					new EntityParticleFX(player.worldObj, ID.PARTICLE_ICON_MERA, 
							posX + offsetX, 
							posY + 1.5 + offsetY, 
							posZ + offsetZ, 
							0, 0, 0)
					.setParticleScale(1.3F)
					.setParticleGravity(0)
					.setParticleAge(10));		
		}
	}

}

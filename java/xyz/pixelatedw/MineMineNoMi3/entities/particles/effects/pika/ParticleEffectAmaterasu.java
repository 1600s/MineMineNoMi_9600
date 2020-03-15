package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.pika;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;

public class ParticleEffectAmaterasu extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{
		double offsetX = 0;
		double offsetZ = 0;
		
		switch(WyHelper.get4Directions(player))
		{
			case NORTH:
				offsetZ = -4.5; break;
			case SOUTH:
				offsetZ = 4.5; break;
			case EAST:
				offsetX = 4.5; break;
			case WEST:
				offsetX = -4.5; break;
		}
		
		MainMod.proxy.spawnCustomParticles(player, 
				new EntityParticleFX(player.worldObj, ID.PARTICLE_ICON_PIKA,
						posX + offsetX,
						posY + 0.5,
						posZ + offsetZ,
						0, 0, 0)
				.setParticleScale(50).setParticleGravity(0).setParticleAge(10).setHasZoom());		
	}

}

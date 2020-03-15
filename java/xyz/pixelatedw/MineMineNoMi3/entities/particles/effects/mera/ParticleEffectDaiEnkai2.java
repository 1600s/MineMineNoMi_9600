package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.mera;

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

public class ParticleEffectDaiEnkai2 extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		EntityParticleFX particle = new EntityParticleFX(player.worldObj, ID.PARTICLE_ICON_MERA, 
				posX, 
				posY + 0.5, 
				posZ, 
				0, 0, 0);
		timer.schedule(ParticleTaskCharge.Create(player, particle.posX, particle.posY, particle.posZ, particle, 1.0, 1, 0.45, 0.2), 0);
	}

}

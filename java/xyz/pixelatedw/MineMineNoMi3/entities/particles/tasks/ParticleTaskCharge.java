package xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks;

import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;

public class ParticleTaskCharge extends TimerTask
{
	
	private EntityLivingBase player;
	private Object particle;
	private double radius, posX, posY, posZ, height, opening;
	private int density;

	public static ParticleTaskCharge Create(EntityLivingBase player, double posX, double posY, double posZ, Object particle, double radius, int density, double opening, double height)
	{
		return new ParticleTaskCharge(player, posX, posY, posZ, particle, radius, density, opening, height);
	}
	
	private ParticleTaskCharge(EntityLivingBase player, double posX, double posY, double posZ, Object particle, double radius, int density, double opening, double height)
	{
		this.player = player;
		this.particle = particle;
		this.radius = radius;
		this.density = density;
		this.opening = opening;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}

	public void run()
	{
		double phi = 0;
		double x, y, z;

		while(phi < density * Math.PI)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{
					x = opening * (radius / Math.PI + t) / Math.cos(t * phi + i * Math.PI);
					y = 0.5 * t;
					z = opening * (radius / Math.PI + t) / Math.sin(t * phi + i * Math.PI);
					
					try
					{
						if(this.particle instanceof EntityParticleFX)
						{
							EntityParticleFX clone = ((EntityParticleFX)particle).clone(this.posX + x, this.posY + y, this.posZ + z);
							Minecraft.getMinecraft().effectRenderer.addEffect(clone);
						}
						else							
							player.worldObj.spawnParticle((String) particle, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						Thread.sleep(1);
					}
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
}

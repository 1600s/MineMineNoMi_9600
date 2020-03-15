package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HieProjectiles.IceBall;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HieProjectiles.IceBlockPartisan;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class GuraProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {ShimaYurashi.class, ListAttributes.SHIMAYURASHI});
		abilitiesClassesArray.add(new Object[] {Kaishin.class, ListAttributes.KAISHIN});
	}
	
	public static class ShimaYurashi extends AbilityProjectile
	{
		public ShimaYurashi(World world)
		{super(world);}
		
		public ShimaYurashi(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public ShimaYurashi(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{		
			if(this.worldObj.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					if(i % 2 == 0)
						this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE.getParticleName(), this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
					else
					{
						EntityParticleFX particle = new EntityParticleFX(this.worldObj, ID.PARTICLE_ICON_GURA2, 
								posX + this.rand.nextFloat() * 2 - 1, 
								posY + this.rand.nextFloat() * 2 - 1, 
								posZ + this.rand.nextFloat() * 2 - 1, 
								0, 0, 0)
								.setParticleAge(10).setParticleScale(3);
						
						MainMod.proxy.spawnCustomParticles(this, particle);	
					}
				}
			}
				
			super.onUpdate();
		}
	}	
	
	public static class Kaishin extends AbilityProjectile
	{
		public Kaishin(World world)
		{super(world);}
		
		public Kaishin(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Kaishin(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{		
			if(this.worldObj.isRemote)
			{
				for (int i = 0; i < 3; i++)
				{
					if(i % 2 == 0)
						this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE.getParticleName(), this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
					else
					{
						EntityParticleFX particle = new EntityParticleFX(this.worldObj, ID.PARTICLE_ICON_GURA2, 
								posX + this.rand.nextFloat() * 2 - 1, 
								posY + this.rand.nextFloat() * 2 - 1, 
								posZ + this.rand.nextFloat() * 2 - 1, 
								0, 0, 0)
								.setParticleAge(10).setParticleScale(3);
						
						MainMod.proxy.spawnCustomParticles(this, particle);	
					}
				}
			}
			
			super.onUpdate();
		}
	}	
}

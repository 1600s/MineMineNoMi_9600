package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.PirateData;

public class EntityDoppelman extends EntityMob
{	
	private EntityPlayer owner;

	public EntityDoppelman(World worldIn, EntityPlayer owner) 
	{ 
		this(worldIn);
		this.setOwner(owner);
	    this.tasks.addTask(1, new EntityAISwimming(this));
	    this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
	    this.tasks.addTask(2, new EntityAIOpenDoor(this, true));
	    this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
	    this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
	    this.tasks.addTask(5, new EntityAILookIdle(this));
	    this.tasks.addTask(6, new EntityAIHurtByTarget(this, true));
	    this.tasks.addTask(7, new EntityAIWatchClosest(this, MarineData.class, 8.0F));
	    this.tasks.addTask(7, new EntityAIWatchClosest(this, PirateData.class, 8.0F));
	    this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
	    this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, PirateData.class, 0, true));
	    this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, MarineData.class, 0, true));
	}

	public EntityDoppelman(World worldIn) 
	{
		super(worldIn); 
	}

	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(10);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
	}
	
    protected Entity findPlayerToAttack()
    {
        EntityPlayer target = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        return target != null && target != this.owner && this.canEntityBeSeen(target) ? target : null;
    }
	
	public boolean isAIEnabled()
	{return true;}
    
	public void onEntityUpdate()
	{ 	
		if(this.getEntityToAttack() == this.owner)
			this.setTarget(null);
		
		if(!this.worldObj.isRemote && (this.owner == null || !this.owner.isEntityAlive()))
			this.setDead();
		
		super.onEntityUpdate();
	}
    
	private void setOwner(EntityPlayer player) {this.owner = player;}
	public EntityPlayer getOwner() {return this.owner;}
	
}

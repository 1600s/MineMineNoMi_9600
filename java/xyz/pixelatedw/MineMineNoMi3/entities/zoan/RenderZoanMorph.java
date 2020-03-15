package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelZoanMorph;

public class RenderZoanMorph extends Render
{
	private ResourceLocation texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/null.png");
	private ModelBase model;
	private double scale;
	private float offset[] = new float[3];
	
	public RenderZoanMorph(ModelBase model, String texture)
	{
		this.shadowSize = 0;
		this.model = model;
		this.scale = 1;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = new float[] {0, 0, 0};
	}
	
	public RenderZoanMorph(ModelBase model, String texture, double scale)
	{
		this.shadowSize = 0;
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = new float[] {0, 0, 0};
	}
	
	public RenderZoanMorph(ModelBase model, String texture, double scale, float offset[])
	{
		this.shadowSize = 0;
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = offset;
	}

    public void renderFirstPersonArm(EntityPlayer player)
    {
        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        if(this.model instanceof ModelZoanMorph && ((ModelZoanMorph) this.model).getHandRenderer() != null)
        	((ModelZoanMorph) this.model).getHandRenderer().render(0.0625F);
    }
	
	public void doRender(Entity entity, double x, double y, double z, float u, float v) 
	{
		EntityLivingBase entityLiving = ((EntityLivingBase)entity);
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float) x + this.offset[0], (float)y + 1.3F + this.offset[1], (float) z + this.offset[2]);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    	//GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);
		
    	GL11.glScaled(this.scale, this.scale, this.scale);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		

		float ageInTicks = entityLiving.ticksExisted + v;
		
        float headYawOffset = this.interpolateRotation(entityLiving.prevRenderYawOffset, entityLiving.renderYawOffset, v);
        float headYaw = this.interpolateRotation(entityLiving.prevRotationYawHead, entityLiving.rotationYawHead, v);
        
        float headPitch = entityLiving.prevRotationPitch + (entityLiving.rotationPitch - entityLiving.prevRotationPitch) * v;
		
		this.rotateCorpse(entityLiving, ageInTicks, headYawOffset, v);
        
		float limbSwingAmount = entityLiving.prevLimbSwingAmount + (entityLiving.limbSwingAmount - entityLiving.prevLimbSwingAmount) * v;
		float limbSwing = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - v);
		
		this.model.onGround = entityLiving.getSwingProgress(v);

		Minecraft.getMinecraft().renderEngine.bindTexture(this.getEntityTexture(entity));
		this.model.render(entityLiving, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);
		
		GL11.glPopMatrix();
	}

    private float interpolateRotation(float lowerLimit, float upperLimit, float range)
    {
        float f3;

        for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return lowerLimit + range * f3;
    }
    
    protected void rotateCorpse(EntityLivingBase entityLiving, float ageInTicks, float headYawOffset, float v)
    {
        GL11.glRotatef(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

        if (entityLiving.deathTime > 0)
        {
            float f3 = ((float)entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
            f3 = MathHelper.sqrt_float(f3);

            if (f3 > 1.0F)
            {
                f3 = 1.0F;
            }

            //GL11.glRotatef(f3 * this.getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
        }
    }
	
	public ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return texture;
	}

}

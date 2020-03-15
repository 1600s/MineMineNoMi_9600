package xyz.pixelatedw.MineMineNoMi3.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.gui.extra.GUIButtonNoTexture;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

@SideOnly(Side.CLIENT)
public class GUICC extends GuiScreen
{
	private EntityPlayer player;
	private int page = 0, selectedOpt = 0, maxOpt, lastFac = 0, lastRace = 0, lastFStyle = 0;
	
	public GUICC(EntityPlayer player)
	{
		this.player = player;
	}
	
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
    
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
    
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_BLANK);
		drawTexturedModalRect(posX, posY + 50, 0, 0, 256, 256);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_COMBATMODE);
		drawTexturedModalRect(posX + 15, posY + 75, 0, 92, 25, 100);	
		drawTexturedModalRect(posX + 200, posY + 73, 26, 92, 30, 100);
		
		drawTexturedModalRect(posX - 80, posY + 70, 0, 196, 96, 49);
		drawTexturedModalRect(posX - 80, posY + (int)(70 * 1.6), 0, 196, 96, 49);
		drawTexturedModalRect(posX - 80, posY + (int)(70 * 2.2), 0, 196, 96, 49);	
		drawTexturedModalRect(posX + 75, posY + 200, 0, 196, 96, 49);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
		drawTexturedModalRect(posX - 78, posY + 80, 0, 232, 86, 22);
		drawTexturedModalRect(posX - 70, posY + 121, 94, 230, 74, 22);
		drawTexturedModalRect(posX - 75, posY + 148, 172, 210, 85, 52);		
		drawTexturedModalRect(posX + 76, posY + 207, 110, 0, 85, 30);
		
		if(this.page == 0) 
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_PIRATE);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 69, posY + 65, 0, 0, 118, 30);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_MARINE);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 70, posY + 75, 0, 30, 129, 23);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_BOUNTYHUNTER);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 46, posY + 65, 0, 52, 170, 50);
			}
		}
		if(this.page == 1)
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_HUMAN);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 75, posY + 65, 0, 102, 129, 30);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_FISHMAN);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 65, posY + 70, 0, 130, 129, 26);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_CYBORG);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 75, posY + 72, 0, 160, 110, 24);
			}	
		}
		if(this.page == 2) 
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_SWORDSMAN);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 38, posY + 72, 0, 185, 170, 24);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_SNIPER);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 80, posY + 72, 0, 209, 110, 23);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.ICON_MEDIC);
				drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_STRINGS1);
				drawTexturedModalRect(posX + 75, posY + 72, 109, 159, 86, 27);
			}	
		}
		
		super.drawScreen(x, y, f);
	}
	
	public void initGui()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		this.buttonList.add(new GUIButtonNoTexture(100, posX - 78, posY + 73, 90, 36, ""));	
		this.buttonList.add(new GUIButtonNoTexture(101, posX - 78, (int)(posY + 73 * 1.6), 90, 36, ""));	
		this.buttonList.add(new GUIButtonNoTexture(102, posX - 78, (int)(posY + 72 * 2.2), 90, 36, ""));
			
		this.buttonList.add(new GUIButtonNoTexture(103, posX + 15, posY + 85, 24, 100, ""));		
		this.buttonList.add(new GUIButtonNoTexture(104, posX + 210, posY + 83, 24, 100, ""));
		
		this.buttonList.add(new GUIButtonNoTexture(105, posX + 77, posY + 205, 90, 35, ""));
	}

	public void updateScreen()
	{
		if(this.page == 0)
			maxOpt = 3;
		if(this.page == 2)
			maxOpt = 3;
		if(this.page != 2)
			maxOpt = 3;
	}
	
	public void actionPerformed(GuiButton button)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		
		if(button.id >= 0 && button.id < 10)
			props.setFaction(button.displayString);	
		
		if(button.id >= 10 && button.id < 20)
			props.setRace(button.displayString);

		if(button.id >= 20 && button.id < 30)
			props.setFightStyle(button.displayString);	
		
		switch (button.id)
		{ 			
		case 104:
			if(this.selectedOpt + 1 < maxOpt)
				this.selectedOpt++;
			else
				this.selectedOpt = 0;
			break;
			
		case 103:
			if(this.selectedOpt - 1 > -1)
				this.selectedOpt--;
			else
				this.selectedOpt = maxOpt - 1;
			break;
			
		case 105:
			if(this.lastFac == 0) props.setFaction(ID.FACTION_PIRATE);
			else if(this.lastFac == 1) props.setFaction(ID.FACTION_MARINE);
			else if(this.lastFac == 2) props.setFaction(ID.FACTION_BOUNTYHUNTER);
			
			if(this.lastRace == 0) props.setRace(ID.RACE_HUMAN);
			else if(this.lastRace == 1) props.setRace(ID.RACE_FISHMAN);
			else if(this.lastRace == 2) props.setRace(ID.RACE_CYBORG);
			
			if(this.lastFStyle == 0) props.setFightStyle(ID.FSTYLE_SWORDSMAN);
			else if(this.lastFStyle == 1) props.setFightStyle(ID.FSTYLE_SNIPER);
			else if(this.lastFStyle == 2) props.setFightStyle(ID.FSTYLE_DOCTOR);
				
			switch(this.page)
			{
				case 0:
				{
					switch(this.selectedOpt)
					{
					case 0:
						props.setFaction(ID.FACTION_PIRATE);
						break;
					case 1:
						props.setFaction(ID.FACTION_MARINE);
						break;
					case 2:
						props.setFaction(ID.FACTION_BOUNTYHUNTER);
						break;
					}
					break;
				}
				case 1:
				{
					switch(this.selectedOpt)
					{
					case 0:
						props.setRace(ID.RACE_HUMAN);
						break;
					case 1:
						props.setRace(ID.RACE_FISHMAN);
						break;
					case 2:
						props.setRace(ID.RACE_CYBORG);
						break;
					}
					break;					
				}
				case 2:
				{
					switch(this.selectedOpt)
					{
					case 0:
						props.setFightStyle(ID.FSTYLE_SWORDSMAN);
						break;
					case 1:
						props.setFightStyle(ID.FSTYLE_SNIPER);
						break;
					case 2:
						props.setFightStyle(ID.FSTYLE_DOCTOR);
						break;
					}
					break;					
				}
			}
			
			if(!props.getRace().equals("N/A") && !props.getFaction().equals("N/A") && !props.getFightStyle().equals("N/A"))
			{
				this.mc.displayGuiScreen((GuiScreen)null);
				WyNetworkHelper.sendToServer(new PacketSync(props));
				WyNetworkHelper.sendToServer(new PacketPlayer("delete_book"));
			}
			break;
			
		case 100:
			if(this.page == 0) lastFac = this.selectedOpt;
			if(this.page == 1) lastRace = this.selectedOpt;
			if(this.page == 2) lastFStyle = this.selectedOpt;			
			
			this.page = 0;
			
			if(this.page == 0) this.selectedOpt = lastFac;
			if(this.page == 1) this.selectedOpt = lastRace;
			if(this.page == 2) this.selectedOpt = lastFStyle;		
			
			this.mc.displayGuiScreen((GuiScreen)this);
			break;
			
		case 101:
			if(this.page == 0) lastFac = this.selectedOpt;
			else if(this.page == 1) lastRace = this.selectedOpt;
			else if(this.page == 2) lastFStyle = this.selectedOpt;
			
			this.page = 1;

			if(this.page == 0) this.selectedOpt = lastFac;
			if(this.page == 1) this.selectedOpt = lastRace;
			if(this.page == 2) this.selectedOpt = lastFStyle;	
			
			this.mc.displayGuiScreen((GuiScreen)this);
			break;
			
		case 102:
			if(this.page == 0) lastFac = this.selectedOpt;
			else if(this.page == 1) lastRace = this.selectedOpt;
			else if(this.page == 2) lastFStyle = this.selectedOpt;
			
			this.page = 2;

			if(this.page == 0) this.selectedOpt = lastFac;
			if(this.page == 1) this.selectedOpt = lastRace;
			if(this.page == 2) this.selectedOpt = lastFStyle;	
			
			this.mc.displayGuiScreen((GuiScreen)this);
			break;
		}
		
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}

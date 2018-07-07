package com.gw.dm.network;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.InputConfusedMovement;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.lwjgl.opengl.GL11;

public class DungeonMobsClient implements ISidedProxy
{
  private final double NAME_VISION_DISTANCE = 32.0D;
  private Minecraft mc;
  private World lastWorld;
  private long nextPacketTime;
  private EntityLivingBase retainedTarget;
  public InputConfusedMovement confusedMovementInput;

  public void preInit()
  {
    FMLCommonHandler.instance().bus().register(this);
    this.mc = FMLClientHandler.instance().getClient();
  }

  public void load()
  {
    this.nextPacketTime = 0L;

    MinecraftForge.EVENT_BUS.register(this);

    this.retainedTarget = null;
  }

  public void onKnockBackPacket(float xv, float zv)
  {
	  DungeonMobsHelper.knockBack(FMLClientHandler.instance().getClient().thePlayer, xv, zv);
  }
  
  public void onConfusionPacket(boolean b)
  {
	  if(b)
		  DungeonMobsHelper.makePlayerConfused(FMLClientHandler.instance().getClient().thePlayer);
	  else
		  DungeonMobsHelper.makePlayerNormal(FMLClientHandler.instance().getClient().thePlayer);
  }
}
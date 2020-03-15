package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class KiloAbilities {

    public static Ability[] abilitiesArray = new Ability[]{new Weightless(), new KickOffJump(),};

    private static void movePlayer(String c, double x, double y, double z, EntityPlayer p) {
        WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
    }

    public static class Weightless extends Ability {

        public Weightless() {
            super(ListAttributes.WEIGHTLESS);
        }

        public void passive(EntityPlayer player) {
            super.passive(player);

        }

        public void duringPassive(EntityPlayer player, int passiveTimer) {
                if (player.onGround) {
                    replaceUmbrella(player);
                    this.setPassiveActive(false);
                    this.setCooldownActive(true);
                    this.endPassive(player);
                } else if (player.getHeldItem() != null && player.getHeldItem().getItem() == ListMisc.UmbrellaOpen) {
                    player.fallDistance = 0;
                    movePlayer("=",player.motionX,-0.2,player.motionZ,player);
                } else if (player.getHeldItem() != null && player.getHeldItem().getItem() == ListMisc.Umbrella) {
                    int slot = player.inventory.currentItem;
                    player.inventory.setInventorySlotContents(slot, new ItemStack(ListMisc.UmbrellaOpen));
                } else {
                    replaceUmbrella(player);
                }


        }

        public void endPassive(EntityPlayer player) {
            this.startCooldown();
            this.startExtUpdate(player);
            replaceUmbrella(player);

        }

        public void replaceUmbrella(EntityPlayer player) {
            for (int count = 0; count < player.inventory.getSizeInventory(); count++) {
                if (player.inventory.getStackInSlot(count) != null && player.inventory.getStackInSlot(count).getItem() == ListMisc.UmbrellaOpen) {
                    player.inventory.setInventorySlotContents(count, new ItemStack(ListMisc.Umbrella));
                }
            }
        }


    }

    public static class KickOffJump extends Ability {
        private double initialY = 255;
        private boolean isFlying = false;
        private int countDoon = 0;


        public KickOffJump() {
            super(ListAttributes.KICKOFFJUMP);
        }

        public void passive(EntityPlayer player) {
            this.initialY = player.posY;
            super.passive(player);

        }

        public void duringPassive(EntityPlayer player, int passiveTimer) {
            if (!isFlying && player.posY > this.initialY) {
                this.isFlying = true;
            } else if (isFlying && this.countDoon <= 10) {
                movePlayer("=",player.motionX,1,player.motionZ,player);
                this.countDoon += 1;
            } else if (isFlying && this.countDoon >=4) {
                this.setPassiveActive(false);
                this.setCooldownActive(true);
                this.endPassive(player);
            } else if (player.onGround) {
                this.initialY = player.posY;
            }
        }

        public void endPassive(EntityPlayer player) {
            this.countDoon = 0;
            this.isFlying = false;
            this.startCooldown();
            this.startExtUpdate(player);
        }
    } {

    }

//    public static class HeavyPunch extends Ability {
//        public HeavyPunch() {
//            super (ListAttributes.HEAVYPUNCH);
//        }
//
//        public void passive(EntityPlayer player) {
//            super.passive(player);
//        }
//
//        public void duringPassive(EntityPlayer player, int countDown) {
//            WyHelper.sendMsgToPlayer(player,"Hi");
//
//            if (!player.onGround) {
//                this.endPassive(player);
//            }
//        }
//
//        public void endPassive(EntityPlayer player) {
//            WyHelper.sendMsgToPlayer(player,"Done");
//        }
//
//    }
}


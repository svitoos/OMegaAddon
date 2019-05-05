package oc.item;

import li.cil.oc.api.CreativeTab;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import oc.Config;
import oc.Omega;

import java.util.List;

public class UpgradeIronInventoryItem extends UpgradeAbstractInventoryItem {

    public UpgradeIronInventoryItem() {
        super(32);
        setCreativeTab(CreativeTab.instance);
        setUnlocalizedName("upgrade_iron_inventory");
        setTextureName(Omega.MOD_ID + ":upgrade_iron_inventory");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("lore.inventory.tier") + " " + Config.IRON_INVENTORY_TIER);
        list.add(StatCollector.translateToLocal("lore.inventory.size") + " 32");
    }

    @Override
    public int tier(ItemStack stack) {
        return Config.IRON_INVENTORY_TIER;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        return worksWith(stack) && Robot.class.isAssignableFrom(host);
    }
}

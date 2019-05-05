package oc.item;

import li.cil.oc.api.CreativeTab;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import oc.Config;
import oc.Omega;

import java.util.List;

public class UpgradeDiamondInventoryItem extends UpgradeAbstractInventoryItem {

    public UpgradeDiamondInventoryItem() {
        super(64);
        setCreativeTab(CreativeTab.instance);
        setUnlocalizedName("upgrade_diamond_inventory");
        setTextureName(Omega.MOD_ID + ":upgrade_diamond_inventory");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("lore.inventory.tier") + " " + Config.DIAMOND_INVENTORY_TIER);
        list.add(StatCollector.translateToLocal("lore.inventory.size") + " 64");
    }

    @Override
    public int tier(ItemStack stack) {
        return Config.DIAMOND_INVENTORY_TIER;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        return worksWith(stack) && Robot.class.isAssignableFrom(host);
    }
}

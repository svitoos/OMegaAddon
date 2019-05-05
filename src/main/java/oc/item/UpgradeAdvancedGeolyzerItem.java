package oc.item;

import li.cil.oc.api.CreativeTab;
import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Drone;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import oc.Config;
import oc.Omega;
import oc.driver.DriverUpgradeAdvancedGeolyzer;

import java.util.List;

public class UpgradeAdvancedGeolyzerItem extends Item implements li.cil.oc.api.driver.Item, EnvironmentProvider, HostAware {

    public UpgradeAdvancedGeolyzerItem() {
        setCreativeTab(CreativeTab.instance);
        setUnlocalizedName("upgrade_advanced_geolyzer");
        setTextureName(Omega.MOD_ID + ":upgrade_advanced_geolyzer");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("lore.geolyzer_tier.name") + " " + Config.GEOLYZER_TIER);
        list.add(StatCollector.translateToLocal("lore.geolyzer_scan_cost.name") + " " + Config.GEOLYZER_SCAN_COST);
        list.add(StatCollector.translateToLocal("lore.geolyzer_radius.name") + " " + Config.GEOLYZER_RADIUS);
    }

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        if(!worksWith(stack))
            return null;

        return DriverUpgradeAdvancedGeolyzer.class;
    }

    @Override
    public boolean worksWith(ItemStack stack) {
        return stack.getItem().equals(this);
    }

    @Override
    public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
        return new DriverUpgradeAdvancedGeolyzer(host);
    }

    @Override
    public String slot(ItemStack stack) {
        return Slot.Upgrade;
    }

    @Override
    public int tier(ItemStack stack) {
        return Config.GEOLYZER_TIER - 1;
    }

    @Override
    public NBTTagCompound dataTag(ItemStack stack) {
        return null;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        boolean b = worksWith(stack);
        return b && Robot.class.isAssignableFrom(host) || Drone.class.isAssignableFrom(host);
    }
}

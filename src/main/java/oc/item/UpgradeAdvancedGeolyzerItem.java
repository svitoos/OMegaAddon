package oc.item;

import li.cil.oc.api.CreativeTab;
import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import oc.Config;
import oc.Omega;
import oc.driver.DriverUpgradeAdvancedGeolyzer;

public class UpgradeAdvancedGeolyzerItem extends Item implements li.cil.oc.api.driver.Item, EnvironmentProvider, HostAware {

    public UpgradeAdvancedGeolyzerItem() {
        setCreativeTab(CreativeTab.instance);
        setUnlocalizedName("upgrade_advanced_geolyzer");
        setTextureName(Omega.MOD_ID + ":upgrade_advanced_geolyzer");
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
        return b && Robot.class.isAssignableFrom(host);
    }
}

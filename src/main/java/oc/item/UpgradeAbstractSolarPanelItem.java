package oc.item;

import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import oc.driver.DriverAbstractAdvancedSolarPanel;

public abstract class UpgradeAbstractSolarPanelItem extends Item implements li.cil.oc.api.driver.Item, EnvironmentProvider, HostAware {

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        return DriverAbstractAdvancedSolarPanel.class;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        return false;
    }

    @Override
    public boolean worksWith(ItemStack stack) {
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
        return null;
    }

    @Override
    public String slot(ItemStack stack) {
        return Slot.Upgrade;
    }

    @Override
    public int tier(ItemStack stack) {
        return 0;
    }

    @Override
    public NBTTagCompound dataTag(ItemStack stack) {
        return null;
    }
}

package oc.item;

import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Inventory;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class UpgradeAbstractInventoryItem extends Item implements Inventory, HostAware, EnvironmentProvider {

    private int capacity;

    public UpgradeAbstractInventoryItem(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int inventoryCapacity(ItemStack stack) {
        return capacity;
    }

    @Override
    public boolean worksWith(ItemStack stack) {
        return stack.getItem().equals(this);
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
        return 1;
    }

    @Override
    public NBTTagCompound dataTag(ItemStack stack) {
        return null;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        return worksWith(stack) && Robot.class.isAssignableFrom(host);
    }

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        return null;
    }
}

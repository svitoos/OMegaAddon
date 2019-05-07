package oc.item;

import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import oc.driver.DriverAbstractSolarPanel;

import java.util.List;

public abstract class UpgradeAbstractSolarPanelItem extends Item implements li.cil.oc.api.driver.Item, EnvironmentProvider, HostAware {

    public void addInformation(List list, int tier, int day_energy_output, int night_energy_output,
                               boolean can_charge_tool) {
        list.add(StatCollector.translateToLocal("lore.tier.name") + " " + tier);
        list.add(StatCollector.translateToLocal("lore.day_output.name") + " " +  day_energy_output);
        list.add(StatCollector.translateToLocal("lore.night_output.name") + " " + night_energy_output);
        list.add(StatCollector.translateToLocal("lore.can_charge.name") + " " + (can_charge_tool ? "Yes" : "No"));
    }

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        return DriverAbstractSolarPanel.class;
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

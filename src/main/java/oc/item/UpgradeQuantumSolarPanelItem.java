package oc.item;

import li.cil.oc.api.CreativeTab;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import oc.Config;
import oc.Omega;
import oc.driver.DriverQuantumSolarPanel;

import java.util.List;

public class UpgradeQuantumSolarPanelItem extends UpgradeAbstractSolarPanelItem {

    public UpgradeQuantumSolarPanelItem() {
        setCreativeTab(CreativeTab.instance);
        setUnlocalizedName("upgrade_quantum_solar_panel");
        setTextureName(Omega.MOD_ID + ":upgrade_quantum_solar_panel");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("lore.tier.name") + " " + Config.QUANTUM_SOLAR_PANEL_TIER);
        list.add(StatCollector.translateToLocal("lore.day_output.name") + " " + Config.QUANTUM_SOLAR_PANEL_DAY_PER_TICK);
        list.add(StatCollector.translateToLocal("lore.night_output.name") + " " + Config.QUANTUM_SOLAR_PANEL_NIGHT_PER_TICK);
        list.add(StatCollector.translateToLocal("lore.can_charge.name") + " " + (Config.QUANTUM_SOLAR_PANEL_CHARGE_TOOL ? "Yes" : "No"));
    }

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        return DriverQuantumSolarPanel.class;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        return worksWith(stack) && Robot.class.isAssignableFrom(host);
    }

    @Override
    public boolean worksWith(ItemStack stack) {
        return stack.getItem().equals(this);
    }

    @Override
    public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
        return new DriverQuantumSolarPanel(host);
    }

    @Override
    public int tier(ItemStack stack) {
        return Config.QUANTUM_SOLAR_PANEL_TIER - 1;
    }
}

package oc.item;

import li.cil.oc.api.CreativeTab;
import li.cil.oc.api.internal.Drone;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import oc.Config;
import oc.Omega;
import oc.driver.DriverHybridSolarPanel;

import java.util.List;

public class UpgradeHybridSolarPanelItem extends UpgradeAbstractSolarPanelItem {

    public UpgradeHybridSolarPanelItem() {
        setCreativeTab(CreativeTab.instance);
        setUnlocalizedName("upgrade_hybrid_solar_panel");
        setTextureName(Omega.MOD_ID + ":upgrade_hybrid_solar_panel");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        addInformation(list, Config.HYBRID_SOLAR_PANEL_TIER, Config.HYBRID_SOLAR_PANEL_DAY_PER_TICK,
                Config.HYBRID_SOLAR_PANEL_NIGHT_PER_TICK, Config.HYBRID_SOLAR_PANEL_CHARGE_TOOL);
    }

    @Override
    public Class<?> getEnvironment(ItemStack stack) {
        if(!worksWith(stack))
            return null;

        return DriverHybridSolarPanel.class;
    }

    @Override
    public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
        return worksWith(stack) && Robot.class.isAssignableFrom(host) || Drone.class.isAssignableFrom(host);
    }

    @Override
    public boolean worksWith(ItemStack stack) {
        return stack.getItem().equals(this);
    }

    @Override
    public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
        return new DriverHybridSolarPanel(host);
    }

    @Override
    public int tier(ItemStack stack) {
        return Config.HYBRID_SOLAR_PANEL_TIER - 1;
    }
}

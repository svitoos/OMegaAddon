package oc.driver;

import li.cil.oc.api.network.EnvironmentHost;
import oc.Config;

public class DriverUltimateSolarPanel extends DriverAbstractAdvancedSolarPanel {

    public DriverUltimateSolarPanel(EnvironmentHost host) {
        super(host, Config.ULTIMATE_SOLAR_PANEL_CHARGE_TOOL, Config.ULTIMATE_SOLAR_PANEL_NIGHT_PER_TICK, Config.ULTIMATE_SOLAR_PANEL_DAY_PER_TICK);
    }
}

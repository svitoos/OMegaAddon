package oc.driver;

import li.cil.oc.api.network.EnvironmentHost;
import oc.Config;

public class DriverAdvancedSolarPanel extends DriverAbstractAdvancedSolarPanel {

    public DriverAdvancedSolarPanel(EnvironmentHost host) {
        super(host, Config.ADVANCED_SOLAR_PANEL_CHARGE_TOOL, Config.ADVANCED_SOLAR_PANEL_NIGHT_PER_TICK, Config.ADVANCED_SOLAR_PANEL_DAY_PER_TICK);
    }
}

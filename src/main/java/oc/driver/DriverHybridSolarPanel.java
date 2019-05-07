package oc.driver;

import li.cil.oc.api.network.EnvironmentHost;
import oc.Config;

public class DriverHybridSolarPanel extends DriverAbstractSolarPanel {

    public DriverHybridSolarPanel(EnvironmentHost host) {
        super(host, Config.HYBRID_SOLAR_PANEL_CHARGE_TOOL, Config.HYBRID_SOLAR_PANEL_NIGHT_PER_TICK, Config.HYBRID_SOLAR_PANEL_DAY_PER_TICK);
    }
}

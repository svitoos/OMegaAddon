package oc.driver;

import li.cil.oc.api.network.EnvironmentHost;
import oc.Config;

public class DriverQuantumSolarPanel extends DriverAbstractSolarPanel {

    public DriverQuantumSolarPanel(EnvironmentHost host) {
        super(host, Config.QUANTUM_SOLAR_PANEL_CHARGE_TOOL, Config.QUANTUM_SOLAR_PANEL_NIGHT_PER_TICK, Config.QUANTUM_SOLAR_PANEL_DAY_PER_TICK);
    }
}

package oc.driver;

import li.cil.oc.api.API;
import li.cil.oc.api.driver.Item;
import oc.item.Items;

public class Drivers {

    public static void init() {
        API.driver.add((Item) Items.advanced_geolyzer);
        API.driver.add((Item)Items.advanced_solar_panel);
        API.driver.add((Item)Items.hybrid_solar_panel);
        API.driver.add((Item)Items.ultimate_solar_panel);
        API.driver.add((Item)Items.quantum_solar_panel);
        API.driver.add((Item)Items.iron_inventory);
        API.driver.add((Item)Items.diamond_inventory);
    }

}

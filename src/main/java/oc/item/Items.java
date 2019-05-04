package oc.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Items {


    public static Item advanced_geolyzer;
    public static Item advanced_solar_panel;
    public static Item hybrid_solar_panel;
    public static Item ultimate_solar_panel;
    public static Item quantum_solar_panel;

    public static void init() {
        advanced_geolyzer = new UpgradeAdvancedGeolyzerItem();
        advanced_solar_panel = new UpgradeAdvancedSolarPanelItem();
        hybrid_solar_panel = new UpgradeHybridSolarPanelItem();
        ultimate_solar_panel = new UpgradeUltimateSolarPanelItem();
        quantum_solar_panel = new UpgradeQuantumSolarPanelItem();

        GameRegistry.registerItem(advanced_geolyzer, advanced_geolyzer.getUnlocalizedName());
        GameRegistry.registerItem(advanced_solar_panel, advanced_solar_panel.getUnlocalizedName());
        GameRegistry.registerItem(hybrid_solar_panel, hybrid_solar_panel.getUnlocalizedName());
        GameRegistry.registerItem(ultimate_solar_panel, ultimate_solar_panel.getUnlocalizedName());
        GameRegistry.registerItem(quantum_solar_panel, quantum_solar_panel.getUnlocalizedName());
    }

}

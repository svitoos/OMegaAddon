package oc;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config extends Configuration {

    public static int GEOLYZER_RADIUS;
    public static int GEOLYZER_SCAN_COST;
    public static int GEOLYZER_TIER;
    //
    public static int ADVANCED_SOLAR_PANEL_DAY_PER_TICK;
    public static int ADVANCED_SOLAR_PANEL_NIGHT_PER_TICK;
    public static int ADVANCED_SOLAR_PANEL_TIER;
    public static boolean ADVANCED_SOLAR_PANEL_CHARGE_TOOL;
    //
    public static int HYBRID_SOLAR_PANEL_DAY_PER_TICK;
    public static int HYBRID_SOLAR_PANEL_NIGHT_PER_TICK;
    public static int HYBRID_SOLAR_PANEL_TIER;
    public static boolean HYBRID_SOLAR_PANEL_CHARGE_TOOL;
    //
    public static int ULTIMATE_SOLAR_PANEL_DAY_PER_TICK;
    public static int ULTIMATE_SOLAR_PANEL_NIGHT_PER_TICK;
    public static int ULTIMATE_SOLAR_PANEL_TIER;
    public static boolean ULTIMATE_SOLAR_PANEL_CHARGE_TOOL;
    //
    public static int QUANTUM_SOLAR_PANEL_DAY_PER_TICK;
    public static int QUANTUM_SOLAR_PANEL_NIGHT_PER_TICK;
    public static int QUANTUM_SOLAR_PANEL_TIER;
    public static boolean QUANTUM_SOLAR_PANEL_CHARGE_TOOL;
    //
    public static int IRON_INVENTORY_TIER;
    //
    public static int DIAMOND_INVENTORY_TIER;

    public Config(File file) {
        super(file);
    }

    @Override
    public void load() {
        super.load();

        GEOLYZER_RADIUS = getInt("radius", "geolyzer", 15, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        GEOLYZER_SCAN_COST = getInt("cost", "geolyzer", 10, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        GEOLYZER_TIER = getInt("tier", "geolyzer", 3, 1, 3, "");
        //
        ADVANCED_SOLAR_PANEL_DAY_PER_TICK = getInt("day_per_tick", "advanced_solar_panel", 2, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        ADVANCED_SOLAR_PANEL_NIGHT_PER_TICK = getInt("night_per_tick", "advanced_solar_panel", 1, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        ADVANCED_SOLAR_PANEL_TIER = getInt("tier", "advanced_solar_panel", 2, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        ADVANCED_SOLAR_PANEL_CHARGE_TOOL = getBoolean("charge_tool", "advanced_solar_panel", false, "");
        //
        HYBRID_SOLAR_PANEL_DAY_PER_TICK = getInt("day_per_tick", "hybrid_solar_panel", 16, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        HYBRID_SOLAR_PANEL_NIGHT_PER_TICK = getInt("night_per_tick", "hybrid_solar_panel", 2, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        HYBRID_SOLAR_PANEL_TIER = getInt("tier", "hybrid_solar_panel", 3, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        HYBRID_SOLAR_PANEL_CHARGE_TOOL = getBoolean("charge_tool", "hybrid_solar_panel", false, "");
        //
        ULTIMATE_SOLAR_PANEL_DAY_PER_TICK = getInt("day_per_tick", "ultimate_solar_panel", 128, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        ULTIMATE_SOLAR_PANEL_NIGHT_PER_TICK = getInt("night_per_tick", "ultimate_solar_panel", 16, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        ULTIMATE_SOLAR_PANEL_TIER = getInt("tier", "ultimate_solar_panel", 3, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        ULTIMATE_SOLAR_PANEL_CHARGE_TOOL = getBoolean("charge_tool", "ultimate_solar_panel", true, "");
        //
        QUANTUM_SOLAR_PANEL_DAY_PER_TICK = getInt("day_per_tick", "quantum_solar_panel", 1024, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        QUANTUM_SOLAR_PANEL_NIGHT_PER_TICK = getInt("night_per_tick", "quantum_solar_panel", 512, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        QUANTUM_SOLAR_PANEL_TIER = getInt("tier", "quantum_solar_panel", 3, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
        QUANTUM_SOLAR_PANEL_CHARGE_TOOL = getBoolean("charge_tool", "quantum_solar_panel", true, "");
        //
        IRON_INVENTORY_TIER = getInt("tier", "iron_inventory", 2, 1, 3, "");
        //
        DIAMOND_INVENTORY_TIER= getInt("tier", "diamond_inventory", 3, 1, 3, "");
    }
}

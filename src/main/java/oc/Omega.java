package oc;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import oc.driver.Drivers;
import oc.item.Items;

@Mod(modid = Omega.MOD_ID, name = Omega.MOD_NAME, version = Omega.MOD_VERSION)
public class Omega {

    public static final String MOD_ID = "omega";
    public static final String MOD_NAME = "Omega";
    public static final String MOD_VERSION = "0.11.0";
    public static Config config;

    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        config = new Config(event.getSuggestedConfigurationFile());
        config.load();
        config.save();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Items.init();
        Drivers.init();
    }

}


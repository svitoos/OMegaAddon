package oc;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import oc.driver.Drivers;
import oc.gui.GuiHandler;
import oc.item.Items;

@Mod(modid = Omega.MOD_ID, name = Omega.MOD_NAME, version = Omega.MOD_VERSION)
public class Omega {

    public static final String MOD_ID = "omega";
    public static final String MOD_NAME = "OmegaAddon";
    public static final String MOD_VERSION = "@VERSION@";
    public static Config config;

    @Mod.Instance
    public static Omega instance;

    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        config = new Config(event.getSuggestedConfigurationFile());
        config.load();
        config.save();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        OCSettings.load();
        Items.init();
        Drivers.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
}


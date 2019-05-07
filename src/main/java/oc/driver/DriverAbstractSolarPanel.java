package oc.driver;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import li.cil.oc.api.Network;
import li.cil.oc.api.internal.Robot;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Connector;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenDesert;
import oc.OCSettings;

public class DriverAbstractSolarPanel extends ManagedEnvironment {

    private static final int checkPeriod = 100;

    private final EnvironmentHost host;
    private final boolean chargedTool;
    private boolean isChargeTool;
    private final int nightEnergyPerTick;
    private final int dayEnergyPerTick;
    private int ticksUntilCheck;
    private int energyPerTick;

    public DriverAbstractSolarPanel(EnvironmentHost host, boolean chargedTool, int night_energy_per_tick, int day_energy_per_tick) {
        this.setNode(Network.newNode(this, Visibility.Network).withComponent("solar_panel").withConnector().create());
        this.host = host;
        this.nightEnergyPerTick = night_energy_per_tick;
        this.dayEnergyPerTick = day_energy_per_tick;
        this.chargedTool = chargedTool && host instanceof Robot;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Callback(doc = "function(activate:boolean):boolean; Activate or deactivate tool charging. Returns whether the panel is currently charging tool.")
    public Object[] chargeTool(Context context, Arguments arguments) {
        if(chargedTool) {
            isChargeTool = arguments.checkBoolean(0);
        }
        return new Object[]{isChargeTool};
    }

    @Callback(doc = "function():number --  Returns the day energy output per tick.")
    public Object[] getDayEnergyPerTick(Context context, Arguments arguments) {
        return new Object[]{dayEnergyPerTick};
    }

    @Callback(doc = "function():number --  Returns the night energy output per tick.")
    public Object[] getNightEnergyPerTick(Context context, Arguments arguments) {
        return new Object[]{nightEnergyPerTick};
    }


    @Callback(doc = "function():boolean -- Returns whether the panel can charge tools.")
    public Object[] canChargeTool(Context context, Arguments arguments) {
        return new Object[]{chargedTool};
    }

    @Callback(doc = "function():boolean -- Returns whether the panel is currently generating energy.")
    public Object[] isActive(Context context, Arguments arguments) {
        return new Object[]{energyPerTick > 0};
    }

    @Callback(doc = "function():boolean -- Returns whether the panel is currently charging tool.")
    public Object[] isChargeTool(Context context, Arguments arguments) {
        return new Object[]{isChargeTool};
    }

    @Callback(doc = "function():number --  Returns the energy output per tick.")
    public Object[] getEnergyPerTick(Context context, Arguments arguments) {
        return new Object[]{energyPerTick};
    }

    private void updateOutputEnergy() {
        final int x = (int)Math.floor(host.xPosition());
        final int y = (int)Math.floor(host.yPosition()) + 1;
        final int z = (int)Math.floor(host.zPosition());
        final World world = host.world();
        final boolean canSeeSky = !world.provider.hasNoSky && world.canBlockSeeTheSky(x, y, z);
        final boolean isSunVisible = canSeeSky && world.isDaytime() &&
            (world.getWorldChunkManager().getBiomeGenAt(x, z) instanceof BiomeGenDesert
                || (!world.isRaining() && !world.isThundering()));
        if (canSeeSky) {
            energyPerTick = isSunVisible ? dayEnergyPerTick : nightEnergyPerTick;
        } else {
            energyPerTick = 0;
        }
    }

    @Override
    public void update() {
        ticksUntilCheck -= 1;
        if (ticksUntilCheck <= 0) {
            ticksUntilCheck = checkPeriod;
            updateOutputEnergy();
        }
        if (energyPerTick > 0 ) {
            if(!isChargeTool) {
                Connector connector = (Connector) node();
                connector.tryChangeBuffer(energyPerTick);
            } else {
                Robot robot = (Robot) host;
                IInventory inventory = robot.equipmentInventory();
                ItemStack tooltip = inventory.getStackInSlot(0);
                if(tooltip != null) {
                    if(tooltip.getItem() instanceof IElectricItem) {
                        ElectricItem.manager.charge(tooltip, toEu(energyPerTick), Integer.MAX_VALUE /*charge any tiers items*/, true, false);
                    }
                }
            }
        }
    }

    @Override
    public void load(NBTTagCompound nbt) {
        super.load(nbt);
        isChargeTool = nbt.getBoolean("isChargeTool") && chargedTool;
    }

    @Override
    public void save(NBTTagCompound nbt) {
        super.save(nbt);
        nbt.setBoolean("isChargeTool", isChargeTool);
    }

    static private double toEu(double value) {
        return value / OCSettings.ratioIndustrialCraft2;
    }
}

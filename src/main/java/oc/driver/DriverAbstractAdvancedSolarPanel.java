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

public class DriverAbstractAdvancedSolarPanel extends ManagedEnvironment {

    private EnvironmentHost host;
    private int night_energy_per_tick;
    private int day_energy_per_tick;
    private boolean charge_tool;
    private boolean is_charge_tool;

    public DriverAbstractAdvancedSolarPanel(EnvironmentHost host, boolean charge_tool, int night_energy_per_tick, int day_energy_per_tick) {
        this.setNode(Network.newNode(this, Visibility.Network).withComponent("solar_panel").withConnector().create());
        this.host = host;
        this.night_energy_per_tick = night_energy_per_tick;
        this.day_energy_per_tick = day_energy_per_tick;
        this.charge_tool = charge_tool;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Callback(doc = "function(activate:boolean):boolean; Activate tool charging")
    public Object[] chargeTool(Context context, Arguments arguments) {
        if(charge_tool) {
            is_charge_tool = arguments.checkBoolean(0);
            return new Object[]{true};
        } else {
            return new Object[]{false};
        }

    }

    @Override
    public void update() {
        World world = host.world();
        Connector connector = (Connector) node();
        if(world.getTopSolidOrLiquidBlock((int)Math.floor(host.xPosition()), (int)Math.floor(host.zPosition())) <= (int)Math.floor(host.yPosition() + 1)) {
            if(world.provider.isDaytime()) {
                if(!is_charge_tool)
                    connector.tryChangeBuffer(day_energy_per_tick);
                else {
                    Robot robot = (Robot) host;
                    IInventory inventory = robot.equipmentInventory();
                    ItemStack tooltip = inventory.getStackInSlot(0);
                    if(tooltip != null) {
                        if(tooltip.getItem() instanceof IElectricItem) {
                            ElectricItem.manager.charge(tooltip, day_energy_per_tick * 4, 300, true, false);
                        }
                    }
                }
            } else {
                if(!is_charge_tool)
                    connector.tryChangeBuffer(night_energy_per_tick);
                else {
                    Robot robot = (Robot) host;
                    IInventory inventory = robot.equipmentInventory();
                    ItemStack tooltip = inventory.getStackInSlot(0);
                    if(tooltip != null) {
                        if(tooltip.getItem() instanceof IElectricItem) {
                            ElectricItem.manager.charge(tooltip, night_energy_per_tick * 4, 3, true, true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void load(NBTTagCompound nbt) {
        super.load(nbt);
        is_charge_tool = nbt.getBoolean("is_charge_tool");
    }

    @Override
    public void save(NBTTagCompound nbt) {
        super.save(nbt);
        nbt.setBoolean("is_charge_tool", is_charge_tool);
    }
}

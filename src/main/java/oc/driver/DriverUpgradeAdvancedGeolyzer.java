package oc.driver;

import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.*;
import li.cil.oc.api.prefab.ManagedEnvironment;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import oc.Config;

import java.util.HashMap;

public class DriverUpgradeAdvancedGeolyzer extends ManagedEnvironment {

    private EnvironmentHost host;

    public DriverUpgradeAdvancedGeolyzer(EnvironmentHost host) {
        this.host = host;
        this.setNode(Network.newNode(this, Visibility.Network).withConnector().withComponent("advanced_geolyzer", Visibility.Neighbors).create());
    }

    @Callback(doc = "function(x:number, y:number, z:number):table; -- Get some information about the block relative to given coordinates.")
    public Object[] scan(Context context, Arguments arguments) {
        if(!((Connector)node()).tryChangeBuffer(-Config.GEOLYZER_SCAN_COST))
            return new Object[]{false, "not enough energy"};

        int x = (int) (arguments.checkInteger(0) + Math.floor(host.xPosition()));
        int y = (int) (arguments.checkInteger(1) + Math.floor(host.yPosition()));
        int z = (int) (arguments.checkInteger(2) + Math.floor(host.zPosition()));

        if(x < host.xPosition() + Config.GEOLYZER_RADIUS && x > host.xPosition() - Config.GEOLYZER_RADIUS
                && y < host.yPosition() + Config.GEOLYZER_RADIUS && y > host.yPosition() - Config.GEOLYZER_RADIUS
        && z < host.zPosition() + Config.GEOLYZER_RADIUS && z > host.zPosition() - Config.GEOLYZER_RADIUS) {
            Block block = host.world().getBlock(x, y, z);
            HashMap<Object, Object> returns = new HashMap<Object, Object>();
            World world = host.world();
            returns.put("name", Block.blockRegistry.getNameForObject(block));
            returns.put("hardness", block.getBlockHardness(world, x, y, z));
            returns.put("metadata", world.getBlockMetadata(x, y, z));
            returns.put("biome", world.getBiomeGenForCoords(x, z).biomeName);
            returns.put("light", world.getBlockLightValue(x, y, z));

            return new Object[]{returns};
        } else {
            return new Object[]{false, "location out of bounds"};
        }
    }

    @Override
    public Node node() {
        return super.node();
    }

    @Override
    public void onConnect(Node node) {

    }

    @Override
    public void onDisconnect(Node node) {

    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void load(NBTTagCompound nbt) {
        super.load(nbt);
    }

    @Override
    public void save(NBTTagCompound nbt) {
        super.save(nbt);
    }
}

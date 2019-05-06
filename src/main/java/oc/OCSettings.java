package oc;

import li.cil.oc.api.API;

public final class OCSettings {
  static void load() {
    ratioIndustrialCraft2 = API.config.getDouble("power.value.IndustrialCraft2") / 1000;
  }
  public static double ratioIndustrialCraft2;
}

package mods.SufficientlyPositive.GoldToolsPlus.client;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;

@Environment(EnvType.CLIENT)
public class GoldToolsPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenInit.init();

        GoldToolsPlusHelperFunctions.log(Level.INFO, "Client finished initialising.");
    }
}

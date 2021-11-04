package mods.SufficientlyPositive.GoldToolsPlus.client;

import mods.SufficientlyPositive.GoldToolsPlus.client.screens.InfuserScreen;
import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.init.ScreenHandlerInit;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import org.apache.logging.log4j.Level;

public class ScreenInit {

    public static void init() {
        ScreenRegistry.register(ScreenHandlerInit.INFUSER_SCREEN_HANDLER, InfuserScreen::new);

        GoldToolsPlusHelperFunctions.log(Level.INFO, "Screens registered.");
    }
}

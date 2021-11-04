package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.game.blocks.screenhandlers.InfuserScreenHandler;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;


public class ScreenHandlerInit {

    public static final ScreenHandlerType<InfuserScreenHandler> INFUSER_SCREEN_HANDLER;

    static {
        INFUSER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(GoldToolsPlus.MOD_ID, "infuser"), InfuserScreenHandler::new);
    }

    public static void init() {
        GoldToolsPlusHelperFunctions.log(Level.INFO, "Screen Handlers registered");
    }
}
package mods.SufficientlyPositive.GoldToolsPlus.functions;

import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;

public class GoldToolsPlusHelperFunctions {

    /**
     * Logs a formatted message in the console at the specified level.
     *
     * @param level what level to display the message, standards are Level.INFO and Level.DEBUG.
     * @param message string message to display.
     */
    public static void log(Level level, String message){
        GoldToolsPlus.LOGGER.log(level, "["+GoldToolsPlus.MOD_NAME+"] " + message);
    }

    /**
     * returns a new identifier registered under the mod id
     *
     * @param path replaces the path variable in the identifier constructor
     * @return a new identifier tied to GoldToolsPlus
     */
    public static Identifier newID(String path) {
        return new Identifier(GoldToolsPlus.MOD_ID, path);
    }
}

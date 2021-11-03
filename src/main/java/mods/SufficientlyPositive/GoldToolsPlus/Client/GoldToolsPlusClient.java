package mods.SufficientlyPositive.GoldToolsPlus.Client;

import mods.SufficientlyPositive.GoldToolsPlus.Client.Screens.InfuserScreen;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPInfuserInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class GoldToolsPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(GTPInfuserInit.INFUSER_SCREEN_HANDLER, InfuserScreen::new);
    }
}

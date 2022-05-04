package mods.SufficientlyPositive.GoldToolsPlus.game.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

/**
 * Custom axe item for getting around protected value of the AxeItem constructor.
 */
public class CustomAxeItem extends AxeItem {
    public CustomAxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}

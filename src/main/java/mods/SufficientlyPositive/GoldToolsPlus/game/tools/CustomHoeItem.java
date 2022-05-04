package mods.SufficientlyPositive.GoldToolsPlus.game.tools;


import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

/**
 * Custom axe item for getting around protected value of the HoeItem constructor.
 */
public class CustomHoeItem extends HoeItem {
    public CustomHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}

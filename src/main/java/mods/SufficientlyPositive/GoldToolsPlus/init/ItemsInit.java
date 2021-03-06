package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.game.materials.WhiteGoldArmourMaterial;
import mods.SufficientlyPositive.GoldToolsPlus.game.tools.CustomAxeItem;
import mods.SufficientlyPositive.GoldToolsPlus.game.tools.CustomHoeItem;
import mods.SufficientlyPositive.GoldToolsPlus.game.tools.CustomPickaxeItem;
import mods.SufficientlyPositive.GoldToolsPlus.game.materials.WhiteGoldToolMaterial;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

/**
 * Registers all items in the mod to Minecraft's item and block registries.
 */
public class ItemsInit {

    /**
     * Shorthand function for registering mod items.
     * @param path the name of the item when it's referred to inside the registries (e.g. will be goldtoolsplus:path)
     * @param instance a static instance of the item to refer to, should be contained inside this class also.
     */
    private static Item registerItem(String path, Item instance) {
        return Registry.register(Registry.ITEM, GoldToolsPlusHelperFunctions.newID(path), instance);
    }

    /**
     * Shorthand function for registering mod items.
     * @param path the name of the block when it's referred to inside the registries (e.g. will be goldtoolsplus:path)
     * @param instance a static instance of the block to refer to, should be contained inside this class also.
     */
    private static Block registerBlock(String path, Block instance) {
        return Registry.register(Registry.BLOCK, GoldToolsPlusHelperFunctions.newID(path), instance);
    }

    /**
     * Shorthand function for creating a directionally placed metal block
     * @param mapColor colour of block as it appears on the map
     * @param hardness hardness of the block (affects mining speed)
     * @param resistance resistance of the block (affects explosion resistance)
     * @return new instance of the blcok, don't forget to edit the .json file to take this into account.
     */
    private static PillarBlock createDirectionalMetalBlock(MapColor mapColor, float hardness, float resistance) {
        return new PillarBlock(FabricBlockSettings.of(Material.METAL, (state) -> mapColor).strength(hardness, resistance).requiresTool());
    }

    // Static block instances
    public static final Block WHITE_GOLD_BLOCK;

    // Static blockitem instances
    public static final Item WHITE_GOLD_BLOCK_ITEM;

    // Static item instances
    public static final Item WHITE_GOLD_SHARD = new Item(new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final Item WHITE_GOLD_INGOT = new Item(new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // Static tool instances
    public static final ToolItem WHITE_GOLD_SWORD = new SwordItem(WhiteGoldToolMaterial.INSTANCE, 3, -2.4F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_PICKAXE = new CustomPickaxeItem(WhiteGoldToolMaterial.INSTANCE, 1, -2.8F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_AXE = new CustomAxeItem(WhiteGoldToolMaterial.INSTANCE, 6, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_SHOVEL = new ShovelItem(WhiteGoldToolMaterial.INSTANCE, 1.5F, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_HOE = new CustomHoeItem(WhiteGoldToolMaterial.INSTANCE, 0, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // Static armour instances
    public static final ArmorItem WHITE_GOLD_HELMET = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.HEAD, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem WHITE_GOLD_CHESTPLATE = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.CHEST, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem WHITE_GOLD_LEGGINGS = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.LEGS, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem WHITE_GOLD_BOOTS = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.FEET, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    /**
     * Function runs on game startup, acts as a call to activate the static block of the class
     */
    public static void init() {}

    static {
        WHITE_GOLD_BLOCK = registerBlock("white_gold_block", createDirectionalMetalBlock(MapColor.PALE_YELLOW, 5, 9));
        WHITE_GOLD_BLOCK_ITEM = registerItem("white_gold_block", new BlockItem(WHITE_GOLD_BLOCK, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof()));

        // register all generic items
        registerItem("white_gold_ingot", WHITE_GOLD_INGOT);
        registerItem("white_gold_shard", WHITE_GOLD_SHARD);


        registerItem("white_gold_sword", WHITE_GOLD_SWORD);
        registerItem("white_gold_shovel", WHITE_GOLD_SHOVEL);
        registerItem("white_gold_pickaxe", WHITE_GOLD_PICKAXE);
        registerItem("white_gold_axe", WHITE_GOLD_AXE);
        registerItem("white_gold_hoe", WHITE_GOLD_HOE);

        registerItem("white_gold_helmet", WHITE_GOLD_HELMET);
        registerItem("white_gold_chestplate", WHITE_GOLD_CHESTPLATE);
        registerItem("white_gold_leggings", WHITE_GOLD_LEGGINGS);
        registerItem("white_gold_boots", WHITE_GOLD_BOOTS);
    }
}

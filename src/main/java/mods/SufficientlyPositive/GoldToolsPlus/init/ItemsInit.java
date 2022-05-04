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
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class ItemsInit {

    private static void registerItem(String path, Item instance) {
        Registry.register(Registry.ITEM, GoldToolsPlusHelperFunctions.newID(path), instance);
    }

    private static void registerBlock(String path, Block instance) {
        Registry.register(Registry.BLOCK, GoldToolsPlusHelperFunctions.newID(path), instance);
    }

    // blocks
    public static final Block WHITE_GOLD_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5, 9).requiresTool());

    // blockitems
    public static final BlockItem WHITE_GOLD_BLOCK_ITEM = new BlockItem(WHITE_GOLD_BLOCK, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // items
    public static final Item WHITE_GOLD_SHARD = new Item(new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final Item WHITE_GOLD_INGOT = new Item(new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // tools
    public static final ToolItem WHITE_GOLD_SWORD = new SwordItem(WhiteGoldToolMaterial.INSTANCE, 3, -2.4F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_PICKAXE = new CustomPickaxeItem(WhiteGoldToolMaterial.INSTANCE, 1, -2.8F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_AXE = new CustomAxeItem(WhiteGoldToolMaterial.INSTANCE, 6, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_SHOVEL = new ShovelItem(WhiteGoldToolMaterial.INSTANCE, 1.5F, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem WHITE_GOLD_HOE = new CustomHoeItem(WhiteGoldToolMaterial.INSTANCE, 0, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // armour
    public static final ArmorItem WHITE_GOLD_HELMET = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.HEAD, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem WHITE_GOLD_CHESTPLATE = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.CHEST, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem WHITE_GOLD_LEGGINGS = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.LEGS, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem WHITE_GOLD_BOOTS = new ArmorItem(WhiteGoldArmourMaterial.INSTANCE, EquipmentSlot.FEET, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());



    public static void init() {
        registerBlock("white_gold_block", WHITE_GOLD_BLOCK);

        // register all generic items
        registerItem("white_gold_ingot", WHITE_GOLD_INGOT);
        registerItem("white_gold_shard", WHITE_GOLD_SHARD);
        registerItem("white_gold_block", WHITE_GOLD_BLOCK_ITEM);

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

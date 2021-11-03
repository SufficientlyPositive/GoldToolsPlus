package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.CustomBlocks.InfuserBlock;
import mods.SufficientlyPositive.GoldToolsPlus.Equipment.MaterialClasses.VenerableGoldArmourMaterial;
import mods.SufficientlyPositive.GoldToolsPlus.Equipment.ToolClasses.CustomAxeItem;
import mods.SufficientlyPositive.GoldToolsPlus.Equipment.ToolClasses.CustomHoeItem;
import mods.SufficientlyPositive.GoldToolsPlus.Equipment.ToolClasses.CustomPickaxeItem;
import mods.SufficientlyPositive.GoldToolsPlus.Equipment.MaterialClasses.VenerableGoldToolMaterial;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GTPItemsInit {

    private static void registerItem(String path, Item instance) {
        Registry.register(Registry.ITEM, new Identifier(GoldToolsPlus.MOD_ID, path), instance);
    }

    private static void registerBlock(String path, Block instance) {
        Registry.register(Registry.BLOCK, new Identifier(GoldToolsPlus.MOD_ID, path), instance);
    }

    // custom identifiers
    public static final Identifier INFUSER_IDENTIFIER = new Identifier(GoldToolsPlus.MOD_ID, "infuser");

    // blocks
    public static final Block VENERABLE_GOLD_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5, 9).requiresTool());
    public static final Block INFUSER_BLOCK = new InfuserBlock(FabricBlockSettings.of(Material.METAL).strength(5, 100).requiresTool());

    // blockitems
    public static final BlockItem VENERABLE_GOLD_BLOCK_ITEM = new BlockItem(VENERABLE_GOLD_BLOCK, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final BlockItem INFUSER_BLOCK_ITEM = new BlockItem(INFUSER_BLOCK, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP));

    // items
    public static final Item VENERABLE_GOLD_SHARD = new Item(new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final Item VENERABLE_GOLD_INGOT = new Item(new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // tools
    public static final ToolItem VENERABLE_GOLD_SWORD = new SwordItem(VenerableGoldToolMaterial.INSTANCE, 3, -2.4F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem VENERABLE_GOLD_PICKAXE = new CustomPickaxeItem(VenerableGoldToolMaterial.INSTANCE, 1, -2.8F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem VENERABLE_GOLD_AXE = new CustomAxeItem(VenerableGoldToolMaterial.INSTANCE, 6, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem VENERABLE_GOLD_SHOVEL = new ShovelItem(VenerableGoldToolMaterial.INSTANCE, 1.5F, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ToolItem VENERABLE_GOLD_HOE = new CustomHoeItem(VenerableGoldToolMaterial.INSTANCE, 0, -3.0F, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());

    // armour
    public static final ArmorItem VENERABLE_GOLD_HELMET = new ArmorItem(VenerableGoldArmourMaterial.INSTANCE, EquipmentSlot.HEAD, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem VENERABLE_GOLD_CHESTPLATE = new ArmorItem(VenerableGoldArmourMaterial.INSTANCE, EquipmentSlot.CHEST, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem VENERABLE_GOLD_LEGGINGS = new ArmorItem(VenerableGoldArmourMaterial.INSTANCE, EquipmentSlot.LEGS, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());
    public static final ArmorItem VENERABLE_GOLD_BOOTS = new ArmorItem(VenerableGoldArmourMaterial.INSTANCE, EquipmentSlot.FEET, new FabricItemSettings().group(GoldToolsPlus.GTP_ITEM_GROUP).fireproof());



    public static void init() {
        registerBlock("venerable-gold-block", VENERABLE_GOLD_BLOCK);
        Registry.register(Registry.BLOCK, INFUSER_IDENTIFIER, INFUSER_BLOCK);

        // register all generic items
        registerItem("venerable-gold-ingot", VENERABLE_GOLD_INGOT);
        registerItem("venerable-gold-shard", VENERABLE_GOLD_SHARD);
        registerItem("venerable-gold-block", VENERABLE_GOLD_BLOCK_ITEM);
        registerItem("infuser", INFUSER_BLOCK_ITEM);

        registerItem("venerable-gold-sword", VENERABLE_GOLD_SWORD);
        registerItem("venerable-gold-shovel", VENERABLE_GOLD_SHOVEL);
        registerItem("venerable-gold-pickaxe", VENERABLE_GOLD_PICKAXE);
        registerItem("venerable-gold-axe", VENERABLE_GOLD_AXE);
        registerItem("venerable-gold-hoe", VENERABLE_GOLD_HOE);

        registerItem("venerable-gold-helmet", VENERABLE_GOLD_HELMET);
        registerItem("venerable-gold-chestplate", VENERABLE_GOLD_CHESTPLATE);
        registerItem("venerable-gold-leggings", VENERABLE_GOLD_LEGGINGS);
        registerItem("venerable-gold-boots", VENERABLE_GOLD_BOOTS);
    }
}

package mods.SufficientlyPositive.GoldToolsPlus.CustomBlocks;

import mods.SufficientlyPositive.GoldToolsPlus.CustomRecipies.InfuserRecipe;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPInfuserInit;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPItemsInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;

public class InfuserScreenHandler extends ScreenHandler {

    private final CraftingInventory input;
    private final CraftingResultInventory result;
    private final ScreenHandlerContext context;
    private final PlayerEntity player;

    public InfuserScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public InfuserScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(GTPInfuserInit.INFUSER_SCREEN_HANDLER, syncId);
        this.input = new CraftingInventory(this, 5 ,1);
        this.result = new CraftingResultInventory();
        this.context = context;
        this.player = playerInventory.player;

        // each slot is 16x16
        this.addSlot(new InfusingResultSlot(this.player, this.input, this.result, 0, 80, 34));

        this.addSlot(new Slot(this.input, 0, 42, 13));
        this.addSlot(new Slot(this.input, 1, 118, 13));
        this.addSlot(new Slot(this.input, 2, 29,41));
        this.addSlot(new Slot(this.input, 3, 131, 41));
        this.addSlot(new Slot(this.input, 4, 80, 61));

        int m;
        int l;
        // player inventory
        for(m = 0; m < 3; ++m) {
            for(l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        // player hotbar
        for(m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, GTPItemsInit.INFUSER_BLOCK);
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 0) {
                // does nothing by default (onCraft does nothing)
                this.context.run((world, pos) -> {
                    itemStack2.getItem().onCraft(itemStack2, world, player);
                });
                if (!this.insertItem(itemStack2, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }


                slot.onQuickTransfer(itemStack2, itemStack);

                // shift clicking into infuser
            } else if (index >= 6 && index < 42) {
                if (!this.insertItem(itemStack2, 1, 6, false)) {
                    if (index < 33) {
                        if (!this.insertItem(itemStack2, 33, 42, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(itemStack2, 6, 33, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(itemStack2, 6, 42, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
            if (index == 0) {
                player.dropItem(itemStack2, false);
            }
        }

        return itemStack;
    }

    // updates result of crafting inventory
    private void updateResult(InfuserScreenHandler infuserScreenHandler, World world, PlayerEntity player, CraftingInventory input, CraftingResultInventory result) {
        if(!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<InfuserRecipe> match = world.getRecipeManager().getFirstMatch(GTPInfuserInit.INFUSER_RECIPE_TYPE, input, world);

            if(match.isPresent()) {
                InfuserRecipe recipe = match.get();
                if(result.shouldCraftRecipe(world, serverPlayerEntity, recipe)) {
                    itemStack = recipe.craft(input);
                }
            }

            result.setStack(0, itemStack);
            infuserScreenHandler.setPreviousTrackedSlot(0, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(infuserScreenHandler.syncId, infuserScreenHandler.nextRevision(), 0, itemStack));
        }
    }

    // runs whenever inventory changed? - doesn't seem to include on craft lol
    public void onContentChanged(Inventory inventory) {
        this.context.run((world, pos) -> {
            updateResult(this, world, this.player, this.input, this.result);
        });
    }

    // runs on crafting inventory closing
    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, pos) -> {
            this.dropInventory(player, this.input);
        });
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot);
    }
}

package net.robin.toymod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public class Custom8x1ScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public Custom8x1ScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenHandlerType.GENERIC_9X1, syncId); // You might need a custom ScreenHandlerType for 8x1
        checkSize(inventory, 8); // Ensure the inventory size is exactly 8 slots
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        // Add the 8 custom inventory slots
        for (int i = 0; i < 8; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 18));
        }

        // Add player inventory slots (3 rows of 9 slots)
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 9; k++) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18));
            }
        }

        // Add player hotbar slots (1 row of 9 slots)
        for (int j = 0; j < 9; j++) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();

            // Determine if the slot is in the container (0-7) or in the player inventory/hotbar (8-43)
            if (index < 8) {  // The clicked slot is in the custom container inventory (8 slots)
                if (!this.insertItem(itemStackInSlot, 8, 44, true)) {
                    return ItemStack.EMPTY;
                }
            } else {  // The clicked slot is in the player inventory (36 slots: 9 hotbar + 27 inventory)
                if (!this.insertItem(itemStackInSlot, 0, 8, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStackInSlot.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }
}

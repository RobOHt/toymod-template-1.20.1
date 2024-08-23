package net.robin.toymod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class CustomInventoryScreenHandler implements NamedScreenHandlerFactory {
    private final SimpleInventory inventory = new SimpleInventory(27);  // A 3x9 inventory

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.custom_chest");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, inventory);
    }

    public void onClose(PlayerEntity player) {
        inventory.clear();  // Clear inventory when closed to simulate "leaking"
    }
}
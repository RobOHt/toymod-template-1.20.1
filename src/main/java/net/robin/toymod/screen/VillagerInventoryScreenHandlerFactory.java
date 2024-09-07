package net.robin.toymod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;

public class VillagerInventoryScreenHandlerFactory implements NamedScreenHandlerFactory {
    private final SimpleInventory inventory;

    public VillagerInventoryScreenHandlerFactory(SimpleInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Villager's Inventory");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // Use the existing constructor that accepts a custom inventory for a 9x1 container, similar to createGeneric9x3 in source code.
        return new GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X1, syncId, playerInventory, inventory, 1);
    }

}

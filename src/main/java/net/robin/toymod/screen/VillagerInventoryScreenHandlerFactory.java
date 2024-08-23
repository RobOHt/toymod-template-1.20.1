package net.robin.toymod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.robin.toymod.screen.Custom8x1ScreenHandler;

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
        return new Custom8x1ScreenHandler(syncId, playerInventory, inventory);
    }
}

package net.robin.toymod.mixin;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.inventory.SimpleInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MerchantEntity.class)
public class MerchantEntityMixin {

	// Redirect the initialization of the inventory to use 9 slots instead of 8
	@Redirect(method = "<init>",
			at = @At(value = "NEW", target = "net/minecraft/inventory/SimpleInventory", ordinal = 0))
	private SimpleInventory redirectInventoryInit(int size) {
		// Return a new SimpleInventory with 9 slots instead of 8
		return new SimpleInventory(9);
	}
}

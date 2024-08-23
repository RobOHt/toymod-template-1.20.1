package net.robin.toymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.text.Text;
import net.robin.toymod.screen.VillagerInventoryScreenHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToyMod implements ModInitializer {
	public static final String MOD_ID = "toymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			VillagerEntity villager = (VillagerEntity) entity;  // Properly cast the entity to VillagerEntity

			player.sendMessage(Text.literal("Opening villager inventory"), true);

			// Access the villager's internal inventory directly
			SimpleInventory inventory = villager.getInventory();

			// Open the inventory for the player
			player.openHandledScreen(new VillagerInventoryScreenHandlerFactory(inventory));

			return ActionResult.SUCCESS;  // Indicate that the interaction was successful
		});
	}
}

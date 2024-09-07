package net.robin.toymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.util.ActionResult;
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

			// Check if the entity is a villager
			if (entity instanceof VillagerEntity villager) {

				// Check if player is sneaking and not in spectator mode
				if (player.isSneaking() && !player.isSpectator()) {
					player.sendMessage(Text.literal("Opening villager inventory"), true);
					SimpleInventory inventory = villager.getInventory();
					player.openHandledScreen(new VillagerInventoryScreenHandlerFactory(inventory));
					return ActionResult.SUCCESS;  // Block future interactions, i.e. the trade screen from popping up.
				}
			}

			return ActionResult.PASS;  // Pass the event if the conditions are not met
		});
	}
}

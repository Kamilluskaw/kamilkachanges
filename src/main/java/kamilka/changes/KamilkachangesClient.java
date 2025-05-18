package kamilka.changes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;

public class KamilkachangesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
// First, allow breaking with any tool
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> true);

        // Then cancel the drops if not using golden pickaxe
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            Block block = state.getBlock();

            if (block == Blocks.DIAMOND_ORE || block == Blocks.EMERALD_ORE ||
                    block == Blocks.DEEPSLATE_DIAMOND_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE) {

                if (!player.getMainHandStack().isOf(Items.GOLDEN_PICKAXE)) {
                    // Clear all drops
                    world.breakBlock(pos, false); // false = no drops
                }
            }
        });
    }
}

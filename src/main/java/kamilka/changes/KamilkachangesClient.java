package kamilka.changes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class KamilkachangesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            Block block = state.getBlock();

            if (block == Blocks.DIAMOND_ORE || block == Blocks.EMERALD_ORE ||
                    block == Blocks.DEEPSLATE_DIAMOND_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE) {

                if (!player.getMainHandStack().isOf(Items.GOLDEN_PICKAXE)) {
                    player.sendMessage(Text.literal("§cGolden pickaxe required!"), false);
                    return false;
                }
            }
            return true;
        });
    }
}

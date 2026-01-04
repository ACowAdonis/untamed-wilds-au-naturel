package untamedwilds.init;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "untamedwilds",
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class BlockColor {
   @SubscribeEvent
   public static void registerBlockColors(Block event) {
      net.minecraft.client.color.block.BlockColor grassColor = (state, worldIn, pos, tintIndex) -> worldIn != null && pos != null
            ? BiomeColors.getAverageGrassColor(worldIn, pos)
            : GrassColor.get(0.5, 1.0);
      event.register(grassColor, new net.minecraft.world.level.block.Block[]{(net.minecraft.world.level.block.Block)ModBlock.YARROW.get()});
      event.register(grassColor, new net.minecraft.world.level.block.Block[]{(net.minecraft.world.level.block.Block)ModBlock.JUNEGRASS.get()});
      event.register(grassColor, new net.minecraft.world.level.block.Block[]{(net.minecraft.world.level.block.Block)ModBlock.CANOLA.get()});
   }
}

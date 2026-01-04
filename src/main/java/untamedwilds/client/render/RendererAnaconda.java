package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelAnaconda;
import untamedwilds.entity.reptile.EntityAnaconda;

public class RendererAnaconda extends MobRenderer<EntityAnaconda, EntityModel<EntityAnaconda>> {
   private static final ModelAnaconda SNAKE_MODEL = new ModelAnaconda();

   public RendererAnaconda(Context renderManager) {
      super(renderManager, SNAKE_MODEL, 0.0F);
   }

   protected void scale(EntityAnaconda entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityAnaconda entity) {
      return entity.getTexture();
   }
}

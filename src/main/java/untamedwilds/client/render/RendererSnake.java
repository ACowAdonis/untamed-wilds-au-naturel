package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelSnake;
import untamedwilds.entity.reptile.EntitySnake;

public class RendererSnake extends MobRenderer<EntitySnake, EntityModel<EntitySnake>> {
   private static final ModelSnake SNAKE_MODEL = new ModelSnake();

   public RendererSnake(Context renderManager) {
      super(renderManager, SNAKE_MODEL, 0.0F);
   }

   protected void scale(EntitySnake entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntitySnake entity) {
      return entity.getTexture();
   }
}

package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelButterfly;
import untamedwilds.entity.arthropod.EntityButterfly;

public class RendererButterfly extends MobRenderer<EntityButterfly, ModelButterfly> {
   private static final ModelButterfly BUTTERFLY_MODEL = new ModelButterfly();

   public RendererButterfly(Context renderManager) {
      super(renderManager, BUTTERFLY_MODEL, 0.2F);
   }

   protected void scale(EntityButterfly entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize() * 0.8F;
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = 0.0F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityButterfly entity) {
      return entity.getTexture();
   }
}

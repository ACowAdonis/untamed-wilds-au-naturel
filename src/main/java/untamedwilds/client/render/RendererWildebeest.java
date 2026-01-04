package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelWildebeest;
import untamedwilds.client.model.ModelWildebeestCalf;
import untamedwilds.entity.mammal.EntityWildebeest;

public class RendererWildebeest extends MobRenderer<EntityWildebeest, EntityModel<EntityWildebeest>> {
   private static final ModelWildebeest WILDEBEEST_MODEL = new ModelWildebeest();
   private static final ModelWildebeestCalf WILDEBEEST_MODEL_CALF = new ModelWildebeestCalf();

   public RendererWildebeest(Context renderManager) {
      super(renderManager, WILDEBEEST_MODEL, 0.4F);
   }

   public void render(EntityWildebeest entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      if (entityIn.isBaby()) {
         this.model = WILDEBEEST_MODEL_CALF;
      } else {
         this.model = WILDEBEEST_MODEL;
      }

      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntityWildebeest entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.6F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityWildebeest entity) {
      return entity.getTexture();
   }
}

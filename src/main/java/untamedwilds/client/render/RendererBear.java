package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelBear;
import untamedwilds.client.model.ModelBearCub;
import untamedwilds.entity.mammal.EntityBear;

public class RendererBear extends MobRenderer<EntityBear, EntityModel<EntityBear>> {
   private static final ModelBear BEAR_MODEL = new ModelBear();
   private static final ModelBearCub BEAR_MODEL_CUB = new ModelBearCub();

   public RendererBear(Context renderManager) {
      super(renderManager, BEAR_MODEL, 1.0F);
   }

   public void render(EntityBear entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      if (entityIn.isBaby()) {
         this.model = BEAR_MODEL_CUB;
      } else {
         this.model = BEAR_MODEL;
      }

      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntityBear entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityBear entity) {
      return entity.getTexture();
   }
}

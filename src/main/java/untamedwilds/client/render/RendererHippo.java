package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelHippo;
import untamedwilds.client.model.ModelHippoCalf;
import untamedwilds.entity.mammal.EntityHippo;

public class RendererHippo extends MobRenderer<EntityHippo, EntityModel<EntityHippo>> {
   private static final ModelHippo HIPPO_MODEL = new ModelHippo();
   private static final ModelHippoCalf HIPPO_MODEL_CALF = new ModelHippoCalf();

   public RendererHippo(Context renderManager) {
      super(renderManager, HIPPO_MODEL, 1.0F);
   }

   public void render(EntityHippo entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      if (entityIn.isBaby()) {
         this.model = HIPPO_MODEL_CALF;
      } else {
         this.model = HIPPO_MODEL;
      }

      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntityHippo entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityHippo entity) {
      return entity.getTexture();
   }
}

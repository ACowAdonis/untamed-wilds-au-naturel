package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelBigCat;
import untamedwilds.client.model.ModelBigCatCub;
import untamedwilds.entity.mammal.EntityBigCat;

public class RendererBigCat extends MobRenderer<EntityBigCat, EntityModel<EntityBigCat>> {
   private static final ModelBigCat BIG_CAT_MODEL = new ModelBigCat();
   private static final ModelBigCatCub BIG_CAT_MODEL_CUB = new ModelBigCatCub();

   public RendererBigCat(Context renderManager) {
      super(renderManager, BIG_CAT_MODEL, 1.0F);
   }

   public void render(EntityBigCat entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      if (entityIn.isBaby()) {
         this.model = BIG_CAT_MODEL_CUB;
      } else {
         this.model = BIG_CAT_MODEL;
      }

      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntityBigCat entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityBigCat entity) {
      return entity.getTexture();
   }
}

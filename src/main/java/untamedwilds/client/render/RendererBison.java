package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelBison;
import untamedwilds.client.model.ModelBisonCalf;
import untamedwilds.entity.mammal.EntityBison;

public class RendererBison extends MobRenderer<EntityBison, EntityModel<EntityBison>> {
   private static final ModelBison BISON_MODEL = new ModelBison();
   private static final ModelBisonCalf BISON_CALF_MODEL = new ModelBisonCalf();

   public RendererBison(Context renderManager) {
      super(renderManager, BISON_MODEL, 1.0F);
   }

   public void render(EntityBison entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      this.model = (EntityModel)(!entityIn.isBaby() ? BISON_MODEL : BISON_CALF_MODEL);
      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntityBison entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.6F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityBison entity) {
      return entity.getTexture();
   }
}

package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelOpossum;
import untamedwilds.entity.mammal.EntityOpossum;

public class RendererOpossum extends MobRenderer<EntityOpossum, EntityModel<EntityOpossum>> {
   private static final ModelOpossum OPOSSUM_MODEL = new ModelOpossum();

   public RendererOpossum(Context renderManager) {
      super(renderManager, OPOSSUM_MODEL, 0.4F);
   }

   public void render(EntityOpossum entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      this.model = OPOSSUM_MODEL;
      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntityOpossum entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.4F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityOpossum entity) {
      return entity.getTexture();
   }
}

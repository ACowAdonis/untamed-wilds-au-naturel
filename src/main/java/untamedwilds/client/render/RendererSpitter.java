package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.MonsterSpitter;
import untamedwilds.client.model.MonsterSpitterLarva;
import untamedwilds.entity.relict.EntitySpitter;

public class RendererSpitter extends MobRenderer<EntitySpitter, EntityModel<EntitySpitter>> {
   private static final MonsterSpitter SPITTER_MODEL = new MonsterSpitter();
   private static final MonsterSpitterLarva SPITTER_MODEL_LARVA = new MonsterSpitterLarva();

   public RendererSpitter(Context renderManager) {
      super(renderManager, SPITTER_MODEL, 1.0F);
   }

   public void render(EntitySpitter entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      if (entityIn.isBaby()) {
         this.model = SPITTER_MODEL_LARVA;
      } else {
         this.model = SPITTER_MODEL;
      }

      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected void scale(EntitySpitter entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntitySpitter entity) {
      return entity.getTexture();
   }
}

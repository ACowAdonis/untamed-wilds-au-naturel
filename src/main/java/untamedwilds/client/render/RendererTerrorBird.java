package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelTerrorBird;
import untamedwilds.entity.bird.EntityTerrorBird;

public class RendererTerrorBird extends MobRenderer<EntityTerrorBird, EntityModel<EntityTerrorBird>> {
   private static final ModelTerrorBird TERROR_BIRD_MODEL = new ModelTerrorBird();

   public RendererTerrorBird(Context renderManager) {
      super(renderManager, TERROR_BIRD_MODEL, 1.0F);
   }

   protected void scale(EntityTerrorBird entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.7F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityTerrorBird entity) {
      return entity.getTexture();
   }
}

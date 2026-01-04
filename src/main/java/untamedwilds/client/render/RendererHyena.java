package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelHyena;
import untamedwilds.entity.mammal.EntityHyena;

public class RendererHyena extends MobRenderer<EntityHyena, EntityModel<EntityHyena>> {
   private static final ModelHyena HYENA_MODEL = new ModelHyena();

   public RendererHyena(Context renderManager) {
      super(renderManager, HYENA_MODEL, 1.0F);
   }

   protected void scale(EntityHyena entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.6F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityHyena entity) {
      return entity.getTexture();
   }
}

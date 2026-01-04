package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelTarantula;
import untamedwilds.entity.arthropod.EntityTarantula;

public class RendererTarantula extends MobRenderer<EntityTarantula, ModelTarantula> {
   private static final ModelTarantula TARANTULA_MODEL = new ModelTarantula();

   public RendererTarantula(Context renderManager) {
      super(renderManager, TARANTULA_MODEL, 0.2F);
   }

   protected void scale(EntityTarantula entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize() * 0.7F;
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.6F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityTarantula entity) {
      return entity.getTexture();
   }
}

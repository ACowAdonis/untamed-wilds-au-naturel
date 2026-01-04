package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelArowana;
import untamedwilds.entity.fish.EntityArowana;

public class RendererArowana extends MobRenderer<EntityArowana, EntityModel<EntityArowana>> {
   private static final ModelArowana AROWANA_MODEL = new ModelArowana();

   public RendererArowana(Context rendermanager) {
      super(rendermanager, AROWANA_MODEL, 0.2F);
   }

   protected void scale(EntityArowana entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize() * 0.8F;
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f * 0.5F;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityArowana entity) {
      return entity.getTexture();
   }
}

package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelSawfish;
import untamedwilds.entity.fish.EntitySawfish;

public class RendererSawfish extends MobRenderer<EntitySawfish, EntityModel<EntitySawfish>> {
   private static final ModelSawfish SAWFISH_MODEL = new ModelSawfish();

   public RendererSawfish(Context rendermanager) {
      super(rendermanager, SAWFISH_MODEL, 0.2F);
   }

   protected void scale(EntitySawfish entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntitySawfish entity) {
      return entity.getTexture();
   }
}

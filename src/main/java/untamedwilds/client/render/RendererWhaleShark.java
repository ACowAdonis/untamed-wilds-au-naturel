package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelWhaleShark;
import untamedwilds.entity.fish.EntityWhaleShark;

public class RendererWhaleShark extends MobRenderer<EntityWhaleShark, EntityModel<EntityWhaleShark>> {
   private static final ModelWhaleShark SHARK_MODEL = new ModelWhaleShark();

   public RendererWhaleShark(Context rendermanager) {
      super(rendermanager, SHARK_MODEL, 0.2F);
   }

   protected void scale(EntityWhaleShark entity, PoseStack matrixStackIn, float partialTickTime) {
      float f = entity.getMobSize();
      f *= entity.getScale();
      matrixStackIn.scale(f, f, f);
      this.shadowRadius = f;
   }

   @NotNull
   public ResourceLocation getTextureLocation(EntityWhaleShark entity) {
      return entity.getTexture();
   }
}

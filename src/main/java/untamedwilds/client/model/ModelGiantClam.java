package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.mollusk.EntityGiantClam;

public class ModelGiantClam extends AdvancedEntityModel<EntityGiantClam> {
   public AdvancedModelBox mantle;
   public AdvancedModelBox shell_2;
   public AdvancedModelBox shell_1;

   public ModelGiantClam() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.shell_1 = new AdvancedModelBox(this, 0, 0);
      this.shell_1.setRotationPoint(0.0F, 1.0F, -3.0F);
      this.shell_1.addBox(-9.0F, -10.0F, -3.0F, 18.0F, 12.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.shell_1, 0.3642502F, 0.0F, 0.0F);
      this.mantle = new AdvancedModelBox(this, 0, 36);
      this.mantle.setRotationPoint(0.0F, 22.0F, 0.0F);
      this.mantle.addBox(-8.0F, -6.0F, -3.0F, 16.0F, 8.0F, 6.0F, 0.0F);
      this.shell_2 = new AdvancedModelBox(this, 0, 18);
      this.shell_2.setRotationPoint(0.01F, 1.0F, 3.0F);
      this.shell_2.addBox(-9.0F, -10.0F, -3.0F, 18.0F, 12.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.shell_2, -0.3642502F, 0.0F, 0.0F);
      this.mantle.addChild(this.shell_1);
      this.mantle.addChild(this.shell_2);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.mantle);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(this.mantle, this.shell_1, this.shell_2);
   }

   public void setupAnim(EntityGiantClam clam, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.mantle.setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.walk(this.shell_1, 0.2F, 0.1F, true, 0.5F, 0.0F, ageInTicks / 20.0F, 0.5F);
      this.walk(this.shell_2, 0.2F, 0.1F, false, 0.5F, 0.0F, ageInTicks / 20.0F, 0.5F);
      this.progressRotation(this.shell_1, (float)clam.closeProgress, 0.0F, 0.0F, 0.0F, 200.0F);
      this.progressRotation(this.shell_2, (float)clam.closeProgress, 0.0F, 0.0F, 0.0F, 200.0F);
   }
}

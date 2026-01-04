package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.arthropod.EntityButterfly;

public class ModelButterfly extends AdvancedEntityModel<EntityButterfly> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox wing_right_top;
   public AdvancedModelBox wing_left_top;
   public AdvancedModelBox proboscis;
   public AdvancedModelBox wing_right_bottom;
   public AdvancedModelBox wing_left_bottom;

   public ModelButterfly() {
      this.texWidth = 32;
      this.texHeight = 32;
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 23.5F, 0.0F);
      this.body_main.addBox(-1.0F, -0.5F, -4.0F, 2.0F, 1.0F, 6.0F, 0.0F);
      this.proboscis = new AdvancedModelBox(this, 14, -3);
      this.proboscis.setRotationPoint(0.0F, 0.0F, -3.5F);
      this.proboscis.addBox(0.0F, -0.5F, -1.0F, 0.0F, 2.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.proboscis, 0.91053826F, 0.0F, 0.0F);
      this.wing_left_top = new AdvancedModelBox(this, -12, 8);
      this.wing_left_top.setRotationPoint(-0.25F, -0.5F, -1.0F);
      this.wing_left_top.addBox(-10.0F, 0.0F, -6.0F, 10.0F, 0.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.wing_left_top, 0.0F, 0.0F, 0.0F);
      this.wing_left_bottom = new AdvancedModelBox(this, -12, 20);
      this.wing_left_bottom.setRotationPoint(0.0F, 0.01F, 0.0F);
      this.wing_left_bottom.addBox(-10.0F, 0.0F, -6.0F, 10.0F, 0.0F, 12.0F, 0.0F);
      this.wing_right_top = new AdvancedModelBox(this, -12, 8);
      this.wing_right_top.mirror = true;
      this.wing_right_top.setRotationPoint(0.25F, -0.5F, -1.0F);
      this.wing_right_top.addBox(0.0F, 0.0F, -6.0F, 10.0F, 0.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.wing_right_top, 0.0F, 0.0F, 0.0F);
      this.wing_right_bottom = new AdvancedModelBox(this, -12, 20);
      this.wing_right_bottom.mirror = true;
      this.wing_right_bottom.setRotationPoint(0.0F, 0.01F, 0.0F);
      this.wing_right_bottom.addBox(0.0F, 0.0F, -6.0F, 10.0F, 0.0F, 12.0F, 0.0F);
      this.body_main.addChild(this.proboscis);
      this.body_main.addChild(this.wing_left_top);
      this.wing_right_top.addChild(this.wing_right_bottom);
      this.wing_left_top.addChild(this.wing_left_bottom);
      this.body_main.addChild(this.wing_right_top);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(this.body_main, this.wing_right_top, this.wing_right_bottom, this.wing_left_top, this.wing_left_bottom, this.proboscis);
   }

   public void setupAnim(EntityButterfly butterfly, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      limbSwingAmount = Math.min(0.4F, limbSwingAmount);
      float globalSpeed = 1.2F;
      float globalDegree = 1.4F;
      if (butterfly.isResting()) {
         this.progressRotation(this.body_main, 1.0F, (float) (-Math.PI / 2), this.body_main.rotateAngleY, this.body_main.rotateAngleZ, 1.0F);
         this.flap(this.wing_left_top, 0.6F, 0.3F, false, 0.5F, 0.4F, ageInTicks / 6.0F, 2.0F);
         this.flap(this.wing_right_top, 0.6F, 0.3F, true, 0.5F, 0.4F, ageInTicks / 6.0F, 2.0F);
      } else {
         this.bob(this.body_main, 0.6F, 1.0F, true, ageInTicks / 2.0F, 2.0F);
         this.swing(this.body_main, 0.6F, 0.2F, false, 0.5F, -0.2F, ageInTicks / 2.0F, 2.0F);
         this.walk(this.body_main, 0.6F, 0.2F, false, 0.0F, -0.2F, ageInTicks / 2.0F, 2.0F);
         this.flap(this.wing_left_top, 0.6F, 0.6F, false, 0.5F, 0.2F, ageInTicks * 1.5F, 2.0F);
         this.flap(this.wing_right_top, 0.6F, 0.6F, true, 0.5F, 0.2F, ageInTicks * 1.5F, 2.0F);
         this.body_main.rotateAngleY = netHeadYaw * (float) (Math.PI / 180.0);
      }
   }
}

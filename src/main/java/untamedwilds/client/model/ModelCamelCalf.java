package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.mammal.EntityCamel;

public class ModelCamelCalf extends AdvancedEntityModel<EntityCamel> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox arm_left;
   public AdvancedModelBox arm_right;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox neck;
   public AdvancedModelBox leg_left_calf;
   public AdvancedModelBox leg_right_calf;
   public AdvancedModelBox head_main;
   public AdvancedModelBox head_nose;
   public AdvancedModelBox head_nose_1;
   public AdvancedModelBox ear_left;
   public AdvancedModelBox ear_right;
   public AdvancedModelBox leg_left_calf_1;
   public AdvancedModelBox leg_right_calf_1;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;

   public ModelCamelCalf() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.ear_right = new AdvancedModelBox(this, 16, 0);
      this.ear_right.setRotationPoint(-3.0F, -3.0F, -1.0F);
      this.ear_right.addBox(-3.0F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, 0.7285004F, 0.5009095F, 0.7740535F);
      this.head_nose = new AdvancedModelBox(this, 94, 0);
      this.head_nose.setRotationPoint(0.0F, -4.5F, -6.0F);
      this.head_nose.addBox(-3.0F, 0.0F, -6.0F, 6.0F, 3.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_nose, 0.18203785F, 0.0F, 0.0F);
      this.leg_right_calf_1 = new AdvancedModelBox(this, 84, 26);
      this.leg_right_calf_1.mirror = true;
      this.leg_right_calf_1.setRotationPoint(0.0F, 9.0F, 0.0F);
      this.leg_right_calf_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right_calf_1, -0.4098033F, 0.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 8, 6);
      this.body_main.setRotationPoint(0.0F, 7.3F, 0.0F);
      this.body_main.addBox(-3.0F, -5.0F, -9.0F, 6.0F, 10.0F, 18.0F, 0.0F);
      this.setRotateAngle(this.body_main, -0.045553092F, 0.0F, 0.0F);
      this.leg_left_calf_1 = new AdvancedModelBox(this, 84, 26);
      this.leg_left_calf_1.setRotationPoint(0.0F, 9.0F, 0.0F);
      this.leg_left_calf_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left_calf_1, -0.4098033F, 0.0F, 0.0F);
      this.leg_left_calf = new AdvancedModelBox(this, 68, 20);
      this.leg_left_calf.setRotationPoint(-2.5F, -2.5F, 6.5F);
      this.leg_left_calf.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_calf, 0.4553564F, 0.0F, 0.0F);
      this.head_nose_1 = new AdvancedModelBox(this, 62, 0);
      this.head_nose_1.setRotationPoint(0.0F, -1.8F, -6.0F);
      this.head_nose_1.addBox(-2.5F, 0.0F, -5.0F, 5.0F, 2.0F, 5.0F, 0.0F);
      this.neck = new AdvancedModelBox(this, 58, 34);
      this.neck.setRotationPoint(0.0F, 0.0F, -9.2F);
      this.neck.addBox(-2.5F, -2.5F, -8.5F, 5.0F, 5.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.neck, -0.95609134F, 0.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 84, 20);
      this.arm_right.mirror = true;
      this.arm_right.setRotationPoint(-2.5F, 0.05F, -7.99F);
      this.arm_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right, 0.045553092F, 0.0F, 0.0F);
      this.arm_left = new AdvancedModelBox(this, 84, 20);
      this.arm_left.setRotationPoint(2.5F, 0.05F, -7.99F);
      this.arm_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left, 0.045553092F, 0.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 84, 27);
      this.arm_right_2.mirror = true;
      this.arm_right_2.setRotationPoint(0.0F, 7.0F, 0.0F);
      this.arm_right_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, 0.045553092F, 0.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 84, 27);
      this.arm_left_2.setRotationPoint(0.0F, 7.0F, 0.0F);
      this.arm_left_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, 0.045553092F, 0.0F, 0.0F);
      this.leg_right_calf = new AdvancedModelBox(this, 68, 20);
      this.leg_right_calf.mirror = true;
      this.leg_right_calf.setRotationPoint(2.5F, -2.5F, 6.5F);
      this.leg_right_calf.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_calf, 0.4553564F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 36);
      this.head_main.setRotationPoint(0.0F, -2.4F, -6.3F);
      this.head_main.addBox(-3.0F, -5.0F, -6.0F, 6.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_main, 1.2747885F, 0.0F, 0.0F);
      this.ear_left = new AdvancedModelBox(this, 16, 0);
      this.ear_left.setRotationPoint(3.0F, -3.0F, -1.0F);
      this.ear_left.addBox(0.0F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, 0.7285004F, -0.5009095F, -0.7740535F);
      this.eye_right = new AdvancedModelBox(this, 0, 0);
      this.eye_right.mirror = true;
      this.eye_right.setRotationPoint(-3.01F, -3.0F, -4.0F);
      this.eye_right.addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 0);
      this.eye_left.setRotationPoint(3.01F, -3.0F, -4.0F);
      this.eye_left.addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.head_main.addChild(this.ear_right);
      this.head_main.addChild(this.head_nose);
      this.arm_left.addChild(this.arm_left_2);
      this.arm_right.addChild(this.arm_right_2);
      this.leg_right_calf.addChild(this.leg_right_calf_1);
      this.leg_left_calf.addChild(this.leg_left_calf_1);
      this.body_main.addChild(this.leg_left_calf);
      this.head_main.addChild(this.head_nose_1);
      this.body_main.addChild(this.neck);
      this.body_main.addChild(this.arm_right);
      this.body_main.addChild(this.arm_left);
      this.body_main.addChild(this.leg_right_calf);
      this.neck.addChild(this.head_main);
      this.head_main.addChild(this.ear_left);
      this.head_main.addChild(this.eye_left);
      this.head_main.addChild(this.eye_right);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.arm_left,
         this.arm_right,
         this.arm_left_2,
         this.arm_right_2,
         this.neck,
         this.leg_left_calf,
         this.leg_left_calf_1,
         this.leg_right_calf,
         this.leg_right_calf_1,
         this.head_main,
         this.head_nose,
         new AdvancedModelBox[]{this.head_nose_1, this.ear_left, this.ear_right, this.eye_left, this.eye_right}
      );
   }

   public void setupAnim(EntityCamel camel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_calf, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_calf, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!camel.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-1.0F, -2.0F, -2.0F);
         this.eye_left.setRotationPoint(1.0F, -2.0F, -2.0F);
      }

      if (!camel.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.neck});
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (camel.canMove()) {
         this.flap(this.body_main, 0.5F, 0.2F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.neck, 0.5F, 0.2F, true, 0.2F, 0.0F, limbSwing, limbSwingAmount);
         this.bob(this.arm_right, 0.5F, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.arm_right, 0.5F, 1.0F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.arm_right_2, 0.5F, 0.6F, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
         this.bob(this.arm_left, 0.5F, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.arm_left, 0.5F, 1.0F, true, 2.4F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.arm_left_2, 0.5F, 0.6F, true, 2.6F, 0.2F, limbSwing, limbSwingAmount);
         this.bob(this.leg_right_calf, 0.5F, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_calf, 0.5F, 1.0F, true, 0.2F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_calf_1, 0.5F, 0.6F, true, 0.4F, 0.2F, limbSwing, limbSwingAmount);
         this.bob(this.leg_left_calf, 0.5F, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_calf, 0.5F, 1.0F, true, 2.6F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_calf_1, 0.5F, 0.6F, true, 2.8F, 0.2F, limbSwing, limbSwingAmount);
      }

      if (camel.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)camel.sitProgress, 0.0F, 17.3F, 0.0F, 40.0F);
         this.progressRotation(this.neck, (float)camel.sitProgress, (float)Math.toRadians(-54.78), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)camel.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_2, (float)camel.sitProgress, 0.0F, 7.0F, -1.7F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)camel.sitProgress, (float)Math.toRadians(104.35), (float)Math.toRadians(2.61), 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)camel.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_left_2, (float)camel.sitProgress, 0.0F, 7.0F, -1.7F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)camel.sitProgress, (float)Math.toRadians(104.35), (float)Math.toRadians(-2.61), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf, (float)camel.sitProgress, (float)Math.toRadians(18.26), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf_1, (float)camel.sitProgress, (float)Math.toRadians(-112.17), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf, (float)camel.sitProgress, (float)Math.toRadians(18.26), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf_1, (float)camel.sitProgress, (float)Math.toRadians(-112.17), 0.0F, 0.0F, 40.0F);
      }

      if (camel.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)camel.sleepProgress, 0.0F, 17.3F, 0.0F, 40.0F);
         this.progressRotation(this.neck, (float)camel.sleepProgress, (float)Math.toRadians(39.13), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.head_main, (float)camel.sleepProgress, 0.0F, 2.6F, -8.3F, 40.0F);
         this.progressRotation(this.head_main, (float)camel.sleepProgress, (float)Math.toRadians(-33.91), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)camel.sleepProgress, (float)Math.toRadians(-10.43F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_2, (float)camel.sleepProgress, 0.0F, 7.0F, -1.7F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)camel.sleepProgress, (float)Math.toRadians(104.35), (float)Math.toRadians(2.61), 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)camel.sleepProgress, (float)Math.toRadians(-10.43F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_left_2, (float)camel.sleepProgress, 0.0F, 7.0F, -1.7F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)camel.sleepProgress, (float)Math.toRadians(104.35), (float)Math.toRadians(-2.61), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf, (float)camel.sleepProgress, (float)Math.toRadians(18.26), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf_1, (float)camel.sleepProgress, (float)Math.toRadians(-112.17), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf, (float)camel.sleepProgress, (float)Math.toRadians(18.26), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf_1, (float)camel.sleepProgress, (float)Math.toRadians(-112.17), 0.0F, 0.0F, 40.0F);
      }
   }
}

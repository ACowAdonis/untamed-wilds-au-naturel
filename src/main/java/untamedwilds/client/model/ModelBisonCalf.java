package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBison;

public class ModelBisonCalf extends AdvancedEntityModel<EntityBison> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox leg_left_thigh;
   public AdvancedModelBox leg_right_thigh;
   public AdvancedModelBox tail;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox leg_left_calf;
   public AdvancedModelBox leg_right_calf;
   public AdvancedModelBox head_main;
   public AdvancedModelBox head_ear_right;
   public AdvancedModelBox head_ear_left;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox arm_left_2;

   public ModelBisonCalf() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.head_neck = new AdvancedModelBox(this, 16, 15);
      this.head_neck.setRotationPoint(0.0F, -1.0F, -4.5F);
      this.head_neck.addBox(-2.0F, -3.5F, -6.0F, 4.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.22759093F, 0.0F, 0.0F);
      this.leg_right_calf = new AdvancedModelBox(this, 86, 28);
      this.leg_right_calf.mirror = true;
      this.leg_right_calf.setRotationPoint(-0.01F, 2.5F, 1.5F);
      this.leg_right_calf.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.arm_right_1 = new AdvancedModelBox(this, 0, 0);
      this.arm_right_1.mirror = true;
      this.arm_right_1.setRotationPoint(-2.0F, 0.0F, -5.5F);
      this.arm_right_1.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, 0.13665928F, 0.0F, 0.0F);
      this.leg_left_thigh = new AdvancedModelBox(this, 62, 26);
      this.leg_left_thigh.setRotationPoint(2.5F, 0.6F, 5.2F);
      this.leg_left_thigh.addBox(-1.5F, -3.5F, -3.5F, 3.0F, 8.0F, 6.0F, 0.0F);
      this.arm_left_1 = new AdvancedModelBox(this, 0, 0);
      this.arm_left_1.setRotationPoint(2.0F, 0.0F, -5.5F);
      this.arm_left_1.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, 0.13665928F, 0.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 88, 42);
      this.body_main.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.body_main.addBox(-3.0F, -5.0F, -7.0F, 6.0F, 8.0F, 14.0F, 0.0F);
      this.head_ear_left = new AdvancedModelBox(this, 18, 32);
      this.head_ear_left.mirror = true;
      this.head_ear_left.setRotationPoint(2.5F, -2.0F, -1.4F);
      this.head_ear_left.addBox(-1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_left, 0.18203785F, -0.18203785F, 0.13665928F);
      this.leg_left_calf = new AdvancedModelBox(this, 86, 28);
      this.leg_left_calf.setRotationPoint(0.01F, 2.5F, 1.5F);
      this.leg_left_calf.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 30);
      this.eye_left.setRotationPoint(2.51F, -1.0F, -3.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 0, 30);
      this.eye_right.setRotationPoint(-2.51F, -1.0F, -3.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 0, 12);
      this.arm_right_2.mirror = true;
      this.arm_right_2.setRotationPoint(0.0F, 6.3F, 0.0F);
      this.arm_right_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, -0.13665928F, 0.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 0, 12);
      this.arm_left_2.setRotationPoint(0.0F, 6.3F, 0.0F);
      this.arm_left_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, -0.13665928F, 0.0F, 0.0F);
      this.tail = new AdvancedModelBox(this, 65, 1);
      this.tail.setRotationPoint(0.0F, -5.0F, 7.0F);
      this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 10.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.tail, 0.18203785F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 68, 51);
      this.head_main.setRotationPoint(0.0F, 1.7F, -5.0F);
      this.head_main.addBox(-2.5F, -4.5F, -5.0F, 5.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_main, -0.3642502F, 0.0F, 0.0F);
      this.leg_right_thigh = new AdvancedModelBox(this, 62, 26);
      this.leg_right_thigh.mirror = true;
      this.leg_right_thigh.setRotationPoint(-2.5F, 0.6F, 5.2F);
      this.leg_right_thigh.addBox(-1.5F, -3.5F, -3.5F, 3.0F, 8.0F, 6.0F, 0.0F);
      this.head_ear_right = new AdvancedModelBox(this, 18, 32);
      this.head_ear_right.setRotationPoint(-2.5F, -2.0F, -1.4F);
      this.head_ear_right.addBox(-2.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_right, 0.18203785F, 0.18203785F, -0.13665928F);
      this.body_main.addChild(this.head_neck);
      this.leg_right_thigh.addChild(this.leg_right_calf);
      this.body_main.addChild(this.arm_right_1);
      this.body_main.addChild(this.leg_left_thigh);
      this.body_main.addChild(this.arm_left_1);
      this.head_main.addChild(this.head_ear_left);
      this.leg_left_thigh.addChild(this.leg_left_calf);
      this.head_main.addChild(this.eye_left);
      this.head_main.addChild(this.eye_right);
      this.arm_right_1.addChild(this.arm_right_2);
      this.arm_left_1.addChild(this.arm_left_2);
      this.body_main.addChild(this.tail);
      this.head_neck.addChild(this.head_main);
      this.body_main.addChild(this.leg_right_thigh);
      this.head_main.addChild(this.head_ear_right);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.leg_left_thigh,
         this.leg_right_thigh,
         this.tail,
         this.arm_right_1,
         this.arm_left_1,
         this.head_neck,
         this.arm_right_2,
         this.arm_left_2,
         this.head_main,
         this.head_ear_right,
         this.head_ear_left,
         new AdvancedModelBox[]{this.eye_left, this.eye_right, this.leg_left_calf, this.leg_right_calf}
      );
   }

   public void setupAnim(EntityBison bison, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_thigh, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_thigh, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!bison.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-1.0F, -2.0F, -2.0F);
         this.eye_left.setRotationPoint(1.0F, -2.0F, -2.0F);
      }

      if (!bison.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (bison.canMove()) {
         this.arm_right_1.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
         this.arm_left_1.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_left_thigh.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_right_thigh.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
      }

      if (bison.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)bison.sitProgress, 0.0F, 17.5F, 0.0F, 40.0F);
         this.progressRotation(this.head_neck, (float)bison.sitProgress, (float)Math.toRadians(-33.91F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_main, (float)bison.sitProgress, (float)Math.toRadians(-2.61F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_1, (float)bison.sitProgress, -4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)bison.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)bison.sitProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(15.65F), 40.0F);
         this.progressPosition(this.arm_left_1, (float)bison.sitProgress, 4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)bison.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)bison.sitProgress, (float)Math.toRadians(86.09F), 0.0F, (float)Math.toRadians(-5.22F), 40.0F);
         this.progressRotation(this.arm_left_2, (float)bison.sitProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(15.65F), 40.0F);
         this.progressRotation(this.leg_right_thigh, (float)bison.sitProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf, (float)bison.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(-10.43F), 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)bison.sitProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(-15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf, (float)bison.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(10.43F), 40.0F);
      }

      if (bison.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)bison.sitProgress, 0.0F, 17.5F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_1, (float)bison.sitProgress, -4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)bison.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)bison.sitProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(15.65F), 40.0F);
         this.progressPosition(this.arm_left_1, (float)bison.sitProgress, 4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)bison.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)bison.sitProgress, (float)Math.toRadians(86.09F), 0.0F, (float)Math.toRadians(-5.22F), 40.0F);
         this.progressRotation(this.arm_left_2, (float)bison.sitProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(15.65F), 40.0F);
         this.progressRotation(this.leg_right_thigh, (float)bison.sitProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf, (float)bison.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(-10.43F), 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)bison.sitProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(-15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf, (float)bison.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(10.43F), 40.0F);
      }
   }
}

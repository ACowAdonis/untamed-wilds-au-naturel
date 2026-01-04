package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityWildebeest;

public class ModelWildebeestCalf extends AdvancedEntityModel<EntityWildebeest> {
   public AdvancedModelBox main_body;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox leg_left_thigh;
   public AdvancedModelBox leg_right_thigh;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox head_face;
   public AdvancedModelBox head_ear_left;
   public AdvancedModelBox head_ear_right;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox leg_left_calf;
   public AdvancedModelBox leg_right_calf;

   public ModelWildebeestCalf() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.arm_left_1 = new AdvancedModelBox(this, 58, 48);
      this.arm_left_1.setRotationPoint(1.5F, 0.5F, -3.0F);
      this.arm_left_1.addBox(0.0F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F);
      this.arm_left_1.mirror = true;
      this.body_tail = new AdvancedModelBox(this, 36, 52);
      this.body_tail.setRotationPoint(0.0F, -1.0F, 4.51F);
      this.body_tail.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.body_tail, 0.27314404F, 0.0F, 0.0F);
      this.head_ear_right = new AdvancedModelBox(this, 15, 38);
      this.head_ear_right.mirror = true;
      this.head_ear_right.setRotationPoint(-1.5F, -1.0F, 1.0F);
      this.head_ear_right.addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_right, -0.13665928F, 0.63739425F, 0.31869712F);
      this.eye_left = new AdvancedModelBox(this, 14, 34);
      this.eye_left.mirror = true;
      this.eye_left.setRotationPoint(2.01F, -0.5F, 0.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.head_ear_left = new AdvancedModelBox(this, 15, 38);
      this.head_ear_left.mirror = true;
      this.head_ear_left.setRotationPoint(1.5F, -1.0F, 1.0F);
      this.head_ear_left.addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_left, -0.13665928F, -0.63739425F, -0.31869712F);
      this.head_neck = new AdvancedModelBox(this, 0, 55);
      this.head_neck.setRotationPoint(0.0F, 0.5F, -2.5F);
      this.head_neck.addBox(-1.5F, -1.5F, -6.0F, 3.0F, 4.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.68294734F, 0.0F, 0.0F);
      this.arm_right_1 = new AdvancedModelBox(this, 58, 48);
      this.arm_right_1.setRotationPoint(-1.5F, 0.5F, -3.0F);
      this.arm_right_1.addBox(-1.0F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F);
      this.leg_left_calf = new AdvancedModelBox(this, 58, 48);
      this.leg_left_calf.mirror = true;
      this.leg_left_calf.setRotationPoint(1.01F, 1.5F, 0.0F);
      this.leg_left_calf.addBox(0.0F, 0.0F, 0.0F, 1.0F, 9.0F, 2.0F, 0.0F);
      this.leg_right_calf = new AdvancedModelBox(this, 58, 48);
      this.leg_right_calf.setRotationPoint(-1.01F, 1.5F, 0.0F);
      this.leg_right_calf.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 2.0F, 0.0F);
      this.leg_right_thigh = new AdvancedModelBox(this, 54, 38);
      this.leg_right_thigh.setRotationPoint(-0.5F, 0.5F, 5.0F);
      this.leg_right_thigh.addBox(-2.0F, -1.5F, -2.5F, 2.0F, 5.0F, 3.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 58, 50);
      this.arm_right_2.setRotationPoint(0.01F, 3.5F, 0.01F);
      this.arm_right_2.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 7.0F, 2.0F, 0.0F);
      this.leg_left_thigh = new AdvancedModelBox(this, 54, 38);
      this.leg_left_thigh.mirror = true;
      this.leg_left_thigh.setRotationPoint(0.5F, 0.5F, 5.0F);
      this.leg_left_thigh.addBox(0.0F, -1.5F, -2.5F, 2.0F, 5.0F, 3.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 14, 34);
      this.eye_right.mirror = true;
      this.eye_right.setRotationPoint(-2.01F, -0.5F, 0.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 58, 50);
      this.arm_left_2.setRotationPoint(0.01F, 3.5F, 0.01F);
      this.arm_left_2.addBox(0.0F, 0.0F, -1.0F, 1.0F, 7.0F, 2.0F, 0.0F);
      this.arm_left_2.mirror = true;
      this.head_face = new AdvancedModelBox(this, 16, 55);
      this.head_face.setRotationPoint(0.0F, 0.5F, -6.0F);
      this.head_face.addBox(-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_face, 1.4570009F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 36, 49);
      this.main_body.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.main_body.addBox(-2.0F, -2.0F, -5.0F, 4.0F, 5.0F, 10.0F, 0.0F);
      this.main_body.addChild(this.arm_left_1);
      this.main_body.addChild(this.body_tail);
      this.head_face.addChild(this.head_ear_right);
      this.head_face.addChild(this.eye_left);
      this.head_face.addChild(this.head_ear_left);
      this.main_body.addChild(this.head_neck);
      this.main_body.addChild(this.arm_right_1);
      this.leg_left_thigh.addChild(this.leg_left_calf);
      this.leg_right_thigh.addChild(this.leg_right_calf);
      this.main_body.addChild(this.leg_right_thigh);
      this.arm_right_1.addChild(this.arm_right_2);
      this.main_body.addChild(this.leg_left_thigh);
      this.head_face.addChild(this.eye_right);
      this.arm_left_1.addChild(this.arm_left_2);
      this.head_neck.addChild(this.head_face);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.head_neck,
         this.arm_left_1,
         this.arm_right_1,
         this.leg_left_thigh,
         this.leg_right_thigh,
         this.body_tail,
         this.head_face,
         this.head_ear_left,
         this.head_ear_right,
         this.eye_left,
         this.eye_right,
         new AdvancedModelBox[]{this.arm_left_2, this.arm_right_2, this.leg_left_calf, this.leg_right_calf}
      );
   }

   public void setupAnim(EntityWildebeest wildebeest, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.main_body, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_thigh, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_thigh, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!wildebeest.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_face});
      }

      if (!wildebeest.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-1.0F, -0.5F, 0.0F);
         this.eye_left.setRotationPoint(1.0F, -0.5F, 0.0F);
      }

      if (wildebeest.canMove()) {
         this.arm_right_1.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
         this.arm_left_1.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_left_thigh.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_right_thigh.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
      }

      if (wildebeest.sitProgress != 0) {
         this.progressRotation(this.leg_right_thigh, (float)wildebeest.sitProgress, (float) (-Math.PI / 2), 0.13665928F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)wildebeest.sitProgress, (float) (-Math.PI / 2), -0.13665928F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)wildebeest.sitProgress, -0.91053826F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)wildebeest.sitProgress, -0.91053826F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)wildebeest.sitProgress, 2.4586453F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)wildebeest.sitProgress, 2.4586453F, 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.main_body, (float)wildebeest.sitProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_left_2, (float)wildebeest.sitProgress, 0.01F, 3.5F, 0.01F, 40.0F);
         this.progressPosition(this.arm_right_2, (float)wildebeest.sitProgress, 0.01F, 3.5F, 0.01F, 40.0F);
      }

      if (wildebeest.sleepProgress != 0) {
         this.progressRotation(this.leg_right_thigh, (float)wildebeest.sleepProgress, (float) (-Math.PI / 2), 0.13665928F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)wildebeest.sleepProgress, (float) (-Math.PI / 2), -0.13665928F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)wildebeest.sleepProgress, -0.91053826F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)wildebeest.sleepProgress, -0.91053826F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)wildebeest.sleepProgress, 2.4586453F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)wildebeest.sleepProgress, 2.4586453F, 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.main_body, (float)wildebeest.sleepProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_left_2, (float)wildebeest.sleepProgress, 0.01F, 3.5F, 0.01F, 40.0F);
         this.progressPosition(this.arm_right_2, (float)wildebeest.sleepProgress, 0.01F, 3.5F, 0.01F, 40.0F);
      }
   }
}

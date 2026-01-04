package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityHippo;

public class ModelHippoCalf extends AdvancedEntityModel<EntityHippo> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox leg_right;
   public AdvancedModelBox leg_left;
   public AdvancedModelBox arm_right;
   public AdvancedModelBox arm_left;
   public AdvancedModelBox head_face;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox face_ear_right;
   public AdvancedModelBox head_jaw_1;
   public AdvancedModelBox face_ear_left;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;

   public ModelHippoCalf() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.leg_right = new AdvancedModelBox(this, 106, 40);
      this.leg_right.setRotationPoint(-2.51F, 2.5F, 6.51F);
      this.leg_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 80, 24);
      this.head_neck.setRotationPoint(0.0F, -0.5F, -6.5F);
      this.head_neck.addBox(-3.5F, -3.0F, -4.0F, 7.0F, 6.0F, 4.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 106, 40);
      this.arm_right.setRotationPoint(-2.51F, 2.5F, -6.51F);
      this.arm_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 80, 34);
      this.head_face.setRotationPoint(0.0F, 0.0F, -3.5F);
      this.head_face.addBox(-3.0F, -3.5F, -4.0F, 6.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.13665928F, 0.0F, 0.0F);
      this.face_ear_right = new AdvancedModelBox(this, 102, 37);
      this.face_ear_right.setRotationPoint(-2.0F, -3.0F, -0.5F);
      this.face_ear_right.addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.face_ear_right, 0.13665928F, -0.091106184F, -0.3642502F);
      this.head_jaw_1 = new AdvancedModelBox(this, 102, 31);
      this.head_jaw_1.setRotationPoint(0.0F, 0.0F, -4.0F);
      this.head_jaw_1.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 3.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 104, 38);
      this.eye_left.setRotationPoint(3.01F, -2.0F, -2.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.eye_left, -0.009075712F, 0.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 106, 40);
      this.leg_left.mirror = true;
      this.leg_left.setRotationPoint(2.51F, 2.5F, 6.51F);
      this.leg_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 102, 24);
      this.head_jaw.setRotationPoint(0.0F, -1.0F, -4.0F);
      this.head_jaw.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 102, 38);
      this.eye_right.setRotationPoint(-3.01F, -2.0F, -2.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 80, 0);
      this.body_main.setRotationPoint(0.0F, 16.5F, 0.0F);
      this.body_main.addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 16.0F, 0.0F);
      this.face_ear_left = new AdvancedModelBox(this, 102, 37);
      this.face_ear_left.mirror = true;
      this.face_ear_left.setRotationPoint(2.0F, -3.0F, -0.5F);
      this.face_ear_left.addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.face_ear_left, 0.13665928F, 0.091106184F, 0.3642502F);
      this.arm_left = new AdvancedModelBox(this, 106, 40);
      this.arm_left.mirror = true;
      this.arm_left.setRotationPoint(2.51F, 2.5F, -6.51F);
      this.arm_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.body_main.addChild(this.leg_right);
      this.body_main.addChild(this.head_neck);
      this.body_main.addChild(this.arm_right);
      this.head_neck.addChild(this.head_face);
      this.head_face.addChild(this.face_ear_right);
      this.head_face.addChild(this.head_jaw_1);
      this.head_face.addChild(this.eye_left);
      this.body_main.addChild(this.leg_left);
      this.head_face.addChild(this.head_jaw);
      this.head_face.addChild(this.eye_right);
      this.head_face.addChild(this.face_ear_left);
      this.body_main.addChild(this.arm_left);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_neck,
         this.leg_right,
         this.leg_left,
         this.arm_right,
         this.arm_left,
         this.head_face,
         this.head_jaw,
         this.face_ear_right,
         this.head_jaw_1,
         this.face_ear_left,
         this.eye_left,
         new AdvancedModelBox[]{this.eye_right}
      );
   }

   public void setupAnim(EntityHippo hippo, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!hippo.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_face});
      }

      if (!hippo.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-1.0F, -2.0F, -2.0F);
         this.eye_left.setRotationPoint(1.0F, -2.0F, -2.0F);
      }

      if (hippo.canMove()) {
         this.arm_right.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
         this.arm_left.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_left.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_right.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
      }
   }
}

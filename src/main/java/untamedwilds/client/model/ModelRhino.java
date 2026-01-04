package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.mammal.EntityRhino;

public class ModelRhino extends AdvancedEntityModel<EntityRhino> {
   private final AdvancedModelBox body_belly;
   private final AdvancedModelBox body_front;
   private final AdvancedModelBox leg_right;
   private final AdvancedModelBox leg_left;
   private final AdvancedModelBox head_neck;
   private final AdvancedModelBox arm_right_1;
   private final AdvancedModelBox arm_left_1;
   private final AdvancedModelBox head_face;
   private final AdvancedModelBox ear_right;
   private final AdvancedModelBox ear_left;
   private final AdvancedModelBox horn_front;
   private final AdvancedModelBox horn_front_small;
   private final AdvancedModelBox horn_back;
   private final AdvancedModelBox eye_left;
   private final AdvancedModelBox eye_right;
   private final AdvancedModelBox arm_right_2;
   private final AdvancedModelBox arm_left_2;
   private final AdvancedModelBox leg_right_2;
   private final AdvancedModelBox leg_left_2;
   private final ModelAnimator animator;

   public ModelRhino() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.horn_back = new AdvancedModelBox(this, 0, 0);
      this.horn_back.setRotationPoint(0.0F, -2.6F, -4.7F);
      this.horn_back.addBox(-1.0F, -4.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.horn_back, 0.045553092F, 0.0F, 0.0F);
      this.horn_front_small = new AdvancedModelBox(this, 11, 0);
      this.horn_front_small.setRotationPoint(0.0F, -3.6F, -9.1F);
      this.horn_front_small.addBox(-1.0F, -4.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.horn_front_small, 0.18203785F, 0.0F, 0.0F);
      this.arm_left_1 = new AdvancedModelBox(this, 50, 0);
      this.arm_left_1.setRotationPoint(4.0F, 6.0F, -6.0F);
      this.arm_left_1.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, 0.3642502F, 0.0F, 0.091106184F);
      this.horn_front = new AdvancedModelBox(this, 0, 10);
      this.horn_front.setRotationPoint(0.0F, -3.6F, -9.0F);
      this.horn_front.addBox(-1.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.horn_front, 0.13665928F, 0.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 72, 28);
      this.leg_left.setRotationPoint(4.0F, 6.0F, 15.0F);
      this.leg_left.addBox(-2.5F, -2.0F, -3.5F, 5.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.leg_left, 0.31869712F, 0.0F, 0.091106184F);
      this.leg_left_2 = new AdvancedModelBox(this, 76, 42);
      this.leg_left_2.setRotationPoint(-0.01F, 3.0F, 0.5F);
      this.leg_left_2.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, -0.31869712F, 0.0F, -0.091106184F);
      this.ear_right = new AdvancedModelBox(this, 0, 36);
      this.ear_right.setRotationPoint(-2.5F, -4.0F, -5.0F);
      this.ear_right.addBox(-1.0F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, 0.0F, 0.22759093F, -0.5462881F);
      this.eye_left = new AdvancedModelBox(this, 48, 44);
      this.eye_left.setRotationPoint(3.01F, -1.5F, -5.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 28, 36);
      this.arm_left_2.setRotationPoint(-0.01F, 6.3F, 0.5F);
      this.arm_left_2.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, -0.13665928F, 0.0F, -0.091106184F);
      this.arm_right_1 = new AdvancedModelBox(this, 50, 0);
      this.arm_right_1.mirror = true;
      this.arm_right_1.setRotationPoint(-4.0F, 6.0F, -6.0F);
      this.arm_right_1.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, 0.3642502F, 0.0F, -0.091106184F);
      this.arm_right_2 = new AdvancedModelBox(this, 28, 36);
      this.arm_right_2.mirror = true;
      this.arm_right_2.setRotationPoint(0.01F, 6.3F, 0.5F);
      this.arm_right_2.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, -0.13665928F, 0.0F, 0.091106184F);
      this.leg_right = new AdvancedModelBox(this, 72, 28);
      this.leg_right.mirror = true;
      this.leg_right.setRotationPoint(-4.0F, 6.0F, 15.0F);
      this.leg_right.addBox(-2.5F, -2.0F, -3.5F, 5.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.leg_right, 0.31869712F, 0.0F, -0.091106184F);
      this.body_front = new AdvancedModelBox(this, 72, 0);
      this.body_front.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body_front.addBox(-8.0F, -8.0F, -12.0F, 16.0F, 16.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.body_front, -0.22759093F, 0.0F, 0.0F);
      this.leg_right_2 = new AdvancedModelBox(this, 76, 42);
      this.leg_right_2.mirror = true;
      this.leg_right_2.setRotationPoint(0.01F, 3.0F, 0.5F);
      this.leg_right_2.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, -0.31869712F, 0.0F, 0.091106184F);
      this.body_belly = new AdvancedModelBox(this, 0, 0);
      this.body_belly.setRotationPoint(0.0F, 8.5F, 0.0F);
      this.body_belly.addBox(-7.0F, -7.0F, -3.0F, 14.0F, 14.0F, 22.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 44, 44);
      this.eye_right.setRotationPoint(-3.01F, -1.5F, -5.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 0, 36);
      this.head_neck.setRotationPoint(0.0F, -1.0F, -11.0F);
      this.head_neck.addBox(-4.0F, -5.0F, -8.0F, 8.0F, 10.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.head_neck, 0.5462881F, 0.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 40, 36);
      this.head_face.setRotationPoint(0.0F, 1.0F, -6.0F);
      this.head_face.addBox(-3.0F, -4.0F, -11.0F, 6.0F, 8.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.22759093F, 0.0F, 0.0F);
      this.ear_left = new AdvancedModelBox(this, 0, 36);
      this.ear_left.mirror = true;
      this.ear_left.setRotationPoint(2.5F, -4.0F, -5.0F);
      this.ear_left.addBox(-1.0F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, 0.0F, -0.22759093F, 0.5462881F);
      this.head_face.addChild(this.horn_back);
      this.body_front.addChild(this.arm_left_1);
      this.head_face.addChild(this.horn_front);
      this.head_face.addChild(this.horn_front_small);
      this.body_belly.addChild(this.leg_left);
      this.leg_left.addChild(this.leg_left_2);
      this.head_neck.addChild(this.ear_right);
      this.head_face.addChild(this.eye_left);
      this.arm_left_1.addChild(this.arm_left_2);
      this.body_front.addChild(this.arm_right_1);
      this.arm_right_1.addChild(this.arm_right_2);
      this.body_belly.addChild(this.leg_right);
      this.body_belly.addChild(this.body_front);
      this.leg_right.addChild(this.leg_right_2);
      this.head_face.addChild(this.eye_right);
      this.body_front.addChild(this.head_neck);
      this.head_neck.addChild(this.head_face);
      this.head_neck.addChild(this.ear_left);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_belly);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_belly,
         this.body_front,
         this.leg_right,
         this.leg_left,
         this.head_neck,
         this.arm_right_1,
         this.arm_left_1,
         this.head_face,
         this.ear_right,
         this.ear_left,
         this.horn_front,
         this.horn_back,
         new AdvancedModelBox[]{this.eye_left, this.eye_right, this.arm_right_2, this.arm_left_2, this.leg_right_2, this.leg_left_2, this.horn_front_small}
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityRhino rhino = (EntityRhino)entityIn;
      this.animator.update(rhino);
      this.animator.setAnimation(EntityRhino.ATTACK_THREATEN);

      for (int i = 0; i < 2; i++) {
         this.animator.startKeyframe(12);
         this.rotate(this.animator, this.body_front, 0.0F, 0.0F, 7.83F);
         this.rotate(this.animator, this.head_neck, 7.83F, 0.0F, -13.04F);
         this.rotate(this.animator, this.arm_right_2, 31.31F, 0.0F, 0.0F);
         this.animator.move(this.arm_left_1, 0.0F, -0.6F, 0.0F);
         this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, -7.83F);
         this.animator.move(this.arm_right_1, 0.0F, -0.5F, 0.0F);
         this.rotate(this.animator, this.arm_right_1, -46.96F, 0.0F, -5.21F);
         this.animator.endKeyframe();
         this.animator.startKeyframe(9);
         this.rotate(this.animator, this.body_front, 0.0F, 0.0F, -13.05F);
         this.rotate(this.animator, this.head_neck, 7.83F, 0.0F, 26.08F);
         this.rotate(this.animator, this.arm_right_2, 31.31F, 0.0F, 0.0F);
         this.animator.move(this.arm_left_1, 0.0F, 0.5F, 0.0F);
         this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, 13.04F);
         this.animator.move(this.arm_right_1, 0.0F, 0.5F, 0.0F);
         this.rotate(this.animator, this.arm_right_1, 54.79F, 0.0F, 10.43F);
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityRhino.ATTACK_GORE);
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_neck, 31.31F, 0.0F, 26.08F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.rotate(this.animator, this.head_neck, -26.08F, 0.0F, -46.96F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(4);
   }

   public void setupAnim(EntityRhino rhino, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(rhino);
      float globalSpeed = 1.5F;
      float globalDegree = 1.0F;
      float f = limbSwing / 2.0F;
      limbSwingAmount = Math.min(0.4F, limbSwingAmount);
      this.body_belly
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.body_front
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.bob(this.body_belly, 0.4F * globalSpeed, 0.1F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_neck, 0.4F * globalSpeed, 0.03F, false, 2.8F, 0.06F, ageInTicks / 20.0F, 2.0F);
      if (!rhino.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-2.0F, -2.0F, -4.0F);
         this.eye_left.setRotationPoint(2.0F, -2.0F, -4.0F);
      }

      if (!rhino.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_face});
      }

      if (rhino.canMove()) {
         this.bob(this.body_belly, 0.8F * globalSpeed, 0.6F * globalDegree, true, f, limbSwingAmount);
         this.walk(this.head_neck, 0.8F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, f, limbSwingAmount);
         this.walk(this.head_face, 0.8F * globalSpeed, 0.15F * globalDegree, true, 0.0F, 0.0F, f, limbSwingAmount);
         this.walk(this.arm_right_1, -0.8F * globalSpeed, 1.4F * globalDegree, true, 0.0F, 1.4F, f, limbSwingAmount);
         this.walk(this.arm_right_2, -0.8F * globalSpeed, 1.4F * globalDegree, false, -1.0F, 1.4F, f, limbSwingAmount * 1.2F);
         this.walk(this.arm_left_1, -0.8F * globalSpeed, 1.4F * globalDegree, true, 2.0F, 1.4F, f, limbSwingAmount);
         this.walk(this.arm_left_2, -0.8F * globalSpeed, 1.4F * globalDegree, false, 1.0F, 1.4F, f, limbSwingAmount * 1.2F);
         this.walk(this.leg_right, 0.8F * globalSpeed, 1.4F * globalDegree, false, 2.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_right_2, 0.8F * globalSpeed, 1.4F * globalDegree, true, 1.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_left, 0.8F * globalSpeed, 1.4F * globalDegree, false, 0.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_left_2, 0.8F * globalSpeed, 1.4F * globalDegree, true, -0.2F, 0.0F, f, limbSwingAmount);
      }

      if (rhino.sitProgress > 0) {
         this.progressPosition(this.body_belly, (float)rhino.sitProgress, 0.0F, 17.5F, 0.0F, 40.0F);
         this.progressRotation(this.body_belly, (float)rhino.sitProgress, 0.0F, 0.0F, (float)Math.toRadians(7.83F), 40.0F);
         this.progressPosition(this.arm_right_1, (float)rhino.sitProgress, -6.0F, 6.0F, -9.0F, 40.0F);
         this.progressRotation(
            this.arm_right_1, (float)rhino.sitProgress, (float)Math.toRadians(-104.3F), (float)Math.toRadians(23.48F), (float)Math.toRadians(-96.52F), 40.0F
         );
         this.progressRotation(this.arm_right_2, (float)rhino.sitProgress, (float)Math.toRadians(80.87F), 0.0F, (float)Math.toRadians(5.22F), 40.0F);
         this.progressPosition(this.arm_left_1, (float)rhino.sitProgress, 6.0F, 6.0F, -9.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)rhino.sitProgress, (float)Math.toRadians(-96.52F), 0.0F, (float)Math.toRadians(5.22F), 40.0F);
         this.progressRotation(this.arm_left_2, (float)rhino.sitProgress, (float)Math.toRadians(86.09F), 0.0F, (float)Math.toRadians(-5.22F), 40.0F);
         this.progressPosition(this.leg_right, (float)rhino.sitProgress, -6.0F, 6.0F, 15.0F, 40.0F);
         this.progressRotation(
            this.leg_right, (float)rhino.sitProgress, (float)Math.toRadians(-106.9F), (float)Math.toRadians(13.04F), (float)Math.toRadians(-2.61F), 40.0F
         );
         this.progressRotation(this.leg_right_2, (float)rhino.sitProgress, (float)Math.toRadians(15.65F), 0.0F, (float)Math.toRadians(5.22F), 40.0F);
         this.progressRotation(
            this.leg_left, (float)rhino.sitProgress, (float)Math.toRadians(-13.04F), (float)Math.toRadians(5.22F), (float)Math.toRadians(88.7F), 40.0F
         );
      }

      if (rhino.sleepProgress > 0) {
         this.progressPosition(this.body_belly, (float)rhino.sleepProgress, 0.0F, 17.5F, 0.0F, 40.0F);
         this.progressRotation(this.body_belly, (float)rhino.sleepProgress, 0.0F, 0.0F, (float)Math.toRadians(7.83F), 40.0F);
         this.progressPosition(this.arm_right_1, (float)rhino.sleepProgress, -6.0F, 6.0F, -9.0F, 40.0F);
         this.progressRotation(
            this.arm_right_1, (float)rhino.sleepProgress, (float)Math.toRadians(-104.3F), (float)Math.toRadians(23.48F), (float)Math.toRadians(-96.52F), 40.0F
         );
         this.progressRotation(this.arm_right_2, (float)rhino.sleepProgress, (float)Math.toRadians(80.87F), 0.0F, (float)Math.toRadians(5.22F), 40.0F);
         this.progressPosition(this.arm_left_1, (float)rhino.sleepProgress, 6.0F, 6.0F, -9.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)rhino.sleepProgress, (float)Math.toRadians(-96.52F), 0.0F, (float)Math.toRadians(5.22F), 40.0F);
         this.progressRotation(this.arm_left_2, (float)rhino.sleepProgress, (float)Math.toRadians(86.09F), 0.0F, (float)Math.toRadians(-5.22F), 40.0F);
         this.progressPosition(this.leg_right, (float)rhino.sleepProgress, -6.0F, 6.0F, 15.0F, 40.0F);
         this.progressRotation(
            this.leg_right, (float)rhino.sleepProgress, (float)Math.toRadians(-106.9F), (float)Math.toRadians(13.04F), (float)Math.toRadians(-2.61F), 40.0F
         );
         this.progressRotation(this.leg_right_2, (float)rhino.sleepProgress, (float)Math.toRadians(15.65F), 0.0F, (float)Math.toRadians(5.22F), 40.0F);
         this.progressRotation(
            this.leg_left, (float)rhino.sleepProgress, (float)Math.toRadians(-13.04F), (float)Math.toRadians(5.22F), (float)Math.toRadians(88.7F), 40.0F
         );
      }
   }
}

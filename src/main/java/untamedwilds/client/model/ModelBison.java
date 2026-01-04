package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBison;

public class ModelBison extends AdvancedEntityModel<EntityBison> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox body_torso;
   private final AdvancedModelBox leg_left_thigh;
   private final AdvancedModelBox leg_right_thigh;
   private final AdvancedModelBox body_hair;
   private final AdvancedModelBox tail;
   private final AdvancedModelBox arm_right_1;
   private final AdvancedModelBox arm_left_1;
   private final AdvancedModelBox head_neck;
   private final AdvancedModelBox arm_right_2;
   private final AdvancedModelBox arm_right_fur;
   private final AdvancedModelBox arm_left_2;
   private final AdvancedModelBox arm_left_fur;
   private final AdvancedModelBox head_main;
   private final AdvancedModelBox head_hair;
   private final AdvancedModelBox head_horn_right;
   private final AdvancedModelBox head_ear_right;
   private final AdvancedModelBox head_ear_left;
   private final AdvancedModelBox head_horn_left;
   private final AdvancedModelBox head_beard;
   private final AdvancedModelBox eye_left;
   private final AdvancedModelBox eye_right;
   private final AdvancedModelBox leg_left_calf;
   private final AdvancedModelBox leg_right_calf;
   private final ModelAnimator animator;

   public ModelBison() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.body_torso = new AdvancedModelBox(this, 64, 0);
      this.body_torso.setRotationPoint(0.0F, 0.0F, -8.0F);
      this.body_torso.addBox(-6.0F, -7.0F, -6.0F, 12.0F, 14.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.body_torso, 0.31869712F, 0.0F, 0.0F);
      this.arm_left_1 = new AdvancedModelBox(this, 0, 0);
      this.arm_left_1.setRotationPoint(4.5F, 2.2F, -2.0F);
      this.arm_left_1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, -0.18203785F, 0.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 0, 12);
      this.arm_left_2.setRotationPoint(0.0F, 6.3F, 0.0F);
      this.arm_left_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, -0.13665928F, 0.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 0, 12);
      this.arm_right_2.mirror = true;
      this.arm_right_2.setRotationPoint(0.0F, 6.3F, 0.0F);
      this.arm_right_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, -0.13665928F, 0.0F, 0.0F);
      this.head_horn_right = new AdvancedModelBox(this, 0, 47);
      this.head_horn_right.mirror = true;
      this.head_horn_right.setRotationPoint(-2.0F, -3.0F, -2.5F);
      this.head_horn_right.addBox(-4.0F, -1.0F, 0.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_horn_right, 0.18203785F, -0.18203785F, 0.3642502F);
      this.leg_left_calf = new AdvancedModelBox(this, 86, 26);
      this.leg_left_calf.setRotationPoint(0.7F, 3.5F, 2.5F);
      this.leg_left_calf.addBox(-1.0F, 0.0F, -1.0F, 3.0F, 10.0F, 3.0F, 0.0F);
      this.head_horn_left = new AdvancedModelBox(this, 0, 47);
      this.head_horn_left.setRotationPoint(2.0F, -3.0F, -2.5F);
      this.head_horn_left.addBox(0.0F, -1.0F, 0.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_horn_left, 0.18203785F, 0.18203785F, -0.3642502F);
      this.head_beard = new AdvancedModelBox(this, 22, 44);
      this.head_beard.setRotationPoint(0.0F, 3.0F, -0.5F);
      this.head_beard.addBox(-2.0F, 0.0F, -1.5F, 4.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_beard, 0.18203785F, 0.0F, 0.0F);
      this.body_hair = new AdvancedModelBox(this, 0, 36);
      this.body_hair.setRotationPoint(0.0F, 5.3F, -0.4F);
      this.body_hair.addBox(0.0F, 0.0F, -12.0F, 0.0F, 4.0F, 24.0F, 0.0F);
      this.setRotateAngle(this.body_hair, 0.091106184F, 0.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 0, 30);
      this.eye_right.setRotationPoint(-2.51F, 0.0F, -2.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 10.0F, 6.0F);
      this.body_main.addBox(-5.5F, -6.0F, -10.0F, 11.0F, 12.0F, 20.0F, 0.0F);
      this.leg_right_thigh = new AdvancedModelBox(this, 62, 26);
      this.leg_right_thigh.mirror = true;
      this.leg_right_thigh.setRotationPoint(-4.0F, 0.5F, 7.5F);
      this.leg_right_thigh.addBox(-3.0F, -3.5F, -3.5F, 5.0F, 10.0F, 7.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 8, 47);
      this.head_neck.setRotationPoint(0.0F, -1.0F, -3.5F);
      this.head_neck.addBox(-2.0F, -3.5F, -6.0F, 4.0F, 7.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.22759093F, 0.0F, 0.0F);
      this.leg_right_calf = new AdvancedModelBox(this, 86, 26);
      this.leg_right_calf.mirror = true;
      this.leg_right_calf.setRotationPoint(-0.7F, 3.5F, 2.5F);
      this.leg_right_calf.addBox(-2.0F, 0.0F, -1.0F, 3.0F, 10.0F, 3.0F, 0.0F);
      this.head_ear_right = new AdvancedModelBox(this, 18, 32);
      this.head_ear_right.setRotationPoint(-3.5F, -1.3F, -0.4F);
      this.head_ear_right.addBox(-2.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_right, 0.18203785F, 0.18203785F, -0.13665928F);
      this.head_main = new AdvancedModelBox(this, 0, 32);
      this.head_main.setRotationPoint(0.0F, 2.0F, -5.0F);
      this.head_main.addBox(-2.5F, -4.5F, -5.0F, 5.0F, 9.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_main, -0.3642502F, 0.0F, 0.0F);
      this.head_hair = new AdvancedModelBox(this, 22, 32);
      this.head_hair.setRotationPoint(0.0F, -2.4F, -2.4F);
      this.head_hair.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.head_hair, 0.22759093F, 0.0F, 0.0F);
      this.arm_right_1 = new AdvancedModelBox(this, 0, 0);
      this.arm_right_1.mirror = true;
      this.arm_right_1.setRotationPoint(-4.5F, 2.2F, -2.0F);
      this.arm_right_1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, -0.18203785F, 0.0F, 0.0F);
      this.arm_left_fur = new AdvancedModelBox(this, 42, 0);
      this.arm_left_fur.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.arm_left_fur.addBox(-2.5F, -0.1F, -3.0F, 5.0F, 9.0F, 6.0F, 0.0F);
      this.leg_left_thigh = new AdvancedModelBox(this, 62, 26);
      this.leg_left_thigh.setRotationPoint(4.0F, 0.5F, 7.5F);
      this.leg_left_thigh.addBox(-2.0F, -3.5F, -3.5F, 5.0F, 10.0F, 7.0F, 0.0F);
      this.tail = new AdvancedModelBox(this, 64, 0);
      this.tail.setRotationPoint(0.0F, -6.0F, 10.0F);
      this.tail.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 10.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.tail, 0.18203785F, 0.0F, 0.0F);
      this.head_ear_left = new AdvancedModelBox(this, 18, 32);
      this.head_ear_left.mirror = true;
      this.head_ear_left.setRotationPoint(3.5F, -1.3F, -0.4F);
      this.head_ear_left.addBox(-1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_left, 0.18203785F, -0.18203785F, 0.13665928F);
      this.eye_left = new AdvancedModelBox(this, 0, 30);
      this.eye_left.setRotationPoint(2.51F, 0.0F, -2.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.arm_right_fur = new AdvancedModelBox(this, 42, 0);
      this.arm_right_fur.mirror = true;
      this.arm_right_fur.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.arm_right_fur.addBox(-2.5F, -0.1F, -3.0F, 5.0F, 9.0F, 6.0F, 0.0F);
      this.body_main.addChild(this.body_torso);
      this.body_torso.addChild(this.arm_left_1);
      this.arm_left_1.addChild(this.arm_left_2);
      this.arm_right_1.addChild(this.arm_right_2);
      this.head_main.addChild(this.head_horn_right);
      this.leg_left_thigh.addChild(this.leg_left_calf);
      this.head_main.addChild(this.head_horn_left);
      this.head_main.addChild(this.head_beard);
      this.body_main.addChild(this.body_hair);
      this.head_main.addChild(this.eye_right);
      this.body_main.addChild(this.leg_right_thigh);
      this.body_torso.addChild(this.head_neck);
      this.leg_right_thigh.addChild(this.leg_right_calf);
      this.head_main.addChild(this.head_ear_right);
      this.head_neck.addChild(this.head_main);
      this.head_main.addChild(this.head_hair);
      this.body_torso.addChild(this.arm_right_1);
      this.arm_left_1.addChild(this.arm_left_fur);
      this.body_main.addChild(this.leg_left_thigh);
      this.body_main.addChild(this.tail);
      this.head_main.addChild(this.head_ear_left);
      this.head_main.addChild(this.eye_left);
      this.arm_right_1.addChild(this.arm_right_fur);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.body_torso,
         this.leg_left_thigh,
         this.leg_right_thigh,
         this.body_hair,
         this.tail,
         this.arm_right_1,
         this.arm_left_1,
         this.head_neck,
         this.arm_right_2,
         this.arm_right_fur,
         this.arm_left_2,
         new AdvancedModelBox[]{
            this.arm_left_fur,
            this.head_main,
            this.head_hair,
            this.head_horn_right,
            this.head_ear_right,
            this.head_ear_left,
            this.head_horn_left,
            this.head_beard,
            this.eye_left,
            this.eye_right,
            this.leg_left_calf,
            this.leg_right_calf
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityBison bison = (EntityBison)entityIn;
      this.animator.update(bison);
      this.animator.setAnimation(EntityBison.ATTACK_THREATEN);

      for (int i = 0; i < 2; i++) {
         this.animator.startKeyframe(12);
         this.rotate(this.animator, this.body_torso, 0.0F, 0.0F, 7.83F);
         this.rotate(this.animator, this.head_neck, 7.83F, 0.0F, -13.04F);
         this.rotate(this.animator, this.arm_right_2, 31.31F, 0.0F, 0.0F);
         this.animator.move(this.arm_left_1, 0.0F, -0.6F, 0.0F);
         this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, -7.83F);
         this.animator.move(this.arm_right_1, 0.0F, -0.5F, 0.0F);
         this.rotate(this.animator, this.arm_right_1, -46.96F, 0.0F, -5.21F);
         this.animator.endKeyframe();
         this.animator.startKeyframe(9);
         this.rotate(this.animator, this.body_torso, 0.0F, 0.0F, -13.05F);
         this.rotate(this.animator, this.head_neck, 7.83F, 0.0F, 26.08F);
         this.rotate(this.animator, this.arm_right_2, 31.31F, 0.0F, 0.0F);
         this.animator.move(this.arm_left_1, 0.0F, 0.5F, 0.0F);
         this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, 13.04F);
         this.animator.move(this.arm_right_1, 0.0F, 0.5F, 0.0F);
         this.rotate(this.animator, this.arm_right_1, 54.79F, 0.0F, 10.43F);
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityBison.ATTACK_GORE);
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_neck, 31.31F, 0.0F, 26.08F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.rotate(this.animator, this.head_neck, -26.08F, 0.0F, -46.96F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(4);
   }

   public void setupAnim(EntityBison bison, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(bison);
      float globalSpeed = 1.5F;
      float globalDegree = 1.0F;
      float f = limbSwing / 2.0F;
      limbSwingAmount = Math.min(0.4F, limbSwingAmount);
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.body_torso
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.1F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_thigh, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_thigh, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_neck, 0.4F * globalSpeed, 0.03F, false, 2.8F, 0.06F, ageInTicks / 20.0F, 2.0F);
      if (!bison.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-2.0F, -2.0F, -4.0F);
         this.eye_left.setRotationPoint(2.0F, -2.0F, -4.0F);
      }

      if (!bison.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (bison.isInWater()) {
         this.setRotateAngle(this.head_main, -0.18203785F, 0.0F, 0.0F);
         if (!bison.onGround()) {
            f = ageInTicks / 6.0F;
            limbSwingAmount = 0.5F;
            float pitch = Mth.clamp(bison.getXRot() - 10.0F, -25.0F, 25.0F);
            this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
         }
      }

      if (bison.canMove()) {
         this.bob(this.body_main, 0.8F * globalSpeed, 0.6F * globalDegree, true, f, limbSwingAmount);
         this.walk(this.head_neck, 0.8F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, f, limbSwingAmount);
         this.walk(this.head_main, 0.8F * globalSpeed, 0.15F * globalDegree, true, 0.0F, 0.0F, f, limbSwingAmount);
         this.walk(this.arm_right_1, -0.8F * globalSpeed, 1.4F * globalDegree, true, 0.0F, 1.4F, f, limbSwingAmount);
         this.walk(this.arm_right_2, -0.8F * globalSpeed, 1.4F * globalDegree, false, -1.0F, 1.4F, f, limbSwingAmount * 1.2F);
         this.walk(this.arm_left_1, -0.8F * globalSpeed, 1.4F * globalDegree, true, 2.0F, 1.4F, f, limbSwingAmount);
         this.walk(this.arm_left_2, -0.8F * globalSpeed, 1.4F * globalDegree, false, 1.0F, 1.4F, f, limbSwingAmount * 1.2F);
         this.walk(this.leg_right_thigh, 0.8F * globalSpeed, 1.4F * globalDegree, false, 2.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_right_calf, 0.8F * globalSpeed, 1.4F * globalDegree, true, 1.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_left_thigh, 0.8F * globalSpeed, 1.4F * globalDegree, false, 0.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_left_calf, 0.8F * globalSpeed, 1.4F * globalDegree, true, -0.2F, 0.0F, f, limbSwingAmount);
      }

      if (bison.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)bison.sitProgress, 0.0F, 17.5F, 0.0F, 40.0F);
         this.progressRotation(this.head_neck, (float)bison.sitProgress, (float)Math.toRadians(-33.91F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_main, (float)bison.sitProgress, (float)Math.toRadians(-2.61F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_1, (float)bison.sitProgress, -4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)bison.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)bison.sitProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(-15.65F), 40.0F);
         this.progressPosition(this.arm_left_1, (float)bison.sitProgress, 4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)bison.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)bison.sitProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(15.65F), 40.0F);
         this.progressRotation(this.leg_right_thigh, (float)bison.sitProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf, (float)bison.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(-10.43F), 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)bison.sitProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(-15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf, (float)bison.sitProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(10.43F), 40.0F);
      } else if (bison.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)bison.sleepProgress, 0.0F, 17.5F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_1, (float)bison.sleepProgress, -4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)bison.sleepProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)bison.sleepProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(-15.65F), 40.0F);
         this.progressPosition(this.arm_left_1, (float)bison.sleepProgress, 4.5F, 0.2F, -2.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)bison.sleepProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)bison.sleepProgress, (float)Math.toRadians(135.65F), 0.0F, (float)Math.toRadians(15.65F), 40.0F);
         this.progressRotation(this.leg_right_thigh, (float)bison.sleepProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_calf, (float)bison.sleepProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(-10.43F), 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)bison.sleepProgress, (float)Math.toRadians(-73.04F), (float)Math.toRadians(-15.65F), 0.0F, 40.0F);
         this.progressRotation(this.leg_left_calf, (float)bison.sleepProgress, (float)Math.toRadians(-10.43F), 0.0F, (float)Math.toRadians(10.43F), 40.0F);
      }
   }
}

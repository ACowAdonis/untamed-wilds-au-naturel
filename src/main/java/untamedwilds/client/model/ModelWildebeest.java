package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.mammal.EntityWildebeest;

public class ModelWildebeest extends AdvancedEntityModel<EntityWildebeest> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox leg_right_thigh;
   public AdvancedModelBox leg_left_thigh;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox body_back;
   public AdvancedModelBox leg_right_calf;
   public AdvancedModelBox leg_left_calf;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox body_tail_hair;
   public AdvancedModelBox head_main;
   public AdvancedModelBox shape15;
   public AdvancedModelBox shape84;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox head_ear_right;
   public AdvancedModelBox head_ear_left;
   public AdvancedModelBox head_eye_right;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox head_horn_left;
   public AdvancedModelBox head_horn_right;
   private final ModelAnimator animator;

   public ModelWildebeest() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.head_jaw = new AdvancedModelBox(this, 14, 46);
      this.head_jaw.setRotationPoint(0.0F, 0.0F, -5.0F);
      this.head_jaw.addBox(-2.0F, 0.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F);
      this.head_eye_right = new AdvancedModelBox(this, 14, 34);
      this.head_eye_right.setRotationPoint(-2.01F, -1.5F, -2.0F);
      this.head_eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.leg_right_thigh = new AdvancedModelBox(this, 42, 0);
      this.leg_right_thigh.setRotationPoint(-1.3F, 1.5F, 7.6F);
      this.leg_right_thigh.addBox(-2.0F, -2.5F, -2.5F, 2.0F, 7.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_right_thigh, -4.3404586E-16F, 0.0F, 0.0F);
      this.head_ear_right = new AdvancedModelBox(this, 14, 38);
      this.head_ear_right.setRotationPoint(-2.0F, 0.0F, 0.0F);
      this.head_ear_right.addBox(-3.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_ear_right, 0.7740535F, -0.31869712F, -0.045553092F);
      this.arm_right_1 = new AdvancedModelBox(this, 46, 12);
      this.arm_right_1.setRotationPoint(-2.0F, 1.5F, -4.5F);
      this.arm_right_1.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 54, 12);
      this.arm_right_2.setRotationPoint(-0.01F, 4.5F, 0.01F);
      this.arm_right_2.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 38, 22);
      this.head_neck.setRotationPoint(0.0F, -3.0F, -7.0F);
      this.head_neck.addBox(-1.5F, -2.0F, -5.0F, 3.0F, 6.0F, 9.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.4098033F, 0.0F, 0.0F);
      this.leg_left_thigh = new AdvancedModelBox(this, 42, 0);
      this.leg_left_thigh.mirror = true;
      this.leg_left_thigh.setRotationPoint(1.3F, 1.5F, 7.6F);
      this.leg_left_thigh.addBox(0.0F, -2.5F, -2.5F, 2.0F, 7.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_left_thigh, -3.7203932E-16F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 0, 46);
      this.head_snout.setRotationPoint(0.0F, -2.0F, -5.0F);
      this.head_snout.addBox(-2.0F, -1.0F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F);
      this.head_horn_left = new AdvancedModelBox(this, 18, 0);
      this.head_horn_left.setRotationPoint(1.0F, -2.0F, -0.5F);
      this.head_horn_left.addBox(0.0F, -1.0F, 0.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_horn_left, 0.045553092F, 0.091106184F, -0.3642502F);
      this.body_tail = new AdvancedModelBox(this, 31, 0);
      this.body_tail.setRotationPoint(0.0F, -2.0F, 9.5F);
      this.body_tail.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.body_tail, 0.18203785F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 36);
      this.head_main.setRotationPoint(0.0F, -1.0F, -4.0F);
      this.head_main.addBox(-2.0F, -3.0F, -5.0F, 4.0F, 4.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_main, 1.3658947F, 0.0F, 0.0F);
      this.body_back = new AdvancedModelBox(this, 0, 17);
      this.body_back.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body_back.addBox(-2.5F, -4.0F, 1.0F, 5.0F, 8.0F, 9.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 14, 34);
      this.eye_left.mirror = true;
      this.eye_left.setRotationPoint(2.01F, -1.5F, -2.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.leg_left_calf = new AdvancedModelBox(this, 56, 0);
      this.leg_left_calf.mirror = true;
      this.leg_left_calf.setRotationPoint(0.9F, 2.5F, 2.0F);
      this.leg_left_calf.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left_calf, 6.200655E-17F, 0.0F, 0.0F);
      this.head_horn_right = new AdvancedModelBox(this, 18, 0);
      this.head_horn_right.mirror = true;
      this.head_horn_right.setRotationPoint(-1.0F, -2.0F, -0.5F);
      this.head_horn_right.addBox(-4.0F, -1.0F, 0.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_horn_right, 0.045553092F, -0.091106184F, 0.3642502F);
      this.arm_left_1 = new AdvancedModelBox(this, 46, 12);
      this.arm_left_1.mirror = true;
      this.arm_left_1.setRotationPoint(2.0F, 1.5F, -4.5F);
      this.arm_left_1.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, -6.200655E-17F, 0.0F, 0.0F);
      this.body_tail_hair = new AdvancedModelBox(this, 30, 5);
      this.body_tail_hair.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.body_tail_hair.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F);
      this.shape15 = new AdvancedModelBox(this, 32, 37);
      this.shape15.setRotationPoint(0.0F, -2.0F, -1.0F);
      this.shape15.addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 10.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 54, 12);
      this.arm_left_2.mirror = true;
      this.arm_left_2.setRotationPoint(0.01F, 4.5F, 0.01F);
      this.arm_left_2.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F);
      this.head_ear_left = new AdvancedModelBox(this, 14, 38);
      this.head_ear_left.mirror = true;
      this.head_ear_left.setRotationPoint(2.0F, 0.0F, 0.0F);
      this.head_ear_left.addBox(0.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_ear_left, 0.7740535F, 0.31869712F, 0.045553092F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 10.0F, 0.0F);
      this.body_main.addBox(-2.5F, -5.0F, -7.0F, 5.0F, 9.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_main, 4.960524E-16F, 0.0F, 0.0F);
      this.leg_right_calf = new AdvancedModelBox(this, 56, 0);
      this.leg_right_calf.setRotationPoint(-0.9F, 2.5F, 2.0F);
      this.leg_right_calf.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right_calf, 6.200655E-17F, 0.0F, 0.0F);
      this.shape84 = new AdvancedModelBox(this, 24, 30);
      this.shape84.setRotationPoint(0.0F, 3.5F, -2.5F);
      this.shape84.addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.shape84, 0.18203785F, 0.0F, 0.0F);
      this.head_main.addChild(this.head_jaw);
      this.head_main.addChild(this.head_eye_right);
      this.body_main.addChild(this.leg_right_thigh);
      this.head_main.addChild(this.head_ear_right);
      this.body_main.addChild(this.arm_right_1);
      this.arm_right_1.addChild(this.arm_right_2);
      this.body_main.addChild(this.head_neck);
      this.body_main.addChild(this.leg_left_thigh);
      this.head_main.addChild(this.head_snout);
      this.head_main.addChild(this.head_horn_left);
      this.body_main.addChild(this.body_tail);
      this.head_neck.addChild(this.head_main);
      this.body_main.addChild(this.body_back);
      this.head_main.addChild(this.eye_left);
      this.leg_left_thigh.addChild(this.leg_left_calf);
      this.head_main.addChild(this.head_horn_right);
      this.body_main.addChild(this.arm_left_1);
      this.body_tail.addChild(this.body_tail_hair);
      this.head_neck.addChild(this.shape15);
      this.arm_left_1.addChild(this.arm_left_2);
      this.head_main.addChild(this.head_ear_left);
      this.leg_right_thigh.addChild(this.leg_right_calf);
      this.head_neck.addChild(this.shape84);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.leg_right_thigh,
         this.leg_left_thigh,
         this.arm_right_1,
         this.arm_left_1,
         this.body_tail,
         this.head_neck,
         this.body_back,
         this.leg_right_calf,
         this.leg_left_calf,
         this.arm_right_2,
         this.arm_left_2,
         new AdvancedModelBox[]{
            this.body_tail_hair,
            this.head_main,
            this.shape15,
            this.shape84,
            this.head_snout,
            this.head_jaw,
            this.head_ear_right,
            this.head_ear_left,
            this.head_eye_right,
            this.eye_left,
            this.head_horn_left,
            this.head_horn_right
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      this.animator.update(entityIn);
      this.animator.setAnimation(EntityWildebeest.IDLE_TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_neck, -5.22F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityWildebeest.IDLE_SHAKE);
      this.animator.startKeyframe(8);
      this.animator.move(this.head_neck, 0.0F, 0.0F, -1.0F);
      this.rotate(this.animator, this.head_neck, 5.22F, 28.7F, -15.65F);
      this.rotate(this.animator, this.head_main, 20.87F, -20.87F, -15.65F);
      this.animator.endKeyframe();
      int head_offset = -1;

      for (int i = 0; i < 6; i++) {
         this.animator.startKeyframe(4);
         this.rotate(this.animator, this.head_neck, 5.22F, 28.7F * (float)head_offset, -15.65F * (float)head_offset);
         this.rotate(this.animator, this.head_main, 20.87F, -20.87F * (float)head_offset, -15.65F * (float)head_offset);
         head_offset *= -1;
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityWildebeest.HOP);
      this.animator.startKeyframe(4);
      this.animator.move(this.body_main, 0.0F, 0.0F, 3.0F);
      this.rotate(this.animator, this.body_main, -20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 20.87F, 0.0F, 0.0F);
      this.animator.move(this.leg_right_thigh, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, -7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_calf, 13.04F, 0.0F, 0.0F);
      this.animator.move(this.leg_left_thigh, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, -7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_calf, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, -62.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, 78.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -33.91F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, 65.22F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.animator.move(this.body_main, 0.0F, -6.0F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, 20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_calf, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, 20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_calf, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, -62.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -62.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.body_tail, 62.61F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.animator.move(this.body_main, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.body_main, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, -18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_calf, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, -18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_calf, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, -62.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -62.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, 13.04F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(3);
      this.animator.setAnimation(EntityWildebeest.EAT);
      this.animator.startKeyframe(10);
      this.animator.move(this.body_main, 0.0F, 2.3F, 0.0F);
      this.rotate(this.animator, this.body_main, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, -26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, -26.09F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, 0.0F, 15.65F);
      this.rotate(this.animator, this.arm_right_2, -41.74F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, -15.65F);
      this.rotate(this.animator, this.arm_left_2, -41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(8);
      this.animator.move(this.body_main, 0.0F, 2.3F, 0.0F);
      this.rotate(this.animator, this.body_main, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 65.21F, 10.43F, 0.0F);
      this.rotate(this.animator, this.head_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, -26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, -26.09F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, 0.0F, 15.65F);
      this.rotate(this.animator, this.arm_right_2, -41.74F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, -15.65F);
      this.rotate(this.animator, this.arm_left_2, -41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, 2.3F, 0.0F);
      this.rotate(this.animator, this.body_main, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 65.21F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 0.0F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, -26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, -26.09F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, 0.0F, 15.65F);
      this.rotate(this.animator, this.arm_right_2, -41.74F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, -15.65F);
      this.rotate(this.animator, this.arm_left_2, -41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, 2.3F, 0.0F);
      this.rotate(this.animator, this.body_main, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 65.21F, -10.43F, 0.0F);
      this.rotate(this.animator, this.head_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_thigh, -26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_thigh, -26.09F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, 0.0F, 15.65F);
      this.rotate(this.animator, this.arm_right_2, -41.74F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_1, 0.0F, -2.0F, 1.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 0.0F, -15.65F);
      this.rotate(this.animator, this.arm_left_2, -41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
   }

   public void setupAnim(EntityWildebeest wildebeest, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(wildebeest);
      float globalSpeed = 2.0F;
      float globalDegree = 1.0F;
      limbSwingAmount *= 4.0F;
      if (limbSwingAmount > 0.3F) {
         limbSwingAmount = 0.3F;
      }

      this.head_neck.scaleX = 0.9F;
      if (!wildebeest.shouldRenderEyes()) {
         this.head_eye_right.setRotationPoint(-1.0F, -1.5F, -2.0F);
         this.eye_left.setRotationPoint(1.0F, -1.5F, -2.0F);
      }

      if (!wildebeest.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_main});
      }

      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_back
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_thigh, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_thigh, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_neck, 0.4F * globalSpeed, 0.03F * globalDegree, false, 0.0F, 0.0F, ageInTicks / 20.0F, 2.0F);
      if (wildebeest.canMove()) {
         this.walk(this.arm_right_1, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.arm_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
         this.bob(this.arm_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.arm_left_1, 0.5F * globalSpeed, globalDegree, true, 1.6F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.arm_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.6F, 0.2F, limbSwing, limbSwingAmount);
         this.bob(this.leg_right_thigh, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_thigh, 0.5F * globalSpeed, globalDegree, true, 0.2F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_calf, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.4F, 0.2F, limbSwing, limbSwingAmount);
         this.bob(this.leg_left_thigh, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_thigh, 0.5F * globalSpeed, globalDegree, true, 1.8F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_calf, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.8F, 0.2F, limbSwing, limbSwingAmount);
         this.walk(this.head_neck, 0.6F * globalSpeed, 0.4F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.head_main, 0.6F * globalSpeed, 0.3F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.bob(this.body_main, 1.0F * globalSpeed, 0.3F * globalDegree, true, limbSwing, limbSwingAmount);
      }

      if (wildebeest.sitProgress != 0) {
         this.progressPosition(this.body_main, (float)wildebeest.sitProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressRotation(this.body_main, (float)wildebeest.sitProgress, 0.0F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_thigh, (float)wildebeest.sitProgress, -1.57F, 0.14F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)wildebeest.sitProgress, -1.57F, -0.14F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)wildebeest.sitProgress, -1.04F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)wildebeest.sitProgress, 2.62F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)wildebeest.sitProgress, -1.04F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)wildebeest.sitProgress, 2.62F, 0.0F, 0.0F, 40.0F);
      }

      if (wildebeest.sleepProgress != 0) {
         this.progressPosition(this.body_main, (float)wildebeest.sleepProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressRotation(this.body_main, (float)wildebeest.sleepProgress, 0.0F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_thigh, (float)wildebeest.sleepProgress, -1.57F, 0.14F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_thigh, (float)wildebeest.sleepProgress, -1.57F, -0.14F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)wildebeest.sleepProgress, -1.04F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_2, (float)wildebeest.sleepProgress, 2.62F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)wildebeest.sleepProgress, -1.04F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_2, (float)wildebeest.sleepProgress, 2.62F, 0.0F, 0.0F, 40.0F);
      }
   }
}

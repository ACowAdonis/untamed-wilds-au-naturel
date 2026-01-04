package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityAardvark;

public class ModelAardvark extends AdvancedEntityModel<EntityAardvark> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox body_booty;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox hair;
   public AdvancedModelBox head_head;
   public AdvancedModelBox head_ear_left;
   public AdvancedModelBox head_ear_right;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox leg_left_1;
   public AdvancedModelBox body_tail_1;
   public AdvancedModelBox leg_right_1;
   public AdvancedModelBox leg_left_2;
   public AdvancedModelBox body_tail_2;
   public AdvancedModelBox body_tail_3;
   public AdvancedModelBox leg_right_2;
   public AdvancedModelBox arm_right_2;
   private final ModelAnimator animator;

   public ModelAardvark() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.leg_right_1 = new AdvancedModelBox(this, 46, 15);
      this.leg_right_1.mirror = true;
      this.leg_right_1.setRotationPoint(0.0F, -0.2F, 5.6F);
      this.leg_right_1.addBox(-4.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_1, -0.13665928F, 0.0F, 0.13962634F);
      this.body_tail_2 = new AdvancedModelBox(this, 14, 17);
      this.body_tail_2.setRotationPoint(0.0F, 0.0F, 3.5F);
      this.body_tail_2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.body_tail_2, -0.22759093F, 0.0F, 0.0F);
      this.body_booty = new AdvancedModelBox(this, 28, 0);
      this.body_booty.setRotationPoint(0.0F, 0.0F, 2.0F);
      this.body_booty.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 6.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_booty, -0.4553564F, 0.0F, 0.0F);
      this.body_tail_3 = new AdvancedModelBox(this, 14, 17);
      this.body_tail_3.setRotationPoint(0.0F, 0.4F, 5.3F);
      this.body_tail_3.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.body_tail_3, 0.8651597F, 0.0F, 0.0F);
      this.body_tail_3.scaleX = 0.7F;
      this.leg_left_1 = new AdvancedModelBox(this, 46, 15);
      this.leg_left_1.setRotationPoint(0.0F, -0.2F, 5.6F);
      this.leg_left_1.addBox(0.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_1, -0.13665928F, 0.0F, -0.13962634F);
      this.leg_right_2 = new AdvancedModelBox(this, 46, 24);
      this.leg_right_2.mirror = true;
      this.leg_right_2.setRotationPoint(-2.5F, 3.1F, 1.2F);
      this.leg_right_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, 0.22759093F, 0.0F, -0.13962634F);
      this.head_ear_left = new AdvancedModelBox(this, 0, 21);
      this.head_ear_left.setRotationPoint(1.0F, -1.0F, -1.0F);
      this.head_ear_left.addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_left, -0.091106184F, -0.091106184F, 0.8651597F);
      this.arm_right_2 = new AdvancedModelBox(this, 30, 23);
      this.arm_right_2.mirror = true;
      this.arm_right_2.setRotationPoint(0.0F, 3.5F, 0.01F);
      this.arm_right_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, 0.0F, 0.0F, -0.13665928F);
      this.head_head = new AdvancedModelBox(this, 2, 22);
      this.head_head.setRotationPoint(0.0F, -1.0F, -2.5F);
      this.head_head.addBox(-1.5F, -2.0F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_head, 0.8196066F, 0.0F, 0.0F);
      this.body_tail_1 = new AdvancedModelBox(this, 0, 13);
      this.body_tail_1.setRotationPoint(0.0F, -1.0F, 7.0F);
      this.body_tail_1.addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.body_tail_1, -0.5462881F, 0.0F, 0.0F);
      this.hair = new AdvancedModelBox(this, 34, 18);
      this.hair.setRotationPoint(0.0F, 2.2F, 4.0F);
      this.hair.addBox(0.0F, 0.0F, -6.0F, 0.0F, 2.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.hair, -0.31869712F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 16, 26);
      this.head_snout.setRotationPoint(0.0F, -1.0F, -4.0F);
      this.head_snout.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.arm_right_1 = new AdvancedModelBox(this, 28, 15);
      this.arm_right_1.mirror = true;
      this.arm_right_1.setRotationPoint(-1.5F, -0.75F, 0.2F);
      this.arm_right_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, -0.3642502F, 0.0F, 0.13665928F);
      this.eye_right = new AdvancedModelBox(this, 0, 27);
      this.eye_right.mirror = true;
      this.eye_right.setRotationPoint(-1.51F, -1.0F, -3.0F);
      this.eye_right.addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 27);
      this.eye_left.setRotationPoint(1.51F, -1.0F, -3.0F);
      this.eye_left.addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 20, 0);
      this.head_neck.setRotationPoint(0.0F, -1.0F, -1.0F);
      this.head_neck.addBox(-1.5F, -2.0F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.5462881F, 0.0F, 0.0F);
      this.head_neck.scaleX = 0.99F;
      this.arm_left_2 = new AdvancedModelBox(this, 30, 23);
      this.arm_left_2.setRotationPoint(0.0F, 3.5F, 0.01F);
      this.arm_left_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, 0.0F, 0.0F, 0.13665928F);
      this.leg_left_2 = new AdvancedModelBox(this, 46, 24);
      this.leg_left_2.setRotationPoint(2.5F, 3.1F, 1.2F);
      this.leg_left_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, 0.22759093F, 0.0F, 0.13962634F);
      this.arm_left_1 = new AdvancedModelBox(this, 28, 15);
      this.arm_left_1.setRotationPoint(1.5F, -0.75F, 0.2F);
      this.arm_left_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, -0.3642502F, 0.0F, -0.13665928F);
      this.head_ear_right = new AdvancedModelBox(this, 0, 21);
      this.head_ear_right.mirror = true;
      this.head_ear_right.setRotationPoint(-1.0F, -1.0F, -1.0F);
      this.head_ear_right.addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_right, -0.091106184F, 0.091106184F, -0.8651597F);
      this.body_main = new AdvancedModelBox(this, 0, 1);
      this.body_main.setRotationPoint(0.0F, 17.6F, -5.0F);
      this.body_main.addBox(-2.5F, -3.3F, -2.0F, 5.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.body_main, 0.3642502F, 0.0F, 0.0F);
      this.body_booty.addChild(this.leg_right_1);
      this.body_tail_1.addChild(this.body_tail_2);
      this.head_head.addChild(this.eye_right);
      this.body_main.addChild(this.body_booty);
      this.body_tail_2.addChild(this.body_tail_3);
      this.body_booty.addChild(this.leg_left_1);
      this.leg_right_1.addChild(this.leg_right_2);
      this.head_head.addChild(this.head_ear_left);
      this.arm_right_1.addChild(this.arm_right_2);
      this.head_neck.addChild(this.head_head);
      this.body_booty.addChild(this.body_tail_1);
      this.body_main.addChild(this.hair);
      this.head_head.addChild(this.head_snout);
      this.body_main.addChild(this.arm_right_1);
      this.head_head.addChild(this.eye_left);
      this.body_main.addChild(this.head_neck);
      this.arm_left_1.addChild(this.arm_left_2);
      this.leg_left_1.addChild(this.leg_left_2);
      this.body_main.addChild(this.arm_left_1);
      this.head_head.addChild(this.head_ear_right);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_neck,
         this.arm_left_1,
         this.body_booty,
         this.arm_right_1,
         this.hair,
         this.head_head,
         this.head_ear_left,
         this.head_ear_right,
         this.head_snout,
         this.eye_left,
         this.eye_right,
         new AdvancedModelBox[]{
            this.arm_left_2,
            this.leg_left_1,
            this.body_tail_1,
            this.leg_right_1,
            this.leg_left_2,
            this.body_tail_2,
            this.body_tail_3,
            this.leg_right_2,
            this.arm_right_2
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      this.animator.update(entityIn);
      this.animator.setAnimation(EntityAardvark.WORK_DIG);
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, 0.0F, 1.5F);
      this.rotate(this.animator, this.body_main, 31.3F, 0.0F, 0.0F);
      this.animator.move(this.body_booty, 0.0F, -0.2F, -0.3F);
      this.rotate(this.animator, this.body_booty, -39.13F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -46.96F, -10.43F, -18.26F);
      this.animator.endKeyframe();

      for (int i = 0; i < 10; i++) {
         AdvancedModelBox limb = i % 2 == 0 ? this.arm_right_1 : this.arm_left_1;
         this.animator.startKeyframe(6);
         this.animator.move(this.body_main, 0.0F, 0.0F, 1.5F);
         this.rotate(this.animator, this.body_main, 31.3F, 0.0F, 0.0F);
         this.animator.move(this.body_booty, 0.0F, -0.2F, -0.3F);
         this.rotate(this.animator, this.body_booty, -39.13F, 0.0F, 0.0F);
         this.rotate(this.animator, this.head_neck, 7.83F, 0.0F, 0.0F);
         this.rotate(this.animator, limb, -46.96F, -10.43F, -18.26F);
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityAardvark.ATTACK);
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, -4.6F, 0.0F);
      this.rotate(this.animator, this.body_main, -26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.body_booty, -33.91F, 0.0F, 0.0F);
      this.animator.move(this.leg_right_1, 0.0F, -1.0F, -1.0F);
      this.rotate(this.animator, this.leg_right_1, 46.96F, 0.0F, 0.0F);
      this.animator.move(this.leg_left_1, 0.0F, -1.0F, -1.0F);
      this.rotate(this.animator, this.leg_left_1, 46.96F, 0.0F, 0.0F);
      this.animator.move(this.body_tail_1, 0.0F, -1.0F, -2.0F);
      this.rotate(this.animator, this.body_tail_1, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -46.96F, -10.43F, -18.26F);
      this.animator.endKeyframe();

      for (int i = 0; i < 3; i++) {
         AdvancedModelBox limb = i % 2 == 0 ? this.arm_right_1 : this.arm_left_1;
         this.animator.startKeyframe(3);
         this.animator.move(this.body_main, 0.0F, -4.6F, 0.0F);
         this.rotate(this.animator, this.body_main, -26.09F, 0.0F, 0.0F);
         this.rotate(this.animator, this.body_booty, -33.91F, 0.0F, 0.0F);
         this.animator.move(this.leg_right_1, 0.0F, -1.0F, -1.0F);
         this.rotate(this.animator, this.leg_right_1, 46.96F, 0.0F, 0.0F);
         this.animator.move(this.leg_left_1, 0.0F, -1.0F, -1.0F);
         this.rotate(this.animator, this.leg_left_1, 46.96F, 0.0F, 0.0F);
         this.animator.move(this.body_tail_1, 0.0F, -1.0F, -2.0F);
         this.rotate(this.animator, this.body_tail_1, 23.48F, 0.0F, 0.0F);
         this.rotate(this.animator, limb, -46.96F, -10.43F, -18.26F);
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(3);
   }

   public void setupAnim(EntityAardvark aardvark, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(aardvark);
      float globalSpeed = 1.0F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(limbSwingAmount, 0.4F);
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_booty
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.1F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_neck, 0.4F * globalSpeed, 0.03F, false, 2.4F, 0.08F, ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_head, 0.4F * globalSpeed, 0.03F, false, 2.8F, 0.06F, ageInTicks / 20.0F, 2.0F);
      this.head_snout
         .setScale(
            (float)(1.0 + Math.sin((double)(ageInTicks / 6.0F)) * 0.08F + Math.sin((double)(ageInTicks / 2.0F)) * 0.1F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 8.0F)) * 0.04F),
            1.0F
         );
      if (!aardvark.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-0.51F, -1.0F, -3.0F);
         this.eye_left.setRotationPoint(0.51F, -1.0F, -3.0F);
      }

      if (!aardvark.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_head});
      }

      if (aardvark.isInWater() && !aardvark.onGround()) {
         float pitch = Mth.clamp(aardvark.getXRot(), -20.0F, 20.0F) - 10.0F;
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (aardvark.canMove()) {
         if (!(aardvark.getCurrentSpeed() > 0.1F) && !aardvark.isAngry()) {
            this.bob(this.arm_right_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_1, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_1, 0.5F * globalSpeed, globalDegree, true, 2.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.6F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_1, 0.5F * globalSpeed, globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_1, 0.5F * globalSpeed, globalDegree, true, 3.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 3.6F, 0.2F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.body_main, 0.5F * globalSpeed, 0.5F, false, limbSwing, limbSwingAmount);
            this.walk(this.body_main, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_neck, 0.5F * globalSpeed, -0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.body_booty, 0.5F * globalSpeed, 0.3F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_right_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_1, 0.5F * globalSpeed, 1.0F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_1, 0.5F * globalSpeed, 1.0F * globalDegree, true, 0.6F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.8F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_1, 0.5F * globalSpeed, 1.0F * globalDegree, true, 1.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.6F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_1, 0.5F * globalSpeed, 1.0F * globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.2F, 0.2F, limbSwing, limbSwingAmount);
         }
      }

      if (aardvark.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)aardvark.sleepProgress, -3.0F, 21.0F, -5.0F, 40.0F);
         this.progressRotation(this.body_main, (float)aardvark.sleepProgress, (float)Math.toRadians(20.87F), 0.0F, (float)Math.toRadians(-91.3), 40.0F);
         this.progressRotation(this.head_neck, (float)aardvark.sleepProgress, (float)Math.toRadians(26.09), (float)Math.toRadians(15.65), 0.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)aardvark.sleepProgress, (float)Math.toRadians(5.22), 0.0F, (float)Math.toRadians(23.48), 40.0F);
         this.progressRotation(this.leg_left_1, (float)aardvark.sleepProgress, (float)Math.toRadians(10.43), 0.0F, (float)Math.toRadians(36.52), 40.0F);
         this.progressRotation(this.body_tail_1, (float)aardvark.sleepProgress, (float)Math.toRadians(-31.3), 0.0F, (float)Math.toRadians(23.48), 40.0F);
         this.progressRotation(this.body_tail_2, (float)aardvark.sleepProgress, (float)Math.toRadians(-39.13), 0.0F, (float)Math.toRadians(-10.43), 40.0F);
         this.progressRotation(this.body_tail_3, (float)aardvark.sleepProgress, (float)Math.toRadians(-52.17), 0.0F, (float)Math.toRadians(23.48), 40.0F);
      } else {
         this.flap(this.body_tail_1, 0.4F * globalSpeed, 0.2F * globalDegree, true, 0.0F, 0.0F, ageInTicks / 6.0F, 2.0F);
         this.flap(this.body_tail_2, 0.4F * globalSpeed, 0.2F * globalDegree, true, 0.5F, 0.0F, ageInTicks / 6.0F, 2.0F);
         this.flap(this.body_tail_3, 0.4F * globalSpeed, 0.2F * globalDegree, true, 1.0F, 0.0F, ageInTicks / 6.0F, 2.0F);
      }
   }
}

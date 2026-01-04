package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBoar;

public class ModelBoar extends AdvancedEntityModel<EntityBoar> {
   public AdvancedModelBox main_body;
   public AdvancedModelBox head_main;
   public AdvancedModelBox leg_left_1;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox shape14;
   public AdvancedModelBox shape15;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox leg_right_1;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox ear_left;
   public AdvancedModelBox head_mouth;
   public AdvancedModelBox ear_right;
   public AdvancedModelBox tusk_left;
   public AdvancedModelBox tusk_left_1;
   public AdvancedModelBox leg_left_2;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox leg_right_2;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;
   private final ModelAnimator animator;

   public ModelBoar() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.shape14 = new AdvancedModelBox(this, 6, 0);
      this.shape14.setRotationPoint(0.0F, -5.0F, 6.0F);
      this.shape14.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.shape14, 0.18203785F, 0.0F, 0.0F);
      this.leg_right_1 = new AdvancedModelBox(this, 0, 36);
      this.leg_right_1.mirror = true;
      this.leg_right_1.setRotationPoint(-2.5F, -1.7F, 3.6F);
      this.leg_right_1.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_right_1, -0.13665928F, 0.0F, 0.13962634F);
      this.head_main = new AdvancedModelBox(this, 0, 22);
      this.head_main.setRotationPoint(0.0F, -0.8F, -8.0F);
      this.head_main.addBox(-2.5F, -4.5F, -4.0F, 5.0F, 7.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_main, 0.31869712F, 0.0F, 0.0F);
      this.tusk_left_1 = new AdvancedModelBox(this, 16, 24);
      this.tusk_left_1.setRotationPoint(-1.4F, 0.4F, -1.0F);
      this.tusk_left_1.addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.tusk_left_1, 0.0F, 0.006773208F, -0.3642502F);
      this.tusk_left = new AdvancedModelBox(this, 16, 24);
      this.tusk_left.setRotationPoint(1.4F, 0.4F, -1.0F);
      this.tusk_left.addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.tusk_left, 0.0F, 0.0F, 0.3642502F);
      this.arm_right_2 = new AdvancedModelBox(this, 56, 10);
      this.arm_right_2.mirror = true;
      this.arm_right_2.setRotationPoint(0.0F, 4.5F, 0.01F);
      this.arm_right_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, 0.0F, 0.0F, -0.13665928F);
      this.shape15 = new AdvancedModelBox(this, 34, 13);
      this.shape15.setRotationPoint(0.0F, -4.5F, -4.0F);
      this.shape15.addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.shape15, -0.045553092F, 0.0F, 0.0F);
      this.leg_right_2 = new AdvancedModelBox(this, 0, 47);
      this.leg_right_2.mirror = true;
      this.leg_right_2.setRotationPoint(0.4F, 3.7F, 2.0F);
      this.leg_right_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, 0.18203785F, 0.0F, -0.13962634F);
      this.head_snout = new AdvancedModelBox(this, 22, 22);
      this.head_snout.setRotationPoint(0.0F, -0.8F, -3.0F);
      this.head_snout.addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.4553564F, 0.0F, 0.0F);
      this.arm_right_1 = new AdvancedModelBox(this, 48, 0);
      this.arm_right_1.mirror = true;
      this.arm_right_1.setRotationPoint(-2.3F, -1.85F, -4.8F);
      this.arm_right_1.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, 0.045553092F, 0.0F, 0.13665928F);
      this.ear_right = new AdvancedModelBox(this, 16, 22);
      this.ear_right.mirror = true;
      this.ear_right.setRotationPoint(-1.1F, -3.4F, -0.3F);
      this.ear_right.addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, -0.22759093F, 0.091106184F, 1.0016445F);
      this.head_mouth = new AdvancedModelBox(this, 18, 30);
      this.head_mouth.setRotationPoint(0.0F, 1.2F, -3.5F);
      this.head_mouth.addBox(-1.5F, 0.0F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_mouth, 0.091106184F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 15.6F, 0.0F);
      this.main_body.addBox(-3.5F, -5.0F, -8.0F, 7.0F, 8.0F, 14.0F, 0.0F);
      this.setRotateAngle(this.main_body, -0.045553092F, 0.0F, 0.0F);
      this.leg_left_2 = new AdvancedModelBox(this, 0, 47);
      this.leg_left_2.setRotationPoint(-0.4F, 3.7F, 2.0F);
      this.leg_left_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, 0.18203785F, 0.0F, 0.13962634F);
      this.arm_left_1 = new AdvancedModelBox(this, 48, 0);
      this.arm_left_1.setRotationPoint(2.3F, -1.85F, -4.8F);
      this.arm_left_1.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, 0.045553092F, 0.0F, -0.13665928F);
      this.leg_left_1 = new AdvancedModelBox(this, 0, 36);
      this.leg_left_1.setRotationPoint(2.5F, -1.7F, 3.6F);
      this.leg_left_1.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_left_1, -0.13665928F, 0.0F, -0.13962634F);
      this.ear_left = new AdvancedModelBox(this, 16, 22);
      this.ear_left.setRotationPoint(1.1F, -3.4F, -0.3F);
      this.ear_left.addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, -0.22759093F, -0.091106184F, -1.0016445F);
      this.arm_left_2 = new AdvancedModelBox(this, 56, 10);
      this.arm_left_2.setRotationPoint(0.0F, 4.5F, 0.01F);
      this.arm_left_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, 0.0F, 0.0F, 0.13665928F);
      this.eye_right = new AdvancedModelBox(this, 0, 20);
      this.eye_right.mirror = true;
      this.eye_right.setRotationPoint(-2.51F, -2.0F, -2.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 20);
      this.eye_left.setRotationPoint(2.51F, -2.0F, -2.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.head_main.addChild(this.eye_left);
      this.head_main.addChild(this.eye_right);
      this.main_body.addChild(this.shape14);
      this.main_body.addChild(this.leg_right_1);
      this.main_body.addChild(this.head_main);
      this.head_mouth.addChild(this.tusk_left_1);
      this.head_mouth.addChild(this.tusk_left);
      this.arm_right_1.addChild(this.arm_right_2);
      this.main_body.addChild(this.shape15);
      this.leg_right_1.addChild(this.leg_right_2);
      this.head_main.addChild(this.head_snout);
      this.main_body.addChild(this.arm_right_1);
      this.head_main.addChild(this.ear_right);
      this.head_main.addChild(this.head_mouth);
      this.leg_left_1.addChild(this.leg_left_2);
      this.main_body.addChild(this.arm_left_1);
      this.main_body.addChild(this.leg_left_1);
      this.head_main.addChild(this.ear_left);
      this.arm_left_1.addChild(this.arm_left_2);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.head_main,
         this.leg_left_1,
         this.arm_left_1,
         this.shape14,
         this.shape15,
         this.arm_right_1,
         this.leg_right_1,
         this.head_snout,
         this.ear_left,
         this.head_mouth,
         this.ear_right,
         new AdvancedModelBox[]{
            this.tusk_left, this.tusk_left_1, this.leg_left_2, this.arm_left_2, this.arm_right_2, this.leg_right_2, this.eye_left, this.eye_right
         }
      );
   }

   void animate(IAnimatedEntity entityIn) {
      this.animator.update(entityIn);
      this.animator.setAnimation(EntityBoar.TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_mouth, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityBoar.ATTACK);
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_main, 31.31F, 0.0F, 26.08F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.rotate(this.animator, this.head_main, -26.08F, 0.0F, -46.96F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(4);
      this.animator.setAnimation(EntityBoar.WORK_DIG);
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.main_body, 13.04F, 0.0F, 0.0F);
      this.animator.move(this.head_main, 0.0F, 2.0F, 1.9F);
      this.rotate(this.animator, this.head_main, 44.35F, 10.43F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, 15.65F, 0.0F, -7.83F);
      this.rotate(this.animator, this.arm_left_2, -54.78F, 0.0F, 7.83F);
      this.rotate(this.animator, this.arm_right_1, 15.65F, 0.0F, 7.83F);
      this.rotate(this.animator, this.arm_right_2, -54.78F, 0.0F, -7.83F);
      this.rotate(this.animator, this.leg_left_1, -23.48F, 0.0F, -8.0F);
      this.animator.move(this.leg_left_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -23.48F, 0.0F, 8.0F);
      this.animator.move(this.leg_right_1, 0.0F, 1.0F, 0.0F);
      this.animator.endKeyframe();

      for (int i = 0; i < 4; i++) {
         float head_angle = i % 2 == 0 ? -10.43F : 10.43F;
         this.animator.startKeyframe(8);
         this.rotate(this.animator, this.main_body, 13.04F, 0.0F, 0.0F);
         this.animator.move(this.head_main, 0.0F, 2.0F, 1.9F);
         this.rotate(this.animator, this.head_main, 44.35F, head_angle, 0.0F);
         this.rotate(this.animator, this.arm_left_1, 15.65F, 0.0F, -7.83F);
         this.rotate(this.animator, this.arm_left_2, -54.78F, 0.0F, 7.83F);
         this.rotate(this.animator, this.arm_right_1, 15.65F, 0.0F, 7.83F);
         this.rotate(this.animator, this.arm_right_2, -54.78F, 0.0F, -7.83F);
         this.rotate(this.animator, this.leg_left_1, -23.48F, 0.0F, -8.0F);
         this.animator.move(this.leg_left_1, 0.0F, 1.0F, 0.0F);
         this.rotate(this.animator, this.leg_right_1, -23.48F, 0.0F, 8.0F);
         this.animator.move(this.leg_right_1, 0.0F, 1.0F, 0.0F);
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(10);
   }

   public void setupAnim(EntityBoar boar, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(boar);
      float globalSpeed = 1.0F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(limbSwingAmount, 0.4F);
      if (boar.isNoAi()) {
         limbSwing = ageInTicks;
         limbSwingAmount = 0.4F;
      }

      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.main_body, 0.4F * globalSpeed, 0.1F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_1, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_main, 0.4F * globalSpeed, 0.03F, false, 2.4F, 0.08F, ageInTicks / 20.0F, 2.0F);
      if (boar.tickCount % 30 > 22) {
         this.head_snout
            .setScale(
               (float)(1.0 + Math.sin((double)(ageInTicks / 6.0F)) * 0.1F + Math.sin((double)(ageInTicks / 2.0F)) * 0.1F),
               (float)(1.0 + Math.sin((double)(ageInTicks / 8.0F)) * 0.06F),
               1.0F
            );
      }

      if (boar.tickCount % 100 > 88) {
         this.swing(this.ear_left, 1.5F * globalSpeed, 0.6F * globalDegree, true, 0.0F, 0.0F, ageInTicks / 2.2F, limbSwingAmount);
         this.swing(this.ear_right, 1.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.0F, ageInTicks / 2.2F, limbSwingAmount);
      }

      if (!boar.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-0.51F, -1.0F, -2.0F);
         this.eye_left.setRotationPoint(0.51F, -1.0F, -2.0F);
      }

      if (!boar.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (boar.isInWater() && !boar.onGround()) {
         float pitch = Mth.clamp(boar.getXRot(), -20.0F, 20.0F) - 10.0F;
         this.setRotateAngle(this.main_body, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (boar.canMove()) {
         if (!(boar.getCurrentSpeed() > 0.1F) && !boar.isAngry()) {
            this.bob(this.main_body, 0.5F * globalSpeed, 0.8F, true, limbSwing, limbSwingAmount);
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
            this.flap(this.shape14, 0.5F * globalSpeed, 1.4F * globalDegree, false, 0.0F, 0.2F, limbSwing, limbSwingAmount);
            this.swing(this.shape14, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.0F, 0.2F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.main_body, 0.5F * globalSpeed, 0.8F, true, limbSwing, limbSwingAmount);
            this.walk(this.main_body, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_main, 0.5F * globalSpeed, -0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.head_main, 0.25F * globalSpeed, -0.3F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.head_main, 0.5F * globalSpeed, 0.8F, true, limbSwing, limbSwingAmount);
            this.bob(this.arm_right_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_1, 0.5F * globalSpeed, 1.4F * globalDegree, true, 0.0F, 0.4F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_2, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0.2F, 0.6F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_1, 0.5F * globalSpeed, 1.4F * globalDegree, true, 0.6F, 0.4F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_2, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0.8F, 0.6F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_1, 0.5F * globalSpeed, 1.0F * globalDegree, true, 1.4F, -0.4F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.6F, -0.6F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_1, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_1, 0.5F * globalSpeed, 1.0F * globalDegree, true, 2.0F, -0.4F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.2F, -0.6F, limbSwing, limbSwingAmount);
            this.flap(this.shape14, 0.5F * globalSpeed, 1.4F * globalDegree, false, 0.0F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.shape14, 0.5F * globalSpeed, 1.8F * globalDegree, false, 1.0F, 1.8F, limbSwing, limbSwingAmount);
         }
      }

      if (boar.sitProgress > 0) {
         this.progressPosition(this.main_body, (float)boar.sitProgress, 0.0F, 21.6F, 0.0F, 40.0F);
         this.progressRotation(this.main_body, (float)boar.sitProgress, (float)Math.toRadians(-2.61F), (float)Math.toRadians(5.22F), 0.0F, 40.0F);
         this.progressRotation(this.head_main, (float)boar.sitProgress, 0.0F, (float)Math.toRadians(-23.48F), 0.0F, 40.0F);
         this.progressRotation(this.arm_right_1, (float)boar.sitProgress, (float)Math.toRadians(-20.87F), 0.0F, (float)Math.toRadians(7.83F), 40.0F);
         this.progressRotation(this.arm_right_2, (float)boar.sitProgress, (float)Math.toRadians(-65.22F), 0.0F, (float)Math.toRadians(-7.83F), 40.0F);
         this.progressRotation(
            this.arm_left_1, (float)boar.sitProgress, (float)Math.toRadians(-26.09F), (float)Math.toRadians(20.87F), (float)Math.toRadians(-7.83F), 40.0F
         );
         this.progressRotation(this.arm_left_2, (float)boar.sitProgress, (float)Math.toRadians(-57.39F), 0.0F, (float)Math.toRadians(7.83F), 40.0F);
         this.progressRotation(
            this.leg_left_2, (float)boar.sitProgress, (float)Math.toRadians(-83.48F), (float)Math.toRadians(-20.87F), (float)Math.toRadians(8.0), 40.0F
         );
         this.progressRotation(
            this.leg_right_2, (float)boar.sitProgress, (float)Math.toRadians(-83.48F), (float)Math.toRadians(20.87F), (float)Math.toRadians(-8.0), 40.0F
         );
      }

      if (boar.sleepProgress > 0) {
         this.progressPosition(this.main_body, (float)boar.sleepProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressRotation(this.main_body, (float)boar.sleepProgress, (float)Math.toRadians(20.87F), 0.0F, (float)Math.toRadians(-91.3), 40.0F);
         this.progressRotation(this.head_main, (float)boar.sleepProgress, (float)Math.toRadians(26.09), (float)Math.toRadians(15.65), 0.0F, 40.0F);
         this.progressRotation(this.arm_left_1, (float)boar.sleepProgress, (float)Math.toRadians(5.22), 0.0F, (float)Math.toRadians(23.48), 40.0F);
         this.progressRotation(this.leg_left_1, (float)boar.sleepProgress, (float)Math.toRadians(10.43), 0.0F, (float)Math.toRadians(36.52), 40.0F);
      }
   }
}

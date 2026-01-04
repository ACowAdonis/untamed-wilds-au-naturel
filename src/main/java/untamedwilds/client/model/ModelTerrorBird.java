package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.bird.EntityTerrorBird;

public class ModelTerrorBird extends AdvancedEntityModel<EntityTerrorBird> {
   public AdvancedModelBox main_body;
   public AdvancedModelBox leg_left_1;
   public AdvancedModelBox neck_1;
   public AdvancedModelBox wing_left;
   public AdvancedModelBox leg_right_1;
   public AdvancedModelBox wing_right;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox leg_left_2;
   public AdvancedModelBox leg_left_3;
   public AdvancedModelBox neck_2;
   public AdvancedModelBox head_main;
   public AdvancedModelBox head_beak;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox head_feathers;
   public AdvancedModelBox head_beak_tip;
   public AdvancedModelBox wing_left_feathers;
   public AdvancedModelBox leg_right_2;
   public AdvancedModelBox leg_right_3;
   public AdvancedModelBox wing_right_feathers;
   public AdvancedModelBox tail_feathers;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;
   private final ModelAnimator animator;

   public ModelTerrorBird() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.neck_2 = new AdvancedModelBox(this, 16, 20);
      this.neck_2.setRotationPoint(0.0F, -4.5F, -0.7F);
      this.neck_2.addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.neck_2, -0.7740535F, 0.0F, 0.0F);
      this.neck_2.scaleX = 1.01F;
      this.leg_left_2 = new AdvancedModelBox(this, 54, 6);
      this.leg_left_2.mirror = true;
      this.leg_left_2.setRotationPoint(0.0F, 3.0F, 2.5F);
      this.leg_left_2.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 10.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, -0.18203785F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 8.0F, 0.0F);
      this.main_body.addBox(-3.5F, -4.5F, -6.0F, 7.0F, 8.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.main_body, -0.18203785F, 0.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 24, 42);
      this.head_jaw.setRotationPoint(0.0F, 0.0F, -3.0F);
      this.head_jaw.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, 0.0F);
      this.wing_left_feathers = new AdvancedModelBox(this, 36, -9);
      this.wing_left_feathers.mirror = true;
      this.wing_left_feathers.setRotationPoint(1.01F, -1.0F, 0.0F);
      this.wing_left_feathers.addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 9.0F, 0.0F);
      this.leg_right_2 = new AdvancedModelBox(this, 54, 6);
      this.leg_right_2.setRotationPoint(0.0F, 3.0F, 2.5F);
      this.leg_right_2.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 10.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, -0.18203785F, 0.0F, 0.0F);
      this.tail_feathers = new AdvancedModelBox(this, 0, 50);
      this.tail_feathers.setRotationPoint(0.0F, 1.0F, 3.0F);
      this.tail_feathers.addBox(-4.0F, -2.0F, 0.0F, 8.0F, 4.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.tail_feathers, -0.13665928F, 0.0F, 0.0F);
      this.leg_right_3 = new AdvancedModelBox(this, 42, 18);
      this.leg_right_3.setRotationPoint(0.0F, 9.5F, 0.0F);
      this.leg_right_3.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_right_3, 0.27314404F, 0.0F, 0.0F);
      this.body_tail = new AdvancedModelBox(this, 40, 24);
      this.body_tail.setRotationPoint(0.0F, -1.5F, 5.0F);
      this.body_tail.addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.body_tail, -0.22759093F, 0.0F, 0.0F);
      this.wing_right_feathers = new AdvancedModelBox(this, 36, -9);
      this.wing_right_feathers.setRotationPoint(-1.01F, -1.0F, 0.0F);
      this.wing_right_feathers.addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 9.0F, 0.0F);
      this.head_beak = new AdvancedModelBox(this, 24, 32);
      this.head_beak.setRotationPoint(0.0F, -4.0F, -1.0F);
      this.head_beak.addBox(-1.5F, 0.0F, -7.0F, 3.0F, 4.0F, 5.0F, 0.0F);
      this.head_feathers = new AdvancedModelBox(this, 40, 40);
      this.head_feathers.setRotationPoint(0.0F, -0.3F, 0.5F);
      this.head_feathers.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_feathers, 0.3642502F, 0.0F, 0.0F);
      this.leg_left_3 = new AdvancedModelBox(this, 42, 18);
      this.leg_left_3.mirror = true;
      this.leg_left_3.setRotationPoint(0.0F, 9.5F, 0.0F);
      this.leg_left_3.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_left_3, 0.27314404F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 30);
      this.head_main.setRotationPoint(0.0F, -6.0F, -0.5F);
      this.head_main.addBox(-2.5F, -4.0F, -3.0F, 5.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_main, -0.091106184F, 0.0F, 0.0F);
      this.head_beak_tip = new AdvancedModelBox(this, 15, 42);
      this.head_beak_tip.setRotationPoint(0.0F, 0.0F, -7.0F);
      this.head_beak_tip.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, 0.0F);
      this.neck_1 = new AdvancedModelBox(this, 0, 20);
      this.neck_1.setRotationPoint(0.0F, -1.0F, -4.6F);
      this.neck_1.addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.neck_1, 1.1383038F, 0.0F, 0.0F);
      this.leg_right_1 = new AdvancedModelBox(this, 38, 6);
      this.leg_right_1.setRotationPoint(-2.5F, 2.0F, 4.0F);
      this.leg_right_1.addBox(-1.5F, -2.0F, -2.0F, 3.0F, 7.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_right_1, 0.091106184F, 0.0F, 0.0F);
      this.wing_right = new AdvancedModelBox(this, 26, 0);
      this.wing_right.setRotationPoint(-3.0F, -3.0F, -4.0F);
      this.wing_right.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.wing_right, 0.0F, -0.045553092F, 0.0F);
      this.leg_left_1 = new AdvancedModelBox(this, 38, 6);
      this.leg_left_1.mirror = true;
      this.leg_left_1.setRotationPoint(2.5F, 2.0F, 4.0F);
      this.leg_left_1.addBox(-1.5F, -2.0F, -2.0F, 3.0F, 7.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_left_1, 0.091106184F, 0.0F, 0.0F);
      this.wing_left = new AdvancedModelBox(this, 26, 0);
      this.wing_left.mirror = true;
      this.wing_left.setRotationPoint(3.0F, -3.0F, -4.0F);
      this.wing_left.addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.wing_left, 0.0F, 0.045553092F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 28);
      this.eye_left.setRotationPoint(2.51F, -3.0F, -1.0F);
      this.eye_left.addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.eye_left, 0.0F, 0.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 0, 28);
      this.eye_right.setRotationPoint(-2.51F, -3.0F, -1.0F);
      this.eye_right.addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.eye_right, 0.0F, 0.0F, 0.0F);
      this.neck_1.addChild(this.neck_2);
      this.leg_left_1.addChild(this.leg_left_2);
      this.head_main.addChild(this.head_jaw);
      this.wing_left.addChild(this.wing_left_feathers);
      this.leg_right_1.addChild(this.leg_right_2);
      this.body_tail.addChild(this.tail_feathers);
      this.leg_right_2.addChild(this.leg_right_3);
      this.main_body.addChild(this.body_tail);
      this.wing_right.addChild(this.wing_right_feathers);
      this.head_main.addChild(this.head_beak);
      this.head_main.addChild(this.head_feathers);
      this.leg_left_2.addChild(this.leg_left_3);
      this.neck_2.addChild(this.head_main);
      this.head_beak.addChild(this.head_beak_tip);
      this.main_body.addChild(this.neck_1);
      this.main_body.addChild(this.leg_right_1);
      this.main_body.addChild(this.wing_right);
      this.main_body.addChild(this.leg_left_1);
      this.main_body.addChild(this.wing_left);
      this.head_main.addChild(this.eye_right);
      this.head_main.addChild(this.eye_left);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.leg_left_1,
         this.leg_left_2,
         this.leg_left_3,
         this.leg_right_1,
         this.leg_right_2,
         this.leg_right_3,
         this.neck_1,
         this.wing_left,
         this.wing_left_feathers,
         this.wing_right,
         this.wing_right_feathers,
         new AdvancedModelBox[]{
            this.body_tail,
            this.neck_2,
            this.head_main,
            this.head_beak,
            this.head_jaw,
            this.head_feathers,
            this.head_beak_tip,
            this.tail_feathers,
            this.eye_left,
            this.eye_right
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityTerrorBird big_cat = (EntityTerrorBird)entityIn;
      this.animator.update(big_cat);
      this.animator.setAnimation(EntityTerrorBird.IDLE_TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_jaw, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityTerrorBird.ATTACK_PECK);
      this.animator.startKeyframe(8);
      this.animator.move(this.main_body, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.main_body, -31.3F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, 33.91F, 0.0F, 18.26F);
      this.rotate(this.animator, this.neck_1, 54.78F, 28.7F, 0.0F);
      this.rotate(this.animator, this.neck_2, -44.35F, -31.3F, 5.22F);
      this.rotate(this.animator, this.wing_left, -20.87F, 104.35F, -20.87F);
      this.rotate(this.animator, this.wing_right, -20.87F, -104.35F, 20.87F);
      this.rotate(this.animator, this.leg_left_1, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, 26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.animator.move(this.main_body, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.main_body, 7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -75.65F, 0.0F, 18.26F);
      this.rotate(this.animator, this.neck_1, 83.48F, 28.7F, 0.0F);
      this.rotate(this.animator, this.neck_2, 7.83F, -31.3F, 5.22F);
      this.rotate(this.animator, this.wing_left, -20.87F, 104.35F, -20.87F);
      this.rotate(this.animator, this.wing_right, -20.87F, -104.35F, 20.87F);
      this.animator.move(this.leg_left_1, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.leg_left_1, -13.04F, 0.0F, 0.0F);
      this.animator.move(this.leg_right_1, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -13.04F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityTerrorBird.ATTACK_KICK);
      this.animator.startKeyframe(8);
      this.animator.move(this.main_body, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.main_body, -54.78F, 18.26F, 0.0F);
      this.rotate(this.animator, this.head_main, 18.26F, -31.3F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 44.35F, 0.0F, 0.0F);
      this.rotate(this.animator, this.neck_1, 101.74F, 0.0F, 0.0F);
      this.rotate(this.animator, this.wing_left, 99.13F, 46.96F, 99.13F);
      this.rotate(this.animator, this.wing_right, 99.13F, -46.96F, -99.13F);
      this.rotate(this.animator, this.leg_left_1, 52.17F, 0.0F, 0.0F);
      this.animator.move(this.leg_right_1, 0.0F, -2.0F, -2.0F);
      this.rotate(this.animator, this.leg_right_1, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_3, 125.22F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.animator.move(this.main_body, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.main_body, -39.13F, 18.26F, 0.0F);
      this.rotate(this.animator, this.head_main, 10.43F, -31.3F, 0.0F);
      this.rotate(this.animator, this.neck_1, 86.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.wing_left, 99.13F, 46.96F, 99.13F);
      this.rotate(this.animator, this.wing_right, 99.13F, -46.96F, -99.13F);
      this.rotate(this.animator, this.leg_left_1, 33.91F, 0.0F, 0.0F);
      this.animator.move(this.leg_right_1, 0.0F, 4.0F, 2.0F);
      this.rotate(this.animator, this.leg_right_1, 5.22F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_3, 54.78F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityTerrorBird.IDLE_GRAZE);
      this.animator.startKeyframe(8);
      this.animator.move(this.main_body, 0.0F, 2.2F, 0.0F);
      this.rotate(this.animator, this.main_body, 10.43F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_1, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.neck_1, 106.96F, 13.04F, 0.0F);
      this.rotate(this.animator, this.neck_2, -31.3F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.animator.move(this.main_body, 0.0F, 2.2F, 0.0F);
      this.rotate(this.animator, this.main_body, 10.43F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_1, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.neck_1, 88.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.neck_2, -31.3F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.animator.move(this.main_body, 0.0F, 2.2F, 0.0F);
      this.rotate(this.animator, this.main_body, 10.43F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_1, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.neck_1, 106.96F, -13.04F, 0.0F);
      this.rotate(this.animator, this.neck_2, -31.3F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
   }

   public void setupAnim(EntityTerrorBird terror_bird, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(terror_bird);
      float globalSpeed = 2.4F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount * 2.0F);
      limbSwing *= 0.5F;
      if (terror_bird.isNoAi()) {
         limbSwing = ageInTicks / 2.0F;
         limbSwingAmount = 0.4F;
      }

      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.main_body, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.neck_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!terror_bird.shouldRenderEyes()) {
         this.eye_left.setRotationPoint(-2.0F, -3.0F, -1.0F);
         this.eye_right.setRotationPoint(2.0F, -3.0F, -1.0F);
      }

      if (!terror_bird.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.neck_2});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (terror_bird.canMove()) {
         if (!(terror_bird.getCurrentSpeed() > 0.1F) && !terror_bird.isAngry()) {
            this.swing(this.wing_right, 0.5F * globalSpeed, globalDegree * 0.2F, true, 0.0F, 0.2F, limbSwing, limbSwingAmount);
            this.swing(this.wing_left, 0.5F * globalSpeed, globalDegree * 0.2F, true, 2.4F, -0.2F, limbSwing, limbSwingAmount);
            this.walk(this.neck_2, 0.2F * globalSpeed, globalDegree * 0.2F, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_main, 0.2F * globalSpeed, globalDegree * 0.2F, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.main_body, 0.5F * globalSpeed, 0.8F, true, limbSwing, limbSwingAmount);
            this.swing(this.wing_right, 0.5F * globalSpeed, globalDegree * 0.2F, true, 0.0F, 0.8F, limbSwing, limbSwingAmount);
            this.swing(this.wing_left, 0.5F * globalSpeed, globalDegree * 0.2F, true, 2.4F, -0.8F, limbSwing, limbSwingAmount);
            this.walk(this.neck_1, 0.4F * globalSpeed, globalDegree * 0.3F, true, 1.0F, -1.0F, limbSwing, limbSwingAmount);
            this.walk(this.neck_2, 0.4F * globalSpeed, globalDegree * 0.3F, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_main, 0.4F * globalSpeed, globalDegree * 0.6F, false, 1.0F, -1.0F, limbSwing, limbSwingAmount);
         }

         this.flap(this.main_body, 0.5F * globalSpeed, globalDegree * 0.2F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.neck_1, 0.5F * globalSpeed, globalDegree * 0.25F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.body_tail, 0.5F * globalSpeed, globalDegree * 0.25F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.bob(this.wing_right, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.bob(this.wing_left, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.bob(this.leg_left_1, 0.7F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_1, 0.5F * globalSpeed, globalDegree, true, 3.4F, 0.0F, limbSwing, limbSwingAmount);
         this.bob(this.leg_left_2, 0.7F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_2, 0.7F * globalSpeed, 0.6F * globalDegree, true, 3.6F, 0.2F, limbSwing, limbSwingAmount);
         this.walk(this.leg_left_3, 0.7F * globalSpeed, 2.0F * globalDegree, false, 3.8F, 1.0F, limbSwing, limbSwingAmount);
         this.bob(this.leg_right_1, 0.7F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_1, 0.5F * globalSpeed, globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
         this.bob(this.leg_right_2, 0.7F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_2, 0.7F * globalSpeed, 0.6F * globalDegree, true, 1.2F, 0.2F, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_3, 0.7F * globalSpeed, 2.0F * globalDegree, false, 1.4F, 1.0F, limbSwing, limbSwingAmount);
      }

      if (terror_bird.sitProgress > 0) {
         this.progressPosition(this.main_body, (float)terror_bird.sitProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressRotation(this.main_body, (float)terror_bird.sitProgress, (float)Math.toRadians(5.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.neck_1, (float)terror_bird.sitProgress, (float)Math.toRadians(54.78F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.leg_left_1, (float)terror_bird.sitProgress, 2.5F, -1.0F, 4.0F, 40.0F);
         this.progressRotation(this.leg_left_1, (float)terror_bird.sitProgress, (float)Math.toRadians(-10.43F), (float)Math.toRadians(-13.04F), 0.0F, 40.0F);
         this.progressRotation(this.leg_left_2, (float)terror_bird.sitProgress, (float)Math.toRadians(-85.0), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_3, (float)terror_bird.sitProgress, (float)Math.toRadians(90.0), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.leg_right_1, (float)terror_bird.sitProgress, -2.5F, -1.0F, 4.0F, 40.0F);
         this.progressRotation(this.leg_right_1, (float)terror_bird.sitProgress, (float)Math.toRadians(-10.43F), (float)Math.toRadians(13.04F), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_2, (float)terror_bird.sitProgress, (float)Math.toRadians(-85.0), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_3, (float)terror_bird.sitProgress, (float)Math.toRadians(90.0), 0.0F, 0.0F, 40.0F);
      }

      if (terror_bird.sleepProgress > 0) {
         this.progressPosition(this.main_body, (float)terror_bird.sleepProgress, 0.0F, 21.0F, 0.0F, 40.0F);
         this.progressRotation(this.main_body, (float)terror_bird.sitProgress, (float)Math.toRadians(5.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.neck_1, (float)terror_bird.sitProgress, (float)Math.toRadians(54.78F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.leg_left_1, (float)terror_bird.sitProgress, 2.5F, -1.0F, 4.0F, 40.0F);
         this.progressRotation(this.leg_left_1, (float)terror_bird.sitProgress, (float)Math.toRadians(-10.43F), (float)Math.toRadians(-13.04F), 0.0F, 40.0F);
         this.progressRotation(this.leg_left_2, (float)terror_bird.sitProgress, (float)Math.toRadians(-85.0), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_3, (float)terror_bird.sitProgress, (float)Math.toRadians(90.0), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.leg_right_1, (float)terror_bird.sitProgress, -2.5F, -1.0F, 4.0F, 40.0F);
         this.progressRotation(this.leg_right_1, (float)terror_bird.sitProgress, (float)Math.toRadians(-10.43F), (float)Math.toRadians(13.04F), 0.0F, 40.0F);
         this.progressRotation(this.leg_right_2, (float)terror_bird.sitProgress, (float)Math.toRadians(-85.0), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_3, (float)terror_bird.sitProgress, (float)Math.toRadians(90.0), 0.0F, 0.0F, 40.0F);
      }
   }
}

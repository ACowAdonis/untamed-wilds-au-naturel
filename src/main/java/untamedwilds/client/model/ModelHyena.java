package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityHyena;

public class ModelHyena extends AdvancedEntityModel<EntityHyena> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox arm_left_upper;
   public AdvancedModelBox leg_left_upper;
   public AdvancedModelBox tail_1;
   public AdvancedModelBox arm_right_upper;
   public AdvancedModelBox leg_right_upper;
   public AdvancedModelBox head_main;
   public AdvancedModelBox hair;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox ear_right;
   public AdvancedModelBox ear_left;
   public AdvancedModelBox arm_left_lower;
   public AdvancedModelBox leg_left_lower;
   public AdvancedModelBox arm_right_lower;
   public AdvancedModelBox leg_right_lower;
   private final ModelAnimator animator;
   private float tailX = -1.0F;

   public ModelHyena() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.leg_left_lower = new AdvancedModelBox(this, 48, 10);
      this.leg_left_lower.mirror = true;
      this.leg_left_lower.setRotationPoint(0.5F, 2.0F, 2.0F);
      this.leg_left_lower.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left_lower, 0.3642502F, 0.045553092F, 0.045553092F);
      this.leg_right_lower = new AdvancedModelBox(this, 48, 10);
      this.leg_right_lower.setRotationPoint(-0.5F, 2.0F, 2.0F);
      this.leg_right_lower.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right_lower, 0.3642502F, -0.045553092F, -0.045553092F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 16.1F, 1.0F);
      this.body_main.addBox(-3.0F, -5.0F, -6.0F, 6.0F, 7.0F, 11.0F, 0.0F);
      this.setRotateAngle(this.body_main, -0.13665928F, 0.0F, 0.0F);
      this.arm_left_upper = new AdvancedModelBox(this, 36, 11);
      this.arm_left_upper.mirror = true;
      this.arm_left_upper.setRotationPoint(2.0F, -2.7F, -3.2F);
      this.arm_left_upper.addBox(-1.0F, 0.0F, -2.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_upper, 0.045553092F, -0.0F, -0.091106184F);
      this.tail_1 = new AdvancedModelBox(this, 23, 0);
      this.tail_1.setRotationPoint(0.0F, -3.0F, 5.0F);
      this.tail_1.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.tail_1, -1.1383038F, 0.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 0, 22);
      this.eye_right.setRotationPoint(-2.51F, -2.0F, -3.01F);
      this.eye_right.addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.ear_left = new AdvancedModelBox(this, 0, 18);
      this.ear_left.mirror = true;
      this.ear_left.setRotationPoint(1.2F, -2.5F, -1.0F);
      this.ear_left.addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, 0.0F, -0.045553092F, 0.31869712F);
      this.arm_left_lower = new AdvancedModelBox(this, 38, 20);
      this.arm_left_lower.mirror = true;
      this.arm_left_lower.setRotationPoint(1.0F, 4.6F, -0.2F);
      this.arm_left_lower.addBox(-1.5F, 0.0F, -1.5F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left_lower, -0.091106184F, 0.0F, 0.045553092F);
      this.hair = new AdvancedModelBox(this, 24, 22);
      this.hair.setRotationPoint(0.0F, -1.0F, -2.7F);
      this.hair.addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 0, 21);
      this.head_neck.setRotationPoint(0.0F, -2.6F, -4.0F);
      this.head_neck.addBox(-2.0F, -1.6F, -6.0F, 4.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.59184116F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 0, 0);
      this.head_snout.setRotationPoint(0.0F, -1.2F, -2.0F);
      this.head_snout.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.13665928F, 0.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 0, 6);
      this.head_jaw.setRotationPoint(0.0F, 0.8F, -1.7F);
      this.head_jaw.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_jaw, 0.091106184F, 0.0F, 0.0F);
      this.leg_right_upper = new AdvancedModelBox(this, 48, 0);
      this.leg_right_upper.setRotationPoint(-2.0F, -2.2F, 4.4F);
      this.leg_right_upper.addBox(-2.0F, -1.0F, -3.0F, 3.0F, 5.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_right_upper, -0.27314404F, -0.0F, 0.045553092F);
      this.head_main = new AdvancedModelBox(this, 14, 18);
      this.head_main.setRotationPoint(0.0F, 1.1F, -4.5F);
      this.head_main.addBox(-2.5F, -3.0F, -3.0F, 5.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_main, 0.7740535F, 0.0F, 0.0F);
      this.ear_right = new AdvancedModelBox(this, 0, 18);
      this.ear_right.setRotationPoint(-1.2F, -2.5F, -1.0F);
      this.ear_right.addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, 0.0F, 0.045553092F, -0.31869712F);
      this.arm_right_lower = new AdvancedModelBox(this, 38, 20);
      this.arm_right_lower.setRotationPoint(-1.0F, 4.6F, -0.2F);
      this.arm_right_lower.addBox(-0.5F, 0.0F, -1.5F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_right_lower, -0.091106184F, 0.0F, -0.045553092F);
      this.arm_right_upper = new AdvancedModelBox(this, 36, 11);
      this.arm_right_upper.setRotationPoint(-2.0F, -2.7F, -3.2F);
      this.arm_right_upper.addBox(-2.0F, 0.0F, -2.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_upper, 0.045553092F, -0.0F, 0.091106184F);
      this.eye_left = new AdvancedModelBox(this, 0, 22);
      this.eye_left.mirror = true;
      this.eye_left.setRotationPoint(2.51F, -2.0F, -3.01F);
      this.eye_left.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.leg_left_upper = new AdvancedModelBox(this, 48, 0);
      this.leg_left_upper.mirror = true;
      this.leg_left_upper.setRotationPoint(2.0F, -2.2F, 4.4F);
      this.leg_left_upper.addBox(-1.0F, -1.0F, -3.0F, 3.0F, 5.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.leg_left_upper, -0.27314404F, -0.0F, -0.045553092F);
      this.leg_left_upper.addChild(this.leg_left_lower);
      this.leg_right_upper.addChild(this.leg_right_lower);
      this.body_main.addChild(this.arm_left_upper);
      this.body_main.addChild(this.tail_1);
      this.head_main.addChild(this.eye_right);
      this.head_main.addChild(this.ear_left);
      this.arm_left_upper.addChild(this.arm_left_lower);
      this.head_neck.addChild(this.hair);
      this.body_main.addChild(this.head_neck);
      this.head_main.addChild(this.head_snout);
      this.head_main.addChild(this.head_jaw);
      this.body_main.addChild(this.leg_right_upper);
      this.head_neck.addChild(this.head_main);
      this.head_main.addChild(this.ear_right);
      this.arm_right_upper.addChild(this.arm_right_lower);
      this.body_main.addChild(this.arm_right_upper);
      this.head_main.addChild(this.eye_left);
      this.body_main.addChild(this.leg_left_upper);
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
         this.arm_left_upper,
         this.leg_left_upper,
         this.tail_1,
         this.arm_right_upper,
         this.leg_right_upper,
         this.head_main,
         this.hair,
         this.eye_right,
         this.eye_left,
         this.head_snout,
         new AdvancedModelBox[]{
            this.head_jaw, this.ear_right, this.ear_left, this.arm_left_lower, this.leg_left_lower, this.arm_right_lower, this.leg_right_lower
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityHyena hyena = (EntityHyena)entityIn;
      this.animator.update(hyena);
      this.animator.setAnimation(EntityHyena.IDLE_TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_neck, -26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityHyena.ATTACK_BITE);
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_main, -10.43F, 15.65F, -20.87F);
      this.rotate(this.animator, this.head_jaw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 44.35F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_main, -18.26F, -5.22F, 10.43F);
      this.rotate(this.animator, this.head_jaw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 20.87F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(5);
      this.animator.setAnimation(EntityHyena.ATTACK_POUNCE);
      this.animator.startKeyframe(12);
      this.rotate(this.animator, this.body_main, -18.26F, 0.0F, 0.0F);
      this.animator.move(this.body_main, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 52.17F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -20.87F, 0.0F, -20.87F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 7.83F);
      this.rotate(this.animator, this.arm_right_upper, -20.87F, 0.0F, 20.87F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -7.83F);
      this.rotate(this.animator, this.leg_left_upper, 15.65F, 0.0F, -2.61F);
      this.animator.move(this.leg_left_lower, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, 15.65F, 0.0F, 2.61F);
      this.animator.move(this.leg_right_lower, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.body_main, 2.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, -10.43F, -5.22F, 7.83F);
      this.rotate(this.animator, this.head_main, 0.0F, 2.61F, -18.26F);
      this.rotate(this.animator, this.head_jaw, 52.17F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -52.17F, -18.26F, -10.44F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 7.83F);
      this.rotate(this.animator, this.arm_right_upper, -52.17F, 18.26F, 10.44F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -7.83F);
      this.rotate(this.animator, this.leg_left_upper, 62.61F, 15.65F, -2.61F);
      this.rotate(this.animator, this.leg_right_upper, 62.61F, -15.65F, 2.61F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.body_main, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, 0.0F, 2.61F, -18.26F);
      this.rotate(this.animator, this.arm_left_upper, 5.22F, 0.0F, -15.65F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 23.48F);
      this.animator.move(this.arm_left_upper, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, 5.22F, 0.0F, 15.65F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -23.48F);
      this.animator.move(this.arm_right_upper, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, 2.61F, -5.22F, -10.43F);
      this.rotate(this.animator, this.leg_right_upper, 2.61F, 5.22F, 10.43F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.body_main, 7.83F, 0.0F, 0.0F);
      this.animator.move(this.body_main, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.head_main, 2.61F, 7.83F, -2.61F);
      this.rotate(this.animator, this.arm_left_upper, 39.13F, 0.0F, -15.65F);
      this.animator.move(this.arm_left_upper, 0.0F, 3.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 23.48F);
      this.rotate(this.animator, this.arm_right_upper, 39.13F, 0.0F, 15.65F);
      this.animator.move(this.arm_right_upper, 0.0F, 3.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -23.48F);
      this.rotate(this.animator, this.leg_left_upper, -5.22F, 0.0F, -2.61F);
      this.rotate(this.animator, this.leg_right_upper, -5.22F, 0.0F, 2.61F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
   }

   public void setupAnim(EntityHyena hyena, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(hyena);
      float globalSpeed = 2.8F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount * 2.0F);
      limbSwing *= 0.5F;
      double scaleX = Math.sin((double)(ageInTicks * 1.0F / 20.0F));
      double scaleY = Math.sin((double)(ageInTicks / 16.0F));
      this.body_main.setScale((float)(1.0 + scaleX * 0.08F), (float)(1.0 + scaleY * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!hyena.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-1.5F, -2.0F, -1.5F);
         this.eye_left.setRotationPoint(1.5F, -2.0F, -1.5F);
      }

      if (!hyena.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (hyena.isInWater() && !hyena.onGround()) {
         limbSwing = ageInTicks / 3.0F;
         limbSwingAmount = 0.5F;
         this.body_main.rotationPointY += 4.0F;
         this.setRotateAngle(this.head_neck, -0.18203785F, 0.0F, 0.0F);
         float pitch = Mth.clamp(hyena.getXRot() - 10.0F, -25.0F, 25.0F);
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      float newZ = Mth.lerp(0.4F, this.tailX, this.tail_1.defaultRotationX + (float)hyena.getCurrentSpeed() * 2.0F);
      this.tail_1.rotateAngleX = newZ;
      this.tailX = newZ;
      if (hyena.canMove()) {
         if (!(hyena.getCurrentSpeed() > 0.06F) && !hyena.isAngry()) {
            this.bob(this.body_main, 0.5F * globalSpeed, 0.2F, false, limbSwing, limbSwingAmount);
            this.walk(this.body_main, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_neck, 0.5F * globalSpeed, -0.2F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_upper, 0.5F * globalSpeed, 0.6F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, false, 0.0F, -0.8F, limbSwing, limbSwingAmount * 1.2F);
            this.walk(this.arm_left_upper, 0.5F * globalSpeed, 0.6F * globalDegree, false, 5.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, false, 3.0F, -0.8F, limbSwing, limbSwingAmount * 1.2F);
            this.bob(this.leg_right_upper, 0.5F * globalSpeed, 0.6F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_upper, 0.5F * globalSpeed, 0.8F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, 0.4F * globalDegree, true, 1.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_upper, 0.5F * globalSpeed, 0.6F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_upper, 0.5F * globalSpeed, 0.8F * globalDegree, true, 3.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, 0.4F * globalDegree, true, 3.6F, 0.2F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.body_main, 0.3F * globalSpeed, 0.5F, false, limbSwing, limbSwingAmount);
            this.walk(this.body_main, 0.3F * globalSpeed, 0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_neck, 0.3F * globalSpeed, -0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_right_upper, 0.3F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_upper, 0.3F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.3F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_upper, 0.3F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_upper, 0.3F * globalSpeed, globalDegree, true, 0.6F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.3F * globalSpeed, 0.6F * globalDegree, true, 0.8F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_upper, 0.3F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_upper, 0.3F * globalSpeed, globalDegree, true, 1.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.3F * globalSpeed, 0.6F * globalDegree, true, 1.6F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_upper, 0.3F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_upper, 0.3F * globalSpeed, globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.3F * globalSpeed, 0.6F * globalDegree, true, 2.2F, 0.2F, limbSwing, limbSwingAmount);
         }
      }

      if (hyena.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)hyena.sitProgress, 0.0F, 22.5F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left_upper, (float)hyena.sitProgress, 2.0F, -2.0F, 6.0F, 40.0F);
         this.progressPosition(this.leg_right_upper, (float)hyena.sitProgress, -2.0F, -2.0F, 6.0F, 40.0F);
         this.progressRotation(this.body_main, (float)hyena.sitProgress, 0.0F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_neck, (float)hyena.sitProgress, -0.8F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_upper, (float)hyena.sitProgress, -0.27314404F, -0.0F, -0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)hyena.sitProgress, -1.2292354F, -0.22759093F, 0.045553092F, 40.0F);
         this.progressRotation(this.arm_left_upper, (float)hyena.sitProgress, 0.27314404F, -6.200655E-17F, -0.24361071F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)hyena.sitProgress, -1.8668041F, 0.0F, 0.10803588F, 40.0F);
         this.progressRotation(this.leg_right_upper, (float)hyena.sitProgress, -0.27314404F, -0.0F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_right_lower, (float)hyena.sitProgress, -1.2292354F, 0.22759093F, -0.045553092F, 40.0F);
         this.progressRotation(this.arm_right_upper, (float)hyena.sitProgress, 0.27314404F, 6.200655E-17F, 0.24361071F, 40.0F);
         this.progressRotation(this.arm_right_lower, (float)hyena.sitProgress, -1.8668041F, 0.0F, -0.10803588F, 40.0F);
         this.progressRotation(this.tail_1, (float)hyena.sitProgress, (float) (-Math.PI / 3), 0.0F, 0.0F, 40.0F);
      }

      if (hyena.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)hyena.sleepProgress, -1.0F, 20.0F, 0.0F, 40.0F);
         this.progressRotation(this.body_main, (float)hyena.sleepProgress, 0.0F, 0.0F, -1.5025539F, 40.0F);
         this.progressRotation(this.leg_right_upper, (float)hyena.sleepProgress, -0.5009095F, -0.0F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)hyena.sleepProgress, -0.13665928F, 0.0F, 0.7740535F, 40.0F);
         this.progressRotation(this.head_neck, (float)hyena.sleepProgress, 0.27314404F, 0.22759093F, 0.0F, 40.0F);
         this.progressRotation(this.head_main, (float)hyena.sleepProgress, 0.4553564F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_upper, (float)hyena.sleepProgress, -0.27314404F, 0.0F, 0.09110619F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)hyena.sleepProgress, -0.5009095F, -0.09110619F, 1.0472F, 40.0F);
      }
   }
}

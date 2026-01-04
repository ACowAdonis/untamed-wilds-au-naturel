package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.mammal.EntityOpossum;

public class ModelOpossum extends AdvancedEntityModel<EntityOpossum> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox head_head;
   public AdvancedModelBox leg_right_1;
   public AdvancedModelBox leg_left_1;
   public AdvancedModelBox joey_1;
   public AdvancedModelBox joey_2;
   public AdvancedModelBox joey_3;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox arm_left_paw;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox arm_right_paw;
   public AdvancedModelBox head_ear_left;
   public AdvancedModelBox head_ear_right;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox whisker_right;
   public AdvancedModelBox whisker_left;
   public AdvancedModelBox leg_right_2;
   public AdvancedModelBox leg_right_paw;
   public AdvancedModelBox leg_left_2;
   public AdvancedModelBox leg_left_paw;
   public AdvancedModelBox tail_1;
   public AdvancedModelBox tail_2;
   private final ModelAnimator animator;

   public ModelOpossum() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.joey_1 = new AdvancedModelBox(this, 40, 0);
      this.joey_1.setRotationPoint(0.0F, -5.0F, 0.0F);
      this.joey_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.joey_1, 0.0F, 0.0F, -0.5462881F);
      this.head_jaw = new AdvancedModelBox(this, 6, 17);
      this.head_jaw.setRotationPoint(0.0F, 1.01F, -2.0F);
      this.head_jaw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 30, 23);
      this.arm_left_2.mirror = true;
      this.arm_left_2.setRotationPoint(0.0F, 3.5F, 0.01F);
      this.arm_left_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, 0.0F, 0.0F, 0.13665928F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 19.0F, -3.8F);
      this.body_main.addBox(-2.5F, -3.3F, -2.0F, 5.0F, 5.0F, 10.0F, 0.0F);
      this.whisker_right = new AdvancedModelBox(this, 0, 0);
      this.whisker_right.setRotationPoint(-0.6F, 0.0F, -3.0F);
      this.whisker_right.addBox(-3.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.whisker_right, 0.0F, -0.22759093F, -0.27314404F);
      this.arm_left_paw = new AdvancedModelBox(this, 0, 4);
      this.arm_left_paw.mirror = true;
      this.arm_left_paw.setRotationPoint(0.5F, 3.02F, 1.0F);
      this.arm_left_paw.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 4.0F, 0.0F);
      this.leg_right_2 = new AdvancedModelBox(this, 46, 24);
      this.leg_right_2.setRotationPoint(0.0F, 3.1F, 1.2F);
      this.leg_right_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, 0.27314404F, 0.0F, -0.13962634F);
      this.eye_left = new AdvancedModelBox(this, 0, 17);
      this.eye_left.setRotationPoint(0.01F, 0.0F, -3.01F);
      this.eye_left.addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.tail_2 = new AdvancedModelBox(this, 14, 17);
      this.tail_2.setRotationPoint(0.0F, 0.1F, 3.9F);
      this.tail_2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_2, 0.13665928F, 0.0F, 0.0F);
      this.tail_2.scaleX = 0.75F;
      this.arm_right_2 = new AdvancedModelBox(this, 30, 23);
      this.arm_right_2.setRotationPoint(0.0F, 3.5F, 0.01F);
      this.arm_right_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, 0.0F, 0.0F, -0.13665928F);
      this.leg_left_1 = new AdvancedModelBox(this, 46, 15);
      this.leg_left_1.mirror = true;
      this.leg_left_1.setRotationPoint(1.5F, -1.3F, 6.8F);
      this.leg_left_1.addBox(-1.5F, -1.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_1, -0.27314404F, 0.0F, -0.13962634F);
      this.head_ear_left = new AdvancedModelBox(this, 0, 21);
      this.head_ear_left.setRotationPoint(1.3F, -1.3F, -1.5F);
      this.head_ear_left.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_left, -0.091106184F, -0.091106184F, 0.8651597F);
      this.whisker_left = new AdvancedModelBox(this, 0, 0);
      this.whisker_left.mirror = true;
      this.whisker_left.setRotationPoint(0.6F, 0.0F, -3.0F);
      this.whisker_left.addBox(0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.whisker_left, 0.0F, 0.22759093F, 0.27314404F);
      this.head_head = new AdvancedModelBox(this, 2, 22);
      this.head_head.setRotationPoint(0.0F, -1.0F, -1.9F);
      this.head_head.addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_head, 0.091106184F, 0.0F, 0.0F);
      this.leg_left_paw = new AdvancedModelBox(this, 0, 4);
      this.leg_left_paw.mirror = true;
      this.leg_left_paw.setRotationPoint(0.5F, 3.01F, 1.0F);
      this.leg_left_paw.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_paw, 0.0036651914F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 18, 26);
      this.head_snout.setRotationPoint(0.0F, 1.0F, -2.0F);
      this.head_snout.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 2.0F, 0.0F);
      this.arm_right_1 = new AdvancedModelBox(this, 28, 15);
      this.arm_right_1.setRotationPoint(-1.5F, -1.55F, 0.2F);
      this.arm_right_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, 0.0F, 0.0F, 0.13665928F);
      this.leg_right_1 = new AdvancedModelBox(this, 46, 15);
      this.leg_right_1.setRotationPoint(-1.5F, -1.3F, 6.8F);
      this.leg_right_1.addBox(-1.5F, -1.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_1, -0.27314404F, 0.0F, 0.13962634F);
      this.leg_right_paw = new AdvancedModelBox(this, 0, 4);
      this.leg_right_paw.setRotationPoint(-0.5F, 3.01F, 1.0F);
      this.leg_right_paw.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_paw, 0.0036651914F, 0.0F, 0.0F);
      this.joey_2 = new AdvancedModelBox(this, 40, 0);
      this.joey_2.mirror = true;
      this.joey_2.setRotationPoint(0.0F, -5.0F, 3.0F);
      this.joey_2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.joey_2, 0.0F, 0.0F, 0.5462881F);
      this.joey_3 = new AdvancedModelBox(this, 40, 0);
      this.joey_3.setRotationPoint(0.0F, -5.0F, 6.0F);
      this.joey_3.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.joey_3, 0.0F, 0.0F, -0.5462881F);
      this.tail_1 = new AdvancedModelBox(this, 24, 2);
      this.tail_1.setRotationPoint(0.0F, -2.0F, 7.5F);
      this.tail_1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.tail_1, -0.4098033F, 0.0F, 0.0F);
      this.arm_right_paw = new AdvancedModelBox(this, 0, 4);
      this.arm_right_paw.setRotationPoint(-0.5F, 3.02F, 1.0F);
      this.arm_right_paw.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 4.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 0, 17);
      this.eye_right.mirror = true;
      this.eye_right.setRotationPoint(-0.01F, 0.0F, -3.01F);
      this.eye_right.addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.head_ear_right = new AdvancedModelBox(this, 0, 21);
      this.head_ear_right.mirror = true;
      this.head_ear_right.setRotationPoint(-1.3F, -1.2F, -1.5F);
      this.head_ear_right.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.head_ear_right, -0.091106184F, 0.091106184F, -0.8651597F);
      this.arm_left_1 = new AdvancedModelBox(this, 28, 15);
      this.arm_left_1.mirror = true;
      this.arm_left_1.setRotationPoint(1.5F, -1.55F, 0.2F);
      this.arm_left_1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, 0.0F, 0.0F, -0.13665928F);
      this.leg_left_2 = new AdvancedModelBox(this, 46, 24);
      this.leg_left_2.mirror = true;
      this.leg_left_2.setRotationPoint(0.0F, 3.1F, 1.2F);
      this.leg_left_2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, 0.27314404F, 0.0F, 0.13962634F);
      this.body_main.addChild(this.joey_1);
      this.head_head.addChild(this.head_jaw);
      this.arm_left_1.addChild(this.arm_left_2);
      this.head_snout.addChild(this.whisker_right);
      this.arm_left_2.addChild(this.arm_left_paw);
      this.leg_right_1.addChild(this.leg_right_2);
      this.head_head.addChild(this.eye_left);
      this.tail_1.addChild(this.tail_2);
      this.arm_right_1.addChild(this.arm_right_2);
      this.body_main.addChild(this.leg_left_1);
      this.head_head.addChild(this.head_ear_left);
      this.head_snout.addChild(this.whisker_left);
      this.body_main.addChild(this.head_head);
      this.leg_left_2.addChild(this.leg_left_paw);
      this.head_head.addChild(this.head_snout);
      this.body_main.addChild(this.arm_right_1);
      this.body_main.addChild(this.leg_right_1);
      this.leg_right_2.addChild(this.leg_right_paw);
      this.body_main.addChild(this.joey_2);
      this.body_main.addChild(this.joey_3);
      this.body_main.addChild(this.tail_1);
      this.arm_right_2.addChild(this.arm_right_paw);
      this.head_head.addChild(this.eye_right);
      this.head_head.addChild(this.head_ear_right);
      this.body_main.addChild(this.arm_left_1);
      this.leg_left_1.addChild(this.leg_left_2);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.arm_left_1,
         this.arm_right_1,
         this.head_head,
         this.leg_right_1,
         this.leg_left_1,
         this.tail_1,
         this.joey_1,
         this.joey_2,
         this.joey_3,
         this.arm_left_2,
         this.arm_left_paw,
         new AdvancedModelBox[]{
            this.arm_right_2,
            this.arm_right_paw,
            this.head_ear_left,
            this.head_ear_right,
            this.head_snout,
            this.eye_left,
            this.eye_right,
            this.head_jaw,
            this.whisker_right,
            this.whisker_left,
            this.leg_right_2,
            this.leg_right_paw,
            this.leg_left_2,
            this.leg_left_paw,
            this.tail_2
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityOpossum monitor = (EntityOpossum)entityIn;
      this.animator.update(monitor);
      this.animator.setAnimation(EntityOpossum.THREAT_BACK_OFF);
      int invert = 1;

      for (int i = 0; i < 4; i++) {
         this.animator.startKeyframe(6);
         this.animator.move(this.head_snout, 0.0F, -1.0F, 0.0F);
         this.rotate(this.animator, this.head_head, -15.65F, 2.61F * (float)invert, 13.04F * (float)invert);
         this.rotate(this.animator, this.head_jaw, 44.35F, 0.0F, 0.0F);
         invert *= -1;
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(6);
      this.animator.setAnimation(EntityOpossum.IDLE_SCRATCH);
      this.animator.startKeyframe(8);
      this.rotate(this.animator, this.body_main, 0.0F, 7.83F, -7.83F);
      this.rotate(this.animator, this.head_head, -23.48F, 20.87F, -39.13F);
      this.rotate(this.animator, this.tail_1, -23.48F, -15.65F, 0.0F);
      this.rotate(this.animator, this.head_ear_right, -28.7F, 5.22F, -49.57F);
      this.animator.move(this.arm_right_1, 0.0F, -0.3F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, 0.0F, 13.04F);
      this.rotate(this.animator, this.leg_left_1, -15.65F, 0.0F, 2.61F);
      this.animator.move(this.leg_right_1, 0.0F, 0.0F, -1.0F);
      this.rotate(this.animator, this.leg_right_1, -60.0F, 18.26F, 8.0F);
      this.animator.move(this.leg_right_paw, 0.0F, -1.0F, -1.0F);
      this.rotate(this.animator, this.leg_right_paw, 88.7F, 0.0F, 0.0F);
      int leg_offset = 1;

      for (int i = 0; i < 6; i++) {
         this.animator.startKeyframe(4);
         this.rotate(this.animator, this.body_main, 0.0F, 7.83F, -7.83F);
         this.rotate(this.animator, this.head_head, -23.48F, 20.87F - (float)(10 * leg_offset), -39.13F - (float)(10 * leg_offset));
         this.rotate(this.animator, this.tail_1, -23.48F, -15.65F, 0.0F);
         this.rotate(this.animator, this.head_ear_right, -28.7F, 5.22F, -49.57F);
         this.animator.move(this.arm_right_1, 0.0F, -0.3F, 0.0F);
         this.rotate(this.animator, this.arm_right_1, 0.0F, 0.0F, 13.04F);
         this.rotate(this.animator, this.leg_left_1, -15.65F, 0.0F, 2.61F);
         this.animator.move(this.leg_right_1, 0.0F, 0.0F, -1.0F);
         this.rotate(this.animator, this.leg_right_1, (float)(-80 - 20 * leg_offset), 18.26F, 8.0F);
         this.animator.move(this.leg_right_paw, 0.0F, -1.0F, -1.0F);
         this.rotate(this.animator, this.leg_right_paw, 88.7F, 0.0F, 0.0F);
         leg_offset *= -1;
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(8);
   }

   public void setupAnim(EntityOpossum opossum, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(opossum);
      float globalSpeed = 1.6F;
      float globalDegree = 1.4F;
      limbSwingAmount = Math.min(0.5F, limbSwingAmount);
      if (opossum.isNoAi()) {
         limbSwing = ageInTicks / 4.0F;
         limbSwingAmount = 0.5F;
      }

      if (!opossum.isPlayingDead()) {
         this.body_main
            .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
         this.head_head.setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), 1.0F, 1.0F);
         this.swing(this.whisker_left, globalSpeed, 0.12F * globalDegree, false, 1.0F, 0.0F, ageInTicks / 6.0F, 2.0F);
         this.swing(this.whisker_right, globalSpeed, 0.12F * globalDegree, true, 1.0F, 0.0F, ageInTicks / 6.0F, 2.0F);
         AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.tail_1, this.tail_2};
         this.chainSwing(bodyParts, 0.3F * globalSpeed, 0.125F * globalDegree, 1.0, ageInTicks / 6.0F, 1.0F);
      }

      this.joey_1.showModel = opossum.getJoeys() >= 1;
      this.joey_2.showModel = opossum.getJoeys() >= 2;
      this.joey_3.showModel = opossum.getJoeys() >= 3;
      if (!opossum.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_head});
      }

      if (opossum.isInWater() && !opossum.onGround()) {
         this.setRotateAngle(this.body_main, opossum.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (opossum.isInWater()) {
         this.flap(this.arm_left_1, globalSpeed, globalDegree, false, 0.8F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_left_1, globalSpeed, globalDegree * 0.8F, false, 1.6F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.arm_right_1, globalSpeed, globalDegree, false, 2.4F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_right_1, globalSpeed, globalDegree * 0.8F, false, 3.2F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.body_main, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.0F, 0.1F, limbSwing / 2.0F, limbSwingAmount);
         this.swing(this.body_main, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.8F, 0.1F, limbSwing / 3.0F, limbSwingAmount);
         this.chainWave(new AdvancedModelBox[]{this.head_head, this.body_main}, globalSpeed * 0.8F, globalDegree, -4.0, limbSwing, limbSwingAmount * 0.2F);
      } else {
         this.bob(this.body_main, 0.5F * globalSpeed, 0.8F, true, limbSwing, limbSwingAmount);
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
      }

      if (opossum.sitProgress != 0) {
         this.body_main.setScaleY(1.0F + 0.4F * (float)opossum.sitProgress / 20.0F);
         this.progressPosition(this.body_main, (float)opossum.sitProgress, 0.0F, 22.0F, -3.8F, 20.0F);
         this.progressPosition(this.arm_left_1, (float)opossum.sitProgress, 1.5F, -2.55F, 0.2F, 20.0F);
         this.progressPosition(this.arm_left_2, (float)opossum.sitProgress, 0.0F, 1.5F, 0.01F, 20.0F);
         this.progressPosition(this.arm_right_1, (float)opossum.sitProgress, -1.5F, -2.55F, 0.2F, 20.0F);
         this.progressPosition(this.arm_right_2, (float)opossum.sitProgress, 0.0F, 1.5F, 0.01F, 20.0F);
         this.progressPosition(this.leg_left_1, (float)opossum.sitProgress, 1.5F, -2.3F, 6.8F, 20.0F);
         this.progressPosition(this.leg_left_2, (float)opossum.sitProgress, 0.0F, 1.0F, 1.2F, 20.0F);
         this.progressPosition(this.leg_right_1, (float)opossum.sitProgress, -1.5F, -2.3F, 6.8F, 20.0F);
         this.progressPosition(this.leg_right_2, (float)opossum.sitProgress, 0.0F, 1.0F, 1.2F, 20.0F);
      }

      if (opossum.sleepProgress != 0) {
         this.progressRotation(this.body_main, (float)opossum.sleepProgress, 0.0F, 0.0F, (float)Math.toRadians(-80.65F), 40.0F);
         this.progressPosition(this.body_main, (float)opossum.sleepProgress, -1.0F, 22.0F, -1.8F, 40.0F);
         this.progressRotation(this.head_head, (float)opossum.sleepProgress, (float)Math.toRadians(20.87F), 0.0F, (float)Math.toRadians(-18.26F), 40.0F);
         if (opossum.isPlayingDead()) {
            this.progressRotation(this.head_jaw, (float)opossum.sleepProgress, (float)Math.toRadians(46.96F), 0.0F, (float)Math.toRadians(23.48F), 40.0F);
         }

         this.progressRotation(this.arm_left_1, (float)opossum.sleepProgress, 0.0F, (float)Math.toRadians(7.83F), (float)Math.toRadians(10.43F), 40.0F);
         this.progressRotation(
            this.leg_left_1, (float)opossum.sleepProgress, (float)Math.toRadians(-36.52F), (float)Math.toRadians(2.61F), (float)Math.toRadians(5.22F), 40.0F
         );
         this.progressRotation(this.tail_1, (float)opossum.sleepProgress, (float)Math.toRadians(-65.22F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.tail_2, (float)opossum.sleepProgress, (float)Math.toRadians(-44.35F), 0.0F, 0.0F, 40.0F);
      }
   }
}

package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.reptile.EntityMonitor;

public class ModelMonitor extends AdvancedEntityModel<EntityMonitor> {
   private final AdvancedModelBox main_body;
   private final AdvancedModelBox head_neck;
   private final AdvancedModelBox arm_left;
   private final AdvancedModelBox leg_left;
   private final AdvancedModelBox tail_1;
   private final AdvancedModelBox arm_right;
   private final AdvancedModelBox leg_right;
   private final AdvancedModelBox head_face;
   private final AdvancedModelBox head_snout;
   private final AdvancedModelBox head_jaw;
   private final AdvancedModelBox head_tongue;
   private final AdvancedModelBox arm_left_2;
   private final AdvancedModelBox arm_left_hand;
   private final AdvancedModelBox leg_left_2;
   private final AdvancedModelBox leg_left_feet;
   private final AdvancedModelBox tail_2;
   private final AdvancedModelBox arm_right_2;
   private final AdvancedModelBox arm_right_hand;
   private final AdvancedModelBox leg_right_2;
   private final AdvancedModelBox leg_right_feet;
   private final ModelAnimator animator;

   public ModelMonitor() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 20.2F, 0.0F);
      this.main_body.addBox(-3.0F, -3.0F, -6.0F, 6.0F, 5.0F, 12.0F, 0.0F);
      this.leg_right_2 = new AdvancedModelBox(this, 24, 6);
      this.leg_right_2.setRotationPoint(0.1F, 3.0F, 0.0F);
      this.leg_right_2.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, 0.4098033F, 0.045553092F, -0.3642502F);
      this.tail_1 = new AdvancedModelBox(this, 36, 0);
      this.tail_1.setRotationPoint(0.0F, -0.9F, 4.0F);
      this.tail_1.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.tail_1, -0.31869712F, 0.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 18, 17);
      this.head_face.setRotationPoint(0.0F, -0.9F, -3.4F);
      this.head_face.addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.22759093F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 14, 24);
      this.head_snout.setRotationPoint(0.0F, -1.0F, -3.0F);
      this.head_snout.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F);
      this.leg_right = new AdvancedModelBox(this, 24, 0);
      this.leg_right.setRotationPoint(-3.0F, -1.9F, 4.8F);
      this.leg_right.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right, -0.4098033F, 0.045553092F, 0.3642502F);
      this.leg_right_feet = new AdvancedModelBox(this, 33, 0);
      this.leg_right_feet.mirror = true;
      this.leg_right_feet.setRotationPoint(0.5F, 3.01F, -1.5F);
      this.leg_right_feet.addBox(-2.0F, 0.0F, -2.5F, 4.0F, 0.0F, 5.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 0, 17);
      this.head_neck.setRotationPoint(0.0F, -0.3F, -5.5F);
      this.head_neck.addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.22759093F, 0.013037238F, 0.0F);
      this.arm_left_hand = new AdvancedModelBox(this, 33, 0);
      this.arm_left_hand.setRotationPoint(-0.5F, 3.01F, -1.1F);
      this.arm_left_hand.addBox(-2.0F, 0.0F, -2.5F, 4.0F, 0.0F, 5.0F, 0.0F);
      this.leg_left_feet = new AdvancedModelBox(this, 33, 0);
      this.leg_left_feet.setRotationPoint(-0.5F, 3.01F, -1.5F);
      this.leg_left_feet.addBox(-2.0F, 0.0F, -2.5F, 4.0F, 0.0F, 5.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 0, 6);
      this.arm_right_2.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.arm_right_2.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, 0.0F, -0.045553092F, -0.5462881F);
      this.leg_left_2 = new AdvancedModelBox(this, 24, 6);
      this.leg_left_2.mirror = true;
      this.leg_left_2.setRotationPoint(-0.1F, 3.0F, 0.0F);
      this.leg_left_2.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, 0.4098033F, -0.045553092F, 0.3642502F);
      this.arm_right_hand = new AdvancedModelBox(this, 33, 0);
      this.arm_right_hand.mirror = true;
      this.arm_right_hand.setRotationPoint(0.5F, 3.01F, -1.1F);
      this.arm_right_hand.addBox(-2.0F, 0.0F, -2.5F, 4.0F, 0.0F, 5.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 24, 0);
      this.leg_left.mirror = true;
      this.leg_left.setRotationPoint(3.0F, -1.9F, 4.8F);
      this.leg_left.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left, -0.4098033F, -0.045553092F, -0.3642502F);
      this.tail_2 = new AdvancedModelBox(this, 40, 14);
      this.tail_2.setRotationPoint(0.0F, 0.5F, 9.0F);
      this.tail_2.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.tail_2, 0.22759093F, 0.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 0, 6);
      this.arm_left_2.mirror = true;
      this.arm_left_2.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.arm_left_2.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, 0.0F, 0.045553092F, 0.5462881F);
      this.arm_left = new AdvancedModelBox(this, 0, 0);
      this.arm_left.mirror = true;
      this.arm_left.setRotationPoint(2.8F, -1.0F, -5.2F);
      this.arm_left.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left, 0.0F, 0.045553092F, -0.5462881F);
      this.head_jaw = new AdvancedModelBox(this, 0, 26);
      this.head_jaw.setRotationPoint(0.0F, 1.0F, -3.0F);
      this.head_jaw.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, 0.0F);
      this.head_tongue = new AdvancedModelBox(this, 22, 26);
      this.head_tongue.setRotationPoint(0.0F, -0.01F, 0.0F);
      this.head_tongue.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 6.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 0, 0);
      this.arm_right.setRotationPoint(-2.8F, -1.0F, -5.2F);
      this.arm_right.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right, 0.0F, -0.045553092F, 0.5462881F);
      this.leg_right.addChild(this.leg_right_2);
      this.main_body.addChild(this.tail_1);
      this.head_neck.addChild(this.head_face);
      this.head_face.addChild(this.head_snout);
      this.main_body.addChild(this.leg_right);
      this.leg_right_2.addChild(this.leg_right_feet);
      this.main_body.addChild(this.head_neck);
      this.arm_left_2.addChild(this.arm_left_hand);
      this.leg_left_2.addChild(this.leg_left_feet);
      this.arm_right.addChild(this.arm_right_2);
      this.leg_left.addChild(this.leg_left_2);
      this.arm_right_2.addChild(this.arm_right_hand);
      this.main_body.addChild(this.leg_left);
      this.tail_1.addChild(this.tail_2);
      this.arm_left.addChild(this.arm_left_2);
      this.main_body.addChild(this.arm_left);
      this.head_face.addChild(this.head_jaw);
      this.head_jaw.addChild(this.head_tongue);
      this.main_body.addChild(this.arm_right);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.head_neck,
         this.arm_left,
         this.leg_left,
         this.tail_1,
         this.arm_right,
         this.leg_right,
         this.head_face,
         this.head_snout,
         this.head_jaw,
         this.head_tongue,
         this.arm_left_2,
         new AdvancedModelBox[]{
            this.arm_left_hand, this.leg_left_2, this.leg_left_feet, this.tail_2, this.arm_right_2, this.arm_right_hand, this.leg_right_2, this.leg_right_feet
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityMonitor monitor = (EntityMonitor)entityIn;
      this.animator.update(monitor);
      this.animator.setAnimation(EntityMonitor.ATTACK_THRASH);
      int invert = 1;

      for (int i = 0; i < 4; i++) {
         this.animator.startKeyframe(6);
         this.rotate(this.animator, this.head_neck, 0.0F, 22.73F * (float)invert, 0.0F);
         this.rotate(this.animator, this.head_face, -5.21F, 15.65F * (float)invert, 31.3F * (float)invert);
         this.rotate(this.animator, this.head_jaw, 20.87F, 0.0F, 0.0F);
         invert *= -1;
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(6);
      this.animator.setAnimation(EntityMonitor.IDLE_TONGUE);
      this.animator.startKeyframe(4);
      this.rotate(this.animator, this.head_tongue, 26.08F, 36.52F, 0.0F);
      this.animator.move(this.head_tongue, 0.0F, 0.0F, -4.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(3);
      this.rotate(this.animator, this.head_tongue, -26.08F, -36.52F, 0.0F);
      this.animator.move(this.head_tongue, 0.0F, 0.0F, -5.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(3);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
   }

   public void setupAnim(EntityMonitor monitor, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(monitor);
      float globalSpeed = 0.8F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount);
      if (monitor.isNoAi()) {
         limbSwing = ageInTicks / 4.0F;
         limbSwingAmount = 0.5F;
      }

      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.head_neck
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.head_face
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.tail_1.setScale(1.0F, (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      if (!monitor.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_face});
      }

      if (monitor.isInWater() && !monitor.onGround()) {
         this.setRotateAngle(this.main_body, monitor.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.main_body.rotateAngleY = Mth.rotLerp(0.05F, this.main_body.rotateAngleY, monitor.offset);
      this.tail_1.rotateAngleY = Mth.rotLerp(0.05F, this.tail_1.rotateAngleY, -1.0F * monitor.offset);
      this.tail_2.rotateAngleY = Mth.rotLerp(0.05F, this.tail_2.rotateAngleY, -2.0F * monitor.offset);
      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.head_face, this.head_neck, this.main_body, this.tail_1, this.tail_2};
      this.chainSwing(bodyParts, globalSpeed * 1.4F, globalDegree * 1.2F, -4.0, limbSwing, limbSwingAmount * 0.3F);
      if (monitor.isInWater()) {
         this.flap(this.arm_left, globalSpeed, globalDegree, false, 0.8F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_left, globalSpeed, globalDegree * 0.8F, false, 1.6F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.arm_right, globalSpeed, globalDegree, false, 2.4F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_right, globalSpeed, globalDegree * 0.8F, false, 3.2F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.main_body, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.0F, 0.1F, limbSwing / 2.0F, limbSwingAmount);
         this.swing(this.main_body, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.8F, 0.1F, limbSwing / 3.0F, limbSwingAmount);
         this.chainWave(
            new AdvancedModelBox[]{this.head_face, this.head_neck, this.main_body}, globalSpeed * 0.8F, globalDegree, -4.0, limbSwing, limbSwingAmount * 0.2F
         );
      } else {
         this.walk(this.arm_left, globalSpeed, globalDegree * 2.0F, false, -1.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.arm_left, globalSpeed, globalDegree * 1.0F, false, 1.0F, -1.5F, limbSwing, limbSwingAmount);
         this.flap(this.arm_left_2, globalSpeed, globalDegree * 1.0F, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.arm_right, globalSpeed, globalDegree * 2.0F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.arm_right, globalSpeed, globalDegree * 1.0F, false, 2.0F, 1.5F, limbSwing, limbSwingAmount);
         this.flap(this.arm_right_2, globalSpeed, globalDegree * 1.0F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.leg_left, globalSpeed, globalDegree * 2.0F, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_left, globalSpeed, globalDegree * 1.0F, false, 3.0F, 0.5F, limbSwing, limbSwingAmount);
         this.flap(this.leg_left_2, globalSpeed, globalDegree * 1.0F, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
         this.walk(this.leg_right, globalSpeed, globalDegree * 2.0F, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_right, globalSpeed, globalDegree * 1.0F, false, 4.0F, 0.5F, limbSwing, limbSwingAmount);
         this.flap(this.leg_right_2, globalSpeed, globalDegree * 1.0F, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
      }

      if (monitor.getTarget() == null && monitor.getAnimation() != EntityMonitor.ATTACK_THRASH) {
         this.setRotateAngle(this.head_neck, this.head_neck.rotateAngleX, (Float)monitor.head_movement.getA(), 0.0F);
         this.setRotateAngle(this.head_face, this.head_face.rotateAngleX, (Float)monitor.head_movement.getB(), 0.0F);
      }

      if (monitor.swimProgress > 0) {
         this.progressPosition(this.arm_left, (float)monitor.swimProgress, 2.8F, 1.0F, -5.2F, 20.0F);
         this.progressRotation(
            this.arm_left, (float)monitor.swimProgress, (float)Math.toRadians(93.91F), (float)Math.toRadians(18.26F), (float)Math.toRadians(-15.65F), 20.0F
         );
         this.progressPosition(this.arm_left_hand, (float)monitor.swimProgress, 1.1F, 3.01F, -0.5F, 20.0F);
         this.progressRotation(this.arm_left_hand, (float)monitor.swimProgress, 0.0F, (float)Math.toRadians(-88.7F), (float)Math.toRadians(86.09F), 20.0F);
         this.progressPosition(this.leg_left, (float)monitor.swimProgress, 3.0F, 0.1F, 4.8F, 20.0F);
         this.progressRotation(
            this.leg_left, (float)monitor.swimProgress, (float)Math.toRadians(83.48F), (float)Math.toRadians(-2.61F), (float)Math.toRadians(-20.87F), 20.0F
         );
         this.progressRotation(this.tail_1, (float)monitor.swimProgress, 0.0F, 0.0F, 0.0F, 20.0F);
         this.progressRotation(this.tail_2, (float)monitor.swimProgress, 0.0F, 0.0F, 0.0F, 20.0F);
         this.progressPosition(this.arm_right, (float)monitor.swimProgress, -2.8F, 1.0F, -5.2F, 20.0F);
         this.progressRotation(
            this.arm_right, (float)monitor.swimProgress, (float)Math.toRadians(93.91F), (float)Math.toRadians(-18.26F), (float)Math.toRadians(15.65F), 20.0F
         );
         this.progressPosition(this.arm_right_hand, (float)monitor.swimProgress, -1.1F, 3.01F, -0.5F, 20.0F);
         this.progressRotation(this.arm_right_hand, (float)monitor.swimProgress, 0.0F, (float)Math.toRadians(88.7F), (float)Math.toRadians(-86.09F), 20.0F);
         this.progressPosition(this.leg_right, (float)monitor.swimProgress, -3.0F, 0.1F, 4.8F, 20.0F);
         this.progressRotation(
            this.leg_right, (float)monitor.swimProgress, (float)Math.toRadians(83.48F), (float)Math.toRadians(2.61F), (float)Math.toRadians(20.87F), 20.0F
         );
      }

      if (monitor.sitProgress != 0) {
         this.progressPosition(this.head_neck, (float)monitor.sitProgress, 0.0F, -1.3F, -5.5F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.head_neck, (float)monitor.sitProgress, (float)Math.toRadians(-54.78F), (float)Math.toRadians(0.75), 0.0F, (float)monitor.ticksToSit
         );
         this.progressRotation(this.head_face, (float)monitor.sitProgress, (float)Math.toRadians(54.78F), 0.0F, 0.0F, (float)monitor.ticksToSit);
         this.progressPosition(this.main_body, (float)monitor.sitProgress, 0.0F, 22.4F, 0.0F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.arm_left,
            (float)monitor.sitProgress,
            (float)Math.toRadians(-31.3F),
            (float)Math.toRadians(2.61F),
            (float)Math.toRadians(-54.78F),
            (float)monitor.ticksToSit
         );
         this.progressRotation(
            this.arm_left_2,
            (float)monitor.sitProgress,
            (float)Math.toRadians(-5.22F),
            (float)Math.toRadians(-5.22F),
            (float)Math.toRadians(7.83F),
            (float)monitor.ticksToSit
         );
         this.progressPosition(this.arm_left_hand, (float)monitor.sitProgress, -0.5F, 2.51F, -1.1F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.arm_left_hand,
            (float)monitor.sitProgress,
            (float)Math.toRadians(20.87F),
            (float)Math.toRadians(-20.87F),
            (float)Math.toRadians(31.3F),
            (float)monitor.ticksToSit
         );
         this.progressRotation(
            this.arm_right,
            (float)monitor.sitProgress,
            (float)Math.toRadians(-31.3F),
            (float)Math.toRadians(-2.61F),
            (float)Math.toRadians(54.78F),
            (float)monitor.ticksToSit
         );
         this.progressRotation(
            this.arm_right_2,
            (float)monitor.sitProgress,
            (float)Math.toRadians(-5.22F),
            (float)Math.toRadians(5.22F),
            (float)Math.toRadians(-7.83F),
            (float)monitor.ticksToSit
         );
         this.progressRotation(
            this.arm_right_hand,
            (float)monitor.sitProgress,
            (float)Math.toRadians(20.87F),
            (float)Math.toRadians(20.87F),
            (float)Math.toRadians(-31.3F),
            (float)monitor.ticksToSit
         );
         this.progressPosition(this.arm_right_hand, (float)monitor.sitProgress, 0.5F, 2.51F, -1.1F, (float)monitor.ticksToSit);
         this.progressPosition(this.leg_left, (float)monitor.sitProgress, 3.0F, -0.9F, 4.8F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.leg_left,
            (float)monitor.sitProgress,
            (float)Math.toRadians(49.57F),
            (float)Math.toRadians(-2.61F),
            (float)Math.toRadians(-75.65F),
            (float)monitor.ticksToSit
         );
         this.progressPosition(this.leg_left_feet, (float)monitor.sitProgress, 0.5F, 3.01F, -1.5F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.leg_left_feet,
            (float)monitor.sitProgress,
            (float)Math.toRadians(-31.3F),
            (float)Math.toRadians(-36.52F),
            (float)Math.toRadians(88.7F),
            (float)monitor.ticksToSit
         );
         this.progressPosition(this.leg_right, (float)monitor.sitProgress, -3.0F, -0.9F, 4.8F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.leg_right,
            (float)monitor.sitProgress,
            (float)Math.toRadians(49.57F),
            (float)Math.toRadians(2.61F),
            (float)Math.toRadians(75.65F),
            (float)monitor.ticksToSit
         );
         this.progressPosition(this.leg_right_feet, (float)monitor.sitProgress, -0.5F, 3.01F, -1.5F, (float)monitor.ticksToSit);
         this.progressRotation(
            this.leg_right_feet,
            (float)monitor.sitProgress,
            (float)Math.toRadians(-31.3F),
            (float)Math.toRadians(36.52F),
            (float)Math.toRadians(-88.7F),
            (float)monitor.ticksToSit
         );
         this.progressRotation(
            this.tail_1, (float)monitor.sitProgress, (float)Math.toRadians(-2.61F), this.tail_1.rotateAngleY, 0.0F, (float)monitor.ticksToSit
         );
         this.progressRotation(this.tail_2, (float)monitor.sitProgress, 0.0F, this.tail_2.rotateAngleY, 0.0F, (float)monitor.ticksToSit);
      }
   }
}

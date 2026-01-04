package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.amphibian.EntityGiantSalamander;

public class ModelGiantSalamander extends AdvancedEntityModel<EntityGiantSalamander> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox body_torso;
   public AdvancedModelBox tail_1;
   public AdvancedModelBox leg_left_1;
   public AdvancedModelBox leg_right_1;
   public AdvancedModelBox arm_left_1;
   public AdvancedModelBox arm_right_1;
   public AdvancedModelBox head_main;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox head_face;
   public AdvancedModelBox tail_2;
   private final ModelAnimator animator;

   public ModelGiantSalamander() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.arm_right_1 = new AdvancedModelBox(this, 24, 0);
      this.arm_right_1.mirror = true;
      this.arm_right_1.setRotationPoint(-2.0F, 0.2F, -3.0F);
      this.arm_right_1.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, 0.22759093F, 1.1838568F, 0.0F);
      this.arm_left_1 = new AdvancedModelBox(this, 24, 0);
      this.arm_left_1.setRotationPoint(2.0F, 0.2F, -3.0F);
      this.arm_left_1.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, 0.22759093F, (float) (-Math.PI / 3), 0.0F);
      this.tail_2 = new AdvancedModelBox(this, 42, 8);
      this.tail_2.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.tail_2.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 7.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 0, 10);
      this.body_main.setRotationPoint(0.0F, 22.3F, 0.0F);
      this.body_main.addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 7.0F, 0.0F);
      this.leg_left_1 = new AdvancedModelBox(this, 24, 8);
      this.leg_left_1.setRotationPoint(1.5F, 0.2F, 5.0F);
      this.leg_left_1.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_1, 0.22759093F, (float) (-Math.PI * 2.0 / 3.0), 0.0F);
      this.tail_1 = new AdvancedModelBox(this, 42, 0);
      this.tail_1.setRotationPoint(0.0F, 0.01F, 7.0F);
      this.tail_1.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 4.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 20);
      this.head_main.setRotationPoint(0.0F, -0.4F, -6.0F);
      this.head_main.addBox(-3.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_main, 0.13665928F, 0.0F, 0.0F);
      this.body_torso = new AdvancedModelBox(this, 0, 0);
      this.body_torso.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body_torso.addBox(-2.5F, -1.5F, -7.0F, 5.0F, 3.0F, 7.0F, 0.0F);
      this.leg_right_1 = new AdvancedModelBox(this, 24, 8);
      this.leg_right_1.mirror = true;
      this.leg_right_1.setRotationPoint(-1.5F, 0.2F, 5.0F);
      this.leg_right_1.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_1, 0.22759093F, (float) (Math.PI * 2.0 / 3.0), 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 18, 25);
      this.head_jaw.setRotationPoint(0.0F, 0.5F, -2.5F);
      this.head_jaw.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 3.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 18, 20);
      this.head_face.setRotationPoint(0.0F, -0.5F, -1.5F);
      this.head_face.addBox(-3.0F, -1.0F, -4.0F, 6.0F, 2.0F, 3.0F, 0.0F);
      this.body_torso.addChild(this.arm_right_1);
      this.body_torso.addChild(this.arm_left_1);
      this.tail_1.addChild(this.tail_2);
      this.body_main.addChild(this.leg_left_1);
      this.body_main.addChild(this.tail_1);
      this.body_torso.addChild(this.head_main);
      this.body_main.addChild(this.body_torso);
      this.body_main.addChild(this.leg_right_1);
      this.head_main.addChild(this.head_jaw);
      this.head_main.addChild(this.head_face);
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
         this.arm_left_1,
         this.arm_right_1,
         this.leg_left_1,
         this.leg_right_1,
         this.tail_1,
         this.tail_2,
         this.head_main,
         this.head_face,
         this.head_jaw
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      this.animator.update(entityIn);
      this.animator.setAnimation(EntityGiantSalamander.ATTACK_SWALLOW);
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_main, -5.22F, 15.65F, -20.87F);
      this.rotate(this.animator, this.head_jaw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -44.35F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_main, -5.22F, -5.22F, 10.43F);
      this.rotate(this.animator, this.head_jaw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -44.35F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(5);
   }

   public void setupAnim(EntityGiantSalamander salamander, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(salamander);
      float globalSpeed = 0.8F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount);
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_torso
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.head_face
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.head_jaw
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.head_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      if (!salamander.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (salamander.isInWater() && !salamander.onGround()) {
         this.setRotateAngle(this.body_main, salamander.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.body_torso.rotateAngleY = Mth.rotLerp(0.05F, this.body_torso.rotateAngleY, salamander.offset);
      this.tail_1.rotateAngleY = Mth.rotLerp(0.05F, this.tail_1.rotateAngleY, -1.0F * salamander.offset);
      this.tail_2.rotateAngleY = Mth.rotLerp(0.05F, this.tail_2.rotateAngleY, -2.0F * salamander.offset);
      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.head_main, this.body_torso, this.body_main, this.tail_1, this.tail_2};
      this.chainSwing(bodyParts, globalSpeed * 1.4F, globalDegree * 1.2F, -4.0, limbSwing, limbSwingAmount * 0.3F);
      float onGround = Math.min(0.8F, limbSwingAmount * (float)(salamander.onGround() ? 2 : 1));
      if (salamander.isInWater()) {
         this.flap(this.arm_left_1, globalSpeed, globalDegree, false, 0.8F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_left_1, globalSpeed, globalDegree * 0.8F, false, 1.6F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.arm_right_1, globalSpeed, globalDegree, false, 2.4F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_right_1, globalSpeed, globalDegree * 0.8F, false, 3.2F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.body_main, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.0F, 0.1F, limbSwing / 2.0F, limbSwingAmount);
         this.swing(this.body_main, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.8F, 0.1F, limbSwing / 3.0F, limbSwingAmount);
         this.chainWave(
            new AdvancedModelBox[]{this.head_main, this.body_torso, this.body_main}, globalSpeed * 0.8F, globalDegree, -4.0, limbSwing, limbSwingAmount * 0.2F
         );
      } else {
         this.swing(this.arm_left_1, globalSpeed, globalDegree * 2.0F, false, 0.8F, 1.0F, limbSwing, onGround);
         this.swing(this.leg_left_1, globalSpeed, globalDegree * 1.8F, false, 1.6F, 1.0F, limbSwing, onGround);
         this.swing(this.arm_right_1, globalSpeed, globalDegree * 2.0F, false, 2.4F, 1.0F, limbSwing, onGround);
         this.swing(this.leg_right_1, globalSpeed, globalDegree * 1.8F, false, 3.2F, 1.0F, limbSwing, onGround);
      }

      if (salamander.swimProgress > 0) {
         this.progressRotation(
            this.arm_right_1,
            (float)salamander.swimProgress,
            (float)Math.toRadians(-20.87F),
            (float)Math.toRadians(172.1F),
            (float)Math.toRadians(-78.26),
            20.0F
         );
         this.progressRotation(
            this.arm_left_1,
            (float)salamander.swimProgress,
            (float)Math.toRadians(-20.87F),
            (float)Math.toRadians(-172.1F),
            (float)Math.toRadians(78.26),
            20.0F
         );
         this.progressRotation(
            this.leg_right_1, (float)salamander.swimProgress, (float)Math.toRadians(-15.65), (float)Math.toRadians(174.7), (float)Math.toRadians(-88.7), 20.0F
         );
         this.progressRotation(
            this.leg_left_1, (float)salamander.swimProgress, (float)Math.toRadians(-15.65), (float)Math.toRadians(-174.7), (float)Math.toRadians(88.7), 20.0F
         );
      }
   }
}

package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityHippo;

public class ModelHippo extends AdvancedEntityModel<EntityHippo> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox arm_right;
   public AdvancedModelBox arm_left;
   public AdvancedModelBox leg_right;
   public AdvancedModelBox leg_left;
   public AdvancedModelBox head_face;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox face_ear_right;
   public AdvancedModelBox head_jaw_1;
   public AdvancedModelBox face_ear_left;
   public AdvancedModelBox head_jaw_2;
   public AdvancedModelBox tooth_r;
   public AdvancedModelBox tooth_l;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox arm_right_2;
   public AdvancedModelBox arm_left_2;
   public AdvancedModelBox leg_right_2;
   public AdvancedModelBox leg_left_2;
   private final ModelAnimator animator;

   public ModelHippo() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 12.5F, 0.0F);
      this.body_main.addBox(-6.0F, -6.0F, -12.0F, 12.0F, 12.0F, 24.0F, 0.0F);
      this.head_neck = new AdvancedModelBox(this, 48, 0);
      this.head_neck.setRotationPoint(0.0F, -0.6F, -10.0F);
      this.head_neck.addBox(-5.0F, -5.0F, -6.0F, 10.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_neck, 0.045553092F, 0.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 0, 36);
      this.head_face.setRotationPoint(0.0F, -2.0F, -5.0F);
      this.head_face.addBox(-4.5F, -3.5F, -6.0F, 9.0F, 9.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.2F, 0.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 37);
      this.eye_left.setRotationPoint(4.51F, -2.0F, -4.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 0, 37);
      this.eye_right.setRotationPoint(-4.51F, -2.0F, -4.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.face_ear_left = new AdvancedModelBox(this, 0, 36);
      this.face_ear_left.mirror = true;
      this.face_ear_left.setRotationPoint(3.0F, -3.0F, -3.0F);
      this.face_ear_left.addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.face_ear_left, 0.13665928F, 0.091106184F, 0.3642502F);
      this.face_ear_right = new AdvancedModelBox(this, 0, 36);
      this.face_ear_right.setRotationPoint(-3.0F, -3.0F, -3.0F);
      this.face_ear_right.addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.face_ear_right, 0.13665928F, -0.091106184F, -0.3642502F);
      this.head_jaw = new AdvancedModelBox(this, 36, 36);
      this.head_jaw.setRotationPoint(0.0F, 0.0F, -6.0F);
      this.head_jaw.addBox(-4.0F, -3.0F, -7.0F, 8.0F, 5.0F, 8.0F, 0.0F);
      this.head_jaw_2 = new AdvancedModelBox(this, 36, 49);
      this.head_jaw_2.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.head_jaw_2.addBox(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F);
      this.head_jaw_1 = new AdvancedModelBox(this, 0, 53);
      this.head_jaw_1.setRotationPoint(0.0F, 1.5F, -4.5F);
      this.head_jaw_1.addBox(-4.0F, 0.0F, -8.0F, 8.0F, 3.0F, 8.0F, 0.0F);
      this.tooth_r = new AdvancedModelBox(this, 0, 53);
      this.tooth_r.setRotationPoint(-2.5F, 0.0F, -5.5F);
      this.tooth_r.addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F);
      this.tooth_l = new AdvancedModelBox(this, 0, 53);
      this.tooth_l.setRotationPoint(2.5F, 0.0F, -5.5F);
      this.tooth_l.addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 0, 0);
      this.arm_right.setRotationPoint(-3.49F, 3.5F, -8.5F);
      this.arm_right.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.arm_right_2 = new AdvancedModelBox(this, 0, 12);
      this.arm_right_2.setRotationPoint(0.0F, 3.0F, 0.0F);
      this.arm_right_2.addBox(-2.51F, 0.0F, -2.51F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.arm_left = new AdvancedModelBox(this, 0, 0);
      this.arm_left.mirror = true;
      this.arm_left.setRotationPoint(3.49F, 3.5F, -8.5F);
      this.arm_left.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 0, 12);
      this.arm_left_2.mirror = true;
      this.arm_left_2.setRotationPoint(0.0F, 3.0F, 0.0F);
      this.arm_left_2.addBox(-2.51F, 0.0F, -2.51F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.leg_right = new AdvancedModelBox(this, 0, 0);
      this.leg_right.setRotationPoint(-3.49F, 3.5F, 8.5F);
      this.leg_right.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.leg_right_2 = new AdvancedModelBox(this, 0, 12);
      this.leg_right_2.setRotationPoint(0.0F, 3.0F, 0.0F);
      this.leg_right_2.addBox(-2.51F, 0.0F, -2.51F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 0, 0);
      this.leg_left.setRotationPoint(3.49F, 3.5F, 8.5F);
      this.leg_left.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.leg_left_2 = new AdvancedModelBox(this, 0, 12);
      this.leg_left_2.setRotationPoint(0.0F, 3.0F, 0.0F);
      this.leg_left_2.addBox(-2.51F, 0.0F, -2.51F, 5.0F, 5.0F, 5.0F, 0.0F);
      this.body_main.addChild(this.arm_left);
      this.head_jaw_1.addChild(this.tooth_r);
      this.head_face.addChild(this.face_ear_left);
      this.leg_left.addChild(this.leg_left_2);
      this.arm_left.addChild(this.arm_left_2);
      this.head_neck.addChild(this.head_face);
      this.head_jaw.addChild(this.head_jaw_2);
      this.head_face.addChild(this.head_jaw);
      this.head_face.addChild(this.head_jaw_1);
      this.body_main.addChild(this.head_neck);
      this.body_main.addChild(this.leg_right);
      this.body_main.addChild(this.leg_left);
      this.head_jaw_1.addChild(this.tooth_l);
      this.arm_right.addChild(this.arm_right_2);
      this.body_main.addChild(this.arm_right);
      this.head_face.addChild(this.face_ear_right);
      this.leg_right.addChild(this.leg_right_2);
      this.head_face.addChild(this.eye_left);
      this.head_face.addChild(this.eye_right);
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
         this.arm_right,
         this.arm_left,
         this.leg_right,
         this.leg_left,
         this.head_face,
         this.head_jaw,
         this.face_ear_right,
         this.head_jaw_1,
         this.face_ear_left,
         this.head_jaw_2,
         new AdvancedModelBox[]{
            this.tooth_r, this.tooth_l, this.eye_right, this.eye_left, this.arm_right_2, this.arm_left_2, this.leg_right_2, this.leg_left_2
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      this.resetToDefaultPose();
      EntityHippo bear = (EntityHippo)entityIn;
      this.animator.update(bear);
      this.animator.setAnimation(EntityHippo.EAT);
      this.animator.startKeyframe(12);
      this.rotate(this.animator, this.head_neck, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 26.09F, 0.0F, 5.22F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(12);
      this.rotate(this.animator, this.head_neck, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 20.87F, 5.22F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -10.43F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 10.43F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(12);
      this.rotate(this.animator, this.head_neck, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 26.09F, -5.22F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(12);
      this.animator.setAnimation(EntityHippo.IDLE_YAWN);
      this.animator.startKeyframe(8);
      this.rotate(this.animator, this.head_neck, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -10.43F, 0.0F, 0.0F);
      this.animator.move(this.head_jaw, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 54.78F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_neck, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -10.43F, 0.0F, 2.61F);
      this.animator.move(this.head_jaw, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -44.35F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 62.61F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_neck, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -10.43F, 0.0F, -2.61F);
      this.animator.move(this.head_jaw, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -44.35F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 62.61F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityHippo.IDLE_LOOK);
      this.animator.startKeyframe(32);
      this.rotate(this.animator, this.head_neck, -20.87F, 5.22F, 0.0F);
      this.rotate(this.animator, this.head_face, 15.65F, 31.3F, 7.83F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(64);
      this.rotate(this.animator, this.head_neck, -20.87F, -7.83F, 0.0F);
      this.rotate(this.animator, this.head_face, 13.04F, -7.83F, -5.22F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(32);
      this.animator.setAnimation(EntityHippo.ATTACK);
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_neck, -13.05F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 13.05F, -10.43F, -7.83F);
      this.animator.move(this.head_jaw, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 36.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_neck, -13.05F, 7.83F, 0.0F);
      this.rotate(this.animator, this.head_face, -13.04F, 23.48F, -26.09F);
      this.animator.move(this.head_jaw, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 36.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_neck, -13.05F, -10.43F, 0.0F);
      this.rotate(this.animator, this.head_face, -13.04F, -20.87F, -2.61F);
      this.animator.move(this.head_jaw, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw_1, 36.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(6);
      this.animator.setAnimation(EntityHippo.IDLE_TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_jaw, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
   }

   public void setupAnim(EntityHippo hippo, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.animate(hippo);
      float globalSpeed = 1.5F;
      float globalDegree = 1.0F;
      float f = limbSwing / 2.0F;
      if (limbSwingAmount > 0.4F) {
         limbSwingAmount = 0.4F;
      }

      if (!hippo.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-2.0F, -2.0F, -4.0F);
         this.eye_left.setRotationPoint(2.0F, -2.0F, -4.0F);
      }

      if (hippo.isInWater() && !hippo.onGround()) {
         float pitch = Mth.clamp(hippo.getXRot() - 10.0F, -25.0F, 25.0F);
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (!hippo.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_face});
      }

      this.head_jaw_1.setScaleX(0.9F);
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.1F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left, 0.4F * globalSpeed, 0.1F, false, -ageInTicks / 20.0F, 2.0F);
      this.walk(this.head_face, 0.4F * globalSpeed, 0.03F, false, 2.8F, 0.06F, ageInTicks / 20.0F, 2.0F);
      if (hippo.angryProgress != 0) {
         this.progressRotation(this.head_jaw_1, (float)hippo.angryProgress, 0.3642502F, 0.0F, 0.0F, 40.0F);
      }

      if (hippo.sitProgress != 0) {
         if (hippo.isSitting()) {
            this.progressPosition(this.body_main, (float)hippo.sitProgress, 0.0F, 18.5F, 0.0F, 40.0F);
            this.progressRotation(this.body_main, (float)hippo.sitProgress, 0.0F, 0.091106184F, 0.0F, 40.0F);
            this.progressPosition(this.head_neck, (float)hippo.sitProgress, 0.0F, 0.0F, -10.0F, 40.0F);
            this.progressRotation(this.head_neck, (float)hippo.sitProgress, 0.18203785F, 0.0F, 0.0F, 40.0F);
            this.progressPosition(this.head_face, (float)hippo.sitProgress, 0.0F, -1.0F, -5.0F, 40.0F);
            this.progressRotation(this.head_face, (float)hippo.sitProgress, 0.0F, -0.27314404F, 0.045553092F, 40.0F);
            this.progressRotation(this.arm_right, (float)hippo.sitProgress, -1.5025539F, 0.27314404F, 0.0F, 40.0F);
            this.progressRotation(this.arm_left, (float)hippo.sitProgress, -1.5025539F, -0.27314404F, 0.0F, 40.0F);
            this.progressRotation(this.leg_right, (float)hippo.sitProgress, -1.548107F, 2.5497515F, 0.0F, 40.0F);
            this.progressRotation(this.leg_left, (float)hippo.sitProgress, -1.548107F, -2.5497515F, 0.0F, 40.0F);
         }

         if (hippo.isSleeping()) {
            this.progressPosition(this.body_main, (float)hippo.sitProgress, 0.0F, 18.5F, 0.0F, 40.0F);
            this.progressRotation(this.body_main, (float)hippo.sitProgress, 0.0F, 0.091106184F, 0.0F, 40.0F);
            this.progressPosition(this.head_neck, (float)hippo.sitProgress, 0.0F, 0.0F, -10.0F, 40.0F);
            this.progressRotation(this.head_neck, (float)hippo.sitProgress, 0.18203785F, 0.0F, 0.0F, 40.0F);
            this.progressPosition(this.head_face, (float)hippo.sitProgress, 0.0F, -1.0F, -5.0F, 40.0F);
            this.progressRotation(this.head_face, (float)hippo.sitProgress, 0.0F, -0.27314404F, 0.045553092F, 40.0F);
            this.progressRotation(this.arm_right, (float)hippo.sitProgress, -1.5025539F, 0.27314404F, 0.0F, 40.0F);
            this.progressRotation(this.arm_left, (float)hippo.sitProgress, -1.5025539F, -0.27314404F, 0.0F, 40.0F);
            this.progressRotation(this.leg_right, (float)hippo.sitProgress, -1.548107F, 2.5497515F, 0.0F, 40.0F);
            this.progressRotation(this.leg_left, (float)hippo.sitProgress, -1.548107F, -2.5497515F, 0.0F, 40.0F);
         }
      }

      if (hippo.canMove()) {
         this.bob(this.body_main, 0.6F * globalSpeed, 0.6F * globalDegree, true, f, limbSwingAmount);
         this.walk(this.head_neck, 0.6F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, f, limbSwingAmount);
         this.walk(this.head_face, 0.6F * globalSpeed, 0.15F * globalDegree, true, 0.0F, 0.0F, f, limbSwingAmount);
         this.walk(this.arm_right, -0.6F * globalSpeed, 1.4F * globalDegree, true, 0.0F, 1.4F, f, limbSwingAmount);
         this.walk(this.arm_right_2, -0.6F * globalSpeed, 1.4F * globalDegree, false, -1.0F, 1.4F, f, limbSwingAmount * 1.2F);
         this.walk(this.arm_left, -0.6F * globalSpeed, 1.4F * globalDegree, true, 2.0F, 1.4F, f, limbSwingAmount);
         this.walk(this.arm_left_2, -0.6F * globalSpeed, 1.4F * globalDegree, false, 1.0F, 1.4F, f, limbSwingAmount * 1.2F);
         this.walk(this.leg_right, 0.6F * globalSpeed, 1.4F * globalDegree, false, 2.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_right_2, 0.6F * globalSpeed, 1.4F * globalDegree, true, 1.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_left, 0.6F * globalSpeed, 1.4F * globalDegree, false, 0.8F, 0.0F, f, limbSwingAmount);
         this.walk(this.leg_left_2, 0.6F * globalSpeed, 1.4F * globalDegree, true, -0.2F, 0.0F, f, limbSwingAmount);
      }
   }
}

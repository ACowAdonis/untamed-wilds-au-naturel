package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.reptile.EntityTortoise;

public class ModelTortoise extends AdvancedEntityModel<EntityTortoise> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox neck;
   public AdvancedModelBox hand_right;
   public AdvancedModelBox leg_right;
   public AdvancedModelBox main_body;
   public AdvancedModelBox hand_left;
   public AdvancedModelBox leg_left;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox head;
   public AdvancedModelBox shape8;
   private final ModelAnimator animator;

   public ModelTortoise() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.leg_right = new AdvancedModelBox(this, 0, 10);
      this.leg_right.setRotationPoint(-2.4F, -0.4F, 3.9F);
      this.leg_right.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right, 0.18203785F, (float) (-Math.PI / 3), -0.13665928F);
      this.neck = new AdvancedModelBox(this, 0, 17);
      this.neck.setRotationPoint(0.0F, -0.4F, -3.4F);
      this.neck.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.neck, -0.3642502F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 20, 0);
      this.main_body.setRotationPoint(0.0F, 1.0F, 0.0F);
      this.main_body.addBox(-4.0F, -4.0F, -5.0F, 8.0F, 4.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.main_body, -0.091106184F, 0.0F, 0.0F);
      this.shape8 = new AdvancedModelBox(this, 0, 0);
      this.shape8.setRotationPoint(0.0F, -6.0F, 0.0F);
      this.shape8.addBox(-3.0F, 0.0F, -4.0F, 6.0F, 2.0F, 8.0F, 0.0F);
      this.head = new AdvancedModelBox(this, 0, 24);
      this.head.setRotationPoint(0.0F, 0.3F, -3.4F);
      this.head.addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head, 0.3642502F, 0.0F, 0.0F);
      this.head.scaleX = 1.01F;
      this.body_tail = new AdvancedModelBox(this, 0, 0);
      this.body_tail.setRotationPoint(0.0F, 0.0F, 4.8F);
      this.body_tail.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.body_tail, 0.31869712F, 0.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 0, 10);
      this.leg_left.setRotationPoint(2.4F, -0.4F, 3.9F);
      this.leg_left.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left, 0.18203785F, (float) (Math.PI / 3), 0.13665928F);
      this.hand_right = new AdvancedModelBox(this, 10, 10);
      this.hand_right.setRotationPoint(-3.1F, -0.1F, -4.3F);
      this.hand_right.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.hand_right, -0.13665928F, -1.2292354F, 0.18203785F);
      this.hand_left = new AdvancedModelBox(this, 10, 10);
      this.hand_left.mirror = true;
      this.hand_left.setRotationPoint(3.1F, -0.1F, -4.3F);
      this.hand_left.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.hand_left, -0.13665928F, 1.2292354F, -0.18203785F);
      this.body_main = new AdvancedModelBox(this, 26, 15);
      this.body_main.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.body_main.addBox(-3.0F, -0.6F, -4.0F, 6.0F, 2.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_main, -0.045553092F, 0.0F, 0.0F);
      this.body_main.addChild(this.leg_right);
      this.body_main.addChild(this.neck);
      this.body_main.addChild(this.main_body);
      this.main_body.addChild(this.shape8);
      this.neck.addChild(this.head);
      this.body_main.addChild(this.body_tail);
      this.body_main.addChild(this.leg_left);
      this.body_main.addChild(this.hand_right);
      this.body_main.addChild(this.hand_left);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public void animate(IAnimatedEntity entity) {
      this.animator.update(entity);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main, this.neck, this.hand_right, this.leg_right, this.main_body, this.hand_left, this.leg_left, this.body_tail, this.head, this.shape8
      );
   }

   public void setupAnim(EntityTortoise tortoise, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(tortoise);
      limbSwing = (float)((double)limbSwing * -1.2);
      float globalSpeed = 1.4F;
      float globalDegree = 2.0F;
      if (!tortoise.isSitting()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head});
      }

      this.flap(this.hand_left, globalSpeed, globalDegree * 0.6F, false, 0.5F, -0.8F, limbSwing, limbSwingAmount);
      this.walk(this.hand_left, globalSpeed, globalDegree * 0.3F, false, 0.0F, 0.8F, limbSwing, limbSwingAmount);
      this.flap(this.hand_right, globalSpeed, -globalDegree * 0.6F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
      this.walk(this.hand_right, globalSpeed, globalDegree * 0.3F, true, 0.5F, -0.8F, limbSwing, limbSwingAmount);
      this.swing(this.leg_left, globalSpeed, globalDegree * 0.6F, false, 2.8F, 0.1F, limbSwing, limbSwingAmount);
      this.flap(this.leg_left, globalSpeed, globalDegree * 0.2F, false, 1.2F, -0.8F, limbSwing, limbSwingAmount);
      this.swing(this.leg_right, globalSpeed, globalDegree * 0.6F, true, 4.4F, 0.1F, limbSwing, limbSwingAmount);
      this.flap(this.leg_right, globalSpeed, globalDegree * 0.2F, true, 2.8F, -0.8F, limbSwing, limbSwingAmount);
      this.flap(this.body_main, globalSpeed / 2.0F, globalDegree * 0.1F, false, 0.0F, 0.1F, limbSwing / 2.0F, limbSwingAmount);
      this.swing(this.body_main, globalSpeed / 2.0F, globalDegree * 0.1F, false, 0.0F, 0.1F, limbSwing / 2.0F, limbSwingAmount);
      if (tortoise.sitProgress != 0) {
         this.progressPosition(this.body_main, (float)tortoise.sitProgress, 0.0F, 22.6F, 0.0F, 20.0F);
         this.progressPosition(this.neck, (float)tortoise.sitProgress, 0.0F, -1.1F, 0.5F, 20.0F);
         this.progressRotation(this.neck, (float)tortoise.sitProgress, (float)Math.toRadians(-2.61F), 0.0F, 0.0F, 20.0F);
         this.progressPosition(this.head, (float)tortoise.sitProgress, 0.0F, 0.8F, -1.7F, 20.0F);
         this.progressRotation(this.head, (float)tortoise.sitProgress, 0.0F, 0.0F, 0.0F, 20.0F);
         this.progressPosition(this.hand_right, (float)tortoise.sitProgress, -1.1F, -0.1F, -5.7F, 20.0F);
         this.progressRotation(
            this.hand_right, (float)tortoise.sitProgress, (float)Math.toRadians(80.87F), (float)Math.toRadians(-60.0), (float)Math.toRadians(10.43F), 20.0F
         );
         this.progressPosition(this.hand_left, (float)tortoise.sitProgress, 1.1F, -0.1F, -5.7F, 20.0F);
         this.progressRotation(
            this.hand_left, (float)tortoise.sitProgress, (float)Math.toRadians(80.87F), (float)Math.toRadians(60.0), (float)Math.toRadians(-10.43F), 20.0F
         );
         this.progressPosition(this.leg_right, (float)tortoise.sitProgress, -4.4F, -0.4F, 3.9F, 20.0F);
         this.progressRotation(
            this.leg_right, (float)tortoise.sitProgress, (float)Math.toRadians(10.43F), (float)Math.toRadians(-5.22F), (float)Math.toRadians(-80.87F), 20.0F
         );
         this.progressPosition(this.leg_left, (float)tortoise.sitProgress, 4.4F, -0.4F, 3.9F, 20.0F);
         this.progressRotation(
            this.leg_left, (float)tortoise.sitProgress, (float)Math.toRadians(10.43F), (float)Math.toRadians(5.22F), (float)Math.toRadians(80.87F), 20.0F
         );
      }
   }
}

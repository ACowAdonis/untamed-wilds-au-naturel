package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.amphibian.EntityNewt;

public class ModelNewt extends AdvancedEntityModel<EntityNewt> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox head_main;
   private final AdvancedModelBox body_hip;
   private final AdvancedModelBox arm_right;
   private final AdvancedModelBox arm_left;
   private final AdvancedModelBox body_crest;
   private final AdvancedModelBox gill_l_1;
   private final AdvancedModelBox gill_l_2;
   private final AdvancedModelBox gill_r_1;
   private final AdvancedModelBox gill_r_2;
   private final AdvancedModelBox tail_1;
   private final AdvancedModelBox leg_right;
   private final AdvancedModelBox leg_left;
   private final AdvancedModelBox tail_2;
   private final AdvancedModelBox tail_1_crest;

   public ModelNewt() {
      this.texWidth = 32;
      this.texHeight = 16;
      this.leg_left = new AdvancedModelBox(this, 14, 5);
      this.leg_left.setRotationPoint(1.0F, 0.0F, 0.5F);
      this.leg_left.addBox(0.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left, 0.0F, -0.4553564F, 0.57595867F);
      this.body_crest = new AdvancedModelBox(this, 0, 0);
      this.body_crest.setRotationPoint(0.0F, -1.0F, 0.0F);
      this.body_crest.addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 6.0F, 0.0F);
      this.tail_1_crest = new AdvancedModelBox(this, 12, 4);
      this.tail_1_crest.setRotationPoint(0.0F, 0.0F, 2.5F);
      this.tail_1_crest.addBox(0.0F, -2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F);
      this.tail_1 = new AdvancedModelBox(this, 20, 10);
      this.tail_1.setRotationPoint(0.0F, -0.2F, 1.5F);
      this.tail_1.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.tail_1, -0.2443461F, 0.0F, 0.0F);
      this.tail_2 = new AdvancedModelBox(this, 22, 4);
      this.tail_2.setRotationPoint(0.0F, 0.0F, 3.6F);
      this.tail_2.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.tail_2, 0.13665928F, 0.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 14, 5);
      this.arm_right.mirror = true;
      this.arm_right.setRotationPoint(-1.0F, 0.0F, -2.0F);
      this.arm_right.addBox(-3.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_right, 0.0F, -0.5009095F, -0.57595867F);
      this.gill_l_2 = new AdvancedModelBox(this, 0, 0);
      this.gill_l_2.setRotationPoint(1.5F, -0.3F, -0.5F);
      this.gill_l_2.addBox(0.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.gill_l_2, 0.0F, -0.5009095F, -0.18203785F);
      this.leg_right = new AdvancedModelBox(this, 14, 5);
      this.leg_right.mirror = true;
      this.leg_right.setRotationPoint(-1.0F, 0.0F, 0.5F);
      this.leg_right.addBox(-3.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right, 0.0F, 0.4553564F, -0.57595867F);
      this.arm_left = new AdvancedModelBox(this, 14, 5);
      this.arm_left.setRotationPoint(1.0F, 0.0F, -2.0F);
      this.arm_left.addBox(0.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left, 0.0F, 0.5009095F, 0.63739425F);
      this.gill_r_2 = new AdvancedModelBox(this, 0, 0);
      this.gill_r_2.mirror = true;
      this.gill_r_2.setRotationPoint(-1.5F, -0.3F, -0.5F);
      this.gill_r_2.addBox(-2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.gill_r_2, 0.0F, 0.5009095F, 0.18203785F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 22.3F, 0.0F);
      this.body_main.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.body_hip = new AdvancedModelBox(this, 14, 0);
      this.body_hip.setRotationPoint(0.0F, 0.0F, 1.5F);
      this.body_hip.addBox(-1.51F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F);
      this.gill_r_1 = new AdvancedModelBox(this, 0, 0);
      this.gill_r_1.mirror = true;
      this.gill_r_1.setRotationPoint(-1.5F, 0.3F, -0.5F);
      this.gill_r_1.addBox(-2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.gill_r_1, 0.0F, 0.5009095F, -0.22759093F);
      this.head_main = new AdvancedModelBox(this, 0, 10);
      this.head_main.setRotationPoint(0.0F, -0.3F, -2.8F);
      this.head_main.addBox(-1.5F, -1.01F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_main, 0.13665928F, 0.0F, 0.0F);
      this.head_main.scaleX = 1.05F;
      this.gill_l_1 = new AdvancedModelBox(this, 0, 0);
      this.gill_l_1.setRotationPoint(1.5F, 0.3F, -0.5F);
      this.gill_l_1.addBox(0.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.gill_l_1, 0.0F, -0.5009095F, 0.22759093F);
      this.body_hip.addChild(this.leg_left);
      this.body_main.addChild(this.body_crest);
      this.tail_1.addChild(this.tail_1_crest);
      this.body_hip.addChild(this.tail_1);
      this.tail_1.addChild(this.tail_2);
      this.body_main.addChild(this.arm_right);
      this.head_main.addChild(this.gill_l_2);
      this.body_hip.addChild(this.leg_right);
      this.body_main.addChild(this.arm_left);
      this.head_main.addChild(this.gill_r_2);
      this.body_main.addChild(this.body_hip);
      this.head_main.addChild(this.gill_r_1);
      this.body_main.addChild(this.head_main);
      this.head_main.addChild(this.gill_l_1);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_main,
         this.body_hip,
         this.arm_right,
         this.arm_left,
         this.leg_left,
         this.leg_right,
         this.body_crest,
         this.tail_1_crest,
         this.tail_2,
         this.tail_1,
         this.gill_l_1,
         new AdvancedModelBox[]{this.gill_l_2, this.gill_r_1, this.gill_r_2}
      );
   }

   public void setupAnim(EntityNewt newt, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 0.8F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount);
      this.body_hip
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.head_main
         .setScale((float)(1.02F + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      if (!newt.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (newt.isInWater() && !newt.onGround()) {
         this.setRotateAngle(this.body_main, newt.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.head_main.rotateAngleY = Mth.rotLerp(0.05F, this.head_main.rotateAngleY, newt.offset);
      this.body_hip.rotateAngleY = Mth.rotLerp(0.05F, this.body_hip.rotateAngleY, -1.0F * newt.offset);
      this.tail_2.rotateAngleY = Mth.rotLerp(0.05F, this.tail_2.rotateAngleY, -2.0F * newt.offset);
      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.head_main, this.body_main, this.body_hip, this.tail_1, this.tail_2};
      this.chainSwing(bodyParts, globalSpeed * 1.4F, globalDegree * 1.2F, -4.0, limbSwing, limbSwingAmount * 0.3F);
      float onGround = Math.min(0.8F, limbSwingAmount * (float)(newt.onGround() ? 2 : 1));
      if (newt.isInWater()) {
         this.flap(this.arm_left, globalSpeed, globalDegree, false, 0.8F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_left, globalSpeed, globalDegree * 0.8F, false, 1.6F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.arm_right, globalSpeed, globalDegree, false, 2.4F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.leg_right, globalSpeed, globalDegree * 0.8F, false, 3.2F, 1.0F, limbSwing, limbSwingAmount);
         this.flap(this.body_main, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.0F, 0.1F, limbSwing / 2.0F, limbSwingAmount);
         this.swing(this.body_main, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.8F, 0.1F, limbSwing / 3.0F, limbSwingAmount);
         this.chainWave(
            new AdvancedModelBox[]{this.head_main, this.body_main, this.body_hip}, globalSpeed * 0.8F, globalDegree, -4.0, limbSwing, limbSwingAmount * 0.2F
         );
      } else {
         this.swing(this.arm_left, globalSpeed, globalDegree * 1.2F, false, 0.8F, 1.0F, limbSwing, onGround);
         this.swing(this.leg_left, globalSpeed, globalDegree * 1.1F, false, 1.6F, 1.0F, limbSwing, onGround);
         this.swing(this.arm_right, globalSpeed, globalDegree * 1.2F, false, 2.4F, 1.0F, limbSwing, onGround);
         this.swing(this.leg_right, globalSpeed, globalDegree * 1.1F, false, 3.2F, 1.0F, limbSwing, onGround);
      }

      if (newt.swimProgress > 0) {
         this.progressRotation(
            this.arm_right, (float)newt.swimProgress, (float)Math.toRadians(41.74F), (float)Math.toRadians(70.43F), (float)Math.toRadians(-36.52F), 20.0F
         );
         this.progressRotation(
            this.arm_left, (float)newt.swimProgress, (float)Math.toRadians(41.74F), (float)Math.toRadians(-70.43F), (float)Math.toRadians(36.52F), 20.0F
         );
         this.progressRotation(
            this.leg_right, (float)newt.swimProgress, (float)Math.toRadians(49.57F), (float)Math.toRadians(73.04F), (float)Math.toRadians(-33.91F), 20.0F
         );
         this.progressRotation(
            this.leg_left, (float)newt.swimProgress, (float)Math.toRadians(49.57F), (float)Math.toRadians(-73.04F), (float)Math.toRadians(33.91F), 20.0F
         );
      }
   }
}

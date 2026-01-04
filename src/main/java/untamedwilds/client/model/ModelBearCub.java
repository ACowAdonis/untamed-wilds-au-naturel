package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBear;

public class ModelBearCub extends AdvancedEntityModel<EntityBear> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox body_buttocks;
   private final AdvancedModelBox head_face;
   private final AdvancedModelBox arm_right;
   private final AdvancedModelBox arm_left;
   private final AdvancedModelBox leg_left;
   private final AdvancedModelBox leg_right;
   private final AdvancedModelBox head_snout;
   private final AdvancedModelBox ear_left;
   private final AdvancedModelBox ear_right;
   private final AdvancedModelBox eye_left;
   private final AdvancedModelBox eye_right;

   public ModelBearCub() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.arm_right = new AdvancedModelBox(this, 85, 53);
      this.arm_right.setRotationPoint(-2.5F, -1.0F, -1.0F);
      this.arm_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.body_buttocks = new AdvancedModelBox(this, 98, 50);
      this.body_buttocks.setRotationPoint(0.0F, 0.75F, 6.0F);
      this.body_buttocks.addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.body_buttocks, -0.13665928F, 0.0F, 0.0F);
      this.ear_left = new AdvancedModelBox(this, 14, 55);
      this.ear_left.mirror = true;
      this.ear_left.setRotationPoint(2.5F, -2.5F, -2.5F);
      this.ear_left.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, -0.31869712F, -0.31869712F, 0.7285004F);
      this.ear_right = new AdvancedModelBox(this, 14, 55);
      this.ear_right.setRotationPoint(-2.5F, -2.5F, -2.5F);
      this.ear_right.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, -0.31869712F, 0.31869712F, -0.7285004F);
      this.arm_left = new AdvancedModelBox(this, 85, 53);
      this.arm_left.mirror = true;
      this.arm_left.setRotationPoint(2.5F, -1.0F, -1.0F);
      this.arm_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 85, 53);
      this.leg_left.mirror = true;
      this.leg_left.setRotationPoint(2.8F, -1.9F, 1.0F);
      this.leg_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left, 0.13665928F, 0.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 81, 41);
      this.body_main.setRotationPoint(0.0F, 17.0F, -2.0F);
      this.body_main.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 20, 55);
      this.eye_right.setRotationPoint(-0.51F, 0.0F, -3.01F);
      this.eye_right.addBox(-2.0F, -0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 18, 58);
      this.head_snout.setRotationPoint(0.0F, 1.0F, -1.3F);
      this.head_snout.addBox(-1.5F, -2.0F, -5.0F, 3.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.091106184F, 0.0F, 0.0F);
      this.leg_right = new AdvancedModelBox(this, 85, 53);
      this.leg_right.setRotationPoint(-2.8F, -1.9F, 1.0F);
      this.leg_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right, 0.13665928F, 0.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 20, 55);
      this.eye_left.mirror = true;
      this.eye_left.setRotationPoint(0.51F, 0.0F, -3.01F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 0, 55);
      this.head_face.setRotationPoint(0.0F, -1.5F, -2.5F);
      this.head_face.addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.18203785F, 0.0F, 0.0F);
      this.body_main.addChild(this.arm_right);
      this.body_main.addChild(this.body_buttocks);
      this.head_face.addChild(this.ear_left);
      this.head_face.addChild(this.ear_right);
      this.body_main.addChild(this.arm_left);
      this.body_buttocks.addChild(this.leg_left);
      this.head_face.addChild(this.eye_right);
      this.head_face.addChild(this.head_snout);
      this.body_buttocks.addChild(this.leg_right);
      this.head_face.addChild(this.eye_left);
      this.body_main.addChild(this.head_face);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.body_buttocks,
         this.head_face,
         this.arm_right,
         this.arm_left,
         this.leg_left,
         this.leg_right,
         this.head_snout,
         this.ear_left,
         this.ear_right,
         this.eye_left,
         this.eye_right,
         new AdvancedModelBox[0]
      );
   }

   public void setupAnim(EntityBear bear, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_buttocks
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.body_main, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!bear.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-0.49F, 0.0F, -2.0F);
         this.eye_left.setRotationPoint(0.49F, 0.0F, -2.0F);
      }

      this.faceTarget(netHeadYaw, headPitch, 1.0F, new AdvancedModelBox[]{this.head_face});
      if (bear.canMove()) {
         this.arm_right.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
         this.arm_left.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_right.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_left.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
      }

      if (bear.sitProgress > 0) {
         this.progressRotation(this.body_main, (float)bear.sitProgress, -0.5462881F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.body_buttocks, (float)bear.sitProgress, -0.7285004F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)bear.sitProgress, 0.18203785F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)bear.sitProgress, 0.18203785F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_face, (float)bear.sitProgress, 0.3642502F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right, (float)bear.sitProgress, -0.045553092F, 0.0F, 0.22759093F, 40.0F);
         this.progressRotation(this.leg_left, (float)bear.sitProgress, -0.045553092F, 0.0F, -0.22759093F, 40.0F);
         this.progressPosition(this.body_main, (float)bear.sitProgress, 0.0F, 17.5F, -1.0F, 40.0F);
         this.progressPosition(this.body_buttocks, (float)bear.sitProgress, 0.0F, 2.0F, 3.5F, 40.0F);
      } else if (bear.sleepProgress > 0) {
         this.progressRotation(this.body_buttocks, (float)bear.sleepProgress, -0.13665928F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right, (float)bear.sleepProgress, 1.5025539F, -0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left, (float)bear.sleepProgress, 1.5025539F, 0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)bear.sleepProgress, -1.3658947F, -0.3642502F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)bear.sleepProgress, -1.3658947F, 0.3642502F, 0.0F, 40.0F);
         this.progressPosition(this.body_main, (float)bear.sleepProgress, 0.0F, 22.0F, -2.0F, 40.0F);
         this.progressPosition(this.body_buttocks, (float)bear.sleepProgress, 0.0F, 0.0F, 6.0F, 40.0F);
         this.progressPosition(this.head_face, (float)bear.sleepProgress, 0.0F, -0.5F, -2.5F, 40.0F);
         this.progressPosition(this.arm_right, (float)bear.sleepProgress, -2.5F, 0.0F, -1.0F, 40.0F);
         this.progressPosition(this.arm_left, (float)bear.sleepProgress, 2.5F, 0.0F, -1.0F, 40.0F);
         this.progressPosition(this.leg_right, (float)bear.sleepProgress, -2.8F, 0.0F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left, (float)bear.sleepProgress, 2.8F, 0.0F, 1.0F, 40.0F);
      }
   }
}

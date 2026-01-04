package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBigCat;

public class ModelBigCatCub extends AdvancedEntityModel<EntityBigCat> {
   private final AdvancedModelBox main_body;
   private final AdvancedModelBox main_buttocks;
   private final AdvancedModelBox head_face;
   private final AdvancedModelBox arm_right;
   private final AdvancedModelBox arm_left;
   private final AdvancedModelBox leg_left;
   private final AdvancedModelBox leg_right;
   private final AdvancedModelBox tail_1;
   private final AdvancedModelBox tail_2;
   private final AdvancedModelBox head_snout;
   private final AdvancedModelBox ear_left;
   private final AdvancedModelBox ear_right;
   private final AdvancedModelBox eye_left;
   private final AdvancedModelBox eye_right;

   public ModelBigCatCub() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.arm_left = new AdvancedModelBox(this, 114, 32);
      this.arm_left.mirror = true;
      this.arm_left.setRotationPoint(2.2F, -1.0F, -1.0F);
      this.arm_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.eye_right = new AdvancedModelBox(this, 94, 25);
      this.eye_right.setRotationPoint(-0.51F, 0.0F, -3.01F);
      this.eye_right.addBox(-2.0F, -0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.main_buttocks = new AdvancedModelBox(this, 96, 12);
      this.main_buttocks.setRotationPoint(0.0F, 0.0F, 6.5F);
      this.main_buttocks.addBox(-2.5F, -3.0F, -3.5F, 5.0F, 6.0F, 7.0F, 0.0F);
      this.ear_right = new AdvancedModelBox(this, 110, 25);
      this.ear_right.setRotationPoint(-2.5F, -2.5F, -2.5F);
      this.ear_right.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, -0.31869712F, 0.31869712F, -0.7285004F);
      this.head_face = new AdvancedModelBox(this, 96, 25);
      this.head_face.setRotationPoint(0.0F, -2.5F, -3.0F);
      this.head_face.addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.13665928F, 0.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 114, 32);
      this.leg_left.mirror = true;
      this.leg_left.setRotationPoint(2.4F, -1.0F, 1.0F);
      this.leg_left.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.tail_2 = new AdvancedModelBox(this, 104, 37);
      this.tail_2.setRotationPoint(0.0F, 0.0F, 6.0F);
      this.tail_2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_2, 0.4553564F, 0.0F, 0.0F);
      this.leg_right = new AdvancedModelBox(this, 114, 32);
      this.leg_right.setRotationPoint(-2.4F, -1.0F, 1.0F);
      this.leg_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.ear_left = new AdvancedModelBox(this, 110, 25);
      this.ear_left.mirror = true;
      this.ear_left.setRotationPoint(2.5F, -2.5F, -2.5F);
      this.ear_left.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, -0.31869712F, -0.31869712F, 0.7285004F);
      this.main_body = new AdvancedModelBox(this, 96, 0);
      this.main_body.setRotationPoint(0.0F, 17.0F, -2.0F);
      this.main_body.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 114, 25);
      this.head_snout.setRotationPoint(0.0F, 1.0F, -1.3F);
      this.head_snout.addBox(-1.5F, -2.0F, -5.0F, 3.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.091106184F, 0.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 114, 32);
      this.arm_right.setRotationPoint(-2.2F, -1.0F, -1.0F);
      this.arm_right.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.tail_1 = new AdvancedModelBox(this, 94, 34);
      this.tail_1.setRotationPoint(0.0F, -2.0F, 3.0F);
      this.tail_1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_1, -0.95609134F, 0.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 94, 25);
      this.eye_left.mirror = true;
      this.eye_left.setRotationPoint(0.51F, 0.0F, -3.01F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.main_body.addChild(this.arm_left);
      this.head_face.addChild(this.eye_right);
      this.main_body.addChild(this.main_buttocks);
      this.head_face.addChild(this.ear_right);
      this.main_body.addChild(this.head_face);
      this.main_buttocks.addChild(this.leg_left);
      this.tail_1.addChild(this.tail_2);
      this.main_buttocks.addChild(this.leg_right);
      this.head_face.addChild(this.ear_left);
      this.head_face.addChild(this.head_snout);
      this.main_body.addChild(this.arm_right);
      this.main_buttocks.addChild(this.tail_1);
      this.head_face.addChild(this.eye_left);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.main_buttocks,
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
         new AdvancedModelBox[]{this.tail_1, this.tail_2}
      );
   }

   public void setupAnim(EntityBigCat big_cat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.main_buttocks
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.main_body, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.flap(this.tail_1, 0.8F, 0.4F, true, 0.0F, 0.0F, ageInTicks / 6.0F, 2.0F);
      this.flap(this.tail_2, 0.8F, 0.4F, true, 0.5F, 0.0F, ageInTicks / 6.0F, 2.0F);
      if (!big_cat.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-0.49F, 0.0F, -2.0F);
         this.eye_left.setRotationPoint(0.49F, 0.0F, -2.0F);
      }

      this.faceTarget(netHeadYaw, headPitch, 1.0F, new AdvancedModelBox[]{this.head_face});
      if (big_cat.canMove()) {
         this.arm_right.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
         this.arm_left.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_right.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_left.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
      }

      if (big_cat.sitProgress > 0) {
         this.progressRotation(this.main_body, (float)big_cat.sitProgress, -0.5462881F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.main_buttocks, (float)big_cat.sitProgress, -0.7285004F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)big_cat.sitProgress, 0.18203785F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)big_cat.sitProgress, 0.18203785F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_face, (float)big_cat.sitProgress, 0.3642502F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right, (float)big_cat.sitProgress, -0.045553092F, 0.0F, 0.22759093F, 40.0F);
         this.progressRotation(this.leg_left, (float)big_cat.sitProgress, -0.045553092F, 0.0F, -0.22759093F, 40.0F);
         this.progressRotation(this.tail_1, (float)big_cat.sitProgress, 1.2685004F, 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.main_body, (float)big_cat.sitProgress, 0.0F, 17.5F, -1.0F, 40.0F);
         this.progressPosition(this.main_buttocks, (float)big_cat.sitProgress, 0.0F, 2.0F, 3.5F, 40.0F);
      } else if (big_cat.isSleeping()) {
         this.progressRotation(this.main_buttocks, (float)big_cat.sleepProgress, -0.13665928F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right, (float)big_cat.sleepProgress, 1.5025539F, -0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left, (float)big_cat.sleepProgress, 1.5025539F, 0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)big_cat.sleepProgress, -1.3658947F, -0.3642502F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)big_cat.sleepProgress, -1.3658947F, 0.3642502F, 0.0F, 40.0F);
         this.progressPosition(this.main_body, (float)big_cat.sleepProgress, 0.0F, 22.0F, -2.0F, 40.0F);
         this.progressPosition(this.main_buttocks, (float)big_cat.sleepProgress, 0.0F, 0.0F, 6.0F, 40.0F);
         this.progressPosition(this.head_face, (float)big_cat.sleepProgress, 0.0F, -0.5F, -2.5F, 40.0F);
         this.progressPosition(this.arm_right, (float)big_cat.sleepProgress, -2.5F, 0.0F, -1.0F, 40.0F);
         this.progressPosition(this.arm_left, (float)big_cat.sleepProgress, 2.5F, 0.0F, -1.0F, 40.0F);
         this.progressPosition(this.leg_right, (float)big_cat.sleepProgress, -2.8F, 0.0F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left, (float)big_cat.sleepProgress, 2.8F, 0.0F, 1.0F, 40.0F);
      }
   }
}

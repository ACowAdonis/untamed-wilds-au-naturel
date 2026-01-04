package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBoar;

public class ModelBoarPiglet extends AdvancedEntityModel<EntityBoar> {
   public AdvancedModelBox main_body;
   public AdvancedModelBox leg_right;
   public AdvancedModelBox leg_left;
   public AdvancedModelBox head_main;
   public AdvancedModelBox arm_right;
   public AdvancedModelBox arm_left;
   public AdvancedModelBox shape14;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox ear_left;
   public AdvancedModelBox ear_right;
   public AdvancedModelBox eye_left;
   public AdvancedModelBox eye_right;

   public ModelBoarPiglet() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.arm_left = new AdvancedModelBox(this, 42, 40);
      this.arm_left.setRotationPoint(1.3F, 0.2F, -2.99F);
      this.arm_left.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_left, 0.045553092F, 0.0F, 0.0F);
      this.shape14 = new AdvancedModelBox(this, 42, 59);
      this.shape14.setRotationPoint(0.0F, -2.5F, 4.0F);
      this.shape14.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.shape14, 0.18203785F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 24, 40);
      this.main_body.setRotationPoint(0.0F, 18.0F, 0.0F);
      this.main_body.addBox(-2.0F, -2.5F, -4.0F, 4.0F, 5.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.main_body, -0.045553092F, 0.0F, 0.0F);
      this.ear_right = new AdvancedModelBox(this, 24, 43);
      this.ear_right.mirror = true;
      this.ear_right.setRotationPoint(-1.1F, -1.2F, -1.5F);
      this.ear_right.addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, 0.27314404F, 0.7740535F, 0.5462881F);
      this.eye_right = new AdvancedModelBox(this, 0, 20);
      this.eye_right.setRotationPoint(-2.01F, -1.0F, -2.0F);
      this.eye_right.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.leg_right = new AdvancedModelBox(this, 42, 40);
      this.leg_right.mirror = true;
      this.leg_right.setRotationPoint(-1.3F, -0.3F, 3.0F);
      this.leg_right.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_right, 0.045553092F, 0.0F, 0.0F);
      this.eye_left = new AdvancedModelBox(this, 0, 20);
      this.eye_left.setRotationPoint(2.01F, -1.0F, -2.0F);
      this.eye_left.addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F);
      this.ear_left = new AdvancedModelBox(this, 24, 43);
      this.ear_left.setRotationPoint(1.1F, -1.2F, -1.5F);
      this.ear_left.addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, 0.27314404F, -0.7740535F, -0.5462881F);
      this.leg_left = new AdvancedModelBox(this, 42, 40);
      this.leg_left.setRotationPoint(1.3F, -0.3F, 3.0F);
      this.leg_left.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.leg_left, 0.045553092F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 24, 53);
      this.head_main.setRotationPoint(0.0F, -0.8F, -3.0F);
      this.head_main.addBox(-2.0F, -2.5F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_main, 0.31869712F, 0.0F, 0.0F);
      this.arm_right = new AdvancedModelBox(this, 42, 40);
      this.arm_right.mirror = true;
      this.arm_right.setRotationPoint(-1.3F, 0.0F, -2.99F);
      this.arm_right.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.arm_right, 0.045553092F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 40, 53);
      this.head_snout.setRotationPoint(0.0F, -0.5F, -2.7F);
      this.head_snout.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.18203785F, 0.0F, 0.0F);
      this.main_body.addChild(this.arm_left);
      this.main_body.addChild(this.shape14);
      this.head_main.addChild(this.ear_right);
      this.head_main.addChild(this.eye_right);
      this.main_body.addChild(this.leg_right);
      this.head_main.addChild(this.eye_left);
      this.head_main.addChild(this.ear_left);
      this.main_body.addChild(this.leg_left);
      this.main_body.addChild(this.head_main);
      this.main_body.addChild(this.arm_right);
      this.head_main.addChild(this.head_snout);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.leg_right,
         this.leg_left,
         this.head_main,
         this.arm_right,
         this.arm_left,
         this.shape14,
         this.head_snout,
         this.ear_left,
         this.ear_right,
         this.eye_left,
         this.eye_right,
         new AdvancedModelBox[0]
      );
   }

   public void setupAnim(EntityBoar bear, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.bob(this.main_body, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!bear.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-0.49F, 0.0F, -2.0F);
         this.eye_left.setRotationPoint(0.49F, 0.0F, -2.0F);
      }

      this.faceTarget(netHeadYaw, headPitch, 1.0F, new AdvancedModelBox[]{this.head_main});
      if (bear.canMove()) {
         this.arm_right.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
         this.arm_left.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_right.rotateAngleX = Mth.cos(limbSwing * 0.5F + (float) Math.PI) * 1.4F * limbSwingAmount;
         this.leg_left.rotateAngleX = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
      } else if (bear.sitProgress > 0) {
         this.progressRotation(this.leg_right, (float)bear.sitProgress, 1.5025539F, -0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left, (float)bear.sitProgress, 1.5025539F, 0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)bear.sitProgress, -1.3658947F, -0.3642502F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)bear.sitProgress, -1.3658947F, 0.3642502F, 0.0F, 40.0F);
         this.progressPosition(this.main_body, (float)bear.sitProgress, 0.0F, 22.0F, -2.0F, 40.0F);
         this.progressPosition(this.head_main, (float)bear.sitProgress, 0.0F, -0.5F, -2.5F, 40.0F);
         this.progressPosition(this.arm_right, (float)bear.sitProgress, -1.0F, 1.0F, -1.0F, 40.0F);
         this.progressPosition(this.arm_left, (float)bear.sitProgress, 1.0F, 1.0F, -1.0F, 40.0F);
         this.progressPosition(this.leg_right, (float)bear.sitProgress, -1.3F, 1.0F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left, (float)bear.sitProgress, 1.3F, 1.0F, 1.0F, 40.0F);
      } else if (bear.sleepProgress > 0) {
         this.progressRotation(this.leg_right, (float)bear.sleepProgress, 1.5025539F, -0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left, (float)bear.sleepProgress, 1.5025539F, 0.4098033F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left, (float)bear.sleepProgress, -1.3658947F, -0.3642502F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right, (float)bear.sleepProgress, -1.3658947F, 0.3642502F, 0.0F, 40.0F);
         this.progressPosition(this.main_body, (float)bear.sleepProgress, 0.0F, 22.0F, -2.0F, 40.0F);
         this.progressPosition(this.head_main, (float)bear.sleepProgress, 0.0F, -0.5F, -2.5F, 40.0F);
         this.progressPosition(this.arm_right, (float)bear.sleepProgress, -1.0F, 1.0F, -1.0F, 40.0F);
         this.progressPosition(this.arm_left, (float)bear.sleepProgress, 1.0F, 1.0F, -1.0F, 40.0F);
         this.progressPosition(this.leg_right, (float)bear.sleepProgress, -1.3F, 1.0F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left, (float)bear.sleepProgress, 1.3F, 1.0F, 1.0F, 40.0F);
      }
   }
}

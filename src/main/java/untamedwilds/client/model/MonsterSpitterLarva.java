package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.relict.EntitySpitter;

public class MonsterSpitterLarva extends AdvancedEntityModel<EntitySpitter> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox back_sail;
   private final AdvancedModelBox head_snout;
   private final AdvancedModelBox arm_left_lower;
   private final AdvancedModelBox arm_right_lower;
   private final AdvancedModelBox leg_left_lower;
   private final AdvancedModelBox leg_right_lower;
   private final AdvancedModelBox head_tube;
   private final AdvancedModelBox arm_left_claw;
   private final AdvancedModelBox arm_right_claw;
   private final AdvancedModelBox leg_left_claw;
   private final AdvancedModelBox leg_right_claw;

   public MonsterSpitterLarva() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 23.0F, 1.0F);
      this.body_main.addBox(-3.5F, -5.0F, -6.0F, 7.0F, 4.0F, 12.0F, 0.0F);
      this.arm_left_claw = new AdvancedModelBox(this, 54, 34);
      this.arm_left_claw.mirror = true;
      this.arm_left_claw.setRotationPoint(0.0F, 3.0F, -2.5F);
      this.arm_left_claw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_claw, 1.548107F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 2, 38);
      this.head_snout.setRotationPoint(0.0F, -3.5F, -6.0F);
      this.head_snout.addBox(-2.5F, -3.0F, -4.0F, 5.0F, 4.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_snout, -0.18203785F, 0.0F, 0.0F);
      this.head_tube = new AdvancedModelBox(this, 2, 49);
      this.head_tube.setRotationPoint(0.0F, -1.5F, -4.0F);
      this.head_tube.addBox(-1.5F, -1.5F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F);
      this.leg_left_lower = new AdvancedModelBox(this, 58, 46);
      this.leg_left_lower.mirror = true;
      this.leg_left_lower.setRotationPoint(3.0F, -4.0F, 5.0F);
      this.leg_left_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left_lower, -0.13665928F, -0.22759093F, 0.10995574F);
      this.arm_right_claw = new AdvancedModelBox(this, 54, 34);
      this.arm_right_claw.mirror = true;
      this.arm_right_claw.setRotationPoint(0.0F, 3.0F, -2.5F);
      this.arm_right_claw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_claw, 1.548107F, 0.0F, 0.0F);
      this.leg_right_claw = new AdvancedModelBox(this, 54, 40);
      this.leg_right_claw.setRotationPoint(0.0F, 3.8F, -2.0F);
      this.leg_right_claw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_claw, 1.6845918F, 0.0F, 0.0F);
      this.leg_right_lower = new AdvancedModelBox(this, 58, 46);
      this.leg_right_lower.mirror = true;
      this.leg_right_lower.setRotationPoint(-3.0F, -4.0F, 5.0F);
      this.leg_right_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right_lower, -0.13665928F, 0.22759093F, -0.10995574F);
      this.arm_right_lower = new AdvancedModelBox(this, 42, 33);
      this.arm_right_lower.mirror = true;
      this.arm_right_lower.setRotationPoint(-3.0F, -2.0F, -2.5F);
      this.arm_right_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_lower, -0.5009095F, 0.0F, -0.091106184F);
      this.arm_left_lower = new AdvancedModelBox(this, 42, 33);
      this.arm_left_lower.mirror = true;
      this.arm_left_lower.setRotationPoint(3.0F, -2.0F, -2.5F);
      this.arm_left_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_lower, -0.5009095F, 0.0F, 0.091106184F);
      this.back_sail = new AdvancedModelBox(this, 82, 44);
      this.back_sail.setRotationPoint(0.0F, -3.0F, 3.0F);
      this.back_sail.addBox(-2.5F, -5.0F, -4.0F, 5.0F, 5.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.back_sail, -0.27314404F, 0.0F, 0.0F);
      this.leg_left_claw = new AdvancedModelBox(this, 54, 40);
      this.leg_left_claw.setRotationPoint(0.0F, 3.8F, -2.0F);
      this.leg_left_claw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_claw, 1.6845918F, 0.0F, 0.0F);
      this.arm_left_lower.addChild(this.arm_left_claw);
      this.body_main.addChild(this.head_snout);
      this.head_snout.addChild(this.head_tube);
      this.body_main.addChild(this.leg_left_lower);
      this.arm_right_lower.addChild(this.arm_right_claw);
      this.leg_right_lower.addChild(this.leg_right_claw);
      this.body_main.addChild(this.leg_right_lower);
      this.body_main.addChild(this.arm_right_lower);
      this.body_main.addChild(this.arm_left_lower);
      this.body_main.addChild(this.back_sail);
      this.leg_left_lower.addChild(this.leg_left_claw);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.back_sail,
         this.leg_right_lower,
         this.arm_right_claw,
         this.leg_left_lower,
         this.arm_left_claw,
         this.arm_right_lower,
         this.arm_left_lower,
         this.head_snout,
         this.head_tube
      );
   }

   public void setupAnim(EntitySpitter spitter, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 2.4F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount * 2.0F);
      limbSwing *= 0.5F;
      double scaleX = Math.sin((double)(ageInTicks / 20.0F));
      double scaleY = Math.sin((double)(ageInTicks / 16.0F));
      this.body_main.setScale((float)(1.0 + scaleX * 0.08F), (float)(1.0 + scaleY * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_lower, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_lower, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_lower, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_lower, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!spitter.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_snout});
      }

      if (spitter.isInWater() && !spitter.onGround()) {
         limbSwing = ageInTicks / 3.0F;
         limbSwingAmount = 0.5F;
         this.body_main.rotationPointY += 4.0F;
         float pitch = Mth.clamp(spitter.getXRot() - 10.0F, -25.0F, 25.0F);
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (spitter.canMove()) {
         if (!(spitter.getCurrentSpeed() > 0.1F) && !spitter.isAngry()) {
            this.bob(this.arm_right_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, globalDegree, true, 2.4F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, globalDegree, true, 3.4F, 0.0F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.body_main, 0.5F * globalSpeed, 0.5F, false, limbSwing, limbSwingAmount);
            this.walk(this.body_main, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_right_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, globalDegree, true, 0.6F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, globalDegree, true, 1.4F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_lower, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
         }
      }

      if (spitter.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)spitter.sitProgress, 0.0F, 23.0F, 1.0F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)spitter.sitProgress, -1.2292354F, -0.22759093F, 0.045553092F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)spitter.sitProgress, -1.8668041F, 0.0F, 0.10803588F, 40.0F);
         this.progressRotation(this.leg_right_lower, (float)spitter.sitProgress, -1.2292354F, 0.22759093F, -0.045553092F, 40.0F);
         this.progressRotation(this.arm_right_lower, (float)spitter.sitProgress, -1.8668041F, 0.0F, -0.10803588F, 40.0F);
      }

      if (spitter.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)spitter.sleepProgress, -4.0F, 23.0F, -4.0F, 40.0F);
         this.progressRotation(this.body_main, (float)spitter.sleepProgress, 0.0F, 0.0F, -1.5025539F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)spitter.sleepProgress, -0.13665928F, 0.0F, 0.7740535F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)spitter.sleepProgress, -0.5009095F, -0.09110619F, 1.0472F, 40.0F);
      }
   }
}

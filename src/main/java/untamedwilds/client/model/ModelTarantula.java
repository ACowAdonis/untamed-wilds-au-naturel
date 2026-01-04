package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.arthropod.EntityTarantula;

public class ModelTarantula extends AdvancedEntityModel<EntityTarantula> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox abdomen;
   public AdvancedModelBox legR4;
   public AdvancedModelBox legR3;
   public AdvancedModelBox legR2;
   public AdvancedModelBox legR1;
   public AdvancedModelBox legL4;
   public AdvancedModelBox legL3;
   public AdvancedModelBox legL2;
   public AdvancedModelBox legL1;
   public AdvancedModelBox legR42;
   public AdvancedModelBox legR32;
   public AdvancedModelBox legR22;
   public AdvancedModelBox legR12;
   public AdvancedModelBox legL42;
   public AdvancedModelBox legL32;
   public AdvancedModelBox legL22;
   public AdvancedModelBox legL12;

   public ModelTarantula() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.legL32 = new AdvancedModelBox(this, 24, 16);
      this.legL32.setRotationPoint(-4.0F, -0.9F, 0.01F);
      this.legL32.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.legL12 = new AdvancedModelBox(this, 0, 16);
      this.legL12.setRotationPoint(-3.5F, -0.9F, -0.01F);
      this.legL12.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legL12, 0.41887903F, 0.0F, (float) (Math.PI / 9));
      this.legL1 = new AdvancedModelBox(this, 0, 12);
      this.legL1.setRotationPoint(2.0F, 1.0F, -2.6F);
      this.legL1.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legL1, 0.13962634F, (float) (-Math.PI * 3.0 / 4.0), (float) (-Math.PI / 10));
      this.legR2 = new AdvancedModelBox(this, 12, 12);
      this.legR2.setRotationPoint(-2.6F, 1.0F, -1.0F);
      this.legR2.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legR2, (float) (-Math.PI / 12), (float) (-Math.PI / 10), 0.57595867F);
      this.legL3 = new AdvancedModelBox(this, 24, 12);
      this.legL3.setRotationPoint(2.6F, 1.0F, 1.0F);
      this.legL3.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legL3, (float) (-Math.PI / 10), 2.687807F, -0.57595867F);
      this.legR1 = new AdvancedModelBox(this, 0, 12);
      this.legR1.setRotationPoint(-2.0F, 1.0F, -2.6F);
      this.legR1.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legR1, -0.045553092F, (float) (-Math.PI / 4), (float) (Math.PI / 10));
      this.legR32 = new AdvancedModelBox(this, 24, 16);
      this.legR32.setRotationPoint(-4.0F, -0.9F, 0.01F);
      this.legR32.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.legL2 = new AdvancedModelBox(this, 12, 12);
      this.legL2.setRotationPoint(2.6F, 1.0F, -1.0F);
      this.legL2.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legL2, (float) (Math.PI / 12), (float) (-Math.PI * 9.0 / 10.0), -0.57595867F);
      this.legR22 = new AdvancedModelBox(this, 12, 16);
      this.legR22.setRotationPoint(-4.0F, -0.9F, -0.01F);
      this.legR22.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.legL4 = new AdvancedModelBox(this, 36, 12);
      this.legL4.setRotationPoint(3.0F, 1.0F, 2.0F);
      this.legL4.addBox(-4.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legL4, (float) (-Math.PI / 9), 2.268928F, (float) (-Math.PI / 9));
      this.legR3 = new AdvancedModelBox(this, 24, 12);
      this.legR3.setRotationPoint(-2.6F, 1.0F, 1.0F);
      this.legR3.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legR3, (float) (Math.PI / 10), 0.41887903F, 0.57595867F);
      this.legR12 = new AdvancedModelBox(this, 0, 16);
      this.legR12.setRotationPoint(-3.5F, -0.9F, -0.01F);
      this.legR12.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legR12, -0.41887903F, 0.0F, (float) (Math.PI / 9));
      this.legR42 = new AdvancedModelBox(this, 36, 16);
      this.legR42.setRotationPoint(-3.5F, 0.0F, 1.01F);
      this.legR42.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legR42, 0.0F, 0.0F, (float) (Math.PI / 4));
      this.legL42 = new AdvancedModelBox(this, 36, 16);
      this.legL42.setRotationPoint(-3.5F, 0.0F, -0.99F);
      this.legL42.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legL42, 0.0F, 0.0F, (float) (Math.PI / 4));
      this.legL22 = new AdvancedModelBox(this, 12, 16);
      this.legL22.setRotationPoint(-4.0F, -0.9F, -0.01F);
      this.legL22.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.body_main.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F);
      this.legR4 = new AdvancedModelBox(this, 36, 12);
      this.legR4.setRotationPoint(-3.0F, 1.0F, 2.0F);
      this.legR4.addBox(-4.0F, -1.0F, 0.0F, 4.0F, 2.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.legR4, (float) (Math.PI / 9), 0.87266463F, (float) (Math.PI / 9));
      this.abdomen = new AdvancedModelBox(this, 24, 0);
      this.abdomen.setRotationPoint(0.0F, 1.0F, 2.5F);
      this.abdomen.addBox(-3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.abdomen, (float) (-Math.PI / 18), 0.0F, 0.0F);
      this.legL3.addChild(this.legL32);
      this.legL1.addChild(this.legL12);
      this.body_main.addChild(this.legL1);
      this.body_main.addChild(this.legR2);
      this.body_main.addChild(this.legL3);
      this.body_main.addChild(this.legR1);
      this.legR3.addChild(this.legR32);
      this.body_main.addChild(this.legL2);
      this.legR2.addChild(this.legR22);
      this.body_main.addChild(this.legL4);
      this.body_main.addChild(this.legR3);
      this.legR1.addChild(this.legR12);
      this.legR4.addChild(this.legR42);
      this.legL4.addChild(this.legL42);
      this.legL2.addChild(this.legL22);
      this.body_main.addChild(this.legR4);
      this.body_main.addChild(this.abdomen);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.abdomen,
         this.legR4,
         this.legR3,
         this.legR2,
         this.legR1,
         this.legL4,
         this.legL3,
         this.legL2,
         this.legL1,
         this.legR42,
         this.legR32,
         new AdvancedModelBox[]{this.legR22, this.legR12, this.legL42, this.legL32, this.legL22, this.legL12}
      );
   }

   public void setupAnim(EntityTarantula tarantula, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      limbSwingAmount = Math.min(0.4F, limbSwingAmount);
      float globalSpeed = 1.2F;
      float globalDegree = 1.4F;
      this.abdomen
         .setScale(
            (float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F)
         );
      this.bob(this.body_main, 0.6F, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.legR1, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legL1, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legR2, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legL2, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legR3, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legL3, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legR4, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.legL4, 0.6F, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (tarantula.aggroProgress != 0) {
         this.progressRotation(this.legR1, (float)tarantula.aggroProgress, (float)Math.toRadians(-41.74), 0.0F, (float)Math.toRadians(75.65F), 40.0F);
         this.progressRotation(
            this.legL1, (float)tarantula.aggroProgress, (float)Math.toRadians(41.74), (float)Math.toRadians(-180.0), (float)Math.toRadians(-75.65F), 40.0F
         );
      } else {
         this.animateArthropodLeg(this.legL1, this.legL12, globalSpeed, globalDegree, 3, ageInTicks, limbSwingAmount);
         this.animateArthropodLeg(this.legR1, this.legR12, globalSpeed, globalDegree, 0, ageInTicks, limbSwingAmount);
      }

      if (!tarantula.isClimbing()) {
         this.animateArthropodLeg(this.legL2, this.legL22, globalSpeed, globalDegree, 4, ageInTicks, limbSwingAmount);
         this.animateArthropodLeg(this.legL3, this.legL32, globalSpeed, globalDegree, 0, ageInTicks, limbSwingAmount);
         this.animateArthropodLeg(this.legL4, this.legL42, globalSpeed, globalDegree, 1, ageInTicks, limbSwingAmount);
         this.animateArthropodLeg(this.legR2, this.legR22, globalSpeed, globalDegree, 2, ageInTicks, limbSwingAmount);
         this.animateArthropodLeg(this.legR3, this.legR32, globalSpeed, globalDegree, 3, ageInTicks, limbSwingAmount);
         this.animateArthropodLeg(this.legR4, this.legR42, globalSpeed, globalDegree, 5, ageInTicks, limbSwingAmount);
      }

      this.progressRotation(
         this.body_main,
         (float)tarantula.climbProgress,
         (float) (Math.PI / 2) * (float)(tarantula.invertClimbing ? 1 : -1),
         this.body_main.rotateAngleY,
         this.body_main.rotateAngleZ,
         20.0F
      );
      if (tarantula.isClimbing() && Math.abs(tarantula.getDeltaMovement().y()) > 0.05F) {
         float swing = tarantula.getDeltaMovement().y() != 0.0 ? 0.3F : limbSwingAmount;
         this.animateArthropodLeg(this.legL2, this.legL22, globalSpeed, globalDegree, 4, ageInTicks / 3.0F, swing);
         this.animateArthropodLeg(this.legL3, this.legL32, globalSpeed, globalDegree, 0, ageInTicks / 3.0F, swing);
         this.animateArthropodLeg(this.legL4, this.legL42, globalSpeed, globalDegree, 1, ageInTicks / 3.0F, swing);
         this.animateArthropodLeg(this.legR2, this.legR22, globalSpeed, globalDegree, 2, ageInTicks / 3.0F, swing);
         this.animateArthropodLeg(this.legR3, this.legR32, globalSpeed, globalDegree, 3, ageInTicks / 3.0F, swing);
         this.animateArthropodLeg(this.legR4, this.legR42, globalSpeed, globalDegree, 5, ageInTicks / 3.0F, swing);
      }
   }

   private void animateArthropodLeg(
      AdvancedModelBox limb_1, AdvancedModelBox limb_2, float speed, float degree, int offset, float limbSwing, float limbSwingAmount
   ) {
      this.swing(limb_1, speed, degree * 1.2F, false, (float)offset, 0.1F, limbSwing, limbSwingAmount);
      this.flap(limb_1, speed, degree * 0.8F, true, (float)offset + 1.5F, 0.2F, limbSwing, limbSwingAmount);
      this.flap(limb_2, speed, degree * 0.8F, true, (float)offset + 1.5F, 0.0F, limbSwing, limbSwingAmount);
   }
}

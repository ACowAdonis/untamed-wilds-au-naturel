package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.fish.EntityWhaleShark;

public class ModelWhaleShark extends AdvancedEntityModel<EntityWhaleShark> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox head_snout_1;
   private final AdvancedModelBox body_tail_1;
   private final AdvancedModelBox fin_pectoral_right;
   private final AdvancedModelBox fin_pectoral_left;
   private final AdvancedModelBox head_snout_2;
   private final AdvancedModelBox body_tail_2;
   private final AdvancedModelBox fin_dorsal;
   private final AdvancedModelBox fin_pelvic_right;
   private final AdvancedModelBox fin_pelvic_right_1;
   private final AdvancedModelBox body_tail_3;
   private final AdvancedModelBox fin_dorsal_2;
   private final AdvancedModelBox fin_anal;
   private final AdvancedModelBox fin_caudal_top;
   private final AdvancedModelBox fin_caudal_bottom;
   private final AdvancedModelBox head_jaw;
   private static AdvancedModelBox[] bodyParts_passive;

   public ModelWhaleShark() {
      this.texWidth = 128;
      this.texHeight = 128;
      this.fin_pelvic_right_1 = new AdvancedModelBox(this, 40, 102);
      this.fin_pelvic_right_1.setRotationPoint(3.0F, 4.5F, 5.0F);
      this.fin_pelvic_right_1.addBox(-1.0F, 0.0F, -3.5F, 2.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_right_1, 0.31869712F, 0.0F, -1.0927507F);
      this.head_snout_2 = new AdvancedModelBox(this, 0, 31);
      this.head_snout_2.mirror = true;
      this.head_snout_2.setRotationPoint(0.0F, 5.6F, 0.0F);
      this.head_snout_2.addBox(-10.0F, -4.0F, -24.0F, 20.0F, 8.0F, 24.0F, 0.0F);
      this.setRotateAngle(this.head_snout_2, -0.13665928F, 0.0F, 0.0F);
      this.body_tail_3 = new AdvancedModelBox(this, 74, 0);
      this.body_tail_3.setRotationPoint(0.0F, 0.0F, 14.0F);
      this.body_tail_3.addBox(-2.0F, -3.0F, -0.5F, 4.0F, 6.0F, 13.0F, 0.0F);
      this.setRotateAngle(this.body_tail_3, 0.045553092F, 0.0F, 0.0F);
      this.fin_pelvic_right = new AdvancedModelBox(this, 40, 102);
      this.fin_pelvic_right.mirror = true;
      this.fin_pelvic_right.setRotationPoint(-3.0F, 4.5F, 5.0F);
      this.fin_pelvic_right.addBox(-1.0F, 0.0F, -3.5F, 2.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_right, 0.31869712F, 0.0F, 1.0927507F);
      this.head_jaw = new AdvancedModelBox(this, 44, 31);
      this.head_jaw.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.head_jaw.addBox(-9.0F, 0.0F, -24.0F, 18.0F, 0.0F, 24.0F, 0.0F);
      this.body_tail_1 = new AdvancedModelBox(this, 58, 82);
      this.body_tail_1.setRotationPoint(0.0F, 1.0F, 15.0F);
      this.body_tail_1.addBox(-5.0F, -6.0F, 0.0F, 10.0F, 12.0F, 20.0F, 0.0F);
      this.setRotateAngle(this.body_tail_1, -0.13665928F, 0.0F, 0.0F);
      this.fin_dorsal = new AdvancedModelBox(this, 0, 0);
      this.fin_dorsal.setRotationPoint(0.0F, -8.0F, -4.0F);
      this.fin_dorsal.addBox(-1.0F, -11.0F, 1.0F, 2.0F, 15.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal, -0.4553564F, 0.0F, 0.0F);
      this.body_tail_2 = new AdvancedModelBox(this, 0, 102);
      this.body_tail_2.setRotationPoint(0.0F, 0.0F, 20.0F);
      this.body_tail_2.addBox(-4.0F, -5.0F, -0.5F, 8.0F, 10.0F, 14.0F, 0.0F);
      this.setRotateAngle(this.body_tail_2, 0.045553092F, 0.0F, 0.0F);
      this.fin_anal = new AdvancedModelBox(this, 74, 114);
      this.fin_anal.setRotationPoint(0.0F, 2.5F, 4.0F);
      this.fin_anal.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.fin_anal, 0.8196066F, 0.0F, 0.0F);
      this.fin_caudal_top = new AdvancedModelBox(this, 108, 0);
      this.fin_caudal_top.setRotationPoint(0.0F, -1.0F, 7.0F);
      this.fin_caudal_top.addBox(-1.0F, -24.0F, 0.0F, 2.0F, 24.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.fin_caudal_top, -0.8651597F, 0.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 0, 64);
      this.body_main.setRotationPoint(0.0F, 18.0F, -20.0F);
      this.body_main.addBox(-7.0F, -7.0F, -8.0F, 14.0F, 14.0F, 24.0F, 0.0F);
      this.setRotateAngle(this.body_main, 0.045553092F, 0.0F, 0.0F);
      this.fin_pectoral_left = new AdvancedModelBox(this, 100, 70);
      this.fin_pectoral_left.mirror = true;
      this.fin_pectoral_left.setRotationPoint(3.0F, 3.2F, -7.0F);
      this.fin_pectoral_left.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 20.0F, 9.0F, 0.0F);
      this.setRotateAngle(this.fin_pectoral_left, 0.31869712F, 0.0F, -1.0927507F);
      this.fin_pectoral_right = new AdvancedModelBox(this, 100, 70);
      this.fin_pectoral_right.setRotationPoint(-3.0F, 3.2F, -7.0F);
      this.fin_pectoral_right.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 20.0F, 9.0F, 0.0F);
      this.setRotateAngle(this.fin_pectoral_right, 0.31869712F, 0.0F, 1.0927507F);
      this.fin_dorsal_2 = new AdvancedModelBox(this, 58, 114);
      this.fin_dorsal_2.setRotationPoint(0.0F, -2.5F, 5.0F);
      this.fin_dorsal_2.addBox(-1.0F, -6.0F, -2.0F, 2.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal_2, -0.63739425F, 0.0F, 0.0F);
      this.head_snout_1 = new AdvancedModelBox(this, 1, 1);
      this.head_snout_1.setRotationPoint(0.0F, -4.1F, -7.5F);
      this.head_snout_1.addBox(-10.0F, -3.0F, -23.8F, 20.0F, 5.0F, 25.0F, 0.0F);
      this.setRotateAngle(this.head_snout_1, 0.045553092F, 0.0F, 0.0F);
      this.head_snout_1.scaleX = 1.01F;
      this.fin_caudal_bottom = new AdvancedModelBox(this, 108, 34);
      this.fin_caudal_bottom.setRotationPoint(0.0F, -8.0F, 8.0F);
      this.fin_caudal_bottom.addBox(-1.0F, -10.0F, 0.0F, 2.0F, 10.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.fin_caudal_bottom, (float) (-Math.PI / 2), 0.0F, 0.0F);
      this.body_tail_1.addChild(this.fin_pelvic_right_1);
      this.head_snout_1.addChild(this.head_snout_2);
      this.body_tail_2.addChild(this.body_tail_3);
      this.body_tail_1.addChild(this.fin_pelvic_right);
      this.body_main.addChild(this.body_tail_1);
      this.body_tail_1.addChild(this.fin_dorsal);
      this.body_tail_1.addChild(this.body_tail_2);
      this.body_tail_2.addChild(this.fin_anal);
      this.body_tail_3.addChild(this.fin_caudal_top);
      this.body_main.addChild(this.fin_pectoral_left);
      this.body_main.addChild(this.fin_pectoral_right);
      this.body_tail_2.addChild(this.fin_dorsal_2);
      this.head_snout_2.addChild(this.head_jaw);
      this.body_main.addChild(this.head_snout_1);
      this.fin_caudal_top.addChild(this.fin_caudal_bottom);
      bodyParts_passive = new AdvancedModelBox[]{this.head_snout_1, this.body_main, this.body_tail_1, this.body_tail_2, this.body_tail_3};
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_snout_1,
         this.body_tail_1,
         this.fin_pectoral_right,
         this.fin_pectoral_left,
         this.head_snout_2,
         this.head_jaw,
         this.body_tail_2,
         this.fin_dorsal,
         this.fin_pelvic_right,
         this.fin_pelvic_right_1,
         this.body_tail_3,
         new AdvancedModelBox[]{this.fin_dorsal_2, this.fin_anal, this.fin_caudal_top, this.fin_caudal_bottom}
      );
   }

   public void setupAnim(EntityWhaleShark whale_shark, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 0.6F;
      float globalDegree = 1.0F;
      this.head_snout_1
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F) + 0.01F, (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.head_snout_2
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      if (whale_shark.isInWater()) {
         this.setRotateAngle(this.body_main, Mth.clamp(whale_shark.getXRot(), -20.0F, 20.0F) * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      float partialTicks = ageInTicks - (float)whale_shark.tickCount;
      float renderYaw = (float)whale_shark.getMovementOffsets(0, partialTicks)[0];
      this.chainSwing(bodyParts_passive, globalSpeed * 0.8F, globalDegree * 0.75F, -5.0, limbSwing / 4.0F, Math.max(0.2F, limbSwingAmount));
      this.body_tail_1.rotateAngleY = this.body_tail_1.rotateAngleY
         + this.smartClamp((float)whale_shark.getMovementOffsets(15, partialTicks)[0] - renderYaw, -40, 40) * (float) (Math.PI / 180.0);
      this.body_tail_2.rotateAngleY = this.body_tail_2.rotateAngleY
         + this.smartClamp((float)whale_shark.getMovementOffsets(17, partialTicks)[0] - renderYaw, -40, 40) * (float) (Math.PI / 180.0);
      this.body_main.rotateAngleZ = this.body_main.rotateAngleZ
         + this.smartClamp((float)whale_shark.getMovementOffsets(7, partialTicks)[0] - renderYaw, -20, 20) * (float) (Math.PI / 180.0);
   }

   public float smartClamp(float angle, int min, int max) {
      float val = Math.abs(angle);
      if (val > 180.0F) {
         angle = 360.0F - val;
      }

      return Mth.clamp(angle, (float)min, (float)max);
   }
}

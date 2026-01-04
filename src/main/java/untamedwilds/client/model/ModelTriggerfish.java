package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.fish.EntityTriggerfish;
import untamedwilds.util.EntityUtils;

public class ModelTriggerfish extends AdvancedEntityModel<EntityTriggerfish> {
   private final AdvancedModelBox main_body;
   private final AdvancedModelBox body_tail;
   private final AdvancedModelBox fin_trigger;
   private final AdvancedModelBox head_main;
   private final AdvancedModelBox fin_anal;
   private final AdvancedModelBox fin_dorsal_2;
   private final AdvancedModelBox fin_caudal;
   private final AdvancedModelBox fin_pectoral_left;
   private final AdvancedModelBox fin_pectoral_right;
   private final AdvancedModelBox head_jaw;

   public ModelTriggerfish() {
      this.texWidth = 32;
      this.texHeight = 32;
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 19.9F, 0.0F);
      this.main_body.addBox(-1.51F, -3.5F, -3.5F, 3.0F, 7.0F, 7.0F, 0.0F);
      this.fin_pectoral_right = new AdvancedModelBox(this, 12, 21);
      this.fin_pectoral_right.setRotationPoint(-1.5F, 1.5F, -1.0F);
      this.fin_pectoral_right.addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_pectoral_right, -0.63739425F, -0.3642502F, 0.18203785F);
      this.head_jaw = new AdvancedModelBox(this, 14, 14);
      this.head_jaw.setRotationPoint(0.0F, 3.0F, -5.0F);
      this.head_jaw.addBox(-1.5F, -4.0F, 0.0F, 3.0F, 4.0F, 1.0F, 0.0F);
      this.head_jaw.scaleX = 1.1F;
      this.fin_anal = new AdvancedModelBox(this, 20, 20);
      this.fin_anal.setRotationPoint(0.0F, 1.5F, -0.4F);
      this.fin_anal.addBox(0.0F, 0.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_anal, 0.5009095F, 0.0F, 0.0F);
      this.body_tail = new AdvancedModelBox(this, 13, 0);
      this.body_tail.setRotationPoint(0.0F, 0.0F, 2.5F);
      this.body_tail.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 3.0F, 0.0F);
      this.fin_pectoral_left = new AdvancedModelBox(this, 12, 21);
      this.fin_pectoral_left.setRotationPoint(1.5F, 1.5F, -1.0F);
      this.fin_pectoral_left.addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_pectoral_left, -0.63739425F, 0.3642502F, -0.18203785F);
      this.fin_caudal = new AdvancedModelBox(this, 0, 20);
      this.fin_caudal.setRotationPoint(0.0F, 0.0F, 1.7F);
      this.fin_caudal.addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_caudal, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.fin_dorsal_2 = new AdvancedModelBox(this, 20, 14);
      this.fin_dorsal_2.setRotationPoint(0.0F, -1.5F, -0.1F);
      this.fin_dorsal_2.addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal_2, -0.3642502F, 0.0F, 0.0F);
      this.fin_trigger = new AdvancedModelBox(this, 0, 20);
      this.fin_trigger.setRotationPoint(0.0F, -3.0F, -3.0F);
      this.fin_trigger.addBox(0.0F, -3.0F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_trigger, -1.4570009F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 14);
      this.head_main.setRotationPoint(0.0F, -2.1F, -2.1F);
      this.head_main.addBox(-1.5F, -2.0F, -4.0F, 3.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_main, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.head_main.scaleX = 1.1F;
      this.head_main.addChild(this.fin_pectoral_right);
      this.head_main.addChild(this.head_jaw);
      this.body_tail.addChild(this.fin_anal);
      this.main_body.addChild(this.body_tail);
      this.head_main.addChild(this.fin_pectoral_left);
      this.body_tail.addChild(this.fin_caudal);
      this.body_tail.addChild(this.fin_dorsal_2);
      this.main_body.addChild(this.fin_trigger);
      this.main_body.addChild(this.head_main);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.body_tail,
         this.fin_trigger,
         this.head_main,
         this.fin_anal,
         this.fin_dorsal_2,
         this.fin_caudal,
         this.fin_pectoral_left,
         this.fin_pectoral_right,
         this.head_jaw
      );
   }

   public void setupAnim(EntityTriggerfish triggerfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      float f = ageInTicks - (float)triggerfish.tickCount / 10.0F;
      this.resetToDefaultPose();
      float globalSpeed = 0.5F;
      float globalDegree = 1.0F;
      if (!triggerfish.isInWater()) {
         this.setRotateAngle(this.main_body, 0.0F, 0.0F, (float)Math.toRadians(90.0));
      } else {
         this.setRotateAngle(this.main_body, triggerfish.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.main_body, this.body_tail, this.fin_caudal};
      this.chainSwing(bodyParts, globalSpeed, globalDegree * 1.1F, -5.0, limbSwing, limbSwingAmount);
      if (!EntityUtils.hasFullHealth(triggerfish) || triggerfish.isAngry()) {
         this.setRotateAngle(this.fin_trigger, -0.4F, 0.0F, 0.0F);
      }

      this.chainSwing(bodyParts, globalSpeed * 0.4F, globalDegree, -5.0, limbSwing, 0.6F);
      this.flap(this.fin_pectoral_left, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, f / 4.0F, 1.0F);
      this.swing(this.fin_pectoral_left, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, f / 4.0F, 0.6F);
      this.flap(this.fin_pectoral_right, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, f / 4.0F, 1.0F);
      this.swing(this.fin_pectoral_right, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, f / 4.0F, 0.6F);
      this.flap(this.fin_dorsal_2, globalSpeed * 0.8F, globalDegree * 0.6F, true, 0.0F, 0.0F, Math.max(f / 6.0F, limbSwing), 1.0F);
      this.flap(this.fin_anal, globalSpeed * 0.8F, globalDegree * 0.6F, false, 0.0F, 0.0F, Math.max(f / 6.0F, limbSwing), 1.0F);
   }
}

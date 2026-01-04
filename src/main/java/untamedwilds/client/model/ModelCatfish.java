package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.fish.EntityCatfish;

public class ModelCatfish extends AdvancedEntityModel<EntityCatfish> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_main;
   public AdvancedModelBox body_bottom;
   public AdvancedModelBox head_main_1;
   public AdvancedModelBox fin_dorsal;
   public AdvancedModelBox fin_pectoral_left;
   public AdvancedModelBox fin_pelvic_left;
   public AdvancedModelBox fin_pelvic_right;
   public AdvancedModelBox fin_pectoral_right;
   public AdvancedModelBox whisker_left;
   public AdvancedModelBox whisker_right;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox fin_anal;
   public AdvancedModelBox fin_dorsal_1;
   public AdvancedModelBox fin_caudal;

   public ModelCatfish() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.fin_dorsal = new AdvancedModelBox(this, 30, 13);
      this.fin_dorsal.setRotationPoint(0.0F, -1.3F, 1.0F);
      this.fin_dorsal.addBox(0.0F, -8.0F, -3.0F, 0.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal, -0.4553564F, 0.0F, 0.0F);
      this.fin_pelvic_right = new AdvancedModelBox(this, 40, 15);
      this.fin_pelvic_right.setRotationPoint(-2.0F, 1.0F, 2.0F);
      this.fin_pelvic_right.addBox(0.0F, 0.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_right, 0.3642502F, 0.13665928F, 0.5462881F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 23.0F, -2.0F);
      this.body_main.addBox(-2.5F, -2.5F, -3.0F, 5.0F, 4.0F, 7.0F, 0.0F);
      this.head_main_1 = new AdvancedModelBox(this, 24, 9);
      this.head_main_1.setRotationPoint(0.0F, 1.6F, -3.0F);
      this.head_main_1.addBox(-3.0F, -2.0F, 0.0F, 6.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_main_1, -0.091106184F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 24, 0);
      this.head_main.setRotationPoint(0.0F, -0.4F, -5.0F);
      this.head_main.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F);
      this.head_main.scaleX = 1.1F;
      this.setRotateAngle(this.head_main, 0.13665928F, 0.0F, 0.0F);
      this.fin_caudal = new AdvancedModelBox(this, 0, 18);
      this.fin_caudal.setRotationPoint(0.0F, 0.0F, 1.2F);
      this.fin_caudal.addBox(0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.fin_caudal, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.fin_pectoral_right = new AdvancedModelBox(this, 12, 6);
      this.fin_pectoral_right.setRotationPoint(-2.5F, 0.0F, -2.2F);
      this.fin_pectoral_right.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_pectoral_right, 0.0F, -0.5462881F, 0.0F);
      this.fin_anal = new AdvancedModelBox(this, 20, 16);
      this.fin_anal.setRotationPoint(0.0F, 0.4F, 2.0F);
      this.fin_anal.addBox(0.0F, 0.0F, -3.0F, 0.0F, 6.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.fin_anal, 0.22759093F, 0.0F, 0.0F);
      this.whisker_right = new AdvancedModelBox(this, 42, 0);
      this.whisker_right.mirror = true;
      this.whisker_right.setRotationPoint(-2.6F, -1.0F, -2.0F);
      this.whisker_right.addBox(-6.0F, 0.0F, -0.5F, 6.0F, 0.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.whisker_right, 0.0F, -0.27314404F, -0.4553564F);
      this.whisker_left = new AdvancedModelBox(this, 42, 0);
      this.whisker_left.mirror = true;
      this.whisker_left.setRotationPoint(2.6F, -1.0F, -2.0F);
      this.whisker_left.addBox(0.0F, 0.0F, -0.5F, 6.0F, 0.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.whisker_left, 0.0F, 0.27314404F, 0.4553564F);
      this.fin_pelvic_left = new AdvancedModelBox(this, 40, 15);
      this.fin_pelvic_left.mirror = true;
      this.fin_pelvic_left.setRotationPoint(2.0F, 1.0F, 2.0F);
      this.fin_pelvic_left.addBox(0.0F, 0.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_left, 0.3642502F, -0.13665928F, -0.5462881F);
      this.body_bottom = new AdvancedModelBox(this, 0, 11);
      this.body_bottom.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.body_bottom.addBox(-1.5F, -2.5F, 0.0F, 3.0F, 4.0F, 6.0F, 0.0F);
      this.fin_dorsal_1 = new AdvancedModelBox(this, 19, 14);
      this.fin_dorsal_1.setRotationPoint(0.0F, 0.1F, 3.0F);
      this.fin_dorsal_1.addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal_1, -1.0016445F, 0.0F, 0.0F);
      this.fin_pectoral_left = new AdvancedModelBox(this, 12, 6);
      this.fin_pectoral_left.mirror = true;
      this.fin_pectoral_left.setRotationPoint(2.5F, 0.0F, -2.2F);
      this.fin_pectoral_left.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_pectoral_left, 0.0F, 0.55900246F, 0.0F);
      this.body_tail = new AdvancedModelBox(this, 18, 0);
      this.body_tail.setRotationPoint(0.0F, -1.0F, 6.0F);
      this.body_tail.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 3.0F, 0.0F);
      this.body_main.addChild(this.fin_dorsal);
      this.body_main.addChild(this.fin_pelvic_right);
      this.head_main.addChild(this.head_main_1);
      this.body_main.addChild(this.head_main);
      this.body_tail.addChild(this.fin_caudal);
      this.body_main.addChild(this.fin_pectoral_right);
      this.body_bottom.addChild(this.fin_anal);
      this.head_main.addChild(this.whisker_right);
      this.head_main.addChild(this.whisker_left);
      this.body_main.addChild(this.fin_pelvic_left);
      this.body_main.addChild(this.body_bottom);
      this.body_bottom.addChild(this.fin_dorsal_1);
      this.body_main.addChild(this.fin_pectoral_left);
      this.body_bottom.addChild(this.body_tail);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_main,
         this.body_bottom,
         this.head_main_1,
         this.fin_dorsal,
         this.fin_pectoral_left,
         this.fin_pelvic_left,
         this.fin_pelvic_right,
         this.fin_pectoral_right,
         this.whisker_left,
         this.whisker_right,
         this.body_tail,
         new AdvancedModelBox[]{this.fin_anal, this.fin_dorsal_1, this.fin_caudal}
      );
   }

   public void setupAnim(EntityCatfish catfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 0.5F;
      float globalDegree = 1.0F;
      if (!catfish.isInWater()) {
         this.setRotateAngle(this.body_main, 0.0F, 0.0F, (float)Math.toRadians(90.0));
      }

      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.head_main, this.body_main, this.body_tail, this.fin_caudal};
      this.chainSwing(bodyParts, globalSpeed, globalDegree * 1.1F, -5.0, limbSwing, limbSwingAmount);
      float speed = Math.min((float)catfish.getCurrentSpeed(), 0.08F);
      this.swing(this.fin_pectoral_left, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, ageInTicks / 6.0F, 0.6F);
      this.swing(this.fin_pectoral_right, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, ageInTicks / 6.0F, 0.6F);
      this.setRotateAngle(this.whisker_right, 0.0F, -0.27314404F + (Float)catfish.whisker_offset.getA(), -0.4553564F + (Float)catfish.whisker_offset.getB());
      this.setRotateAngle(this.whisker_left, 0.0F, 0.27314404F - (Float)catfish.whisker_offset.getA(), 0.4553564F - (Float)catfish.whisker_offset.getB());
   }
}

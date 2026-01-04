package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.fish.EntityTrevally;

public class ModelTrevally extends AdvancedEntityModel<EntityTrevally> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_main;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox fin_top;
   public AdvancedModelBox fin_bottom;
   public AdvancedModelBox fin_dorsal;
   public AdvancedModelBox head_mouth;
   public AdvancedModelBox fin_right;
   public AdvancedModelBox fin_left;
   public AdvancedModelBox fin_tail;

   public ModelTrevally() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.head_main = new AdvancedModelBox(this, 0, 18);
      this.head_main.setRotationPoint(0.0F, 1.57F, -2.53F);
      this.head_main.addBox(-1.5F, -3.5F, -5.0F, 3.0F, 6.0F, 5.0F, 0.0F);
      this.head_main.scaleX = 1.1F;
      this.setRotateAngle(this.head_main, -0.5462881F, 0.0F, 0.0F);
      this.body_tail = new AdvancedModelBox(this, 18, 0);
      this.body_tail.setRotationPoint(0.0F, 0.0F, 5.0F);
      this.body_tail.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 3.0F, 0.0F);
      this.fin_tail = new AdvancedModelBox(this, 28, 8);
      this.fin_tail.setRotationPoint(0.0F, 0.0F, 0.7F);
      this.fin_tail.addBox(0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.fin_tail, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.fin_right = new AdvancedModelBox(this, 16, 20);
      this.fin_right.setRotationPoint(-1.4F, -0.5F, -1.0F);
      this.fin_right.addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_right, 0.59184116F, -0.27314404F, -0.18203785F);
      this.fin_bottom = new AdvancedModelBox(this, 28, 2);
      this.fin_bottom.setRotationPoint(0.0F, 2.0F, 5.0F);
      this.fin_bottom.addBox(0.0F, -1.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_bottom, 0.59184116F, 0.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 18.0F, 0.0F);
      this.body_main.addBox(-1.5F, -4.0F, -5.0F, 3.0F, 8.0F, 10.0F, 0.0F);
      this.fin_dorsal = new AdvancedModelBox(this, 28, 21);
      this.fin_dorsal.setRotationPoint(0.0F, -3.5F, -1.4F);
      this.fin_dorsal.addBox(0.0F, -3.0F, -0.5F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal, -0.63739425F, 0.0F, 0.0F);
      this.fin_left = new AdvancedModelBox(this, 16, 20);
      this.fin_left.setRotationPoint(1.4F, -0.5F, -1.0F);
      this.fin_left.addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_left, 0.59184116F, 0.27314404F, 0.18203785F);
      this.fin_top = new AdvancedModelBox(this, 28, -6);
      this.fin_top.setRotationPoint(0.0F, -2.0F, 5.0F);
      this.fin_top.addBox(0.0F, -7.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_top, -0.59184116F, 0.0F, 0.0F);
      this.head_mouth = new AdvancedModelBox(this, 12, 18);
      this.head_mouth.setRotationPoint(0.0F, 2.4F, -1.8F);
      this.head_mouth.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_mouth, 0.091106184F, 0.0F, 0.0F);
      this.body_main.addChild(this.head_main);
      this.body_main.addChild(this.body_tail);
      this.body_tail.addChild(this.fin_tail);
      this.head_main.addChild(this.fin_right);
      this.body_main.addChild(this.fin_bottom);
      this.body_main.addChild(this.fin_dorsal);
      this.head_main.addChild(this.fin_left);
      this.body_main.addChild(this.fin_top);
      this.head_main.addChild(this.head_mouth);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_main,
         this.body_tail,
         this.fin_top,
         this.fin_bottom,
         this.fin_dorsal,
         this.head_mouth,
         this.fin_right,
         this.fin_left,
         this.fin_tail
      );
   }

   public void setupAnim(EntityTrevally trevally, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 0.5F;
      float globalDegree = 1.0F;
      if (!trevally.isInWater()) {
         this.setRotateAngle(this.body_main, 0.0F, 0.0F, (float)Math.toRadians(90.0));
      }

      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.head_main, this.body_main, this.body_tail, this.fin_tail};
      this.chainSwing(bodyParts, globalSpeed, globalDegree * 1.1F, -5.0, limbSwing, limbSwingAmount);
      float speed = Math.min((float)trevally.getCurrentSpeed(), 0.08F);
      this.fin_dorsal.rotateAngleX = this.fin_dorsal.defaultRotationX + speed * -8.0F;
      this.fin_top.rotateAngleX = this.fin_top.defaultRotationX + speed * -3.0F;
      this.fin_bottom.rotateAngleX = this.fin_bottom.defaultRotationX + speed * 3.0F;
      this.swing(this.fin_left, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, ageInTicks / 6.0F, 0.6F);
      this.swing(this.fin_right, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, ageInTicks / 6.0F, 0.6F);
   }
}

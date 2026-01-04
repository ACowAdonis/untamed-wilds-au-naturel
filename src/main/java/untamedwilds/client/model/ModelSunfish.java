package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.fish.EntitySunfish;

public class ModelSunfish extends AdvancedEntityModel<EntitySunfish> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox body_head;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox body_fin_right;
   public AdvancedModelBox body_fin_left;
   public AdvancedModelBox body_tail_fin;
   public AdvancedModelBox body_fin_top;
   public AdvancedModelBox body_fin_bottom;

   public ModelSunfish() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.body_main.addBox(-3.0F, -13.0F, -8.0F, 6.0F, 26.0F, 16.0F, 0.0F);
      this.body_fin_right = new AdvancedModelBox(this, 0, 0);
      this.body_fin_right.setRotationPoint(-3.0F, -2.0F, -4.0F);
      this.body_fin_right.addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.body_fin_right, 0.0F, -0.5462881F, 0.0F);
      this.body_tail_fin = new AdvancedModelBox(this, 104, 0);
      this.body_tail_fin.setRotationPoint(0.0F, 0.0F, 8.0F);
      this.body_tail_fin.addBox(-1.0F, -11.0F, 0.0F, 2.0F, 22.0F, 5.0F, 0.0F);
      this.body_fin_top = new AdvancedModelBox(this, 82, 32);
      this.body_fin_top.setRotationPoint(0.0F, -10.0F, 6.0F);
      this.body_fin_top.addBox(-1.0F, -20.0F, -4.0F, 2.0F, 20.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_fin_top, -0.22759093F, 0.0F, 0.0F);
      this.body_fin_left = new AdvancedModelBox(this, 0, 0);
      this.body_fin_left.setRotationPoint(3.0F, -2.0F, -4.0F);
      this.body_fin_left.addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.body_fin_left, 0.0F, 0.5462881F, 0.0F);
      this.body_head = new AdvancedModelBox(this, 44, 0);
      this.body_head.setRotationPoint(0.0F, 0.0F, -9.0F);
      this.body_head.addBox(-2.5F, -9.0F, -6.0F, 5.0F, 18.0F, 7.0F, 0.0F);
      this.body_tail = new AdvancedModelBox(this, 78, 0);
      this.body_tail.setRotationPoint(0.0F, 0.0F, 8.0F);
      this.body_tail.addBox(-2.5F, -11.0F, 0.0F, 5.0F, 22.0F, 8.0F, 0.0F);
      this.body_fin_bottom = new AdvancedModelBox(this, 104, 32);
      this.body_fin_bottom.setRotationPoint(0.0F, 10.0F, 6.0F);
      this.body_fin_bottom.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 20.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_fin_bottom, 0.22759093F, 0.0F, 0.0F);
      this.body_main.addChild(this.body_fin_right);
      this.body_tail.addChild(this.body_tail_fin);
      this.body_tail.addChild(this.body_fin_top);
      this.body_main.addChild(this.body_fin_left);
      this.body_main.addChild(this.body_head);
      this.body_main.addChild(this.body_tail);
      this.body_tail.addChild(this.body_fin_bottom);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main, this.body_head, this.body_tail, this.body_fin_right, this.body_fin_left, this.body_tail_fin, this.body_fin_top, this.body_fin_bottom
      );
   }

   public void setupAnim(EntitySunfish sunfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      float f = ageInTicks - (float)sunfish.tickCount / 10.0F;
      this.resetToDefaultPose();
      if (!sunfish.isInWater()) {
         this.body_main.defaultPositionY = 20.0F;
         this.setRotateAngle(this.body_main, 0.0F, 0.0F, (float)Math.toRadians(90.0));
      } else {
         this.setRotateAngle(this.body_main, netHeadYaw * 2.0F * (float) (Math.PI / 180.0), headPitch * 2.0F * (float) (Math.PI / 180.0), 0.0F);
         this.progressRotation(this.body_main, (float)sunfish.baskProgress, 0.0F, 0.0F, (float)Math.toRadians(90.0), 100.0F);
      }

      float globalSpeed = 0.6F;
      float globalDegree = 0.6F;
      this.flap(this.body_fin_bottom, globalSpeed * 0.4F, globalDegree * 0.8F, true, 0.0F, 0.0F, f, 1.0F);
      this.flap(this.body_fin_top, globalSpeed * 0.4F, globalDegree * 0.8F, false, 0.0F, 0.0F, f, 1.0F);
      this.swing(this.body_tail_fin, globalSpeed * 0.4F, globalDegree * 0.8F, false, 0.0F, 0.0F, f, 0.6F);
      this.flap(this.body_fin_left, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, f / 6.0F, 1.0F);
      this.swing(this.body_fin_left, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, f / 6.0F, 0.6F);
      this.flap(this.body_fin_right, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, f / 6.0F, 1.0F);
      this.swing(this.body_fin_right, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, f / 6.0F, 0.6F);
   }
}

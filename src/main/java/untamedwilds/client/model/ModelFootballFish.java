package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.fish.EntityFootballFish;

public class ModelFootballFish extends AdvancedEntityModel<EntityFootballFish> {
   public AdvancedModelBox main_body;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox head_fin_left;
   public AdvancedModelBox head_fin_right;
   public AdvancedModelBox body_tail;
   public AdvancedModelBox body_bait;
   public AdvancedModelBox tail_fin;
   public AdvancedModelBox tail_top;
   public AdvancedModelBox tail_bottom;
   public AdvancedModelBox attached_male;

   public ModelFootballFish() {
      this.texWidth = 32;
      this.texHeight = 32;
      this.head_fin_right = new AdvancedModelBox(this, 0, 0);
      this.head_fin_right.mirror = true;
      this.head_fin_right.setRotationPoint(-3.5F, 0.0F, 0.0F);
      this.head_fin_right.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_fin_right, -0.045553092F, -0.22759093F, 0.0F);
      this.body_bait = new AdvancedModelBox(this, 16, 10);
      this.body_bait.setRotationPoint(0.0F, -3.0F, -2.0F);
      this.body_bait.addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F);
      this.body_tail = new AdvancedModelBox(this, 0, 27);
      this.body_tail.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.body_tail.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.body_tail, -0.22759093F, 0.0F, 0.0F);
      this.tail_top = new AdvancedModelBox(this, 13, 21);
      this.tail_top.setRotationPoint(0.0F, -1.1F, 1.0F);
      this.tail_top.addBox(0.0F, -1.5F, -0.5F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.tail_top, 1.1838568F, 0.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 0, 15);
      this.head_jaw.setRotationPoint(0.0F, 2.0F, -3.0F);
      this.head_jaw.addBox(-2.5F, -4.0F, -1.0F, 5.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_jaw, 0.3642502F, 0.0F, 0.0F);
      this.head_fin_left = new AdvancedModelBox(this, 0, 0);
      this.head_fin_left.setRotationPoint(3.5F, 0.0F, 0.0F);
      this.head_fin_left.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_fin_left, -0.045553092F, 0.22759093F, 0.0F);
      this.tail_bottom = new AdvancedModelBox(this, 13, 21);
      this.tail_bottom.setRotationPoint(0.0F, 1.1F, 1.0F);
      this.tail_bottom.addBox(0.0F, -1.5F, -0.5F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.tail_bottom, -1.1838568F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 21.0F, 0.0F);
      this.main_body.addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.main_body, 0.22759093F, 0.0F, 0.0F);
      this.tail_fin = new AdvancedModelBox(this, 10, 24);
      this.tail_fin.setRotationPoint(0.0F, 0.0F, 2.0F);
      this.tail_fin.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F);
      this.attached_male = new AdvancedModelBox(this, 6, 4);
      this.attached_male.setRotationPoint(0.0F, 0.0F, 2.0F);
      this.attached_male.addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.attached_male, 0.3642502F, 0.0F, 0.0F);
      this.main_body.addChild(this.head_fin_right);
      this.main_body.addChild(this.body_bait);
      this.main_body.addChild(this.body_tail);
      this.body_tail.addChild(this.tail_top);
      this.main_body.addChild(this.head_jaw);
      this.main_body.addChild(this.head_fin_left);
      this.body_tail.addChild(this.tail_bottom);
      this.body_tail.addChild(this.tail_fin);
      this.main_body.addChild(this.attached_male);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.head_jaw,
         this.head_fin_left,
         this.head_fin_right,
         this.body_tail,
         this.body_bait,
         this.tail_fin,
         this.tail_top,
         this.tail_bottom,
         this.attached_male
      );
   }

   public void setupAnim(EntityFootballFish football_fish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      float f = ageInTicks - (float)football_fish.tickCount / 10.0F;
      this.resetToDefaultPose();
      if (!football_fish.isInWater()) {
         this.main_body.defaultPositionY = 20.0F;
         this.setRotateAngle(this.main_body, 0.0F, 0.0F, (float)Math.toRadians(90.0));
      } else {
         this.setRotateAngle(this.main_body, netHeadYaw * 2.0F * (float) (Math.PI / 180.0), headPitch * 2.0F * (float) (Math.PI / 180.0), 0.0F);
      }

      float globalSpeed = 0.6F;
      float globalDegree = 0.6F;
      if (football_fish.hasAttachedMale()) {
         this.attached_male.setRotationPoint(0.0F, 3.0F, -2.0F);
      }

      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.body_tail, this.tail_fin};
      this.chainSwing(bodyParts, globalSpeed * 0.4F, globalDegree, -5.0, f, 0.6F);
      this.walk(this.head_jaw, globalSpeed, globalDegree * 0.2F, false, 0.2F, 0.2F, ageInTicks / 6.0F, 0.6F);
      this.flap(this.head_fin_left, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, f / 6.0F, 1.0F);
      this.swing(this.head_fin_left, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, f / 6.0F, 0.6F);
      this.flap(this.head_fin_right, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.2F, f / 6.0F, 1.0F);
      this.swing(this.head_fin_right, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.2F, f / 6.0F, 0.6F);
   }
}

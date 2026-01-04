package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import untamedwilds.entity.fish.EntityArowana;

public class ModelArowana extends AdvancedEntityModel<EntityArowana> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox body_tail_1;
   public AdvancedModelBox body_head;
   public AdvancedModelBox body_tail_fin_1;
   public AdvancedModelBox body_tail_fin_2;
   public AdvancedModelBox body_tail_3;
   public AdvancedModelBox body_tail_fin;
   public AdvancedModelBox head_mouth;
   public AdvancedModelBox shape15;
   public AdvancedModelBox shape16;
   public AdvancedModelBox head_mouth_whisker_1;
   public AdvancedModelBox head_mouth_whisker_2;

   public ModelArowana() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.head_mouth = new AdvancedModelBox(this, 0, 25);
      this.head_mouth.setRotationPoint(0.0F, 1.5F, -6.0F);
      this.head_mouth.addBox(-1.5F, -4.0F, -1.0F, 3.0F, 5.0F, 1.0F, 0.0F);
      this.head_mouth.scaleX = 1.1F;
      this.head_mouth_whisker_1 = new AdvancedModelBox(this, 16, 20);
      this.head_mouth_whisker_1.setRotationPoint(0.5F, -3.9F, 0.0F);
      this.head_mouth_whisker_1.addBox(-0.5F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_mouth_whisker_1, 0.0F, -0.22759093F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 21.1F, -2.0F);
      this.body_main.addBox(-1.5F, -2.5F, -4.0F, 3.0F, 5.0F, 7.0F, 0.0F);
      this.body_head = new AdvancedModelBox(this, 0, 14);
      this.body_head.setRotationPoint(0.0F, -0.2F, -2.0F);
      this.body_head.addBox(-1.5F, -2.5F, -6.0F, 3.0F, 5.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.body_head, 0.091106184F, 0.0F, 0.0F);
      this.body_head.scaleX = 1.1F;
      this.body_tail_fin_1 = new AdvancedModelBox(this, 24, 8);
      this.body_tail_fin_1.setRotationPoint(0.0F, -1.4F, 4.5F);
      this.body_tail_fin_1.addBox(0.0F, -5.0F, 0.0F, 0.0F, 10.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.body_tail_fin_1, 1.4570009F, 0.0F, 0.0F);
      this.body_tail_fin_2 = new AdvancedModelBox(this, 32, 8);
      this.body_tail_fin_2.setRotationPoint(0.0F, 1.5F, 4.5F);
      this.body_tail_fin_2.addBox(0.0F, -5.0F, 0.0F, 0.0F, 10.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.body_tail_fin_2, -1.4114478F, 0.0F, 0.0F);
      this.head_mouth_whisker_2 = new AdvancedModelBox(this, 16, 20);
      this.head_mouth_whisker_2.setRotationPoint(-0.5F, -3.9F, 0.0F);
      this.head_mouth_whisker_2.addBox(-0.5F, 0.0F, -3.0F, 1.0F, 0.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_mouth_whisker_2, 0.0F, 0.22759093F, 0.0F);
      this.body_tail_fin = new AdvancedModelBox(this, 44, 8);
      this.body_tail_fin.setRotationPoint(0.0F, 0.0F, 2.3F);
      this.body_tail_fin.addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.body_tail_fin, (float) (Math.PI / 4), 0.0F, 0.0F);
      this.shape15 = new AdvancedModelBox(this, 24, 20);
      this.shape15.setRotationPoint(1.3F, 1.0F, -3.0F);
      this.shape15.addBox(0.0F, 0.0F, -1.5F, 0.0F, 7.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.shape15, (float) (Math.PI / 3), 0.31869712F, 0.0F);
      this.shape16 = new AdvancedModelBox(this, 24, 20);
      this.shape16.setRotationPoint(-1.3F, 1.0F, -3.0F);
      this.shape16.addBox(0.0F, 0.0F, -1.5F, 0.0F, 7.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.shape16, (float) (Math.PI / 3), -0.31869712F, 0.0F);
      this.body_tail_1 = new AdvancedModelBox(this, 24, 0);
      this.body_tail_1.setRotationPoint(0.0F, 0.0F, 3.0F);
      this.body_tail_1.addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 7.0F, 0.0F);
      this.body_tail_3 = new AdvancedModelBox(this, 44, 6);
      this.body_tail_3.setRotationPoint(0.0F, 0.0F, 5.6F);
      this.body_tail_3.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 4.0F, 0.0F);
      this.body_head.addChild(this.head_mouth);
      this.head_mouth.addChild(this.head_mouth_whisker_1);
      this.body_main.addChild(this.body_head);
      this.body_tail_1.addChild(this.body_tail_fin_1);
      this.body_tail_1.addChild(this.body_tail_fin_2);
      this.head_mouth.addChild(this.head_mouth_whisker_2);
      this.body_tail_3.addChild(this.body_tail_fin);
      this.body_head.addChild(this.shape15);
      this.body_head.addChild(this.shape16);
      this.body_main.addChild(this.body_tail_1);
      this.body_tail_1.addChild(this.body_tail_3);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.body_tail_1,
         this.body_head,
         this.body_tail_fin_1,
         this.body_tail_fin_2,
         this.body_tail_3,
         this.body_tail_fin,
         this.head_mouth,
         this.shape15,
         this.shape16,
         this.head_mouth_whisker_1,
         this.head_mouth_whisker_2,
         new AdvancedModelBox[0]
      );
   }

   public void setupAnim(EntityArowana arowana, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 0.5F;
      float globalDegree = 1.0F;
      this.walk(this.head_mouth, globalSpeed, globalDegree * 0.2F, false, 0.2F, 0.2F, ageInTicks / 6.0F, 0.6F);
      this.swing(this.shape15, globalSpeed, globalDegree * 0.5F, false, 0.0F, 0.2F, ageInTicks / 6.0F, 0.6F);
      this.swing(this.shape16, globalSpeed, globalDegree * 0.5F, true, 0.0F, 0.2F, ageInTicks / 6.0F, 0.6F);
      if (!arowana.isInWater()) {
         this.setRotateAngle(this.body_main, 0.0F, 0.0F, (float)Math.toRadians(90.0));
      } else {
         this.setRotateAngle(this.body_main, netHeadYaw * 2.0F * (float) (Math.PI / 180.0), headPitch * 2.0F * (float) (Math.PI / 180.0), 0.0F);
      }

      AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.body_head, this.body_main, this.body_tail_1, this.body_tail_3, this.body_tail_fin};
      this.chainSwing(bodyParts, globalSpeed, globalDegree * 1.1F, -5.0, limbSwing, limbSwingAmount);
   }
}

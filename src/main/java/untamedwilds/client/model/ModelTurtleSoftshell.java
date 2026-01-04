package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.reptile.EntitySoftshellTurtle;

public class ModelTurtleSoftshell extends AdvancedEntityModel<EntitySoftshellTurtle> {
   public AdvancedModelBox main_body;
   public AdvancedModelBox body_shell;
   public AdvancedModelBox hand_right;
   public AdvancedModelBox leg_right;
   public AdvancedModelBox neck;
   public AdvancedModelBox hand_left;
   public AdvancedModelBox leg_left;
   public AdvancedModelBox body_tail_short;
   public AdvancedModelBox main_head;
   public AdvancedModelBox head_nose;

   public ModelTurtleSoftshell() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.neck = new AdvancedModelBox(this, 20, 0);
      this.neck.setRotationPoint(0.0F, 0.0F, -0.01F);
      this.neck.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.neck, 0.0F, 0.0F, 0.0F);
      this.leg_left = new AdvancedModelBox(this, 26, 14);
      this.leg_left.mirror = true;
      this.leg_left.setRotationPoint(3.0F, 0.51F, 2.0F);
      this.leg_left.addBox(-1.0F, -0.5F, -1.0F, 5.0F, 1.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left, 0.0F, -1.0927507F, 0.0F);
      this.body_tail_short = new AdvancedModelBox(this, 8, 22);
      this.body_tail_short.setRotationPoint(0.0F, 0.0F, 2.0F);
      this.body_tail_short.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F);
      this.main_head = new AdvancedModelBox(this, 32, 0);
      this.main_head.setRotationPoint(0.0F, -0.2F, -3.41F);
      this.main_head.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.main_head, 0.0F, 0.0F, 0.0F);
      this.body_shell = new AdvancedModelBox(this, 0, 11);
      this.body_shell.setRotationPoint(0.0F, -0.4F, 0.0F);
      this.body_shell.addBox(-4.0F, -1.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.body_shell, -0.045553092F, 0.0F, 0.0F);
      this.leg_right = new AdvancedModelBox(this, 26, 14);
      this.leg_right.setRotationPoint(-3.0F, 0.51F, 2.0F);
      this.leg_right.addBox(-4.0F, -0.5F, -1.0F, 5.0F, 1.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right, 0.0F, 1.0927507F, 0.0F);
      this.hand_right = new AdvancedModelBox(this, 26, 8);
      this.hand_right.setRotationPoint(-2.0F, 0.7F, -3.0F);
      this.hand_right.addBox(-4.0F, -0.5F, -2.0F, 5.0F, 1.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.hand_right, 0.0F, (float) (-Math.PI / 9), 0.0F);
      this.head_nose = new AdvancedModelBox(this, 28, 0);
      this.head_nose.setRotationPoint(0.0F, -0.49F, -3.0F);
      this.head_nose.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_nose, 0.091106184F, 0.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.main_body.addBox(-3.0F, -2.0F, -4.0F, 6.0F, 3.0F, 7.0F, 0.0F);
      this.hand_left = new AdvancedModelBox(this, 26, 8);
      this.hand_left.mirror = true;
      this.hand_left.setRotationPoint(2.0F, 0.7F, -3.0F);
      this.hand_left.addBox(-1.0F, -0.5F, -2.0F, 5.0F, 1.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.hand_left, 0.0F, (float) (Math.PI / 9), 0.0F);
      this.main_body.addChild(this.neck);
      this.main_body.addChild(this.leg_left);
      this.main_body.addChild(this.body_tail_short);
      this.neck.addChild(this.main_head);
      this.main_body.addChild(this.body_shell);
      this.main_body.addChild(this.leg_right);
      this.main_body.addChild(this.hand_right);
      this.main_head.addChild(this.head_nose);
      this.main_body.addChild(this.hand_left);
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(this.main_body, this.hand_left, this.hand_right, this.leg_left, this.leg_right, this.neck, this.main_head);
   }

   public void setupAnim(EntitySoftshellTurtle turtle, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 1.2F;
      float globalDegree = 0.6F;
      if ((double)limbSwingAmount > 0.5) {
         limbSwingAmount = 0.5F;
      }

      if (turtle.extendNeckProgress != 0) {
         this.progressPosition(this.neck, (float)turtle.extendNeckProgress, 0.0F, 0.0F, -4.01F, 100.0F);
         this.progressRotation(this.neck, (float)turtle.extendNeckProgress, -0.5009095F, 0.0F, 0.0F, 100.0F);
         this.progressRotation(this.main_head, (float)turtle.extendNeckProgress, 0.3642502F, 0.0F, 0.0F, 100.0F);
      }

      if (turtle.isInWater() && !turtle.onGround()) {
         float pitch = Mth.clamp(turtle.getXRot(), -45.0F, 45.0F) - 10.0F;
         this.setRotateAngle(this.main_body, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.main_body
         .setScale(
            (float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F)
         );
      this.body_shell
         .setScale(
            (float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.03F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.03F),
            (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.03F)
         );
      this.walk(this.neck, 0.4F, 0.3F, false, 2.8F, 0.1F, ageInTicks / 16.0F, 0.1F);
      this.setRotateAngle(this.neck, this.neck.rotateAngleX, (Float)turtle.head_movement.getA(), 0.0F);
      this.setRotateAngle(this.main_head, this.main_head.rotateAngleX, (Float)turtle.head_movement.getB(), 0.0F);
      this.swing(this.hand_left, globalSpeed, globalDegree * 1.4F, false, 0.0F, 0.8F, ageInTicks / 2.0F, limbSwingAmount);
      this.swing(this.leg_left, globalSpeed, globalDegree * 1.2F, false, 0.8F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
      this.swing(this.hand_right, globalSpeed, globalDegree * 1.4F, false, 1.6F, 0.8F, ageInTicks / 2.0F, limbSwingAmount);
      this.swing(this.leg_right, globalSpeed, globalDegree * 1.2F, false, 2.4F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
      if (turtle.isInWater()) {
         if (!turtle.onGround()) {
            this.swing(this.hand_left, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         }

         this.flap(this.hand_left, globalSpeed, globalDegree * 1.4F, false, 0.0F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.swing(this.hand_left, globalSpeed, globalDegree * 1.4F, false, 0.0F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.flap(this.leg_left, globalSpeed, globalDegree * 1.2F, false, 0.8F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.swing(this.leg_left, globalSpeed, globalDegree * 1.2F, false, 0.8F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.flap(this.hand_right, globalSpeed, globalDegree * 1.4F, false, 1.6F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.swing(this.hand_right, globalSpeed, globalDegree * 1.4F, false, 1.6F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.flap(this.leg_right, globalSpeed, globalDegree * 1.2F, false, 2.4F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.swing(this.leg_right, globalSpeed, globalDegree * 1.2F, false, 2.4F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         float f = netHeadYaw * 0.33F;
         float f1 = Mth.sin(f);
         float f3 = 0.13F * f1;
         this.main_body.rotateAngleX = Mth.lerp(0.1F, this.main_body.rotateAngleX, headPitch * (float) (Math.PI / 180.0) + f3);
         this.flap(this.main_body, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.0F, 0.1F, ageInTicks / 2.0F, limbSwingAmount);
         this.swing(this.main_body, globalSpeed / 2.0F, globalDegree * 1.2F, false, 0.8F, 0.1F, ageInTicks / 3.0F, limbSwingAmount);
      }
   }
}

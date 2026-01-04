package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.reptile.EntityAnaconda;

public class ModelAnaconda extends AdvancedEntityModel<EntityAnaconda> {
   public AdvancedModelBox body_5;
   public AdvancedModelBox body_6;
   public AdvancedModelBox body_4;
   public AdvancedModelBox body_7;
   public AdvancedModelBox body_8;
   public AdvancedModelBox body_9;
   public AdvancedModelBox body_10;
   public AdvancedModelBox body_11;
   public AdvancedModelBox body_12;
   public AdvancedModelBox body_13;
   public AdvancedModelBox body_3;
   public AdvancedModelBox body_2;
   public AdvancedModelBox body_1;
   public AdvancedModelBox head_main;
   public AdvancedModelBox body_head_top;
   public AdvancedModelBox body_head_top_1;
   public AdvancedModelBox body_snout_top;
   public AdvancedModelBox body_snout_top_1;
   private final ModelAnimator animator;
   private final AdvancedModelBox[] bodyParts;

   public ModelAnaconda() {
      this.texWidth = 64;
      this.texHeight = 32;
      this.body_7 = new AdvancedModelBox(this, 0, 0);
      this.body_7.setRotationPoint(0.0F, 0.01F, 8.0F);
      this.body_7.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_snout_top_1 = new AdvancedModelBox(this, 14, 23);
      this.body_snout_top_1.setRotationPoint(0.0F, -0.4F, -3.0F);
      this.body_snout_top_1.addBox(-2.5F, -0.5F, -4.0F, 5.0F, 2.0F, 4.0F, 0.0F);
      this.body_head_top_1 = new AdvancedModelBox(this, 18, 13);
      this.body_head_top_1.setRotationPoint(0.0F, 0.0F, -3.0F);
      this.body_head_top_1.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 3.0F, 0.0F);
      this.body_2 = new AdvancedModelBox(this, 28, 0);
      this.body_2.setRotationPoint(0.0F, 0.0F, -8.0F);
      this.body_2.addBox(-2.5F, -3.0F, -8.0F, 5.0F, 4.0F, 8.0F, 0.0F);
      this.body_9 = new AdvancedModelBox(this, 0, 0);
      this.body_9.setRotationPoint(0.0F, 0.01F, 8.0F);
      this.body_9.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_4 = new AdvancedModelBox(this, 0, 0);
      this.body_4.setRotationPoint(0.0F, 0.01F, 0.0F);
      this.body_4.addBox(-3.0F, -4.0F, -8.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_8 = new AdvancedModelBox(this, 0, 0);
      this.body_8.setRotationPoint(0.0F, 0.01F, 8.0F);
      this.body_8.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_10 = new AdvancedModelBox(this, 0, 0);
      this.body_10.setRotationPoint(0.0F, 0.01F, 8.0F);
      this.body_10.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_5 = new AdvancedModelBox(this, 0, 0);
      this.body_5.setRotationPoint(0.0F, 23.01F, 6.0F);
      this.body_5.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 13);
      this.head_main.setRotationPoint(0.0F, -0.01F, -8.0F);
      this.head_main.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 3.0F, 0.0F);
      this.body_3 = new AdvancedModelBox(this, 0, 0);
      this.body_3.setRotationPoint(0.0F, 0.01F, -8.0F);
      this.body_3.addBox(-3.0F, -4.0F, -8.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_snout_top = new AdvancedModelBox(this, 0, 25);
      this.body_snout_top.setRotationPoint(0.0F, -0.4F, -3.0F);
      this.body_snout_top.addBox(-2.5F, -1.0F, -4.0F, 5.0F, 2.0F, 4.0F, 0.0F);
      this.body_head_top = new AdvancedModelBox(this, 0, 20);
      this.body_head_top.setRotationPoint(0.0F, -1.5F, -3.0F);
      this.body_head_top.addBox(-3.0F, -1.5F, -3.0F, 6.0F, 2.0F, 3.0F, 0.0F);
      this.body_6 = new AdvancedModelBox(this, 0, 0);
      this.body_6.setRotationPoint(0.0F, 0.01F, 8.0F);
      this.body_6.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F);
      this.body_1 = new AdvancedModelBox(this, 28, 0);
      this.body_1.setRotationPoint(0.0F, 0.01F, -8.0F);
      this.body_1.addBox(-2.5F, -3.0F, -8.0F, 5.0F, 4.0F, 8.0F, 0.0F);
      this.body_11 = new AdvancedModelBox(this, 28, 0);
      this.body_11.setRotationPoint(0.0F, 0.0F, 8.0F);
      this.body_11.addBox(-2.5F, -3.0F, 0.0F, 5.0F, 4.0F, 8.0F, 0.0F);
      this.body_12 = new AdvancedModelBox(this, 38, 12);
      this.body_12.setRotationPoint(0.0F, 0.01F, 8.0F);
      this.body_12.addBox(-2.0F, -3.0F, 0.0F, 4.0F, 4.0F, 8.0F, 0.0F);
      this.body_13 = new AdvancedModelBox(this, 24, 21);
      this.body_13.setRotationPoint(0.0F, 0.0F, 8.0F);
      this.body_13.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 8.0F, 0.0F);
      this.body_6.addChild(this.body_7);
      this.body_head_top_1.addChild(this.body_snout_top_1);
      this.head_main.addChild(this.body_head_top_1);
      this.body_3.addChild(this.body_2);
      this.body_8.addChild(this.body_9);
      this.body_5.addChild(this.body_4);
      this.body_7.addChild(this.body_8);
      this.body_9.addChild(this.body_10);
      this.body_10.addChild(this.body_11);
      this.body_11.addChild(this.body_12);
      this.body_12.addChild(this.body_13);
      this.body_1.addChild(this.head_main);
      this.body_4.addChild(this.body_3);
      this.body_head_top.addChild(this.body_snout_top);
      this.head_main.addChild(this.body_head_top);
      this.body_5.addChild(this.body_6);
      this.body_2.addChild(this.body_1);
      this.bodyParts = new AdvancedModelBox[]{this.body_6, this.body_7, this.body_8, this.body_9, this.body_10, this.body_11, this.body_12, this.body_13};
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_5);
   }

   public void animate(IAnimatedEntity entity) {
      this.animator.update(entity);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.head_main,
         this.body_head_top,
         this.body_head_top_1,
         this.body_snout_top,
         this.body_snout_top_1,
         this.body_1,
         this.body_2,
         this.body_3,
         this.body_4,
         this.body_5,
         this.body_6,
         this.body_7,
         new AdvancedModelBox[]{this.body_8, this.body_9, this.body_10, this.body_11, this.body_12, this.body_13}
      );
   }

   public void setupAnim(EntityAnaconda anaconda, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(anaconda);
      limbSwing = (float)((double)limbSwing * -1.2);
      float globalSpeed = 0.6F;
      float globalDegree = 2.0F;
      limbSwingAmount = 0.5F;
      if (anaconda.isInWater() && !anaconda.onGround()) {
         this.setRotateAngle(this.body_5, anaconda.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.body_5.rotationPointX = this.body_5.rotationPointX
         + (float)(
            Math.sin((double)(limbSwing * -globalSpeed) * 0.5) * (double)limbSwingAmount * (double)globalDegree * -4.0
               - (double)(limbSwingAmount * globalDegree * -4.0F)
         );
      limbSwingAmount = (float)((double)limbSwingAmount / Math.max((double)anaconda.sitProgress / 6.0, 1.0));
      float partialTicks = ageInTicks - (float)anaconda.tickCount;
      float renderYaw = (float)anaconda.getMovementOffsets(0, partialTicks)[0];
      this.body_6.rotateAngleY = this.body_6.rotateAngleY
         + Mth.clamp((float)anaconda.getMovementOffsets(6, partialTicks)[0] - renderYaw, -15.0F, 15.0F) * (float) (Math.PI / 180.0);
      this.body_8.rotateAngleY = this.body_8.rotateAngleY
         + Mth.clamp((float)anaconda.getMovementOffsets(12, partialTicks)[0] - renderYaw, -15.0F, 15.0F) * (float) (Math.PI / 180.0);
      this.body_10.rotateAngleY = this.body_10.rotateAngleY
         + Mth.clamp((float)anaconda.getMovementOffsets(18, partialTicks)[0] - renderYaw, -15.0F, 15.0F) * (float) (Math.PI / 180.0);
      this.body_12.rotateAngleY = this.body_12.rotateAngleY
         + Mth.clamp((float)anaconda.getMovementOffsets(24, partialTicks)[0] - renderYaw, -15.0F, 15.0F) * (float) (Math.PI / 180.0);
      this.swing(this.head_main, 0.5F * globalSpeed, 0.6F * globalDegree, false, -5.4F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_1, 0.5F * globalSpeed, 0.8F * globalDegree, false, -4.4F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_2, 0.5F * globalSpeed, 0.6F * globalDegree, false, -3.6F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_3, 0.5F * globalSpeed, 1.0F * globalDegree, false, -2.8F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_4, 0.5F * globalSpeed, 0.8F * globalDegree, false, -2.0F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_5, 0.5F * globalSpeed, 1.0F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_6, 0.5F * globalSpeed, 0.8F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_7, 0.5F * globalSpeed, 1.0F * globalDegree, false, 2.8F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_8, 0.5F * globalSpeed, 0.6F * globalDegree, false, 3.6F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_9, 0.5F * globalSpeed, 0.8F * globalDegree, false, 4.4F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_10, 0.5F * globalSpeed, 0.6F * globalDegree, false, 5.4F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_11, 0.5F * globalSpeed, 0.6F * globalDegree, false, 6.6F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_12, 0.5F * globalSpeed, 0.8F * globalDegree, false, 7.0F, 0.0F, limbSwing, limbSwingAmount);
      this.swing(this.body_13, 0.5F * globalSpeed, 0.6F * globalDegree, false, 8.2F, 0.0F, limbSwing, limbSwingAmount);
      if (anaconda.isAngry()) {
         this.body_1.rotateAngleX = this.body_1.rotateAngleX + (float)Math.toRadians(-18.26F);
         this.head_main.rotateAngleX = this.head_main.rotateAngleX + (float)Math.toRadians(18.26F);
         this.body_head_top.rotateAngleX = this.body_head_top.rotateAngleX + (float)Math.toRadians(-41.74F);
         this.body_head_top_1.rotateAngleX = this.body_head_top_1.rotateAngleX + (float)Math.toRadians(49.57F);
      }

      if (!anaconda.isInWater() && !anaconda.isBaby() && anaconda.canMove()) {
         double difference = 0.0;
         int counter = 0;
         int parts = 0;

         for (EntityAnaconda.EntityAnacondaPart multipart : anaconda.anacondaParts) {
            if (counter > 0 && counter < 3 && multipart.getParent() == anaconda) {
               Vec3 position = multipart.getPosition(0.0F).add(0.0, difference, 0.0);
               BlockHitResult rayTrace = anaconda.level()
                  .clip(new ClipContext(position.add(0.0, 3.0, 0.0), position.add(0.0, -3.0, 0.0), Block.COLLIDER, Fluid.ANY, null));
               Vec3 vec3d = rayTrace.getLocation();
               difference = vec3d.y() - position.y;
               float angle = Math.abs(difference) > 0.2 ? (float)Mth.atan2(difference, 0.0) : 0.0F;
               float newZ = Mth.lerp(0.1F, anaconda.buffer[counter], this.bodyParts[counter].defaultRotationX + angle);
               this.bodyParts[parts].rotateAngleX = newZ / 2.0F;
               this.bodyParts[parts + 1].rotateAngleX = newZ / 4.0F;
               this.bodyParts[parts + 2].rotateAngleX = -newZ / 4.0F;
               this.bodyParts[parts + 3].rotateAngleX = -newZ / 2.0F;
               anaconda.buffer[counter] = newZ;
               parts += 4;
            }

            counter++;
         }
      }

      if (anaconda.sitProgress != 0) {
         this.progressPosition(this.body_5, (float)anaconda.sitProgress, -8.0F, 23.0F, -3.0F, (float)anaconda.ticksToSit);
         this.progressRotation(
            this.body_1,
            (float)anaconda.sitProgress,
            (float)Math.toRadians(39.13F),
            (float)Math.toRadians(67.83F),
            (float)Math.toRadians(44.35F),
            (float)anaconda.ticksToSit
         );
         this.progressRotation(
            this.body_2, (float)anaconda.sitProgress, (float)Math.toRadians(-15.65F), (float)Math.toRadians(70.43F), 0.0F, (float)anaconda.ticksToSit
         );
         this.progressRotation(this.body_3, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(75.65F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_4, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(75.65F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_5, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(67.83F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_6, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(-57.39F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_7, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(49.57F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_8, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(57.39F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_9, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(75.65F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_10, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(62.61F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_11, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(-49.57F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_12, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(39.13F), 0.0F, (float)anaconda.ticksToSit);
         this.progressRotation(this.body_13, (float)anaconda.sitProgress, 0.0F, (float)Math.toRadians(49.57F), 0.0F, (float)anaconda.ticksToSit);
      }
   }
}

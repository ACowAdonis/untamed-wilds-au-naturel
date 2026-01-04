package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.Mth;
import untamedwilds.entity.reptile.EntitySnake;

public class ModelSnake extends AdvancedEntityModel<EntitySnake> {
   public AdvancedModelBox main_neck;
   public AdvancedModelBox head_face;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox body_1;
   public AdvancedModelBox head_fangs;
   public AdvancedModelBox head_tongue;
   public AdvancedModelBox body_2;
   public AdvancedModelBox body_3;
   public AdvancedModelBox body_4;
   public AdvancedModelBox body_5;
   public AdvancedModelBox body_6;
   public AdvancedModelBox body_7;
   public AdvancedModelBox body_8;
   public AdvancedModelBox body_9;
   public AdvancedModelBox body_10;
   private final ModelAnimator animator;

   public ModelSnake() {
      this.texWidth = 32;
      this.texHeight = 32;
      this.main_neck = new AdvancedModelBox(this, 0, 0);
      this.main_neck.setRotationPoint(0.0F, 0.01F, -4.0F);
      this.main_neck.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 0, 3);
      this.head_face.setRotationPoint(0.0F, 0.0F, -1.0F);
      this.head_face.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F);
      this.head_fangs = new AdvancedModelBox(this, 1, 8);
      this.head_fangs.setRotationPoint(0.0F, 0.0F, 0.5F);
      this.head_fangs.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 0, 12);
      this.head_jaw.setRotationPoint(0.0F, 0.0F, -1.0F);
      this.head_jaw.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F);
      this.head_tongue = new AdvancedModelBox(this, -1, 16);
      this.head_tongue.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.head_tongue.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_tongue, -0.01F, 0.0F, 0.0F);
      this.body_1 = new AdvancedModelBox(this, 12, 0);
      this.body_1.setRotationPoint(0.0F, -0.01F, -4.0F);
      this.body_1.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_1.setScale(0.9F, 0.9F, 1.0F);
      this.body_2 = new AdvancedModelBox(this, 12, 0);
      this.body_2.setRotationPoint(0.0F, 0.01F, -4.0F);
      this.body_2.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_2.setScale(1.1F, 1.1F, 1.0F);
      this.body_3 = new AdvancedModelBox(this, 12, 0);
      this.body_3.setRotationPoint(0.0F, -0.01F, -4.0F);
      this.body_3.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_3.setScale(1.2F, 1.2F, 1.0F);
      this.body_4 = new AdvancedModelBox(this, 12, 0);
      this.body_4.setRotationPoint(0.0F, 0.01F, 0.0F);
      this.body_4.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_4.setScale(1.3F, 1.3F, 1.0F);
      this.body_5 = new AdvancedModelBox(this, 12, 0);
      this.body_5.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.body_5.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_5.setScale(1.3F, 1.3F, 1.0F);
      this.body_6 = new AdvancedModelBox(this, 12, 0);
      this.body_6.setRotationPoint(0.0F, 0.01F, 4.0F);
      this.body_6.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_6.setScale(1.3F, 1.3F, 1.0F);
      this.body_7 = new AdvancedModelBox(this, 12, 0);
      this.body_7.setRotationPoint(0.0F, -0.01F, 4.0F);
      this.body_7.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_7.setScale(1.2F, 1.2F, 1.0F);
      this.body_8 = new AdvancedModelBox(this, 12, 0);
      this.body_8.setRotationPoint(0.0F, 0.01F, 4.0F);
      this.body_8.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_8.setScale(1.1F, 1.1F, 1.0F);
      this.body_9 = new AdvancedModelBox(this, 12, 0);
      this.body_9.setRotationPoint(0.0F, -0.01F, 4.0F);
      this.body_9.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_9.setScale(1.0F, 1.0F, 1.0F);
      this.body_10 = new AdvancedModelBox(this, 12, 6);
      this.body_10.setRotationPoint(0.0F, 0.01F, 4.0F);
      this.body_10.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.body_10.setScale(0.8F, 0.8F, 1.0F);
      this.body_8.addChild(this.body_9);
      this.body_1.addChild(this.main_neck);
      this.body_7.addChild(this.body_8);
      this.main_neck.addChild(this.head_jaw);
      this.body_2.addChild(this.body_1);
      this.body_5.addChild(this.body_4);
      this.body_9.addChild(this.body_10);
      this.main_neck.addChild(this.head_face);
      this.head_face.addChild(this.head_fangs);
      this.body_4.addChild(this.body_3);
      this.body_5.addChild(this.body_6);
      this.head_jaw.addChild(this.head_tongue);
      this.body_6.addChild(this.body_7);
      this.body_3.addChild(this.body_2);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_5);
   }

   public void animate(IAnimatedEntity entity) {
      this.animator.update(entity);
      this.animator.setAnimation(EntitySnake.ANIMATION_TONGUE);
      this.animator.startKeyframe(4);
      this.rotate(this.animator, this.head_tongue, 26.08F, 36.52F, 0.0F);
      this.animator.move(this.head_tongue, 0.0F, 0.0F, -2.5F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(3);
      this.rotate(this.animator, this.head_tongue, -26.08F, -36.52F, 0.0F);
      this.animator.move(this.head_tongue, 0.0F, 0.0F, -3.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(3);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_neck,
         this.head_face,
         this.head_jaw,
         this.body_1,
         this.head_fangs,
         this.head_tongue,
         this.body_2,
         this.body_3,
         this.body_4,
         this.body_5,
         this.body_6,
         this.body_7,
         new AdvancedModelBox[]{this.body_8, this.body_9, this.body_10}
      );
   }

   public void setupAnim(EntitySnake snake, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(snake);
      limbSwing = (float)((double)limbSwing * -1.2);
      float globalSpeed = 1.0F;
      float globalDegree = 2.0F;
      limbSwingAmount = 0.5F;
      if (snake.isInWater() && !snake.onGround()) {
         this.setRotateAngle(this.body_5, (float)(snake.getDeltaMovement().get(Axis.Y) * -30.0 * Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.body_1.rotateAngleY = Mth.rotLerp(0.05F, this.body_4.rotateAngleY, snake.offset);
      this.body_9.rotateAngleY = Mth.rotLerp(0.05F, this.body_6.rotateAngleY, -1.0F * snake.offset);
      this.body_10.rotateAngleY = Mth.rotLerp(0.05F, this.body_8.rotateAngleY, -2.0F * snake.offset);
      limbSwingAmount = (float)((double)limbSwingAmount / Math.max((double)snake.sitProgress / 6.0, 1.0));
      this.swing(this.main_neck, 0.5F * globalSpeed, 0.6F * globalDegree, false, -5.4F, 0.0F, limbSwing, limbSwingAmount);
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
      if (snake.isAngry()) {
         this.body_1.rotateAngleX = this.body_1.rotateAngleX + (float)Math.toRadians(-18.26F);
         this.main_neck.rotateAngleX = this.main_neck.rotateAngleX + (float)Math.toRadians(18.26F);
         this.head_face.rotateAngleX = this.head_face.rotateAngleX + (float)Math.toRadians(-41.74F);
         this.head_jaw.rotateAngleX = this.head_jaw.rotateAngleX + (float)Math.toRadians(49.57F);
         if (snake.isRattler()) {
            this.body_9.rotateAngleX = (float)((double)this.body_9.rotateAngleX + Math.toRadians(31.3F));
            this.body_10.rotateAngleX = (float)((double)this.body_10.rotateAngleX + Math.toRadians(60.0));
            this.swing(this.body_10, globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, (float)snake.tickCount, 0.5F);
            this.flap(this.body_10, globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, (float)snake.tickCount, 0.5F);
         }
      }

      if (snake.sitProgress != 0) {
         this.progressPosition(this.body_5, (float)snake.sitProgress, -4.0F, 23.0F, -3.0F, 20.0F);
         this.progressRotation(
            this.body_1, (float)snake.sitProgress, (float)Math.toRadians(39.13F), (float)Math.toRadians(67.83F), (float)Math.toRadians(44.35F), 20.0F
         );
         this.progressRotation(this.body_2, (float)snake.sitProgress, (float)Math.toRadians(-15.65F), (float)Math.toRadians(70.43F), 0.0F, 20.0F);
         this.progressRotation(this.body_3, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(75.65F), 0.0F, 20.0F);
         this.progressRotation(this.body_4, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(75.65F), 0.0F, 20.0F);
         this.progressRotation(this.body_5, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(67.83F), 0.0F, 20.0F);
         this.progressRotation(this.body_6, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(-57.39F), 0.0F, 20.0F);
         this.progressRotation(this.body_7, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(49.57F), 0.0F, 20.0F);
         this.progressRotation(this.body_8, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(57.39F), 0.0F, 20.0F);
         this.progressRotation(this.body_9, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(75.65F), 0.0F, 20.0F);
         this.progressRotation(this.body_10, (float)snake.sitProgress, 0.0F, (float)Math.toRadians(62.61F), 0.0F, 20.0F);
      }
   }
}

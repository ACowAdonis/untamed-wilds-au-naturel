package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBigCat;

public class ModelBigCat extends AdvancedEntityModel<EntityBigCat> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox body_abdomen;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox arm_right_upper;
   public AdvancedModelBox arm_left_upper;
   public AdvancedModelBox leg_right_upper;
   public AdvancedModelBox tail_1;
   public AdvancedModelBox leg_left_upper;
   public AdvancedModelBox leg_right_lower;
   public AdvancedModelBox leg_right_paw;
   public AdvancedModelBox tail_2;
   public AdvancedModelBox tail_3;
   public AdvancedModelBox tail_4;
   public AdvancedModelBox tail_5;
   public AdvancedModelBox leg_left_lower;
   public AdvancedModelBox leg_left_paw;
   public AdvancedModelBox head_main;
   public AdvancedModelBox eye_right;
   public AdvancedModelBox eye_right_1;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox head_jaw;
   public AdvancedModelBox ear_right;
   public AdvancedModelBox ear_left;
   public AdvancedModelBox head_cheek_right;
   public AdvancedModelBox head_cheek_left;
   public AdvancedModelBox head_snout_teeth;
   public AdvancedModelBox arm_right_lower;
   public AdvancedModelBox arm_right_paw;
   public AdvancedModelBox arm_left_lower;
   public AdvancedModelBox arm_left_paw;
   public AdvancedModelBox teeth_right;
   public AdvancedModelBox teeth_left;
   public AdvancedModelBox neck_mane;
   public AdvancedModelBox teeth_down_right;
   public AdvancedModelBox teeth_down_left;
   private final ModelAnimator animator;
   private static AdvancedModelBox[] bodyParts_tail;
   private float tailX = -1.0F;

   public ModelBigCat() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.arm_left_lower = new AdvancedModelBox(this, 42, 33);
      this.arm_left_lower.mirror = true;
      this.arm_left_lower.setRotationPoint(1.0F, 7.0F, 0.5F);
      this.arm_left_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_lower, -0.5009095F, 0.0F, 0.091106184F);
      this.eye_right = new AdvancedModelBox(this, 19, 34);
      this.eye_right.setRotationPoint(-2.5F, -2.0F, -5.01F);
      this.eye_right.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F);
      this.eye_right_1 = new AdvancedModelBox(this, 23, 34);
      this.eye_right_1.setRotationPoint(2.5F, -2.0F, -5.01F);
      this.eye_right_1.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F);
      this.arm_left_upper = new AdvancedModelBox(this, 42, 20);
      this.arm_left_upper.mirror = true;
      this.arm_left_upper.setRotationPoint(2.0F, -2.0F, -2.5F);
      this.arm_left_upper.addBox(-1.0F, 0.0F, -2.5F, 4.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.arm_left_upper, 0.27314404F, 0.0F, -0.091106184F);
      this.ear_left = new AdvancedModelBox(this, 20, 35);
      this.ear_left.mirror = true;
      this.ear_left.setRotationPoint(2.0F, -2.5F, -2.0F);
      this.ear_left.addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_left, 0.0F, -0.045553092F, 0.22759093F);
      this.body_abdomen = new AdvancedModelBox(this, 40, 0);
      this.body_abdomen.setRotationPoint(0.0F, -1.0F, 6.0F);
      this.body_abdomen.addBox(-3.5F, -4.0F, 0.0F, 7.0F, 10.0F, 10.0F, 0.0F);
      this.leg_left_paw = new AdvancedModelBox(this, 54, 40);
      this.leg_left_paw.setRotationPoint(0.0F, 6.8F, -1.0F);
      this.leg_left_paw.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_left_paw, 0.31869712F, 0.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 34);
      this.head_main.setRotationPoint(0.0F, -0.5F, -4.5F);
      this.head_main.addBox(-3.5F, -3.0F, -5.0F, 7.0F, 6.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_main, 0.091106184F, 0.0F, 0.0F);
      this.arm_right_upper = new AdvancedModelBox(this, 42, 20);
      this.arm_right_upper.setRotationPoint(-2.0F, -2.0F, -2.5F);
      this.arm_right_upper.addBox(-3.0F, 0.0F, -2.5F, 4.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.arm_right_upper, 0.27314404F, 0.0F, 0.091106184F);
      this.head_neck = new AdvancedModelBox(this, 0, 22);
      this.head_neck.setRotationPoint(0.0F, -1.5F, -4.5F);
      this.head_neck.addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -0.13665928F, 0.0F, 0.0F);
      this.leg_right_lower = new AdvancedModelBox(this, 58, 46);
      this.leg_right_lower.setRotationPoint(-1.0F, 5.5F, 3.0F);
      this.leg_right_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right_lower, -0.13665928F, 0.0F, -0.045553092F);
      this.head_cheek_left = new AdvancedModelBox(this, 28, 40);
      this.head_cheek_left.setRotationPoint(2.0F, 0.0F, -2.0F);
      this.head_cheek_left.addBox(0.0F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_cheek_left, 0.0F, 0.0F, 0.22759093F);
      this.tail_1 = new AdvancedModelBox(this, 74, 0);
      this.tail_1.setRotationPoint(0.0F, -2.5F, 9.0F);
      this.tail_1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_1, -1.0016445F, 0.0F, 0.0F);
      this.ear_right = new AdvancedModelBox(this, 20, 35);
      this.ear_right.setRotationPoint(-2.0F, -2.5F, -2.0F);
      this.ear_right.addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.ear_right, 0.0F, 0.045553092F, -0.22759093F);
      this.tail_3 = new AdvancedModelBox(this, 74, 16);
      this.tail_3.setRotationPoint(0.0F, 0.0F, 5.5F);
      this.tail_3.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_3, 0.31869712F, 0.0F, 0.0F);
      this.tail_4 = new AdvancedModelBox(this, 74, 24);
      this.tail_4.setRotationPoint(0.0F, -0.1F, 5.5F);
      this.tail_4.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_4, 0.63739425F, 0.0F, 0.0F);
      this.tail_5 = new AdvancedModelBox(this, 74, 32);
      this.tail_5.setRotationPoint(0.0F, -0.1F, 5.5F);
      this.tail_5.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_5, 0.8F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 0, 45);
      this.head_snout.setRotationPoint(0.0F, -1.2F, -4.0F);
      this.head_snout.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 3.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.31869712F, 0.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 25, 35);
      this.head_jaw.setRotationPoint(0.0F, 1.9F, -3.5F);
      this.head_jaw.addBox(-2.0F, 0.0F, -3.5F, 4.0F, 1.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.head_jaw, 0.13665928F, 0.0F, 0.0F);
      this.head_jaw.scaleX = 0.95F;
      this.leg_left_upper = new AdvancedModelBox(this, 38, 46);
      this.leg_left_upper.mirror = true;
      this.leg_left_upper.setRotationPoint(2.0F, 0.0F, 6.0F);
      this.leg_left_upper.addBox(-1.0F, -2.0F, -3.0F, 4.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_left_upper, -0.18203785F, 0.0F, -0.045553092F);
      this.arm_left_paw = new AdvancedModelBox(this, 54, 34);
      this.arm_left_paw.mirror = true;
      this.arm_left_paw.setRotationPoint(0.0F, 7.0F, -1.0F);
      this.arm_left_paw.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_paw, 0.22759093F, 0.0F, 0.0F);
      this.leg_right_upper = new AdvancedModelBox(this, 38, 46);
      this.leg_right_upper.setRotationPoint(-2.0F, 0.0F, 6.0F);
      this.leg_right_upper.addBox(-3.0F, -2.0F, -3.0F, 4.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_right_upper, -0.18203785F, 0.0F, 0.045553092F);
      this.head_snout_teeth = new AdvancedModelBox(this, 16, 45);
      this.head_snout_teeth.setRotationPoint(0.0F, 2.0F, 0.1F);
      this.head_snout_teeth.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 2.0F, 4.0F, 0.0F);
      this.arm_right_paw = new AdvancedModelBox(this, 54, 34);
      this.arm_right_paw.setRotationPoint(0.0F, 7.0F, -1.0F);
      this.arm_right_paw.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_paw, 0.22759093F, 0.0F, 0.0F);
      this.leg_left_lower = new AdvancedModelBox(this, 58, 46);
      this.leg_left_lower.mirror = true;
      this.leg_left_lower.setRotationPoint(1.0F, 5.5F, 3.0F);
      this.leg_left_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left_lower, -0.13665928F, 0.0F, 0.045553092F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 11.0F, -4.0F);
      this.body_main.addBox(-4.0F, -5.0F, -6.0F, 8.0F, 10.0F, 12.0F, 0.0F);
      this.leg_right_paw = new AdvancedModelBox(this, 54, 40);
      this.leg_right_paw.mirror = true;
      this.leg_right_paw.setRotationPoint(0.0F, 6.8F, -1.0F);
      this.leg_right_paw.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.leg_right_paw, 0.31869712F, 0.0F, 0.0F);
      this.arm_right_lower = new AdvancedModelBox(this, 42, 33);
      this.arm_right_lower.setRotationPoint(-1.0F, 7.0F, 0.5F);
      this.arm_right_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_lower, -0.5009095F, 0.0F, -0.091106184F);
      this.tail_2 = new AdvancedModelBox(this, 74, 8);
      this.tail_2.setRotationPoint(0.0F, -0.1F, 5.5F);
      this.tail_2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_2, -0.22759093F, 0.0F, 0.0F);
      this.head_cheek_right = new AdvancedModelBox(this, 28, 40);
      this.head_cheek_right.mirror = true;
      this.head_cheek_right.setRotationPoint(-2.0F, 0.0F, -2.0F);
      this.head_cheek_right.addBox(-3.0F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.head_cheek_right, 0.0F, 0.0F, -0.22759093F);
      this.teeth_right = new AdvancedModelBox(this, 16, 52);
      this.teeth_right.setRotationPoint(-1.48F, 2.4F, -2.7F);
      this.teeth_right.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.teeth_right, 0.0F, 0.0F, 0.045553092F);
      this.teeth_left = new AdvancedModelBox(this, 16, 52);
      this.teeth_left.setRotationPoint(1.48F, 2.4F, -2.7F);
      this.teeth_left.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.teeth_left, 0.0F, 0.0F, -0.045553092F);
      this.neck_mane = new AdvancedModelBox(this, 70, 44);
      this.neck_mane.setRotationPoint(0.0F, -1.0F, -2.6F);
      this.neck_mane.addBox(-5.5F, -3.5F, -4.0F, 11.0F, 12.0F, 8.0F, 0.0F);
      this.teeth_down_right = new AdvancedModelBox(this, 22, 52);
      this.teeth_down_right.setRotationPoint(-1.48F, 0.8F, -2.7F);
      this.teeth_down_right.addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.teeth_down_right, 0.091106184F, 0.0F, -0.18203785F);
      this.teeth_down_left = new AdvancedModelBox(this, 22, 52);
      this.teeth_down_left.setRotationPoint(1.48F, 0.8F, -2.7F);
      this.teeth_down_left.addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F);
      this.setRotateAngle(this.teeth_down_left, 0.091106184F, 0.0F, 0.18203785F);
      this.head_jaw.addChild(this.teeth_down_left);
      this.head_jaw.addChild(this.teeth_down_right);
      this.head_neck.addChild(this.neck_mane);
      this.head_snout.addChild(this.teeth_right);
      this.head_snout.addChild(this.teeth_left);
      this.arm_left_upper.addChild(this.arm_left_lower);
      this.head_main.addChild(this.eye_right);
      this.body_main.addChild(this.arm_left_upper);
      this.head_main.addChild(this.ear_left);
      this.body_main.addChild(this.body_abdomen);
      this.leg_left_lower.addChild(this.leg_left_paw);
      this.head_neck.addChild(this.head_main);
      this.head_main.addChild(this.eye_right_1);
      this.body_main.addChild(this.arm_right_upper);
      this.body_main.addChild(this.head_neck);
      this.leg_right_upper.addChild(this.leg_right_lower);
      this.head_main.addChild(this.head_cheek_left);
      this.body_abdomen.addChild(this.tail_1);
      this.head_main.addChild(this.ear_right);
      this.tail_3.addChild(this.tail_4);
      this.tail_4.addChild(this.tail_5);
      this.head_main.addChild(this.head_snout);
      this.head_main.addChild(this.head_jaw);
      this.body_abdomen.addChild(this.leg_left_upper);
      this.arm_left_lower.addChild(this.arm_left_paw);
      this.body_abdomen.addChild(this.leg_right_upper);
      this.head_snout.addChild(this.head_snout_teeth);
      this.arm_right_lower.addChild(this.arm_right_paw);
      this.leg_left_upper.addChild(this.leg_left_lower);
      this.leg_right_lower.addChild(this.leg_right_paw);
      this.arm_right_upper.addChild(this.arm_right_lower);
      this.tail_1.addChild(this.tail_2);
      this.head_main.addChild(this.head_cheek_right);
      this.tail_2.addChild(this.tail_3);
      bodyParts_tail = new AdvancedModelBox[]{this.tail_1, this.tail_2, this.tail_3, this.tail_4, this.tail_5};
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.body_abdomen,
         this.head_neck,
         this.arm_right_upper,
         this.arm_left_upper,
         this.leg_right_upper,
         this.tail_1,
         this.leg_left_upper,
         this.leg_right_lower,
         this.leg_right_paw,
         this.tail_2,
         this.tail_3,
         new AdvancedModelBox[]{
            this.tail_4,
            this.leg_left_lower,
            this.leg_left_paw,
            this.head_main,
            this.eye_right,
            this.eye_right_1,
            this.head_snout,
            this.head_jaw,
            this.ear_right,
            this.ear_left,
            this.head_cheek_right,
            this.head_cheek_left,
            this.head_snout_teeth,
            this.arm_right_lower,
            this.arm_right_paw,
            this.arm_left_lower,
            this.arm_left_paw,
            this.tail_5
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityBigCat big_cat = (EntityBigCat)entityIn;
      this.animator.update(big_cat);
      this.animator.setAnimation(EntityBigCat.IDLE_TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_jaw, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityBigCat.ATTACK_POUNCE);
      this.animator.startKeyframe(12);
      this.rotate(this.animator, this.body_main, -18.26F, 0.0F, 0.0F);
      this.animator.move(this.body_main, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.body_abdomen, -7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 52.17F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -20.87F, 0.0F, -20.87F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 7.83F);
      this.rotate(this.animator, this.arm_left_paw, 52.17F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_paw, 0.0F, 1.5F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -20.87F, 0.0F, 20.87F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -7.83F);
      this.rotate(this.animator, this.arm_right_paw, 52.17F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_paw, 0.0F, 1.5F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, 15.65F, 0.0F, -2.61F);
      this.animator.move(this.leg_left_lower, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, 15.65F, 0.0F, 2.61F);
      this.animator.move(this.leg_right_lower, 0.0F, -1.5F, 0.0F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.body_main, 2.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.body_abdomen, -7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, -10.43F, -5.22F, 7.83F);
      this.rotate(this.animator, this.head_main, 0.0F, 2.61F, -18.26F);
      this.rotate(this.animator, this.head_jaw, 52.17F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -52.17F, -18.26F, -10.44F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 7.83F);
      this.rotate(this.animator, this.arm_left_paw, 60.0F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_paw, 0.0F, 1.5F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -52.17F, 18.26F, 10.44F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -7.83F);
      this.rotate(this.animator, this.arm_right_paw, 60.0F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_paw, 0.0F, 1.5F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, 62.61F, 15.65F, -2.61F);
      this.rotate(this.animator, this.leg_left_paw, 78.26F, 0.0F, 0.0F);
      this.animator.move(this.leg_left_paw, 0.0F, 1.0F, 1.0F);
      this.rotate(this.animator, this.leg_right_upper, 62.61F, -15.65F, 2.61F);
      this.rotate(this.animator, this.leg_right_paw, 78.26F, 0.0F, 0.0F);
      this.animator.move(this.leg_right_paw, 0.0F, 1.0F, 1.0F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.body_main, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.body_abdomen, -23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, 0.0F, 2.61F, -18.26F);
      this.rotate(this.animator, this.arm_left_upper, 5.22F, 0.0F, -15.65F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 23.48F);
      this.animator.move(this.arm_left_upper, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_paw, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, 5.22F, 0.0F, 15.65F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -23.48F);
      this.animator.move(this.arm_right_upper, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_paw, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, 2.61F, -5.22F, -10.43F);
      this.animator.move(this.leg_left_paw, 0.0F, 1.0F, 1.0F);
      this.rotate(this.animator, this.leg_right_upper, 2.61F, 5.22F, 10.43F);
      this.animator.move(this.leg_right_paw, 0.0F, 1.0F, 1.0F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.body_main, 7.83F, 0.0F, 0.0F);
      this.animator.move(this.body_main, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.body_abdomen, -10.43F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, 2.61F, 7.83F, -2.61F);
      this.rotate(this.animator, this.arm_left_upper, 39.13F, 0.0F, -15.65F);
      this.animator.move(this.arm_left_upper, 0.0F, 3.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 23.48F);
      this.rotate(this.animator, this.arm_left_paw, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, 39.13F, 0.0F, 15.65F);
      this.animator.move(this.arm_right_upper, 0.0F, 3.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -23.48F);
      this.rotate(this.animator, this.arm_right_paw, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, -5.22F, 0.0F, -2.61F);
      this.rotate(this.animator, this.leg_right_upper, -5.22F, 0.0F, 2.61F);
      this.rotate(this.animator, this.tail_1, -15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityBigCat.ATTACK_MAUL);
      this.animator.startKeyframe(8);
      this.animator.move(this.body_main, 0.0F, -0.7F, 0.0F);
      this.rotate(this.animator, this.body_main, -5.22F, -10.43F, 7.83F);
      this.rotate(this.animator, this.body_abdomen, -5.22F, 10.43F, -7.83F);
      this.rotate(this.animator, this.head_main, 31.3F, -13.04F, 23.48F);
      this.animator.move(this.arm_right_upper, -1.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -78.26F, 33.91F, -10.43F);
      this.rotate(this.animator, this.arm_right_lower, -62.61F, -5.22F, -5.22F);
      this.animator.move(this.arm_right_paw, 0.0F, 2.0F, 1.4F);
      this.rotate(this.animator, this.arm_right_paw, 164.35F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, 0.9F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, 23.48F, 10.43F, -10.43F);
      this.rotate(this.animator, this.leg_right_upper, -2.61F, 0.0F, 2.61F);
      this.rotate(this.animator, this.leg_left_upper, -2.61F, 0.0F, -2.61F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, 1.9F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, -10.43F, 7.83F);
      this.rotate(this.animator, this.body_abdomen, -23.48F, 10.43F, -7.83F);
      this.rotate(this.animator, this.head_main, 39.13F, 7.83F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 33.91F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, -54.78F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_upper, 0.0F, 0.0F, -2.0F);
      this.rotate(this.animator, this.arm_right_upper, -73.04F, 13.04F, -10.43F);
      this.rotate(this.animator, this.arm_right_lower, -13.04F, -5.22F, -5.22F);
      this.animator.move(this.arm_right_paw, 0.0F, 0.0F, -0.7F);
      this.rotate(this.animator, this.arm_right_paw, 117.39F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, -1.2F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, 15.65F, 10.43F, -10.43F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 5.22F);
      this.rotate(this.animator, this.arm_left_paw, 31.3F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, -2.61F, 0.0F, 2.61F);
      this.rotate(this.animator, this.leg_left_upper, -2.61F, 0.0F, -2.61F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityBigCat.IDLE_STRETCH);
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, 5.0F, 6.0F);
      this.rotate(this.animator, this.body_main, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, -15.65F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -7.83F, 0.0F, 0.0F);
      this.animator.move(this.body_abdomen, 0.0F, 0.0F, -2.0F);
      this.rotate(this.animator, this.body_abdomen, 20.87F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -62.0F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_left_paw, 57.39F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_upper, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -62.0F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_right_paw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, -39.14F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, -39.14F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(70);
      this.animator.move(this.body_main, 0.0F, 5.0F, 6.0F);
      this.rotate(this.animator, this.body_main, 18.26F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, -15.65F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_main, -7.83F, 0.0F, 0.0F);
      this.animator.move(this.body_abdomen, 0.0F, 0.0F, -2.0F);
      this.rotate(this.animator, this.body_abdomen, 20.87F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -62.0F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_left_paw, 57.39F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_upper, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -62.0F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_right_paw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, -39.14F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, -39.14F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(20);
   }

   public void setupAnim(EntityBigCat big_cat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(big_cat);
      float globalSpeed = 2.4F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount * 2.0F);
      limbSwing *= 0.5F;
      float tail_scale = big_cat.hasFluffyTail() ? 2.0F : 1.0F;

      for (AdvancedModelBox tail_part : bodyParts_tail) {
         tail_part.scaleX = tail_scale;
         tail_part.scaleY = tail_scale;
      }

      boolean isPurring = big_cat.getAnimation() == EntityBigCat.IDLE_STRETCH && big_cat.getAnimationTick() > 20;
      double scaleX = Math.sin((double)(ageInTicks * (isPurring ? 2.0F : 0.05F)));
      double scaleY = Math.sin((double)(ageInTicks / (float)(isPurring ? 8 : 16)));
      this.body_main.setScale((float)(1.0 + scaleX * 0.08F), (float)(1.0 + scaleY * 0.06F), 1.0F);
      this.body_abdomen.setScale((float)(1.0 + scaleX * 0.06F), (float)(1.0 + scaleY * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!big_cat.isSleeping()) {
         this.chainFlap(bodyParts_tail, 0.3F * globalSpeed, 0.25F * globalDegree, 0.5, ageInTicks / 6.0F, 2.0F);
      }

      if (!big_cat.shouldRenderEyes()) {
         this.eye_right.setRotationPoint(-2.5F, -2.0F, -4.5F);
         this.eye_right_1.setRotationPoint(2.5F, -2.0F, -4.5F);
      }

      if (!big_cat.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 3.0F, new AdvancedModelBox[]{this.head_main});
      }

      if (big_cat.isInWater() && !big_cat.onGround()) {
         limbSwing = ageInTicks / 3.0F;
         limbSwingAmount = 0.5F;
         this.body_main.rotationPointY += 4.0F;
         this.setRotateAngle(this.head_neck, -0.18203785F, 0.0F, 0.0F);
         float pitch = Mth.clamp(big_cat.getXRot() - 10.0F, -25.0F, 25.0F);
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      float newZ = Mth.lerp(0.4F, this.tailX, this.tail_1.defaultRotationX + (float)big_cat.getCurrentSpeed() * 2.0F);
      this.tail_1.rotateAngleX = newZ;
      this.tailX = newZ;
      if (big_cat.canMove()) {
         if (!(big_cat.getCurrentSpeed() > 0.1F) && !big_cat.isAngry()) {
            this.bob(this.arm_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_upper, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_paw, 0.5F * globalSpeed, 2.0F * globalDegree, false, 0.4F, 1.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_upper, 0.5F * globalSpeed, globalDegree, true, 2.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.6F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_paw, 0.5F * globalSpeed, 2.0F * globalDegree, false, 2.8F, 1.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_upper, 0.5F * globalSpeed, globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.2F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_paw, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.4F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_upper, 0.5F * globalSpeed, globalDegree, true, 3.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 3.6F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_paw, 0.5F * globalSpeed, 0.8F * globalDegree, false, 3.8F, 0.0F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.body_main, 0.5F * globalSpeed, 0.5F, false, limbSwing, limbSwingAmount);
            this.walk(this.body_main, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_neck, 0.5F * globalSpeed, -0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.body_abdomen, 0.5F * globalSpeed, 0.3F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_upper, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_paw, 0.5F * globalSpeed, 2.0F * globalDegree, false, 0.4F, 1.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_upper, 0.5F * globalSpeed, globalDegree, true, 0.6F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.8F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_paw, 0.5F * globalSpeed, 2.0F * globalDegree, false, 1.0F, 1.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_upper, 0.5F * globalSpeed, globalDegree, true, 1.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.6F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_paw, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.8F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_upper, 0.5F * globalSpeed, globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.2F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_paw, 0.5F * globalSpeed, 0.8F * globalDegree, false, 2.4F, 0.0F, limbSwing, limbSwingAmount);
         }
      }

      if (big_cat.aggroProgress != 0) {
         this.head_snout_teeth.scaleX = 1.05F;
         this.progressPosition(this.body_main, (float)big_cat.aggroProgress, 0.0F, 14.0F, -4.0F, 40.0F);
         this.progressPosition(this.arm_right_upper, (float)big_cat.aggroProgress, -2.0F, -4.0F, -2.5F, 40.0F);
         this.progressPosition(this.arm_left_upper, (float)big_cat.aggroProgress, 2.0F, -4.0F, -2.5F, 40.0F);
         this.progressPosition(this.leg_right_upper, (float)big_cat.aggroProgress, -2.0F, -2.0F, 6.0F, 40.0F);
         this.progressPosition(this.leg_left_upper, (float)big_cat.aggroProgress, 2.0F, -2.0F, 6.0F, 40.0F);
         this.progressRotation(this.head_neck, (float)big_cat.aggroProgress, 0.22759093F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_main, (float)big_cat.aggroProgress, -0.22759093F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.ear_right, (float)big_cat.aggroProgress, -0.22759093F, 0.045553092F, -0.22759093F, 40.0F);
         this.progressRotation(this.ear_left, (float)big_cat.aggroProgress, -0.22759093F, -0.045553092F, 0.22759093F, 40.0F);
         this.progressRotation(this.tail_1, (float)big_cat.aggroProgress, (float)Math.toRadians(-44.35), 0.0F, 0.0F, 40.0F);
      } else {
         this.head_snout_teeth.scaleX = 0.9F;
      }

      if (big_cat.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)big_cat.sitProgress, 0.0F, 18.5F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left_upper, (float)big_cat.sitProgress, 2.0F, -2.0F, 6.0F, 40.0F);
         this.progressPosition(this.leg_left_paw, (float)big_cat.sitProgress, 0.0F, 7.8F, 0.0F, 40.0F);
         this.progressPosition(this.arm_left_paw, (float)big_cat.sitProgress, 0.0F, 7.8F, 0.0F, 40.0F);
         this.progressPosition(this.leg_right_upper, (float)big_cat.sitProgress, -2.0F, -2.0F, 6.0F, 40.0F);
         this.progressPosition(this.leg_right_paw, (float)big_cat.sitProgress, 0.0F, 7.8F, 0.0F, 40.0F);
         this.progressPosition(this.arm_right_paw, (float)big_cat.sitProgress, 0.0F, 7.8F, 0.0F, 40.0F);
         this.progressRotation(this.head_neck, (float)big_cat.sitProgress, -0.5462881F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_left_upper, (float)big_cat.sitProgress, -0.27314404F, -0.0F, -0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)big_cat.sitProgress, -1.2292354F, -0.22759093F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_paw, (float)big_cat.sitProgress, 1.548107F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_left_upper, (float)big_cat.sitProgress, 0.27314404F, -6.200655E-17F, -0.24361071F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)big_cat.sitProgress, -1.8668041F, 0.0F, 0.10803588F, 40.0F);
         this.progressRotation(this.arm_left_paw, (float)big_cat.sitProgress, 1.5934856F, -0.18203785F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_upper, (float)big_cat.sitProgress, -0.27314404F, -0.0F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_right_lower, (float)big_cat.sitProgress, -1.2292354F, 0.22759093F, -0.045553092F, 40.0F);
         this.progressRotation(this.leg_right_paw, (float)big_cat.sitProgress, 1.548107F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_upper, (float)big_cat.sitProgress, 0.27314404F, 6.200655E-17F, 0.24361071F, 40.0F);
         this.progressRotation(this.arm_right_lower, (float)big_cat.sitProgress, -1.8668041F, 0.0F, -0.10803588F, 40.0F);
         this.progressRotation(this.arm_right_paw, (float)big_cat.sitProgress, 1.5934856F, 0.18203785F, 0.0F, 40.0F);
         this.progressRotation(this.tail_1, (float)big_cat.sitProgress, (float) (-Math.PI / 3), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.tail_2, (float)big_cat.sitProgress, 0.59184116F, 0.0F, 0.0F, 40.0F);
      }

      if (big_cat.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)big_cat.sleepProgress, -4.0F, 19.0F, -4.0F, 40.0F);
         this.progressRotation(this.body_main, (float)big_cat.sleepProgress, 0.0F, 0.0F, -1.5025539F, 40.0F);
         this.progressRotation(this.body_abdomen, (float)big_cat.sleepProgress, -0.4553564F, -0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_upper, (float)big_cat.sleepProgress, -0.5009095F, -0.0F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)big_cat.sleepProgress, -0.13665928F, 0.0F, 0.7740535F, 40.0F);
         this.progressRotation(this.head_neck, (float)big_cat.sleepProgress, 0.27314404F, 0.22759093F, 0.0F, 40.0F);
         this.progressRotation(this.head_main, (float)big_cat.sleepProgress, 0.4553564F, 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.arm_right_upper, (float)big_cat.sleepProgress, -0.27314404F, 0.0F, 0.09110619F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)big_cat.sleepProgress, -0.5009095F, -0.09110619F, 1.0472F, 40.0F);
      }
   }
}

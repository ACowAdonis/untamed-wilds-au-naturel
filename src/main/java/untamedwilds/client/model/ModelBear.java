package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBear;

public class ModelBear extends AdvancedEntityModel<EntityBear> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox body_buttocks;
   private final AdvancedModelBox body_torso;
   private final AdvancedModelBox leg_left_1;
   private final AdvancedModelBox leg_right_1;
   private final AdvancedModelBox leg_left_2;
   private final AdvancedModelBox leg_left_foot;
   private final AdvancedModelBox leg_right_2;
   private final AdvancedModelBox leg_right_foot;
   private final AdvancedModelBox arm_left_1;
   private final AdvancedModelBox head_face;
   private final AdvancedModelBox arm_right_1;
   private final AdvancedModelBox arm_left_2;
   private final AdvancedModelBox arm_left_foot;
   private final AdvancedModelBox head_snout;
   private final AdvancedModelBox head_jaw;
   private final AdvancedModelBox head_eyes;
   private final AdvancedModelBox arm_right_2;
   private final AdvancedModelBox arm_right_foot;
   private final ModelAnimator animator;

   public ModelBear() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.body_main = new AdvancedModelBox(this, 52, 0);
      this.body_main.setRotationPoint(0.0F, 8.5F, 0.0F);
      this.body_main.addBox(-5.5F, -5.5F, -5.0F, 11.0F, 11.0F, 10.0F, 0.0F);
      this.body_torso = new AdvancedModelBox(this, 52, 21);
      this.body_torso.setRotationPoint(0.0F, -0.5F, -1.0F);
      this.body_torso.addBox(-5.0F, -5.0F, -10.0F, 10.0F, 10.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_torso, 0.13665928F, 0.0F, 0.0F);
      this.body_buttocks = new AdvancedModelBox(this, 0, 0);
      this.body_buttocks.setRotationPoint(0.0F, 0.0F, 3.0F);
      this.body_buttocks.addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 14.0F, 0.0F);
      this.setRotateAngle(this.body_buttocks, -0.18203785F, 0.0F, 0.0F);
      AdvancedModelBox body_tail = new AdvancedModelBox(this, 38, 0);
      body_tail.setRotationPoint(0.0F, -4.0F, 13.0F);
      body_tail.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F);
      this.setRotateAngle(body_tail, 0.4098033F, 0.0F, 0.0F);
      this.head_face = new AdvancedModelBox(this, 0, 26);
      this.head_face.setRotationPoint(0.0F, 0.0F, -9.0F);
      this.head_face.addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.head_face, 0.13665928F, 0.0F, 0.0F);
      this.head_eyes = new AdvancedModelBox(this, 28, 32);
      this.head_eyes.setRotationPoint(0.0F, -1.5F, -6.01F);
      this.head_eyes.addBox(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 0, 40);
      this.head_snout.setRotationPoint(0.0F, -1.5F, -6.3F);
      this.head_snout.addBox(-2.0F, -1.0F, -4.0F, 4.0F, 4.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.22759093F, 0.0F, 0.0F);
      this.head_snout.scaleX = 1.05F;
      AdvancedModelBox head_teeth = new AdvancedModelBox(this, 0, 50);
      head_teeth.setRotationPoint(0.0F, 2.0F, 0.0F);
      head_teeth.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 18, 40);
      this.head_jaw.setRotationPoint(0.0F, 1.0F, -5.5F);
      this.head_jaw.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 2.0F, 4.0F, 0.0F);
      AdvancedModelBox ear_right = new AdvancedModelBox(this, 24, 28);
      ear_right.setRotationPoint(-3.5F, -3.0F, -3.5F);
      ear_right.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(ear_right, -0.31869712F, 0.31869712F, -0.7285004F);
      AdvancedModelBox ear_left = new AdvancedModelBox(this, 24, 28);
      ear_left.mirror = true;
      ear_left.setRotationPoint(3.5F, -3.0F, -3.5F);
      ear_left.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(ear_left, -0.31869712F, -0.31869712F, 0.7285004F);
      this.leg_left_2 = new AdvancedModelBox(this, 106, 18);
      this.leg_left_2.mirror = true;
      this.leg_left_2.setRotationPoint(0.0F, 6.0F, -2.0F);
      this.leg_left_2.addBox(-2.0F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_left_2, 0.22759093F, -0.045553092F, 0.18203785F);
      this.arm_left_1 = new AdvancedModelBox(this, 35, 40);
      this.arm_left_1.mirror = true;
      this.arm_left_1.setRotationPoint(2.0F, -2.0F, -5.0F);
      this.arm_left_1.addBox(0.0F, 0.0F, -3.0F, 5.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.arm_left_1, 0.0F, 0.18203785F, -0.13665928F);
      this.arm_right_1 = new AdvancedModelBox(this, 35, 40);
      this.arm_right_1.setRotationPoint(-2.0F, -2.0F, -5.0F);
      this.arm_right_1.addBox(-5.0F, 0.0F, -3.0F, 5.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.arm_right_1, 0.0F, -0.18203785F, 0.13665928F);
      this.arm_right_2 = new AdvancedModelBox(this, 57, 40);
      this.arm_right_2.setRotationPoint(-1.5F, 8.0F, -0.5F);
      this.arm_right_2.addBox(-3.0F, 0.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.arm_right_2, -0.13665928F, 0.18203785F, -0.13665928F);
      this.arm_right_foot = new AdvancedModelBox(this, 54, 54);
      this.arm_right_foot.setRotationPoint(-0.01F, 7.5F, -0.99F);
      this.arm_right_foot.addBox(-3.0F, 0.0F, -4.0F, 5.0F, 2.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.arm_right_foot, 0.0F, 2.480262E-16F, 0.0F);
      this.leg_right_1 = new AdvancedModelBox(this, 100, 0);
      this.leg_right_1.setRotationPoint(-5.0F, 0.0F, 10.0F);
      this.leg_right_1.addBox(-3.0F, -2.0F, -6.0F, 6.0F, 10.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.leg_right_1, -0.045553092F, -0.045553092F, 0.18203785F);
      this.leg_right_2 = new AdvancedModelBox(this, 106, 18);
      this.leg_right_2.setRotationPoint(0.0F, 6.0F, -2.0F);
      this.leg_right_2.addBox(-3.0F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_right_2, 0.22759093F, 0.045553092F, -0.18203785F);
      this.leg_left_foot = new AdvancedModelBox(this, 100, 30);
      this.leg_left_foot.mirror = true;
      this.leg_left_foot.setRotationPoint(1.0F, 6.0F, 0.0F);
      this.leg_left_foot.addBox(-3.0F, 0.0F, -5.0F, 5.0F, 2.0F, 8.0F, 0.0F);
      this.arm_left_2 = new AdvancedModelBox(this, 57, 40);
      this.arm_left_2.mirror = true;
      this.arm_left_2.setRotationPoint(1.5F, 8.0F, -0.5F);
      this.arm_left_2.addBox(-2.0F, 0.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.arm_left_2, -0.13665928F, -0.18203785F, 0.13665928F);
      this.leg_left_1 = new AdvancedModelBox(this, 100, 0);
      this.leg_left_1.mirror = true;
      this.leg_left_1.setRotationPoint(5.0F, 0.0F, 10.0F);
      this.leg_left_1.addBox(-3.0F, -2.0F, -6.0F, 6.0F, 10.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.leg_left_1, -0.045553092F, 0.045553092F, -0.18203785F);
      this.arm_left_foot = new AdvancedModelBox(this, 54, 54);
      this.arm_left_foot.mirror = true;
      this.arm_left_foot.setRotationPoint(0.01F, 7.5F, -0.99F);
      this.arm_left_foot.addBox(-2.0F, 0.0F, -4.0F, 5.0F, 2.0F, 8.0F, 0.0F);
      this.leg_right_foot = new AdvancedModelBox(this, 100, 30);
      this.leg_right_foot.setRotationPoint(0.0F, 6.0F, 0.0F);
      this.leg_right_foot.addBox(-3.0F, 0.0F, -5.0F, 5.0F, 2.0F, 8.0F, 0.0F);
      this.body_main.addChild(this.body_torso);
      this.body_main.addChild(this.body_buttocks);
      this.body_torso.addChild(this.arm_right_1);
      this.body_torso.addChild(this.arm_left_1);
      this.body_torso.addChild(this.head_face);
      this.body_buttocks.addChild(this.leg_right_1);
      this.body_buttocks.addChild(this.leg_left_1);
      this.body_buttocks.addChild(body_tail);
      this.head_face.addChild(ear_right);
      this.head_face.addChild(ear_left);
      this.head_face.addChild(this.head_snout);
      this.head_face.addChild(this.head_jaw);
      this.head_face.addChild(this.head_eyes);
      this.head_snout.addChild(head_teeth);
      this.leg_left_1.addChild(this.leg_left_2);
      this.arm_right_2.addChild(this.arm_right_foot);
      this.leg_right_1.addChild(this.leg_right_2);
      this.arm_right_1.addChild(this.arm_right_2);
      this.leg_left_2.addChild(this.leg_left_foot);
      this.arm_left_1.addChild(this.arm_left_2);
      this.arm_left_2.addChild(this.arm_left_foot);
      this.leg_right_2.addChild(this.leg_right_foot);
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.body_buttocks,
         this.body_torso,
         this.leg_left_1,
         this.leg_right_1,
         this.leg_left_2,
         this.leg_left_foot,
         this.leg_right_2,
         this.leg_right_foot,
         this.arm_left_1,
         this.head_face,
         this.arm_right_1,
         new AdvancedModelBox[]{this.arm_left_2, this.arm_left_foot, this.head_snout, this.head_jaw, this.head_eyes, this.arm_right_2, this.arm_right_foot}
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntityBear bear = (EntityBear)entityIn;
      this.animator.update(bear);
      this.animator.setAnimation(EntityBear.ATTACK_MAUL);
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, -6.0F, 0.0F);
      this.rotate(this.animator, this.body_main, -36.52F, 0.0F, 0.0F);
      this.animator.move(this.body_torso, 0.0F, -0.5F, 0.0F);
      this.rotate(this.animator, this.body_torso, 10.43F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 33.91F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, 44.35F, -2.61F, 10.43F);
      this.animator.move(this.leg_right_1, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_2, 5.22F, 2.61F, -10.43F);
      this.rotate(this.animator, this.leg_left_1, 44.35F, 2.61F, -10.43F);
      this.animator.move(this.leg_left_1, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_2, 5.22F, -2.61F, 10.43F);
      this.rotate(this.animator, this.arm_right_1, -33.91F, 7.83F, 36.52F);
      this.rotate(this.animator, this.arm_right_2, -20.87F, 5.22F, -33.91F);
      this.animator.move(this.arm_right_foot, 0.0F, 2.0F, 2.0F);
      this.rotate(this.animator, this.arm_right_foot, 100.0F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -20.87F, -7.83F, -36.52F);
      this.rotate(this.animator, this.arm_left_2, -18.26F, -5.22F, 33.91F);
      this.animator.move(this.arm_left_foot, 0.0F, 2.0F, 2.0F);
      this.rotate(this.animator, this.arm_left_foot, 100.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.animator.move(this.body_torso, 0.0F, -0.5F, 0.0F);
      this.animator.move(this.body_main, 0.0F, 4.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 23.48F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, -10.43F, 33.91F);
      this.rotate(this.animator, this.arm_right_2, -18.26F, 5.22F, -33.91F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 10.43F, -33.91F);
      this.rotate(this.animator, this.arm_left_2, -18.26F, -5.22F, 33.91F);
      this.animator.move(this.leg_right_1, 0.0F, -4.0F, 0.0F);
      this.animator.move(this.leg_left_1, 0.0F, -4.0F, 0.0F);
      this.animator.move(this.arm_right_1, 0.0F, -4.0F, 0.0F);
      this.animator.move(this.arm_left_1, 0.0F, -4.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(12);
      this.animator.setAnimation(EntityBear.ATTACK_BITE);
      this.animator.startKeyframe(8);
      this.rotate(this.animator, this.head_face, 0.0F, 0.0F, 30.0F);
      this.rotate(this.animator, this.head_snout, -16.0F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 64.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(8);
      this.rotate(this.animator, this.head_face, 0.0F, 0.0F, -30.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(2);
      this.animator.setAnimation(EntityBear.ATTACK_SWIPE);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.body_torso, 7.83F, -10.43F, 0.0F);
      this.rotate(this.animator, this.head_face, 7.83F, 10.43F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, -20.87F, 26.09F, 26.09F);
      this.rotate(this.animator, this.arm_right_2, -49.57F, 28.7F, -5.22F);
      this.animator.move(this.arm_right_foot, -1.0F, 3.0F, 0.5F);
      this.rotate(this.animator, this.arm_right_foot, 90.0F, 96.52F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(8);
      this.rotate(this.animator, this.body_torso, 7.83F, -28.7F, 0.0F);
      this.rotate(this.animator, this.head_face, 7.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, -52.17F, -2.61F, 10.43F);
      this.rotate(this.animator, this.arm_right_2, -49.57F, 28.7F, -5.22F);
      this.animator.move(this.arm_right_foot, -1.0F, 3.0F, 0.5F);
      this.rotate(this.animator, this.arm_right_foot, 90.0F, 96.52F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityBear.ATTACK_POUND);
      this.animator.startKeyframe(12);
      this.rotate(this.animator, this.body_main, -18.26F, 0.0F, 0.0F);
      this.animator.move(this.body_main, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.body_torso, 7.83F, 5.22F, -7.83F);
      this.rotate(this.animator, this.head_snout, -2.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 54.78F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, 13.04F, -2.61F, 10.43F);
      this.animator.move(this.leg_right_1, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_1, 13.04F, 2.61F, -10.43F);
      this.animator.move(this.leg_left_1, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, -60.0F, 23.47F, 26.09F);
      this.animator.move(this.arm_right_1, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, -46.96F, 10.43F, -7.83F);
      this.rotate(this.animator, this.arm_right_foot, 127.83F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_foot, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -54.78F, -18.26F, -20.87F);
      this.animator.move(this.arm_left_1, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, -46.96F, -10.43F, 7.83F);
      this.rotate(this.animator, this.arm_left_foot, 127.83F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_foot, 0.0F, 2.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(8);
      this.animator.move(this.body_main, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.body_torso, 18.26F, 5.22F, 2.61F);
      this.rotate(this.animator, this.head_face, -28.7F, 0.0F, 5.22F);
      this.rotate(this.animator, this.head_snout, -2.61F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 54.78F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -2.61F, -2.61F, 10.43F);
      this.rotate(this.animator, this.leg_left_1, -2.61F, 2.61F, -10.43F);
      this.rotate(this.animator, this.arm_right_1, -20.87F, -5.21F, 18.26F);
      this.rotate(this.animator, this.arm_right_2, -33.91F, 10.43F, -7.83F);
      this.rotate(this.animator, this.arm_right_foot, 39.13F, 15.65F, 0.0F);
      this.animator.move(this.arm_right_foot, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, -20.87F, 5.21F, -18.26F);
      this.rotate(this.animator, this.arm_left_2, -33.91F, -10.43F, 7.83F);
      this.rotate(this.animator, this.arm_left_foot, 39.13F, -15.65F, 0.0F);
      this.animator.move(this.arm_left_foot, 0.0F, -2.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntityBear.ANIMATION_ROAR);
      this.animator.startKeyframe(15);
      this.rotate(this.animator, this.head_face, 0.0F, 0.0F, 30.0F);
      this.rotate(this.animator, this.head_snout, -16.0F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_jaw, 64.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(15);
      this.rotate(this.animator, this.head_face, 0.0F, 0.0F, -30.0F);
      this.rotate(this.animator, this.head_jaw, 64.0F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_snout, -16.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(15);
      this.rotate(this.animator, this.head_face, 0.0F, 0.0F, 30.0F);
      this.rotate(this.animator, this.head_jaw, 64.0F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_snout, -16.0F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(5);
      this.animator.setAnimation(EntityBear.IDLE_STAND);
      this.animator.startKeyframe(24);
      this.animator.move(this.body_main, 0.0F, -6.0F, 0.0F);
      this.rotate(this.animator, this.body_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 33.91F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, 44.35F, -2.61F, 10.43F);
      this.animator.move(this.leg_right_1, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_2, 5.22F, 2.61F, -10.43F);
      this.rotate(this.animator, this.leg_left_1, 44.35F, 2.61F, -10.43F);
      this.animator.move(this.leg_left_1, 0.0F, -2.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_2, 5.22F, -2.61F, 10.43F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(24);
      this.animator.move(this.body_main, 0.0F, -6.0F, 6.0F);
      this.animator.move(this.leg_right_1, 0.0F, -2.0F, -5.2F);
      this.animator.move(this.leg_left_1, 0.0F, -2.0F, -5.2F);
      this.rotate(this.animator, this.body_main, -86.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 67.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, 83.48F, -10.43F, 10.43F);
      this.rotate(this.animator, this.leg_left_1, 83.48F, 10.43F, -10.43F);
      this.rotate(this.animator, this.arm_right_1, 62.61F, -10.43F, 7.83F);
      this.rotate(this.animator, this.arm_right_2, -70.43F, 10.43F, -7.83F);
      this.animator.move(this.arm_right_foot, 0.0F, 2.0F, 2.0F);
      this.rotate(this.animator, this.arm_right_foot, 146.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, 62.61F, 10.43F, -7.83F);
      this.rotate(this.animator, this.arm_left_2, -70.43F, -10.43F, 7.83F);
      this.animator.move(this.arm_left_foot, 0.0F, 2.0F, 2.0F);
      this.rotate(this.animator, this.arm_left_foot, 146.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(80);
      this.animator.move(this.body_main, 0.0F, -6.0F, 7.0F);
      this.animator.move(this.leg_right_1, 0.0F, -2.0F, -5.2F);
      this.animator.move(this.leg_left_1, 0.0F, -2.0F, -5.2F);
      this.rotate(this.animator, this.body_main, -86.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, 67.83F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, 83.48F, -10.43F, 10.43F);
      this.rotate(this.animator, this.leg_left_1, 83.48F, 10.43F, -10.43F);
      this.rotate(this.animator, this.arm_right_1, 62.61F, -10.43F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, -70.43F, 10.43F, -7.83F);
      this.animator.move(this.arm_right_foot, 0.0F, 2.0F, 2.0F);
      this.rotate(this.animator, this.arm_right_foot, 146.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, 62.61F, 10.43F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, -70.43F, -10.43F, 7.83F);
      this.animator.move(this.arm_left_foot, 0.0F, 2.0F, 2.0F);
      this.rotate(this.animator, this.arm_left_foot, 146.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(20);
      this.animator.setAnimation(EntityBear.IDLE_TALK);
      this.animator.startKeyframe(10);
      this.rotate(this.animator, this.head_jaw, 26.09F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face, -26.09F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(10);
      this.animator.setAnimation(EntityBear.ANIMATION_EAT);
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, 3.2F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -15.65F, -2.61F, 10.43F);
      this.rotate(this.animator, this.leg_left_1, -15.65F, 2.61F, -10.43F);
      this.animator.move(this.arm_right_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, -10.43F, 57.39F);
      this.animator.move(this.arm_right_2, -0.5F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, -28.7F, 7.83F, -57.39F);
      this.animator.move(this.arm_left_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 10.43F, -57.39F);
      this.animator.move(this.arm_left_2, 0.5F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, -28.7F, -7.83F, 57.39F);
      this.rotate(this.animator, this.head_face, 23.48F, 23.48F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(16);
      this.animator.move(this.body_main, 0.0F, 3.2F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -15.65F, -2.61F, 10.43F);
      this.rotate(this.animator, this.leg_left_1, -15.65F, 2.61F, -10.43F);
      this.animator.move(this.arm_right_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, -10.43F, 57.39F);
      this.animator.move(this.arm_right_2, -0.5F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, -28.7F, 7.83F, -57.39F);
      this.animator.move(this.arm_left_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 10.43F, -57.39F);
      this.animator.move(this.arm_left_2, 0.5F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, -28.7F, -7.83F, 57.39F);
      this.rotate(this.animator, this.head_face, 23.48F, -23.48F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(16);
      this.animator.move(this.body_main, 0.0F, 3.2F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_1, -15.65F, -2.61F, 10.43F);
      this.rotate(this.animator, this.leg_left_1, -15.65F, 2.61F, -10.43F);
      this.animator.move(this.arm_right_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_1, 0.0F, -10.43F, 57.39F);
      this.animator.move(this.arm_right_2, -0.5F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_2, -28.7F, 7.83F, -57.39F);
      this.animator.move(this.arm_left_1, 0.0F, 1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_1, 0.0F, 10.43F, -57.39F);
      this.animator.move(this.arm_left_2, 0.5F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_2, -28.7F, -7.83F, 57.39F);
      this.rotate(this.animator, this.head_face, 10.43F, 23.48F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(20);
   }

   public void setupAnim(EntityBear bear, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(bear);
      float globalSpeed = 2.5F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.3F, limbSwingAmount * 2.0F);
      limbSwing *= 0.5F;
      if (bear.getAnimation() == EntityBear.ATTACK_MAUL || bear.getAnimation() == EntityBear.IDLE_STAND) {
         limbSwingAmount *= 0.5F;
      }

      float shortSnout = bear.hasShortSnout() ? 0.7F : 1.0F;
      this.head_snout.scaleZ = shortSnout;
      this.head_jaw.scaleZ = shortSnout;
      float torsoScale = bear.hasHump() ? 1.2F : 1.0F;
      this.body_torso.scaleY = torsoScale;
      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_buttocks
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F);
      this.body_torso
         .setScale(
            (float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.06F), (float)((double)torsoScale + Math.sin((double)(ageInTicks / 16.0F)) * 0.06F), 1.0F
         );
      this.bob(this.body_main, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_1, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!bear.shouldRenderEyes()) {
         this.head_eyes.setRotationPoint(0.0F, -1.5F, -5.01F);
      }

      this.head_eyes.setScaleY(Math.min(bear.getHealth() / bear.getMaxHealth() + 0.4F, 1.0F));
      if (!bear.isSleeping()) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_face});
      }

      if (bear.isInWater() && !bear.onGround()) {
         limbSwing = ageInTicks / 3.0F;
         limbSwingAmount = 0.5F;
         this.setRotateAngle(this.head_face, -0.22759093F, 0.0F, 0.0F);
         float pitch = Mth.clamp(bear.getXRot() - 10.0F, -25.0F, 25.0F);
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (bear.canMove()) {
         if (!(bear.getCurrentSpeed() > 0.08F) && !bear.isAngry()) {
            this.walk(this.body_main, 0.5F * globalSpeed, 0.1F * globalDegree, true, -0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.body_torso, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.body_buttocks, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.head_face, 0.5F * globalSpeed, 0.2F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_1, 0.5F * globalSpeed, globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_2, 0.5F * globalSpeed, 0.6F * globalDegree, false, 1.0F, -0.8F, limbSwing, limbSwingAmount * 1.2F);
            this.walk(this.arm_right_foot, 0.5F * globalSpeed, 0.8F * globalDegree, false, -3.0F, 0.75F, limbSwing, limbSwingAmount);
            this.flap(this.arm_right_1, 0.5F * globalSpeed, 0.2F * globalDegree, false, 2.2F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_1, 0.5F * globalSpeed, globalDegree, false, 3.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_2, 0.5F * globalSpeed, 0.6F * globalDegree, false, 4.0F, -0.8F, limbSwing, limbSwingAmount * 1.2F);
            this.walk(this.arm_left_foot, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.0F, 0.75F, limbSwing, limbSwingAmount);
            this.flap(this.arm_left_1, 0.5F * globalSpeed, 0.2F * globalDegree, false, 5.2F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_1, 0.5F * globalSpeed, globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_2, 0.5F * globalSpeed, 0.8F * globalDegree, false, -0.5F, 0.5F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_foot, 0.5F * globalSpeed, 0.8F * globalDegree, false, -2.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_1, 0.5F * globalSpeed, globalDegree, false, 4.0F, 0.0F, limbSwing, limbSwingAmount);
         } else {
            this.walk(this.body_main, 0.5F * globalSpeed, 0.6F * globalDegree, true, -0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.body_torso, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.body_buttocks, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.flap(this.head_face, 0.5F * globalSpeed, 0.2F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_1, 0.5F * globalSpeed, 1.4F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_2, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.0F, -0.8F, limbSwing, limbSwingAmount * 1.2F);
            this.walk(this.arm_right_foot, 0.5F * globalSpeed, 1.4F * globalDegree, false, -3.0F, 0.75F, limbSwing, limbSwingAmount);
            this.flap(this.arm_right_1, 0.5F * globalSpeed, 0.2F * globalDegree, false, 2.3F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_1, 0.5F * globalSpeed, 1.4F * globalDegree, false, 0.3F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_2, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.3F, -0.8F, limbSwing, limbSwingAmount * 1.2F);
            this.walk(this.arm_left_foot, 0.5F * globalSpeed, 1.4F * globalDegree, false, -3.3F, 0.75F, limbSwing, limbSwingAmount);
            this.flap(this.arm_left_1, 0.5F * globalSpeed, 0.2F * globalDegree, false, 2.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_1, 0.5F * globalSpeed, 0.8F * globalDegree, false, 4.3F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_2, 0.5F * globalSpeed, 0.8F * globalDegree, false, 2.8F, 0.5F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_foot, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1.3F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_1, 0.5F * globalSpeed, 0.8F * globalDegree, false, 4.0F, 0.0F, limbSwing, limbSwingAmount);
         }

         this.walk(this.leg_right_2, 0.5F * globalSpeed, 0.8F * globalDegree, false, 2.5F, 0.5F, limbSwing, limbSwingAmount);
         this.walk(this.leg_right_foot, 0.5F * globalSpeed, 0.6F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
      }

      if (bear.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)bear.sitProgress, 0.0F, 11.7F, 4.0F, 40.0F);
         this.progressPosition(this.body_torso, (float)bear.sitProgress, 0.0F, -2.5F, -1.0F, 40.0F);
         this.progressPosition(this.body_buttocks, (float)bear.sitProgress, 0.0F, -1.5F, 1.0F, 40.0F);
         this.progressPosition(this.head_face, (float)bear.sitProgress, 0.0F, -1.0F, -9.0F, 40.0F);
         this.progressPosition(this.arm_right_1, (float)bear.sitProgress, -2.0F, -1.0F, -5.0F, 40.0F);
         this.progressPosition(this.arm_left_1, (float)bear.sitProgress, 2.0F, -1.0F, -5.0F, 40.0F);
         this.progressRotation(this.body_main, (float)bear.sitProgress, (float)Math.toRadians(-60.0), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.body_torso, (float)bear.sitProgress, (float)Math.toRadians(46.96F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.body_buttocks, (float)bear.sitProgress, (float)Math.toRadians(-26.08F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(this.head_face, (float)bear.sitProgress, (float)Math.toRadians(31.31F), 0.0F, 0.0F, 40.0F);
         this.progressRotation(
            this.leg_right_1, (float)bear.sitProgress, (float)Math.toRadians(-5.22F), (float)Math.toRadians(-5.22F), (float)Math.toRadians(33.9F), 40.0F
         );
         this.progressRotation(
            this.leg_left_1, (float)bear.sitProgress, (float)Math.toRadians(-5.22F), (float)Math.toRadians(5.22F), (float)Math.toRadians(-33.9F), 40.0F
         );
         this.progressRotation(
            this.arm_right_1, (float)bear.sitProgress, (float)Math.toRadians(13.04F), (float)Math.toRadians(-10.43F), (float)Math.toRadians(7.83F), 40.0F
         );
         this.progressRotation(
            this.arm_left_1, (float)bear.sitProgress, (float)Math.toRadians(13.04F), (float)Math.toRadians(10.43F), (float)Math.toRadians(-7.83F), 40.0F
         );
      } else if (bear.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)bear.sleepProgress, -2.0F, 18.5F, 0.0F, 40.0F);
         this.progressPosition(this.leg_right_1, (float)bear.sleepProgress, -5.0F, 0.0F, 10.0F, 40.0F);
         this.progressPosition(this.leg_left_1, (float)bear.sleepProgress, 5.0F, 0.0F, 10.0F, 40.0F);
         this.progressRotation(this.body_main, (float)bear.sleepProgress, 0.0F, (float) (Math.PI / 2), 0.0F, 40.0F);
         this.progressRotation(this.body_buttocks, (float)bear.sleepProgress, 0.0F, 0.13665928F, -0.13665928F, 40.0F);
         this.progressRotation(this.body_torso, (float)bear.sleepProgress, 0.13665928F, -0.4098033F, 0.0F, 40.0F);
         this.progressRotation(
            this.head_face, (float)bear.sleepProgress, (float)Math.toRadians(10.43F), (float)Math.toRadians(7.83F), (float)Math.toRadians(-80.87F), 40.0F
         );
         this.progressRotation(
            this.arm_right_1, (float)bear.sleepProgress, (float)Math.toRadians(60.0), (float)Math.toRadians(78.26F), (float)Math.toRadians(7.83F), 40.0F
         );
         this.progressRotation(
            this.arm_right_2, (float)bear.sleepProgress, (float)Math.toRadians(80.87F), (float)Math.toRadians(-39.13F), (float)Math.toRadians(-78.26F), 40.0F
         );
         this.progressRotation(this.arm_left_1, (float)bear.sleepProgress, 0.0F, 0.0F, (float)Math.toRadians(-52.17F), 40.0F);
         this.progressRotation(
            this.arm_left_2, (float)bear.sleepProgress, (float)Math.toRadians(-93.91F), (float)Math.toRadians(10.43F), (float)Math.toRadians(101.74F), 40.0F
         );
         this.progressRotation(this.arm_left_foot, (float)bear.sleepProgress, (float)Math.toRadians(114.78F), 0.0F, 0.0F, 40.0F);
         this.progressPosition(this.arm_left_foot, (float)bear.sleepProgress, 0.01F, 7.5F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_1, (float)bear.sleepProgress, -0.4098033F, 0.045553092F, -1.1838568F, 40.0F);
         this.progressRotation(this.leg_right_2, (float)bear.sleepProgress, 0.22759093F, 0.045553092F, -0.18203785F, 40.0F);
         this.progressRotation(this.leg_left_1, (float)bear.sleepProgress, 0.22759093F, 0.045553092F, -1.0016445F, 40.0F);
         this.progressRotation(this.leg_left_2, (float)bear.sleepProgress, 2.276433F, -0.3642502F, -0.4553564F, 40.0F);
      }
   }
}

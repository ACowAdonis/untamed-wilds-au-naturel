package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.relict.EntitySpitter;

public class MonsterSpitter extends AdvancedEntityModel<EntitySpitter> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox body_abdomen;
   public AdvancedModelBox arm_right_upper;
   public AdvancedModelBox arm_left_upper;
   public AdvancedModelBox head_neck;
   public AdvancedModelBox back_sail;
   public AdvancedModelBox leg_right_upper;
   public AdvancedModelBox leg_left_upper;
   public AdvancedModelBox leg_right_lower;
   public AdvancedModelBox arm_right_claw;
   public AdvancedModelBox leg_left_lower;
   public AdvancedModelBox arm_left_claw;
   public AdvancedModelBox arm_right_lower;
   public AdvancedModelBox arm_right_claw_1;
   public AdvancedModelBox arm_left_lower;
   public AdvancedModelBox arm_left_claw_1;
   public AdvancedModelBox head_snout;
   public AdvancedModelBox head_tube;
   private final ModelAnimator animator;

   public MonsterSpitter() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.head_tube = new AdvancedModelBox(this, 0, 47);
      this.head_tube.setRotationPoint(0.0F, -1.5F, -4.0F);
      this.head_tube.addBox(-1.5F, -1.5F, -8.0F, 3.0F, 3.0F, 8.0F, 0.0F);
      this.body_abdomen = new AdvancedModelBox(this, 40, 0);
      this.body_abdomen.setRotationPoint(0.0F, -1.0F, 6.0F);
      this.body_abdomen.addBox(-3.5F, -4.0F, 0.0F, 7.0F, 6.0F, 10.0F, 0.0F);
      this.leg_left_lower = new AdvancedModelBox(this, 58, 46);
      this.leg_left_lower.mirror = true;
      this.leg_left_lower.setRotationPoint(1.0F, 4.0F, 3.0F);
      this.leg_left_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_left_lower, -0.13665928F, 0.0F, 0.13665928F);
      this.arm_right_lower = new AdvancedModelBox(this, 42, 33);
      this.arm_right_lower.setRotationPoint(-1.0F, 7.0F, 0.5F);
      this.arm_right_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_right_lower, -0.5009095F, 0.0F, -0.091106184F);
      this.leg_right_upper = new AdvancedModelBox(this, 38, 46);
      this.leg_right_upper.setRotationPoint(-3.0F, -0.5F, 6.0F);
      this.leg_right_upper.addBox(-3.0F, -2.0F, -3.0F, 4.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_right_upper, -0.27314404F, 0.0F, 0.18203785F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 11.5F, -4.5F);
      this.body_main.addBox(-3.5F, -5.0F, -6.0F, 7.0F, 8.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.body_main, -0.091106184F, 0.0F, 0.0F);
      this.arm_left_upper = new AdvancedModelBox(this, 42, 20);
      this.arm_left_upper.mirror = true;
      this.arm_left_upper.setRotationPoint(2.5F, -2.5F, -0.5F);
      this.arm_left_upper.addBox(-1.0F, 0.0F, -2.5F, 4.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.arm_left_upper, 0.27314404F, -0.0F, -0.091106184F);
      this.head_neck = new AdvancedModelBox(this, 0, 22);
      this.head_neck.setRotationPoint(0.0F, -2.0F, -4.0F);
      this.head_neck.addBox(-2.0F, -2.5F, -8.0F, 4.0F, 5.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.head_neck, -1.7301449F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 0, 36);
      this.head_snout.setRotationPoint(0.0F, 0.2F, -7.0F);
      this.head_snout.addBox(-2.5F, -3.0F, -4.0F, 5.0F, 4.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 1.9577358F, 0.0F, 0.0F);
      this.arm_left_claw = new AdvancedModelBox(this, 54, 40);
      this.arm_left_claw.setRotationPoint(0.0F, 6.8F, -2.0F);
      this.arm_left_claw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_claw, 1.6845918F, 0.0F, 0.0F);
      this.arm_right_claw = new AdvancedModelBox(this, 54, 40);
      this.arm_right_claw.mirror = true;
      this.arm_right_claw.setRotationPoint(0.0F, 6.8F, -2.0F);
      this.arm_right_claw.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_claw, 1.6845918F, 0.0F, 0.0F);
      this.leg_left_upper = new AdvancedModelBox(this, 38, 46);
      this.leg_left_upper.mirror = true;
      this.leg_left_upper.setRotationPoint(3.0F, -0.5F, 6.0F);
      this.leg_left_upper.addBox(-1.0F, -2.0F, -3.0F, 4.0F, 8.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leg_left_upper, -0.27314404F, -0.0F, -0.18203785F);
      this.arm_left_lower = new AdvancedModelBox(this, 42, 33);
      this.arm_left_lower.mirror = true;
      this.arm_left_lower.setRotationPoint(1.0F, 7.0F, 0.5F);
      this.arm_left_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.arm_left_lower, -0.5009095F, 0.0F, 0.091106184F);
      this.back_sail = new AdvancedModelBox(this, 78, 40);
      this.back_sail.setRotationPoint(0.0F, -4.0F, 11.0F);
      this.back_sail.addBox(-2.5F, -10.0F, -5.0F, 5.0F, 12.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.back_sail, -0.31869712F, 0.0F, 0.0F);
      this.arm_right_claw_1 = new AdvancedModelBox(this, 54, 34);
      this.arm_right_claw_1.setRotationPoint(0.0F, 6.0F, -2.5F);
      this.arm_right_claw_1.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_right_claw_1, 1.548107F, 0.0F, 0.0F);
      this.arm_left_claw_1 = new AdvancedModelBox(this, 54, 34);
      this.arm_left_claw_1.mirror = true;
      this.arm_left_claw_1.setRotationPoint(0.0F, 6.0F, -2.5F);
      this.arm_left_claw_1.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F);
      this.setRotateAngle(this.arm_left_claw_1, 1.548107F, 0.0F, 0.0F);
      this.arm_right_upper = new AdvancedModelBox(this, 42, 20);
      this.arm_right_upper.setRotationPoint(-2.5F, -2.5F, -0.5F);
      this.arm_right_upper.addBox(-3.0F, 0.0F, -2.5F, 4.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.arm_right_upper, 0.27314404F, 0.0F, 0.091106184F);
      this.leg_right_lower = new AdvancedModelBox(this, 58, 46);
      this.leg_right_lower.setRotationPoint(-1.0F, 4.0F, 3.0F);
      this.leg_right_lower.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.leg_right_lower, -0.13665928F, 0.0F, -0.13665928F);
      this.head_snout.addChild(this.head_tube);
      this.body_main.addChild(this.body_abdomen);
      this.leg_left_upper.addChild(this.leg_left_lower);
      this.arm_right_upper.addChild(this.arm_right_lower);
      this.body_abdomen.addChild(this.leg_right_upper);
      this.body_main.addChild(this.arm_left_upper);
      this.body_main.addChild(this.head_neck);
      this.head_neck.addChild(this.head_snout);
      this.leg_left_lower.addChild(this.arm_left_claw);
      this.leg_right_lower.addChild(this.arm_right_claw);
      this.body_abdomen.addChild(this.leg_left_upper);
      this.arm_left_upper.addChild(this.arm_left_lower);
      this.body_main.addChild(this.back_sail);
      this.arm_right_lower.addChild(this.arm_right_claw_1);
      this.arm_left_lower.addChild(this.arm_left_claw_1);
      this.body_main.addChild(this.arm_right_upper);
      this.leg_right_upper.addChild(this.leg_right_lower);
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
         this.arm_right_upper,
         this.arm_left_upper,
         this.head_neck,
         this.back_sail,
         this.leg_right_upper,
         this.leg_left_upper,
         this.leg_right_lower,
         this.arm_right_claw,
         this.leg_left_lower,
         this.arm_left_claw,
         new AdvancedModelBox[]{this.arm_right_lower, this.arm_right_claw_1, this.arm_left_lower, this.arm_left_claw_1, this.head_snout, this.head_tube}
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      EntitySpitter spitter = (EntitySpitter)entityIn;
      this.animator.update(spitter);
      this.animator.setAnimation(EntitySpitter.ATTACK_MAUL_RIGHT);
      this.animator.startKeyframe(8);
      this.animator.move(this.body_main, 0.0F, -0.7F, 0.0F);
      this.rotate(this.animator, this.body_main, -5.22F, -10.43F, 7.83F);
      this.rotate(this.animator, this.body_abdomen, -5.22F, 10.43F, -7.83F);
      this.rotate(this.animator, this.head_snout, 31.3F, -13.04F, 23.48F);
      this.animator.move(this.arm_right_upper, -1.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -78.26F, 33.91F, -10.43F);
      this.rotate(this.animator, this.arm_right_lower, -62.61F, -5.22F, -5.22F);
      this.animator.move(this.arm_left_upper, 0.0F, 0.9F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, 23.48F, 10.43F, -10.43F);
      this.rotate(this.animator, this.leg_right_upper, -2.61F, 0.0F, 2.61F);
      this.rotate(this.animator, this.leg_left_upper, -2.61F, 0.0F, -2.61F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, 1.9F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, -10.43F, 7.83F);
      this.rotate(this.animator, this.body_abdomen, -23.48F, 10.43F, -7.83F);
      this.rotate(this.animator, this.head_snout, 39.13F, 7.83F, 0.0F);
      this.rotate(this.animator, this.head_neck, -54.78F, 0.0F, 0.0F);
      this.animator.move(this.arm_right_upper, 0.0F, 0.0F, -2.0F);
      this.rotate(this.animator, this.arm_right_upper, -73.04F, 13.04F, -10.43F);
      this.rotate(this.animator, this.arm_right_lower, -13.04F, -5.22F, -5.22F);
      this.animator.move(this.arm_left_upper, 0.0F, -1.2F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, 15.65F, 10.43F, -10.43F);
      this.rotate(this.animator, this.arm_left_lower, -60.0F, 0.0F, 5.22F);
      this.rotate(this.animator, this.leg_right_upper, -2.61F, 0.0F, 2.61F);
      this.rotate(this.animator, this.leg_left_upper, -2.61F, 0.0F, -2.61F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntitySpitter.ATTACK_MAUL_LEFT);
      this.animator.startKeyframe(8);
      this.animator.move(this.body_main, 0.0F, -0.7F, 0.0F);
      this.rotate(this.animator, this.body_main, -5.22F, 10.43F, -7.83F);
      this.rotate(this.animator, this.body_abdomen, -5.22F, -10.43F, 7.83F);
      this.rotate(this.animator, this.head_snout, 31.3F, 13.04F, -23.48F);
      this.animator.move(this.arm_left_upper, -1.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -78.26F, -33.91F, 10.43F);
      this.rotate(this.animator, this.arm_left_lower, -62.61F, 5.22F, 5.22F);
      this.animator.move(this.arm_right_upper, 0.0F, 0.9F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, 23.48F, -10.43F, 10.43F);
      this.rotate(this.animator, this.leg_left_upper, -2.61F, 0.0F, -2.61F);
      this.rotate(this.animator, this.leg_right_upper, -2.61F, 0.0F, 2.61F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(6);
      this.animator.move(this.body_main, 0.0F, 1.9F, 0.0F);
      this.rotate(this.animator, this.body_main, 13.04F, 10.43F, -7.83F);
      this.rotate(this.animator, this.body_abdomen, -23.48F, -10.43F, 7.83F);
      this.rotate(this.animator, this.head_snout, 39.13F, -7.83F, 0.0F);
      this.rotate(this.animator, this.head_neck, -54.78F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, 0.0F, -2.0F);
      this.rotate(this.animator, this.arm_left_upper, -73.04F, -13.04F, 10.43F);
      this.rotate(this.animator, this.arm_left_lower, -13.04F, 5.22F, 5.22F);
      this.animator.move(this.arm_right_upper, 0.0F, -1.2F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, 15.65F, -10.43F, 10.43F);
      this.rotate(this.animator, this.arm_right_lower, -60.0F, 0.0F, -5.22F);
      this.rotate(this.animator, this.leg_left_upper, -2.61F, 0.0F, -2.61F);
      this.rotate(this.animator, this.leg_right_upper, -2.61F, 0.0F, 2.61F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(8);
      this.animator.setAnimation(EntitySpitter.ATTACK_SPIT);
      this.animator.startKeyframe(6);
      this.rotate(this.animator, this.head_neck, -46.96F, 0.0F, 0.0F);
      this.animator.move(this.head_snout, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.head_snout, 15.65F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(4);
      this.rotate(this.animator, this.head_neck, 46.96F, 0.0F, 0.0F);
      this.animator.move(this.head_snout, 0.0F, 2.0F, 0.0F);
      this.rotate(this.animator, this.head_snout, -28.7F, 0.0F, 0.0F);
      this.animator.move(this.head_snout, 0.0F, 0.0F, 1.5F);
      this.rotate(this.animator, this.head_snout, -28.7F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_snout, 36.52F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(6);
      this.animator.setAnimation(EntitySpitter.IDLE_WATCH);
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, -6.0F, 0.0F);
      this.rotate(this.animator, this.body_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 41.74F, 0.0F, 0.0F);
      this.animator.move(this.head_snout, 0.0F, -1.0F, -2.0F);
      this.rotate(this.animator, this.head_snout, -20.0F, 0.0F, 46.96F);
      this.rotate(this.animator, this.arm_left_upper, 41.74F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_right_upper, 41.74F, 0.0F, 5.22F);
      this.rotate(this.animator, this.leg_left_upper, 41.74F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, 41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, -6.0F, 0.0F);
      this.rotate(this.animator, this.body_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 41.74F, 0.0F, 0.0F);
      this.animator.move(this.head_snout, 0.0F, -1.0F, -2.0F);
      this.rotate(this.animator, this.head_snout, -20.0F, 0.0F, -54.78F);
      this.rotate(this.animator, this.arm_left_upper, 41.74F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_right_upper, 41.74F, 0.0F, 5.22F);
      this.rotate(this.animator, this.leg_left_upper, 41.74F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, 41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, -6.0F, 0.0F);
      this.rotate(this.animator, this.body_main, -36.52F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 41.74F, 0.0F, 0.0F);
      this.animator.move(this.head_snout, 0.0F, -1.0F, -2.0F);
      this.rotate(this.animator, this.head_snout, -20.0F, 0.0F, 39.13F);
      this.rotate(this.animator, this.arm_left_upper, 41.74F, 0.0F, -5.22F);
      this.rotate(this.animator, this.arm_right_upper, 41.74F, 0.0F, 5.22F);
      this.rotate(this.animator, this.leg_left_upper, 41.74F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, 41.74F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(20);
      this.animator.setAnimation(EntitySpitter.IDLE_TALK);
      this.animator.startKeyframe(20);
      this.rotate(this.animator, this.head_neck, 41.74F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_snout, -60.0F, 0.0F, 10.43F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(20);
      this.animator.setAnimation(EntitySpitter.ANIMATION_EAT);
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, 2.0F, 1.0F);
      this.rotate(this.animator, this.body_main, 13.05F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 65.22F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_left_upper, -20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, -20.87F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -13.04F, 0.0F, -10.43F);
      this.animator.move(this.arm_right_upper, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -13.04F, 0.0F, 10.43F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, 2.0F, 1.0F);
      this.rotate(this.animator, this.body_main, 13.05F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 65.22F, 20.87F, 10.43F);
      this.rotate(this.animator, this.leg_left_upper, -20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, -20.87F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -13.04F, 0.0F, -10.43F);
      this.animator.move(this.arm_right_upper, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -13.04F, 0.0F, 10.43F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(20);
      this.animator.move(this.body_main, 0.0F, 2.0F, 1.0F);
      this.rotate(this.animator, this.body_main, 13.05F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_neck, 65.22F, -20.87F, -10.43F);
      this.rotate(this.animator, this.leg_left_upper, -20.87F, 0.0F, 0.0F);
      this.rotate(this.animator, this.leg_right_upper, -20.87F, 0.0F, 0.0F);
      this.animator.move(this.arm_left_upper, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_left_upper, -13.04F, 0.0F, -10.43F);
      this.animator.move(this.arm_right_upper, 0.0F, -1.0F, 0.0F);
      this.rotate(this.animator, this.arm_right_upper, -13.04F, 0.0F, 10.43F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(20);
   }

   public void setupAnim(EntitySpitter spitter, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(spitter);
      float globalSpeed = 2.4F;
      float globalDegree = 1.0F;
      limbSwingAmount = Math.min(0.6F, limbSwingAmount * 2.0F);
      limbSwing *= 0.5F;
      double scaleX = Math.sin((double)(ageInTicks / 20.0F));
      double scaleY = Math.sin((double)(ageInTicks / 16.0F));
      this.body_main.setScale((float)(1.0 + scaleX * 0.08F), (float)(1.0 + scaleY * 0.06F), 1.0F);
      this.body_abdomen.setScale((float)(1.0 + scaleX * 0.06F), (float)(1.0 + scaleY * 0.06F), 1.0F);
      this.bob(this.body_main, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.head_neck, 0.4F * globalSpeed, 0.03F, false, ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_right_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.arm_left_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_right_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      this.bob(this.leg_left_upper, 0.4F * globalSpeed, 0.03F, false, -ageInTicks / 20.0F, 2.0F);
      if (!spitter.isSleeping() && spitter.getAnimation() != EntitySpitter.IDLE_WATCH) {
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_neck});
         this.faceTarget(netHeadYaw, headPitch, 2.0F, new AdvancedModelBox[]{this.head_snout});
      }

      if (spitter.isInWater() && !spitter.onGround()) {
         limbSwing = ageInTicks / 3.0F;
         limbSwingAmount = 0.5F;
         this.body_main.rotationPointY += 4.0F;
         this.setRotateAngle(this.head_neck, -0.18203785F, 0.0F, 0.0F);
         float pitch = Mth.clamp(spitter.getXRot() - 10.0F, -25.0F, 25.0F);
         this.setRotateAngle(this.body_main, (float)((double)pitch * Math.PI / 180.0), 0.0F, 0.0F);
      }

      if (spitter.canMove() && spitter.getAnimation() != EntitySpitter.IDLE_WATCH) {
         if (!(spitter.getCurrentSpeed() > 0.1F) && !spitter.isAngry()) {
            this.bob(this.arm_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_upper, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_upper, 0.5F * globalSpeed, globalDegree, true, 2.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.6F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_upper, 0.5F * globalSpeed, globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_upper, 0.5F * globalSpeed, globalDegree, true, 3.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 3.6F, 0.2F, limbSwing, limbSwingAmount);
         } else {
            this.bob(this.body_main, 0.5F * globalSpeed, 0.5F, false, limbSwing, limbSwingAmount);
            this.walk(this.body_main, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.head_neck, 0.5F * globalSpeed, -0.5F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.body_abdomen, 0.5F * globalSpeed, 0.3F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.bob(this.arm_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_upper, 0.5F * globalSpeed, globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.2F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.arm_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_upper, 0.5F * globalSpeed, globalDegree, true, 0.6F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.arm_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.8F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_right_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_upper, 0.5F * globalSpeed, globalDegree, true, 1.4F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_right_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.6F, 0.2F, limbSwing, limbSwingAmount);
            this.bob(this.leg_left_upper, 0.5F * globalSpeed, 0.8F, false, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_upper, 0.5F * globalSpeed, globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
            this.walk(this.leg_left_lower, 0.5F * globalSpeed, 0.6F * globalDegree, true, 2.2F, 0.2F, limbSwing, limbSwingAmount);
         }
      }

      if (spitter.sitProgress > 0) {
         this.progressPosition(this.body_main, (float)spitter.sitProgress, 0.0F, 19.5F, 1.0F, 40.0F);
         this.progressPosition(this.leg_left_upper, (float)spitter.sitProgress, 2.0F, -2.0F, 6.0F, 40.0F);
         this.progressPosition(this.leg_right_upper, (float)spitter.sitProgress, -2.0F, -2.0F, 6.0F, 40.0F);
         this.progressRotation(this.leg_left_upper, (float)spitter.sitProgress, -0.27314404F, -0.0F, -0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)spitter.sitProgress, -1.2292354F, -0.22759093F, 0.045553092F, 40.0F);
         this.progressRotation(this.arm_left_upper, (float)spitter.sitProgress, 0.27314404F, -6.200655E-17F, -0.24361071F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)spitter.sitProgress, -1.8668041F, 0.0F, 0.10803588F, 40.0F);
         this.progressRotation(this.leg_right_upper, (float)spitter.sitProgress, -0.27314404F, -0.0F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_right_lower, (float)spitter.sitProgress, -1.2292354F, 0.22759093F, -0.045553092F, 40.0F);
         this.progressRotation(this.arm_right_upper, (float)spitter.sitProgress, 0.27314404F, 6.200655E-17F, 0.24361071F, 40.0F);
         this.progressRotation(this.arm_right_lower, (float)spitter.sitProgress, -1.8668041F, 0.0F, -0.10803588F, 40.0F);
      }

      if (spitter.sleepProgress > 0) {
         this.progressPosition(this.body_main, (float)spitter.sleepProgress, -4.0F, 20.0F, -4.0F, 40.0F);
         this.progressRotation(this.body_main, (float)spitter.sleepProgress, 0.0F, 0.0F, -1.5025539F, 40.0F);
         this.progressRotation(this.body_abdomen, (float)spitter.sleepProgress, -0.4553564F, -0.0F, 0.0F, 40.0F);
         this.progressRotation(this.leg_right_upper, (float)spitter.sleepProgress, -0.5009095F, -0.0F, 0.045553092F, 40.0F);
         this.progressRotation(this.leg_left_lower, (float)spitter.sleepProgress, -0.13665928F, 0.0F, 0.7740535F, 40.0F);
         this.progressRotation(this.arm_right_upper, (float)spitter.sleepProgress, -0.27314404F, 0.0F, 0.09110619F, 40.0F);
         this.progressRotation(this.arm_left_lower, (float)spitter.sleepProgress, -0.5009095F, -0.09110619F, 1.0472F, 40.0F);
      }
   }
}

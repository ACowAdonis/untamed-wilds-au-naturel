package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import untamedwilds.entity.fish.EntityShark;

public class ModelShark extends AdvancedEntityModel<EntityShark> {
   private final AdvancedModelBox body_main;
   private final AdvancedModelBox head_snout;
   private final AdvancedModelBox body_tail_1;
   private final AdvancedModelBox fin_dorsal;
   private final AdvancedModelBox fin_right;
   private final AdvancedModelBox fin_left;
   private final AdvancedModelBox head_face_1;
   private final AdvancedModelBox head_jaw;
   private final AdvancedModelBox head_face_teeth;
   private final AdvancedModelBox head_hammer;
   private final AdvancedModelBox head_jaw_teeth;
   private final AdvancedModelBox body_tail_2;
   private final AdvancedModelBox fin_pelvic_left;
   private final AdvancedModelBox fin_pelvic_right;
   private final AdvancedModelBox body_tail_3;
   private final AdvancedModelBox fin_what_top;
   private final AdvancedModelBox fin_what_bottom;
   private final AdvancedModelBox fin_caudal;
   private final AdvancedModelBox fin_caudal_2;
   private final AdvancedModelBox head_nose;
   private final ModelAnimator animator;
   private static AdvancedModelBox[] bodyParts_passive;
   private static AdvancedModelBox[] bodyParts_angry;

   public ModelShark() {
      this.texWidth = 128;
      this.texHeight = 64;
      this.head_hammer = new AdvancedModelBox(this, 0, 56);
      this.head_hammer.setRotationPoint(0.0F, -0.3F, -6.2F);
      this.head_hammer.addBox(-8.0F, -1.5F, -2.0F, 16.0F, 3.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_hammer, -0.091106184F, 0.0F, 0.0F);
      this.head_jaw = new AdvancedModelBox(this, 26, 23);
      this.head_jaw.setRotationPoint(0.0F, 2.0F, -6.5F);
      this.head_jaw.addBox(-3.0F, 0.0F, -5.0F, 6.0F, 2.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.head_jaw, -0.13665928F, 0.0F, 0.0F);
      this.head_snout = new AdvancedModelBox(this, 0, 23);
      this.head_snout.setRotationPoint(0.0F, -0.9F, -7.0F);
      this.head_snout.addBox(-4.5F, -3.0F, -7.0F, 9.0F, 7.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.head_snout, 0.091106184F, -5.2705566E-16F, 3.1003275E-16F);
      this.head_jaw_teeth = new AdvancedModelBox(this, 26, 38);
      this.head_jaw_teeth.setRotationPoint(0.0F, -1.0F, 0.0F);
      this.head_jaw_teeth.addBox(-3.0F, -1.0F, -5.0F, 6.0F, 2.0F, 5.0F, 0.0F);
      this.head_jaw.scaleX = 1.1F;
      this.body_tail_1 = new AdvancedModelBox(this, 44, 4);
      this.body_tail_1.setRotationPoint(0.0F, -0.6F, 5.5F);
      this.body_tail_1.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 7.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.body_tail_1, -0.13665928F, 0.0F, 0.0F);
      this.fin_left = new AdvancedModelBox(this, 50, 22);
      this.fin_left.mirror = true;
      this.fin_left.setRotationPoint(3.0F, 2.5F, -5.0F);
      this.fin_left.addBox(-0.5F, 0.0F, -2.0F, 1.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_left, 0.31869712F, 0.0F, -1.0927507F);
      this.fin_dorsal = new AdvancedModelBox(this, 66, 22);
      this.fin_dorsal.setRotationPoint(0.0F, -3.3F, 0.0F);
      this.fin_dorsal.addBox(-0.5F, -9.0F, -2.0F, 1.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal, -0.4098033F, 0.0F, 0.0F);
      this.fin_what_bottom = new AdvancedModelBox(this, 0, 6);
      this.fin_what_bottom.setRotationPoint(0.0F, 1.5F, 5.0F);
      this.fin_what_bottom.addBox(-0.5F, 0.0F, -2.0F, 1.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_what_bottom, 0.5009095F, 0.0F, 0.0F);
      this.head_face_1 = new AdvancedModelBox(this, 0, 37);
      this.head_face_1.setRotationPoint(0.0F, 0.3F, -6.0F);
      this.head_face_1.addBox(-4.0F, -3.0F, -8.0F, 8.0F, 5.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.head_face_1, 0.045553092F, 0.0F, 0.0F);
      this.head_nose = new AdvancedModelBox(this, 42, 49);
      this.head_nose.setRotationPoint(0.0F, -1.3F, -7.0F);
      this.head_nose.addBox(-2.5F, -1.5F, -12.0F, 5.0F, 3.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.head_nose, -0.18203785F, 0.0F, 0.0F);
      this.fin_caudal = new AdvancedModelBox(this, 96, 0);
      this.fin_caudal.setRotationPoint(0.0F, -1.1F, 5.4F);
      this.fin_caudal.addBox(-0.5F, -10.0F, -3.0F, 1.0F, 11.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.fin_caudal, -0.59184116F, 0.0F, 0.0F);
      this.body_tail_2 = new AdvancedModelBox(this, 76, 6);
      this.body_tail_2.setRotationPoint(0.0F, 0.0F, 10.0F);
      this.body_tail_2.addBox(-2.0F, -2.5F, -0.5F, 4.0F, 5.0F, 10.0F, 0.0F);
      this.setRotateAngle(this.body_tail_2, -0.045553092F, 0.0F, 0.0F);
      this.body_tail_3 = new AdvancedModelBox(this, 30, 0);
      this.body_tail_3.setRotationPoint(0.0F, 0.0F, 10.0F);
      this.body_tail_3.addBox(-1.5F, -2.0F, -0.5F, 3.0F, 3.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.body_tail_3, 0.13665928F, 0.0F, 0.0F);
      this.fin_what_top = new AdvancedModelBox(this, 0, 0);
      this.fin_what_top.setRotationPoint(0.0F, -1.5F, 3.0F);
      this.fin_what_top.addBox(-0.5F, -3.0F, -2.0F, 1.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_what_top, -0.5009095F, 0.0F, 0.0F);
      this.fin_caudal_2 = new AdvancedModelBox(this, 68, 0);
      this.fin_caudal_2.setRotationPoint(0.0F, 4.2F, 6.8F);
      this.fin_caudal_2.addBox(-0.5F, -4.0F, -5.0F, 1.0F, 4.0F, 7.0F, 0.0F);
      this.setRotateAngle(this.fin_caudal_2, -0.7740535F, 0.0F, 0.0F);
      this.fin_pelvic_right = new AdvancedModelBox(this, 36, 30);
      this.fin_pelvic_right.setRotationPoint(-2.3F, 3.0F, 7.7F);
      this.fin_pelvic_right.addBox(-0.5F, 0.0F, -0.8F, 1.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_right, 0.31869712F, 0.0F, 1.0927507F);
      this.body_main = new AdvancedModelBox(this, 0, 0);
      this.body_main.setRotationPoint(0.0F, 17.0F, 6.0F);
      this.body_main.addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 14.0F, 0.0F);
      this.setRotateAngle(this.body_main, 0.045553092F, 0.0F, 0.0F);
      this.fin_pelvic_left = new AdvancedModelBox(this, 36, 30);
      this.fin_pelvic_left.mirror = true;
      this.fin_pelvic_left.setRotationPoint(2.3F, 3.0F, 7.7F);
      this.fin_pelvic_left.addBox(-0.5F, 0.0F, -0.8F, 1.0F, 3.0F, 3.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_left, 0.31869712F, 0.0F, -1.0927507F);
      this.fin_right = new AdvancedModelBox(this, 50, 22);
      this.fin_right.setRotationPoint(-3.0F, 2.5F, -5.0F);
      this.fin_right.addBox(-0.5F, 0.0F, -2.0F, 1.0F, 10.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_right, 0.31869712F, 0.0F, 1.0927507F);
      this.head_face_teeth = new AdvancedModelBox(this, 28, 46);
      this.head_face_teeth.setRotationPoint(0.0F, 3.0F, 0.0F);
      this.head_face_teeth.addBox(-3.0F, -1.0F, -5.0F, 6.0F, 2.0F, 5.0F, 0.0F);
      this.head_face_1.addChild(this.head_hammer);
      this.head_snout.addChild(this.head_jaw);
      this.body_main.addChild(this.head_snout);
      this.head_jaw.addChild(this.head_jaw_teeth);
      this.body_main.addChild(this.body_tail_1);
      this.body_main.addChild(this.fin_left);
      this.body_main.addChild(this.fin_dorsal);
      this.body_tail_2.addChild(this.fin_what_bottom);
      this.head_snout.addChild(this.head_face_1);
      this.body_tail_3.addChild(this.fin_caudal);
      this.body_tail_1.addChild(this.body_tail_2);
      this.body_tail_2.addChild(this.body_tail_3);
      this.body_tail_2.addChild(this.fin_what_top);
      this.body_tail_3.addChild(this.fin_caudal_2);
      this.body_tail_1.addChild(this.fin_pelvic_right);
      this.body_tail_1.addChild(this.fin_pelvic_left);
      this.body_main.addChild(this.fin_right);
      this.head_face_1.addChild(this.head_face_teeth);
      this.head_face_1.addChild(this.head_nose);
      bodyParts_passive = new AdvancedModelBox[]{this.head_snout, this.body_main, this.body_tail_1, this.body_tail_2, this.body_tail_3};
      bodyParts_angry = new AdvancedModelBox[]{this.body_main, this.body_tail_1, this.body_tail_2, this.body_tail_3};
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_snout,
         this.body_tail_1,
         this.fin_dorsal,
         this.fin_right,
         this.fin_left,
         this.head_face_1,
         this.head_jaw,
         this.head_face_teeth,
         this.head_hammer,
         this.head_jaw_teeth,
         this.body_tail_2,
         new AdvancedModelBox[]{
            this.fin_pelvic_left,
            this.fin_pelvic_right,
            this.body_tail_3,
            this.fin_what_top,
            this.fin_what_bottom,
            this.fin_caudal,
            this.fin_caudal_2,
            this.head_nose
         }
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      this.animator.update(entityIn);
      this.animator.setAnimation(EntityShark.ATTACK_THRASH);
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_snout, -5.22F, 15.65F, -20.87F);
      this.rotate(this.animator, this.head_jaw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face_1, -44.35F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_snout, -5.22F, -5.22F, 10.43F);
      this.rotate(this.animator, this.head_jaw, 57.39F, 0.0F, 0.0F);
      this.rotate(this.animator, this.head_face_1, -44.35F, 0.0F, 0.0F);
      this.animator.endKeyframe();
      this.animator.resetKeyframe(5);
   }

   public void setupAnim(EntityShark shark, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(shark);
      float globalSpeed = 0.6F;
      float globalDegree = 1.0F;
      float shortFins = shark.hasShortFins() ? 0.5F : 1.0F;
      this.fin_left.scaleY = shortFins;
      this.fin_right.scaleY = shortFins;
      this.fin_dorsal.scaleY = shortFins;
      this.head_snout
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      if (shark.isInWater()) {
         this.setRotateAngle(this.body_main, shark.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
         this.body_main.rotationPointY = 17.0F;
         this.fin_left.rotateAngleZ = -1.0927507F;
         this.fin_right.rotateAngleZ = 1.0927507F;
      } else {
         this.body_main.rotationPointY = 20.0F;
         this.fin_left.rotateAngleZ = -1.4F;
         this.fin_right.rotateAngleZ = 1.4F;
      }

      if (!shark.isAngry()) {
         this.chainSwing(bodyParts_passive, globalSpeed * 0.8F, globalDegree, -5.0, limbSwing / 3.0F, Math.max(0.3F, limbSwingAmount));
      } else {
         this.chainSwing(bodyParts_angry, globalSpeed, globalDegree / 1.5F, -4.0, limbSwing / 3.0F, Math.max(0.3F, limbSwingAmount));
      }

      float partialTicks = Minecraft.getInstance().getFrameTime();
      float renderYaw = (float)shark.getMovementOffsets(0, partialTicks, 0);
      float renderPitch = (float)shark.getMovementOffsets(0, partialTicks, 1);
      this.body_tail_1.rotateAngleY = this.body_tail_1.rotateAngleY
         + this.smartClamp(Mth.wrapDegrees((float)shark.getMovementOffsets(12, partialTicks, 0) - renderYaw), -40, 40) * (float) (Math.PI / 180.0);
      this.body_tail_2.rotateAngleY = this.body_tail_2.rotateAngleY
         + this.smartClamp(Mth.wrapDegrees((float)shark.getMovementOffsets(17, partialTicks, 0) - renderYaw), -40, 40) * (float) (Math.PI / 180.0);
      this.body_tail_1.rotateAngleX = this.body_tail_1.rotateAngleX
         + this.smartClamp(Mth.wrapDegrees((float)shark.getMovementOffsets(12, partialTicks, 1) - renderPitch), -20, 20) * (float) (Math.PI / 180.0);
      this.body_main.rotateAngleZ = this.body_main.rotateAngleZ
         + this.smartClamp(Mth.wrapDegrees((float)shark.getMovementOffsets(7, partialTicks, 0) - renderYaw), -20, 20) * (float) (Math.PI / 180.0);
   }

   public float smartClamp(float angle, int min, int max) {
      float val = Math.abs(angle);
      if (val > 180.0F) {
         angle = 360.0F - val;
      }

      return Mth.clamp(angle, (float)min, (float)max);
   }
}

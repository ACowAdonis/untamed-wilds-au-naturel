package untamedwilds.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.fish.EntitySawfish;

public class ModelSawfish extends AdvancedEntityModel<EntitySawfish> {
   public AdvancedModelBox body_main;
   public AdvancedModelBox head_main;
   public AdvancedModelBox fin_right;
   public AdvancedModelBox body_2;
   public AdvancedModelBox fin_left;
   public AdvancedModelBox head_rostrum;
   public AdvancedModelBox head_teeth;
   public AdvancedModelBox body_3;
   public AdvancedModelBox fin_pelvic_right;
   public AdvancedModelBox fin_pelvic_left;
   public AdvancedModelBox fin_dorsal;
   public AdvancedModelBox tail_fin;
   public AdvancedModelBox fin_dorsal_1;
   private final ModelAnimator animator;
   private static AdvancedModelBox[] bodyParts_passive;

   public ModelSawfish() {
      this.texWidth = 64;
      this.texHeight = 64;
      this.fin_left = new AdvancedModelBox(this, 0, 37);
      this.fin_left.setRotationPoint(1.0F, 1.49F, 0.0F);
      this.fin_left.addBox(0.0F, -1.0F, 0.0F, 8.0F, 2.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.fin_left, 0.0F, (float) (Math.PI / 3), 0.0F);
      this.fin_pelvic_left = new AdvancedModelBox(this, 0, 0);
      this.fin_pelvic_left.setRotationPoint(4.0F, 0.99F, -1.0F);
      this.fin_pelvic_left.addBox(-6.0F, -1.0F, 0.0F, 6.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_left, 0.0F, 0.91053826F, 0.0F);
      this.fin_dorsal = new AdvancedModelBox(this, 48, 0);
      this.fin_dorsal.setRotationPoint(0.0F, -2.0F, -2.0F);
      this.fin_dorsal.addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal, -0.3642502F, 0.0F, 0.0F);
      this.fin_pelvic_right = new AdvancedModelBox(this, 0, 0);
      this.fin_pelvic_right.mirror = true;
      this.fin_pelvic_right.setRotationPoint(-4.0F, 0.99F, -1.0F);
      this.fin_pelvic_right.addBox(0.0F, -1.0F, 0.0F, 6.0F, 2.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.fin_pelvic_right, 0.0F, -0.91053826F, 0.0F);
      this.fin_right = new AdvancedModelBox(this, 0, 37);
      this.fin_right.mirror = true;
      this.fin_right.setRotationPoint(-1.0F, 1.49F, 0.0F);
      this.fin_right.addBox(-8.0F, -1.0F, 0.0F, 8.0F, 2.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.fin_right, 0.0F, (float) (-Math.PI / 3), 0.0F);
      this.head_rostrum = new AdvancedModelBox(this, 22, 44);
      this.head_rostrum.setRotationPoint(0.0F, 0.0F, -8.0F);
      this.head_rostrum.addBox(-1.5F, 0.0F, -18.0F, 3.0F, 2.0F, 18.0F, 0.0F);
      this.body_main = new AdvancedModelBox(this, 12, 0);
      this.body_main.setRotationPoint(0.0F, 21.5F, 0.0F);
      this.body_main.addBox(-5.0F, -2.5F, -8.0F, 10.0F, 5.0F, 16.0F, 0.0F);
      this.head_main = new AdvancedModelBox(this, 0, 48);
      this.head_main.setRotationPoint(0.0F, 0.5F, -8.0F);
      this.head_main.addBox(-4.0F, -2.0F, -8.0F, 8.0F, 4.0F, 8.0F, 0.0F);
      this.fin_dorsal_1 = new AdvancedModelBox(this, 48, 0);
      this.fin_dorsal_1.setRotationPoint(0.0F, -2.0F, -2.0F);
      this.fin_dorsal_1.addBox(-1.0F, -6.0F, 0.0F, 2.0F, 7.0F, 5.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal_1, -0.3642502F, 0.0F, 0.0F);
      this.body_2 = new AdvancedModelBox(this, 0, 21);
      this.body_2.setRotationPoint(0.0F, 0.5F, 8.0F);
      this.body_2.addBox(-3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 12.0F, 0.0F);
      this.tail_fin = new AdvancedModelBox(this, 48, 40);
      this.tail_fin.setRotationPoint(0.0F, -2.0F, 10.0F);
      this.tail_fin.addBox(-1.0F, -8.0F, 0.0F, 2.0F, 12.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.tail_fin, -0.59184116F, 0.0F, 0.0F);
      this.body_3 = new AdvancedModelBox(this, 30, 25);
      this.body_3.setRotationPoint(0.0F, 0.5F, 12.0F);
      this.body_3.addBox(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 12.0F, 0.0F);
      this.head_teeth = new AdvancedModelBox(this, 34, 21);
      this.head_teeth.setRotationPoint(0.0F, 1.5F, 0.0F);
      this.head_teeth.addBox(-3.5F, 0.0F, -17.0F, 7.0F, 0.0F, 16.0F, 0.0F);
      this.body_main.addChild(this.fin_left);
      this.body_2.addChild(this.fin_pelvic_left);
      this.body_2.addChild(this.fin_dorsal);
      this.body_2.addChild(this.fin_pelvic_right);
      this.body_main.addChild(this.fin_right);
      this.head_main.addChild(this.head_rostrum);
      this.body_main.addChild(this.head_main);
      this.body_3.addChild(this.fin_dorsal_1);
      this.body_main.addChild(this.body_2);
      this.body_3.addChild(this.tail_fin);
      this.body_2.addChild(this.body_3);
      this.head_rostrum.addChild(this.head_teeth);
      bodyParts_passive = new AdvancedModelBox[]{this.head_main, this.body_main, this.body_2, this.body_main, this.tail_fin};
      this.animator = ModelAnimator.create();
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.body_main);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.body_main,
         this.head_main,
         this.fin_right,
         this.body_2,
         this.fin_left,
         this.head_rostrum,
         this.head_teeth,
         this.body_3,
         this.fin_pelvic_right,
         this.fin_pelvic_left,
         this.fin_dorsal,
         this.tail_fin,
         new AdvancedModelBox[]{this.fin_dorsal_1}
      );
   }

   private void animate(IAnimatedEntity entityIn) {
      this.animator.update(entityIn);
      this.animator.setAnimation(EntitySawfish.ATTACK_THRASH);
      this.animator.startKeyframe(5);
      this.rotate(this.animator, this.head_main, -5.22F, 15.65F, -20.87F);
      this.animator.endKeyframe();
      int head_offset = -1;

      for (int i = 0; i < 6; i++) {
         this.animator.startKeyframe(4);
         this.rotate(this.animator, this.head_main, -5.22F, 30.0F * (float)head_offset, -20.87F);
         head_offset *= -1;
         this.animator.endKeyframe();
      }

      this.animator.resetKeyframe(4);
   }

   public void setupAnim(EntitySawfish shark, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      this.animate(shark);
      float globalSpeed = 0.6F;
      float globalDegree = 1.0F;
      if (shark.isBurrowing()) {
         this.body_main.rotationPointY = 24.0F;
      } else {
         this.body_main.rotationPointY = 21.5F;
      }

      this.body_main
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      if (shark.isInWater()) {
         this.setRotateAngle(this.body_main, shark.getXRot() * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      this.chainSwing(bodyParts_passive, globalSpeed * 0.8F, globalDegree, -5.0, limbSwing / 3.0F, Math.max(0.3F, limbSwingAmount));
      float partialTicks = ageInTicks - (float)shark.tickCount;
      float renderYaw = (float)shark.getMovementOffsets(0, partialTicks)[0];
      this.body_2.rotateAngleY = this.body_2.rotateAngleY
         + this.smartClamp((float)shark.getMovementOffsets(15, partialTicks)[0] - renderYaw, -20, 20) * (float) (Math.PI / 180.0);
      this.body_2.rotateAngleY = this.body_2.rotateAngleY
         + this.smartClamp((float)shark.getMovementOffsets(17, partialTicks)[0] - renderYaw, -20, 20) * (float) (Math.PI / 180.0);
      this.body_main.rotateAngleZ = this.body_main.rotateAngleZ
         + this.smartClamp((float)shark.getMovementOffsets(7, partialTicks)[0] - renderYaw, -10, 10) * (float) (Math.PI / 180.0);
   }

   public float smartClamp(float angle, int min, int max) {
      float val = Math.abs(angle);
      if (val > 180.0F) {
         angle = 360.0F - val;
      }

      return Mth.clamp(angle, (float)min, (float)max);
   }
}

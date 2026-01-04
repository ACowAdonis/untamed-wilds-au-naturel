package untamedwilds.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import untamedwilds.entity.mammal.EntityBaleenWhale;

public class ModelBaleenWhale extends AdvancedEntityModel<EntityBaleenWhale> {
   private final AdvancedModelBox main_body;
   private final AdvancedModelBox main_head;
   private final AdvancedModelBox fin_left;
   private final AdvancedModelBox tail_1;
   private final AdvancedModelBox fin_right;
   private final AdvancedModelBox head_mouth_top;
   private final AdvancedModelBox head_jaw_1;
   private final AdvancedModelBox head_mouth_top_1;
   private final AdvancedModelBox head_jaw_2;
   private final AdvancedModelBox fin_dorsal;
   private final AdvancedModelBox tail_2;
   private final AdvancedModelBox tail_3;
   private final AdvancedModelBox tail_left;
   private final AdvancedModelBox tail_right;
   private static AdvancedModelBox[] bodyParts;

   public ModelBaleenWhale() {
      this.texWidth = 256;
      this.texHeight = 256;
      this.tail_3 = new AdvancedModelBox(this, 0, 16);
      this.tail_3.setRotationPoint(0.0F, 0.0F, 30.0F);
      this.tail_3.addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 10.0F, 0.0F);
      this.head_mouth_top_1 = new AdvancedModelBox(this, 160, 72);
      this.head_mouth_top_1.setRotationPoint(0.0F, 6.0F, 0.0F);
      this.head_mouth_top_1.addBox(-9.0F, 0.0F, -26.0F, 18.0F, 8.0F, 26.0F, 0.0F);
      this.main_head = new AdvancedModelBox(this, 92, 0);
      this.main_head.setRotationPoint(0.0F, -4.0F, 0.0F);
      this.main_head.addBox(-12.0F, -6.0F, -10.0F, 24.0F, 12.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.main_head, 0.045553092F, 0.0F, 0.0F);
      this.main_head.scaleX = 1.02F;
      this.tail_1 = new AdvancedModelBox(this, 136, 16);
      this.tail_1.setRotationPoint(0.0F, 0.0F, 38.0F);
      this.tail_1.addBox(-10.0F, -9.0F, 0.0F, 20.0F, 18.0F, 38.0F, 0.0F);
      this.main_body = new AdvancedModelBox(this, 0, 0);
      this.main_body.setRotationPoint(0.0F, 12.0F, -32.0F);
      this.main_body.addBox(-12.0F, -10.0F, 0.0F, 24.0F, 22.0F, 44.0F, 0.0F);
      this.tail_right = new AdvancedModelBox(this, 0, 162);
      this.tail_right.mirror = true;
      this.tail_right.setRotationPoint(-1.0F, 0.0F, 7.0F);
      this.tail_right.addBox(-30.0F, -2.0F, -8.0F, 30.0F, 4.0F, 14.0F, 0.0F);
      this.setRotateAngle(this.tail_right, 0.0F, 0.59184116F, 0.0F);
      this.head_jaw_1 = new AdvancedModelBox(this, 68, 72);
      this.head_jaw_1.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.head_jaw_1.addBox(-12.0F, 4.0F, -42.0F, 24.0F, 8.0F, 44.0F, 0.0F);
      this.setRotateAngle(this.head_jaw_1, -0.13665928F, 0.0F, 0.0F);
      this.head_jaw_1.scaleX = 1.01F;
      this.fin_right = new AdvancedModelBox(this, 164, 0);
      this.fin_right.mirror = true;
      this.fin_right.setRotationPoint(-7.0F, 5.0F, 11.0F);
      this.fin_right.addBox(-28.0F, -2.0F, -6.0F, 28.0F, 4.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.fin_right, 0.0F, 0.59184116F, -0.68294734F);
      this.head_jaw_2 = new AdvancedModelBox(this, 0, 124);
      this.head_jaw_2.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.head_jaw_2.addBox(-12.0F, -6.0F, -32.0F, 24.0F, 6.0F, 32.0F, 0.0F);
      this.tail_2 = new AdvancedModelBox(this, 114, 124);
      this.tail_2.setRotationPoint(0.0F, 0.0F, 38.0F);
      this.tail_2.addBox(-6.0F, -7.0F, 0.0F, 12.0F, 14.0F, 30.0F, 0.0F);
      this.tail_left = new AdvancedModelBox(this, 0, 162);
      this.tail_left.setRotationPoint(1.0F, 0.0F, 7.0F);
      this.tail_left.addBox(0.0F, -2.0F, -8.0F, 30.0F, 4.0F, 14.0F, 0.0F);
      this.setRotateAngle(this.tail_left, 0.0F, -0.59184116F, 0.0F);
      this.head_mouth_top = new AdvancedModelBox(this, 0, 66);
      this.head_mouth_top.setRotationPoint(0.0F, -6.0F, -10.0F);
      this.head_mouth_top.addBox(-12.0F, 0.0F, -32.0F, 24.0F, 6.0F, 32.0F, 0.0F);
      this.head_mouth_top.scaleX = 1.01F;
      this.fin_left = new AdvancedModelBox(this, 164, 0);
      this.fin_left.setRotationPoint(7.0F, 5.0F, 11.0F);
      this.fin_left.addBox(0.0F, -2.0F, -6.0F, 28.0F, 4.0F, 12.0F, 0.0F);
      this.setRotateAngle(this.fin_left, 0.0F, -0.59184116F, 0.68294734F);
      this.fin_dorsal = new AdvancedModelBox(this, 0, 0);
      this.fin_dorsal.setRotationPoint(0.0F, -6.0F, 36.0F);
      this.fin_dorsal.addBox(-2.0F, -8.0F, -4.0F, 4.0F, 8.0F, 8.0F, 0.0F);
      this.setRotateAngle(this.fin_dorsal, -0.5462881F, 0.0F, 0.0F);
      this.tail_2.addChild(this.tail_3);
      this.head_mouth_top.addChild(this.head_mouth_top_1);
      this.main_body.addChild(this.main_head);
      this.main_body.addChild(this.tail_1);
      this.tail_3.addChild(this.tail_right);
      this.main_head.addChild(this.head_jaw_1);
      this.main_body.addChild(this.fin_right);
      this.head_jaw_1.addChild(this.head_jaw_2);
      this.tail_1.addChild(this.tail_2);
      this.tail_3.addChild(this.tail_left);
      this.main_head.addChild(this.head_mouth_top);
      this.main_body.addChild(this.fin_left);
      this.tail_1.addChild(this.fin_dorsal);
      bodyParts = new AdvancedModelBox[]{this.main_head, this.main_body, this.tail_1, this.tail_2, this.tail_3};
      this.updateDefaultPose();
   }

   public Iterable<BasicModelPart> parts() {
      return ImmutableList.of(this.main_body);
   }

   public Iterable<AdvancedModelBox> getAllParts() {
      return ImmutableList.of(
         this.main_body,
         this.main_head,
         this.fin_left,
         this.tail_1,
         this.fin_right,
         this.head_mouth_top,
         this.head_jaw_1,
         this.head_mouth_top_1,
         this.head_jaw_2,
         this.fin_dorsal,
         this.tail_2,
         this.tail_3,
         new AdvancedModelBox[]{this.tail_left, this.tail_right}
      );
   }

   public void setupAnim(EntityBaleenWhale whale, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.resetToDefaultPose();
      float globalSpeed = 0.6F;
      float globalDegree = 1.0F;
      float fin_scale = whale.hasLongFins() ? 1.7F : 1.0F;
      this.fin_left.scaleX = fin_scale;
      this.fin_right.scaleX = fin_scale;
      this.main_body
         .setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.08F) + 0.01F, (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.08F), 1.0F);
      this.tail_1.setScale((float)(1.0 + Math.sin((double)(ageInTicks / 20.0F)) * 0.04F), (float)(1.0 + Math.sin((double)(ageInTicks / 16.0F)) * 0.04F), 1.0F);
      this.main_body.rotateAngleX = headPitch * (float) (Math.PI / 180.0);
      this.main_body.rotateAngleY = netHeadYaw * (float) (Math.PI / 180.0);
      if (whale.isInWater()) {
         this.setRotateAngle(this.main_body, Mth.clamp(whale.getXRot(), -20.0F, 20.0F) * (float) (Math.PI / 180.0), 0.0F, 0.0F);
      }

      float partialTicks = ageInTicks - (float)whale.tickCount;
      float renderYaw = (float)whale.getMovementOffsets(0, partialTicks)[0];
      if (!whale.isFeeding()) {
         this.chainWave(bodyParts, globalSpeed * 0.3F, globalDegree * 0.6F, -5.0, limbSwing, limbSwingAmount);
      }

      this.flap(this.fin_right, globalSpeed * 0.3F, globalDegree * 0.6F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
      this.flap(this.fin_left, globalSpeed * 0.3F, globalDegree * 0.6F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
      this.tail_1.rotateAngleY = this.tail_1.rotateAngleY
         + this.smartClamp((float)whale.getMovementOffsets(15, partialTicks)[0] - renderYaw, -40, 40) * (float) (Math.PI / 180.0);
      this.tail_2.rotateAngleY = this.tail_2.rotateAngleY
         + this.smartClamp((float)whale.getMovementOffsets(17, partialTicks)[0] - renderYaw, -40, 40) * (float) (Math.PI / 180.0);
      this.progressRotation(this.head_jaw_1, (float)whale.gulpProgress, (float)Math.toRadians(57.39F), 0.0F, 0.0F, 50.0F);
      this.progressPosition(this.head_jaw_2, (float)whale.gulpProgress, 0.0F, 6.0F, 0.0F, 50.0F);
      this.head_jaw_1.scaleY = 1.0F + (float)(0.3 * (double)whale.gulpProgress / 50.0);
      this.head_jaw_2.scaleZ = 1.0F + (float)(0.3 * (double)whale.gulpProgress / 50.0);
      this.main_body.scaleX = 1.0F + 0.1F * (float)whale.gulpProgress / 50.0F;
      this.main_body.scaleY = 1.0F + 0.1F * (float)whale.gulpProgress / 50.0F;
   }

   public float smartClamp(float angle, int min, int max) {
      float val = Math.abs(angle);
      if (val > 180.0F) {
         angle = 360.0F - val;
      }

      return Mth.clamp(angle, (float)min, (float)max);
   }
}

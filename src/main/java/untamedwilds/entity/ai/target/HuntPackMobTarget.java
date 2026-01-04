package untamedwilds.entity.ai.target;

import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import untamedwilds.entity.ComplexMob;

public class HuntPackMobTarget<T extends LivingEntity> extends HuntMobTarget<T> {
   public HuntPackMobTarget(
      ComplexMob creature, Class<T> classTarget, boolean checkSight, int hungerThreshold, boolean onlyNearby, Predicate<LivingEntity> targetSelector
   ) {
      super(creature, classTarget, checkSight, hungerThreshold, false, null);
   }

   @Override
   public void start() {
      this.mob.setTarget(this.targetMob);
      if (this.mob instanceof ComplexMob mob && mob.herd != null) {
         for (ComplexMob creature : mob.herd.creatureList) {
            creature.setTarget(this.targetMob);
         }
      }

      super.start();
   }
}

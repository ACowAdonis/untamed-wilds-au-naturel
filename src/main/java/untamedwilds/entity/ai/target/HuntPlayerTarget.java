package untamedwilds.entity.ai.target;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMobTerrestrial;

/**
 * Goal that allows large predators to hunt players.
 * Triggers when:
 * - The mob is a "man-eater" (rolled on spawn), OR
 * - The mob is hungry (hunger <= threshold) and passes a per-second probability check
 *
 * Players who are sneaking, creative, or spectator are exempt.
 */
public class HuntPlayerTarget extends TargetGoal {
   private final ComplexMobTerrestrial predator;
   private final int hungerThreshold;
   private Player targetPlayer;
   private int checkCooldown = 0;

   public HuntPlayerTarget(ComplexMobTerrestrial predator) {
      this(predator, 30);
   }

   public HuntPlayerTarget(ComplexMobTerrestrial predator, int hungerThreshold) {
      super(predator, true, true);
      this.predator = predator;
      this.hungerThreshold = hungerThreshold;
      this.setFlags(EnumSet.of(Flag.TARGET));
   }

   @Override
   public boolean canUse() {
      // Check if feature is enabled
      if (!ConfigGamerules.predatorPlayerHunting.get()) {
         return false;
      }

      // Don't hunt if baby, tamed, or already has a target
      if (this.predator.isBaby() || this.predator.isTame() || this.predator.getTarget() != null) {
         return false;
      }

      // Don't hunt if health is low
      if (this.predator.getHealth() < this.predator.getMaxHealth() / 3.0F) {
         return false;
      }

      // Man-eaters always try to hunt players
      boolean isManEater = this.predator.isManEater();

      // Non-man-eaters need to be hungry and pass a probability check
      if (!isManEater) {
         if (this.predator.getHunger() > this.hungerThreshold) {
            return false;
         }

         // Only check once per second to avoid constant probability rolls
         if (this.checkCooldown > 0) {
            this.checkCooldown--;
            return false;
         }
         this.checkCooldown = 20; // Reset to 1 second

         // Probability check
         double chance = ConfigGamerules.predatorHungryHuntChance.get();
         if (this.predator.getRandom().nextDouble() >= chance) {
            return false;
         }
      }

      // Find nearby players
      List<Player> nearbyPlayers = this.predator.level().getEntitiesOfClass(
         Player.class,
         this.predator.getBoundingBox().inflate(this.getFollowDistance(), 4.0, this.getFollowDistance()),
         this::isValidTarget
      );

      if (nearbyPlayers.isEmpty()) {
         return false;
      }

      // Sort by distance and pick closest
      nearbyPlayers.sort((p1, p2) -> {
         double d1 = this.predator.distanceToSqr(p1);
         double d2 = this.predator.distanceToSqr(p2);
         return Double.compare(d1, d2);
      });

      this.targetPlayer = nearbyPlayers.get(0);
      return true;
   }

   private boolean isValidTarget(Player player) {
      // Exempt sneaking, creative, and spectator players
      if (player.isSteppingCarefully() || player.isCreative() || player.isSpectator()) {
         return false;
      }

      // Standard targeting conditions
      return TargetingConditions.forCombat()
         .range(this.getFollowDistance())
         .test(this.predator, player);
   }

   @Override
   public void start() {
      this.predator.setTarget(this.targetPlayer);
      super.start();
   }

   @Override
   public boolean canContinueToUse() {
      return super.canContinueToUse();
   }

   @Override
   public void stop() {
      this.targetPlayer = null;
      super.stop();
   }
}

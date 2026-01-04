package untamedwilds.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.phys.Vec3;

public class HerdEntity {
   private static final int HERD_TICK_INTERVAL = 40; // Reduced from 10 to 40 ticks for performance
   private int maxHerdSize;
   private float radius = 8.0F;
   private boolean openToCombine;
   private ComplexMob leader;
   private final Random rand;
   public final List<ComplexMob> creatureList = new ArrayList<>();
   public double splitOffDistance = 1024.0;
   private final List<ComplexMob> toRemove = new ArrayList<>(); // Reusable list to avoid allocations

   public HerdEntity(ComplexMob creature, int maxSize) {
      this.openToCombine = true;
      this.rand = new Random();
      this.maxHerdSize = maxSize;
      this.setLeader(creature);
   }

   public void setLeader(ComplexMob creature) {
      this.leader = creature;
      if (!this.containsCreature(this.leader)) {
         this.addCreature(this.leader);
      }
   }

   public void chooseRandomLeader() {
      this.setLeader(this.creatureList.get(this.rand.nextInt(this.creatureList.size())));
   }

   public ComplexMob getLeader() {
      return this.leader;
   }

   public void addCreature(ComplexMob creature) {
      if (!this.creatureList.contains(creature)) {
         this.creatureList.add(creature);
      }
   }

   public boolean containsCreature(ComplexMob creature) {
      return this.creatureList.contains(creature);
   }

   public void removeCreature(HerdEntity herd, ComplexMob creature) {
      herd.creatureList.remove(creature);
      if (herd.creatureList.size() > 0 && herd.getLeader() == creature) {
         herd.chooseRandomLeader();
      }

      if (creature instanceof IPackEntity) {
         IPackEntity.initPack(creature);
      }
   }

   public void setMaxSize(int maxSchoolSize) {
      this.maxHerdSize = maxSchoolSize;
   }

   public int getMaxSize() {
      return this.maxHerdSize;
   }

   public void setRadius(float radius) {
      this.radius = radius;
   }

   public float getRadius() {
      return this.radius;
   }

   public void setOpenToCombine(boolean openToCombine) {
      this.openToCombine = openToCombine;
   }

   private boolean isOpenToCombine() {
      return this.openToCombine;
   }

   public void tick() {
      if (this.creatureList.size() == this.getMaxSize()) {
         this.setOpenToCombine(false);
      } else if (this.rand.nextInt(1800) == 0) {
         this.setOpenToCombine(!this.isOpenToCombine());
      }

      if (this.getLeader().tickCount % HERD_TICK_INTERVAL == 0) {
         this.toRemove.clear(); // Reuse list instead of allocating new one
         if (this.isOpenToCombine()) {
            for (ComplexMob creature : this.getLeader().level().getEntitiesOfClass(ComplexMob.class, this.getLeader().getBoundingBox().inflate(16.0, 12.0, 16.0))) {
               if (!this.containsCreature(creature) && creature.herd != null && canCombineHerds(this, creature.herd)) {
                  int netSize = this.creatureList.size() + creature.herd.creatureList.size();
                  if (creature.herd.isOpenToCombine()
                     && creature.getClass().equals(this.getLeader().getClass())
                     && netSize <= this.getMaxSize()
                     && netSize <= creature.herd.getMaxSize()) {
                     combineHerds(this, creature.herd);
                  }
               }
            }
         }

         for (ComplexMob complexMob : this.creatureList) {
            if (!complexMob.isAlive() || !(complexMob.distanceToSqr(this.leader) <= this.splitOffDistance)) {
               this.toRemove.add(complexMob);
            } else if (complexMob != this.leader && complexMob.distanceToSqr(this.leader) <= (double)(this.radius * this.radius)) {
               Vec3 vec = this.leader.getLookAngle();
               complexMob.getLookControl()
                  .setLookAt(complexMob.getX() + vec.x, complexMob.getY() + vec.y, complexMob.getZ() + vec.z, 6.0F, 85.0F);
            }
         }

         for (ComplexMob mob : this.toRemove) {
            this.removeCreature(this, mob);
         }
      }
   }

   static boolean canCombineHerds(HerdEntity thisPack, HerdEntity otherPack) {
      return thisPack.creatureList.size() + otherPack.creatureList.size() <= thisPack.getMaxSize();
   }

   public static void combineHerds(HerdEntity herd1, HerdEntity herd2) {
      if (herd2.creatureList.size() > herd1.creatureList.size()) {
         herd1.setLeader(herd2.getLeader());
      }

      if (herd2.getMaxSize() < herd1.getMaxSize()) {
         herd1.setMaxSize(herd2.getMaxSize());
      }

      if (herd2.getRadius() < herd1.getRadius()) {
         herd1.setRadius(herd2.getRadius());
      }

      herd1.creatureList.addAll(herd2.creatureList);
   }
}

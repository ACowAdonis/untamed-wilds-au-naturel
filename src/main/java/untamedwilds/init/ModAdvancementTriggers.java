package untamedwilds.init;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.storage.loot.LootContext;

public class ModAdvancementTriggers {
   public static ModAdvancementTriggers.UntamedTriggers NO_PATCHOULI_LOADED = new ModAdvancementTriggers.UntamedTriggers(
      new ResourceLocation("untamedwilds", "guidebook_alt")
   );
   public static ModAdvancementTriggers.UntamedTriggers BAIT_BASIC = new ModAdvancementTriggers.UntamedTriggers(
      new ResourceLocation("untamedwilds", "used_bait")
   );
   public static ModAdvancementTriggers.UntamedTriggers MASTER_BAIT = new ModAdvancementTriggers.UntamedTriggers(
      new ResourceLocation("untamedwilds", "master_bait")
   );
   public static ModAdvancementTriggers.UntamedTriggers ACTIVATED_FEEDER = new ModAdvancementTriggers.UntamedTriggers(
      new ResourceLocation("untamedwilds", "activated_feeder")
   );
   public static ModAdvancementTriggers.DiscoverAnimalTrigger DISCOVERED = new ModAdvancementTriggers.DiscoverAnimalTrigger();

   public static void register() {
      CriteriaTriggers.register(NO_PATCHOULI_LOADED);
      CriteriaTriggers.register(BAIT_BASIC);
      CriteriaTriggers.register(MASTER_BAIT);
      CriteriaTriggers.register(ACTIVATED_FEEDER);
      CriteriaTriggers.register(DISCOVERED);
   }

   public static class DiscoverAnimalTrigger extends SimpleCriterionTrigger<ModAdvancementTriggers.DiscoverAnimalTrigger.TriggerInstance> {
      private static final ResourceLocation id = new ResourceLocation("untamedwilds", "discovered");

      public ResourceLocation getId() {
         return id;
      }

      public ModAdvancementTriggers.DiscoverAnimalTrigger.TriggerInstance createInstance(
         JsonObject jsonObjectIn, ContextAwarePredicate predicateIn, DeserializationContext contextIn
      ) {
         ContextAwarePredicate composite = EntityPredicate.fromJson(jsonObjectIn, "entity", contextIn);
         return new ModAdvancementTriggers.DiscoverAnimalTrigger.TriggerInstance(predicateIn, composite);
      }

      public void trigger(ServerPlayer playerIn, Animal targetIn) {
         LootContext lootcontext = EntityPredicate.createContext(playerIn, targetIn);
         this.trigger(playerIn, input -> input.matches(lootcontext));
      }

      public static class TriggerInstance extends AbstractCriterionTriggerInstance {
         private final ContextAwarePredicate entity;

         public TriggerInstance(ContextAwarePredicate p_68846_, ContextAwarePredicate p_68847_) {
            super(ModAdvancementTriggers.DiscoverAnimalTrigger.id, p_68846_);
            this.entity = p_68847_;
         }

         public boolean matches(LootContext p_68853_) {
            return this.entity.matches(p_68853_);
         }

         public JsonObject serializeToJson(SerializationContext p_68851_) {
            JsonObject jsonobject = super.serializeToJson(p_68851_);
            jsonobject.add("entity", this.entity.toJson(p_68851_));
            return jsonobject;
         }
      }
   }

   public static class UntamedTriggers extends SimpleCriterionTrigger<ModAdvancementTriggers.UntamedTriggers.Instance> {
      private final ResourceLocation id;

      public UntamedTriggers(ResourceLocation resourceLocation) {
         this.id = resourceLocation;
      }

      public ModAdvancementTriggers.UntamedTriggers.Instance createInstance(
         JsonObject objectIn, ContextAwarePredicate predicateIn, DeserializationContext p_230241_3_
      ) {
         return new ModAdvancementTriggers.UntamedTriggers.Instance(predicateIn, this.id);
      }

      public void trigger(ServerPlayer entityIn) {
         this.trigger(entityIn, input -> true);
      }

      public ResourceLocation getId() {
         return this.id;
      }

      public static class Instance extends AbstractCriterionTriggerInstance {
         public Instance(ContextAwarePredicate p_i231507_1_, ResourceLocation res) {
            super(res, p_i231507_1_);
         }

         public JsonObject serializeToJson(SerializationContext p_230240_1_) {
            return super.serializeToJson(p_230240_1_);
         }
      }
   }
}

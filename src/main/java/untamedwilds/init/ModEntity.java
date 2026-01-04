package untamedwilds.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import untamedwilds.client.render.RendererAardvark;
import untamedwilds.client.render.RendererAnaconda;
import untamedwilds.client.render.RendererArowana;
import untamedwilds.client.render.RendererBaleenWhale;
import untamedwilds.client.render.RendererBear;
import untamedwilds.client.render.RendererBigCat;
import untamedwilds.client.render.RendererBison;
import untamedwilds.client.render.RendererBoar;
import untamedwilds.client.render.RendererButterfly;
import untamedwilds.client.render.RendererCamel;
import untamedwilds.client.render.RendererCatfish;
import untamedwilds.client.render.RendererFootballFish;
import untamedwilds.client.render.RendererGiantClam;
import untamedwilds.client.render.RendererGiantSalamander;
import untamedwilds.client.render.RendererHippo;
import untamedwilds.client.render.RendererHyena;
import untamedwilds.client.render.RendererKingCrab;
import untamedwilds.client.render.RendererManatee;
import untamedwilds.client.render.RendererMonitor;
import untamedwilds.client.render.RendererNewt;
import untamedwilds.client.render.RendererOpossum;
import untamedwilds.client.render.RendererProjectileSpit;
import untamedwilds.client.render.RendererRhino;
import untamedwilds.client.render.RendererSawfish;
import untamedwilds.client.render.RendererShark;
import untamedwilds.client.render.RendererSnake;
import untamedwilds.client.render.RendererSoftshellTurtle;
import untamedwilds.client.render.RendererSpadefish;
import untamedwilds.client.render.RendererSpitter;
import untamedwilds.client.render.RendererSunfish;
import untamedwilds.client.render.RendererTarantula;
import untamedwilds.client.render.RendererTerrorBird;
import untamedwilds.client.render.RendererTortoise;
import untamedwilds.client.render.RendererTrevally;
import untamedwilds.client.render.RendererTriggerfish;
import untamedwilds.client.render.RendererWhaleShark;
import untamedwilds.client.render.RendererWildebeest;
import untamedwilds.entity.ProjectileSpit;
import untamedwilds.entity.amphibian.EntityGiantSalamander;
import untamedwilds.entity.amphibian.EntityNewt;
import untamedwilds.entity.arthropod.EntityButterfly;
import untamedwilds.entity.arthropod.EntityKingCrab;
import untamedwilds.entity.arthropod.EntityTarantula;
import untamedwilds.entity.bird.EntityTerrorBird;
import untamedwilds.entity.fish.EntityArowana;
import untamedwilds.entity.fish.EntityCatfish;
import untamedwilds.entity.fish.EntityFootballFish;
import untamedwilds.entity.fish.EntitySawfish;
import untamedwilds.entity.fish.EntityShark;
import untamedwilds.entity.fish.EntitySpadefish;
import untamedwilds.entity.fish.EntitySunfish;
import untamedwilds.entity.fish.EntityTrevally;
import untamedwilds.entity.fish.EntityTriggerfish;
import untamedwilds.entity.fish.EntityWhaleShark;
import untamedwilds.entity.mammal.EntityAardvark;
import untamedwilds.entity.mammal.EntityBaleenWhale;
import untamedwilds.entity.mammal.EntityBear;
import untamedwilds.entity.mammal.EntityBigCat;
import untamedwilds.entity.mammal.EntityBison;
import untamedwilds.entity.mammal.EntityBoar;
import untamedwilds.entity.mammal.EntityCamel;
import untamedwilds.entity.mammal.EntityHippo;
import untamedwilds.entity.mammal.EntityHyena;
import untamedwilds.entity.mammal.EntityManatee;
import untamedwilds.entity.mammal.EntityOpossum;
import untamedwilds.entity.mammal.EntityRhino;
import untamedwilds.entity.mammal.EntityWildebeest;
import untamedwilds.entity.mollusk.EntityGiantClam;
import untamedwilds.entity.relict.EntitySpitter;
import untamedwilds.entity.reptile.EntityAnaconda;
import untamedwilds.entity.reptile.EntityMonitor;
import untamedwilds.entity.reptile.EntitySnake;
import untamedwilds.entity.reptile.EntitySoftshellTurtle;
import untamedwilds.entity.reptile.EntityTortoise;
import untamedwilds.item.UntamedSpawnEggItem;

@EventBusSubscriber(
   modid = "untamedwilds",
   bus = Bus.MOD
)
public class ModEntity {
   public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "untamedwilds");
   public static RegistryObject<EntityType<EntityTarantula>> TARANTULA = createEntity(EntityTarantula::new, "tarantula", 0.4F, 0.3F, 11907221, 2500907);
   public static RegistryObject<EntityType<EntityKingCrab>> KING_CRAB = createEntity(EntityKingCrab::new, "king_crab", 0.6F, 0.5F, 7426614, 11374672);
   public static RegistryObject<EntityType<EntityButterfly>> BUTTERFLY = createEntity(EntityButterfly::new, "butterfly", 0.6F, 0.5F, 11817989, 10525486);
   public static RegistryObject<EntityType<EntitySnake>> SNAKE = createEntity(EntitySnake::new, "snake", 0.95F, 0.3F, 14198098, 6042917);
   public static RegistryObject<EntityType<EntitySoftshellTurtle>> SOFTSHELL_TURTLE = createEntity(
      EntitySoftshellTurtle::new, "softshell_turtle", 0.6F, 0.3F, 8553540, 2500907
   );
   public static RegistryObject<EntityType<EntityTortoise>> TORTOISE = createEntity(EntityTortoise::new, "tortoise", 0.6F, 0.6F, 11509620, 7819826);
   public static RegistryObject<EntityType<EntityAnaconda>> ANACONDA = createEntity(EntityAnaconda::new, "large_snake", 1.5F, 0.6F, 6647884, 4335898);
   public static RegistryObject<EntityType<EntityMonitor>> MONITOR = createEntity(EntityMonitor::new, "monitor", 1.3F, 0.6F, 4340780, 9800806);
   public static RegistryObject<EntityType<EntityGiantClam>> GIANT_CLAM = createEntity(EntityGiantClam::new, "giant_clam", 1.0F, 1.0F, 3435376, 11366716);
   public static RegistryObject<EntityType<EntityBear>> BEAR = createEntity(EntityBear::new, "bear", 1.3F, 1.3F, 2102027, 5655621);
   public static RegistryObject<EntityType<EntityBigCat>> BIG_CAT = createEntity(EntityBigCat::new, "big_cat", 1.2F, 1.0F, 12951365, 3682593);
   public static RegistryObject<EntityType<EntityHippo>> HIPPO = createEntity(EntityHippo::new, "hippo", 1.8F, 1.8F, 4602417, 9791329);
   public static RegistryObject<EntityType<EntityAardvark>> AARDVARK = createEntity(EntityAardvark::new, "aardvark", 0.9F, 0.9F, 4602417, 9791329);
   public static RegistryObject<EntityType<EntityRhino>> RHINO = createEntity(EntityRhino::new, "rhino", 2.0F, 1.8F, 7894646, 6707542);
   public static RegistryObject<EntityType<EntityHyena>> HYENA = createEntity(EntityHyena::new, "hyena", 0.9F, 1.1F, 7104599, 9931110);
   public static RegistryObject<EntityType<EntityBoar>> BOAR = createEntity(EntityBoar::new, "boar", 1.2F, 1.2F, 5258282, 6313033);
   public static RegistryObject<EntityType<EntityBison>> BISON = createEntity(EntityBison::new, "bison", 1.7F, 1.6F, 8674091, 4797482);
   public static RegistryObject<EntityType<EntityCamel>> CAMEL = createEntity(EntityCamel::new, "camel", 1.8F, 2.0F, 14727561, 9923389);
   public static RegistryObject<EntityType<EntityManatee>> MANATEE = createEntity(EntityManatee::new, "manatee", 1.8F, 2.0F, 4866112, 7894646);
   public static RegistryObject<EntityType<EntityBaleenWhale>> BALEEN_WHALE = createEntity(EntityBaleenWhale::new, "baleen_whale", 2.6F, 1.6F, 1184798, 5988712);
   public static RegistryObject<EntityType<EntityOpossum>> OPOSSUM = createEntity(EntityOpossum::new, "opossum", 0.9F, 0.9F, 11248283, 3684149);
   public static RegistryObject<EntityType<EntityWildebeest>> WILDEBEEST = createEntity(EntityWildebeest::new, "wildebeest", 1.2F, 1.2F, 7959647, 3815213);
   public static RegistryObject<EntityType<EntitySunfish>> SUNFISH = createEntity(EntitySunfish::new, "sunfish", 1.6F, 1.6F, 2905179, 11981011);
   public static RegistryObject<EntityType<EntityTrevally>> TREVALLY = createEntity(EntityTrevally::new, "trevally", 0.8F, 0.8F, 10859695, 13147415);
   public static RegistryObject<EntityType<EntityArowana>> AROWANA = createEntity(EntityArowana::new, "arowana", 0.6F, 0.6F, 6577221, 11706194);
   public static RegistryObject<EntityType<EntityShark>> SHARK = createEntity(EntityShark::new, "shark", 1.8F, 1.3F, 7033154, 11579555);
   public static RegistryObject<EntityType<EntityFootballFish>> FOOTBALL_FISH = createEntity(
      EntityFootballFish::new, "football_fish", 0.8F, 0.8F, 5461356, 3092535
   );
   public static RegistryObject<EntityType<EntityWhaleShark>> WHALE_SHARK = createEntity(EntityWhaleShark::new, "whale_shark", 2.6F, 1.6F, 2237478, 8289668);
   public static RegistryObject<EntityType<EntityTriggerfish>> TRIGGERFISH = createEntity(EntityTriggerfish::new, "triggerfish", 0.8F, 0.8F, 2034201, 16563456);
   public static RegistryObject<EntityType<EntityCatfish>> CATFISH = createEntity(EntityCatfish::new, "catfish", 0.8F, 0.8F, 5527907, 3812387);
   public static RegistryObject<EntityType<EntitySpadefish>> SPADEFISH = createEntity(EntitySpadefish::new, "spadefish", 0.8F, 0.8F, 5527907, 3812387);
   public static RegistryObject<EntityType<EntitySawfish>> SAWFISH = createEntity(EntitySawfish::new, "sawfish", 1.8F, 0.8F, 11579555, 6776152);
   public static RegistryObject<EntityType<EntityGiantSalamander>> GIANT_SALAMANDER = createEntity(
      EntityGiantSalamander::new, "giant_salamander", 1.0F, 0.6F, 3812387, 7033154
   );
   public static RegistryObject<EntityType<EntityNewt>> NEWT = createEntity(EntityNewt::new, "newt", 0.6F, 0.3F, 2302755, 16747776);
   public static RegistryObject<EntityType<EntityTerrorBird>> TERROR_BIRD = createEntity(EntityTerrorBird::new, "terror_bird", 0.9F, 1.4F, 3812387, 7033154);
   public static RegistryObject<EntityType<EntitySpitter>> SPITTER = createEntity(EntitySpitter::new, "spitter", 1.3F, 1.3F, 3814494, 11756768);
   public static RegistryObject<EntityType<ProjectileSpit>> SPIT = createProjectile(ProjectileSpit::new, "spit", 64, true, 0.6F, 0.3F);

   private static <T extends Projectile> RegistryObject<EntityType<T>> createProjectile(
      EntityFactory<T> factory, String name, int trackingRange, boolean sendsVelocityUpdates, float sizeX, float sizeY
   ) {
      return ENTITIES.register(
         name,
         () -> Builder.of(factory, MobCategory.MISC)
               .sized(sizeX, sizeY)
               .clientTrackingRange(trackingRange)
               .setShouldReceiveVelocityUpdates(sendsVelocityUpdates)
               .build(name)
      );
   }

   private static <T extends Mob> RegistryObject<EntityType<T>> createEntity(
      EntityFactory<T> factory, String name, float sizeX, float sizeY, int baseColor, int overlayColor
   ) {
      return createEntity(factory, MobCategory.MISC, name, 64, true, sizeX, sizeY, baseColor, overlayColor);
   }

   private static <T extends Mob> RegistryObject<EntityType<T>> createEntity(
      EntityFactory<T> factory,
      MobCategory classification,
      String name,
      int trackingRange,
      boolean sendsVelocityUpdates,
      float sizeX,
      float sizeY,
      int maincolor,
      int backcolor
   ) {
      RegistryObject<EntityType<T>> type = ENTITIES.register(
         name,
         () -> Builder.of(factory, classification)
               .sized(sizeX, sizeY)
               .clientTrackingRange(trackingRange)
               .setShouldReceiveVelocityUpdates(sendsVelocityUpdates)
               .build(name)
      );
      ModItems.ITEMS.register(name + "_spawn_egg", () -> new UntamedSpawnEggItem(type, maincolor, backcolor, new Properties()));
      return type;
   }

   @SubscribeEvent
   public static void bakeAttributes(EntityAttributeCreationEvent event) {
      event.put((EntityType)TARANTULA.get(), EntityTarantula.registerAttributes().build());
      event.put((EntityType)KING_CRAB.get(), EntityKingCrab.registerAttributes().build());
      event.put((EntityType)BUTTERFLY.get(), EntityButterfly.registerAttributes().build());
      event.put((EntityType)SNAKE.get(), EntitySnake.registerAttributes().build());
      event.put((EntityType)SOFTSHELL_TURTLE.get(), EntitySoftshellTurtle.registerAttributes().build());
      event.put((EntityType)TORTOISE.get(), EntityTortoise.registerAttributes().build());
      event.put((EntityType)ANACONDA.get(), EntityAnaconda.registerAttributes().build());
      event.put((EntityType)MONITOR.get(), EntityMonitor.registerAttributes().build());
      event.put((EntityType)GIANT_CLAM.get(), EntityGiantClam.registerAttributes().build());
      event.put((EntityType)BEAR.get(), EntityBear.registerAttributes().build());
      event.put((EntityType)BIG_CAT.get(), EntityBigCat.registerAttributes().build());
      event.put((EntityType)HIPPO.get(), EntityHippo.registerAttributes().build());
      event.put((EntityType)AARDVARK.get(), EntityAardvark.registerAttributes().build());
      event.put((EntityType)RHINO.get(), EntityRhino.registerAttributes().build());
      event.put((EntityType)HYENA.get(), EntityHyena.registerAttributes().build());
      event.put((EntityType)BOAR.get(), EntityBoar.registerAttributes().build());
      event.put((EntityType)BISON.get(), EntityBison.registerAttributes().build());
      event.put((EntityType)CAMEL.get(), EntityCamel.registerAttributes().build());
      event.put((EntityType)MANATEE.get(), EntityManatee.registerAttributes().build());
      event.put((EntityType)BALEEN_WHALE.get(), EntityBaleenWhale.registerAttributes().build());
      event.put((EntityType)OPOSSUM.get(), EntityOpossum.registerAttributes().build());
      event.put((EntityType)WILDEBEEST.get(), EntityWildebeest.registerAttributes().build());
      event.put((EntityType)SUNFISH.get(), EntitySunfish.registerAttributes().build());
      event.put((EntityType)TREVALLY.get(), EntityTrevally.registerAttributes().build());
      event.put((EntityType)AROWANA.get(), EntityArowana.registerAttributes().build());
      event.put((EntityType)SHARK.get(), EntityShark.registerAttributes().build());
      event.put((EntityType)FOOTBALL_FISH.get(), EntityFootballFish.registerAttributes().build());
      event.put((EntityType)WHALE_SHARK.get(), EntityWhaleShark.registerAttributes().build());
      event.put((EntityType)TRIGGERFISH.get(), EntityTriggerfish.registerAttributes().build());
      event.put((EntityType)CATFISH.get(), EntityCatfish.registerAttributes().build());
      event.put((EntityType)SPADEFISH.get(), EntitySpadefish.registerAttributes().build());
      event.put((EntityType)SAWFISH.get(), EntitySawfish.registerAttributes().build());
      event.put((EntityType)GIANT_SALAMANDER.get(), EntityGiantSalamander.registerAttributes().build());
      event.put((EntityType)NEWT.get(), EntityNewt.registerAttributes().build());
      event.put((EntityType)TERROR_BIRD.get(), EntityTerrorBird.registerAttributes().build());
      event.put((EntityType)SPITTER.get(), EntitySpitter.registerAttributes().build());
   }

   @SubscribeEvent
   public static void onRegisterRenderer(RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)TARANTULA.get(), RendererTarantula::new);
      event.registerEntityRenderer((EntityType)KING_CRAB.get(), RendererKingCrab::new);
      event.registerEntityRenderer((EntityType)BUTTERFLY.get(), RendererButterfly::new);
      event.registerEntityRenderer((EntityType)SOFTSHELL_TURTLE.get(), RendererSoftshellTurtle::new);
      event.registerEntityRenderer((EntityType)SNAKE.get(), RendererSnake::new);
      event.registerEntityRenderer((EntityType)TORTOISE.get(), RendererTortoise::new);
      event.registerEntityRenderer((EntityType)ANACONDA.get(), RendererAnaconda::new);
      event.registerEntityRenderer((EntityType)MONITOR.get(), RendererMonitor::new);
      event.registerEntityRenderer((EntityType)GIANT_CLAM.get(), RendererGiantClam::new);
      event.registerEntityRenderer((EntityType)BEAR.get(), RendererBear::new);
      event.registerEntityRenderer((EntityType)BIG_CAT.get(), RendererBigCat::new);
      event.registerEntityRenderer((EntityType)HIPPO.get(), RendererHippo::new);
      event.registerEntityRenderer((EntityType)AARDVARK.get(), RendererAardvark::new);
      event.registerEntityRenderer((EntityType)RHINO.get(), RendererRhino::new);
      event.registerEntityRenderer((EntityType)HYENA.get(), RendererHyena::new);
      event.registerEntityRenderer((EntityType)BOAR.get(), RendererBoar::new);
      event.registerEntityRenderer((EntityType)BISON.get(), RendererBison::new);
      event.registerEntityRenderer((EntityType)CAMEL.get(), RendererCamel::new);
      event.registerEntityRenderer((EntityType)MANATEE.get(), RendererManatee::new);
      event.registerEntityRenderer((EntityType)BALEEN_WHALE.get(), RendererBaleenWhale::new);
      event.registerEntityRenderer((EntityType)OPOSSUM.get(), RendererOpossum::new);
      event.registerEntityRenderer((EntityType)WILDEBEEST.get(), RendererWildebeest::new);
      event.registerEntityRenderer((EntityType)SUNFISH.get(), RendererSunfish::new);
      event.registerEntityRenderer((EntityType)TREVALLY.get(), RendererTrevally::new);
      event.registerEntityRenderer((EntityType)AROWANA.get(), RendererArowana::new);
      event.registerEntityRenderer((EntityType)SHARK.get(), RendererShark::new);
      event.registerEntityRenderer((EntityType)FOOTBALL_FISH.get(), RendererFootballFish::new);
      event.registerEntityRenderer((EntityType)WHALE_SHARK.get(), RendererWhaleShark::new);
      event.registerEntityRenderer((EntityType)TRIGGERFISH.get(), RendererTriggerfish::new);
      event.registerEntityRenderer((EntityType)CATFISH.get(), RendererCatfish::new);
      event.registerEntityRenderer((EntityType)SPADEFISH.get(), RendererSpadefish::new);
      event.registerEntityRenderer((EntityType)SAWFISH.get(), RendererSawfish::new);
      event.registerEntityRenderer((EntityType)GIANT_SALAMANDER.get(), RendererGiantSalamander::new);
      event.registerEntityRenderer((EntityType)NEWT.get(), RendererNewt::new);
      event.registerEntityRenderer((EntityType)TERROR_BIRD.get(), RendererTerrorBird::new);
      event.registerEntityRenderer((EntityType)SPITTER.get(), RendererSpitter::new);
      event.registerEntityRenderer((EntityType)SPIT.get(), RendererProjectileSpit::new);
   }
}

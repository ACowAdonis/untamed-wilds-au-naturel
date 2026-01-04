package untamedwilds.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import untamedwilds.util.EntityUtils;

@EventBusSubscriber(
   modid = "untamedwilds",
   bus = Bus.FORGE
)
public class ModCommands {
   @SubscribeEvent
   public static void registerCommands(RegisterCommandsEvent event) {
      ModCommands.SonarCommand.register(event.getDispatcher(), event.getBuildContext());
   }

   public static class SonarCommand {
      public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
         dispatcher.register(
            (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("sonar").requires(input -> input.hasPermission(2)))
               .then(
                  ((RequiredArgumentBuilder)Commands.argument("entity", ResourceArgument.resource(context, Registries.ENTITY_TYPE))
                        .suggests(SuggestionProviders.SUMMONABLE_ENTITIES)
                        .executes(input -> execute((CommandSourceStack)input.getSource(), ResourceArgument.getSummonableEntityType(input, "entity"), false)))
                     .then(
                        Commands.argument("highlight", BoolArgumentType.bool())
                           .executes(
                              input -> execute(
                                    (CommandSourceStack)input.getSource(),
                                    ResourceArgument.getSummonableEntityType(input, "entity"),
                                    BoolArgumentType.getBool(input, "highlight")
                                 )
                           )
                     )
               )
         );
      }

      private static int execute(CommandSourceStack command, Reference<EntityType<?>> entityTypeIn, boolean highlight) {
         int entities = 0;

         for (Entity part : command.getLevel().getAllEntities()) {
            if (part instanceof LivingEntity) {
               LivingEntity living = (LivingEntity)part;
               if (living.getType().equals(entityTypeIn.get())) {
                  entities++;
                  if (highlight) {
                     living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                  }
               }
            }
         }

         if (command.getPlayer() != null) {
            command.getPlayer()
               .sendSystemMessage(
                  MutableComponent.create(
                     new LiteralContents(entities + " " + EntityUtils.getRegistryName((EntityType<?>)entityTypeIn.get()) + " currently loaded")
                  )
               );
         }

         return 1;
      }
   }
}

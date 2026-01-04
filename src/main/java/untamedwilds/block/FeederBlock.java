package untamedwilds.block;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.ISpecies;
import untamedwilds.init.ModAdvancementTriggers;
import untamedwilds.world.FaunaHandler;

public class FeederBlock extends Block implements SimpleWaterloggedBlock {
   protected static final VoxelShape SHAPE = Block.box(3.0, 3.0, 3.0, 13.0, 5.0, 13.0);
   public static final IntegerProperty HAS_FOOD = BlockStateProperties.AGE_3;

   public FeederBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_FOOD, 0));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HAS_FOOD});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = this.defaultBlockState();
      return (BlockState)blockstate.setValue(HAS_FOOD, 0);
   }

   public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collision) {
      return SHAPE;
   }

   public boolean canSurvive(BlockState state, LevelReader serverLevel, BlockPos pos) {
      BlockPos blockpos = pos.above();
      return this.isValidGround(serverLevel.getBlockState(blockpos), serverLevel, blockpos);
   }

   protected boolean isValidGround(BlockState state, BlockGetter serverLevel, BlockPos pos) {
      return !state.getCollisionShape(serverLevel, pos).getFaceShape(Direction.DOWN).isEmpty();
   }

   public InteractionResult use(BlockState state, Level serverLevel, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
      ItemStack itemstack = playerIn.getItemInHand(hand);
      if (hand == InteractionHand.MAIN_HAND && itemstack.is(Items.HONEY_BOTTLE)) {
         if (!serverLevel.isClientSide) {
            ModAdvancementTriggers.ACTIVATED_FEEDER.trigger((ServerPlayer)playerIn);
         }

         playerIn.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
         serverLevel.setBlock(pos, (BlockState)state.setValue(HAS_FOOD, 3), 3);
         serverLevel.playSound(
            null,
            (double)pos.getX() + 0.5,
            (double)pos.getY() + 0.5,
            (double)pos.getZ() + 0.5,
            SoundEvents.BOTTLE_EMPTY,
            SoundSource.BLOCKS,
            1.0F,
            1.0F
         );
         return InteractionResult.CONSUME;
      } else {
         return InteractionResult.PASS;
      }
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor serverLevel, BlockPos currentPos, BlockPos facingPos) {
      if (!this.canSurvive(stateIn, serverLevel, currentPos)) {
         serverLevel.destroyBlock(currentPos, true);
      }

      return super.updateShape(stateIn, facing, facingState, serverLevel, currentPos, facingPos);
   }

   public boolean isRandomlyTicking(BlockState state) {
      return (Integer)state.getValue(HAS_FOOD) > 0;
   }

   public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
      if ((Integer)state.getValue(HAS_FOOD) > 0) {
         this.spawnMobs(serverLevel, pos);
         this.spawnParticles(serverLevel, pos, ParticleTypes.HAPPY_VILLAGER);
         serverLevel.setBlock(pos, (BlockState)state.setValue(HAS_FOOD, (Integer)state.getValue(HAS_FOOD) - 1), 3);
      }
   }

   private <T extends ParticleOptions> void spawnParticles(Level serverLevel, BlockPos pos, T particle) {
      RandomSource random = serverLevel.getRandom();
      float d3 = random.nextFloat() * 0.02F;
      float d1 = random.nextFloat() * 0.02F;
      float d2 = random.nextFloat() * 0.02F;
      ((ServerLevel)serverLevel)
         .sendParticles(
            particle,
            (double)((float)pos.getX() + random.nextFloat()),
            (double)pos.getY(),
            (double)((float)pos.getZ() + random.nextFloat()),
            15,
            (double)d3,
            (double)d1,
            (double)d2,
            0.12F
         );
   }

   private void spawnMobs(ServerLevel serverLevel, BlockPos pos) {
      int count = serverLevel.random.nextInt(3) + 1;

      for (int i = 0; i < count; i++) {
         Optional<FaunaHandler.SpawnListEntry> entry = WeightedRandom.getRandomItem(
            serverLevel.random, FaunaHandler.getSpawnableList(FaunaHandler.animalType.FEEDER)
         );
         if (entry.isPresent()) {
            int x = pos.getX() + serverLevel.random.nextInt(16) - 8;
            int y = pos.getY() + serverLevel.random.nextInt(6) - 3;
            int z = pos.getZ() + serverLevel.random.nextInt(16) - 8;
            EntityType<?> entityType = entry.get().entityType;
            if (serverLevel.noCollision(entityType.getAABB((double)x, (double)y, (double)z))
               && SpawnPlacements.checkSpawnRules(entityType, serverLevel, MobSpawnType.CHUNK_GENERATION, pos, serverLevel.getRandom())) {
               Entity spawn = entityType.create(serverLevel, null, null, new BlockPos(x, y, z), MobSpawnType.NATURAL, true, true);
               if (spawn != null) {
                  if (spawn instanceof ComplexMob) {
                     ComplexMob entitySpawn = (ComplexMob)spawn;
                     Holder<Biome> optional = serverLevel.getBiome(new BlockPos(x, y, z));
                     int species = ((ISpecies)spawn).setSpeciesByBiome(optional, MobSpawnType.NATURAL);
                     entitySpawn.setVariant(species);
                     if (species == 99) {
                        entitySpawn.remove(RemovalReason.DISCARDED);
                        continue;
                     }

                     entitySpawn.chooseSkinForSpecies(entitySpawn, true);
                     entitySpawn.setRandomMobSize();
                     entitySpawn.setGender(entitySpawn.getRandom().nextInt(2));
                     if (spawn instanceof INeedsPostUpdate) {
                        ((INeedsPostUpdate)entitySpawn).updateAttributes();
                     }
                  }

                  serverLevel.addFreshEntityWithPassengers(spawn);
               }
            }
         }
      }
   }
}

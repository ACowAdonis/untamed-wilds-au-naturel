package untamedwilds.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "untamedwilds",
   bus = Bus.MOD
)
public class ConfigBase {
   private static final Builder common_builder = new Builder();
   // Config classes must be initialized BEFORE build() is called
   public static final ConfigFeatureControl FEATURES = new ConfigFeatureControl(common_builder);
   public static final ConfigGamerules GAMERULES = new ConfigGamerules(common_builder);
   public static final ConfigMobControl MOBS = new ConfigMobControl(common_builder);
   public static final ConfigModCompat COMPAT = new ConfigModCompat(common_builder);
   // Build the spec AFTER all config values are defined
   public static final ForgeConfigSpec common_config = common_builder.build();

   public static void loadConfig(ForgeConfigSpec config, String path) {
      CommentedFileConfig configData = (CommentedFileConfig)CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
      configData.load();
      config.setConfig(configData);
   }
}

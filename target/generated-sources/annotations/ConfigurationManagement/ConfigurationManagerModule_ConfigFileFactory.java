package ConfigurationManagement;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigurationManagerModule_ConfigFileFactory implements Factory<File> {
  private static final ConfigurationManagerModule_ConfigFileFactory INSTANCE =
      new ConfigurationManagerModule_ConfigFileFactory();

  @Override
  public File get() {
    return proxyConfigFile();
  }

  public static ConfigurationManagerModule_ConfigFileFactory create() {
    return INSTANCE;
  }

  public static File proxyConfigFile() {
    return Preconditions.checkNotNull(
        ConfigurationManagerModule.configFile(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

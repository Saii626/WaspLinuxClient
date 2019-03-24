package ConfigurationManagement.impl.ConfigManager;

import ConfigurationManagement.ConfigurationFileManager;
import ConfigurationManagement.ConfigurationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigManagerModule_GetConfigManagerFactory
    implements Factory<ConfigurationManager> {
  private final Provider<ConfigurationFileManager> configurationFileManagerProvider;

  public ConfigManagerModule_GetConfigManagerFactory(
      Provider<ConfigurationFileManager> configurationFileManagerProvider) {
    this.configurationFileManagerProvider = configurationFileManagerProvider;
  }

  @Override
  public ConfigurationManager get() {
    return proxyGetConfigManager(configurationFileManagerProvider.get());
  }

  public static ConfigManagerModule_GetConfigManagerFactory create(
      Provider<ConfigurationFileManager> configurationFileManagerProvider) {
    return new ConfigManagerModule_GetConfigManagerFactory(configurationFileManagerProvider);
  }

  public static ConfigurationManager proxyGetConfigManager(
      ConfigurationFileManager configurationFileManager) {
    return Preconditions.checkNotNull(
        ConfigManagerModule.getConfigManager(configurationFileManager),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

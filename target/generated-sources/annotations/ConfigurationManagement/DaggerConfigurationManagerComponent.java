package ConfigurationManagement;

import ConfigurationManagement.impl.ConfigFile.ConfigFileModule;
import ConfigurationManagement.impl.ConfigFile.ConfigFileModule_GetConfigGsonFactory;
import ConfigurationManagement.impl.ConfigFile.ConfigFileModule_GetFileManagerFactory;
import ConfigurationManagement.impl.ConfigManager.ConfigManagerModule;
import ConfigurationManagement.impl.ConfigManager.ConfigManagerModule_GetConfigManagerFactory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerConfigurationManagerComponent implements ConfigurationManagerComponent {
  private DaggerConfigurationManagerComponent() {}

  public static Builder builder() {
    return new Builder();
  }

  public static ConfigurationManagerComponent create() {
    return new Builder().build();
  }

  @Override
  public ConfigurationManager getConfigurationManager() {
    return ConfigManagerModule_GetConfigManagerFactory.proxyGetConfigManager(
        getConfigFileManager());
  }

  @Override
  public ConfigurationFileManager getConfigFileManager() {
    return ConfigFileModule_GetFileManagerFactory.proxyGetFileManager(
        ConfigurationManagerModule_ConfigFileFactory.proxyConfigFile(),
        ConfigFileModule_GetConfigGsonFactory.proxyGetConfigGson());
  }

  public static final class Builder {
    private Builder() {}

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder configurationManagerModule(
        ConfigurationManagerModule configurationManagerModule) {
      Preconditions.checkNotNull(configurationManagerModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder configFileModule(ConfigFileModule configFileModule) {
      Preconditions.checkNotNull(configFileModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder configManagerModule(ConfigManagerModule configManagerModule) {
      Preconditions.checkNotNull(configManagerModule);
      return this;
    }

    public ConfigurationManagerComponent build() {
      return new DaggerConfigurationManagerComponent();
    }
  }
}

package ConfigurationManagement;

import ConfigurationManagement.impl.ConfigFile.ConfigFileModule;
import ConfigurationManagement.impl.ConfigFile.ConfigFileModule_GetConfigGsonFactory;
import ConfigurationManagement.impl.ConfigFile.ConfigFileModule_GetFileManagerFactory;
import ConfigurationManagement.impl.ConfigManager.ConfigManagerModule;
import ConfigurationManagement.impl.ConfigManager.ConfigManagerModule_GetConfigManagerFactory;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerConfigurationManagerComponent implements ConfigurationManagerComponent {
  private Provider<File> getConfigFileProvider;

  private Provider<Gson> getConfigGsonProvider;

  private Provider<ConfigurationFileManager> getFileManagerProvider;

  private Provider<ConfigurationManager> getConfigManagerProvider;

  private DaggerConfigurationManagerComponent(
      UnsatisfiedDependenciesModule unsatisfiedDependenciesModuleParam) {

    initialize(unsatisfiedDependenciesModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final UnsatisfiedDependenciesModule unsatisfiedDependenciesModuleParam) {
    this.getConfigFileProvider =
        DoubleCheck.provider(
            UnsatisfiedDependenciesModule_GetConfigFileFactory.create(
                unsatisfiedDependenciesModuleParam));
    this.getConfigGsonProvider =
        DoubleCheck.provider(ConfigFileModule_GetConfigGsonFactory.create());
    this.getFileManagerProvider =
        DoubleCheck.provider(
            ConfigFileModule_GetFileManagerFactory.create(
                getConfigFileProvider, getConfigGsonProvider));
    this.getConfigManagerProvider =
        DoubleCheck.provider(
            ConfigManagerModule_GetConfigManagerFactory.create(getFileManagerProvider));
  }

  @Override
  public ConfigurationManager getConfigurationManager() {
    return getConfigManagerProvider.get();
  }

  public static final class Builder {
    private UnsatisfiedDependenciesModule unsatisfiedDependenciesModule;

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

    public Builder unsatisfiedDependenciesModule(
        UnsatisfiedDependenciesModule unsatisfiedDependenciesModule) {
      this.unsatisfiedDependenciesModule =
          Preconditions.checkNotNull(unsatisfiedDependenciesModule);
      return this;
    }

    public ConfigurationManagerComponent build() {
      Preconditions.checkBuilderRequirement(
          unsatisfiedDependenciesModule, UnsatisfiedDependenciesModule.class);
      return new DaggerConfigurationManagerComponent(unsatisfiedDependenciesModule);
    }
  }
}

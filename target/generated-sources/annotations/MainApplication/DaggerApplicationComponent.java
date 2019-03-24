package MainApplication;

import ConfigurationManagement.ConfigurationManagerComponent;
import SocketManagement.WaspberryWebsocket.WaspberryWebsocketComponent;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerApplicationComponent implements ApplicationComponent {
  private final WaspberryWebsocketComponent waspberryWebsocketComponent;

  private final ConfigurationManagerComponent configurationManagerComponent;

  private Provider<MainApplication> getProvider;

  private DaggerApplicationComponent(
      MainApplicationModule mainApplicationModuleParam,
      WaspberryWebsocketComponent waspberryWebsocketComponentParam,
      ConfigurationManagerComponent configurationManagerComponentParam) {
    this.waspberryWebsocketComponent = waspberryWebsocketComponentParam;
    this.configurationManagerComponent = configurationManagerComponentParam;
    initialize(
        mainApplicationModuleParam,
        waspberryWebsocketComponentParam,
        configurationManagerComponentParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(
      final MainApplicationModule mainApplicationModuleParam,
      final WaspberryWebsocketComponent waspberryWebsocketComponentParam,
      final ConfigurationManagerComponent configurationManagerComponentParam) {
    this.getProvider =
        DoubleCheck.provider(MainApplicationModule_GetFactory.create(mainApplicationModuleParam));
  }

  @Override
  public void inject(MainApplication application) {}

  @Override
  public WaspberryWebsocketComponent getWebsocketComponent() {
    return waspberryWebsocketComponent;
  }

  @Override
  public ConfigurationManagerComponent getConfigManagerCompoonent() {
    return configurationManagerComponent;
  }

  @Override
  public MainApplication getMainApplication() {
    return getProvider.get();
  }

  public static final class Builder {
    private MainApplicationModule mainApplicationModule;

    private WaspberryWebsocketComponent waspberryWebsocketComponent;

    private ConfigurationManagerComponent configurationManagerComponent;

    private Builder() {}

    public Builder mainApplicationModule(MainApplicationModule mainApplicationModule) {
      this.mainApplicationModule = Preconditions.checkNotNull(mainApplicationModule);
      return this;
    }

    public Builder waspberryWebsocketComponent(
        WaspberryWebsocketComponent waspberryWebsocketComponent) {
      this.waspberryWebsocketComponent = Preconditions.checkNotNull(waspberryWebsocketComponent);
      return this;
    }

    public Builder configurationManagerComponent(
        ConfigurationManagerComponent configurationManagerComponent) {
      this.configurationManagerComponent =
          Preconditions.checkNotNull(configurationManagerComponent);
      return this;
    }

    public ApplicationComponent build() {
      Preconditions.checkBuilderRequirement(mainApplicationModule, MainApplicationModule.class);
      Preconditions.checkBuilderRequirement(
          waspberryWebsocketComponent, WaspberryWebsocketComponent.class);
      Preconditions.checkBuilderRequirement(
          configurationManagerComponent, ConfigurationManagerComponent.class);
      return new DaggerApplicationComponent(
          mainApplicationModule, waspberryWebsocketComponent, configurationManagerComponent);
    }
  }
}

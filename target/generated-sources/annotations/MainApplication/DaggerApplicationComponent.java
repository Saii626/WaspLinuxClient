package MainApplication;

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

  private Provider<MainApplication> getProvider;

  private DaggerApplicationComponent(
      MainApplicationModule mainApplicationModuleParam,
      WaspberryWebsocketComponent waspberryWebsocketComponentParam) {
    this.waspberryWebsocketComponent = waspberryWebsocketComponentParam;
    initialize(mainApplicationModuleParam, waspberryWebsocketComponentParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(
      final MainApplicationModule mainApplicationModuleParam,
      final WaspberryWebsocketComponent waspberryWebsocketComponentParam) {
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
  public MainApplication getMainApplication() {
    return getProvider.get();
  }

  public static final class Builder {
    private MainApplicationModule mainApplicationModule;

    private WaspberryWebsocketComponent waspberryWebsocketComponent;

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

    public ApplicationComponent build() {
      Preconditions.checkBuilderRequirement(mainApplicationModule, MainApplicationModule.class);
      Preconditions.checkBuilderRequirement(
          waspberryWebsocketComponent, WaspberryWebsocketComponent.class);
      return new DaggerApplicationComponent(mainApplicationModule, waspberryWebsocketComponent);
    }
  }
}

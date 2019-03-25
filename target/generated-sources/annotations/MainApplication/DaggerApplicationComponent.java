package MainApplication;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerApplicationComponent implements ApplicationComponent {
  private Provider<MainApplication> getProvider;

  private DaggerApplicationComponent(MainApplicationModule mainApplicationModuleParam) {

    initialize(mainApplicationModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final MainApplicationModule mainApplicationModuleParam) {
    this.getProvider =
        DoubleCheck.provider(MainApplicationModule_GetFactory.create(mainApplicationModuleParam));
  }

  @Override
  public void inject(MainApplication application) {}

  @Override
  public MainApplication getMainApplication() {
    return getProvider.get();
  }

  public static final class Builder {
    private MainApplicationModule mainApplicationModule;

    private Builder() {}

    public Builder mainApplicationModule(MainApplicationModule mainApplicationModule) {
      this.mainApplicationModule = Preconditions.checkNotNull(mainApplicationModule);
      return this;
    }

    public ApplicationComponent build() {
      Preconditions.checkBuilderRequirement(mainApplicationModule, MainApplicationModule.class);
      return new DaggerApplicationComponent(mainApplicationModule);
    }
  }
}

package MainApplication;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainApplicationModule_GetFactory implements Factory<MainApplication> {
  private final MainApplicationModule module;

  public MainApplicationModule_GetFactory(MainApplicationModule module) {
    this.module = module;
  }

  @Override
  public MainApplication get() {
    return proxyGet(module);
  }

  public static MainApplicationModule_GetFactory create(MainApplicationModule module) {
    return new MainApplicationModule_GetFactory(module);
  }

  public static MainApplication proxyGet(MainApplicationModule instance) {
    return Preconditions.checkNotNull(
        instance.get(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

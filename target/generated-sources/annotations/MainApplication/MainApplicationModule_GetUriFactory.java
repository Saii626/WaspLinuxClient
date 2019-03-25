package MainApplication;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.net.URI;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainApplicationModule_GetUriFactory implements Factory<URI> {
  private static final MainApplicationModule_GetUriFactory INSTANCE =
      new MainApplicationModule_GetUriFactory();

  @Override
  public URI get() {
    return proxyGetUri();
  }

  public static MainApplicationModule_GetUriFactory create() {
    return INSTANCE;
  }

  public static URI proxyGetUri() {
    return Preconditions.checkNotNull(
        MainApplicationModule.getUri(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

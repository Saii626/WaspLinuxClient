package ConfigurationManagement;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UnsatisfiedDependenciesModule_GetConfigFileFactory implements Factory<File> {
  private final UnsatisfiedDependenciesModule module;

  public UnsatisfiedDependenciesModule_GetConfigFileFactory(UnsatisfiedDependenciesModule module) {
    this.module = module;
  }

  @Override
  public File get() {
    return proxyGetConfigFile(module);
  }

  public static UnsatisfiedDependenciesModule_GetConfigFileFactory create(
      UnsatisfiedDependenciesModule module) {
    return new UnsatisfiedDependenciesModule_GetConfigFileFactory(module);
  }

  public static File proxyGetConfigFile(UnsatisfiedDependenciesModule instance) {
    return Preconditions.checkNotNull(
        instance.getConfigFile(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

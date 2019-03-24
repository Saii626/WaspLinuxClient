package MainApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class MainApplicationModule {

    private MainApplication mainApplication;

    public MainApplicationModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @ApplicationScope
    public MainApplication get() {
        return mainApplication;
    }
}

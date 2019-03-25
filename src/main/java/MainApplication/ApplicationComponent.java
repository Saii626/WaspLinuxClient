package MainApplication;

import SocketManagement.WaspberryWebsocket.WaspberryWebsocketComponent;
import dagger.Component;

@ApplicationScope
@Component(modules = {MainApplicationModule.class}, dependencies = {WaspberryWebsocketComponent.class})
public interface ApplicationComponent {

    void inject(MainApplication application);

    WaspberryWebsocketComponent getWebsocketComponent();

    MainApplication getMainApplication();
}

package MainApplication;

import ConfigurationManagement.ConfigurationManagerComponent;
import SocketManagement.WaspberryWebsocket.WaspberryWebsocketComponent;
import dagger.Component;

@ApplicationScope
@Component(modules = {MainApplicationModule.class}, dependencies = {WaspberryWebsocketComponent.class, ConfigurationManagerComponent.class})
public interface ApplicationComponent {

    void inject(MainApplication application);

    WaspberryWebsocketComponent getWebsocketComponent();

    ConfigurationManagerComponent getConfigManagerCompoonent();

    MainApplication getMainApplication();
}

package MainApplication;

import ConfigurationManagement.ConfigKey;
import ConfigurationManagement.ConfigurationManager;
import ConfigurationManagement.ConfigurationManagerComponent;
import ConfigurationManagement.DaggerConfigurationManagerComponent;
import ConfigurationManagement.SerializerDeserializerClassMismatchException;
import ConfigurationManagement.UnsatisfiedDependenciesModule;
import Resources.Resources;
import SocketManagement.SocketManager;
import WaspberrySocketManagement.WaspberryWebsocket.WaspberrySocketManager;
import com.sun.jna.Library;
import com.sun.jna.Native;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApplication {

    private ConfigurationManager configurationManager;
    private WaspberrySocketManager waspberrySocketManager;
    private static MainApplication instance;

    private final int port = Resources.PORT;
    private Logger logger = LoggerFactory.getLogger(MainApplication.class.getSimpleName());
    private interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load("c", CLibrary.class);

        int getpid();
    }

    public MainApplication(String[] args) throws IOException, SerializerDeserializerClassMismatchException {
        // Don't change the order unless necessary
        instance = this;

        ConfigurationManagerComponent configurationManagerComponent = DaggerConfigurationManagerComponent.builder()
                .unsatisfiedDependenciesModule(new UnsatisfiedDependenciesModule(Resources.CONFIG_FILE))
                .build();
        configurationManager = configurationManagerComponent.getConfigurationManager();

        waspberrySocketManager = SocketManager.getSocketManager();

        if (configurationManager.get(ConfigKey.PID, Integer.class).isPresent()) {
            logger.debug("An instance is already running");
            logger.debug("Exiting....");
            System.exit(0);
        } else {
            logger.debug("Instance started. Running with pid: {}", CLibrary.INSTANCE.getpid());
            configurationManager.put(ConfigKey.PID, Integer.class, CLibrary.INSTANCE.getpid());
            configurationManager.sync();
            logger.debug("Adding shutdown hook");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    configurationManager.put(ConfigKey.PID);
                    configurationManager.sync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            startApplicationLoop();
        }
    }




    private void startApplicationLoop() {
        logger.debug("Starting application loop");

        waspberrySocketManager.connect();

        logger.debug("Infinity main loop");
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConfigurationManager getConfigurationManager() {
        return instance.configurationManager;
    }

    public static WaspberrySocketManager getWaspberrySocketManager() {
        return instance.waspberrySocketManager;
    }
}

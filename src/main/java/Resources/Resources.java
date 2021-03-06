package Resources;


import java.io.File;
import java.time.Duration;

public class Resources {

    public static final String HOME_LOCATION = System.getProperty("user.home");
    public static final String APP_CONFIG_LOCATION = HOME_LOCATION.concat("/.WaspLinuxClient");
    public static final File CONFIG_FILE = new File(APP_CONFIG_LOCATION.concat("/app.config"));
    public static final int PORT = 8080;
    public static final Duration WEBSOCKET_RECONNECT_TIMEOUT = Duration.ofSeconds(5);
    public static final String WEBSOCKET_HANDLER_DIR = "SocketManagement.Handlers";
    public static final String WEBSOCKET_MODELS_DIR = "SocketManagement.Models";
    public static final Duration NOTIFICATION_DEFAULT_TIMEOUT = Duration.ofSeconds(5);
}

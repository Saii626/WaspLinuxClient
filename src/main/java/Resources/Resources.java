package Resources;


import java.time.Duration;

public class Resources {

    public static final String HOME_LOCATION = System.getProperty("user.home");
    public static final String APP_CONFIG_LOCATION = HOME_LOCATION.concat("/.WaspLinuxClient");
    public static final String CONFIG_FILE = APP_CONFIG_LOCATION.concat("/app.config");
    public static final int PORT = 8080;
    public static final int WEBSOCKET_RECONNECT_TIMEOUT = 5000;
    public static final String WEBSOCKET_HANDLER_DIR = "WebsocketMessageHandling.Handlers";
    public static final String WEBSOCKET_MODELS_DIR = "WebsocketMessageHandling.Models";
    public static final Duration NOTIFICATION_DEFAULT_TIMEOUT = Duration.ofSeconds(5);
}

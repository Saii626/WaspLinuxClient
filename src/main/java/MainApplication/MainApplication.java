package MainApplication;

import CommandLineArgsParser.CommandLineParser;
import ConfigurationManagement.ConfigKey;
import ConfigurationManagement.ConfigurationManager;
import Resources.Resources;
import SocketManagement.WaspberrySocketManager;
import com.sun.jna.Library;
import com.sun.jna.Native;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainApplication {
    public static MainApplication instance;

    private ConfigurationManager configurationManager;
    private final File configFile = new File(Resources.CONFIG_FILE);
    private final int port = Resources.PORT;
    private Logger logger = LoggerFactory.getLogger(MainApplication.class.getSimpleName());

    private interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.load("c", CLibrary.class);

        int getpid();
    }

    public MainApplication(String[] args) throws IOException, URISyntaxException {
        MainApplication.instance = this;

        configurationManager = new ConfigurationManager(configFile);

        if (configurationManager.get(ConfigKey.PID).isPresent()) {
            logger.debug("An instance is already running");
//            sendDataToAlreadyRunningInstance(Integer.parseInt(configurationManager.get(ConfigKey.PID).get()), args);
            logger.debug("Exiting....");
            System.exit(0);
        } else {
            logger.debug("Instance started. Running with pid: {}", CLibrary.INSTANCE.getpid());
            configurationManager.put(ConfigKey.PID, String.valueOf(CLibrary.INSTANCE.getpid()));
            configurationManager.syncConfigurationFile();
            logger.debug("Adding shutdown hook");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    configurationManager.put(ConfigKey.PID);
                    configurationManager.syncConfigurationFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            updateConfigurations(args);
            startApplicationLoop();
        }
    }

//    private static void sendDataToAlreadyRunningInstance(int pid, String[] args) throws IOException {
//        StringBuilder commandStringBuilder = new StringBuilder();
//
//        for (String arg : args) {
//            commandStringBuilder.append(arg.concat(" "));
//        }
//
//        String commandString = commandStringBuilder.toString().trim();
//        ByteBuffer br = ByteBuffer.wrap(commandString.getBytes());
//
//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        serverSocketChannel.socket().bind(new InetSocketAddress(port));
//        serverSocketChannel.configureBlocking(false);
//        serverSocketChannel.register(Selector.open(), SelectionKey.OP_ACCEPT);
//
//        if (serverSocketChannel.isOpen()) {
//                SocketChannel socketChannel = serverSocketChannel.accept();
//                logger.debug("Opened socket connection to port: {}", port);
//
//                socketChannel.write(br);
//                logger.debug("Sent \"{}\"", commandString);
//                serverSocketChannel.close();
//                logger.debug("Closed socket connection");
//        }
//    }

    private void updateConfigurations(String[] args) {
        logger.debug("Updating configurations");
        CommandLineParser commandLineParser = new CommandLineParser(args);

        commandLineParser.getAllArguments().forEach(entry -> {
            ConfigKey key = commandLineParser.getConfigKey(entry.getKey());
            logger.debug("Updating \t{}:\t{}", key.getKey(), entry.getValue());
            configurationManager.put(key, entry.getValue());
        });
    }

    private void startApplicationLoop() {
        logger.debug("Starting application loop");
//        setupSocketServer();
        WaspberrySocketManager.connect();

        logger.debug("Infinity main loop");
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }
}

package SocketManagement;

import Resources.Resources;
import URLs.Url;
import WaspberrySocketManagement.WaspberryMessageHandler.MessageHandler;
import WaspberrySocketManagement.WaspberryMessageHandler.MessageModel;
import WaspberrySocketManagement.WaspberryWebsocket.DaggerWaspberryWebsocketComponent;
import WaspberrySocketManagement.WaspberryWebsocket.UnsatisfiedWebsocketDependenciesModule;
import WaspberrySocketManagement.WaspberryWebsocket.WaspberrySocketManager;
import WaspberrySocketManagement.WaspberryWebsocket.WaspberryWebsocketComponent;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SocketManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private WaspberryWebsocketComponent waspberryWebsocketComponent;

    private static SocketManager instance;
    private SocketManager(){}
    static {
        instance = new SocketManager();

        instance.waspberryWebsocketComponent = DaggerWaspberryWebsocketComponent.builder()
                .unsatisfiedWebsocketDependenciesModule(new UnsatisfiedWebsocketDependenciesModule(
                        instance.getConnectionUri(),
                        Resources.WEBSOCKET_RECONNECT_TIMEOUT,
                        instance.getModels(),
                        instance.getHandlers()
                )).build();
    }

    private URI getConnectionUri() {
        try {
            return new URI(Url.DEVICE.getUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<MessageHandler> getHandlers() {
        logger.debug("loading message handlers");
        List<MessageHandler> messageHandlers = new ArrayList<>();

        Reflections reflections = new Reflections(Resources.WEBSOCKET_HANDLER_DIR);
        Set<Class<? extends MessageHandler>> handlerClasses = reflections.getSubTypesOf(MessageHandler.class);

        try {
            for (Class<? extends MessageHandler> handlerClass : handlerClasses) {
                MessageHandler messageHandler = handlerClass.newInstance();
                messageHandlers.add(messageHandler);

                logger.debug("Adding {} to handle {}", handlerClass.getSimpleName(),
                        messageHandler.handlesMessageOfType().getSimpleName());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return messageHandlers;
    }

    private List<MessageModel> getModels() {
        logger.debug("loading message models");

        List<MessageModel> messageModels = new ArrayList<>();

        Reflections reflections = new Reflections(Resources.WEBSOCKET_MODELS_DIR);
        Set<Class<? extends MessageModel>> modelClasses = reflections.getSubTypesOf(MessageModel.class);

        try {
            for (Class<? extends MessageModel> modelClass : modelClasses) {
                MessageModel messageModel = modelClass.newInstance();
                messageModels.add(messageModel);

                logger.debug("Found {} model for {} type messgae", modelClass.getSimpleName(),
                        messageModel.modelName());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return messageModels;
    }

    public static WaspberrySocketManager getSocketManager() {
        return instance.waspberryWebsocketComponent.getWaspberrySocketManager();
    }
}

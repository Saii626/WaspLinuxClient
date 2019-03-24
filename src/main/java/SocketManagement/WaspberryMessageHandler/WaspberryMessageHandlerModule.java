package SocketManagement.WaspberryMessageHandler;

import Resources.Resources;
import SocketManagement.WaspberryWebsocket.WaspberryWebsocketScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Module
public class WaspberryMessageHandlerModule {

    private static Logger logger = LoggerFactory.getLogger(WaspberryMessageHandlerModule.class);

    @Provides
    @WaspberryWebsocketScope
    static WaspberryMessageHandler getHandler(Gson gson, List<MessageHandler> list) {
        return new WaspberryMessageHandler(gson, list);
    }

    @Provides
    @WaspberryWebsocketScope
    static Gson getGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .registerTypeAdapter(WaspberryMessage.class, new WaspberryMessageDeserializer())
                .create();
    }

    @Provides
    @WaspberryWebsocketScope
    static  List<MessageHandler> getHandlers() {
        logger.debug("loading message handlers");
        List<MessageHandler> messageHandlers = new ArrayList<>();

        Reflections reflections = new Reflections(Resources.WEBSOCKET_HANDLER_DIR);
        Set<Class<? extends MessageHandler>> handlerClasses = reflections.getSubTypesOf(MessageHandler.class);

        try {
            for (Class<? extends MessageHandler> handlerClass : handlerClasses) {
                MessageHandler messageHandler = handlerClass.newInstance();
                logger.debug("Adding {} to handle {}", handlerClass.getSimpleName(),
                        messageHandler.handlesMessageOfType().getSimpleName());
                messageHandlers.add(messageHandler);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return messageHandlers;
    }
}

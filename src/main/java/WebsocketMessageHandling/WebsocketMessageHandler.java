package WebsocketMessageHandling;

import Resources.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebsocketMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(WebsocketMessageHandler.class.getSimpleName());
    private static Gson gson;
    private static List<MessageHandler> messageHandlers;

    static  {
        logger.debug("Initializing WebsocketMessageHandler");

        try {
            messageHandlers = loadMessageHandlers();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }


        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .registerTypeAdapter(WebsocketMessage.class, new WebsocketMessageDeserializer())
                .create();

    }

    public static void handleMessage(String message) {

        WebsocketMessage websocketMessage = gson.fromJson(message, WebsocketMessage.class);
        Class payloadClass = websocketMessage.getType();

        MessageModel messageModel = (MessageModel) gson.fromJson(websocketMessage.getPayload(), payloadClass);

        for (MessageHandler handler : messageHandlers) {
            if (handler.handlesMessageOfType().equals(payloadClass)) {
                logger.debug("Found {} for handling message", handler.getClass().getSimpleName());
                handler.onMessage(messageModel);
            }
        }
    }


    private static List<MessageHandler> loadMessageHandlers() throws IllegalAccessException, InstantiationException {
        logger.debug("loading message handlers");
        List<MessageHandler> messageHandlers = new ArrayList<>();

        Reflections reflections = new Reflections(Resources.WEBSOCKET_HANDLER_DIR);
        Set<Class<? extends MessageHandler>> handlerClasses = reflections.getSubTypesOf(MessageHandler.class);

        for (Class<? extends MessageHandler> handlerClass : handlerClasses) {
            MessageHandler messageHandler = handlerClass.newInstance();
            logger.debug("Adding {} to handle {}", handlerClass.getSimpleName(), messageHandler.handlesMessageOfType().getSimpleName());
            messageHandlers.add(messageHandler);
        }

        return messageHandlers;
    }
}

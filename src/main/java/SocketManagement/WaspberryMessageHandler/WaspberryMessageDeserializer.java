package SocketManagement.WaspberryMessageHandler;

import Resources.Resources;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class WaspberryMessageDeserializer implements JsonDeserializer<WaspberryMessage> {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Override
    public WaspberryMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String type = json.getAsJsonObject().get("type").getAsString();

        logger.debug("found {} payload", type);

        try {
            Class classOfType = Class.forName(String.format("%s.%s", Resources.WEBSOCKET_MODELS_DIR, type));

            WaspberryMessage message = new WaspberryMessage();
            message.setType(classOfType);
            message.setPayload(json.getAsJsonObject().get("payload"));

            return message;
        } catch (ClassNotFoundException e) {
            logger.error("Unable to find model of {}", type);
//            e.printStackTrace();
        }
        return null;
    }
}

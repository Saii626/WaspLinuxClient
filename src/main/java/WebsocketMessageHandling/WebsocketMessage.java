package WebsocketMessageHandling;

import com.google.gson.JsonElement;

public class WebsocketMessage<T> {

    private Class<T> type;
    private JsonElement payload;

    public Class<T> getType() {
        return type;
    }

    public JsonElement getPayload() {
        return payload;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public void setPayload(JsonElement payload) {
        this.payload = payload;
    }
}

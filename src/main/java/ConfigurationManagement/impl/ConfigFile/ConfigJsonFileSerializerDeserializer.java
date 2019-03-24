package ConfigurationManagement.impl.ConfigFile;

import ConfigurationManagement.ConfigKey;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;

public class ConfigJsonFileSerializerDeserializer implements JsonDeserializer<ConfigJsonFile>, JsonSerializer<ConfigJsonFile> {

    @Override
    public ConfigJsonFile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
         ConfigJsonFile jsonFile = new ConfigJsonFile();

         for (ConfigKey key : ConfigKey.values()) {
             String keyName = key.getKey();

             JsonElement element = json.getAsJsonObject().get(keyName);

             jsonFile.addConfig(key, element);
         }

         return jsonFile;
    }

    @Override
    public JsonElement serialize(ConfigJsonFile src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject element = new JsonObject();

        for (Map.Entry<ConfigKey, JsonElement> entry : src.getConfigMap().entrySet()) {
            element.add(entry.getKey().getKey(), entry.getValue());
        }

        return element;
    }
}

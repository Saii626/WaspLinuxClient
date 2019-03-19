package CommandLineArgsParser;

import ConfigurationManagement.ConfigKey;

import java.util.HashMap;
import java.util.Map;

public class CommandConfigMapper {

    private Map<CommandLineKey, ConfigKey> mapper;

    public CommandConfigMapper() {
        mapper = new HashMap<>();

        mapper.put(CommandLineKey.b, ConfigKey.BASE_URL);
        mapper.put(CommandLineKey.t, ConfigKey.TEST);
    }

    public ConfigKey getConfigKeyFor(CommandLineKey key) {
        return mapper.get(key);
    }

}

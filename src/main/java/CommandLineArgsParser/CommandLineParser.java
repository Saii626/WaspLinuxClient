package CommandLineArgsParser;

import ConfigurationManagement.ConfigKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandLineParser {

    private Map<CommandLineKey, String> map;
    private CommandConfigMapper commandConfigMapper;

    public CommandLineParser(String[] args) throws IllegalArgumentException{

        this.map = new HashMap<>();
        this.commandConfigMapper = new CommandConfigMapper();

        CommandLineKey key = null;
        StringBuilder val = new StringBuilder();

        for (String str : args) {
            if (str.charAt(0) == '-') {
                if (key!=null) {
                    map.put(key, val.toString().trim());
                }

                key = CommandLineKey.valueOf(str.substring(1));
                val = new StringBuilder();

            } else {
                val.append(str);
                val.append(" ");
            }
        }
    }

    public String get(CommandLineKey key) {
        return map.get(key);
    }

    public Set<Map.Entry<CommandLineKey, String>> getAllArguments() {
        return map.entrySet();
    }

    public ConfigKey getConfigKey(CommandLineKey commandLineKey) {
        return commandConfigMapper.getConfigKeyFor(commandLineKey);
    }

}

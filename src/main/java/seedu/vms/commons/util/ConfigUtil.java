package seedu.vms.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

import seedu.vms.commons.core.Config;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {
    /**
     * Loads the specified config file.
     *
     * @param configFilePath - the path of the config file to load.
     */
    public static Config readConfig(Path configFilePath) throws IOException {
        Objects.requireNonNull(configFilePath);
        return JsonUtil.deserializeFromFile(configFilePath, Config.class);
    }


    /**
     * Saves the specified config instance to the specified file path.
     *
     * @param config - the config instance to save.
     * @param configFilePath - the path to save the config file to.
     */
    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        Objects.requireNonNull(config);
        Objects.requireNonNull(configFilePath);
        FileUtil.createIfMissing(configFilePath);
        JsonUtil.serializeToFile(configFilePath, config);
    }

}

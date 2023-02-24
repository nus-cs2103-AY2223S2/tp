package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * The singleton instance that wraps around the {@code ConfigUtil} class.
 */
public class ConfigHelper {
    /**
     * The singleton instance of this class.
     */
    public static final ConfigHelper INSTANCE;

    static {
        INSTANCE = new ConfigHelper();
    }

    private ConfigHelper() {
    }

    /**
     * @see ConfigUtil#readConfig(Path)
     */
    public Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return ConfigUtil.readConfig(configFilePath);
    }

    /**
     * @see ConfigUtil#saveConfig(Config, Path)
     */
    public void saveConfig(Config config, Path configFilePath) throws IOException {
        ConfigUtil.saveConfig(config, configFilePath);
    }
}

package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    /**
     * Reads in the {@code Config} located at {@code configFilePath}.
     *
     * @param configFilePath File to read from.
     * @return {@code Config} if any.
     * @throws DataConversionException If there is an error converting data.
     */
    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    /**
     * Saves {@code Config} to the file at {@code configFilePath}.
     *
     * @param config Config to be saved.
     * @param configFilePath File path for saving.
     * @throws IOException If I/O operations fail or get interrupted.
     */
    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}

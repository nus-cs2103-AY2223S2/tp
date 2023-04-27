package vimification;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.application.Application;
import vimification.common.core.LogsCenter;
import vimification.common.util.FileUtil;

/**
 * Represents the parsed command-line parameters given to the application.
 */
public class AppParameters {

    private static final Logger LOGGER = LogsCenter.getLogger(AppParameters.class);

    private Path configPath;

    public Path getConfigPath() {
        return configPath;
    }

    public void setConfigPath(Path configPath) {
        this.configPath = configPath;
    }

    /**
     * Parses the application command-line parameters.
     *
     * @param parameters the application command-line parameters
     * @return an {@code AppParameters} instance that contains relevant parameters for the
     *         application
     */
    public static AppParameters parse(Application.Parameters parameters) {
        Map<String, String> namedParameters = parameters.getNamed();
        String configPathParameter = namedParameters.get("config");
        if (configPathParameter != null && !FileUtil.isValidPath(configPathParameter)) {
            LOGGER.warning("Invalid config path "
                    + configPathParameter
                    + ". Using default config path.");
            configPathParameter = null;
        }
        Path configPath = configPathParameter == null ? null : Path.of(configPathParameter);
        AppParameters appParameters = new AppParameters();
        appParameters.setConfigPath(configPath);
        return appParameters;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AppParameters)) {
            return false;
        }
        AppParameters otherAppParameters = (AppParameters) other;
        return Objects.equals(configPath, otherAppParameters.configPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configPath);
    }
}

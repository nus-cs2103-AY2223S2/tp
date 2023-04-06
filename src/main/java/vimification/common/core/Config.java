package vimification.common.core;

import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Config values used by the application.
 */
public class Config {

    public static final Path DEFAULT_CONFIG_FILE = Path.of(".vimification", "config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;
    private Path userPrefsFilePath = Path.of(".vimification", "preferences.json");

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Path getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    public void setUserPrefsFilePath(Path userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { // this handles null as well.
            return false;
        }
        Config otherConfig = (Config) other;
        return Objects.equals(logLevel, otherConfig.logLevel)
                && Objects.equals(userPrefsFilePath, otherConfig.userPrefsFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logLevel, userPrefsFilePath);
    }

    @Override
    public String toString() {
        return "Config [logLevel=" + logLevel + ", userPrefsFilePath=" + userPrefsFilePath + "]";
    }
}

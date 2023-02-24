package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    private static final Config DEFAULT_CONFIG = new Config();

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void setLogLevel_validLevel_success() {
        Config defaultConfig = new Config();
        defaultConfig.setLogLevel(Level.INFO);
        assertEquals(defaultConfig.getLogLevel(), Level.INFO);
    }

    @Test
    public void setUserPrefsFilePath_validPath_success() {
        Config defaultConfig = new Config();
        Path defaultPath = Paths.get("preferences.json");
        defaultConfig.setUserPrefsFilePath(defaultPath);
        assertEquals(defaultConfig.getUserPrefsFilePath(), defaultPath);
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(DEFAULT_CONFIG, DEFAULT_CONFIG);
    }

    @Test
    public void equals_notConfig_false() {
        assertNotEquals(ConfigTest.DEFAULT_CONFIG, null);
    }

    @Test
    public void hashCode_validConfig_success() {
        Config defaultConfig = new Config();
        Path defaultPath = Paths.get("preferences.json");
        defaultConfig.setUserPrefsFilePath(defaultPath);
        defaultConfig.setLogLevel(Level.INFO);
        assertEquals(defaultConfig.hashCode(), Objects.hash(Level.INFO, defaultPath));
    }

    @Test
    public void equals_sameLogLevelAndPath_true() {
        Config defaultConfig = new Config();
        Path defaultPath = Paths.get("preferences.json");
        defaultConfig.setUserPrefsFilePath(defaultPath);
        defaultConfig.setLogLevel(Level.INFO);

        Config otherConfig = new Config();
        otherConfig.setUserPrefsFilePath(defaultPath);
        otherConfig.setLogLevel(Level.INFO);

        assertEquals(defaultConfig, otherConfig);
    }

}

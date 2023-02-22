package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
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
        Config defaultConfig = new Config();
        assertEquals(defaultConfig, defaultConfig);
    }

    @Test
    public void equals_notConfig_false() {
        Config defaultConfig = new Config();
        assertNotEquals(defaultConfig, null);
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

package seedu.recipe.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {
    private static final Path USER_PREFS_FILE_PATH = Paths.get("preferences.json");

    @Test
    public void test_getLogLevel_initialInfo() {
        Config defaultConfig = new Config();
        assertEquals(defaultConfig.getLogLevel(), Level.INFO);
    }

    @Test
    public void test_setLogLevel() {
        Config defaultConfig = new Config();
        defaultConfig.setLogLevel(Level.WARNING);
        assertEquals(defaultConfig.getLogLevel(), Level.WARNING);
    }

    @Test
    public void test_getUserPrefsPath() {
        assertEquals(USER_PREFS_FILE_PATH, new Config().getUserPrefsFilePath());
    }

    @Test
    public void test_setUserPrefsFilePath() {
        Path newPath = Paths.get("null path");
        Config defaultConfig = new Config();
        defaultConfig.setUserPrefsFilePath(newPath);
        assertEquals(defaultConfig.getUserPrefsFilePath(), newPath);
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertEquals(defaultConfig, defaultConfig);
    }

    @Test
    public void test_hashCode() {
        assertEquals(Objects.hash(Level.INFO, USER_PREFS_FILE_PATH), new Config().hashCode());
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }
}

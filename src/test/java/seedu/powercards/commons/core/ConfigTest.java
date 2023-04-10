package seedu.powercards.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void hashCode_equalConfigObjects_success() {
        // Create two equal Config objects
        Config config1 = new Config();
        Config config2 = new Config();

        // Set the same values for both config objects
        Level logLevel = Level.INFO;
        Path userPrefsFilePath = Paths.get("preferences.json");
        config1.setLogLevel(logLevel);
        config1.setUserPrefsFilePath(userPrefsFilePath);
        config2.setLogLevel(logLevel);
        config2.setUserPrefsFilePath(userPrefsFilePath);

        // Check that the hash codes are the same for the two config objects
        assertEquals(config1.hashCode(), config2.hashCode());
    }

    @Test
    public void hashCode_unequalConfigObjects_failure() {
        // Create two Config objects with different values
        Config config1 = new Config();
        Config config2 = new Config();
        config1.setLogLevel(Level.SEVERE);
        config1.setUserPrefsFilePath(Paths.get("preferences1.json"));
        config2.setLogLevel(Level.WARNING);
        config2.setUserPrefsFilePath(Paths.get("preferences2.json"));

        // Check that the hash codes are different for the two config objects
        assertNotEquals(config1.hashCode(), config2.hashCode());
    }


}

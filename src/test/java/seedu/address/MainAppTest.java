package seedu.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Config;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;

public class MainAppTest {
    private final MainApp mainApp = new MainApp();
    private final Path invalidJsonFile =
            Paths.get("src", "test", "data", "ConfigUtilTest", "NotJsonFormatConfig.json");


    @Test
    public void initConfig_nullFilePath_returnsDefaultConfig() {
        initConfigHelper(null);
    }

    @Test
    public void initConfig_missingFile_returnsDefaultConfig() {
        Path missing = Paths.get("missingConfig.json");
        initConfigHelper(missing);
        new File(missing.toUri()).delete();
    }

    @Test
    public void initConfig_invalidFormatFile_returnsDefaultConfig() throws IOException {
        initConfigHelper(invalidJsonFile);

        // Resets file
        Files.write(invalidJsonFile, Arrays.asList("this file is not in json format!"));
    }

    @Test
    public void initPrefs_success() {
        UserPrefs testPrefs = mainApp.initPrefs(new JsonUserPrefsStorage(Paths.get("preferences.json")));

        Config config = mainApp.initConfig(Paths.get("config.json"));
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = mainApp.initPrefs(userPrefsStorage);

        assertEquals(testPrefs, userPrefs);
    }

    @Test
    public void initPrefs_invalidFormatFile_returnsDefaultPrefs() {
        UserPrefs testPrefs = mainApp.initPrefs(new JsonUserPrefsStorage(invalidJsonFile));
        UserPrefs expectedPrefs = new UserPrefs();
        assertEquals(testPrefs, expectedPrefs);
    }

    public void initConfigHelper(Path filePath) {
        Config testConfig = mainApp.initConfig(filePath);
        assertNotNull(testConfig);
        Path actualFilePath = Paths.get("config.json");
        Config expectedConfig = mainApp.initConfig(actualFilePath);
        assertEquals(expectedConfig, testConfig);
    }
}

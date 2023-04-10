package vimification;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import vimification.common.core.Config;
import vimification.common.core.LogsCenter;
import vimification.common.exceptions.DataConversionException;
import vimification.common.util.JsonUtil;
import vimification.common.util.StringUtil;
import vimification.internal.Logic;
import vimification.internal.LogicManager;
import vimification.model.CommandStack;
import vimification.model.MacroMap;
import vimification.model.TaskList;
import vimification.model.UserPrefs;
import vimification.storage.JsonMacroMapStorage;
import vimification.storage.JsonTaskListStorage;
import vimification.storage.JsonUserPrefsStorage;
import vimification.storage.Storage;
import vimification.storage.StorageManager;
import vimification.storage.UserPrefsStorage;
import vimification.ui.Ui;
import vimification.ui.UiManager;

/**
 * This class sets up the necessary code for the GUI to access the back-end.
 */
public class MainApp extends Application {

    private static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    private Ui ui;

    @Override
    public void init() throws Exception {
        LOGGER.info("========== [ Initializing Vimification ] ==========");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        Config config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initUserPrefs(userPrefsStorage);
        Storage storage = new StorageManager(
                new JsonTaskListStorage(userPrefs.getTaskListFilePath()),
                new JsonMacroMapStorage(userPrefs.getMacroMapFilePath()),
                userPrefsStorage);

        Logic logic = new LogicManager(
                initTaskList(storage),
                initMacroMap(storage),
                initCommandStack(),
                storage);
        ui = new UiManager(logic);
        initLogging(config);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.start(primaryStage);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}, or a new {@code Config}
     * with default configuration if {@code configFilePath} is null.
     */
    private Config initConfig(Path configFilePath) {
        Path configFilePathUsed;
        if (configFilePath != null) {
            LOGGER.info("Custom config file specified: " + configFilePath);
            configFilePathUsed = configFilePath;
        } else {
            configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
        }
        LOGGER.info("Using config file: " + configFilePathUsed);
        Config config;
        try {
            config = JsonUtil.readJsonFile(configFilePathUsed, Config.class);
        } catch (IOException e) {
            LOGGER.warning("Problem while reading config from file...");
            config = new Config();
        }
        try {
            JsonUtil.saveJsonFile(config, configFilePathUsed);
        } catch (IOException ex) {
            LOGGER.warning("Failed to save config: " + StringUtil.getDetails(ex));
        }
        return config;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a
     * new {@code UserPrefs} with default configuration if errors occur when reading from the file.
     */
    private UserPrefs initUserPrefs(UserPrefsStorage storage) {
        UserPrefs userPrefs;
        try {
            userPrefs = storage.readUserPrefs();
        } catch (IOException ex) {
            LOGGER.warning("Problem while reading user prefs from file...");
            userPrefs = new UserPrefs();
        }
        try {
            storage.saveUserPrefs(userPrefs);
        } catch (IOException ex) {
            LOGGER.warning("Failed to save user prefs: " + StringUtil.getDetails(ex));
        }
        return userPrefs;
    }

    /**
     * Returns a {@code TaskList} using the file at {@code storage}'s task list file path, or a new
     * {@code TaskList} with default configuration if errors occur when reading from the file.
     */
    private TaskList initTaskList(Storage storage) {
        TaskList taskList;
        try {
            taskList = storage.readTaskList();
        } catch (DataConversionException ex) {
            LOGGER.warning("Task list is in invalid format: " + StringUtil.getDetails(ex));
            taskList = new TaskList();
        } catch (IOException ex) {
            LOGGER.warning("Problem while reading task list from the file...");
            taskList = new TaskList();
        }
        try {
            storage.saveTaskList(taskList);
        } catch (IOException ex) {
            LOGGER.warning("Failed to save task list: " + StringUtil.getDetails(ex));
        }
        return taskList;
    }

    /**
     * Returns a {@code MacroMap} using the file at {@code storage}'s macro map file path, or a new
     * {@code MacroMap} with default configuration if errors occur when reading from the file.
     */
    private MacroMap initMacroMap(Storage storage) {
        MacroMap macroMap;
        try {
            macroMap = storage.readMacroMap();
        } catch (IOException ex) {
            LOGGER.warning("Problem while reading macro map from file...");
            macroMap = new MacroMap();
        }
        try {
            storage.saveMacroMap(macroMap);
        } catch (IOException ex) {
            LOGGER.warning("Failed to save macro map: " + StringUtil.getDetails(ex));
        }
        return macroMap;
    }

    private CommandStack initCommandStack() {
        return new CommandStack();
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }
}

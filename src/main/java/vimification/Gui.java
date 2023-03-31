package vimification;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import vimification.commons.core.Config;
import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.DataConversionException;
import vimification.commons.util.JsonUtil;
import vimification.commons.util.StringUtil;
import vimification.internal.Logic;
import vimification.internal.LogicManager;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.MacroMap;
import vimification.model.TaskListRef;
import vimification.model.UserPrefs;
import vimification.model.util.SampleDataUtil;
import vimification.storage.JsonTaskListRefStorage;
import vimification.storage.JsonMacroMapStorage;
import vimification.storage.JsonUserPrefsStorage;
import vimification.storage.TaskListRefStorage;
import vimification.storage.Storage;
import vimification.storage.StorageManager;
import vimification.storage.UserPrefsStorage;
import vimification.ui.MainScreen;
import vimification.ui.Ui;
import vimification.ui.UiManager;

/**
 * This class sets up the necessary code for the GUI to access the back-end.
 */
public class Gui extends Application {

    private static final Logger LOGGER = LogsCenter.getLogger(Gui.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Config config;

    @Override
    public void init() throws Exception {
        LOGGER.info("========== [ Initializing Vimification ] ==========");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initUserPrefs(userPrefsStorage);

        TaskListRefStorage logicTaskListStorage =
                new JsonTaskListRefStorage(userPrefs.getLogicTaskListFilePath());
        JsonMacroMapStorage macroMapStorage =
                new JsonMacroMapStorage(userPrefs.getMacroMapFilePath());

        storage = new StorageManager(logicTaskListStorage, macroMapStorage, userPrefsStorage);
        initLogging(config);
        logic = new LogicManager(
                initTaskListRef(storage),
                initMacroMap(storage),
                initCommandStack(),
                storage);
        ui = new UiManager(logic);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.start(primaryStage);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and
     * {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book
     * is not found, or an empty address book will be used instead if errors occur when reading
     * {@code storage}'s address book.
     */
    private TaskListRef initTaskListRef(Storage storage) {
        TaskListRef initialData;
        try {
            initialData = storage.readTaskListRef();
        } catch (DataConversionException e) {
            LOGGER.warning("Data file not in the correct format.");
            initialData = new TaskListRef(new ArrayList<>());
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file.");
            initialData = new TaskListRef(new ArrayList<>());
        }
        return initialData;
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    protected Config initConfig(Path configFilePath) {
        Path configFilePathUsed;
        if (configFilePath != null) {
            LOGGER.info("Custom config file specified: " + configFilePath);
            configFilePathUsed = configFilePath;
        } else {
            configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
        }

        LOGGER.info("Using config file: " + configFilePathUsed);
        Config initializedConfig;
        try {
            initializedConfig = JsonUtil.readJsonFile(configFilePathUsed, Config.class);
        } catch (IOException e) {
            LOGGER.warning("Config file at " + configFilePathUsed
                    + " is not in the correct format."
                    + " Using default config.");
            initializedConfig = new Config();
        }

        try {
            JsonUtil.saveJsonFile(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            LOGGER.warning("Failed to save file: " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }


    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a
     * new {@code UserPrefs} with default configuration if errors occur when reading from the file.
     */
    protected UserPrefs initUserPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        LOGGER.info("Using pref file: " + prefsFilePath);
        UserPrefs initializedPrefs;
        try {
            initializedPrefs = storage.readUserPrefs();
        } catch (IOException e) {
            LOGGER.warning("UserPrefs file at " + prefsFilePath
                    + " is not in the correct format."
                    + " Using default user prefs");
            initializedPrefs = new UserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            LOGGER.warning("Failed to save file: " + StringUtil.getDetails(e));
        }
        return initializedPrefs;
    }

    private MacroMap initMacroMap(Storage storage) {
        MacroMap initialData;
        try {
            initialData = storage.readMacroMap();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file.");
            initialData = new MacroMap();
        }
        return initialData;
    }

    private CommandStack initCommandStack() {
        return new CommandStack();
    }
}

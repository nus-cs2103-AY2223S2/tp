package vimification;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import vimification.commons.core.Config;
import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.DataConversionException;
import vimification.commons.util.ConfigUtil;
import vimification.commons.util.StringUtil;
import vimification.logic.Logic;
import vimification.logic.LogicManager;
import vimification.model.LogicTaskList;
import vimification.model.ReadOnlyUserPrefs;
import vimification.model.UserPrefs;
import vimification.storage.JsonLogicTaskListStorage;
import vimification.storage.JsonUserPrefsStorage;
import vimification.storage.LogicTaskListStorage;
import vimification.storage.Storage;
import vimification.storage.StorageManager;
import vimification.storage.UserPrefsStorage;
import vimification.taskui.Ui;
import vimification.taskui.UiManager;

/**
 * This class sets up the necessary code for the GUI to access the back-end.
 */
public class Gui extends Application {
    private static final Logger logger = LogsCenter.getLogger(Gui.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info(
                "=============================[ Initializing TaskPlanner ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        LogicTaskListStorage vimificationStorage =
                new JsonLogicTaskListStorage(userPrefs.getTaskListFilePath());
        storage = new StorageManager(vimificationStorage, userPrefsStorage);

        initLogging(config);

        logic = new LogicManager(initLogicTaskList(storage, userPrefs), storage);
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
    private LogicTaskList initLogicTaskList(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<LogicTaskList> addressBookOptional;
        LogicTaskList initialData;
        try {
            addressBookOptional = storage.readLogicTaskList();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(LogicTaskList::new);
        } catch (DataConversionException e) {
            logger.warning(
                    "Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new LogicTaskList();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new LogicTaskList();
        }
        return initialData;
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning(
                    "Config file at " + configFilePathUsed + " is not in the correct format. "
                            + "Using default config properties");
            initializedConfig = new Config();
        }

        // Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }


    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a
     * new {@code UserPrefs} with default configuration if errors occur when reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }
}

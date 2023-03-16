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
import vimification.model.TaskPlanner;
import vimification.model.Model;
import vimification.model.ModelManager;
import vimification.model.ReadOnlyTaskPlanner;
import vimification.model.ReadOnlyUserPrefs;
import vimification.model.UserPrefs;
import vimification.model.util.SampleDataUtil;
import vimification.storage.TaskPlannerStorage;
import vimification.storage.JsonTaskPlannerStorage;
import vimification.storage.JsonUserPrefsStorage;
import vimification.storage.Storage;
import vimification.storage.StorageManager;
import vimification.storage.UserPrefsStorage;
import vimification.taskui.Ui;
import vimification.taskui.UiManager;

public class Gui extends Application {

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;


    private static final Logger logger = LogsCenter.getLogger(Gui.class);

    @Override
    public void init() throws Exception {
        logger.info(
                "=============================[ Initializing TaskPlanner ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        // TODO: Temporary fix until Jiayue finishes implementation.
        // TaskPlannerStorage addressBookStorage = null;
        TaskPlannerStorage addressBookStorage =
                new JsonTaskPlannerStorage(userPrefs.getTaskListFilePath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

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
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTaskPlanner> addressBookOptional;
        ReadOnlyTaskPlanner initialData;
        try {
            addressBookOptional = storage.readTaskList();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData =
                    addressBookOptional.orElseGet(SampleDataUtil::getSampleReadOnlyTaskPlanner);
        } catch (DataConversionException e) {
            logger.warning(
                    "Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new TaskPlanner();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new TaskPlanner();
        }

        return new ModelManager(initialData, userPrefs);
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

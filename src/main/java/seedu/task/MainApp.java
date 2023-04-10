package seedu.task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.task.commons.core.Config;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.core.Version;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.commons.util.ConfigUtil;
import seedu.task.commons.util.StringUtil;
import seedu.task.logic.Logic;
import seedu.task.logic.LogicManager;
import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.Planner;
import seedu.task.model.ReadOnlyPlanner;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.ReadOnlyUserPrefs;
import seedu.task.model.TaskBook;
import seedu.task.model.UserPrefs;
import seedu.task.model.util.SampleDataUtil;
import seedu.task.storage.JsonPlannerStorage;
import seedu.task.storage.JsonTaskBookStorage;
import seedu.task.storage.JsonUserPrefsStorage;
import seedu.task.storage.PlannerStorage;
import seedu.task.storage.Storage;
import seedu.task.storage.StorageManager;
import seedu.task.storage.TaskBookStorage;
import seedu.task.storage.UserPrefsStorage;
import seedu.task.ui.Ui;
import seedu.task.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing TaskBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TaskBookStorage taskBookStorage = new JsonTaskBookStorage(userPrefs.getTaskBookFilePath());
        PlannerStorage plannerStorage = new JsonPlannerStorage(userPrefs.getPlannerFilePath());
        storage = new StorageManager(taskBookStorage, userPrefsStorage, plannerStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s task book and {@code userPrefs}. <br>
     * The data from the sample task book will be used instead if {@code storage}'s task book is not found,
     * or an empty task book will be used instead if errors occur when reading {@code storage}'s task book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTaskBook> taskBookOptional;
        ReadOnlyTaskBook initialData;
        Optional<ReadOnlyPlanner> plannerOptional;
        ReadOnlyPlanner plannerData;
        try {
            taskBookOptional = storage.readTaskBook();
            if (!taskBookOptional.isPresent()) {
                logger.info("Task Data file not found. Will be starting with a sample TaskBook");
            }
            initialData = taskBookOptional.orElseGet(SampleDataUtil::getSampleTaskBook);

        } catch (DataConversionException e) {
            logger.warning("Task data file not in the correct format. Will be starting with an empty TaskBook");
            initialData = new TaskBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the task file. Will be starting with an empty TaskBook");
            initialData = new TaskBook();
        }

        try {
            plannerOptional = storage.readPlanner();
            if (!plannerOptional.isPresent()) {
                logger.info("Planner Data file not found. Will be starting with a sample Planner");
            }
            plannerData = plannerOptional.orElseGet(SampleDataUtil::getSamplePlanner);
        } catch (DataConversionException e) {
            logger.warning("Planner data file not in the correct format. Will be starting with an empty Planner");
            plannerData = new Planner();
        } catch (IOException e) {
            logger.warning("Problem while reading from the planner file. Will be starting with an empty Planner");
            plannerData = new Planner();
        }

        return new ModelManager(initialData, userPrefs, plannerData);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
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
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
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
            logger.warning("Problem while reading from the file. Will be starting with an empty TaskBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting TaskBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Task Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

package trackr;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import trackr.commons.core.Config;
import trackr.commons.core.LogsCenter;
import trackr.commons.core.Version;
import trackr.commons.exceptions.DataConversionException;
import trackr.commons.util.ConfigUtil;
import trackr.commons.util.StringUtil;
import trackr.logic.Logic;
import trackr.logic.LogicManager;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.ReadOnlyUserPrefs;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.UserPrefs;
import trackr.model.util.SampleDataUtil;
import trackr.storage.JsonTrackrStorage;
import trackr.storage.JsonUserPrefsStorage;
import trackr.storage.Storage;
import trackr.storage.StorageManager;
import trackr.storage.TrackrStorage;
import trackr.storage.UserPrefsStorage;
import trackr.ui.Ui;
import trackr.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Trackr ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TrackrStorage trackrStorage = new JsonTrackrStorage(userPrefs.getTrackrFilePath());
        storage = new StorageManager(trackrStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s supplier list,
     * task list and {@code userPrefs}. <br>
     * The data from the sample supplier list and task list will be used instead if {@code storage}'s supplier list
     * and task list is not found,
     * or an empty supplier list and task list will be used instead if errors occur when reading {@code storage}'s
     * supplier list and task list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlySupplierList> supplierListOptional;
        Optional<ReadOnlyTaskList> taskListOptional;
        ReadOnlySupplierList initialSupplierList;
        ReadOnlyTaskList initialTaskList;

        try {
            supplierListOptional = storage.readSupplierList();
            if (!supplierListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample SupplierList");
            }
            initialSupplierList = supplierListOptional.orElseGet(SampleDataUtil::getSampleSupplierList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty SupplierList");
            initialSupplierList = new SupplierList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty SupplierList");
            initialSupplierList = new SupplierList();
        }

        try {
            taskListOptional = storage.readTaskList();
            if (!taskListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample TaskList");
            }
            initialTaskList = taskListOptional.orElseGet(SampleDataUtil::getSampleTaskList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty TaskList");
            initialTaskList = new TaskList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TaskList");
            initialTaskList = new TaskList();
        }

        return new ModelManager(initialSupplierList, initialTaskList, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Trackr");
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
        logger.info("Starting Trackr " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Trackr ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

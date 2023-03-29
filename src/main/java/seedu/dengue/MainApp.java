package seedu.dengue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.dengue.commons.core.Config;
import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.core.Version;
import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.commons.util.ConfigUtil;
import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.logic.Logic;
import seedu.dengue.logic.LogicManager;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.ReadOnlyUserPrefs;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.util.SampleDataUtil;
import seedu.dengue.storage.CsvDengueHotspotStorage;
import seedu.dengue.storage.DengueHotspotStorage;
import seedu.dengue.storage.JsonUserPrefsStorage;
import seedu.dengue.storage.Storage;
import seedu.dengue.storage.StorageManager;
import seedu.dengue.storage.UserPrefsStorage;
import seedu.dengue.ui.Ui;
import seedu.dengue.ui.UiManager;

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
        logger.info("=============================[ Initializing DengueHotspotTracker ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        DengueHotspotStorage dengueHotspotStorage = new CsvDengueHotspotStorage(userPrefs
                .getDengueHotspotTrackerFilePath());
        storage = new StorageManager(dengueHotspotStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Dengue Hotspot Tracker and {@code userPrefs}.
     * The data from the sample Dengue Hotspot Tracker will be used instead if {@code storage}'s Dengue Hotspot Tracker
     *  is not found, or an empty Dengue Hotspot Tracker will be used instead if errors occur when reading
     *  {@code storage}'s Dengue Hotspot Tracker.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyDengueHotspotTracker> dengueHotspotTrackerOptional;
        ReadOnlyDengueHotspotTracker initialData;
        try {
            dengueHotspotTrackerOptional = storage.readDengueHotspotTracker();
            if (!dengueHotspotTrackerOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample DengueHotspotTracker");
            }
            initialData = dengueHotspotTrackerOptional.orElseGet(SampleDataUtil::getSampleDengueHotspotTracker);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty DengueHotspotTracker");
            initialData = new DengueHotspotTracker();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty DengueHotspotTracker");
            initialData = new DengueHotspotTracker();
        }

        return new ModelManager(initialData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty DengueHotspotTracker");
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
        logger.info("Starting DengueHotspotTracker " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Program ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

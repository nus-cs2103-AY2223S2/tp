package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.LogicManagerNew;
import seedu.address.logic.LogicNew;
import seedu.address.model.ModelManagerNew;
import seedu.address.model.ModelNew;
import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.ReadOnlyUserPrefsNew;
import seedu.address.model.Ultron;
import seedu.address.model.UserPrefsNew;
import seedu.address.model.util.SampleDataUtilNew;
import seedu.address.storage.JsonUltronStorage;
import seedu.address.storage.JsonUserPrefsStorageNew;
import seedu.address.storage.StorageManagerNew;
import seedu.address.storage.StorageNew;
import seedu.address.storage.UltronStorage;
import seedu.address.storage.UserPrefsStorageNew;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManagerNew;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected LogicNew logic;
    protected StorageNew storage;
    protected ModelNew model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Ultron ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorageNew userPrefsStorage = new JsonUserPrefsStorageNew(config.getUserPrefsFilePath());
        UserPrefsNew userPrefs = initPrefs(userPrefsStorage);
        UltronStorage ultronStorage = new JsonUltronStorage(userPrefs.getUltronFilePath());
        storage = new StorageManagerNew(ultronStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManagerNew(model, storage);

        ui = new UiManagerNew(logic);
    }

    /**
     * Returns a {@code ModelManagerNew} with the data from {@code storage}'s  ultron and {@code userPrefs}. <br>
     * The data from the sample ultron will be used instead if {@code storage}'s ultron is not found,
     * or an empty ultron will be used instead if errors occur when reading {@code storage}'s ultron.
     */
    private ModelNew initModelManager(StorageNew storage, ReadOnlyUserPrefsNew userPrefs) {
        Optional<ReadOnlyUltron> ultronOptional;
        ReadOnlyUltron initialData;
        try {
            ultronOptional = storage.readUltron();
            if (!ultronOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Ultron");
            }
            initialData = ultronOptional.orElseGet(SampleDataUtilNew::getSampleUltron);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Ultron");
            initialData = new Ultron();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Ultron");
            initialData = new Ultron();
        }

        return new ModelManagerNew(initialData, userPrefs);
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
    protected UserPrefsNew initPrefs(UserPrefsStorageNew storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefsNew initializedPrefs;
        try {
            Optional<UserPrefsNew> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefsNew());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefsNew();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Ultron");
            initializedPrefs = new UserPrefsNew();
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
        logger.info("Starting Ultron " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Ultron ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

package expresslibrary;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import expresslibrary.commons.core.Config;
import expresslibrary.commons.core.LogsCenter;
import expresslibrary.commons.core.Version;
import expresslibrary.commons.exceptions.DataConversionException;
import expresslibrary.commons.util.ConfigUtil;
import expresslibrary.commons.util.StringUtil;
import expresslibrary.logic.Logic;
import expresslibrary.logic.LogicManager;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.ReadOnlyUserPrefs;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.util.SampleDataUtil;
import expresslibrary.storage.ExpressLibraryStorage;
import expresslibrary.storage.JsonExpressLibraryStorage;
import expresslibrary.storage.JsonUserPrefsStorage;
import expresslibrary.storage.Storage;
import expresslibrary.storage.StorageManager;
import expresslibrary.storage.UserPrefsStorage;
import expresslibrary.ui.Ui;
import expresslibrary.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ExpressLibrary ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ExpressLibraryStorage expressLibraryStorage = new JsonExpressLibraryStorage(
                userPrefs.getExpressLibraryFilePath());
        storage = new StorageManager(expressLibraryStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s express
     * library and {@code userPrefs}. <br>
     * The data from the sample express library will be used instead if
     * {@code storage}'s express library book is not found,
     * or an empty express library book will be used instead if errors occur when
     * reading
     * {@code storage}'s express library book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyExpressLibrary> expressLibraryOptional;
        ReadOnlyExpressLibrary initialData;
        try {
            expressLibraryOptional = storage.readExpressLibrary();
            if (!expressLibraryOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ExpressLibrary");
            }
            initialData = expressLibraryOptional.orElseGet(SampleDataUtil::getSampleExpressLibrary);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ExpressLibrary");
            initialData = new ExpressLibrary();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ExpressLibrary");
            initialData = new ExpressLibrary();
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

        // Update config file in case it was missing to begin with or there are
        // new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs
     * file path,
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ExpressLibrary");
            initializedPrefs = new UserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are
        // new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting ExpressLibrary " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ExpressLibrary ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

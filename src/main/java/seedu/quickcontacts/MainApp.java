package seedu.quickcontacts;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.quickcontacts.commons.core.Config;
import seedu.quickcontacts.commons.core.Cron;
import seedu.quickcontacts.commons.core.LogsCenter;
import seedu.quickcontacts.commons.core.Version;
import seedu.quickcontacts.commons.exceptions.DataConversionException;
import seedu.quickcontacts.commons.util.ConfigUtil;
import seedu.quickcontacts.commons.util.StringUtil;
import seedu.quickcontacts.logic.Logic;
import seedu.quickcontacts.logic.LogicManager;
import seedu.quickcontacts.logic.jobs.CheckMeetingHasPassed;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.ReadOnlyUserPrefs;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.util.SampleDataUtil;
import seedu.quickcontacts.storage.JsonQuickBookStorage;
import seedu.quickcontacts.storage.JsonUserPrefsStorage;
import seedu.quickcontacts.storage.QuickBookStorage;
import seedu.quickcontacts.storage.Storage;
import seedu.quickcontacts.storage.StorageManager;
import seedu.quickcontacts.storage.UserPrefsStorage;
import seedu.quickcontacts.ui.Ui;
import seedu.quickcontacts.ui.UiManager;

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
    protected Cron cron;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing QuickContacts ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        QuickBookStorage quickBookStorage = new JsonQuickBookStorage(userPrefs.getQuickBookFilePath());
        storage = new StorageManager(quickBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

        cron = initCron();
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s quick book and {@code userPrefs}. <br>
     * The data from the sample quick book will be used instead if {@code storage}'s quick book is not found,
     * or an empty quick book will be used instead if errors occur when reading {@code storage}'s quick book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyQuickBook> quickBookOptional;
        ReadOnlyQuickBook initialData;
        try {
            quickBookOptional = storage.readQuickBook();
            if (!quickBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample QuickBook");
            }
            initialData = quickBookOptional.orElseGet(SampleDataUtil::getSampleQuickBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty QuickBook");
            initialData = new QuickBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty QuickBook");
            initialData = new QuickBook();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    private Cron initCron() {
        Cron cron = Cron.getInstance();
        logger.info("Initialised CRON engine");
        cron.addTask(new CheckMeetingHasPassed(model), 1);

        return cron;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty QuickBook");
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
        logger.info("Starting QuickContacts " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping QuickContacts ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
            cron.stop();
            logger.info("CRON engine stopped");
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

package tfifteenfour.clipboard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import tfifteenfour.clipboard.commons.core.Config;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.commons.core.Version;
import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.commons.util.ConfigUtil;
import tfifteenfour.clipboard.commons.util.StringUtil;
import tfifteenfour.clipboard.logic.Logic;
import tfifteenfour.clipboard.logic.LogicManager;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ModelManager;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.ReadOnlyUserPrefs;
import tfifteenfour.clipboard.model.UserPrefs;
import tfifteenfour.clipboard.model.util.SampleDataUtil;
import tfifteenfour.clipboard.storage.JsonRosterStorage;
import tfifteenfour.clipboard.storage.JsonUserPrefsStorage;
import tfifteenfour.clipboard.storage.RosterStorage;
import tfifteenfour.clipboard.storage.Storage;
import tfifteenfour.clipboard.storage.StorageManager;
import tfifteenfour.clipboard.storage.UserPrefsStorage;
import tfifteenfour.clipboard.ui.Ui;
import tfifteenfour.clipboard.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);
    public static final String MESSAGE_SAMPLE = "Will be starting with a sample Roster";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;
    protected ReadOnlyRoster roster;


    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Roster ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        RosterStorage rosterStorage = new JsonRosterStorage(userPrefs.getRosterFilePath());

        storage = new StorageManager(rosterStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);
        model.setRoster(roster);
        logic = new LogicManager(model, storage);
        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyRoster> rosterOptional;
        Path sampleFilePath = userPrefs.getSampleFilePath();
        InputStream sampleResourceStream = this.getClass().getResourceAsStream("/assets/sampleRoster.json");


        ReadOnlyRoster initialData;
        try {
            rosterOptional = storage.readRoster();
            if (rosterOptional.isEmpty()) {
                logger.info("Data file not found. " + MESSAGE_SAMPLE);
                new File("data").mkdir();
            }
            initialData = rosterOptional.orElseGet(() ->
                    SampleDataUtil.getSampleRoster(sampleFilePath, sampleResourceStream));
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. " + MESSAGE_SAMPLE);
            initialData = SampleDataUtil.getSampleRoster(sampleFilePath, sampleResourceStream);
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid data detected. " + MESSAGE_SAMPLE);
            initialData = SampleDataUtil.getSampleRoster(sampleFilePath, sampleResourceStream);
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. " + MESSAGE_SAMPLE);
            initialData = SampleDataUtil.getSampleRoster(sampleFilePath, sampleResourceStream);

        }

        this.roster = initialData;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Roster");
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
        logger.info("Starting Roster " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ClIpboard ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

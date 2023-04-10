package seedu.modtrek;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.modtrek.commons.core.Config;
import seedu.modtrek.commons.core.LogsCenter;
import seedu.modtrek.commons.core.Version;
import seedu.modtrek.commons.exceptions.DataConversionException;
import seedu.modtrek.commons.util.ConfigUtil;
import seedu.modtrek.commons.util.StringUtil;
import seedu.modtrek.logic.Logic;
import seedu.modtrek.logic.LogicManager;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.ReadOnlyUserPrefs;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.util.SampleDataUtil;
import seedu.modtrek.storage.DegreeProgressionStorage;
import seedu.modtrek.storage.JsonDegreeProgressionStorage;
import seedu.modtrek.storage.JsonUserPrefsStorage;
import seedu.modtrek.storage.Storage;
import seedu.modtrek.storage.StorageManager;
import seedu.modtrek.storage.UserPrefsStorage;
import seedu.modtrek.ui.Ui;
import seedu.modtrek.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing MODTrek ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        DegreeProgressionStorage degreeProgressionStorage = new JsonDegreeProgressionStorage(userPrefs.getFilePath());
        storage = new StorageManager(degreeProgressionStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyDegreeProgression> degreeProgressionOptional;
        ReadOnlyDegreeProgression initialData;
        try {
            degreeProgressionOptional = storage.readDegreeProgression();
            if (!degreeProgressionOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ModTrek");
            }
            initialData = degreeProgressionOptional.orElseGet(SampleDataUtil::getSampleDegreeProgression);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty MODTrek");
            initialData = new DegreeProgression();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty MODTrek");
            initialData = new DegreeProgression();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty MODTrek");
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
        logger.info("Starting MODTrek " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping MODTrek ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

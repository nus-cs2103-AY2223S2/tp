package seedu.medinfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.medinfo.commons.core.Config;
import seedu.medinfo.commons.core.LogsCenter;
import seedu.medinfo.commons.core.Version;
import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.commons.util.ConfigUtil;
import seedu.medinfo.commons.util.StringUtil;
import seedu.medinfo.logic.Logic;
import seedu.medinfo.logic.LogicManager;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.ReadOnlyUserPrefs;
import seedu.medinfo.model.UserPrefs;
import seedu.medinfo.model.util.SampleDataUtil;
import seedu.medinfo.storage.JsonMedInfoStorage;
import seedu.medinfo.storage.JsonUserPrefsStorage;
import seedu.medinfo.storage.MedInfoStorage;
import seedu.medinfo.storage.Storage;
import seedu.medinfo.storage.StorageManager;
import seedu.medinfo.storage.UserPrefsStorage;
import seedu.medinfo.ui.Ui;
import seedu.medinfo.ui.UiManager;

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
        logger.info("=============================[ Initializing MedInfo ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        MedInfoStorage medInfoStorage = new JsonMedInfoStorage(userPrefs.getMedInfoFilePath());
        storage = new StorageManager(medInfoStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s medinfo book and {@code userPrefs}. <br>
     * The data from the sample medinfo book will be used instead if {@code storage}'s medinfo book is not found,
     * or an empty medinfo book will be used instead if errors occur when reading {@code storage}'s medinfo book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyMedInfo> medInfoOptional;
        ReadOnlyMedInfo initialData;
        try {
            medInfoOptional = storage.readMedInfo();
            if (!medInfoOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample MedInfo");
            }
            Optional<ReadOnlyMedInfo> sampleData = Optional.ofNullable(SampleDataUtil.getSampleMedInfo());
            initialData = medInfoOptional.orElseGet(() -> {
                try {
                    return SampleDataUtil.getSampleMedInfo();
                } catch (CommandException e) {
                    logger.warning("Data file not in the correct format. Will be starting with an empty MedInfo");
                    return new MedInfo();
                }
            });
        } catch (CommandException | DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty MedInfo");
            initialData = new MedInfo();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty MedInfo");
            initialData = new MedInfo();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty MedInfo");
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
        logger.info("Starting MedInfo " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping MedInfo ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

package seedu.sudohr;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.sudohr.commons.core.Config;
import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.commons.core.Version;
import seedu.sudohr.commons.exceptions.DataConversionException;
import seedu.sudohr.commons.util.ConfigUtil;
import seedu.sudohr.commons.util.StringUtil;
import seedu.sudohr.logic.Logic;
import seedu.sudohr.logic.LogicManager;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.ReadOnlyUserPrefs;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.util.SampleDataUtil;
import seedu.sudohr.storage.JsonSudoHrStorage;
import seedu.sudohr.storage.JsonUserPrefsStorage;
import seedu.sudohr.storage.Storage;
import seedu.sudohr.storage.StorageManager;
import seedu.sudohr.storage.SudoHrStorage;
import seedu.sudohr.storage.UserPrefsStorage;
import seedu.sudohr.ui.Ui;
import seedu.sudohr.ui.UiManager;

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
        logger.info("=============================[ Initializing SudoHr ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        SudoHrStorage sudoHrStorage = new JsonSudoHrStorage(userPrefs.getSudoHrFilePath());
        storage = new StorageManager(sudoHrStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);
        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s SudoHR and {@code userPrefs}. <br>
     * The data from the sample SudoHR will be used instead if {@code storage}'s SudoHR is not found,
     * or an empty SudoHR will be used instead if errors occur when reading {@code storage}'s SudoHR.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlySudoHr> sudoHrOptional;
        ReadOnlySudoHr initialData;
        try {
            sudoHrOptional = storage.readSudoHr();
            if (!sudoHrOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample SudoHr");
            }
            initialData = sudoHrOptional.orElseGet(SampleDataUtil::getSampleSudoHr);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty SudoHR");
            initialData = new SudoHr();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty SudoHR");
            initialData = new SudoHr();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty SudoHr");
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
        logger.info("Starting SudoHR " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping SudoHR Application ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

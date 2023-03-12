package seedu.vms;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import seedu.vms.commons.core.Config;
import seedu.vms.commons.core.LogsCenter;
import seedu.vms.commons.core.Version;
import seedu.vms.commons.exceptions.DataConversionException;
import seedu.vms.commons.util.ConfigUtil;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.logic.Logic;
import seedu.vms.logic.LogicManager;
import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.util.SampleDataUtil;
import seedu.vms.model.vaccination.VaxTypeManager;
import seedu.vms.storage.JsonPatientManagerStorage;
import seedu.vms.storage.JsonUserPrefsStorage;
import seedu.vms.storage.PatientManagerStorage;
import seedu.vms.storage.Storage;
import seedu.vms.storage.StorageManager;
import seedu.vms.storage.UserPrefsStorage;
import seedu.vms.storage.vaccination.JsonVaxTypeStorage;
import seedu.vms.storage.vaccination.VaxTypeStorage;
import seedu.vms.ui.Ui;
import seedu.vms.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private static final int FPS = 30;

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing PatientManager ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PatientManagerStorage patientManagerStorage = new JsonPatientManagerStorage(
                userPrefs.getPatientManagerFilePath());
        VaxTypeStorage vaxTypeStorage = new JsonVaxTypeStorage();
        storage = new StorageManager(patientManagerStorage, vaxTypeStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s patient manager and {@code userPrefs}. <br>
     * The data from the sample patient manager will be used instead if {@code storage}'s patient manager is not found,
     * or an empty patient manager will be used instead if errors occur when reading {@code storage}'s patient manager.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPatientManager> patientManagerOptional;
        ReadOnlyPatientManager initialData;
        try {
            patientManagerOptional = storage.readPatientManager();
            if (!patientManagerOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PatientManager");
            }
            initialData = patientManagerOptional.orElseGet(SampleDataUtil::getSamplePatientManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PatientManager");
            initialData = new PatientManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PatientManager");
            initialData = new PatientManager();
        }

        VaxTypeManager vaxTypeManager = new VaxTypeManager();
        try {
            vaxTypeManager = storage.loadUserVaxTypes();
        } catch (IOException ioEx) {
            logger.warning("Unable to load vaccination types, default will be loaded, problem: " + ioEx.getMessage());
            vaxTypeManager = storage.loadDefaultVaxTypes();
        } catch (RuntimeException rte) {
            // not suppose to happen but initialize as empty
        }

        return new ModelManager(initialData, vaxTypeManager, userPrefs);
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

        // Update config file in case it was missing to begin with or there are new/unused fields
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PatientManager");
            initializedPrefs = new UserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting PatientManager " + MainApp.VERSION);
        ui.start(primaryStage);
        startRefreshLoop();
    }


    private void startRefreshLoop() {
        executor.scheduleAtFixedRate(
                () -> Platform.runLater(() -> ui.refresh()),
                0,
                1000 / FPS,
                TimeUnit.MILLISECONDS);
    }


    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
        executor.shutdown();
    }
}

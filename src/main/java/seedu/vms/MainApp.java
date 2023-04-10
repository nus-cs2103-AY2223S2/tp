package seedu.vms;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import seedu.vms.commons.core.Config;
import seedu.vms.commons.core.LogsCenter;
import seedu.vms.commons.core.Version;
import seedu.vms.commons.util.ConfigUtil;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.logic.Logic;
import seedu.vms.logic.LogicManager;
import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.model.keyword.KeywordManager;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.vaccination.VaxTypeManager;
import seedu.vms.storage.JsonUserPrefsStorage;
import seedu.vms.storage.Storage;
import seedu.vms.storage.StorageManager;
import seedu.vms.storage.UserPrefsStorage;
import seedu.vms.storage.appointment.AppointmentStorage;
import seedu.vms.storage.appointment.JsonAppointmentStorage;
import seedu.vms.storage.keyword.JsonKeywordStorage;
import seedu.vms.storage.keyword.KeywordStorage;
import seedu.vms.storage.patient.JsonPatientManagerStorage;
import seedu.vms.storage.patient.PatientManagerStorage;
import seedu.vms.storage.vaccination.JsonVaxTypeStorage;
import seedu.vms.storage.vaccination.VaxTypeStorage;
import seedu.vms.ui.Ui;
import seedu.vms.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 3, 0, false);

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

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage();
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PatientManagerStorage patientManagerStorage = new JsonPatientManagerStorage();
        VaxTypeStorage vaxTypeStorage = new JsonVaxTypeStorage();
        AppointmentStorage appointmentStorage = new JsonAppointmentStorage();
        KeywordStorage keywordStorage = new JsonKeywordStorage();
        storage = new StorageManager(patientManagerStorage, vaxTypeStorage,
                appointmentStorage, userPrefsStorage, keywordStorage);

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
        return new ModelManager(
                new PatientManager(),
                new VaxTypeManager(),
                new AppointmentManager(),
                new KeywordManager(),
                userPrefs);
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
            initializedConfig = ConfigUtil.readConfig(configFilePathUsed);
        } catch (IOException ioEx) {
            logger.warning("Default config will be used due to: " + ioEx.getMessage());
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
        UserPrefs initializedPrefs;
        try {
            initializedPrefs = storage.readUserPrefs();
        } catch (IOException e) {
            logger.warning("Default user preference will be used due to: " + e.getMessage());
            initializedPrefs = new UserPrefs();
        }
        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting PatientManager " + MainApp.VERSION);
        ui.start(primaryStage);
        startRefreshLoop();
        logic.loadManagers(ui::showErrorDialogAndShutdown);
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
        logger.info("============================ [ Stopping VMS ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
        executor.shutdown();
    }
}

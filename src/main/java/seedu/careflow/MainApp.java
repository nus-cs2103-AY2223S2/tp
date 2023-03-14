package seedu.careflow;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import seedu.careflow.commons.core.Config;
import seedu.careflow.commons.core.LogsCenter;
import seedu.careflow.commons.core.Version;
import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.commons.util.ConfigUtil;
import seedu.careflow.commons.util.StringUtil;
import seedu.careflow.logic.CareFlowLogic;
import seedu.careflow.logic.CareFlowLogicManager;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.HospitalRecord;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.ReadOnlyUserPrefs;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.model.util.SampleDataUtil;
import seedu.careflow.storage.CareFlowStorage;
import seedu.careflow.storage.CareFlowStorageManager;
import seedu.careflow.storage.DrugInventoryStorage;
import seedu.careflow.storage.JsonDrugInventoryStorage;
import seedu.careflow.storage.JsonPatientRecordStorage;
import seedu.careflow.storage.JsonUserPrefsStorage;
import seedu.careflow.storage.PatientRecordStorage;
import seedu.careflow.storage.UserPrefsStorage;
import seedu.careflow.ui.Ui;
import seedu.careflow.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private static HostServices hostServices;

    protected Ui ui;
    protected CareFlowLogic logic;
    protected CareFlowStorage storage;
    protected CareFlowModel model;
    protected Config config;

    public static HostServices getStaticHostServices() {
        return hostServices;
    }

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing CareFlow ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        PatientRecordStorage patientRecordStorage = new JsonPatientRecordStorage(userPrefs.getPatientRecordFilePath());
        DrugInventoryStorage drugInventoryStorage = new JsonDrugInventoryStorage(userPrefs.getDrugInventoryFilePath());
        storage = new CareFlowStorageManager(patientRecordStorage, drugInventoryStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new CareFlowLogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private CareFlowModel initModelManager(CareFlowStorage careFlowStorage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPatientRecord> patientRecordOptional;
        Optional<ReadOnlyDrugInventory> drugInventoryOptional;

        ReadOnlyPatientRecord initialDataPatient;
        ReadOnlyDrugInventory initialDataDrug;
        ReadOnlyHospitalRecords initialDataHospital;
        initialDataHospital = new HospitalRecord();
        try {
            patientRecordOptional = storage.readPatientRecord();
            drugInventoryOptional = storage.readDrugInventory();
            if (patientRecordOptional.isEmpty()) {
                logger.info("Patient data file not found. Will be starting with a sample Patient Record");
            }
            if (drugInventoryOptional.isEmpty()) {
                logger.info("Drug data file not found. Will be starting with a sample Drug Inventory");
            }
            initialDataPatient = patientRecordOptional.orElseGet(SampleDataUtil::getSamplePatientRecord);
            initialDataDrug = drugInventoryOptional.orElseGet(SampleDataUtil::getSampleDrugInventory);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with empty Patient Record and Drug "
                    + "Inventory");
            initialDataPatient = new PatientRecord();
            initialDataDrug = new DrugInventory();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with a empty Patient Record and "
                    + "Drug Inventory");
            initialDataPatient = new PatientRecord();
            initialDataDrug = new DrugInventory();
        }


        return new CareFlowModelManager(initialDataPatient, initialDataDrug, initialDataHospital, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
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
        hostServices = getHostServices();
        logger.info("Starting CareFlow " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping CareFlow ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

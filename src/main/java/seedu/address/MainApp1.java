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
import seedu.address.logic.Logic1;
import seedu.address.logic.LogicManager1;
import seedu.address.model.InternshipCatalogue;
import seedu.address.model.Model1;
import seedu.address.model.ModelManager1;
import seedu.address.model.ReadOnlyInternshipCatalogue;
import seedu.address.model.ReadOnlyUserPrefs1;
import seedu.address.model.UserPrefs1;
import seedu.address.model.util.SampleDataUtil1;
import seedu.address.storage.InternshipCatalogueStorage;
import seedu.address.storage.JsonInternshipCatalogueStorage;
import seedu.address.storage.JsonUserPrefsStorage1;
import seedu.address.storage.Storage1;
import seedu.address.storage.StorageManager1;
import seedu.address.storage.UserPrefsStorage1;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp1 extends Application {
    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp1.class);

    protected Ui ui;
    // Changed this to new Logic Class
    protected Logic1 logic;
    protected Storage1 storage;
    // Changes this to Model class.
    protected Model1 model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing InternshipCatalogue ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage1 userPrefsStorage = new JsonUserPrefsStorage1(config.getUserPrefsFilePath());
        UserPrefs1 userPrefs = initPrefs(userPrefsStorage);
        InternshipCatalogueStorage internshipCatalogueStorage = new JsonInternshipCatalogueStorage(userPrefs.getInternshipCatalogueFilePath());
        storage = new StorageManager1(internshipCatalogueStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager1(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s internship catalogue and {@code userPrefs}. <br>
     * The data from the sample internship catalogue will be used instead if {@code storage}'s internship catalogue is not found,
     * or an empty internship catalogue will be used instead if errors occur when reading {@code storage}'s internship catalogue.
     */
    private Model1 initModelManager(Storage1 storage, ReadOnlyUserPrefs1 userPrefs) {
        Optional<ReadOnlyInternshipCatalogue> internshipCatalogueOptional;
        ReadOnlyInternshipCatalogue initialData;

        try {
            internshipCatalogueOptional = storage.readInternshipCatalogue();
            if (!internshipCatalogueOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Internship Catalogue");
            }
            initialData = internshipCatalogueOptional.orElseGet(SampleDataUtil1::getSampleInternshipCatalogue);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty InternshipCatalogue");
            initialData = new InternshipCatalogue();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty InternshipCatalogue");
            initialData = new InternshipCatalogue();
        }

        return new ModelManager1(initialData, userPrefs);
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
    protected UserPrefs1 initPrefs(UserPrefsStorage1 storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs1 initializedPrefs;
        try {
            Optional<UserPrefs1> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs1());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs1();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs1();
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
        logger.info("Starting The Intern's Ship " + MainApp1.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Internship Catalogue] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}


package seedu.internship;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.internship.commons.core.Config;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.commons.core.Version;
import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.commons.util.ConfigUtil;
import seedu.internship.commons.util.StringUtil;
import seedu.internship.logic.Logic;
import seedu.internship.logic.LogicManager;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.ReadOnlyUserPrefs;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.util.SampleDataUtil;
import seedu.internship.storage.InternshipCatalogueStorage;
import seedu.internship.storage.JsonInternshipCatalogueStorage;
import seedu.internship.storage.JsonUserPrefsStorage;
import seedu.internship.storage.Storage;
import seedu.internship.storage.StorageManager;
import seedu.internship.storage.UserPrefsStorage;
import seedu.internship.ui.Ui;
import seedu.internship.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {
    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    // Changed this to new Logic Class
    protected Logic logic;
    protected Storage storage;
    // Changes this to Model class.
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing InternshipCatalogue ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        InternshipCatalogueStorage internshipCatalogueStorage = new JsonInternshipCatalogueStorage(
                userPrefs.getInternshipCatalogueFilePath());
        storage = new StorageManager(internshipCatalogueStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s internship catalogue and {@code userPrefs}.
     * The data from the sample internship catalogue will be used instead if {@code storage}'s internship catalogue is
     * not found, or an empty internship catalogue will be used instead if errors occur when reading {@code storage}'s
     * internship catalogue.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyInternshipCatalogue> internshipCatalogueOptional;
        ReadOnlyInternshipCatalogue initialData;

        try {
            internshipCatalogueOptional = storage.readInternshipCatalogue();
            if (!internshipCatalogueOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Internship Catalogue");
            }
            initialData = internshipCatalogueOptional.orElseGet(SampleDataUtil::getSampleInternshipCatalogue);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty InternshipCatalogue");
            initialData = new InternshipCatalogue();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty InternshipCatalogue");
            initialData = new InternshipCatalogue();
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
        logger.info("Starting The Intern's Ship " + MainApp.VERSION);
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

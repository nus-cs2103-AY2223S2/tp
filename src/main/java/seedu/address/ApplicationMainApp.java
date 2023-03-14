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
import seedu.address.logic.ApplicationLogic;
import seedu.address.logic.ApplicationLogicManager;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.InternshipBook;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.ApplicationSampleDataUtil;
import seedu.address.storage.ApplicationStorage;
import seedu.address.storage.ApplicationStorageManager;
import seedu.address.storage.InternshipBookStorage;
import seedu.address.storage.JsonInternshipBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.ApplicationUiManager;
import seedu.address.ui.Ui;


/**
 * Runs the application.
 */
public class ApplicationMainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(ApplicationMainApp.class);

    protected Ui ui;
    protected ApplicationLogic logic;
    protected ApplicationStorage storage;
    protected ApplicationModel model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing InternshipBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        // Remark: the following line was edited to allow compilation,
        // but the program might no longer behave as intended. Proceed with caution.
        InternshipBookStorage internshipBookStorage =
                new JsonInternshipBookStorage(userPrefs.getInternshipBookFilePath());
        storage = new ApplicationStorageManager(internshipBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new ApplicationLogicManager(model, storage);

        ui = new ApplicationUiManager(logic);
    }

    /**
     * Returns a {@code ApplicationModelManager} with the data from {@code storage}'s
     * internship book and {@code userPrefs}. <br>
     * The data from the sample internship book will be used instead if {@code storage}'s internship book is not found,
     * or an empty internship book will be used instead if errors occur when reading {@code storage}'s internship book.
     */
    private ApplicationModel initModelManager(ApplicationStorage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyInternshipBook> internshipBookOptional;
        ReadOnlyInternshipBook initialData;
        try {
            internshipBookOptional = storage.readInternshipBook();
            if (!internshipBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample InternshipBook");
            }
            initialData = internshipBookOptional.orElseGet(ApplicationSampleDataUtil::getSampleInternshipBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty InternshipBook");
            initialData = new InternshipBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty InternshipBook");
            initialData = new InternshipBook();
        }

        return new ApplicationModelManager(initialData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty InternshipBook");
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
        logger.info("Starting InternshipBook " + ApplicationMainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Internship Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

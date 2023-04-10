package seedu.techtrack;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.techtrack.commons.core.Config;
import seedu.techtrack.commons.core.LogsCenter;
import seedu.techtrack.commons.core.Version;
import seedu.techtrack.commons.exceptions.DataConversionException;
import seedu.techtrack.commons.util.ConfigUtil;
import seedu.techtrack.commons.util.StringUtil;
import seedu.techtrack.logic.Logic;
import seedu.techtrack.logic.LogicManager;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.ModelManager;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.ReadOnlyUserPrefs;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.UserPrefs;
import seedu.techtrack.model.util.SampleDataUtil;
import seedu.techtrack.storage.JsonRoleBookStorage;
import seedu.techtrack.storage.JsonUserPrefsStorage;
import seedu.techtrack.storage.RoleBookStorage;
import seedu.techtrack.storage.Storage;
import seedu.techtrack.storage.StorageManager;
import seedu.techtrack.storage.UserPrefsStorage;
import seedu.techtrack.ui.Ui;
import seedu.techtrack.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing TechTrack ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        RoleBookStorage roleBookStorage = new JsonRoleBookStorage(userPrefs.getRoleBookFilePath());
        storage = new StorageManager(roleBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s role book and {@code userPrefs}. <br>
     * The data from the sample role book will be used instead if {@code storage}'s role book is not found,
     * or an empty role book will be used instead if errors occur when reading {@code storage}'s role book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyRoleBook> roleBookOptional;
        ReadOnlyRoleBook initialData;
        try {
            roleBookOptional = storage.readRoleBook();
            if (!roleBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample RoleBook");
            }
            initialData = roleBookOptional.orElseGet(SampleDataUtil::getSampleRoleBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty RoleBook"
                    + " (storage model)");
            initialData = new RoleBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty RoleBook"
                    + " (storage model)");
            initialData = new RoleBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty RoleBook.");
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
        logger.info("Starting TechTrack " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping TechTrack ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

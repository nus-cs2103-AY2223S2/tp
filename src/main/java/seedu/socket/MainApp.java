package seedu.socket;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import seedu.socket.commons.core.Config;
import seedu.socket.commons.core.LogsCenter;
import seedu.socket.commons.core.Version;
import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.commons.util.ConfigUtil;
import seedu.socket.commons.util.StringUtil;
import seedu.socket.logic.Logic;
import seedu.socket.logic.LogicManager;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.ReadOnlyUserPrefs;
import seedu.socket.model.Socket;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.util.SampleDataUtil;
import seedu.socket.storage.JsonSocketStorage;
import seedu.socket.storage.JsonUserPrefsStorage;
import seedu.socket.storage.SocketStorage;
import seedu.socket.storage.Storage;
import seedu.socket.storage.StorageManager;
import seedu.socket.storage.UserPrefsStorage;
import seedu.socket.ui.Ui;
import seedu.socket.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private static HostServices services;

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Socket ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        SocketStorage socketStorage = new JsonSocketStorage(userPrefs.getSocketFilePath());
        storage = new StorageManager(socketStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

        services = getHostServices();
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s {@code Socket} and {@code userPrefs}. <br>
     * The data from the sample {@code Socket} will be used instead if {@code storage}'s {@code Socket} is not found,
     * or an empty {@code Socket} will be used instead if errors occur when reading {@code storage}'s {@code Socket}.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlySocket> socketOptional;
        ReadOnlySocket initialData;
        try {
            socketOptional = storage.readSocket();
            if (!socketOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Socket");
            }
            initialData = socketOptional.orElseGet(SampleDataUtil::getSampleSocket);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Socket");
            initialData = new Socket();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Socket");
            initialData = new Socket();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Socket");
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
        logger.info("Starting Socket " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Socket ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }

    /**
     * Opens the given {@code String} URL in a browser.
     */
    public static void openUrl(String url) {
        services.showDocument(url);
    }
}

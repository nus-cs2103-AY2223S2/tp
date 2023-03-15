package codoc;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import codoc.commons.core.Config;
import codoc.commons.core.LogsCenter;
import codoc.commons.core.Version;
import codoc.commons.exceptions.DataConversionException;
import codoc.commons.util.ConfigUtil;
import codoc.commons.util.StringUtil;
import codoc.logic.Logic;
import codoc.logic.LogicManager;
import codoc.model.Codoc;
import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.ReadOnlyCodoc;
import codoc.model.ReadOnlyUserPrefs;
import codoc.model.UserPrefs;
import codoc.model.util.SampleDataUtil;
import codoc.storage.CodocStorage;
import codoc.storage.JsonCodocStorage;
import codoc.storage.JsonUserPrefsStorage;
import codoc.storage.Storage;
import codoc.storage.StorageManager;
import codoc.storage.UserPrefsStorage;
import codoc.ui.Ui;
import codoc.ui.UiManager;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        logger.info("=============================[ Initializing CoDoc ]===========================");
        super.init();

        Font rbtBold = Font.loadFont(getClass().getResourceAsStream("/font/Roboto_Bold.ttf"), 10);
        Font rbtReg = Font.loadFont(getClass().getResourceAsStream("/font/Roboto_Medium.ttf"), 10);
        Font rbtLight = Font.loadFont(getClass().getResourceAsStream("/font/Roboto_Light.ttf"), 10);
        Font rbtmBold = Font.loadFont(getClass().getResourceAsStream("/font/RobotoMono_Bold.ttf"), 10);
        Font rbtmReg = Font.loadFont(getClass().getResourceAsStream("/font/RobotoMono_Regular.ttf"), 10);
        Font rbtmLight = Font.loadFont(getClass().getResourceAsStream("/font/RobotoMono_Light.ttf"), 10);

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        CodocStorage codocStorage = new JsonCodocStorage(userPrefs.getCodocFilePath());
        storage = new StorageManager(codocStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s CoDoc and {@code userPrefs}. <br>
     * The data from the sample CoDoc will be used instead if {@code storage}'s CoDoc is not found,
     * or an empty CoDoc will be used instead if errors occur when reading {@code storage}'s CoDoc.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyCodoc> codocOptional;
        ReadOnlyCodoc initialData;
        try {
            codocOptional = storage.readCodoc();
            if (!codocOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Codoc");
            }
            initialData = codocOptional.orElseGet(SampleDataUtil::getSampleCodoc);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Codoc");
            initialData = new Codoc();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Codoc");
            initialData = new Codoc();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Codoc");
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
        logger.info("Starting Codoc " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping CoDoc ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

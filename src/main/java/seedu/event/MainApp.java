package seedu.event;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.event.commons.core.Config;
import seedu.event.commons.core.LogsCenter;
import seedu.event.commons.core.Version;
import seedu.event.commons.exceptions.DataConversionException;
import seedu.event.commons.util.ConfigUtil;
import seedu.event.commons.util.StringUtil;
import seedu.event.logic.Logic;
import seedu.event.logic.LogicManager;
import seedu.event.model.ContactList;
import seedu.event.model.EventBook;
import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.ReadOnlyUserPrefs;
import seedu.event.model.UserPrefs;
import seedu.event.model.util.SampleDataUtil;
import seedu.event.storage.ContactListStorage;
import seedu.event.storage.EventBookStorage;
import seedu.event.storage.JsonContactListStorage;
import seedu.event.storage.JsonEventBookStorage;
import seedu.event.storage.JsonUserPrefsStorage;
import seedu.event.storage.Storage;
import seedu.event.storage.StorageManager;
import seedu.event.storage.UserPrefsStorage;
import seedu.event.ui.Ui;
import seedu.event.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Paidlancers ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        EventBookStorage eventBookStorage = new JsonEventBookStorage(userPrefs.getEventBookFilePath());
        ContactListStorage contactListStorage = new JsonContactListStorage(userPrefs.getContactListFilePath());
        storage = new StorageManager(eventBookStorage, contactListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s event book and {@code userPrefs}. <br>
     * The data from the sample event book will be used instead if {@code storage}'s event book is not found,
     * or an empty event book will be used instead if errors occur when reading {@code storage}'s event book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyEventBook> eventBookOptional;
        Optional<ReadOnlyContactList> contactListOptional;
        ReadOnlyEventBook initialData;
        ReadOnlyContactList initialContactData;
        try {
            eventBookOptional = storage.readEventBook();
            if (!eventBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Paidlancers");
            }
            initialData = eventBookOptional.orElseGet(SampleDataUtil::getSampleEventBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Paidlancers");
            initialData = new EventBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Paidlancers");
            initialData = new EventBook();
        }

        try {
            contactListOptional = storage.readContactList();
            if (!contactListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample contactList");
            }
            initialContactData = contactListOptional.orElseGet(SampleDataUtil::getSampleContactList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ContactList");
            initialContactData = new ContactList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ContactList");
            initialContactData = new ContactList();
        }

        return new ModelManager(initialData, initialContactData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Paidlancers");
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
        logger.info("Starting Paidlancers " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Paidlancers ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

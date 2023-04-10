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
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TankList;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.readings.FullReadingLevels;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.util.SampleReadingsUtil;
import seedu.address.model.util.SampleTankUtil;
import seedu.address.model.util.SampleTaskUtil;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.fish.AddressBookStorage;
import seedu.address.storage.fish.JsonAddressBookStorage;
import seedu.address.storage.tank.JsonTankListStorage;
import seedu.address.storage.tank.TankListStorage;
import seedu.address.storage.tank.readings.ammonialevels.FullReadingLevelsStorage;
import seedu.address.storage.tank.readings.ammonialevels.JsonFullReadingLevelsStorage;
import seedu.address.storage.task.JsonTaskListStorage;
import seedu.address.storage.task.TaskListStorage;
import seedu.address.storage.userprefs.JsonUserPrefsStorage;
import seedu.address.storage.userprefs.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

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
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        TaskListStorage taskListStorage = new JsonTaskListStorage(userPrefs.getTaskListFilePath());
        TankListStorage tankListStorage = new JsonTankListStorage(userPrefs.getTankListFilePath());
        FullReadingLevelsStorage fullReadingLevelsStorage = new JsonFullReadingLevelsStorage(userPrefs
                .getFullReadingsLevelsPath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage, taskListStorage, tankListStorage,
                fullReadingLevelsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ReadOnlyAddressBook initialData;
        ReadOnlyTaskList initialTaskList;
        ReadOnlyTankList initialTankList;
        ReadOnlyReadingLevels initialFullReadings;

        try {
            initialData = readAddressBookFromStorage(storage);
            initialTaskList = readTaskListFromStorage(storage);
            initialTankList = readTankListFromStorage(storage);
            initialFullReadings = readReadingLevelsFromStorage(storage);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Files");
            initialData = new AddressBook();
            initialTaskList = new TaskList();
            initialTankList = new TankList();
            initialFullReadings = new FullReadingLevels();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Files");
            initialData = new AddressBook();
            initialTaskList = new TaskList();
            initialTankList = new TankList();
            initialFullReadings = new FullReadingLevels();
        }

        return new ModelManager(initialData, userPrefs, initialTaskList, initialTankList, initialFullReadings);
    }

    private ReadOnlyAddressBook readAddressBookFromStorage(Storage storage) throws DataConversionException,
            IOException {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        addressBookOptional = storage.readAddressBook();
        if (!addressBookOptional.isPresent()) {
            logger.info("Data file not found. Will be starting with a sample AddressBook");
        }
        initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        return initialData;
    }

    private ReadOnlyTaskList readTaskListFromStorage(Storage storage) throws DataConversionException,
            IOException {
        Optional<ReadOnlyTaskList> taskListOptional;
        ReadOnlyTaskList initialData;
        taskListOptional = storage.readTaskList();
        if (!taskListOptional.isPresent()) {
            logger.info("Data file not found. Will be starting with a sample Task List");
        }
        initialData = taskListOptional.orElseGet(SampleTaskUtil::getSampleTaskList);
        return initialData;
    }

    private ReadOnlyTankList readTankListFromStorage(Storage storage) throws DataConversionException,
            IOException {
        Optional<ReadOnlyTankList> tankListOptional;
        ReadOnlyTankList initialData;
        tankListOptional = storage.readTankList();
        if (!tankListOptional.isPresent()) {
            logger.info("Data file not found. Will be starting with a sample Tank List");
        }
        initialData = tankListOptional.orElseGet(SampleTankUtil::getSampleTankList);
        return initialData;
    }

    private ReadOnlyReadingLevels readReadingLevelsFromStorage(Storage storage) throws DataConversionException,
            IOException {
        Optional<ReadOnlyReadingLevels> readingLevelsOptional;
        ReadOnlyReadingLevels initialData;
        readingLevelsOptional = storage.readFullReadingLevels();
        if (!readingLevelsOptional.isPresent()) {
            logger.info("Data file not found. Will be starting with a sample Reading levels");
        }
        initialData = readingLevelsOptional.orElseGet(SampleReadingsUtil::getSampleFullReadingLevels);
        return initialData;
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

    private void executeAutoInitActions() {
        executeFeedingReminderFeature();
    }

    private void executeFeedingReminderFeature() {
        ui.executeFeedingReminderInitUi();
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
        logger.info("Checking for feeding reminders " + MainApp.VERSION);
        executeAutoInitActions();
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

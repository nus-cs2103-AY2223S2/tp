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
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonAssignTaskStorage;
import seedu.address.storage.JsonTaskStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.RepositoryStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
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
    protected OfficeConnectModel officeConnectModel;
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
        RepositoryStorage<Task> taskRepositoryStorage = new JsonTaskStorage(userPrefs.getTaskFilePath());
        RepositoryStorage<AssignTask> personTaskStorage = new JsonAssignTaskStorage(userPrefs
            .getPersonTaskPath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage, taskRepositoryStorage, personTaskStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        officeConnectModel = initOfficeConnectModel(storage, model);

        logic = new LogicManager(storage, officeConnectModel);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code OfficeConnectModel} with the data from {@code storage}'s repository and {@code userPrefs}. <br>
     * The data from the sample repository will be used instead if {@code storage}'s repository is not found,
     * or an empty repository will be used instead if errors occur when reading {@code storage}'s repository.
     */
    private OfficeConnectModel initOfficeConnectModel(Storage storage, Model model) {


        Optional<ReadOnlyRepository<Task>> taskReadOnlyRepository;
        Optional<ReadOnlyRepository<AssignTask>> personTaskReadOnlyRepository;
        ReadOnlyRepository<Task> initialTaskData;
        ReadOnlyRepository<AssignTask> initialPersonTaskData;
        try {
            taskReadOnlyRepository = storage.readTaskBook();
            personTaskReadOnlyRepository = storage.readPersonTaskBook();
            if (taskReadOnlyRepository.isEmpty() || personTaskReadOnlyRepository.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample.");
            }
            SampleDataUtil sampleDataUtil = new SampleDataUtil();
            initialTaskData = taskReadOnlyRepository.orElseGet(sampleDataUtil::getSampleTasksRepo);
            initialPersonTaskData = personTaskReadOnlyRepository.orElseGet(sampleDataUtil::getSampleAssignTaskRepo);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty.");
            initialTaskData = new Repository<>();
            initialPersonTaskData = new Repository<>();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with.");
            initialTaskData = new Repository<>();
            initialPersonTaskData = new Repository<>();

        }

        RepositoryModelManager<Task> taskRepositoryModelManager =
            new RepositoryModelManager<>(initialTaskData);
        RepositoryModelManager<AssignTask> personTaskRepositoryModelManager =
            new RepositoryModelManager<>(initialPersonTaskData);

        return new OfficeConnectModel(taskRepositoryModelManager, personTaskRepositoryModelManager, model);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (addressBookOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
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
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
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

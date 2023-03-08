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
import seedu.address.logic.Logic2;
import seedu.address.logic.LogicManager2;
import seedu.address.model.ListingBook;
import seedu.address.model.Model2;
import seedu.address.model.ModelManager2;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.ReadOnlyUserPrefs2;
import seedu.address.model.UserPrefs2;
import seedu.address.model.util.SampleDataUtil2;
import seedu.address.storage.JsonListingBookStorage;
import seedu.address.storage.ListingBookStorage;
import seedu.address.storage.JsonUserPrefsStorage2;
import seedu.address.storage.UserPrefsStorage2;
import seedu.address.storage.Storage2;
import seedu.address.storage.StorageManager2;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager2;



/**
 * Runs the application.
 */
public class MainApp2 extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp2.class);

    protected Ui ui;
    protected Logic2 logic;
    protected Storage2 storage;
    protected Model2 model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage2 userPrefsStorage = new JsonUserPrefsStorage2(config.getUserPrefsFilePath());
        UserPrefs2 userPrefs = initPrefs(userPrefsStorage);
        ListingBookStorage listingBookStorage = new JsonListingBookStorage(userPrefs.getListingBookFilePath());
        storage = new StorageManager2(listingBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager2(model, storage);

        ui = new UiManager2(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model2 initModelManager(Storage2 storage, ReadOnlyUserPrefs2 userPrefs) {
        Optional<ReadOnlyListingBook> listingBookOptional;
        ReadOnlyListingBook initialData;
        try {
            listingBookOptional = storage.readListingBook();
            if (!listingBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ListingBook");
            }
            initialData = listingBookOptional.orElseGet(SampleDataUtil2::getSampleListingBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ListingBook");
            initialData = new ListingBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ListingBook");
            initialData = new ListingBook();
        }

        return new ModelManager2(initialData, userPrefs);
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
    protected UserPrefs2 initPrefs(UserPrefsStorage2 storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs2 initializedPrefs;
        try {
            Optional<UserPrefs2> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs2());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs2();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs2();
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
        logger.info("Starting AddressBook " + MainApp2.VERSION);
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

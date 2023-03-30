package wingman;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import wingman.commons.core.Config;
import wingman.commons.core.LogsCenter;
import wingman.commons.core.Version;
import wingman.commons.exceptions.DataConversionException;
import wingman.commons.util.ConfigUtil;
import wingman.commons.util.FileUtil;
import wingman.commons.util.GetUtil;
import wingman.commons.util.StringUtil;
import wingman.logic.Logic;
import wingman.logic.LogicManager;
import wingman.model.ItemManager;
import wingman.model.Model;
import wingman.model.ModelManager;
import wingman.model.ReadOnlyItemManager;
import wingman.model.ReadOnlyUserPrefs;
import wingman.model.UserPrefs;
import wingman.model.crew.Crew;
import wingman.model.flight.Flight;
import wingman.model.item.Item;
import wingman.model.location.Location;
import wingman.model.pilot.Pilot;
import wingman.model.plane.Plane;
import wingman.storage.ItemStorage;
import wingman.storage.JsonUserPrefsStorage;
import wingman.storage.Storage;
import wingman.storage.StorageManager;
import wingman.storage.UserPrefsStorage;
import wingman.storage.json.storage.JsonCrewManagerStorage;
import wingman.storage.json.storage.JsonFlightManagerStorage;
import wingman.storage.json.storage.JsonLocationManagerStorage;
import wingman.storage.json.storage.JsonPilotManagerStorage;
import wingman.storage.json.storage.JsonPlaneManagerStorage;
import wingman.ui.Ui;
import wingman.ui.UiManager;

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
        logger.info(
                "=============================[ Initializing Wingman ]===========================");
        super.init();
        initFields();
        configureServiceLocator();
    }

    /**
     * Initialises respective fields from storage
     */
    private void initFields() throws IOException {
        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        initLogging(config);
        storage = initStorage(userPrefs, userPrefsStorage);
        model = initModelManager(storage, userPrefs);
        logic = new LogicManager(model, storage);
        ui = new UiManager(logic);
    }

    /**
     * To allow for convenient access to the services in the specified packages,
     * without disrupting effective isolation between classes
     */
    private void configureServiceLocator() {
        GetUtil.put(Config.class, config);
        GetUtil.put(Storage.class, storage);
        GetUtil.put(Model.class, model);
        GetUtil.put(Logic.class, logic);
    }

    /**
     * Read a specified manager from data
     *
     * @param managerSupplier the data from which the manager is read
     * @param managerName     name of the manager
     * @param <T>             the type of object that the manager manages
     * @return a read only version of the item manager
     */
    private <T extends Item> ReadOnlyItemManager<T> readManager(
            Callable<Optional<? extends ReadOnlyItemManager<T>>> managerSupplier,
            String managerName
    ) {
        Optional<? extends ReadOnlyItemManager<T>> managerOptional;
        try {
            managerOptional = managerSupplier.call();
            if (managerOptional.isPresent()) {
                return managerOptional.get();
            }
            logger.info(
                    String.format("Data file for %s is not found.", managerName)
            );
            return new ItemManager<>();
        } catch (DataConversionException | IOException e) {
            logger.info(String.format(
                    "Exception caught while reading data file for %s: %s",
                    managerName,
                    e.toString()
            ));
            return new ItemManager<>();
        } catch (Exception e) {
            logger.info(String.format(
                    "Unknown Exception: %s.",
                    e.getMessage()
            ));
            return new ItemManager<>();
        }
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(
            Storage storage,
            ReadOnlyUserPrefs userPrefs
    ) {
        ReadOnlyItemManager<Pilot> pilotManager =
                readManager(storage::readPilotManager, "PilotManager");
        ReadOnlyItemManager<Location> locationManager =
                readManager(storage::readLocationManager, "LocationManager");
        ReadOnlyItemManager<Crew> crewManager =
                readManager(storage::readCrewManager, "CrewManager");
        ReadOnlyItemManager<Plane> planeManager =
                readManager(storage::readPlaneManager, "PlaneManager");
        ReadOnlyItemManager<Flight> flightManager =
                readManager(storage::readFlightManager, "FlightManager");

        return new ModelManager(userPrefs, pilotManager, locationManager,
                crewManager, planeManager, flightManager
        );
    }

    /**
     * Initialises storages for specified object types and user preferences
     *
     * @param userPrefs        user preferences
     * @param userPrefsStorage storage destination for user preferences
     * @return StorageManager object that manages the storages for specified object types and user preferences
     */
    private Storage initStorage(
            UserPrefs userPrefs,
            UserPrefsStorage userPrefsStorage
    ) throws IOException {
        FileUtil.createParentDirsOfFile(userPrefs.getPilotManagerFilePath());
        FileUtil.createParentDirsOfFile(userPrefs.getCrewManagerFilePath());
        FileUtil.createParentDirsOfFile(userPrefs.getPlaneManagerFilePath());
        FileUtil.createParentDirsOfFile(userPrefs.getLocationManagerFilePath());
        FileUtil.createParentDirsOfFile(userPrefs.getFlightManagerFilePath());
        FileUtil.createIfMissing(userPrefs.getPilotManagerFilePath());
        FileUtil.createIfMissing(userPrefs.getCrewManagerFilePath());
        FileUtil.createIfMissing(userPrefs.getPlaneManagerFilePath());
        FileUtil.createIfMissing(userPrefs.getLocationManagerFilePath());
        FileUtil.createIfMissing(userPrefs.getFlightManagerFilePath());
        ItemStorage<Pilot> pilotStorage =
                new JsonPilotManagerStorage(userPrefs.getPilotManagerFilePath());
        ItemStorage<Location> locationStorage =
                new JsonLocationManagerStorage(userPrefs.getLocationManagerFilePath());
        ItemStorage<Crew> crewStorage =
                new JsonCrewManagerStorage(userPrefs.getCrewManagerFilePath());
        ItemStorage<Plane> planeStorage =
                new JsonPlaneManagerStorage(userPrefs.getPlaneManagerFilePath());
        ItemStorage<Flight> flightStorage =
                new JsonFlightManagerStorage(userPrefs.getFlightManagerFilePath());
        return new StorageManager(userPrefsStorage, pilotStorage,
                locationStorage,
                crewStorage, planeStorage, flightStorage
        );
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
            Optional<Config> configOptional = ConfigUtil.readConfig(
                    configFilePathUsed);
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
            logger.warning("Failed to save config file : " + StringUtil.getDetails(
                    e));
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
            logger.warning(
                    "Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(
                    e));
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
        logger.info(
                "============================ [ Stopping Wingman ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(
                    e));
        }
    }
}

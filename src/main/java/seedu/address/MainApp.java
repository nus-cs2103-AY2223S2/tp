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
import seedu.address.commons.util.GetUtils;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.ItemManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.crew.Crew;
import seedu.address.model.flight.Flight;
import seedu.address.model.location.Location;
import seedu.address.model.pilot.Pilot;
import seedu.address.model.plane.Plane;
import seedu.address.storage.ItemStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.json.storage.JsonCrewManagerStorage;
import seedu.address.storage.json.storage.JsonFlightManagerStorage;
import seedu.address.storage.json.storage.JsonLocationManagerStorage;
import seedu.address.storage.json.storage.JsonPilotManagerStorage;
import seedu.address.storage.json.storage.JsonPlaneManagerStorage;
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
        logger.info("=============================[ Initializing Wingman ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
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
        storage = new StorageManager(userPrefsStorage, pilotStorage, locationStorage,
            crewStorage, planeStorage, flightStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

        GetUtils.put(Config.class, config);
        GetUtils.put(Storage.class, storage);
        GetUtils.put(Model.class, model);
        GetUtils.put(Logic.class, logic);
    }

    /**
     * Read pilot manager from data.
     *
     * @param storage the storage object
     * @return the pilot manager
     */
    private ReadOnlyItemManager<Pilot> readPilotManager(Storage storage) {
        ReadOnlyItemManager<Pilot> pilotManager;
        Optional<? extends ReadOnlyItemManager<Pilot>> pilotManagerOptional;
        try {
            pilotManagerOptional = storage.readPilotManager();
            if (pilotManagerOptional.isEmpty()) {
                logger.info("Data file for pilot manager not found.");
                pilotManager = new ItemManager<>();
            } else {
                pilotManager = pilotManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            logger.info("Exception caught while reading data file for storage manager: "
                            + e.toString());
            pilotManager = new ItemManager<>();
        }
        return pilotManager;
    }

    /**
     * Read location manager from data.
     *
     * @param storage the storage object
     * @return the location manager
     */
    private ReadOnlyItemManager<Location> readLocationManager(Storage storage) {
        ReadOnlyItemManager<Location> locationManager;
        Optional<? extends ReadOnlyItemManager<Location>> locationManagerOptional;
        try {
            locationManagerOptional = storage.readLocationManager();
            if (locationManagerOptional.isEmpty()) {
                logger.info("Data file for location manager not found.");
                locationManager = new ItemManager<>();
            } else {
                locationManager = locationManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            logger.info("Exception caught while reading data file for location manager: "
                            + e.toString());
            locationManager = new ItemManager<>();
        }
        return locationManager;
    }

    /**
     * Read crew manager from data.
     *
     * @param storage the storage object
     * @return the crew manager
     */
    private ReadOnlyItemManager<Crew> readCrewManager(Storage storage) {
        ReadOnlyItemManager<Crew> crewManager;
        Optional<? extends ReadOnlyItemManager<Crew>> crewManagerOptional;
        try {
            crewManagerOptional = storage.readCrewManager();
            if (crewManagerOptional.isEmpty()) {
                logger.info("Data file for crew manager not found.");
                crewManager = new ItemManager<>();
            } else {
                crewManager = crewManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            logger.info("Exception caught while reading data file for crew manager: "
                            + e.toString());
            crewManager = new ItemManager<>();
        }
        return crewManager;
    }

    /**
     * Read plane manager from data.
     *
     * @param storage the storage object
     * @return the plane manager
     */
    private ReadOnlyItemManager<Plane> readPlaneManager(Storage storage) {
        ReadOnlyItemManager<Plane> planeManager;
        Optional<? extends ReadOnlyItemManager<Plane>> crewManagerOptional;
        try {
            crewManagerOptional = storage.readPlaneManager();
            if (crewManagerOptional.isEmpty()) {
                logger.info("Data file for plane manager not found.");
                planeManager = new ItemManager<>();
            } else {
                planeManager = crewManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            logger.info("Exception caught while reading data file for plane manager: "
                            + e.toString());
            planeManager = new ItemManager<>();
        }
        return planeManager;
    }

    /**
     * Read flight manager from data.
     *
     * @param storage the storage object
     * @return flight plane manager
     */
    private ReadOnlyItemManager<Flight> readFlightManager(Storage storage) {
        ReadOnlyItemManager<Flight> flightManager;
        Optional<? extends ReadOnlyItemManager<Flight>> flightManagerOptional;
        try {
            flightManagerOptional = storage.readFlightManager();
            if (flightManagerOptional.isEmpty()) {
                logger.info("Data file for flight manager not found.");
                flightManager = new ItemManager<>();
            } else {
                flightManager = flightManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            logger.info("Exception caught while reading data file for flight manager: "
                            + e.toString());
            flightManager = new ItemManager<>();
        }
        return flightManager;
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage,
        ReadOnlyUserPrefs userPrefs) {
        ReadOnlyItemManager<Pilot> pilotManager = readPilotManager(storage);
        ReadOnlyItemManager<Location> locationManager = readLocationManager(storage);
        ReadOnlyItemManager<Crew> crewManager = readCrewManager(storage);
        ReadOnlyItemManager<Plane> planeManager = readPlaneManager(storage);
        ReadOnlyItemManager<Flight> flightManager = readFlightManager(storage);
        return new ModelManager(userPrefs, pilotManager, locationManager,
            crewManager, planeManager, flightManager);
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
        logger.info("============================ [ Stopping Wingman ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

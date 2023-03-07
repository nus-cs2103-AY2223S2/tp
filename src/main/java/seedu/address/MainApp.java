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
import seedu.address.model.IdentifiableManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.crew.Crew;
import seedu.address.model.flight.Flight;
import seedu.address.model.location.Location;
import seedu.address.model.pilot.Pilot;
import seedu.address.model.plane.Plane;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.IdentifiableStorage;
import seedu.address.storage.JsonAddressBookStorage;
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
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        IdentifiableStorage<Pilot> pilotStorage =
            new JsonPilotManagerStorage(userPrefs.getPilotManagerFilePath());
        IdentifiableStorage<Location> locationStorage =
                new JsonLocationManagerStorage(userPrefs.getLocationManagerFilePath());
        IdentifiableStorage<Crew> crewStorage =
                new JsonCrewManagerStorage(userPrefs.getCrewManagerFilePath());
        IdentifiableStorage<Plane> planeStorage =
                new JsonPlaneManagerStorage(userPrefs.getPlaneManagerFilePath());
        IdentifiableStorage<Flight> flightStorage =
                new JsonFlightManagerStorage(userPrefs.getFlightManagerFilePath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage, pilotStorage, locationStorage,
                crewStorage, planeStorage, flightStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Read pilot manager from data.
     * @param storage the storage object
     * @return the pilot manager
     */
    private ReadOnlyIdentifiableManager<Pilot> readPilotManager(Storage storage) {
        ReadOnlyIdentifiableManager<Pilot> pilotManager;
        Optional<? extends ReadOnlyIdentifiableManager<Pilot>> pilotManagerOptional;
        try {
            pilotManagerOptional = storage.readPilotManager();
            if (pilotManagerOptional.isEmpty()) {
                pilotManager = new IdentifiableManager<>();
            } else {
                logger.info("Data file for pilot manager found.");
                pilotManager = pilotManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            pilotManager = new IdentifiableManager<>();
        }
        return pilotManager;
    }

    /**
     * Read location manager from data.
     * @param storage the storage object
     * @return the location manager
     */
    private ReadOnlyIdentifiableManager<Location> readLocationManager(Storage storage) {
        ReadOnlyIdentifiableManager<Location> locationManager;
        Optional<? extends ReadOnlyIdentifiableManager<Location>> locationManagerOptional;
        try {
            locationManagerOptional = storage.readLocationManager();
            if (locationManagerOptional.isEmpty()) {
                locationManager = new IdentifiableManager<>();
            } else {
                logger.info("Data file for location manager found.");
                locationManager = locationManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            locationManager = new IdentifiableManager<>();
        }
        return locationManager;
    }

    /**
     * Read crew manager from data.
     * @param storage the storage object
     * @return the crew manager
     */
    private ReadOnlyIdentifiableManager<Crew> readCrewManager(Storage storage) {
        ReadOnlyIdentifiableManager<Crew> crewManager;
        Optional<? extends ReadOnlyIdentifiableManager<Crew>> crewManagerOptional;
        try {
            crewManagerOptional = storage.readCrewManager();
            if (crewManagerOptional.isEmpty()) {
                crewManager = new IdentifiableManager<>();
            } else {
                logger.info("Data file for pilot manager found.");
                crewManager = crewManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            crewManager = new IdentifiableManager<>();
        }
        return crewManager;
    }

    /**
     * Read plane manager from data.
     * @param storage the storage object
     * @return the plane manager
     */
    private ReadOnlyIdentifiableManager<Plane> readPlaneManager(Storage storage) {
        ReadOnlyIdentifiableManager<Plane> planeManager;
        Optional<? extends ReadOnlyIdentifiableManager<Plane>> crewManagerOptional;
        try {
            crewManagerOptional = storage.readPlaneManager();
            if (crewManagerOptional.isEmpty()) {
                planeManager = new IdentifiableManager<>();
            } else {
                logger.info("Data file for pilot manager found.");
                planeManager = crewManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            planeManager = new IdentifiableManager<>();
        }
        return planeManager;
    }

    /**
     * Read flight manager from data.
     * @param storage the storage object
     * @return flight plane manager
     */
    private ReadOnlyIdentifiableManager<Flight> readFlightManager(Storage storage) {
        ReadOnlyIdentifiableManager<Flight> flightManager;
        Optional<? extends ReadOnlyIdentifiableManager<Flight>> flightManagerOptional;
        try {
            flightManagerOptional = storage.readFlightManager();
            if (flightManagerOptional.isEmpty()) {
                flightManager = new IdentifiableManager<>();
            } else {
                logger.info("Data file for pilot manager found.");
                flightManager = flightManagerOptional.get();
            }
        } catch (DataConversionException | IOException e) {
            flightManager = new IdentifiableManager<>();
        }
        return flightManager;
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook addressBook;
        try {
            addressBookOptional = storage.readAddressBook();
            if (addressBookOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            addressBook = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            addressBook = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            addressBook = new AddressBook();
        }

        ReadOnlyIdentifiableManager<Pilot> pilotManager = readPilotManager(storage);
        ReadOnlyIdentifiableManager<Location> locationManager = readLocationManager(storage);
        ReadOnlyIdentifiableManager<Crew> crewManager = readCrewManager(storage);
        ReadOnlyIdentifiableManager<Plane> planeManager = readPlaneManager(storage);
        ReadOnlyIdentifiableManager<Flight> flightManager = readFlightManager(storage);

        return new ModelManager(addressBook, userPrefs,
                                pilotManager, locationManager, crewManager, planeManager, flightManager);
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

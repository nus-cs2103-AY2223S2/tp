package seedu.fitbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.fitbook.commons.core.Config;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.commons.core.Version;
import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.commons.util.ConfigUtil;
import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.logic.Logic;
import seedu.fitbook.logic.LogicManager;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyUserPrefs;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.util.SampleDataUtil;
import seedu.fitbook.model.util.SampleExerciseRoutineDataUtil;
import seedu.fitbook.storage.FitBookStorage;
import seedu.fitbook.storage.JsonFitBookStorage;
import seedu.fitbook.storage.JsonUserPrefsStorage;
import seedu.fitbook.storage.Storage;
import seedu.fitbook.storage.StorageManager;
import seedu.fitbook.storage.UserPrefsStorage;
import seedu.fitbook.storage.routine.FitBookExerciseRoutineStorage;
import seedu.fitbook.storage.routine.JsonFitBookExerciseRoutineStorage;
import seedu.fitbook.ui.Ui;
import seedu.fitbook.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected FitBookModel model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing FitBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FitBookStorage fitBookStorage = new JsonFitBookStorage(userPrefs.getFitBookFilePath());
        FitBookExerciseRoutineStorage exerciseBookStorage =
                new JsonFitBookExerciseRoutineStorage(userPrefs.getFitBookExerciseRoutineFilePath());
        storage = new StorageManager(fitBookStorage, userPrefsStorage, exerciseBookStorage);

        initLogging(config);

        model = initFitBookModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code FitBookModelManager} with the data from {@code storage}'s FitBook, FitBookExerciseRoutine
     * and {@code userPrefs}. <br>
     * The data from the sample FitBook will be used instead if {@code storage}'s FitBook is not found,
     * or an empty FitBook will be used instead if errors occur when reading {@code storage}'s FitBook.
     */
    private FitBookModel initFitBookModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ReadOnlyFitBook initialData;
        ReadOnlyFitBookExerciseRoutine initialExerciseRoutineData;
        initialData = initFitBookData(storage);
        initialExerciseRoutineData = initExerciseRoutineData(storage);
        return new FitBookModelManager(initialData, initialExerciseRoutineData, userPrefs);
    }

    /**
     * Returns a {@code ReadOnlyFitBook} with the initial data from {@code storage}'s FitBook.
     * The initial data from the sample FitBook will be used instead if {@code storage}'s FitBook is not found,
     * or an empty FitBook will be used instead if errors occur when reading {@code storage}'s FitBook.
     */
    private ReadOnlyFitBook initFitBookData(Storage storage) {
        Optional<ReadOnlyFitBook> addressBookOptional;
        ReadOnlyFitBook initialData;
        try {
            addressBookOptional = storage.readFitBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found for FitBook. Will be starting with a sample FitBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleFitBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format for FitBook. "
                    + "Will be starting with an empty FitBook");
            initialData = new FitBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file for FitBook. "
                    + "Will be starting with an empty FitBook");
            initialData = new FitBook();
        }
        return initialData;
    }

    /**
     * Returns a {@code ReadOnlyFitBookExerciseRoutine} with the initial data from {@code storage}'s
     * FitBookExerciseRoutine.
     * The initial data from the sample FitBookExerciseRoutine will be used instead
     * if {@code storage}'s FitBookExerciseRoutine is not found,
     * or an empty FitBookExerciseRoutine will be used instead if errors occur
     * when reading {@code storage}'s FitBookExerciseRoutine.
     */
    private ReadOnlyFitBookExerciseRoutine initExerciseRoutineData(Storage storage) {
        ReadOnlyFitBookExerciseRoutine initialExerciseRoutineData;
        Optional<ReadOnlyFitBookExerciseRoutine> exerciseRoutineOptional;
        try {
            exerciseRoutineOptional = storage.readFitBookExerciseRoutine();
            if (!exerciseRoutineOptional.isPresent()) {
                logger.info("Data file not found for Exercise Routine. "
                        + "Will be starting with a sample Exercise Routine");
            }
            initialExerciseRoutineData = exerciseRoutineOptional
                    .orElseGet(SampleExerciseRoutineDataUtil::getSampleFitBookExerciseRoutine);
        } catch (DataConversionException e) {
            logger.warning("Data file for Exercise Routine not in the correct format. "
                    + "Will be starting with an empty Exercise Routine");
            initialExerciseRoutineData = new FitBookExerciseRoutine();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file for Exercise Routine."
                    + " Will be starting with an empty Exercise Routine");
            initialExerciseRoutineData = new FitBookExerciseRoutine();
        }
        return initialExerciseRoutineData;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty FitBook");
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
        logger.info("Starting FitBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping FitBook ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

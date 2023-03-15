package seedu.modtrek.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.modtrek.commons.core.LogsCenter;
import seedu.modtrek.commons.exceptions.DataConversionException;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.ReadOnlyUserPrefs;
import seedu.modtrek.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DegreeProgressionStorage degreeProgressionStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code DegreeProgressionStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DegreeProgressionStorage degreeProgressionStorage, UserPrefsStorage userPrefsStorage) {
        this.degreeProgressionStorage = degreeProgressionStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getDegreeProgressionFilePath() {
        return degreeProgressionStorage.getDegreeProgressionFilePath();
    }

    @Override
    public Optional<ReadOnlyDegreeProgression> readDegreeProgression() throws DataConversionException, IOException {
        return readDegreeProgression(degreeProgressionStorage.getDegreeProgressionFilePath());
    }

    @Override
    public Optional<ReadOnlyDegreeProgression> readDegreeProgression(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return degreeProgressionStorage.readDegreeProgression(filePath);
    }

    @Override
    public void saveDegreeProgression(ReadOnlyDegreeProgression degreeProgression) throws IOException {
        saveDegreeProgression(degreeProgression, degreeProgressionStorage.getDegreeProgressionFilePath());
    }

    @Override
    public void saveDegreeProgression(ReadOnlyDegreeProgression degreeProgression, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        degreeProgressionStorage.saveDegreeProgression(degreeProgression, filePath);
    }

}

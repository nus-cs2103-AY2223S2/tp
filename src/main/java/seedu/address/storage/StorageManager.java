package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of EduMate data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EduMateStorage eduMateStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EduMateStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EduMateStorage eduMateStorage, UserPrefsStorage userPrefsStorage) {
        this.eduMateStorage = eduMateStorage;
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


    // ================ EduMate methods ==============================

    @Override
    public Path getEduMateFilePath() {
        return eduMateStorage.getEduMateFilePath();
    }

    @Override
    public Optional<ReadOnlyEduMate> readEduMate() throws DataConversionException, IOException {
        return readEduMate(eduMateStorage.getEduMateFilePath());
    }

    @Override
    public Optional<ReadOnlyEduMate> readEduMate(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return eduMateStorage.readEduMate(filePath);
    }

    @Override
    public void saveEduMate(ReadOnlyEduMate eduMate) throws IOException {
        saveEduMate(eduMate, eduMateStorage.getEduMateFilePath());
    }

    @Override
    public void saveEduMate(ReadOnlyEduMate eduMate, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        eduMateStorage.saveEduMate(eduMate, filePath);
    }

}

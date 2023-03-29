package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMathutoring;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;


/**
 * Manages storage of Mathutoring data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MathutoringStorage mathutoringStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MathutoringStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MathutoringStorage mathutoringStorage, UserPrefsStorage userPrefsStorage) {
        this.mathutoringStorage = mathutoringStorage;
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


    // ================ Mathutoring methods ==============================

    @Override
    public Path getMathutoringFilePath() {
        return mathutoringStorage.getMathutoringFilePath();
    }

    @Override
    public Optional<ReadOnlyMathutoring> readMathutoring() throws DataConversionException, IOException {
        return readMathutoring(mathutoringStorage.getMathutoringFilePath());
    }

    @Override
    public Optional<ReadOnlyMathutoring> readMathutoring(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mathutoringStorage.readMathutoring(filePath);
    }

    @Override
    public void saveMathutoring(ReadOnlyMathutoring mathutoring) throws IOException {
        saveMathutoring(mathutoring, mathutoringStorage.getMathutoringFilePath());
    }

    @Override
    public void saveMathutoring(ReadOnlyMathutoring mathutoring, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mathutoringStorage.saveMathutoring(mathutoring, filePath);
    }

}

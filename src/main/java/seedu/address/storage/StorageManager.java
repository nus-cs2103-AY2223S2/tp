package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.storage.parents.ParentsStorage;
import seedu.address.storage.pcclass.PcClassStorage;

/**
 * Manages storage of PCClass data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PcClassStorage pcClassStorage;
    private ParentsStorage parentsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PCClassStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PcClassStorage pcClassStorage, ParentsStorage parentsStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.pcClassStorage = pcClassStorage;
        this.parentsStorage = parentsStorage;
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


    // ================ PCClass methods ==============================
    @Override
    public Path getPcFilePath() {
        return pcClassStorage.getPcFilePath();
    }

    @Override
        public Optional<ReadOnlyPcClass> readPC() throws DataConversionException, IOException {
        return readPC(pcClassStorage.getPcFilePath());
    }

    @Override
    public Optional<ReadOnlyPcClass> readPC(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return pcClassStorage.readPC(filePath);
    }

    @Override
    public void savePC(ReadOnlyPcClass readOnlyPcClass) throws IOException {
        savePC(readOnlyPcClass, pcClassStorage.getPcFilePath());
    }

    @Override
    public void savePC(ReadOnlyPcClass readOnlyPcClass, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        pcClassStorage.savePC(readOnlyPcClass, filePath);
    }

    // ================ Parents methods ==============================
    @Override
    public Path getParentsFilePath() {
        return parentsStorage.getParentsFilePath();
    }

    @Override
    public Optional<ReadOnlyParents> readParents() throws DataConversionException, IOException {
        return readParents(parentsStorage.getParentsFilePath());
    }

    @Override
    public Optional<ReadOnlyParents> readParents(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return parentsStorage.readParents(filePath);
    }

    @Override
    public void saveParents(ReadOnlyParents readOnlyParents) throws IOException {
        saveParents(readOnlyParents, parentsStorage.getParentsFilePath());
    }

    @Override
    public void saveParents(ReadOnlyParents readOnlyParents, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        parentsStorage.saveParents(readOnlyParents, filePath);
    }

}

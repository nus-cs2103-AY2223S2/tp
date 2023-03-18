package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Deck data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final MasterDeckStorage masterDeckStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MasterDeckStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MasterDeckStorage masterDeckStorage, UserPrefsStorage userPrefsStorage) {
        this.masterDeckStorage = masterDeckStorage;
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


    // ================ Deck methods ==============================

    @Override
    public Path getMasterDeckFilePath() {
        return masterDeckStorage.getMasterDeckFilePath();
    }

    @Override
    public Optional<ReadOnlyMasterDeck> readMasterDeck() throws DataConversionException, IOException {
        return readMasterDeck(masterDeckStorage.getMasterDeckFilePath());
    }

    @Override
    public Optional<ReadOnlyMasterDeck> readMasterDeck(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return masterDeckStorage.readMasterDeck(filePath);
    }

    @Override
    public void saveMasterDeck(ReadOnlyMasterDeck masterDeck) throws IOException {
        saveMasterDeck(masterDeck, masterDeckStorage.getMasterDeckFilePath());
    }

    @Override
    public void saveMasterDeck(ReadOnlyMasterDeck masterDeck, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        masterDeckStorage.saveMasterDeck(masterDeck, filePath);
    }

}

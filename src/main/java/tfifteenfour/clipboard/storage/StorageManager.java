package tfifteenfour.clipboard.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.ReadOnlyUserPrefs;
import tfifteenfour.clipboard.model.UserPrefs;

/**
 * Manages storage of Roster data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RosterStorage rosterStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code Roster} and {@code UserPrefStorage}.
     */
    public StorageManager(RosterStorage rosterStorage, UserPrefsStorage userPrefsStorage) {
        this.rosterStorage = rosterStorage;
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


    // ================ Roster methods ==============================

    @Override
    public Path getRosterFilePath() {
        return rosterStorage.getRosterFilePath();
    }

    @Override
    public Optional<ReadOnlyRoster> readRoster() throws DataConversionException, IOException {
        return readRoster(rosterStorage.getRosterFilePath());
    }

    @Override
    public Optional<ReadOnlyRoster> readRoster(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return rosterStorage.readRoster(filePath);
    }

    @Override
    public void saveRoster(ReadOnlyRoster roster) throws IOException {
        saveRoster(roster, rosterStorage.getRosterFilePath());
    }

    @Override
    public void saveRoster(ReadOnlyRoster roster, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        rosterStorage.saveRoster(roster, filePath);
    }

}

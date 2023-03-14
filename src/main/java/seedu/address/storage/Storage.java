package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTrackerFilePath();

    @Override
    Optional<ReadOnlyTracker> readTracker() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyTracker> readTracker(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveTracker(ReadOnlyTracker tracker) throws IOException;

    @Override
    void saveTracker(ReadOnlyTracker tracker, Path filePath) throws IOException;
}

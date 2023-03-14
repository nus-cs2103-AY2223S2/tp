package seedu.dengue.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.ReadOnlyUserPrefs;
import seedu.dengue.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends DengueHotspotStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDengueHotspotTrackerFilePath();

    @Override
    Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker() throws DataConversionException, IOException;

    @Override
    void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker) throws IOException;

}

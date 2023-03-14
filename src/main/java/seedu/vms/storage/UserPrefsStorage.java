package seedu.vms.storage;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.DataConversionException;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;

/**
 * Represents a storage for {@link seedu.vms.model.UserPrefs}.
 */
public interface UserPrefsStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    UserPrefs readUserPrefs() throws IOException;

    /**
     * Saves the given {@link seedu.vms.model.ReadOnlyUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

}

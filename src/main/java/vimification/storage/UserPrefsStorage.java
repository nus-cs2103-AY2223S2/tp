package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.model.UserPrefs;

/**
 * Represents a storage for {@link vimification.model.UserPrefs}.
 */
public interface UserPrefsStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage. Returns {@code Optional.empty()} if storage file is not
     * found.
     *
     * @throws IOException if there was any problem when reading from the storage.
     */
    UserPrefs readUserPrefs() throws IOException;

    /**
     * Saves the given {@link vimification.model.UserPrefs} to the storage.
     *
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem when writing to the file.
     */
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

}

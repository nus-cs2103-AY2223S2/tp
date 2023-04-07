package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.model.UserPrefs;

/**
 * Represents a storage for {@link UserPrefs}.
 */
public interface UserPrefsStorage {

    /**
     * Returns the file path of the data file.
     *
     * @return the file path of the data file
     */
    Path getUserPrefsFilePath();

    /**
     * Reads from the data file and constructs a {@code UserPrefs} instance from the data read.
     *
     * @return a {@code UserPrefs} instance constructed from the file data
     * @throws IOException if there is any problem when reading from the storage
     */
    UserPrefs readUserPrefs() throws IOException;

    /**
     * Saves the given {@code UserPrefs} to the storage.
     *
     * @param userPrefs the {@code UserPrefs} instance to be saved
     * @throws IOException if there is any problem when writing to the file
     */
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;
}

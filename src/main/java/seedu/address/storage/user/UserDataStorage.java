package seedu.address.storage.user;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserData;

/**
 * Represents a storage for {@link seedu.address.model.user.UserData}.
 */
public interface UserDataStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getUserDataFilePath();

    /**
     * Returns UserData as a {@link ReadOnlyUserData}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUserData> readUserData() throws DataConversionException, IOException;

    /**
     * @see #getUserDataFilePath()
     */
    Optional<ReadOnlyUserData> readUserData(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUserData} to the storage.
     * @param userData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserData(ReadOnlyUserData userData) throws IOException;

    /**
     * @see #saveUserData(ReadOnlyUserData)
     */
    void saveUserData(ReadOnlyUserData userData, Path filePath) throws IOException;
}

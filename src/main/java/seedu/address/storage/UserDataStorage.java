package seedu.address.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.UserData;

/**
 * Represents a storage for user password
 */
public interface UserDataStorage {

    /**
     * Returns the file path of the user password file
     * @return path of user password file
     */
    Path getUserDataFilePath();

    /**
     * Returns the user data from storage
     * @return user data
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserData> readUserData() throws DataConversionException, IOException;

    /**
     * Saves the user data to the storage
     * @param userData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserData(ReadOnlyUserData userData) throws IOException;
}

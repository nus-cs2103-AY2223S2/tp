package taa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import taa.commons.exceptions.DataConversionException;
import taa.model.ReadOnlyAddressBook;
import taa.model.ReadOnlyUserPrefs;
import taa.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}

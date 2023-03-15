package mycelium.mycelium.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import mycelium.mycelium.commons.exceptions.DataConversionException;
import mycelium.mycelium.model.ReadOnlyAddressBook;
import mycelium.mycelium.model.ReadOnlyUserPrefs;
import mycelium.mycelium.model.UserPrefs;

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

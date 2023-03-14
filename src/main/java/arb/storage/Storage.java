package arb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import arb.commons.exceptions.DataConversionException;
import arb.model.ReadOnlyAddressBook;
import arb.model.ReadOnlyUserPrefs;
import arb.model.UserPrefs;

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

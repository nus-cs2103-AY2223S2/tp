package t154.CLIpboard.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import t154.CLIpboard.commons.exceptions.DataConversionException;
import t154.CLIpboard.model.ReadOnlyAddressBook;
import t154.CLIpboard.model.ReadOnlyUserPrefs;
import t154.CLIpboard.model.UserPrefs;

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

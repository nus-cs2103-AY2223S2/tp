package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.addressbook.AddressBookStorage;
import seedu.address.storage.user.UserDataStorage;
import seedu.address.storage.userpref.UserPrefsStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, UserDataStorage {

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

    @Override
    Optional<ReadOnlyUserData> readUserData() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyUserData> readUserData(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveUserData(ReadOnlyUserData addressBook) throws IOException;

    @Override
    void saveUserData(ReadOnlyUserData userData, Path filePath) throws IOException;


}

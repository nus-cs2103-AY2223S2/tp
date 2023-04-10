package seedu.loyaltylift.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.loyaltylift.commons.exceptions.DataConversionException;
import seedu.loyaltylift.model.ReadOnlyAddressBook;
import seedu.loyaltylift.model.ReadOnlyUserPrefs;
import seedu.loyaltylift.model.UserPrefs;

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

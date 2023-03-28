package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TaskStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;


    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;


    @Override
    Path getAddressBookFilePath();

    @Override
    Path getUserPrefsFilePath();

    @Override
    Path getTaskBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Optional<ReadOnlyTaskBook> readTasks() throws DataConversionException, IOException;

    @Override
    void saveTasks(ReadOnlyTaskBook addressBook) throws IOException;


}

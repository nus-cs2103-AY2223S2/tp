package seedu.event.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.event.commons.exceptions.DataConversionException;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.ReadOnlyUserPrefs;
import seedu.event.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends EventBookStorage, ContactListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getEventBookFilePath();

    @Override
    Path getContactListFilePath();

    @Override
    Optional<ReadOnlyEventBook> readEventBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException;

    @Override
    void saveEventBook(ReadOnlyEventBook eventBook) throws IOException;

    @Override
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

}

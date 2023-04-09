package seedu.quickcontacts.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.quickcontacts.commons.exceptions.DataConversionException;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.ReadOnlyUserPrefs;
import seedu.quickcontacts.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends QuickBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getQuickBookFilePath();

    @Override
    Optional<ReadOnlyQuickBook> readQuickBook() throws DataConversionException, IOException;

    @Override
    void saveQuickBook(ReadOnlyQuickBook quickBook) throws IOException;

}

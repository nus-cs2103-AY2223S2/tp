package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface ApplicationStorage extends InternshipBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternshipBookFilePath();

    @Override
    Optional<ReadOnlyInternshipBook> readInternshipBook() throws DataConversionException, IOException;

    @Override
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook) throws IOException;

}

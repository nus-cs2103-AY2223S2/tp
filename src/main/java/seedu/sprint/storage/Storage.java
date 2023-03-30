package seedu.sprint.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.sprint.commons.exceptions.DataConversionException;
import seedu.sprint.model.ReadOnlyInternshipBook;
import seedu.sprint.model.ReadOnlyUserPrefs;
import seedu.sprint.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends InternshipBookStorage, UserPrefsStorage {

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

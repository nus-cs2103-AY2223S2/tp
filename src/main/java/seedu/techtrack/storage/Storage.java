package seedu.techtrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.techtrack.commons.exceptions.DataConversionException;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.ReadOnlyUserPrefs;
import seedu.techtrack.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends RoleBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getRoleBookFilePath();

    @Override
    Optional<ReadOnlyRoleBook> readRoleBook() throws DataConversionException, IOException;

    @Override
    void saveRoleBook(ReadOnlyRoleBook roleBook) throws IOException;

}

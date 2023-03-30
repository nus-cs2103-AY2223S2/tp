package seedu.roles.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.roles.commons.exceptions.DataConversionException;
import seedu.roles.model.ReadOnlyRoleBook;
import seedu.roles.model.ReadOnlyUserPrefs;
import seedu.roles.model.UserPrefs;

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

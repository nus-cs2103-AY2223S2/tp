package seedu.internship.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.ReadOnlyInternBuddy;
import seedu.internship.model.ReadOnlyUserPrefs;
import seedu.internship.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InternBuddyStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternBuddyFilePath();

    @Override
    Optional<ReadOnlyInternBuddy> readInternBuddy() throws DataConversionException, IOException;

    @Override
    void saveInternBuddy(ReadOnlyInternBuddy internBuddy) throws IOException;

}

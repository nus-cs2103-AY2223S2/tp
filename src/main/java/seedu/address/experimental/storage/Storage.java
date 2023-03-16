package seedu.address.experimental.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.ReadOnlyUserPrefs;
import seedu.address.experimental.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends RerollStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getRerollFilePath();

    @Override
    Optional<ReadOnlyReroll> readReroll() throws DataConversionException, IOException;

    @Override
    void saveReroll(ReadOnlyReroll reroll) throws IOException;

}

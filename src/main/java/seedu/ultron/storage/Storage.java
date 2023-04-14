package seedu.ultron.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ultron.commons.exceptions.DataConversionException;
import seedu.ultron.model.ReadOnlyUltron;
import seedu.ultron.model.ReadOnlyUserPrefs;
import seedu.ultron.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UltronStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getUltronFilePath();

    @Override
    Optional<ReadOnlyUltron> readUltron() throws DataConversionException, IOException;

    @Override
    void saveUltron(ReadOnlyUltron ultron) throws IOException;

}

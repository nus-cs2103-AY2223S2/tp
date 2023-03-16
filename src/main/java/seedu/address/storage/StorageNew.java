package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.ReadOnlyUserPrefsNew;
import seedu.address.model.UserPrefsNew;

/**
 * API of the Storage component
 */
public interface StorageNew extends UltronStorage, UserPrefsStorageNew {

    @Override
    Optional<UserPrefsNew> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefsNew userPrefs) throws IOException;

    @Override
    Path getUltronFilePath();

    @Override
    Optional<ReadOnlyUltron> readUltron() throws DataConversionException, IOException;

    @Override
    void saveUltron(ReadOnlyUltron ultron) throws IOException;

}

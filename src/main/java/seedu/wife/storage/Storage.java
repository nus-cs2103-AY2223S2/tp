package seedu.wife.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.wife.commons.exceptions.DataConversionException;
import seedu.wife.model.ReadOnlyUserPrefs;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends WifeStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getWifeFilePath();

    @Override
    Optional<ReadOnlyWife> readWife() throws DataConversionException, IOException;

    @Override
    void saveWife(ReadOnlyWife wife) throws IOException;

}

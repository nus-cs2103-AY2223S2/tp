package seedu.sudohr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.sudohr.commons.exceptions.DataConversionException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.ReadOnlyUserPrefs;
import seedu.sudohr.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SudoHrStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSudoHrFilePath();

    @Override
    Optional<ReadOnlySudoHr> readSudoHr() throws DataConversionException, IOException;

    @Override
    void saveSudoHr(ReadOnlySudoHr sudoHr) throws IOException;

}

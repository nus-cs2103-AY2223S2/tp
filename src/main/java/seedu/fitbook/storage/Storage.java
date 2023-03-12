package seedu.fitbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.ReadOnlyUserPrefs;
import seedu.fitbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FitBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFitBookFilePath();

    @Override
    Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException, IOException;

    @Override
    void saveFitBook(ReadOnlyFitBook fitBook) throws IOException;

}

package seedu.addressbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.addressbook.commons.exceptions.DataConversionException;
import seedu.addressbook.model.ReadOnlyFitBook;
import seedu.addressbook.model.ReadOnlyUserPrefs;
import seedu.addressbook.model.UserPrefs;

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
    void saveFitBook(ReadOnlyFitBook addressBook) throws IOException;

}

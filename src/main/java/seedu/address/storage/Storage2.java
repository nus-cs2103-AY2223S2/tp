package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.ReadOnlyUserPrefs2;
import seedu.address.model.UserPrefs2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage2 extends ListingBookStorage, UserPrefsStorage2 {

    @Override
    Optional<UserPrefs2> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs2 userPrefs) throws IOException;

    @Override
    Path getListingBookFilePath();

    @Override
    Optional<ReadOnlyListingBook> readListingBook() throws DataConversionException, IOException;

    @Override
    void saveListingBook(ReadOnlyListingBook listingBook) throws IOException;

}

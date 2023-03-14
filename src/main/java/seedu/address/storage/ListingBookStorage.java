package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyListingBook;

/**
 * Represents a storage for {@link seedu.address.model.ListingBook}.
 */
public interface ListingBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getListingBookFilePath();

    /**
     * Returns ListingBook data as a {@link ReadOnlyListingBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyListingBook> readListingBook() throws DataConversionException, IOException;

    /**
     * @see #getListingBookFilePath() ()
     */
    Optional<ReadOnlyListingBook> readListingBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyListingBook} to the storage.
     * @param listingBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveListingBook(ReadOnlyListingBook listingBook) throws IOException;

    /**
     * @see #saveListingBook(ReadOnlyListingBook)
     */
    void saveListingBook(ReadOnlyListingBook listingBook, Path filePath) throws IOException;

}

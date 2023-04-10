package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyListingBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonListingBookStorage implements ListingBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonListingBookStorage.class);

    private Path filePath;

    public JsonListingBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getListingBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyListingBook> readListingBook() throws DataConversionException {
        return readListingBook(filePath);
    }

    /**
     * Similar to {@link #readListingBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyListingBook> readListingBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableListingBook> jsonListingBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableListingBook.class);
        if (!jsonListingBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonListingBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveListingBook(ReadOnlyListingBook listingBook) throws IOException {
        saveListingBook(listingBook, filePath);
    }

    /**
     * Similar to {@link #saveListingBook(ReadOnlyListingBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveListingBook(ReadOnlyListingBook listingBook, Path filePath) throws IOException {
        requireNonNull(listingBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableListingBook(listingBook), filePath);
    }

}

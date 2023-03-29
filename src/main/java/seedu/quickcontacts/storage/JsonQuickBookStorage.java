package seedu.quickcontacts.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.quickcontacts.commons.core.LogsCenter;
import seedu.quickcontacts.commons.exceptions.DataConversionException;
import seedu.quickcontacts.commons.exceptions.IllegalValueException;
import seedu.quickcontacts.commons.util.FileUtil;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.model.ReadOnlyQuickBook;

/**
 * A class to access QuickBook data stored as a json file on the hard disk.
 */
public class JsonQuickBookStorage implements QuickBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonQuickBookStorage.class);

    private Path filePath;

    public JsonQuickBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getQuickBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyQuickBook> readQuickBook() throws DataConversionException {
        return readQuickBook(filePath);
    }

    /**
     * Similar to {@link #readQuickBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyQuickBook> readQuickBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableQuickBook> jsonQuickBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableQuickBook.class);
        if (!jsonQuickBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonQuickBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveQuickBook(ReadOnlyQuickBook quickBook) throws IOException {
        saveQuickBook(quickBook, filePath);
    }

    /**
     * Similar to {@link #saveQuickBook(ReadOnlyQuickBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveQuickBook(ReadOnlyQuickBook quickBook, Path filePath) throws IOException {
        requireNonNull(quickBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableQuickBook(quickBook), filePath);
    }

}

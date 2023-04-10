package seedu.event.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.event.commons.core.LogsCenter;
import seedu.event.commons.exceptions.DataConversionException;
import seedu.event.commons.exceptions.IllegalValueException;
import seedu.event.commons.util.FileUtil;
import seedu.event.commons.util.JsonUtil;
import seedu.event.model.ReadOnlyEventBook;

/**
 * A class to access EventBook data stored as a json file on the hard disk.
 */
public class JsonEventBookStorage implements EventBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEventBookStorage.class);

    private Path filePath;

    public JsonEventBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEventBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEventBook> readEventBook() throws DataConversionException {
        return readEventBook(filePath);
    }

    /**
     * Similar to {@link #readEventBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEventBook> readEventBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEventBook> jsonEventBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableEventBook.class);
        if (!jsonEventBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEventBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEventBook(ReadOnlyEventBook eventBook) throws IOException {
        saveEventBook(eventBook, filePath);
    }

    /**
     * Similar to {@link #saveEventBook(ReadOnlyEventBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEventBook(ReadOnlyEventBook eventBook, Path filePath) throws IOException {
        requireNonNull(eventBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEventBook(eventBook), filePath);
    }

}

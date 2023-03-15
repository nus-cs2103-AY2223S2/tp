package seedu.fitbook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.commons.util.FileUtil;
import seedu.fitbook.commons.util.JsonUtil;
import seedu.fitbook.model.ReadOnlyFitBook;

/**
 * A class to access FitBook data stored as a json file on the hard disk.
 */
public class JsonFitBookStorage implements FitBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFitBookStorage.class);

    private Path filePath;

    public JsonFitBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFitBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException {
        return readFitBook(filePath);
    }

    /**
     * Similar to {@link #readFitBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFitBook> readFitBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFitBook> jsonFitBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableFitBook.class);
        if (!jsonFitBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFitBook.get().toFitBookModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook fitBook) throws IOException {
        saveFitBook(fitBook, filePath);
    }

    /**
     * Similar to {@link #saveFitBook(ReadOnlyFitBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFitBook(ReadOnlyFitBook fitBook, Path filePath) throws IOException {
        requireNonNull(fitBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFitBook(fitBook), filePath);
    }

}

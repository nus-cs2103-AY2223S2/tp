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
import seedu.address.model.ReadOnlyTaskBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonTaskStorage implements TaskStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskStorage.class);

    private Path filePath;

    public JsonTaskStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTasks() throws DataConversionException {
        return readTasks(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskBook> readTasks(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskBook> jsonTasks = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskBook.class);
        if (!jsonTasks.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTasks.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTasks(ReadOnlyTaskBook taskBook) throws IOException {
        saveTasks(taskBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTasks(ReadOnlyTaskBook taskBook, Path filePath) throws IOException {
        requireNonNull(taskBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskBook(taskBook), filePath);
    }

}

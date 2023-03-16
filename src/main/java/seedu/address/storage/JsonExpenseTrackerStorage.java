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
import seedu.address.model.ReadOnlyExpenseTracker;

/**
 * A class to access ExpenseTracker data stored as a json file on the hard disk.
 */
public class JsonExpenseTrackerStorage implements ExpenseTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonExpenseTrackerStorage.class);
    private Path filePath;

    public JsonExpenseTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getExpenseTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyExpenseTracker> readExpenseTracker() throws DataConversionException {
        return readExpenseTracker(filePath);
    }

    /**
     * Similar to {@link #readExpenseTracker()}.
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyExpenseTracker> readExpenseTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableExpenseTracker> jsonExpenseTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableExpenseTracker.class);
        if (!jsonExpenseTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonExpenseTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker) throws IOException {
        saveExpenseTracker(expenseTracker, filePath);
    }

    /**
     * Similar to {@link #saveExpenseTracker(ReadOnlyExpenseTracker)}.
     * @param filePath location of the data. Cannot be null.
     */
    public void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker, Path filePath) throws IOException {
        requireNonNull(expenseTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableExpenseTracker(expenseTracker), filePath);
    }

}

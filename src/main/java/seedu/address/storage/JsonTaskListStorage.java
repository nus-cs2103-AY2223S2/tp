package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;

/**
 * A class to access {@code TaskList} data stored as a json file on the hard disk.
 */
public class JsonTaskListStorage implements TaskListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskListStorage.class);

    private final Path filePath;

    public JsonTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Returns {@code TaskList} data as a {@link ReadOnlyTaskList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param filePath The path of the {@code TaskList} data file.
     * @throws DataConversionException If the data in storage is not in the expected format.
     */
    @Override
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);
        if (jsonTaskList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList TaskList) throws IOException {
        saveTaskList(TaskList, filePath);
    }

    /**
     * Similar to {@link #saveTaskList(ReadOnlyTaskList)}.
     *
     * @param filePath Location of the data. Cannot be null.
     */
    @Override
    public void saveTaskList(ReadOnlyTaskList TaskList, Path filePath) throws IOException {
        requireAllNonNull(TaskList, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(TaskList), filePath);
    }

}

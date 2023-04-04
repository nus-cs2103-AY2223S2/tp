package storage.task.todo;

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
import seedu.address.model.ReadOnlyTodoList;

/**
 * A class to access TodoList data stored as a json file on the hard disk.
 */
public class JsonTodoListStorage implements TodoListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTodoListStorage.class);

    private Path filePath;

    public JsonTodoListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTodoListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException {
        return readTodoList(filePath);
    }

    /**
     * Similar to {@link #readTodoList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTodoList> readTodoList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTodoList> jsonTodoList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTodoList.class);
        if (!jsonTodoList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTodoList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList todoList) throws IOException {
        saveTodoList(todoList, filePath);
    }

    /**
     * Similar to {@link #saveTodoList(ReadOnlyTodoList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException {
        requireNonNull(todoList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTodoList(todoList), filePath);
    }

}

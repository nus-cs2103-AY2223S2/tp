package seedu.address.storage.task.todo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTodoList;

/**
 * Represents a storage for {@link seedu.address.model.TodoList}.
 */
public interface TodoListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTodoListFilePath();

    /**
     * Returns TodoList data as a {@link ReadOnlyTodoList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException;

    /**
     * @see #getTodoListFilePath()
     */
    Optional<ReadOnlyTodoList> readTodoList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTodoList} to the storage.
     *
     * @param todoList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTodoList(ReadOnlyTodoList todoList) throws IOException;

    /**
     * @see #saveTodoList(ReadOnlyTodoList)
     */
    void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException;

}

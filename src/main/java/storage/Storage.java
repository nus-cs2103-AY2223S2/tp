package storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyNote;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import storage.task.note.NoteStorage;
import storage.task.todo.TodoListStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, TodoListStorage, NoteStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getTodoListFilePath();

    @Override
    Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException;

    @Override
    void saveTodoList(ReadOnlyTodoList todoList) throws IOException;

    @Override
    Path getNoteListFilePath();

    @Override
    Optional<ReadOnlyNote> readNoteList() throws DataConversionException, IOException;

    @Override
    void saveNoteList(ReadOnlyNote notes) throws IOException;

}

package seedu.address.logic.commands.task.note;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NoteList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListNoteCommand.
 */
public class ListNoteCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
    }

    @Test
    public void execute_successNoteList() {
        assertCommandSuccess(new ListNoteCommand(), model, ListNoteCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nullNoteList_alertNullNoteList() {
        Model nullNoteModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                new NoteList());
        Model expectedNoteModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                new NoteList());
        assertCommandSuccess(new ListNoteCommand(), nullNoteModel,
                ListNoteCommand.MESSAGE_NO_NOTE, expectedNoteModel);
    }
}

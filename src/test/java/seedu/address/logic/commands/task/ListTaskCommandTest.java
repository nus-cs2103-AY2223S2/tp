package seedu.address.logic.commands.task;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NoteList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

/**
* Contains integration tests (interaction with the Model) for {@code ListTaskCommand}.
*/
public class ListTaskCommandTest {

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
    public void execute_successTaskList() {
        assertCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nullLists_alertNullLists() {
        Model newNullModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TodoList(), new NoteList());
        Model expectedNullModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                new TodoList(), new NoteList());
        assertCommandSuccess(new ListTaskCommand(), newNullModel,
                ListTaskCommand.MESSAGE_NO_APPLICATIONS, expectedNullModel);
    }
}

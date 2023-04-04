package seedu.address.logic.commands.task.todo;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ListTodoCommand}.
 */
public class ListTodoCommandTest {
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
    public void execute_successTodoList() {
        assertCommandSuccess(new ListTodoCommand(), model, ListTodoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nullTodoList_alertNullTodoList() {
        Model nullTodoModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TodoList(),
                getTypicalNoteList());
        Model expectedTodoModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                new TodoList(), getTypicalNoteList());
        assertCommandSuccess(new ListTodoCommand(), nullTodoModel,
                ListTodoCommand.MESSAGE_NO_APPLICATIONS, expectedTodoModel);
    }
}

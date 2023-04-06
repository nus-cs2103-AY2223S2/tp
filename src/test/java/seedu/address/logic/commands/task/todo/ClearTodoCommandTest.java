package seedu.address.logic.commands.task.todo;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ClearTodoCommand}.
 */
public class ClearTodoCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_emptyTodoList_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TodoList(), getTypicalNoteList());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TodoList(),
                getTypicalNoteList());

        assertCommandSuccess(new ClearTodoCommand(), model, ClearTodoCommand.MESSAGE_NULL, expectedModel);
    }


    @Test
    public void execute_nonEmptyTodoList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TodoList(),
                getTypicalNoteList());

        ClearTodoCommand clearTodoCommand = new ClearTodoCommand();
        CommandResult result = clearTodoCommand.execute(model);

        assertCommandSuccess(result, model, ClearTodoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

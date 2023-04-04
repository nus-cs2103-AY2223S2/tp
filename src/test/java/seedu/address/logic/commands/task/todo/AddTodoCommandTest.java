package seedu.address.logic.commands.task.todo;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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
import seedu.address.model.task.InternshipTodo;
import seedu.address.testutil.InternshipTodoBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTodoCommand}.
 */
public class AddTodoCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_todoAcceptedByModel_addSuccessful() {
        InternshipTodo validTodo = new InternshipTodoBuilder().build();
        TodoList todoList = getTypicalTodoList();
        todoList.addTodo(validTodo);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                todoList, getTypicalNoteList());

        assertCommandSuccess(new AddTodoCommand(validTodo), model,
                String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo), expectedModel);
    }

    @Test
    public void execute_duplicateTodo_throwsCommandException() {
        InternshipTodo validTodo = model.getFilteredTodoList().get(0);

        assertCommandFailure(new AddTodoCommand(validTodo), model, AddTodoCommand.MESSAGE_DUPLICATE_TODO);
    }
}

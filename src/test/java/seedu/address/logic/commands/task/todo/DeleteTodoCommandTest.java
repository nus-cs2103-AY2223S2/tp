package seedu.address.logic.commands.task.todo;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.InternshipTodo;

/**
 * Contains integration tests (interaction with the Model) for {@code DeleteTodoCommand}.
 */
public class DeleteTodoCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_validTodoList_success() {
        InternshipTodo todoToDelete = model.getFilteredTodoList()
                .get(Index.fromOneBased(3).getZeroBased());
        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(Index.fromOneBased(3));

        String expectedMessage = String.format(DeleteTodoCommand.MESSAGE_DELETE_TODO_SUCCESS, todoToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.deleteTodo(todoToDelete);

        try {
            CommandResult deleteMessage = deleteTodoCommand.execute(model);
            assertCommandSuccess(deleteMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }

    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(outOfBoundIndex);

        assertCommandFailure(deleteTodoCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }
}

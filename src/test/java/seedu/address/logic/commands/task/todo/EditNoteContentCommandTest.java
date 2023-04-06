package seedu.address.logic.commands.task.todo;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.NoteContent;

/**
 * Contains integration tests (interaction with the Model) for {@code EditNoteContentCommand}.
 */
public class EditNoteContentCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_editNoteContent_success() {
        EditNoteContentCommand editNoteContentCommand = new EditNoteContentCommand(
                Index.fromOneBased(3), new NoteContent("New note for test"));
        InternshipTodo editedTodo = model.getFilteredTodoList().get(Index.fromOneBased(3).getZeroBased());
        editedTodo.setNote(new NoteContent("New note for test"));

        String expectedMessage = String.format(EditNoteContentCommand.MESSAGE_UPDATE_STATUS_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setTodo(model.getFilteredTodoList().get(Index.fromOneBased(3).getZeroBased()),
                editedTodo);

        assertCommandSuccess(editNoteContentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTodoIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        EditNoteContentCommand editNoteCommand = new EditNoteContentCommand(outOfBoundIndex,
                new NoteContent("New note for test"));

        assertCommandFailure(editNoteCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
    }
}

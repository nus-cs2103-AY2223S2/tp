package seedu.address.logic.commands.task.todo;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;

/**
 * Contains integration tests (interaction with the Model) for {@code EditDeadlineCommand}.
 */
public class EditDeadlineCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_editTodoDeadline_success() {
        EditDeadlineCommand editDeadlineCommand = new EditDeadlineCommand(
                Index.fromOneBased(3), new ApplicationDeadline(LocalDate.parse("2023-09-09")));
        InternshipTodo editedTodo = model.getFilteredTodoList().get(Index.fromOneBased(3).getZeroBased());
        editedTodo.setDeadline(new ApplicationDeadline(LocalDate.parse("2023-09-09")));

        String expectedMessage = String.format(EditDeadlineCommand.MESSAGE_UPDATE_STATUS_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setTodo(model.getFilteredTodoList().get(Index.fromOneBased(3).getZeroBased()),
                editedTodo);

        assertCommandSuccess(editDeadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editTodoDeadline_successNullNote() {
        InternshipTodo newTodo = new InternshipTodo(new CompanyName("Tesla"), new JobTitle("Tester"),
                new ApplicationDeadline(LocalDate.parse("2023-05-05")));
        model.addTodo(newTodo);

        int targetIndex = model.getFilteredTodoList().size();
        EditDeadlineCommand editDeadlineCommand = new EditDeadlineCommand(
                Index.fromOneBased(targetIndex),
                new ApplicationDeadline(LocalDate.parse("2023-09-09")));

        InternshipTodo editedTodo = model.getFilteredTodoList().get(Index.fromOneBased(targetIndex).getZeroBased());
        editedTodo.setDeadline(new ApplicationDeadline(LocalDate.parse("2023-09-09")));

        String expectedMessage = String.format(EditDeadlineCommand.MESSAGE_UPDATE_STATUS_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), model.getTodoList(),
                getTypicalNoteList());
        expectedModel.setTodo(model.getFilteredTodoList().get(Index.fromOneBased(targetIndex).getZeroBased()),
                editedTodo);

        assertCommandSuccess(editDeadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTodoIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        EditDeadlineCommand editDeadlineCommand = new EditDeadlineCommand(outOfBoundIndex,
                new ApplicationDeadline(LocalDate.parse("2023-09-09")));

        assertCommandFailure(editDeadlineCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
    }
}

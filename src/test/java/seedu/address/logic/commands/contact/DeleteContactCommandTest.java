package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_SOFTWARE_TESTER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.model.application.InternshipStatus.ACCEPTED;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InterviewDate;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteContactCommand}.
 */
public class DeleteContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternshipApplication applicationToDeleteContact =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST_APPLICATION);

        InternshipApplication contactDeletedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_META)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_TESTER)
                .withInterviewDate(new InterviewDate("2023-04-01 08:00 PM"))
                .withStatus(ACCEPTED).build();
        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_CONTACT_SUCCESS,
                applicationToDeleteContact);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(applicationToDeleteContact, contactDeletedApplication);

        assertCommandSuccess(deleteContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);

        InternshipApplication applicationToDeleteContact =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST_APPLICATION);

        InternshipApplication contactDeletedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_META)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_TESTER)
                .withInterviewDate(new InterviewDate("2023-04-01 08:00 PM"))
                .withStatus(ACCEPTED).build();

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_CONTACT_SUCCESS,
                applicationToDeleteContact);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(applicationToDeleteContact, contactDeletedApplication);

        assertCommandSuccess(deleteContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteContactCommand deleteFirstCommand = new DeleteContactCommand(INDEX_FIRST_APPLICATION);
        DeleteContactCommand deleteSecondCommand = new DeleteContactCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteContactCommand deleteFirstCommandCopy = new DeleteContactCommand(INDEX_FIRST_APPLICATION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}

package seedu.address.logic.commands.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_NETWORK_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_APPLICATION;
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
import seedu.address.model.person.InternshipApplication;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDocumentsCommand}.
 */
public class DeleteDocumentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternshipApplication applicationToDeleteDocuments =
                model.getSortedFilteredInternshipList().get(INDEX_FIFTH_APPLICATION.getZeroBased());
        DeleteDocumentsCommand deleteDocumentsCommand = new DeleteDocumentsCommand(INDEX_FIFTH_APPLICATION);

        InternshipApplication documentsDeletedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_NETFLIX)
                .withJobTitle(VALID_JOB_TITLE_NETWORK_ENGINEER).build();
        String expectedMessage = String.format(DeleteDocumentsCommand.MESSAGE_DELETE_DOCUMENTS_SUCCESS,
                applicationToDeleteDocuments);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(applicationToDeleteDocuments, documentsDeletedApplication);

        assertCommandSuccess(deleteDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        DeleteDocumentsCommand deleteDocumentsCommand = new DeleteDocumentsCommand(outOfBoundIndex);

        assertCommandFailure(deleteDocumentsCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIFTH_APPLICATION);

        InternshipApplication applicationToDeleteDocuments =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteDocumentsCommand deleteDocumentsCommand = new DeleteDocumentsCommand(INDEX_FIRST_APPLICATION);

        InternshipApplication contactDeletedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_NETFLIX)
                .withJobTitle(VALID_JOB_TITLE_NETWORK_ENGINEER).build();

        String expectedMessage = String.format(DeleteDocumentsCommand.MESSAGE_DELETE_DOCUMENTS_SUCCESS,
                applicationToDeleteDocuments);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(applicationToDeleteDocuments, contactDeletedApplication);

        assertCommandSuccess(deleteDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        DeleteDocumentsCommand deleteDocumentsCommand = new DeleteDocumentsCommand(outOfBoundIndex);

        assertCommandFailure(deleteDocumentsCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteDocumentsCommand deleteFourthCommand = new DeleteDocumentsCommand(INDEX_FOURTH_APPLICATION);
        DeleteDocumentsCommand deleteFifthCommand = new DeleteDocumentsCommand(INDEX_FIFTH_APPLICATION);

        // same object -> returns true
        assertEquals(deleteFourthCommand, deleteFourthCommand);

        // same values -> returns true
        DeleteDocumentsCommand deleteFourthCommandCopy = new DeleteDocumentsCommand(INDEX_FOURTH_APPLICATION);
        assertEquals(deleteFourthCommand, deleteFourthCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFourthCommand);

        // null -> returns false
        assertNotEquals(null, deleteFourthCommand);

        // different person -> returns false
        assertNotEquals(deleteFourthCommand, deleteFifthCommand);
    }
}

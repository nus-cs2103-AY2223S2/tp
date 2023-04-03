package seedu.address.logic.commands.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_PRODUCT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_GOOGLE;
import static seedu.address.testutil.TypicalDocuments.DOCUMENTS_NETFLIX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInternships.getTypicalNoteList;
import static seedu.address.testutil.TypicalInternships.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;
import seedu.address.model.person.InternshipApplication;
import seedu.address.testutil.DocumentsBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddDocumentsCommand.
 */
public class AddDocumentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Documents documents = new DocumentsBuilder().build();
        InternshipApplication initialApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
                .withJobTitle(VALID_JOB_TITLE_PRODUCT_MANAGER).build();
        InternshipApplication documentsAddedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
                .withJobTitle(VALID_JOB_TITLE_PRODUCT_MANAGER)
                .withDocuments(documents).build();
        AddDocumentsCommand addDocumentsCommand = new AddDocumentsCommand(INDEX_FOURTH_APPLICATION, documents);

        String expectedMessage = String.format(AddDocumentsCommand.MESSAGE_ADD_DOCUMENTS_SUCCESS, initialApplication
             + "\n" + documents);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(3), documentsAddedApplication);

        assertCommandSuccess(addDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Documents documents = new DocumentsBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        AddDocumentsCommand addDocumentsCommand = new AddDocumentsCommand(outOfBoundIndex, documents);

        assertCommandFailure(addDocumentsCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FOURTH_APPLICATION);

        InternshipApplication applicationInFilteredList =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        InternshipApplication initialApplication =
                new InternshipBuilder(applicationInFilteredList).build();
        Documents documents = new DocumentsBuilder().build();
        InternshipApplication documentsAddedApplication =
                new InternshipBuilder(applicationInFilteredList).withDocuments(documents).build();
        AddDocumentsCommand addDocumentsCommand = new AddDocumentsCommand(INDEX_FIRST_APPLICATION, documents);

        String expectedMessage = String.format(AddDocumentsCommand.MESSAGE_ADD_DOCUMENTS_SUCCESS, initialApplication
                + "\n" + documents);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), documentsAddedApplication);

        assertCommandSuccess(addDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FOURTH_APPLICATION);

        Documents documents = new DocumentsBuilder().build();
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        AddDocumentsCommand addDocumentsCommand = new AddDocumentsCommand(outOfBoundIndex, documents);

        assertCommandFailure(addDocumentsCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddDocumentsCommand standardCommand = new AddDocumentsCommand(INDEX_FOURTH_APPLICATION, DOCUMENTS_GOOGLE
        );

        // same values -> returns true
        Documents copyDocuments = new Documents(new ResumeLink(VALID_RESUME_LINK_GOOGLE),
                new CoverLetterLink(VALID_COVER_LETTER_LINK_GOOGLE));
        AddDocumentsCommand commandWithSameValues = new AddDocumentsCommand(INDEX_FOURTH_APPLICATION, copyDocuments);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new AddDocumentsCommand(INDEX_FIFTH_APPLICATION, DOCUMENTS_GOOGLE));

        // different documents -> returns false
        assertNotEquals(standardCommand, new AddDocumentsCommand(INDEX_FOURTH_APPLICATION, DOCUMENTS_NETFLIX));
    }
}

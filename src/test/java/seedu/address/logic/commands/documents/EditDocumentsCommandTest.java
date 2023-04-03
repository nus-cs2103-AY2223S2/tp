package seedu.address.logic.commands.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DOCUMENTS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DOCUMENTS_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVER_LETTER_LINK_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_DATA_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_NETWORK_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_NETFLIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_LINK_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
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
import seedu.address.testutil.EditDocumentsDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDocumentsCommand.
 */
public class EditDocumentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Documents editedDocuments = new DocumentsBuilder().build();
        EditDocumentsCommand.EditDocumentsDescriptor descriptor =
                new EditDocumentsDescriptorBuilder(editedDocuments).build();
        InternshipApplication applicationWithEditedDocuments = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_NETFLIX)
                .withJobTitle(VALID_JOB_TITLE_NETWORK_ENGINEER).withDocuments(editedDocuments).build();
        EditDocumentsCommand editDocumentsCommand = new EditDocumentsCommand(INDEX_FIFTH_APPLICATION, descriptor);

        String expectedMessage = String.format(EditDocumentsCommand.MESSAGE_EDIT_DOCUMENTS_SUCCESS,
                applicationWithEditedDocuments);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(4), applicationWithEditedDocuments);

        assertCommandSuccess(editDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexSecondLastApplication = Index.fromOneBased(model.getSortedFilteredInternshipList().size() - 1);
        InternshipApplication lastApplication = model.getSortedFilteredInternshipList()
                .get(indexSecondLastApplication.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastApplication);
        InternshipApplication editedApplication = internshipInList.withCompanyName(VALID_COMPANY_NAME_ORACLE)
                .withJobTitle(VALID_JOB_TITLE_DATA_ENGINEER)
                .withDocuments(new Documents(new ResumeLink(VALID_RESUME_LINK_ORACLE),
                        new CoverLetterLink("https://drive.example.com/coverletter_oracle_2")))
                .build();

        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_ORACLE)
                .withCoverLetterLink("https://drive.example.com/coverletter_oracle_2").build();
        EditDocumentsCommand editDocumentsCommand = new EditDocumentsCommand(indexSecondLastApplication, descriptor);

        String expectedMessage = String.format(EditDocumentsCommand.MESSAGE_EDIT_DOCUMENTS_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDocumentsCommand editDocumentsCommand = new EditDocumentsCommand(INDEX_FIFTH_APPLICATION,
                new EditDocumentsCommand.EditDocumentsDescriptor());
        InternshipApplication editedApplication =
                model.getSortedFilteredInternshipList().get(INDEX_FIFTH_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditDocumentsCommand.MESSAGE_EDIT_DOCUMENTS_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());

        assertCommandSuccess(editDocumentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIFTH_APPLICATION);

        InternshipApplication applicationInFilteredList =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        Documents editedDocuments = new DocumentsBuilder().build();
        InternshipApplication editedApplication = new InternshipBuilder(applicationInFilteredList)
                .withDocuments(editedDocuments).build();
        EditDocumentsCommand editCommand = new EditDocumentsCommand(INDEX_FIRST_APPLICATION,
                new EditDocumentsDescriptorBuilder()
                        .withResumeLink(VALID_RESUME_LINK_NETFLIX)
                        .withCoverLetterLink(VALID_COVER_LETTER_LINK_NETFLIX)
                        .build());

        String expectedMessage = String.format(EditDocumentsCommand.MESSAGE_EDIT_DOCUMENTS_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        EditDocumentsCommand.EditDocumentsDescriptor descriptor =
                new EditDocumentsDescriptorBuilder().withResumeLink(VALID_RESUME_LINK_GOOGLE).build();
        EditDocumentsCommand editDocumentsCommand = new EditDocumentsCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editDocumentsCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        EditDocumentsCommand editDocumentsCommand = new EditDocumentsCommand(outOfBoundIndex,
                new EditDocumentsDescriptorBuilder().withResumeLink(VALID_RESUME_LINK_GOOGLE).build());

        assertCommandFailure(editDocumentsCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDocumentsCommand standardCommand = new EditDocumentsCommand(INDEX_FOURTH_APPLICATION,
                DESC_DOCUMENTS_GOOGLE);

        // same values -> returns true
        EditDocumentsCommand.EditDocumentsDescriptor copyDescriptor =
                new EditDocumentsCommand.EditDocumentsDescriptor(DESC_DOCUMENTS_GOOGLE);
        EditDocumentsCommand commandWithSameValues = new EditDocumentsCommand(INDEX_FOURTH_APPLICATION, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditDocumentsCommand(INDEX_FIFTH_APPLICATION,
                DESC_DOCUMENTS_GOOGLE));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditDocumentsCommand(INDEX_FOURTH_APPLICATION, DESC_DOCUMENTS_NETFLIX));
    }
}

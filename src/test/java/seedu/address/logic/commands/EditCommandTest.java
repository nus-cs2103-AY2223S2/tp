package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_META;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_CAP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.model.application.InternshipStatus.ACCEPTED;
import static seedu.address.testutil.InternshipBuilder.DEFAULT_PHONE;
import static seedu.address.testutil.InternshipBuilder.DEFAULT_EMAIL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.EditInternshipDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                                                                getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        InternshipApplication editedInternship = new InternshipBuilder()
                                .withContact(new Contact(new Phone(DEFAULT_PHONE), new Email(DEFAULT_EMAIL)))
                                .withStatus(ACCEPTED)
                                .withInterviewDate(new InterviewDate("2023-04-01 08:00 PM"))
                                .build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(editedInternship).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                                                                        model.getTodoList(), model.getNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getSortedFilteredInternshipList().size());
        InternshipApplication lastInternship = model.getSortedFilteredInternshipList()
                                                                            .get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        InternshipApplication editedInternship = internshipInList.withCompanyName(VALID_COMPANY_NAME_META)
                                                        .withQualifications(VALID_QUALIFICATION_CAP).build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                                                .withCompanyName(VALID_COMPANY_NAME_META)
                                                .withQualifications(VALID_QUALIFICATION_CAP).build();
        EditCommand editCommand = new EditCommand(indexLastInternship, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                                                                        getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(lastInternship, editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, new EditInternshipDescriptor());
        InternshipApplication editedInternship = model.getSortedFilteredInternshipList()
                                                    .get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                                                                    getTypicalTodoList(), getTypicalNoteList());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);

        InternshipApplication internshipInFilteredList = model.getSortedFilteredInternshipList()
                                                                        .get(INDEX_FIRST_APPLICATION.getZeroBased());
        InternshipApplication editedInternship = new InternshipBuilder(internshipInFilteredList)
                                                                .withCompanyName(VALID_COMPANY_NAME_META).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_META).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                                                                        getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        InternshipApplication firstInternship = model.getSortedFilteredInternshipList()
                                                    .get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit internship in filtered list into a duplicate in address book
        InternshipApplication internshipInList = model.getAddressBook().getInternshipList()
                                                    .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditInternshipDescriptorBuilder(internshipInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                                            .withCompanyName(VALID_COMPANY_NAME_META).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_META).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_META);

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_META);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_META)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_GOOGLE)));
    }

}

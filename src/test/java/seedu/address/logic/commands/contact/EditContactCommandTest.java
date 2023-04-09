package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BANK_OF_AMERICA_CONTACT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_META_CONTACT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_CLOUD_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_META;
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
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditContactCommand.
 */
public class EditContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        InternshipApplication applicationWithEditedContact = new InternshipBuilder().withCompanyName("Meta")
                .withJobTitle("Software Tester").withInterviewDate(new InterviewDate("2023-04-01 08:00 PM"))
                .withStatus(ACCEPTED).withContact(editedContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                applicationWithEditedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), applicationWithEditedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getSortedFilteredInternshipList().size());
        InternshipApplication lastApplication = model.getSortedFilteredInternshipList()
                .get(indexLastApplication.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastApplication);
        InternshipApplication editedApplication = internshipInList.withCompanyName(VALID_COMPANY_NAME_AMAZON)
                .withJobTitle(VALID_JOB_TITLE_CLOUD_ENGINEER)
                .withContact(new Contact(new Phone(VALID_PHONE_AMAZON), new Email("example2@amazon.com")))
                .build();

        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withPhone(VALID_PHONE_AMAZON).withEmail("example2@amazon.com").build();
        EditContactCommand editContactCommand = new EditContactCommand(indexLastApplication, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST_APPLICATION,
                new EditContactCommand.EditContactDescriptor());
        InternshipApplication editedApplication =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_APPLICATION);

        InternshipApplication applicationInFilteredList =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        Contact editedContact = new ContactBuilder().build();
        InternshipApplication editedApplication = new InternshipBuilder(applicationInFilteredList)
                .withContact(editedContact).build();
        EditContactCommand editCommand = new EditContactCommand(INDEX_FIRST_APPLICATION,
                new EditContactDescriptorBuilder().withPhone(VALID_PHONE_META).withEmail(VALID_EMAIL_META).build());

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        EditContactCommand.EditContactDescriptor descriptor =
                new EditContactDescriptorBuilder().withPhone(VALID_PHONE_BANK_OF_AMERICA).build();
        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
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

        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex,
                new EditContactDescriptorBuilder().withPhone(VALID_PHONE_BANK_OF_AMERICA).build());

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditContactCommand standardCommand = new EditContactCommand(INDEX_FIRST_APPLICATION,
                DESC_BANK_OF_AMERICA_CONTACT);

        // same values -> returns true
        EditContactCommand.EditContactDescriptor copyDescriptor =
                new EditContactCommand.EditContactDescriptor(DESC_BANK_OF_AMERICA_CONTACT);
        EditContactCommand commandWithSameValues = new EditContactCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditContactCommand(INDEX_SECOND_APPLICATION,
                DESC_BANK_OF_AMERICA_CONTACT));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditContactCommand(INDEX_FIRST_APPLICATION, DESC_META_CONTACT));
    }
}

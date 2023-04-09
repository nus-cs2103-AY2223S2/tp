package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.model.application.InternshipStatus.DECLINED;
import static seedu.address.testutil.TypicalContacts.BANK_OF_AMERICA_CONTACT;
import static seedu.address.testutil.TypicalContacts.META_CONTACT;
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
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddContactCommand.
 */
public class AddContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact contact = new ContactBuilder().build();
        InternshipApplication initialApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BANK_OF_AMERICA)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_ENGINEER)
                .withStatus(DECLINED)
                .withInterviewDate(new InterviewDate("2023-04-09 12:00 PM")).build();
        InternshipApplication contactAddedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BANK_OF_AMERICA)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_ENGINEER)
                .withStatus(DECLINED)
                .withInterviewDate(new InterviewDate("2023-04-09 12:00 PM"))
                .withContact(contact).build();
        AddContactCommand addContactCommand = new AddContactCommand(INDEX_SECOND_APPLICATION, contact);

        String expectedMessage = String.format(AddContactCommand.MESSAGE_ADD_CONTACT_SUCCESS, initialApplication
                + "\n" + contact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(1), contactAddedApplication);

        assertCommandSuccess(addContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Contact contact = new ContactBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        AddContactCommand addContactCommand = new AddContactCommand(outOfBoundIndex, contact);

        assertCommandFailure(addContactCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_SECOND_APPLICATION);

        InternshipApplication applicationInFilteredList =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        InternshipApplication initialApplication =
                new InternshipBuilder(applicationInFilteredList).build();
        Contact contact = new ContactBuilder().build();
        InternshipApplication contactAddedApplication =
                new InternshipBuilder(applicationInFilteredList).withContact(contact).build();
        AddContactCommand addContactCommand = new AddContactCommand(INDEX_FIRST_APPLICATION, contact);

        String expectedMessage = String.format(AddContactCommand.MESSAGE_ADD_CONTACT_SUCCESS, initialApplication
                + "\n" + contact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), contactAddedApplication);

        assertCommandSuccess(addContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_SECOND_APPLICATION);

        Contact contact = new ContactBuilder().build();
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        AddContactCommand addContactCommand = new AddContactCommand(outOfBoundIndex, contact);

        assertCommandFailure(addContactCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddContactCommand standardCommand = new AddContactCommand(INDEX_FIRST_APPLICATION, BANK_OF_AMERICA_CONTACT
        );

        // same values -> returns true
        Contact copyContact = new Contact(new Phone(VALID_PHONE_BANK_OF_AMERICA),
                new Email(VALID_EMAIL_BANK_OF_AMERICA));
        AddContactCommand commandWithSameValues = new AddContactCommand(INDEX_FIRST_APPLICATION, copyContact);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddContactCommand(INDEX_SECOND_APPLICATION, BANK_OF_AMERICA_CONTACT)));

        // different contact -> returns false
        assertFalse(standardCommand.equals(new AddContactCommand(INDEX_FIRST_APPLICATION, META_CONTACT)));
    }
}

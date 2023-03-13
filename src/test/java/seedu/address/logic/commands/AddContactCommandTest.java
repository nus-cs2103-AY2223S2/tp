//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_COMPANY_A;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_COMPANY_B;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_COMPANY_A;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_COMPANY_A;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.AddressBook;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.contact.Contact;
//import seedu.address.model.contact.Email;
//import seedu.address.model.contact.Phone;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.ContactBuilder;
//import seedu.address.testutil.PersonBuilder;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for AddContactCommand.
// */
//public class AddContactCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Person contactAddedPerson = new PersonBuilder().withName(VALID_NAME_BENSON)
//                .withTags("owesMoney", "friends").withAddress("311, Clementi Ave 2, #02-25").build();
//        Contact contact = new ContactBuilder().build();
//        contactAddedPerson.setContact(contact);
//        AddContactCommand addContactCommand = new AddContactCommand(INDEX_SECOND_PERSON, contact);
//
//        String expectedMessage = String.format(AddContactCommand.MESSAGE_ADD_CONTACT_SUCCESS, contactAddedPerson
//                + "\n" + contact);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredInternshipList().get(1), contactAddedPerson);
//
//        assertCommandSuccess(addContactCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showPersonAtIndex(model, INDEX_SECOND_PERSON);
//
//        Person personInFilteredList = model.getFilteredInternshipList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Person contactAddedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BENSON).build();
//        Contact contact = new ContactBuilder().build();
//        contactAddedPerson.setContact(contact);
//        AddContactCommand addContactCommand = new AddContactCommand(INDEX_FIRST_PERSON, contact);
//
//        String expectedMessage = String.format(AddContactCommand.MESSAGE_ADD_CONTACT_SUCCESS, contactAddedPerson
//                + "\n" + contact);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredInternshipList().get(0), contactAddedPerson);
//
//        assertCommandSuccess(addContactCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void equals() {
//        final AddContactCommand standardCommand = new AddContactCommand(INDEX_FIRST_PERSON, DESC_COMPANY_A
//                );
//
//        // same values -> returns true
//        Contact copyContact = new Contact(new Phone(VALID_PHONE_COMPANY_A), new Email(VALID_EMAIL_COMPANY_A));
//        AddContactCommand commandWithSameValues = new AddContactCommand(INDEX_FIRST_PERSON, copyContact);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new AddContactCommand(INDEX_SECOND_PERSON, DESC_COMPANY_A)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new AddContactCommand(INDEX_FIRST_PERSON, DESC_COMPANY_B)));
//    }
//}

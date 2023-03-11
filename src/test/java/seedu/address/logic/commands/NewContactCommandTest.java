package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Event;
import seedu.address.model.person.Rate;
import seedu.address.testutil.ContactBuilder;


class NewContactCommandTest {

    @Test
    public void constructor_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NewContactCommand(null));
    }

    @Test
    public void execute_contactAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact validContact = new ContactBuilder().build();

        CommandResult commandResult = new NewContactCommand(validContact).execute(modelStub);

        assertEquals(String.format(NewContactCommand.MESSAGE_SUCCESS, validContact), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validContact), modelStub.contactsAdded);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact validContact = new ContactBuilder().build();
        NewContactCommand newContactCommand = new NewContactCommand(validContact);
        ModelStub modelStub = new ModelStubWithContact(validContact);

        assertThrows(CommandException.class,
                NewContactCommand.MESSAGE_DUPLICATE_CONTACT, () -> newContactCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Contact deborah = new ContactBuilder().withName("Deborah").build();
        Contact mandy = new ContactBuilder().withName("Mandy").build();
        NewContactCommand addDeborahCommand = new NewContactCommand(deborah);
        NewContactCommand addMandyCommand = new NewContactCommand(mandy);

        // same object -> true
        assertTrue(addDeborahCommand.equals(addDeborahCommand));

        // same values -> true
        NewContactCommand addMandyCommandCopy = new NewContactCommand(mandy);
        assertTrue(addMandyCommand.equals(addMandyCommandCopy));

        // different types -> false
        assertFalse(addMandyCommand.equals(1));

        // null -> returns false
        assertFalse(addMandyCommand.equals(null));

        // different person -> false
        assertFalse(addMandyCommand.equals(addDeborahCommand));
    }

    /**
     * A default model stub that has all the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getContactListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactListFilePath(Path contactListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyContactList getContactList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean hasPerson(Event person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Event person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Event target, Event editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Rate getRate(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markEvent(Event target, Event markedEvent) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * Model stub that contains a single contact.
     */
    private class ModelStubWithContact extends ModelStub {

        private final Contact contact;

        ModelStubWithContact(Contact contact) {
            requireNonNull(contact);
            this.contact = contact;
        }

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return this.contact.isSameContact(contact);
        }
    }

    /**
     * Model stub that always accept the contact being added.
     */
    private class ModelStubAcceptingContactAdded extends ModelStub {
        final ArrayList<Contact> contactsAdded = new ArrayList<>();

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return contactsAdded.stream().anyMatch(contact::isSameContact);
        }

        @Override
        public void addContact(Contact contact) {
            requireNonNull(contact);
            contactsAdded.add(contact);
        }

        @Override
        public ReadOnlyContactList getContactList() {
            return new ContactList();
        }
    }
}

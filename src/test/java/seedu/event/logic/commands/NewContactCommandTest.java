package seedu.event.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.event.commons.core.GuiSettings;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.model.ContactList;
import seedu.event.model.Model;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.ReadOnlyUserPrefs;
import seedu.event.model.contact.Contact;
import seedu.event.model.event.Event;
import seedu.event.model.event.Rate;
import seedu.event.testutil.ContactBuilder;


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

        // different event -> false
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
        public Path getEventBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getContactListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBookFilePath(Path eventBookFilePath) {
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
        public void setEventBook(ReadOnlyEventBook eventBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactList(ReadOnlyContactList contactList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyContactList getContactList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void linkContact(Event event, Event linkedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Rate getRate(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkEvent(Event target) {
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

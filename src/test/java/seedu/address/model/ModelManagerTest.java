package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.AMY;
import static seedu.address.testutil.TypicalContacts.BOB;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.event.NameContainsKeywordsPredicate;
import seedu.address.testutil.ContactListBuilder;
import seedu.address.testutil.EventBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new EventBook(), new EventBook(modelManager.getEventBook()));
        assertEquals(new ContactList(), new ContactList(modelManager.getContactList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setEventBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setContactListFilePath(Paths.get("contact/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setEventBookFilePath(Paths.get("new/address/book/file/path"));
        userPrefs.setContactListFilePath(Paths.get("new/contact/list/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setEventBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEventBookFilePath(null));
    }

    @Test
    public void setContactListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setContactListFilePath(null));
    }

    @Test
    public void setEventBookFilePath_validPath_setsEventBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setEventBookFilePath(path);
        assertEquals(path, modelManager.getEventBookFilePath());
    }

    @Test
    public void setContactListFilePath_validPath_setsContactListFilePath() {
        Path path = Paths.get("contact/list/file/path");
        modelManager.setContactListFilePath(path);
        assertEquals(path, modelManager.getContactListFilePath());
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasEvent_eventNotInEventBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(ALICE));
    }

    @Test
    public void hasContact_contactNotInContactList_returnsFalse() {
        assertFalse(modelManager.hasContact(AMY));
    }

    @Test
    public void hasEvent_eventInEventBook_returnsTrue() {
        modelManager.addEvent(ALICE);
        assertTrue(modelManager.hasEvent(ALICE));
    }

    @Test
    public void hasContact_contactInContactList_returnsTrue() {
        modelManager.addContact(AMY);
        assertTrue(modelManager.hasContact(AMY));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void linkContact() {
        modelManager.addContact(AMY);
        modelManager.addEvent(ALICE);
        Event event = modelManager.getEventBook().getEventList().get(0);
        Contact contact = modelManager.getContactList().getContactList().get(0);
        Event before = event;
        event.linkContact(contact);
        Event linkedEvent = event;
        modelManager.linkContact(before, linkedEvent);
        assertTrue(modelManager.getFilteredEventList().get(0).equals(linkedEvent));
    }

    @Test
    public void equals() {
        EventBook eventBook = new EventBookBuilder().withEvent(ALICE).withEvent(BENSON).build();
        EventBook differentEventBook = new EventBook();
        ContactList contactList = new ContactListBuilder().withContact(AMY).withContact(BOB).build();
        ContactList differentContactList = new ContactList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(eventBook, contactList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(eventBook, contactList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different eventBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentEventBook, contactList, userPrefs)));

        // different contactList -> return false
        assertFalse(modelManager.equals(new ModelManager(eventBook, differentContactList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEventList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(eventBook, contactList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setEventBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(eventBook, contactList, differentUserPrefs)));
    }
}

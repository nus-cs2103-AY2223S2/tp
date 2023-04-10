package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void undo_throwsCommandException() {
        assertThrows(CommandException.class, () -> modelManager.undoAddressBook());
    }

    @Test
    public void redo_throwsCommandException() {
        assertThrows(CommandException.class, () -> modelManager.redoAddressBook());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().formattedName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void deletePerson_personExists_success() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));

        modelManager.deletePerson(ALICE);
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void setPerson_personExists_success() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));

        Person editedAlice = new PersonBuilder(ALICE).withName("Edited Alice").build();
        modelManager.setPerson(ALICE, editedAlice);
        assertFalse(modelManager.hasPerson(ALICE));
        assertTrue(modelManager.hasPerson(editedAlice));
    }

    @Test
    public void updateFilteredPersonList_predicate_success() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BENSON);

        modelManager.updateFilteredPersonList(p -> p.getName().formattedName.contains("Ben"));
        assertEquals(1, modelManager.getFilteredPersonList().size());
        assertTrue(modelManager.getFilteredPersonList().contains(BENSON));
    }

    /*// Test for getFilteredCalendarEventList() method
    @Test
    public void getFilteredCalendarEventList_success() {
        // Set up a list of persons with calendar events
        Person person1 = new PersonBuilder().withName("Amy Bee")
                .withSession("01-01-2022 12:00", "01-01-2022 13:00").build();
        Person person2 = new PersonBuilder().withName("Bob Choo")
                .withSession("01-01-2022 10:00", "01-01-2022 11:00").build();
        modelManager.addPerson(person1);
        modelManager.addPerson(person2);

        // Check that the filtered list contains only persons with calendar events
        ObservableList<CalendarEvent> expectedCalendarEvents = getTypicalCalendarEvents();
        ObservableList<CalendarEvent> actualCalendarEvents = modelManager.getFilteredCalendarEventList();
        assertEquals(expectedCalendarEvents, actualCalendarEvents);
    }

    // Test for updateCalendarEventList() method
    @Test
    public void updateCalendarEventList_success() {
        // Set up a list of persons with calendar events
        Person person1 = new PersonBuilder().withName("Amy Bee")
                .withSession("01-01-2022 12:00", "01-01-2022 13:00").build();
        Person person2 = new PersonBuilder().withName("Bob Choo")
                .withSession("01-01-2022 10:00", "01-01-2022 11:00").build();
        modelManager.addPerson(person1);
        modelManager.addPerson(person2);

        // Filter the list to show only persons with calendar events
        modelManager.updateFilteredPersonList(x -> !x.getCalendarEvents().isEmpty());

        // Update the calendar event list
        modelManager.updateCalendarEventList();

        // Check that the filtered list contains only persons with calendar events
        ObservableList<CalendarEvent> expectedCalendarEvents = getTypicalCalendarEvents();
        ObservableList<CalendarEvent> actualCalendarEvents = modelManager.getFilteredCalendarEventList();
        assertEquals(expectedCalendarEvents, actualCalendarEvents);
    }*/

}

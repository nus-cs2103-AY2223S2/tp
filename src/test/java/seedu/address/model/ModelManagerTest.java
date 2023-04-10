package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS0000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BORROWED_TEXTBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_OWE_MONEY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.group.Group;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.time.ScheduleWeek;
import seedu.address.model.time.TimeMask;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SampleEventUtil;

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
    public void hasGroup_groupNotInAddressBook_returnsFalse() {
        Group group = new Group("2103");
        assertFalse(modelManager.hasGroup(group));
    }

    @Test
    public void hasGroup_groupInAddressBook_returnsTrue() {
        Group group = new Group("2103");
        modelManager.addGroup(group);
        assertTrue(modelManager.hasGroup(group));
    }


    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGroupList().remove(0));
    }

    @Test
    public void addPersonInGroup_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.addPersonInGroup(null, new Group("CS2103")));
    }

    @Test
    public void addPersonInGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.addPersonInGroup(new PersonBuilder(ALICE).build(), null));
    }

    @Test
    public void removePersonFromGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.removePersonFromGroup(new PersonBuilder(ALICE).build(), null));
    }

    @Test
    public void removePersonFromGroup_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.removePersonFromGroup(null, new Group("CS2103")));
    }

    @Test
    public void updateFilteredTimeSlotList_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.updateFilteredTimeSlotList(null, LocalDate.of(299, 1, 1)));
    }

    @Test
    public void updateFilteredTimeSlotList_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.updateFilteredTimeSlotList(new Group("CS2103"), null));
    }

    /**
     * Includes integration tests for scheduling features
     * which involve TimeMask, the Events, the EventLists and ScheduleWeek
     */
    @Test
    public void updateFilteredTimeSlotList_multipleEventCombinations_returnsTrue() {
        ModelManager modelManager = new ModelManager();

        Set<IsolatedEvent> validIsolatedEventSet = new TreeSet<>();
        validIsolatedEventSet.add(SampleEventUtil.getNearFutureIsolatedEvent());
        validIsolatedEventSet.add(SampleEventUtil.getFarFutureIsolatedEvent());

        Set<RecurringEvent> validRecurringEventSet = new TreeSet<>();
        validRecurringEventSet.add(SampleEventUtil.HALF_DAY_RECURRING_EVENT);

        // Names are abbreviations for type of Person with respect to involved Groups and Events
        Person SingleMemberNoEvents = new PersonBuilder().withName("SMNE").withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_OWE_MONEY, VALID_TAG_BORROWED_TEXTBOOK)
                .withGroups(VALID_GROUP_CS2103)
                .build();

        Person SingleMemberWithMixedEvents = new PersonBuilder().withName("SMWME").withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_OWE_MONEY, VALID_TAG_BORROWED_TEXTBOOK)
                .withGroups(VALID_GROUP_CS0000)
                .withIsolatedEventList(validIsolatedEventSet)
                .withRecurringEventList(validRecurringEventSet)
                .build();

        Person FirstMemberWithIsolatedEventsOnly = new PersonBuilder().withName("FMIEO").withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_OWE_MONEY, VALID_TAG_BORROWED_TEXTBOOK)
                .withGroups(VALID_GROUP_CS0000)
                .withIsolatedEventList(validIsolatedEventSet)
                .build();

        Group CS0000 = new Group(VALID_GROUP_CS0000);
        Group CS2103 = new Group(VALID_GROUP_CS2103);


        Person SecondMemberWithRecurringEventsOnly = new PersonBuilder().withName("SMREO").withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_OWE_MONEY, VALID_TAG_BORROWED_TEXTBOOK)
                .withGroups(VALID_GROUP_CS0000)
                .withRecurringEventList(validRecurringEventSet)
                .build();

        // Force start date to be the nearest Monday after a week from now
        LocalDate startDate = LocalDate.now().plusWeeks(1);
        startDate = startDate.plusDays(7 + 1 - startDate.getDayOfWeek().getValue());
        ScheduleWeek expectedSchedule = new ScheduleWeek();
        int fullyOccupiedDay = Integer.parseInt("1".repeat(24),2);
        int halfOccupiedDay = Integer.parseInt("1".repeat(12),2);

        // First Test Case - Occupied Monday and Tuesday, combination of Schedules of 2 Persons in same Group
        AddressBook addressBook = new AddressBookBuilder()
                .withGroup(CS0000)
                .withGroup(CS2103)
                .withPerson(FirstMemberWithIsolatedEventsOnly)
                .withPerson(SecondMemberWithRecurringEventsOnly)
                .build();

        TimeMask occupiedMondayAndTuesday = new TimeMask(new int[]{halfOccupiedDay, fullyOccupiedDay, 0, 0, 0, 0, 0});
        modelManager.setAddressBook(addressBook);
        modelManager.updateFilteredTimeSlotList(CS0000, startDate);
        expectedSchedule.setInternalList(
                TimeMask.getTimeSlotIndexes(occupiedMondayAndTuesday),
                DayOfWeek.MONDAY);
        assertEquals(modelManager.getAddressBook().getSchedule(), expectedSchedule);

        // Second Test Case - Fully unoccupied Timetable
        addressBook = new AddressBookBuilder()
                .withGroup(CS0000)
                .withGroup(CS2103)
                .withPerson(SingleMemberNoEvents)
                .withPerson(SingleMemberWithMixedEvents)
                .build();

        // The IsolatedEvents used here are forced to occur during a Tuesday
        expectedSchedule.setInternalList(
                TimeMask.getTimeSlotIndexes(new TimeMask()),
                DayOfWeek.MONDAY);
        modelManager.setAddressBook(addressBook);
        modelManager.updateFilteredTimeSlotList(CS2103, startDate);
        assertEquals(modelManager.getAddressBook().getSchedule(), expectedSchedule);

        // Third Test Case - Occupied Tuesday by a single member in a Group
        expectedSchedule.setInternalList(
                TimeMask.getTimeSlotIndexes(new TimeMask(new int[]{halfOccupiedDay, fullyOccupiedDay, 0, 0, 0, 0, 0})),
                DayOfWeek.MONDAY);
        modelManager.updateFilteredTimeSlotList(CS0000, startDate);
        assertEquals(modelManager.getAddressBook().getSchedule(), expectedSchedule);

        expectedSchedule.setInternalList(TimeMask.getTimeSlotIndexes(new TimeMask()), DayOfWeek.MONDAY);
        assertNotEquals(modelManager.getAddressBook().getSchedule(), expectedSchedule);
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
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}

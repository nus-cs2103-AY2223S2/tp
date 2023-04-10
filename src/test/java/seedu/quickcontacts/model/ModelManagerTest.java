package seedu.quickcontacts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.quickcontacts.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.quickcontacts.testutil.Assert.assertThrows;
import static seedu.quickcontacts.testutil.TypicalMeetings.DONE_MEETING;
import static seedu.quickcontacts.testutil.TypicalMeetings.MEETING_A;
import static seedu.quickcontacts.testutil.TypicalMeetings.UNDONE_MEETING;
import static seedu.quickcontacts.testutil.TypicalPersons.ALICE;
import static seedu.quickcontacts.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.quickcontacts.commons.core.GuiSettings;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.model.person.NameContainsKeywordsPredicate;
import seedu.quickcontacts.testutil.QuickBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        Assertions.assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        Assertions.assertEquals(new QuickBook(), new QuickBook(modelManager.getQuickBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setQuickBookFilePath(Paths.get("quick/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4, false));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setQuickBookFilePath(Paths.get("new/quick/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4, false);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setQuickBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setQuickBookFilePath(null));
    }

    @Test
    public void setQuickBookFilePath_validPath_setsQuickBookFilePath() {
        Path path = Paths.get("quick/book/file/path");
        modelManager.setQuickBookFilePath(path);
        assertEquals(path, modelManager.getQuickBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInQuickBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInQuickBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getPersonByName_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.getPersonByName(null));
    }

    @Test
    public void getPersonByName_personNotInQuickBook_returnsNull() {
        assertNull(modelManager.getPersonByName(ALICE.getName()));
    }

    @Test
    public void getPersonByName_personInQuickBook_returnsPerson() {
        modelManager.addPerson(ALICE);
        assertEquals(ALICE, modelManager.getPersonByName(ALICE.getName()));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void addMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addMeeting(null));
    }

    @Test
    public void addMeeting_meetingInQuickBook_returnsTrue() {
        modelManager.addMeeting(MEETING_A);
        assertTrue(modelManager.hasMeeting(MEETING_A));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInQuickBook_returnsFalse() {
        assertFalse(modelManager.hasMeeting(MEETING_A));
    }

    @Test
    public void hasMeeting_meetingInQuickBook_returnsTrue() {
        modelManager.addMeeting(MEETING_A);
        assertTrue(modelManager.hasMeeting(MEETING_A));
    }

    @Test
    public void markMeetingDone_meetingExists() {
        Model modelManager = new ModelManager();
        modelManager.addMeeting(UNDONE_MEETING);
        modelManager.markMeetingsAsDone(List.of(Index.fromZeroBased(0)));
        assertTrue(modelManager.getFilteredMeetingList().get(0).getIsCompleted());
    }

    @Test
    public void markMeetingNotDone_meetingsExist() {
        Model modelManager = new ModelManager();
        modelManager.addMeeting(DONE_MEETING);
        modelManager.markMeetingsAsNotDone(List.of(Index.fromZeroBased(0)));
        assertFalse(modelManager.getFilteredMeetingList().get(0).getIsCompleted());
    }

    @Test
    public void equals() {
        QuickBook quickBook = new QuickBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        QuickBook differentQuickBook = new QuickBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(quickBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(quickBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different quickBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentQuickBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(quickBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setQuickBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(quickBook, differentUserPrefs)));
    }
}

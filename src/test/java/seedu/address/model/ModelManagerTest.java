package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.ALICE;
import static seedu.address.testutil.TypicalElderly.CARL;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalVolunteers.BENSON;
import static seedu.address.testutil.TypicalVolunteers.DANIEL;
import static seedu.address.testutil.TypicalVolunteers.ELLE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.FriendlyLinkBuilder;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.TypicalElderly;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManagerBuilder().build();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FriendlyLink(), new FriendlyLink(modelManager.getFriendlyLink()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPairFilePath(Paths.get("pair/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPairFilePath(Paths.get("new/pair/file/path"));
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
    public void setElderlyFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setElderlyFilePath(null));
    }

    @Test
    public void setElderlyFilePath_validPath_setsElderlyFilePath() {
        Path path = Paths.get("elderly/file/path");
        modelManager.setElderlyFilePath(path);
        assertEquals(path, modelManager.getElderlyFilePath());
    }

    @Test
    public void setVolunteerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setVolunteerFilePath(null));
    }

    @Test
    public void setVolunteerFilePath_validPath_setsVolunteerFilePath() {
        Path path = Paths.get("volunteer/file/path");
        modelManager.setVolunteerFilePath(path);
        assertEquals(path, modelManager.getVolunteerFilePath());
    }

    @Test
    public void setPairFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPairFilePath(null));
    }

    @Test
    public void setPairFilePath_validPath_setsPairFilePath() {
        Path path = Paths.get("pair/file/path");
        modelManager.setPairFilePath(path);
        assertEquals(path, modelManager.getPairFilePath());
    }

    @Test
    public void hasElderly_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasElderly(null));
    }

    @Test
    public void hasVolunteer_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasVolunteer(null));
    }

    @Test
    public void hasElderly_elderlyNotInFriendlyLink_returnsFalse() {
        assertFalse(modelManager.hasElderly(ALICE));
    }

    @Test
    public void hasVolunteer_volunteerNotInFriendlyLink_returnsFalse() {
        assertFalse(modelManager.hasVolunteer(DANIEL));
    }

    @Test
    public void hasElderly_elderlyInFriendlyLink_returnsTrue() {
        modelManager.addElderly(TypicalElderly.ALICE);
        assertTrue(modelManager.hasElderly(TypicalElderly.ALICE));
    }

    @Test
    public void hasVolunteer_volunteerInFriendlyLink_returnsTrue() {
        modelManager.addVolunteer(DANIEL);
        assertTrue(modelManager.hasVolunteer(DANIEL));
    }

    @Test
    public void setElderly_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setElderly(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setElderly(ALICE, null));
        assertThrows(NullPointerException.class, () -> modelManager.setElderly(null, ALICE));
    }

    @Test
    public void setVolunteer_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setVolunteer(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setVolunteer(DANIEL, null));
        assertThrows(NullPointerException.class, () -> modelManager.setVolunteer(null, DANIEL));
    }

    @Test
    public void getFilteredElderlyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredElderlyList().remove(0));
    }

    @Test
    public void getFilteredVolunteerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredVolunteerList().remove(0));
    }

    @Test
    public void updateFilteredElderlyList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredElderlyList(null));
    }

    @Test
    public void updateFilteredVolunteerList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredVolunteerList(null));
    }


    @Test
    public void hasPair_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPair(null));
    }

    @Test
    public void hasPair_pairNotInFriendlyLink_returnsFalse() {
        assertFalse(modelManager.hasPair(PAIR1));
    }

    @Test
    public void hasPair_pairInFriendlyLink_returnsTrue() {
        modelManager.addElderly(ALICE);
        modelManager.addVolunteer(ELLE);
        modelManager.addPair(PAIR1);
        assertTrue(modelManager.hasPair(PAIR1));
    }


    @Test
    public void setPair_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPair(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setPair(PAIR1, null));
        assertThrows(NullPointerException.class, () -> modelManager.setPair(null, PAIR1));
    }

    @Test
    public void getFilteredPairList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPairList().remove(0));
    }

    @Test
    public void updateFilteredPairList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredPairList(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void equals() {
        FriendlyLink friendlyLink = new FriendlyLinkBuilder().withElderly(ALICE).withElderly(CARL)
                .withVolunteer(DANIEL).withVolunteer(BENSON).build();
        FriendlyLink differentFriendlyLink = new FriendlyLink();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManagerBuilder()
                .withFriendlyLink(friendlyLink)
                .withUserPrefs(userPrefs)
                .build();
        ModelManager modelManagerCopy = new ModelManagerBuilder()
                .withFriendlyLink(friendlyLink)
                .withUserPrefs(userPrefs)
                .build();
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different friendlyLink -> returns false
        assertNotEquals(modelManager, new ModelManagerBuilder()
                .withFriendlyLink(differentFriendlyLink)
                .withUserPrefs(userPrefs)
                .build());

        // different filteredElderlyList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredElderlyList(new NameContainsKeywordsPredicate<>(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManagerBuilder()
                .withFriendlyLink(friendlyLink)
                .withUserPrefs(userPrefs)
                .build());

        // different filteredVolunteerList -> returns false
        keywords = DANIEL.getName().fullName.split("\\s+");
        modelManager.updateFilteredVolunteerList(new NameContainsKeywordsPredicate<>(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManagerBuilder()
                .withFriendlyLink(friendlyLink)
                .withUserPrefs(userPrefs)
                .build());

        // TODO: different filteredPairList -> returns false

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredElderlyList((Predicate<Elderly>) PREDICATE_SHOW_ALL);
        modelManager.updateFilteredVolunteerList((Predicate<Volunteer>) PREDICATE_SHOW_ALL);

        // TODO: reset pair list in modelManager to initial state for upcoming tests

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPairFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManagerBuilder()
                .withFriendlyLink(friendlyLink)
                .withUserPrefs(differentUserPrefs)
                .build());
    }
}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.FriendlyLinkBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

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
        userPrefs.setFriendlyLinkFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFriendlyLinkFilePath(Paths.get("new/address/book/file/path"));
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
    public void setFriendlyLinkFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFriendlyLinkFilePath(null));
    }

    @Test
    public void setFriendlyLinkFilePath_validPath_setsFriendlyLinkFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFriendlyLinkFilePath(path);
        assertEquals(path, modelManager.getFriendlyLinkFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInFriendlyLink_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInFriendlyLink_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void setPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(ALICE, null));
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(null, ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void updateFilteredPersonList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredPersonList(null));
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
    public void equals() {
        FriendlyLink friendlyLink = new FriendlyLinkBuilder().withPerson(ALICE).withPerson(BENSON).build();
        FriendlyLink differentFriendlyLink = new FriendlyLink();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(friendlyLink, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(friendlyLink, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different friendlyLink -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFriendlyLink, userPrefs)));

        // different filteredPersonList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(friendlyLink, userPrefs)));

        // TODO: different filteredPairList -> returns false

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // TODO: resets modelManager to initial state for upcoming tests

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFriendlyLinkFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(friendlyLink, differentUserPrefs)));
    }
}

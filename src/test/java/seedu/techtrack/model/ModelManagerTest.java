package seedu.techtrack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.techtrack.testutil.Assert.assertThrows;
import static seedu.techtrack.testutil.TypicalRoles.ALICE;
import static seedu.techtrack.testutil.TypicalRoles.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.techtrack.commons.core.GuiSettings;
import seedu.techtrack.model.role.NameContainsKeywordsPredicate;
import seedu.techtrack.testutil.RoleBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new RoleBook(), new RoleBook(modelManager.getRoleBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRoleBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setRoleBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setRoleBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setRoleBookFilePath(null));
    }

    @Test
    public void setRoleBookFilePath_validPath_setsroleBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setRoleBookFilePath(path);
        assertEquals(path, modelManager.getRoleBookFilePath());
    }

    @Test
    public void hasRole_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRole(null));
    }

    @Test
    public void hasRole_roleNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleInAddressBook_returnsTrue() {
        modelManager.addRole(ALICE);
        assertTrue(modelManager.hasRole(ALICE));
    }

    @Test
    public void getFilteredRoleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRoleList().remove(0));
    }

    @Test
    public void equals() {
        RoleBook roleBook = new RoleBookBuilder().withRole(ALICE).withRole(BENSON).build();
        RoleBook differentAddressBook = new RoleBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(roleBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(roleBook, userPrefs);
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
        modelManager.updateFilteredRoleList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(roleBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredRoleList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setRoleBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(roleBook, differentUserPrefs)));
    }
}

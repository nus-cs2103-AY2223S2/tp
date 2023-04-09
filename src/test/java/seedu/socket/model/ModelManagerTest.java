package seedu.socket.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.socket.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BENSON;
import static seedu.socket.testutil.TypicalProjects.ALPHA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.GuiSettings;
import seedu.socket.model.person.predicate.FindCommandNamePredicate;
import seedu.socket.model.person.predicate.FindCommandProjectNamePredicate;
import seedu.socket.testutil.SocketBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Socket(), new Socket(modelManager.getSocket()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSocketFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSocketFilePath(Paths.get("new/address/book/file/path"));
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
    public void setSocketFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSocketFilePath(null));
    }

    @Test
    public void setSocketFilePath_validPath_setsSocketFilePath() {
        Path path = Paths.get("socket/file/path");
        modelManager.setSocketFilePath(path);
        assertEquals(path, modelManager.getSocketFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasPerson_personNotInSocket_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasProject_projectNotInSocket_returnsFalse() {
        assertFalse(modelManager.hasProject(ALPHA));
    }

    @Test
    public void hasPerson_personInSocket_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasProject_projectInSocket_returnsTrue() {
        modelManager.addProject(ALPHA);
        assertTrue(modelManager.hasProject(ALPHA));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProjectList().remove(0));
    }

    @Test
    public void equals() {
        Socket socket = new SocketBuilder().withPerson(ALICE).withPerson(BENSON).withProject(ALPHA).build();
        Socket differentSocket = new Socket();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(socket, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(socket, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different socket -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSocket, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new FindCommandNamePredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(socket, userPrefs)));
        keywords = ALPHA.getName().projectName.split("\\s+");
        modelManager.updateFilteredProjectList(new FindCommandProjectNamePredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(socket, userPrefs)));

        // different viewedPerson -> returns false
        modelManager = new ModelManager(socket, userPrefs);
        modelManager.updateViewedPerson(ALICE);
        assertFalse(modelManager.equals(new ModelManager(socket, userPrefs)));

        // different viewedProject -> returns false
        modelManager = new ModelManager(socket, userPrefs);
        modelManager.updateViewedProject(ALPHA);
        assertFalse(modelManager.equals(new ModelManager(socket, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSocketFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(socket, differentUserPrefs)));
    }
}

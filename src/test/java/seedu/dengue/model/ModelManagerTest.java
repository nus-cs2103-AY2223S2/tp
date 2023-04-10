package seedu.dengue.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.dengue.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.ALICE;
//import static seedu.dengue.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.core.GuiSettings;


public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new DengueHotspotTracker(), new DengueHotspotTracker(modelManager.getDengueHotspotTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDengueHotspotTrackerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setDengueHotspotTrackerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setDengueHotspotTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDengueHotspotTrackerFilePath(null));
    }

    @Test
    public void setDengueHotspotTrackerFilePath_validPath_setsDengueHotspotTrackerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDengueHotspotTrackerFilePath(path);
        assertEquals(path, modelManager.getDengueHotspotTrackerFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInDengueHotspotTracker_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInDengueHotspotTracker_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    //    @Test
    //    public void equals() {
    //        DengueHotspotTracker dengueHotspotTracker = new DengueHotspotTrackerBuilder()
    //                .withPerson(ALICE).withPerson(BENSON).build();
    //        DengueHotspotTracker differentDengueHotspotTracker = new DengueHotspotTracker();
    //        UserPrefs userPrefs = new UserPrefs();
    //
    //        // same values -> returns true
    //        modelManager = new ModelManager(dengueHotspotTracker, userPrefs);
    //        ModelManager modelManagerCopy = new ModelManager(dengueHotspotTracker, userPrefs);
    //        assertTrue(modelManager.equals(modelManagerCopy));
    //
    //        // same object -> returns true
    //        assertTrue(modelManager.equals(modelManager));
    //
    //        // null -> returns false
    //        assertFalse(modelManager.equals(null));
    //
    //        // different types -> returns false
    //        assertFalse(modelManager.equals(5));
    //
    //        // different dengueHotspotTracker -> returns false
    //        assertFalse(modelManager.equals(new ModelManager(differentDengueHotspotTracker, userPrefs)));
    //
    //        // different filteredList -> returns false
    //        String[] keywords = ALICE.getName().fullName.split("\\s+");
    //        Optional<SubPostal> emptySubPostal = Optional.empty();
    //        Optional<Name> testName = Optional.of(new Name("Alice Pauline"));
    //        Optional<Age> emptyAge = Optional.empty();
    //        Optional<Date> emptyDate = Optional.empty();
    //        Set<Variant> emptyVariants = new HashSet<>();
    //        modelManager.updateFilteredPersonList(new FindPredicate(testName,
    //                emptySubPostal, emptyAge, emptyDate, emptyVariants));
    //        assertFalse(modelManager.equals(new ModelManager(dengueHotspotTracker, userPrefs)));
    //
    //        // resets modelManager to initial state for upcoming tests
    //        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    //
    //        // different userPrefs -> returns false
    //        UserPrefs differentUserPrefs = new UserPrefs();
    //        differentUserPrefs.setDengueHotspotTrackerFilePath(Paths.get("differentFilePath"));
    //        assertFalse(modelManager.equals(new ModelManager(dengueHotspotTracker, differentUserPrefs)));
    //    }
}

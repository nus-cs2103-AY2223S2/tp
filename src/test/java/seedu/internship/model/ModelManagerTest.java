package seedu.internship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.ML1;
import static seedu.internship.testutil.TypicalInternships.ML2;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.GuiSettings;
import seedu.internship.testutil.InternshipCatalogueBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternshipCatalogue(), new InternshipCatalogue(modelManager.getInternshipCatalogue()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternshipCatalogueFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternshipCatalogueFilePath(Paths.get("new/address/book/file/path"));
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
        assertThrows(NullPointerException.class, () -> modelManager.setInternshipCatalogueFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setInternshipCatalogueFilePath(path);
        assertEquals(path, modelManager.getInternshipCatalogueFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipCatalogue_returnsFalse() {
        assertFalse(modelManager.hasInternship(ML2));
    }

    @Test
    public void hasInternship_internshipInInternshipCatalogue_returnsTrue() {
        modelManager.addInternship(ML1);
        assertTrue(modelManager.hasInternship(ML1));
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void equals() {
        InternshipCatalogue internshipCatalogue = new InternshipCatalogueBuilder().withInternship(ML2).withInternship(ML1).build();
        InternshipCatalogue differentInternshipCatalogue = new InternshipCatalogue();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internshipCatalogue, userPrefs);
        ModelManager modelManager1Copy = new ModelManager(internshipCatalogue, userPrefs);
        assertTrue(modelManager.equals(modelManager1Copy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternshipCatalogue, userPrefs)));

//        // different filteredList -> returns false
//        String[] keywords = ALICE.getName().fullName.split("\\s+");
//        modelManager1.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
//        assertFalse(modelManager1.equals(new ModelManager1(internshipCatalogue, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternshipCatalogueFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internshipCatalogue, differentUserPrefs)));
    }
}


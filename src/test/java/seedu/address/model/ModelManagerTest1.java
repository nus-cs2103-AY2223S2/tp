package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model1.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ML1;
import static seedu.address.testutil.TypicalInternships.ML2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.InternshipCatalogueBuilder;

public class ModelManagerTest1 {

    private ModelManager1 modelManager1 = new ModelManager1();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager1.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager1.getGuiSettings());
        assertEquals(new InternshipCatalogue(), new InternshipCatalogue(modelManager1.getInternshipCatalogue()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager1.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager1.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager1.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager1.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager1.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager1.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager1.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager1.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager1.setAddressBookFilePath(path);
        assertEquals(path, modelManager1.getAddressBookFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager1.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipCatalogue_returnsFalse() {
        assertFalse(modelManager1.hasInternship(ML2));
    }

    @Test
    public void hasInternship_internshipInInternshipCatalogue_returnsTrue() {
        modelManager1.addInternship(ML1);
        assertTrue(modelManager1.hasInternship(ML1));
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager1.getFilteredInternshipList().remove(0));
    }

    @Test
    public void equals() {
        InternshipCatalogue internshipCatalogue = new InternshipCatalogueBuilder().withInternship(ML2).withInternship(ML1).build();
        InternshipCatalogue differentInternshipCatalogue = new InternshipCatalogue();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager1 = new ModelManager1(internshipCatalogue, userPrefs);
        ModelManager1 modelManager1Copy = new ModelManager1(internshipCatalogue, userPrefs);
        assertTrue(modelManager1.equals(modelManager1Copy));

        // same object -> returns true
        assertTrue(modelManager1.equals(modelManager1));

        // null -> returns false
        assertFalse(modelManager1.equals(null));

        // different types -> returns false
        assertFalse(modelManager1.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager1.equals(new ModelManager1(differentInternshipCatalogue, userPrefs)));

//        // different filteredList -> returns false
//        String[] keywords = ALICE.getName().fullName.split("\\s+");
//        modelManager1.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
//        assertFalse(modelManager1.equals(new ModelManager1(internshipCatalogue, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager1.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager1.equals(new ModelManager1(internshipCatalogue, differentUserPrefs)));
    }
}


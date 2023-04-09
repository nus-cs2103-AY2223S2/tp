package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.WHISKERS;
import static seedu.address.testutil.TypicalPets.WOOFERS;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.pet.NameContainsKeywordsPredicate;
import seedu.address.testutil.PetPalBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PetPal(), new PetPal(modelManager.getPetPal()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPetPalFilePath(Paths.get("pet/pal/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPetPalFilePath(Paths.get("new/pet/pal/file/path"));
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
    public void setPetPalFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPetPalFilePath(null));
    }

    @Test
    public void setPetPalFilePath_validPath_setsPetPalFilePath() {
        Path path = Paths.get("pet/pal/file/path");
        modelManager.setPetPalFilePath(path);
        assertEquals(path, modelManager.getPetPalFilePath());
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPet(null));
    }

    @Test
    public void hasPet_petNotInPetPal_returnsFalse() {
        assertFalse(modelManager.hasPet(WHISKERS));
    }

    @Test
    public void hasPet_petInPetPal_returnsTrue() {
        modelManager.addPet(WHISKERS);
        assertTrue(modelManager.hasPet(WHISKERS));
    }

    @Test
    public void setPetPalArchiveFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPetPalArchiveFilePath(null));
    }

    @Test
    public void setPetPalArchiveFilePath_validPath_setsPetPalFilePath() {
        Path path = Paths.get("pet/pal/archive/file/path");
        modelManager.setPetPalArchiveFilePath(path);
        assertEquals(path, modelManager.getPetPalArchiveFilePath());
    }

    @Test
    public void getFilteredPetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPetList().remove(0));
    }

    @Test
    public void equals() {
        PetPal petPal = new PetPalBuilder().withPet(WHISKERS).withPet(WOOFERS).build();
        PetPal archivePetPal = new PetPal();
        PetPal differentPetPal = new PetPal();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(petPal, archivePetPal, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(petPal, archivePetPal, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotNull(modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different PetPal -> returns false
        assertNotEquals(modelManager, new ModelManager(differentPetPal, archivePetPal, userPrefs));

        // different filteredList -> returns false
        String[] keywords = WHISKERS.getName().fullName.split("\\s+");
        modelManager.updateFilteredPetList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManager(petPal, archivePetPal, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPetPalFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(petPal, archivePetPal, differentUserPrefs));
    }
}

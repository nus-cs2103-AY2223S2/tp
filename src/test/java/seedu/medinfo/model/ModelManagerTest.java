package seedu.medinfo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medinfo.model.Model.PREDICATE_SHOW_ALL_PATIENTS;
import static seedu.medinfo.testutil.Assert.assertThrows;
import static seedu.medinfo.testutil.TypicalPatients.ALEX;
import static seedu.medinfo.testutil.TypicalPatients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.medinfo.commons.core.GuiSettings;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.patient.NameContainsKeywordsPredicate;
import seedu.medinfo.testutil.MedInfoBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MedInfo(), new MedInfo(modelManager.getMedInfo()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMedInfoFilePath(Paths.get("medinfo/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMedInfoFilePath(Paths.get("new/medinfo/book/file/path"));
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
    public void setMedInfoFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMedInfoFilePath(null));
    }

    @Test
    public void setMedInfoFilePath_validPath_setsMedInfoFilePath() {
        Path path = Paths.get("medinfo/book/file/path");
        modelManager.setMedInfoFilePath(path);
        assertEquals(path, modelManager.getMedInfoFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient(null));
    }

    @Test
    public void hasPerson_personNotInMedInfo_returnsFalse() {
        assertFalse(modelManager.hasPatient(ALEX));
    }

    @Test
    public void hasPerson_personInMedInfo_returnsTrue() {
        try {
            modelManager.addPatient(ALEX);
        } catch (CommandException e) {
            System.out.println("Caught CommandException error!!!");
        }
        assertTrue(modelManager.hasPatient(ALEX));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().remove(0));
    }

    @Test
    public void equals() {
        MedInfo medInfo = new MedInfoBuilder().withPerson(ALEX).withPerson(BENSON).build();
        MedInfo differentMedInfo = new MedInfo();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(medInfo, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(medInfo, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different medInfo -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentMedInfo, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALEX.getName().fullName.split("\\s+");
        modelManager.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(medInfo, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMedInfoFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(medInfo, differentUserPrefs)));
    }
}

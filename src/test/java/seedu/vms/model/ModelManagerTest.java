package seedu.vms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.model.Model.PREDICATE_SHOW_ALL_PATIENTS;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalPatients.ALICE;
import static seedu.vms.testutil.TypicalPatients.BENSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.GuiSettings;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.predicates.NameContainsKeywordsPredicate;
import seedu.vms.testutil.PatientManagerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PatientManager(), new PatientManager(modelManager.getPatientManager()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
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
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().put(0, null));
    }

    @Test
    public void equals() {
        PatientManager patientManager = new PatientManagerBuilder().withPatient(ALICE).withPatient(BENSON).build();
        PatientManager differentPatientManager = new PatientManager();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(patientManager, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(patientManager, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different patientManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPatientManager, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(patientManager, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setGuiSettings(new GuiSettings(0, 0, 0, 0));
        assertFalse(modelManager.equals(new ModelManager(patientManager, differentUserPrefs)));
    }
}

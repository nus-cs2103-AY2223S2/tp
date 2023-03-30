package seedu.careflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.model.CareFlowModel.PREDICATE_SHOW_ALL_PATIENTS;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalDrugs.PROZAC;
import static seedu.careflow.testutil.TypicalDrugs.ROBITUSSIN;
import static seedu.careflow.testutil.TypicalPatients.ALICE;
import static seedu.careflow.testutil.TypicalPatients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.model.patient.NameContainsKeywordsPredicate;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.testutil.DrugInventoryBuilder;
import seedu.careflow.testutil.PatientBuilder;
import seedu.careflow.testutil.PatientRecordBuilder;

public class CareFlowModelManagerTest {

    private CareFlowModelManager modelManager = new CareFlowModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PatientRecord(), new PatientRecord(modelManager.getPatientRecord()));
        assertEquals(new DrugInventory(), new DrugInventory(modelManager.getDrugInventory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPatientRecordFilePath(Paths.get("address/book/file/patientpath"));
        userPrefs.setDrugInventoryFilePath(Paths.get("address/book/file/drugpath"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4, "DARK"));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPatientRecordFilePath(Paths.get("new/address/book/file/patientpath"));
        userPrefs.setDrugInventoryFilePath(Paths.get("new/address/book/file/drugpath"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4, "LIGHT");
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPatientRecordFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPatientRecordFilePath(null));
    }

    @Test
    public void setPatientRecordFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("careflow/file/patientpath");
        modelManager.setPatientRecordFilePath(path);
        assertEquals(path, modelManager.getPatientRecordFilePath());
    }

    @Test
    public void getDrugInventoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDrugInventoryFilePath(null));
    }

    @Test
    public void getDrugInventoryFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("careflow/file/drugpath");
        modelManager.setDrugInventoryFilePath(path);
        assertEquals(path, modelManager.getDrugInventoryFilePath());
    }

    @Test
    public void hasSamePatientName_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSamePatientName(null));
    }

    @Test
    public void hasSamePatientName_patientNotInPatientRecord_returnsFalse() {
        assertFalse(modelManager.hasSamePatientName(ALICE));
    }

    @Test
    public void hasSamePatientName_patientInPatientRecord_returnsTrue() {
        modelManager.addPatient(ALICE);
        assertTrue(modelManager.hasSamePatientName(ALICE));
    }

    @Test
    public void hasSamePatientIc_nullIc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSamePatientIc(null));
    }

    @Test
    public void hasSamePatientIc_icNotInPatientRecord_returnsFalse() {
        assertFalse(modelManager.hasSamePatientIc(ALICE));
    }

    @Test
    public void hasSamePatientIc_icInPatientRecord_returnsTrue() {
        modelManager.addPatient(ALICE);
        Patient patient = new PatientBuilder().withIc(ALICE.getIc().value).build();
        assertTrue(modelManager.hasSamePatientIc(ALICE));
    }

    @Test
    public void hasDrug_nullDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDrug(null));
    }

    @Test
    public void hasDrug_drugNotInDrugInventory_returnsFalse() {
        assertFalse(modelManager.hasDrug(PROZAC));
    }

    @Test
    public void hasDrug_drugInDrugInventory_returnsTrue() {
        modelManager.addDrug(PROZAC);
        assertTrue(modelManager.hasDrug(PROZAC));
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().remove(0));
    }

    @Test
    public void getFilteredDrugList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDrugList().remove(0));
    }

    @Test
    public void equals() {
        PatientRecord patientRecord = new PatientRecordBuilder().withPatient(ALICE).withPatient(BENSON).build();
        DrugInventory drugInventory = new DrugInventoryBuilder().withDrug(PROZAC).withDrug(ROBITUSSIN).build();

        PatientRecord differentPatientRecord = new PatientRecord();
        DrugInventory differentDrugInventory = new DrugInventory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new CareFlowModelManager(patientRecord, drugInventory, userPrefs);
        CareFlowModelManager modelManagerCopy = new CareFlowModelManager(patientRecord, drugInventory, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different careFlow -> returns false
        assertFalse(modelManager.equals(new CareFlowModelManager(differentPatientRecord,
                differentDrugInventory, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new CareFlowModelManager(patientRecord,
                drugInventory, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPatientRecordFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new CareFlowModelManager(patientRecord,
                drugInventory, differentUserPrefs)));
    }
}

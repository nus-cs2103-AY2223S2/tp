package seedu.careflow.logic.commands.patientcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_AMY;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.AMY;
import static seedu.careflow.testutil.TypicalPatients.BOB;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.ReadOnlyUserPrefs;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.testutil.PatientBuilder;

class AddCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPatientAdded modelStub = new ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPatient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
    }
    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddCommand addCommand = new AddCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);
        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PATIENT_NAME, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePatientIc_throwsCommandException() {
        Patient validPatient = new PatientBuilder(AMY).build();
        // patient with duplicate IC as validPatient
        Patient patient = new PatientBuilder(BOB).withIc(VALID_IC_AMY).build();
        AddCommand addCommand = new AddCommand(patient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);
        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PATIENT_IC, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    private class ModelStub implements CareFlowModel {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPatientRecordFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDrugInventoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatientRecordFilePath(Path patientRecordFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDrugInventoryFilePath(Path drugInventoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatientRecord(ReadOnlyPatientRecord patientRecord) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDrugInventory(ReadOnlyDrugInventory drugInventory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPatientRecord getPatientRecord() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDrugInventory getDrugInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHospitalRecords getHospitalRecords() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSamePatientName(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSamePatientIc(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDrug(Drug drug) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDrug(Drug target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDrug(Drug drug) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDrug(Drug target, Drug editedDrug) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Drug> getFilteredDrugList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Hospital> getHospitalList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDrugList(Predicate<Drug> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasSamePatientName(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSamePatient(patient);
        }

        @Override
        public boolean hasSamePatientIc(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSameIc(patient);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        final ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public boolean hasSamePatientName(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSamePatient);
        }

        @Override
        public boolean hasSamePatientIc(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSameIc);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public ReadOnlyPatientRecord getPatientRecord() {
            return new PatientRecord();
        }
    }
}

package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

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
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.ReadOnlyUserPrefs;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.testutil.DrugBuilder;

class AddCommandTest {
    @Test
    public void constructor_nullDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_drugAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDrugAdded modelStub = new ModelStubAcceptingDrugAdded();
        Drug validDrug = new DrugBuilder().build();

        CommandResult commandResult = new AddCommand(validDrug).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validDrug), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDrug), modelStub.drugsAdded);
    }

    @Test
    public void execute_duplicateDrug_throwsCommandException() {
        Drug validDrug = new DrugBuilder().build();
        AddCommand addCommand = new AddCommand(validDrug);
        ModelStub modelStub = new ModelStubWithDrug(validDrug);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_DRUG, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Drug prozac = new DrugBuilder().withTradeName("Prozac").build();
        Drug visine = new DrugBuilder().withTradeName("Bob").build();
        AddCommand addProzacCommand = new AddCommand(prozac);
        AddCommand addVisineCommand = new AddCommand(visine);

        // same object -> returns true
        assertTrue(addProzacCommand.equals(addProzacCommand));

        // same values -> returns true
        AddCommand addProzacCommandCopy = new AddCommand(prozac);
        assertTrue(addProzacCommand.equals(addProzacCommandCopy));

        // different types -> returns false
        assertFalse(addProzacCommand.equals(1));

        // null -> returns false
        assertFalse(addProzacCommand.equals(null));

        // different person -> returns false
        assertFalse(addProzacCommand.equals(addVisineCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDrug(Drug drug) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatientRecord(ReadOnlyPatientRecord newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPatientRecord getPatientRecord() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDrugInventory(ReadOnlyDrugInventory newData) {
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
     * A Model stub that contains a single drug.
     */
    private class ModelStubWithDrug extends ModelStub {
        private final Drug drug;

        ModelStubWithDrug(Drug drug) {
            requireNonNull(drug);
            this.drug = drug;
        }

        @Override
        public boolean hasDrug(Drug drug) {
            requireNonNull(drug);
            return this.drug.isSameDrug(drug);
        }
    }

    /**
     * A Model stub that always accept the drug being added.
     */
    private class ModelStubAcceptingDrugAdded extends ModelStub {
        final ArrayList<Drug> drugsAdded = new ArrayList<>();

        @Override
        public boolean hasDrug(Drug drug) {
            requireNonNull(drug);
            return drugsAdded.stream().anyMatch(drug::isSameDrug);
        }

        @Override
        public void addDrug(Drug drug) {
            requireNonNull(drug);
            drugsAdded.add(drug);
        }

        @Override
        public ReadOnlyDrugInventory getDrugInventory() {
            return new DrugInventory();
        }
    }
}

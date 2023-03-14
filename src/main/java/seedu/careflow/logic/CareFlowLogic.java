package seedu.careflow.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * API of the CareFlow Logic component
 */
public interface CareFlowLogic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the PatientRecord
     */
    ReadOnlyPatientRecord getPatientRecord();

    /**
     * Returns the DrugInventory
     */
    ReadOnlyDrugInventory getDrugInventory();

    ReadOnlyHospitalRecords getHospitalRecord();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered list of drugs */
    ObservableList<Drug> getFilteredDrugList();

    /** Returns an unmodifiable view of hospitals */
    ObservableList<Hospital> getHospitalList();

    /**
     * Returns the user prefs' patient record file path.
     */
    Path getPatientRecordFilePath();

    /**
     * Returns the user prefs' drug inventory file path.
     */
    Path getDrugInventoryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}

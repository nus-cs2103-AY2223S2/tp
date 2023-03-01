package seedu.address.logic;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drug.Drug;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.readonly.ReadOnlyDrugInventory;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.nio.file.Path;

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

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered list of drugs */
    ObservableList<Drug> getFilteredDrugList();

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

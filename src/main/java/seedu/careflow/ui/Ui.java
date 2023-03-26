package seedu.careflow.ui;

import javafx.stage.Stage;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.patient.Patient;

/**
 * API of UI component
 */
public interface Ui {
    /**
     * Starts the UI (and the App).
     */
    void start(Stage primaryStage);

    /**
     * Display all details of a selected patient
     */
    void showSelectedPatient(Patient selectedPatient);

    /**
     * Display all details of a selected drug
     */
    void showSelectedDrug(Drug selectedDrug);
}

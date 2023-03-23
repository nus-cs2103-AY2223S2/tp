package seedu.address.ui;

import javafx.stage.Stage;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /**
     * Show information about selected {@code Doctor}.
     *
     * @param doctor a selected doctor.
     */
    void showSelectedDoctor(Doctor doctor);

    /**
     * Show information about selected {@code Patient}.
     *
     * @param patient a selected patient.
     */
    void showSelectedPatient(Patient patient);

    /**
     * Scrolls down to show information about selected {@code Doctor}.
     *
     * @param doctorIndex the Index of the selected doctor.
     */
    void scrollToSelectedDoctor(Index doctorIndex);

    /**
     * Scrolls down to show information about selected {@code Patient}.
     *
     * @param patientIndex the Index of the selected patient.
     */
    void scrollToSelectedPatient(Index patientIndex);
}

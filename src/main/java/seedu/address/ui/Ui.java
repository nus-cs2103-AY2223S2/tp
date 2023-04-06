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

}

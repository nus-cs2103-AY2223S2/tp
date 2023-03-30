package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.model.patient.Patient;

public class PatientViewCard extends UiPart<Stage> {

    private static final String FXML = "PatientViewCard.fxml";

    @FXML
    private Label patientCard;

    public PatientViewCard(Patient patient, Stage root) {
        super(FXML, root);
        if (patient == null) {
            patientCard.setText("");
        } else {
            patientCard.setGraphic(new PatientCard(patient).getRoot());
        }
    }

    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}



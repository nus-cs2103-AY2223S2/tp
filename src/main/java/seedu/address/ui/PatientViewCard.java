package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.model.patient.Patient;

/**
 * A UI component that pops up and displays information of an individual {@code Patient}.
 */
public class PatientViewCard extends UiPart<Stage> {

    private static final String FXML = "PatientViewCard.fxml";

    @FXML
    private Label patientViewCard;

    /**
     * Creates a card to view details of the {@code Patient}.
     */
    public PatientViewCard(Patient patient, Stage root) {
        super(FXML, root);
        if (patient == null) {
            patientViewCard.setText("");
        } else {
            patientViewCard.setText(patient.getName().fullName + "\n" + patient.getAddress().value + "\n"
                    + patient.getPhone().value + "\n" + patient.getEmail().value + "\n" + patient.getRemark() + "\n"
                    + patient.getTags().toString());
        }
    }

    /**
     * Shows the patientCard window.
     */
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



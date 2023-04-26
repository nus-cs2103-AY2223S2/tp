package seedu.medinfo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medinfo.model.patient.Patient;


/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/medInfo-level4/issues/336">The
     *      issue on MedInfo level 4</a>
     */

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label nric;
    @FXML
    private Label status;
    @FXML
    private Label ward;
    @FXML
    private Label discharge;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to
     * display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        nric.setText(patient.getNric().value);
        String statusString = patient.getStatusDesc();
        status.setText(statusString);
        status.getStyleClass().clear();
        status.getStyleClass().add("status-" + statusString);
        ward.setText(patient.getWardNameString());
        discharge.setText(patient.getDischargeString());
    }

    /**
     * Overrides equality check for PatientCard to compare two patient cards by
     * the NRICs and the patients.
     *
     * @param other Object to check equality with.
     * @return Boolean indicating whether objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return nric.getText().equals(card.nric.getText())
                && patient.equals(card.patient);
    }
}

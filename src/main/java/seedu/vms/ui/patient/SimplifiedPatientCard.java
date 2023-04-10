package seedu.vms.ui.patient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.vms.model.IdData;
import seedu.vms.model.patient.Patient;
import seedu.vms.ui.UiPart;


/**
 * Simplified graphical view of a patient.
 */
public class SimplifiedPatientCard extends UiPart<HBox> {
    private static final String FXML_FILE = "SimplifiedPatientCard.fxml";

    @FXML private Label idLabel;
    @FXML private Label nameLabel;


    /**
     * Constructs a {@code SimplifiedVaxTypeCard}.
     *
     * @param patient - the vaccination to display.
     */
    public SimplifiedPatientCard(IdData<Patient> data) {
        super(FXML_FILE);
        idLabel.setText(String.format("#%04d", data.getId() + 1));
        nameLabel.setText(data.getValue().getName().toString());
    }
}

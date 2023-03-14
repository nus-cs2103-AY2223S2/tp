package seedu.vms.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.vms.model.patient.Patient;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label dobLabel;
    @FXML private Label bloodTypeLabel;
    @FXML private VBox allergyBox;
    @FXML private VBox vaccineBox;
    @FXML private Label phoneLabel;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int id) {
        super(FXML);
        idLabel.setText(String.format("#%d", id));
        nameLabel.setText(patient.getName().toString());
        dobLabel.setText(patient.getDob().toString());
        bloodTypeLabel.setText(patient.getBloodType().toString());
        allergyBox.getChildren().add(new TagFlowView(
                patient.getAllergyAsString(),
                TagFlowView.STYLE_CLASS_TAG_RED));
        vaccineBox.getChildren().add(new TagFlowView(
                patient.getVaccineAsString(),
                TagFlowView.STYLE_CLASS_TAG_GREEN));
        phoneLabel.setText(patient.getPhone().toString());
    }
}

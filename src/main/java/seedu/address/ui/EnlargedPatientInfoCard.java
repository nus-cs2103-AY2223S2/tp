package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.patient.Patient;

/**
 * An UI component that displays all information of a {@code Patient}.
 */
public class EnlargedPatientInfoCard extends UiPart<Region> {
    private static final String FXML = "EnlargedPatientInfoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Optional<Patient> selectedPatientOptional;

    @javafx.fxml.FXML
    private VBox enlargedPatientInfoCard;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label height;
    @FXML
    private Label weight;
    @FXML
    private Label diagnosis;
    @FXML
    private Label status;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EnlargedPatientInfoCard} with the given {@code Optional< Patient >}.
     */
    public EnlargedPatientInfoCard(Optional<Patient> selectedPatientOptional) {
        super(FXML);
        updateSelectedPatientOptional(selectedPatientOptional);
    }

    /**
     * Creates an empty {@code EnlargedPatientInfoCard}.
     */
    public EnlargedPatientInfoCard() {
        super(FXML);
        clearDisplay();
    }

    /**
     * Updates the selected patient stored in {@code EnlargedPatientInfoCard}.
     *
     * @param selectedPatient the given {@code Optional< Patient >}
     */
    public void updateSelectedPatientOptional(Optional<Patient> selectedPatient) {
        this.selectedPatientOptional = selectedPatient;
        updateDisplay();
    }

    /**
     * Updates the information shown on the {@code EnlargedPatientInfoCard}
     * with that of the stored {@code Optional< Patient >}.
     * If no patient is stored, then the {@code EnlargedPatientInfoCard} is cleared.
     */
    private void updateDisplay() {
        if (selectedPatientOptional.isEmpty()) {
            clearDisplay();
            return;
        }
        Patient selectedPatient = selectedPatientOptional.get();
        name.setText(selectedPatient.getName().toString());
        phone.setText(selectedPatient.getPhone().toString());
        email.setText(selectedPatient.getEmail().toString());
        height.setText(selectedPatient.getHeight().toString());
        weight.setText(selectedPatient.getWeight().toString());
        diagnosis.setText(selectedPatient.getDiagnosis().toString());
        status.setText(selectedPatient.getStatus().toString());
        remark.setText(selectedPatient.getRemark().toString());
        tags.getChildren().clear();
        selectedPatient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tagLabel.setWrapText(true);
                    tagLabel.setMaxWidth(150);
                    tags.getChildren().add(tagLabel);
                });
    }

    /**
     * Clears the information shown on the {@code EnlargedPatientInfoCard}.
     */
    public void clearDisplay() {
        name.setText("");
        phone.setText("");
        email.setText("");
        height.setText("");
        weight.setText("");
        diagnosis.setText("");
        status.setText("");
        remark.setText("");
        tags.getChildren().clear();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnlargedPatientInfoCard)) {
            return false;
        }

        // state check
        EnlargedPatientInfoCard card = (EnlargedPatientInfoCard) other;
        return selectedPatientOptional.equals(card.selectedPatientOptional);
    }
}

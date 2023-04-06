package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays all information of a {@code Patient}.
 */
public class EnlargedPatientInfoCard extends EnlargedInfoCard {
    private static final String FXML = "EnlargedPatientInfoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Optional<Patient> selectedPatientOptional;

    @FXML
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
        clearDisplay();
        if (selectedPatientOptional.isEmpty()) {
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
        selectedPatient.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(this::addTagToFlowPane);
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
        this.clearTags();
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

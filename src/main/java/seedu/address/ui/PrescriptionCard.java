package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Patient;

/**
 * Creates a {@code PrescriptionCard} with the given {@code Person} and medication to display.
 */
public class PrescriptionCard extends UiPart<Region> {
    private static final String FXML = "PrescriptionListCard.fxml";

    public final Medication medication;

    public final Patient patient;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;

    @FXML
    private Label meds;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PrescriptionCard(Patient patient, Medication medication) {
        super(FXML);
        this.patient = (Patient) patient;
        this.medication = medication;

        meds.setText(patient.getMedication().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PrescriptionCard)) {
            return false;
        }

        // state check
        PrescriptionCard card = (PrescriptionCard) other;
        return medication.equals(card.medication);
    }

}

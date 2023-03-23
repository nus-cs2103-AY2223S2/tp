package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Ward;


/**
 * An UI component that displays information of a {@code Patient}.
 */
public class WardCard extends UiPart<Region> {

    private static final String FXML = "WardListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Ward ward;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label occupancy;
    @FXML
    private Label capacity;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to
     * display.
     */
    public WardCard(Ward ward, int displayedIndex) {
        super(FXML);
        this.ward = ward;
        id.setText(displayedIndex + ". ");
        name.setText(ward.getName());
        nric.setText(patient.getNric().value);
        String statusString = patient.getStatusDesc();
        status.setText(statusString);
        status.getStyleClass().clear();
        status.getStyleClass().add("status-" + statusString);
        ward.setText(patient.getWard().value);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WardCard)) {
            return false;
        }

        // state check
        WardCard card = (WardCard) other;
        return nric.getText().equals(card.nric.getText())
                && patient.equals(card.patient);
    }
}

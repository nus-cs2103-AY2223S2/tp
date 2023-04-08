package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays all information of a {@code Doctor}.
 */
public class EnlargedDoctorInfoCard extends EnlargedInfoCard {
    private static final String FXML = "EnlargedDoctorInfoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Doctor selectedDoctor;

    @FXML
    private VBox enlargedDoctorInfoCard;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label specialty;
    @FXML
    private Label yearsOfExperience;


    /**
     * Creates an empty {@code EnlargedDoctorInfoCard}.
     */
    public EnlargedDoctorInfoCard() {
        super(FXML);
        clearDisplay();
    }

    /**
     * Updates the selected doctor stored in {@code EnlargedDoctorInfoCard}.
     *
     * @param selectedDoctor the given {@code Doctor}
     */
    public void updateSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
        updateDisplay();
    }

    /**
     * Updates the information shown on the {@code EnlargedDoctorInfoCard}
     * with that of the stored {@code Doctor}.
     * If no doctor is stored, then the {@code EnlargedDoctorInfoCard} is cleared.
     */
    private void updateDisplay() {
        clearDisplay();
        if (selectedDoctor == null) {
            return;
        }

        name.setText(selectedDoctor.getName().getValue());
        phone.setText(selectedDoctor.getPhone().getValue());
        email.setText(selectedDoctor.getEmail().getValue());
        specialty.setText(selectedDoctor.getSpecialty().getValue());
        yearsOfExperience.setText(selectedDoctor.getYoe().getValue());
        selectedDoctor.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(this::addTagToFlowPane);
    }

    /**
     * Clears the information shown on the {@code EnlargedDoctorInfoCard}.
     */
    public void clearDisplay() {
        name.setText("");
        phone.setText("");
        email.setText("");
        specialty.setText("");
        yearsOfExperience.setText("");
        this.clearTags();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnlargedDoctorInfoCard)) {
            return false;
        }

        // state check
        EnlargedDoctorInfoCard card = (EnlargedDoctorInfoCard) other;
        if (selectedDoctor == null) {
            return card.selectedDoctor == null;
        }
        return selectedDoctor.equals(card.selectedDoctor);
    }
}

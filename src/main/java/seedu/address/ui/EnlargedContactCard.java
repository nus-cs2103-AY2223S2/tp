package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Doctor;

/**
 * An UI component that displays all information of a {@code Doctor}.
 */
public class EnlargedContactCard extends UiPart<Region> {
    private static final String FXML = "EnlargedContactCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Doctor selectedDoctor;

    @javafx.fxml.FXML
    private VBox enlargedContactCard;
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
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EnlargedContactCard} with the given {@code Doctor}.
     */
    public EnlargedContactCard(Doctor selectedDoctor) {
        super(FXML);
        updateSelectedDoctor(selectedDoctor);
    }

    /**
     * Updates the information shown on the {@code EnlargedContactCard}
     * with that of the given {@code Doctor}.
     *
     * @param selectedDoctor the given {@code Doctor}
     */
    public void updateSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
        name.setText(selectedDoctor.getName().fullName);
        phone.setText(selectedDoctor.getPhone().value);
        email.setText(selectedDoctor.getEmail().value);
        specialty.setText(selectedDoctor.getSpecialty().specialty);
        yearsOfExperience.setText(selectedDoctor.getYoe().value);
        tags.getChildren().clear();
        selectedDoctor.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnlargedContactCard)) {
            return false;
        }

        // state check
        EnlargedContactCard card = (EnlargedContactCard) other;
        return selectedDoctor.equals(card.selectedDoctor);
    }
}

package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ViewPane extends UiPart<Region> {

    private static final String FXML = "ViewPane.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox viewPane;
    @FXML
    private Label nric;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label doctor;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label gender;
    @FXML
    private Label drugAllergy;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane medicines;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ViewPane(Person person) {
        super(FXML);
        this.person = person;
        nric.setText("Nric: " + person.getNric().fullNric);
        name.setText(person.getName().fullName);
        date.setText("Birth Date: " + person.getDate().dateOfBirth);
        doctor.setText("Attending Doctor: " + person.getDoctor());
        phone.setText("Phone Number: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        gender.setText("Gender: " + person.getGender().gender);
        drugAllergy.setText("Drug Allergy: " + person.getDrugAllergy().value);
        email.setText("Email: " + person.getEmail().value);
        address.setWrapText(true);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getMedicines().stream()
                .sorted(Comparator.comparing(medicine -> medicine.medicineName))
                .forEach(medicine -> medicines.getChildren().add(new Label(medicine.medicineName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewPane)) {
            return false;
        }

        // state check
        ViewPane viewPane = (ViewPane) other;
        return person.equals(viewPane.person);
    }
}

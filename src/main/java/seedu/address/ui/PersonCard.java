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
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    @FXML
    private Label medicalCondition;

    @FXML
    private Label age;
    @FXML
    private FlowPane tags;
    @FXML
    private Label appointment;

    @FXML
    private Label nric;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person.getAge() == null) {
            age.setText("");
        } else {
            String s = person.getAge().toString();
            age.setText(s);
        }
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        if (person.hasAppointment()) {
            appointment.setText(person.getAppointment().toString());
            if (person.getMedicalCondition() != null) {
                String s = person.getMedicalCondition().getValue();
                if (s.equals("")) {
                    medicalCondition.setText("Medical Condition: NA");
                } else {
                    medicalCondition.setText("Medical Condition: " + s);
                }
            }
        } else {
            appointment.setText("No appointment yet");
            if (person.getMedicalCondition() == null) {
                medicalCondition.setText("Medical Condition: NA");
            } else {
                String s = person.getMedicalCondition().getValue();
                if (s.equals("")) {
                    medicalCondition.setText("Medical Condition: NA");
                } else {
                    medicalCondition.setText("Medical Condition: " + s);
                }
            }
        }
        if (person.getNric() == null) {
            nric.setText("NRIC:" + "");
        } else {
            String s = person.getNric().toString();
            nric.setText(s);
        }
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

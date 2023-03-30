package teambuilder.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import teambuilder.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
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
    private Label major;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane teams;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        setPhone(person);
        setAddress(person);
        setEmail(person);
        setMajor(person);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getTeams().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> teams.getChildren().add(new Label(tag.tagName)));
    }

    private void setPhone(Person person) {
        if (person.getPhone().isEmptyPhone()) {
            phone.setVisible(false);
            phone.setManaged(false);
        } else {
            phone.setText(person.getPhone().toString());
        }
    }

    private void setEmail(Person person) {
        if (person.getEmail().isEmptyEmail()) {
            email.setVisible(false);
            email.setManaged(false);
        } else {
            email.setText(person.getEmail().toString());
        }
    }

    private void setMajor(Person person) {
        if (person.getMajor().isEmptyMajor()) {
            major.setVisible(false);
            major.setManaged(false);
        } else {
            major.setText(person.getMajor().toString());
        }
    }

    private void setAddress(Person person) {
        if (person.getAddress().isEmptyAddress()) {
            address.setVisible(false);
            address.setManaged(false);
        } else {
            address.setText(person.getAddress().toString());
        }
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

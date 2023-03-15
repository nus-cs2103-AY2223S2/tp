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
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private HBox birthdayContainer;
    @FXML
    private HBox phoneContainer;
    @FXML
    private HBox emailContainer;
    @FXML
    private HBox addressContainer;
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
    private FlowPane tags;

    @FXML
    private Label birthday;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person.getPhone().isPresent()) {
            phone.setText(person.getPhone().get().toString());
        } else {
            phone.setText("");
            phoneContainer.setVisible(false);
            phoneContainer.managedProperty().bind(phoneContainer.visibleProperty());
        }
        if (person.getEmail().isPresent()) {
            email.setText(person.getEmail().get().toString());
        } else {
            email.setText("");
            emailContainer.setVisible(false);
            emailContainer.managedProperty().bind(emailContainer.visibleProperty());
        }
        if (person.getAddress().isPresent()) {
            address.setText(person.getAddress().get().toString());
        } else {
            address.setText("");
            addressContainer.setVisible(false);
            addressContainer.managedProperty().bind(addressContainer.visibleProperty());
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
                
        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.tagName))
                .forEach(module -> tags.getChildren().add(new Label(module.tagName)));

        if (person.getBirthday().isPresent()) {
            birthday.setText(person.getBirthday().get().toString());
        } else {
            birthday.setText("");
            birthdayContainer.setVisible(false);
            birthdayContainer.managedProperty().bind(birthdayContainer.visibleProperty());
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

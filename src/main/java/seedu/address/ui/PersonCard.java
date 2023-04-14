package seedu.address.ui;

import java.util.Comparator;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
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

    public final MainWindow mainWindow;
    public final Person person;
    private final String attendingDoctor = "Attending Doctor: ";
    @FXML
    private HBox cardPane;
    @FXML
    private Label nric;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label gender;
    @FXML
    private Label doctor;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, MainWindow mainWindow) {
        super(FXML);
        this.person = person;
        this.mainWindow = mainWindow;
        cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, viewPerson());
        id.setText(displayedIndex + ". ");
        nric.setText(person.getNric().fullNric);
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        gender.setText(person.getGender().gender);
        doctor.setText(attendingDoctor + person.getDoctor().doctor);

        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private EventHandler<MouseEvent> viewPerson() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent clickEvent) {
                try {
                    mainWindow.execute("view i/" + person.getNric().fullNric);
                } catch (CommandException | IllegalValueException e) {
                    e.printStackTrace();
                }
            };
        };
    };

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

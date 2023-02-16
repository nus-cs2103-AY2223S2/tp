package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays detailed information of a {@code Person}.
 */
public class PersonDetailPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailPanel.fxml";

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

    /**
     * Creates a blank {@code PersonDetailPanel}.
     */
    public PersonDetailPanel() {
        super(FXML);
        clearPerson();
    }

    public void setPerson(Person person, int displayedIndex) {
        clearPerson();
        if (person == null) {
            return;
        }

        id.setText(displayedIndex + ".");
        name.setText(person.getName().toString());
        phone.setText(person.getPhone().toString());
        address.setText(person.getAddress().toString());
        email.setText(person.getEmail().toString());
        person.getTags()
                .stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Empties the fields, resulting in a blank {@code PersonDetailPanel}.
     */
    public void clearPerson() {
        id.setText("Select a contact.");
        name.setText(null);
        phone.setText(null);
        address.setText(null);
        email.setText(null);
        tags.getChildren().clear();
    }
}

package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays more detailed information of a {@code Person} when selected.
 */
public class PersonalPane extends UiPart<Region> {

    private static final String FXML = "PersonalPane.fxml";

    @FXML
    private HBox personalPane;

    @FXML
    private ImageView imageView;
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
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonalPane(Person person) {
        super(FXML);
        Image image;
        if (person.hasDefaultImage()) {
            image = new Image(person.getImagePath());
        } else {
            image = new Image("file:" + person.getImagePath());
        }
        imageView.setImage(image);
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}

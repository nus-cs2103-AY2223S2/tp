package seedu.address.ui;

import java.io.IOException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ImageUtil;

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
    private Label status;

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
            try {
                if (ImageUtil.isValidImage(person.getImagePath())) {
                    image = new Image("file:" + person.getImagePath());
                } else {
                    image = new Image(Person.getDefaultImagePath());
                }
            } catch (IOException io) {
                image = new Image(Person.getDefaultImagePath());
            }
        }
        imageView.setImage(image);
        name.setText(person.getName().fullName);
        status.setText(person.getStatus().fullStatusDetail);
        status.setWrapText(true);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        address.setWrapText(true);
        email.setText(person.getEmail().value);
        email.setWrapText(true);
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(this::createLabel);

    }

    /**
     * Creates labels based on the types to be displayed.
     *
     * @param tag takes in a tag type to extract information from within
     */
    private void createLabel(Tag tag) {
        Label label = new Label(tag.tagName.contains("XXXXX")
            ? tag.tagName.split("XXXXX")[1]
            : tag.tagName);
        String colour = tag.tagColor();
        label.setStyle("-fx-background-color: " + colour + ";");
        tags.getChildren().add(label);
    }

}

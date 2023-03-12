package seedu.address.ui.parent;

import java.io.File;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.model.person.parent.Parent;
import seedu.address.ui.UiPart;

public class ParentCard extends UiPart<Region> {
    private static final String FXML = "StudentCard.fxml";

    public final Parent parent;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label relationship;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label age;
    // @FXML
    // private Label image;
    @FXML
    private Circle circle;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ParentCard(Parent parent, int displayedIndex) {
        super(FXML);
        this.parent = parent;
        name.setText(parent.getName().fullName);
        phone.setText(parent.getPhone().value);
        email.setText(parent.getEmail().value);
        age.setText(parent.getAge().value);
        //image.setText(student.getImage().value);
        parent.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        String path = "src/main/resources/images/" + parent.getName() + ".png";
        File file = new File(path);
        if (!file.exists()) {
            path = "src/main/resources/images/defaultParent.png";
            file = new File(path);
            Image newImage = new Image(file.toURI().toString());
            circle.setFill(new ImagePattern(newImage));
        } else {
            Image newImage = new Image(file.toURI().toString());
            circle.setFill(new ImagePattern(newImage));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ParentCard)) {
            return false;
        }

        return false;
    }
}

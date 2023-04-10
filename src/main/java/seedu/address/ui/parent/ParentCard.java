package seedu.address.ui.parent;

import java.io.File;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.model.person.parent.Parent;
import seedu.address.ui.UiPart;

/**
 * A parent card feature in the UI representing parent information.
 */
public class ParentCard extends UiPart<Region> {
    private static final String FXML = "ParentCard.fxml";

    public final Parent parent;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
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
    @FXML
    private Label id;
    @FXML
    private GridPane students;
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ParentCard(Parent parent, int displayedIndex) {
        super(FXML);
        this.parent = parent;
        id.setText(displayedIndex + ". ");
        name.setText(parent.getName().fullName);
        phone.setText(parent.getPhone().value);
        email.setText(parent.getEmail().value);
        address.setText(parent.getAddress().value);
        age.setText(parent.getAge().value);
        AtomicInteger rowCounter = new AtomicInteger();
        parent.getStudents().stream()
                .sorted(Comparator.comparing(Student -> Student.getName().fullName))
                .forEach(Student -> {
                    students.add(new Label(" Student Name: "), 0, rowCounter.get());
                    students.add(new Label(Student.getName().fullName), 1, rowCounter.get());
                    students.add(new Label("\n"), 2, rowCounter.get());
                    students.add(new Label(" Student Class: "), 3, rowCounter.get());
                    students.add(new Label(Student.getStudentClass().getClassName()), 4, rowCounter.get());
                    students.add(new Label("\n"), 5, rowCounter.get());
                    students.add(new Label(" Index Number: "), 6, rowCounter.get());
                    students.add(new Label(Student.getIndexNumber().value), 7, rowCounter.get());
                    rowCounter.getAndIncrement();
                });
        updateImage();
    }

    /**
     * Updates the image of the parent.
     */
    public void updateImage() {
        String strImage = parent.getImage().value;
        if (!strImage.equals("Insert parent image here!")) {
            File file = new File(strImage);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                circle.setFill(new ImagePattern(image));
                return;
            }
        }
        String path = "images/parent/" + parent.getName() + ".png";
        File file = new File(path);
        if (!file.exists()) {
            Image defaultImage = new Image("images/defaultParent.png");
            circle.setFill(new ImagePattern(defaultImage));
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
        return false;
    }
}

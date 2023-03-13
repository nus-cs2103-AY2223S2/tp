package seedu.address.ui.student;

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
import seedu.address.model.person.student.Student;
import seedu.address.ui.UiPart;

/**
 * A student card feature in the UI representing student information.
 */
public class StudentCard extends UiPart<Region> {
    private static final String FXML = "StudentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
    @FXML
    private Label sex;
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
    private Label parentName;
    @FXML
    private Label age;
    // @FXML
    // private Label image;
    @FXML
    private Label cca;
    @FXML
    private Circle circle;
    @FXML
    private Label className;
    @FXML
    private Label comment;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        className.setText(student.getStudentClass().getClassName());
        index.setText(student.getIndexNumber().value);
        sex.setText(student.getSex().value);
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        parentName.setText(student.getParentName().value);
        age.setText(student.getAge().value);
        //image.setText(student.getImage().value);
        cca.setText(student.getCca().value);
        comment.setText(student.getComment().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        String path = "src/main/resources/images/" + student.getName() + ".png";
        File file = new File(path);
        if (!file.exists()) {
            path = "src/main/resources/images/defaultStudent.png";
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
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}

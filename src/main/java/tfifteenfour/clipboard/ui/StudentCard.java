package tfifteenfour.clipboard.ui;

import java.io.File;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label studentId;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane modules;
    @FXML
    private Label remark;
    @FXML
    private ImageView displayPhoto;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        studentId.setText(student.getStudentId().value);
        email.setText(student.getEmail().value);
        displayPhoto.setImage(new Image(this.getClass().getResourceAsStream("/images/studenticon.png")));
        student.getModules().stream()
                .sorted(Comparator.comparing(module -> module.moduleCode))
                .forEach(module -> modules.getChildren().add(new Label(module.moduleCode)));
        remark.setText(student.getRemark().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        try {
            String imageUrl = "data/" + student.getStudentId() + ".png";
            File file = new File(imageUrl);
            if (!file.exists()) {
                Image defaultImage = new Image(this.getClass().getResourceAsStream("/images/studenticon.png"));
                displayPhoto.setImage(defaultImage);
            } else {
                Image newImage = new Image(file.toURI().toString());
                displayPhoto.setImage(newImage);
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
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

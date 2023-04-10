package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *      Mathutoring level 4</a>
     */

    public final Student student;

    private ExportProgressWindow exportProgressWindow;
    private Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label parentPhone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView avatar;
    @FXML
    private ImageView phoneIcon1;
    @FXML
    private ImageView phoneIcon2;
    @FXML
    private ImageView addressIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private Button exportProgressButton;


    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display,
     * together with the import information.
     */
    public StudentCard(Student student, int displayedIndex, ExportProgressWindow exportProgressWindow) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        parentPhone.setText(student.getParentPhone().value + " (P)");
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        phoneIcon1.setImage(getPic("/images/Phone.png"));
        phoneIcon2.setImage(getPic("/images/Phone.png"));
        addressIcon.setImage(getPic("/images/Address.png"));
        emailIcon.setImage(getPic("/images/Email.png"));

        student.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        studentCardImage(student);
        this.exportProgressWindow = exportProgressWindow;
    }

    private Image getPic(String picName) {
        return new Image((this.getClass().getResourceAsStream(picName)));
    }

    /**
     * Provides avatar based on student's gender.
     *
     * @param student a given student
     */
    private void studentCardImage(Student student) {
        boolean isMale = false;
        boolean isFemale = false;
        String lowerCaseTagNameGender = "";

        for (Tag tag : student.getTags()) {
            lowerCaseTagNameGender = tag.tagName.toLowerCase();

            if (lowerCaseTagNameGender.equals("male")) {
                isMale = true;
            } else if (lowerCaseTagNameGender.equals("female")) {
                isFemale = true;
            }
        }

        if (isMale && isFemale) {
            avatar.setImage(getPic("/images/unisex.png"));
        } else if (isMale) {
            avatar.setImage(getPic("/images/male.png"));
        } else if (isFemale) {
            avatar.setImage(getPic("/images/female.png"));
        } else {
            avatar.setImage(getPic("/images/unisex.png"));
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
        return id.getText().equals(card.id.getText()) && student.equals(card.student);
    }

    /**
     * Opens the export progress window or focuses on it if it's already opened.
     */
    public void exportProgress() {
        this.exportProgressWindow.setCheckedStudent(this.student);
        if (!this.exportProgressWindow.isShowing()) {
            exportProgressWindow.show();
        } else {
            exportProgressWindow.focus();
        }
    }
}

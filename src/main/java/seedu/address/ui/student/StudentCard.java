package seedu.address.ui.student;

import java.io.File;
import java.time.LocalDate;
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
import seedu.address.model.person.student.Attendance;
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
    private Label attendance;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label parentPhoneNumber;
    @FXML
    private Label parentName;
    @FXML
    private Label rls;
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
    @FXML
    private GridPane tests;
    @FXML
    private GridPane homework;

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

        LocalDate currDate = LocalDate.now();
        attendance.setText("F");
        for (Attendance attendance : student.getAttendance()) {
            if (attendance.isPresent(currDate)) {
                this.attendance.setText("T");
            }
        }

        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        address.setText(student.getAddress().value);
        parentName.setText(student.getParentName().fullName);
        parentPhoneNumber.setText(student.getParentNumber().value);
        rls.setText(student.getRls().toString());
        age.setText(student.getAge().value);
        cca.setText(student.getCca().value);
        comment.setText(student.getComment().value);
        updateTest();
        updateHomework();
        updateImage();
    }

    /**
     * Updates the homework of the student
     */
    public void updateHomework() {
        AtomicInteger rowCounter = new AtomicInteger();
        student.getHomework().stream()
                .sorted(Comparator.comparing(hw -> hw.getName()))
                .forEach(hw -> {
                    homework.add(new Label("Name: "), 0, rowCounter.get());
                    homework.add(new Label(hw.getName()), 1, rowCounter.get());
                    homework.add(new Label(" Score: "), 2, rowCounter.get());
                    homework.add(new Label(Integer.toString(hw.getScore())), 3, rowCounter.get());
                    homework.add(new Label(" Weightage: "), 4, rowCounter.get());
                    homework.add(new Label(Integer.toString(hw.getWeightage())), 5, rowCounter.get());
                    homework.add(new Label(" Deadline: "), 6, rowCounter.get());
                    if (hw.getDeadline() != null) {
                        homework.add(new Label(hw.getDeadline().toString()), 7, rowCounter.get());
                    } else {
                        homework.add(new Label("No Deadline"), 7, rowCounter.get());
                    }
                    homework.add(new Label(" Is Done: "), 8, rowCounter.get());
                    homework.add(new Label(Boolean.toString(hw.getIsDone())), 9, rowCounter.get());
                    rowCounter.getAndIncrement();
                });
    }

    /**
     * Updates the test of the student.
     */
    public void updateTest() {
        AtomicInteger rowCounter = new AtomicInteger();
        student.getTest().stream()
                .sorted(Comparator.comparing(test -> test.getName()))
                .forEach(test -> {
                    tests.add(new Label("Name: "), 0, rowCounter.get());
                    tests.add(new Label(test.getName()), 1, rowCounter.get());
                    tests.add(new Label(" Score: "), 2, rowCounter.get());
                    tests.add(new Label(Integer.toString(test.getScore())), 3, rowCounter.get());
                    tests.add(new Label(" Weightage: "), 4, rowCounter.get());
                    tests.add(new Label(Integer.toString(test.getWeightage())), 5, rowCounter.get());
                    tests.add(new Label(" Deadline: "), 6, rowCounter.get());
                    if (test.getDeadline() != null) {
                        tests.add(new Label(test.getDeadline().toString()), 7, rowCounter.get());
                    } else {
                        tests.add(new Label("No Deadline"), 7, rowCounter.get());
                    }
                    rowCounter.getAndIncrement();
                });
    }

    /**
     * Updates the image of the student.
     * If the student has an image, it will be displayed, else it will check the image folder for the student's image.
     */
    public void updateImage() {
        String strImage = student.getImage().value;
        File file = new File(strImage);
        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            if (image.isError()) {
                Image defaultImage = new Image("images/defaultStudent.png");
                circle.setFill(new ImagePattern(defaultImage));
                return;
            }
            circle.setFill(new ImagePattern(image));
            ;

        } else {
            String path = "images/student/" + student.getName()
                    + student.getStudentClass().getClassName() + student.getIndexNumber().toString() + ".png";
            File files = new File(path);
            if (!files.exists()) {
                Image defaultImage = new Image("images/defaultStudent.png");
                circle.setFill(new ImagePattern(defaultImage));
            } else {
                Image newImage = new Image(files.toURI().toString());
                if (newImage.isError()) {
                    Image defaultImage = new Image("images/defaultStudent.png");
                    circle.setFill(new ImagePattern(defaultImage));
                    return;
                }
                circle.setFill(new ImagePattern(newImage));
            }
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

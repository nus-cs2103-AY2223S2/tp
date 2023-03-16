package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;
import seedu.address.ui.detail.ProfileContent;
import seedu.address.ui.exam.EmptyExamsContent;
import seedu.address.ui.exam.ExamsContent;
import seedu.address.ui.homework.EmptyHomeworkContent;
import seedu.address.ui.homework.HomeworkContent;
import seedu.address.ui.lesson.EmptyLessonsContent;
import seedu.address.ui.lesson.LessonsContent;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class StudentCard extends UiPart<Region> {
    private static final String FXML = "StudentListCard.fxml";
    private static final String PROFILE_ICON = "images/profile_icon.png";
    private static final String HOMEWORK_ICON = "images/homework_icon.png";
    private static final String LESSONS_ICON = "images/lessons_icon.png";
    private static final String EXAMS_ICON = "images/exams_icon.png";

    private final MainWindow mainWindow;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private Button viewProfileButton;
    @FXML
    private Button viewSchoolTasksButton;
    @FXML
    private Button viewLessonsButton;
    @FXML
    private Button viewExamsButton;

    private final Student student;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StudentCard(Student student, int displayedIndex, MainWindow mainWindow) {
        super(FXML);
        this.student = student;
        this.mainWindow = mainWindow;

        id.setText(displayedIndex + ". ");
        name.setText(student.getName().getFirstName());
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        viewProfileButton.setOnAction(event -> handleViewProfileClick());
        viewSchoolTasksButton.setOnAction(event -> handleViewHomeworkClick());
        viewLessonsButton.setOnAction(event -> handleViewLessonsClick());
        viewExamsButton.setOnAction(event -> handleViewExamsClick());
    }

    /**
     * Handles the view profile button click event.
     * Displays the student profile.
     */
    @FXML
    private void handleViewProfileClick() {
        mainWindow.setDetailedHeaderBar(String.format("Student Profile: %s",
                student.getName().getFirstName()), PROFILE_ICON);
        mainWindow.setDetailedContent(new ProfileContent(student));
    }

    /**
     * Handles the view school tasks button click event.
     * Displays the student homework list with a pie chart of the homework completion status.
     */
    private void handleViewHomeworkClick() {
        mainWindow.setDetailedHeaderBar(String.format("Homework List: %s",
                student.getName().getFirstName()), HOMEWORK_ICON);
        if (student.getHomeworkList().isEmpty()) {
            mainWindow.setDetailedContent(new EmptyHomeworkContent(student));
        } else {
            mainWindow.setDetailedContent(new HomeworkContent(student));
        }
    }

    /**
     * Handles the view student lessons button click event
     * Displays the student past lessons history and upcoming lessons.
     */
    private void handleViewLessonsClick() {
        mainWindow.setDetailedHeaderBar(String.format("Lessons List: %s",
                student.getName().getFirstName()), LESSONS_ICON);
        if (student.getLessonsList().isEmpty()) {
            mainWindow.setDetailedContent(new EmptyLessonsContent(student));
        } else {
            mainWindow.setDetailedContent(new LessonsContent(student));
        }
    }

    /**
     * Handles the view student exams button click event
     * Displays all the student exams and upcoming exams in a quick glance.
     */
    private void handleViewExamsClick() {
        mainWindow.setDetailedHeaderBar(String.format("Exams List: %s",
                student.getName().getFirstName()), EXAMS_ICON);
        if (student.getExamsList().isEmpty()) {
            mainWindow.setDetailedContent(new EmptyExamsContent(student));
        } else {
            mainWindow.setDetailedContent(new ExamsContent(student));
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

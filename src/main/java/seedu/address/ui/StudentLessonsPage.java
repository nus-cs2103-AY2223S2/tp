package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * An UI component that displays detailed information of a {@code Student}.
 */
public class StudentLessonsPage extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(StudentTasksPage.class);

    private static final String FXML = "StudentLessonsPage.fxml";
    private final Name studentName;

    @FXML
    private Label name;
    @FXML
    private Label listName;

    @FXML
    private ListView<String> lessonList;

    /**
     * Creates a {@code StudentTasksPage} with the given {@code Student}.
     */
    public StudentLessonsPage(Student student, Stage stage) {
        super(FXML, stage);
        studentName = student.getName();

        name.setText(String.format("Full Name: %s", student.getName().fullName));
        listName.setText("Student Lessons List: ");

        ObservableList<String> lessonItems = FXCollections.observableArrayList();
        List<Lesson> studentLessonsList = student.getLessonsList();
        for (int i = 0; i < studentLessonsList.size(); i++) {
            Lesson lessonItem = studentLessonsList.get(i);
            String lessonString = String.format("%d. %s", i + 1, lessonItem.toString());
            lessonItems.add(lessonString);
        }

        lessonList.setItems(lessonItems);
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine(String.format("Showing Lessons page for student %s", name.getText()));
        getRoot().show();
        getRoot().setTitle(String.format("Lessons for %s", studentName.fullName));
        getRoot().centerOnScreen();
    }
}

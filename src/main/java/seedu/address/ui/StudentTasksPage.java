package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * An UI component that displays detailed information of a {@code Student}.
 */
public class StudentTasksPage extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(StudentTasksPage.class);

    private static final String FXML = "StudentTasksPage.fxml";
    private Name studentName;

    @FXML
    private Label name;

    /**
     * Creates a {@code StudentTasksPage} with the given {@code Student}.
     */
    public StudentTasksPage(Student student, Stage stage) {
        super(FXML, stage);
        studentName = student.getName();

        name.setText(String.format("Full Name: %s", student.getName().fullName));
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
        logger.fine(String.format("Showing Tasks page for student %s", name.getText()));
        getRoot().show();
        getRoot().setTitle(String.format("Tasks for %s", studentName.fullName));
        getRoot().centerOnScreen();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof StudentTasksPage)) {
            return false;
        }

        StudentTasksPage otherStudentTasksPage = (StudentTasksPage) obj;
        return otherStudentTasksPage.getRoot().equals(getRoot());
    }
}

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
public class StudentInfoPage extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(StudentInfoPage.class);

    private static final String FXML = "StudentInfoPage.fxml";
    private Name studentName;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    /**
     * Creates a {@code StudentInfoPage} with the given {@code Student}.
     */
    public StudentInfoPage(Student student, Stage stage) {
        super(FXML, stage);
        studentName = student.getName();

        name.setText(String.format("Full Name: %s", student.getName().fullName));
        phone.setText(String.format("Phone Number: %s", student.getPhone().value));
        address.setText(String.format("Address: %s", student.getAddress().value));
        email.setText(String.format("Email: %s", student.getEmail().value));
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
        logger.fine(String.format("Showing Profile for student %s", name.getText()));
        getRoot().show();
        getRoot().setTitle(String.format("Profile of %s", studentName.fullName));
        getRoot().centerOnScreen();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof StudentInfoPage)) {
            return false;
        }

        StudentInfoPage otherStudentInfoPage = (StudentInfoPage) obj;
        return otherStudentInfoPage.getRoot().equals(getRoot());
    }
}

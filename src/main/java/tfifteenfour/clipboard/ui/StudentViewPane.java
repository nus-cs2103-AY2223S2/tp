package tfifteenfour.clipboard.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.model.student.Student;

public class StudentViewPane extends UiPart<Region> {

    public final Student student;

    private static final String FXML = "StudentViewPane.fxml";

    @FXML
    Label name;

    public StudentViewPane(Student student) {
        super(FXML);
        this.student = student;
        name.setText(student.getName().fullName);
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
        StudentViewPane pane = (StudentViewPane) other;
        return student.equals(pane.student);
    }
}

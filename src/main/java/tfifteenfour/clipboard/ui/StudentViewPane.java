package tfifteenfour.clipboard.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.model.student.Student;


import java.util.Optional;

public class StudentViewPane extends UiPart<Region> {

    private static final String FXML = "StudentViewPane.fxml";

    @FXML
    private Label title;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label studentId;
    @FXML
    private Label email;

    public StudentViewPane(Optional<Student> viewedStudent) {
        super(FXML);
        if (!viewedStudent.isEmpty()) {
            name.setText(viewedStudent.get().getName().fullName);
            phone.setText(viewedStudent.get().getPhone().value);
            studentId.setText(viewedStudent.get().getStudentId().value);
            email.setText(viewedStudent.get().getEmail().value);
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

        return true;
    }
}

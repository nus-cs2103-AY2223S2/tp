package seedu.address.ui.detail;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays detailed information of a {@code Student}.
 */
public class ProfileContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(ProfileContent.class);

    private static final String FXML = "ProfileContent.fxml";

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    /**
     * Creates a {@code ProfileContent} with the given {@code Student}.
     */
    public ProfileContent(Student student) {
        super(FXML);

        name.setText(String.format("Full Name: %s", student.getName().fullName));
        phone.setText(String.format("Phone Number: %s", student.getPhone().value));
        address.setText(String.format("Address: %s", student.getAddress().value));
        email.setText(String.format("Email: %s", student.getEmail().value));
    }
}

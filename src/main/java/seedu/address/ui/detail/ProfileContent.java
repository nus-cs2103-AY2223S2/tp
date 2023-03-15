package seedu.address.ui.detail;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;
import seedu.address.ui.UiUtil;

/**
 * A UI component that displays detailed information of a {@code Student}.
 */
public class ProfileContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(ProfileContent.class);
    private static final String FXML = "ProfileContent.fxml";
    private static final int MAX_LINE_LENGTH = 40;
    private static final String NAME_LABEL = "Full Name: %s";
    private static final String PHONE_LABEL = "Phone Number: %s";
    private static final String ADDRESS_LABEL = "Address: %s";
    private static final String EMAIL_LABEL = "Email: %s";

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
     *
     * @param student The student to display the profile content for.
     */
    public ProfileContent(Student student) {
        super(FXML);

        name.setText(UiUtil.addLineBreaksWithIndent(
                String.format(NAME_LABEL, student.getName().fullName), MAX_LINE_LENGTH));
        phone.setText(UiUtil.addLineBreaksWithIndent(
                String.format(PHONE_LABEL, student.getPhone().value), MAX_LINE_LENGTH));
        address.setText(UiUtil.addLineBreaksWithIndent(
                String.format(ADDRESS_LABEL, student.getAddress().value), MAX_LINE_LENGTH));
        email.setText(UiUtil.addLineBreaksWithIndent(
                String.format(EMAIL_LABEL, student.getEmail().value), MAX_LINE_LENGTH));
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof ProfileContent // instanceof handles nulls
                && name.equals(((ProfileContent) obj).name)); // state check
    }
}

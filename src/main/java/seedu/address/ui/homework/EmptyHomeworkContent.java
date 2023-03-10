package seedu.address.ui.homework;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

/**
 * An empty UI component that is displayed when there is no homework to be displayed.
 */
public class EmptyHomeworkContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(EmptyHomeworkContent.class);

    private static final String FXML = "EmptyContent.fxml";
    @FXML
    private Label message;

    /**
     * Creates an empty {@code HomeworkContent} with the given {@code Student}.
     *
     * @param student The student to display the empty homework content for.
     */
    public EmptyHomeworkContent(Student student) {
        super(FXML);
        message.setText("No homework to display for " + student.getName().fullName + "!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmptyHomeworkContent // instanceof handles nulls
                && message.getText().equals(((EmptyHomeworkContent) other).message.getText())); // state check
    }
}

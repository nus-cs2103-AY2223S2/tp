package seedu.address.ui.homework;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiUtil;
/**
 * An empty UI component that is displayed when there is no homework to be displayed.
 */
public class EmptyHomeworkContent extends GeneralHomeworkContent {
    private static final Logger logger = LogsCenter.getLogger(EmptyHomeworkContent.class);
    private static final String FXML = "EmptyContent.fxml";
    private static final String MESSAGE = "No homework to display for %s!";

    @FXML
    private Label message;

    /**
     * Creates an empty {@code FilledHomeworkContent} with the given {@code Student}.
     *
     * @param student The student to display the empty homework content for.
     */
    public EmptyHomeworkContent(Student student) {
        super(FXML);
        String textToDisplay = String.format(MESSAGE, student.getName().fullName);
        message.setText(UiUtil.addLineBreaksWithoutIndent(textToDisplay, MAX_LINE_LENGTH));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmptyHomeworkContent // instanceof handles nulls
                && message.getText().equals(((EmptyHomeworkContent) other).message.getText())); // state check
    }
}

package seedu.address.ui.lesson;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiUtil;

/**
 * An empty UI component that is displayed when there is no lessons to be displayed.
 */
public class EmptyLessonsContent extends GeneralLessonsContent {
    private static final Logger logger = LogsCenter.getLogger(EmptyLessonsContent.class);
    private static final String FXML = "EmptyContent.fxml";
    private static final String MESSAGE = "No lessons to display for %s!";

    @FXML
    private Label message;

    /**
     * Creates an empty {@code FilledLessonsContent} with the given {@code Student}.
     *
     * @param student The student to display the empty exams content for.
     */
    public EmptyLessonsContent(Student student) {
        super(FXML);
        String textToDisplay = String.format(MESSAGE, student.getName().fullName);
        message.setText(UiUtil.addLineBreaksWithoutIndent(textToDisplay, MAX_LINE_LENGTH));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof EmptyLessonsContent)) {
            return false;
        }

        EmptyLessonsContent other = (EmptyLessonsContent) obj;
        return message.getText().equals(other.message.getText());
    }
}

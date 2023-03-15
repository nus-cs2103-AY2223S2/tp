package seedu.address.ui.exam;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;
import seedu.address.ui.UiUtil;
import seedu.address.ui.lesson.EmptyLessonsContent;

/**
 * An empty UI component that is displayed when there is no exams to be displayed.
 */
public class EmptyExamsContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(EmptyLessonsContent.class);
    private static final String FXML = "EmptyContent.fxml";
    private static final int MAX_LINE_LENGTH = 40;
    private static final String MESSAGE = "No exams to display for %s!";

    @FXML
    private Label message;

    /**
     * Creates an empty {@code LessonsContent} with the given {@code Student}.
     *
     * @param student The student to display the empty exams content for.
     */
    public EmptyExamsContent(Student student) {
        super(FXML);
        String textToDisplay = String.format(MESSAGE, student.getName().fullName);
        message.setText(UiUtil.addLineBreaksWithoutIndent(textToDisplay, MAX_LINE_LENGTH));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof EmptyExamsContent)) {
            return false;
        }

        EmptyExamsContent other = (EmptyExamsContent) obj;
        return message.getText().equals(other.message.getText());
    }
}

package seedu.address.ui.exam;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;
import seedu.address.ui.lesson.EmptyLessonsContent;

/**
 * An empty UI component that is displayed when there is no exams to be displayed.
 */
public class EmptyExamsContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(EmptyLessonsContent.class);
    private static final String FXML = "EmptyContent.fxml";

    @FXML
    private Label message;

    /**
     * Creates an empty {@code LessonsContent} with the given {@code Student}.
     *
     * @param student The student to display the empty exams content for.
     */
    public EmptyExamsContent(Student student) {
        super(FXML);
        message.setText("No exams to display for " + student.getName().fullName + "!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmptyExamsContent // instanceof handles nulls
                && message.getText().equals(((EmptyExamsContent) other).message.getText())); // state check
    }
}

package seedu.address.ui.lesson;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

/**
 * An empty UI component that is displayed when there is no lessons to be displayed.
 */
public class EmptyLessonsContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(EmptyLessonsContent.class);
    private static final String FXML = "EmptyContent.fxml";

    @FXML
    private Label message;

    /**
     * Creates an empty {@code LessonsContent} with the given {@code Student}.
     *
     * @param student The student to display the empty exams content for.
     */
    public EmptyLessonsContent(Student student) {
        super(FXML);
        message.setText("No lessons to display for " + student.getName().fullName + "!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmptyLessonsContent // instanceof handles nulls
                && message.getText().equals(((EmptyLessonsContent) other).message.getText())); // state check
    }
}

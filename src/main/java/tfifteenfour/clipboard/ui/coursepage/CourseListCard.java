package tfifteenfour.clipboard.ui.coursepage;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * An UI component that displays information of a {@code Course}.
 */
public class CourseListCard extends UiPart<Region> {

    private static final String FXML = "ListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Course course;

    @FXML
    private Label id;
    @FXML
    private Label code;

    /**
     * Creates a {@code CourseListCard} with the given {@code Course} and index to display.
     */
    public CourseListCard(Course course, int displayedIndex) {
        super(FXML);
        this.course = course;
        id.setText(displayedIndex + ". ");
        code.setText(course.getCourseCode());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseListCard)) {
            return false;
        }

        // state check
        CourseListCard card = (CourseListCard) other;
        return id.getText().equals(card.id.getText())
                && course.equals(card.course);
    }
}


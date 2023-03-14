package seedu.address.ui.lesson;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;
import seedu.address.ui.homework.HomeworkContent;

/**
 * An UI component that displays detailed information of a {@code Student}.
 */
public class LessonsContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(HomeworkContent.class);

    private static final String FXML = "LessonsContent.fxml";
    private final Name studentName;

    @FXML
    private Label name;
    @FXML
    private Label pastLessonsListName;

    @FXML
    private StackPane pastLessonsListPlaceholder;

    @FXML
    private Label upcomingLessonsListName;

    @FXML
    private StackPane upcomingLessonsListPlaceholder;

    /**
     * Creates a {@code HomeworkContent} with the given {@code Student}.
     */
    public LessonsContent(Student student) {
        super(FXML);
        studentName = student.getName();

        name.setText(String.format("Full Name: %s", student.getName().fullName));
        pastLessonsListName.setText("Past Lessons: ");

        // Set the past lessons list panel to display the past lessons of the student
        ObservableList<Lesson> pastLessonsList = student.getPastLessonsList();
        LessonsListPanel pastLessonsListPanel = new LessonsListPanel(pastLessonsList);
        pastLessonsListPlaceholder.getChildren().add(pastLessonsListPanel.getRoot());

        upcomingLessonsListName.setText("Upcoming Lessons: ");

        // Set the upcoming lessons list panel to display the upcoming lessons of the student
        ObservableList<Lesson> upcomingLessonsList = student.getUpcomingLessonsList();
        LessonsListPanel upcomingLessonsListPanel = new LessonsListPanel(upcomingLessonsList);
        upcomingLessonsListPlaceholder.getChildren().add(upcomingLessonsListPanel.getRoot());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof LessonsContent // instanceof handles nulls
                && studentName.equals(((LessonsContent) obj).studentName)); // state check
    }
}

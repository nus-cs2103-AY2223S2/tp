package seedu.address.ui.lesson;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.ui.homework.FilledHomeworkContent;
import seedu.address.ui.homework.GeneralHomeworkContent;

/**
 * A UI component that displays detailed information of a {@code Student}.
 */
public class FilledLessonsContent extends GeneralHomeworkContent {
    private static final Logger logger = LogsCenter.getLogger(FilledHomeworkContent.class);
    private static final String FXML = "LessonsContent.fxml";
    private static final String NAME_LABEL = "First Name: %s";
    private static final String PAST_LESSONS_LIST_NAME = "Past Lessons: ";
    private static final String UPCOMING_LESSONS_LIST_NAME = "Upcoming Lessons: ";

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
     * Creates a {@code FilledHomeworkContent} with the given {@code Student}.
     */
    public FilledLessonsContent(Student student) {
        super(FXML);
        studentName = student.getName();

        name.setText(String.format(NAME_LABEL, student.getName().getFirstName()));
        pastLessonsListName.setText(PAST_LESSONS_LIST_NAME);

        // Set the past lessons list panel to display the past lessons of the student
        ObservableList<Lesson> pastLessonsList = student.getPastLessonsList();
        LessonsListPanel pastLessonsListPanel = new LessonsListPanel(pastLessonsList);
        pastLessonsListPlaceholder.getChildren().add(pastLessonsListPanel.getRoot());

        upcomingLessonsListName.setText(UPCOMING_LESSONS_LIST_NAME);

        // Set the upcoming lessons list panel to display the upcoming lessons of the student
        ObservableList<Lesson> upcomingLessonsList = student.getUpcomingLessonsList();
        LessonsListPanel upcomingLessonsListPanel = new LessonsListPanel(upcomingLessonsList);
        upcomingLessonsListPlaceholder.getChildren().add(upcomingLessonsListPanel.getRoot());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof FilledLessonsContent // instanceof handles nulls
                && studentName.equals(((FilledLessonsContent) obj).studentName)); // state check
    }
}

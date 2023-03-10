package seedu.address.ui.detail;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

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
    private Label listName;

    @FXML
    private ListView<String> lessonList;

    /**
     * Creates a {@code HomeworkContent} with the given {@code Student}.
     */
    public LessonsContent(Student student) {
        super(FXML);
        studentName = student.getName();

        name.setText(String.format("Full Name: %s", student.getName().fullName));
        listName.setText("Student Lessons List: ");

        ObservableList<String> lessonItems = FXCollections.observableArrayList();
        List<Lesson> studentLessonsList = student.getLessonsList();
        for (int i = 0; i < studentLessonsList.size(); i++) {
            Lesson lessonItem = studentLessonsList.get(i);
            String lessonString = String.format("%d. %s", i + 1, lessonItem.toString());
            lessonItems.add(lessonString);
        }

        lessonList.setItems(lessonItems);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof LessonsContent // instanceof handles nulls
                && studentName.equals(((LessonsContent) obj).studentName)); // state check
    }
}

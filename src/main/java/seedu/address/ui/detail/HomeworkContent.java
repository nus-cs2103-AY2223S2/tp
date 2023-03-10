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
import seedu.address.model.student.Homework;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays detailed information of a {@code Student}.
 */
public class HomeworkContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(HomeworkContent.class);

    private static final String FXML = "HomeworkContent.fxml";

    @FXML
    private Label name;
    @FXML
    private Label listName;
    @FXML
    private ListView<String> homeworkList;

    /**
     * Creates a {@code HomeworkContent} with the given {@code Student}.
     */
    public HomeworkContent(Student student) {
        super(FXML);

        name.setText(String.format("Full Name: %s", student.getName().fullName));
        listName.setText("Student Homework List: ");

        // Create an observable list of homework items
        // Each homework item is a string representation of the homework
        ObservableList<String> homeworkItems = FXCollections.observableArrayList();
        List<Homework> studentHomeworkList = student.getHomeworkList();
        for (int i = 0; i < studentHomeworkList.size(); i++) {
            Homework homework = studentHomeworkList.get(i);
            String homeworkString = String.format("%d. %s", i + 1, homework.toString());
            homeworkItems.add(homeworkString);
        }

        homeworkList.setItems(homeworkItems);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof HomeworkContent // instanceof handles nulls
                && name.equals(((HomeworkContent) obj).name)); // state check
    }
}

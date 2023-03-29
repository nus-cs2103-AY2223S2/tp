package seedu.address.ui.exam;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Exam;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.ui.homework.FilledHomeworkContent;

/**
 * Panel containing the list of Exams.
 */
public class FilledExamsContent extends GeneralExamsContent {
    private static final Logger logger = LogsCenter.getLogger(FilledHomeworkContent.class);

    private static final String FXML = "ExamsContent.fxml";
    private final Name studentName;

    @FXML
    private Label name;
    @FXML
    private Label examsListName;

    @FXML
    private StackPane examsListPlaceholder;

    @FXML
    private Label upcomingExamsListName;

    @FXML
    private StackPane upcomingExamsListPlaceholder;

    /**
     * Creates a {@code FilledHomeworkContent} with the given {@code Student}.
     */
    public FilledExamsContent(Student student) {
        super(FXML);
        studentName = student.getName();

        name.setText(String.format("First Name: %s", student.getName().getFirstName()));
        examsListName.setText("All Exams: ");

        // Set the all exams list panel to display the all the exams of the student
        ObservableList<Exam> examsList = student.getExamsList();
        ExamsListPanel examsListPanel = new ExamsListPanel(examsList);
        examsListPlaceholder.getChildren().add(examsListPanel.getRoot());

        upcomingExamsListName.setText("Most recent upcoming Exams at a glance: ");

        // Set the upcoming exams list panel to display the upcoming exams of the student
        ObservableList<Exam> upcomingExamsList = student.getUpcomingExamsList();
        ExamsListPanel upcomingExamsListPanel = new ExamsListPanel(upcomingExamsList);
        upcomingExamsListPlaceholder.getChildren().add(upcomingExamsListPanel.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilledExamsContent)) {
            return false;
        }

        // state check
        FilledExamsContent card = (FilledExamsContent) other;
        return studentName.equals(card.studentName);
    }
}

package seedu.address.ui.homework;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Student;

/**
 * A UI component that displays detailed information of a {@code Student}.
 */
public class FilledHomeworkContent extends GeneralHomeworkContent {
    private static final Logger logger = LogsCenter.getLogger(FilledHomeworkContent.class);
    private static final String FXML = "HomeworkContent.fxml";
    private static final String NAME_LABEL = "First Name: %s";
    private static final String LIST_NAME_LABEL = "Homework List: ";
    private static final String PIE_CHART_TITLE = "Completed/Pending Homework";

    @FXML
    private Label name;
    @FXML
    private Label listName;
    @FXML
    private PieChart homeworkPieChart;
    @FXML
    private StackPane homeworkListPlaceholder;

    /**
     * Creates a {@code FilledHomeworkContent} with the given {@code Student}.
     */
    public FilledHomeworkContent(Student student) {
        super(FXML);

        name.setText(String.format(NAME_LABEL, student.getName().getFirstName()));
        listName.setText(String.format(LIST_NAME_LABEL));

        // Set the homework pie chart to display the homework data of the student
        homeworkPieChart.setData(student.getHomeworkPieChartData());
        homeworkPieChart.setTitle(PIE_CHART_TITLE);

        // Set the homework list panel to display the homework list of the student
        ObservableList<Homework> homeworkList = student.getHomeworkList();
        HomeworkListPanel homeworkListPanel = new HomeworkListPanel(homeworkList);
        homeworkListPlaceholder.getChildren().add(homeworkListPanel.getRoot());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof FilledHomeworkContent // instanceof handles nulls
                && name.equals(((FilledHomeworkContent) obj).name)); // state check
    }
}

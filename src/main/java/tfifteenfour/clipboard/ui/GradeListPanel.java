package tfifteenfour.clipboard.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.StudentWithGrades;

/**
 * Panel containing the list of student attendance.
 */
public class GradeListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<StudentWithGrades> listView;

    /**
     * Creates a {@code AttendanceListPanel} with the given {@code ObservableList}.
     */
    public GradeListPanel(ObservableList<StudentWithGrades> studentList) {
        super(FXML);
        listView.setItems(studentList);
        listView.setCellFactory(listView -> new GradeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code AttendanceListCard}.
     */
    class GradeListViewCell extends ListCell<StudentWithGrades> {
        @Override
        protected void updateItem(StudentWithGrades student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GradeListCard(student, getIndex() + 1).getRoot());
            }
        }
    }

    public void setGradeListView(ObservableList<StudentWithGrades> studentList) {
        listView.setItems(studentList);
    }

}

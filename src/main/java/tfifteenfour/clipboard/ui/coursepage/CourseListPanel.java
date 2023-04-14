package tfifteenfour.clipboard.ui.coursepage;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * Panel containing the list of courses.
 */
public class CourseListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<Course> listView;

    /**
     * Creates a {@code CourseListPanel} with the given {@code ObservableList}.
     */
    public CourseListPanel(ObservableList<Course> courseList) {
        super(FXML);
        listView.setItems(courseList);
        listView.setCellFactory(listView -> new CourseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Course} using a {@code CourseListCard}.
     */
    class CourseListViewCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);

            if (empty || course == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CourseListCard(course, getIndex() + 1).getRoot());
            }
        }
    }

    public void setCourseListView(ObservableList<Course> courseList) {
        listView.setItems(courseList);
    }

}


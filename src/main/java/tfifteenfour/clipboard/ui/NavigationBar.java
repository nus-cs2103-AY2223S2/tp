package tfifteenfour.clipboard.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.Logic;
import tfifteenfour.clipboard.logic.PageType;

/**
 * A UI for navigation bar.
 */
public class NavigationBar extends UiPart<Region> {

    private static final String FXML = "NavigationBar.fxml";

    @FXML
    private Label status;

    /**
     * Constructs a navigation bar based on current page selection.
     */
    public NavigationBar(Logic logic) {
        super(FXML);
        CurrentSelection currentSelection = logic.getModel().getCurrentSelection();

        if (currentSelection.getCurrentPage() == PageType.GROUP_PAGE) {
            status.setText(currentSelection.getSelectedCourse().getCourseCode());

        } else if (currentSelection.getCurrentPage() == PageType.STUDENT_PAGE
                || currentSelection.getCurrentPage() == PageType.SESSION_PAGE
                || currentSelection.getCurrentPage() == PageType.TASK_PAGE) {
            String courseCode = currentSelection.getSelectedCourse().getCourseCode();
            String groupName = currentSelection.getSelectedGroup().getGroupName();

            status.setText(courseCode + " > " + groupName);
        } else if (currentSelection.getCurrentPage() == PageType.SESSION_STUDENT_PAGE) {
            String courseCode = currentSelection.getSelectedCourse().getCourseCode();
            String groupName = currentSelection.getSelectedGroup().getGroupName();
            String sessionName = currentSelection.getSelectedSession().getSessionName();

            status.setText(courseCode + " > " + groupName + " > " + sessionName + " > Attendance");
        } else if (currentSelection.getCurrentPage() == PageType.TASK_STUDENT_PAGE) {
            String courseCode = currentSelection.getSelectedCourse().getCourseCode();
            String groupName = currentSelection.getSelectedGroup().getGroupName();
            String taskName = currentSelection.getSelectedTask().getTaskName();

            status.setText(courseCode + " > " + groupName + " > " + taskName + " > Grades");
        }
    }
}

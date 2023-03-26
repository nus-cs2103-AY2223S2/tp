package tfifteenfour.clipboard.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.Logic;
import tfifteenfour.clipboard.logic.PageType;

public class NavigationBar extends UiPart<Region> {

    private static final String FXML = "NavigationBar.fxml";

    @FXML
    Label status;

    public NavigationBar(Logic logic) {
        super(FXML);
        CurrentSelection currentSelection = logic.getCurrentSelection();

        if (currentSelection.getCurrentPage() == PageType.GROUP_PAGE) {
            status.setText(currentSelection.getSelectedCourse().getCourseCode());
        }

        else if (currentSelection.getCurrentPage() == PageType.STUDENT_PAGE) {
            String courseCode = currentSelection.getSelectedCourse().getCourseCode();
            String groupName = currentSelection.getSelectedGroup().getGroupName();

            status.setText(courseCode + " > " + groupName);
        }
    }
}

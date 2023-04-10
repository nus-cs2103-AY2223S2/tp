package codoc.ui;

import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import codoc.model.course.CourseList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Class to represent the left panel that displays a list of courses.
 */
public class CourseListPanel extends UiPart<Region> {

    /**
     * Panel containing the list of courses that CoDoc currently supports.
     */
    private static final String FXML = "CourseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger("CourseList panel");

    @FXML
    private Label header;

    @FXML
    private Label courses;


    /**
     * Creates a {@code InfoTab} with the given {@code protagonist} and {@code tab}.
     */
    public CourseListPanel() {
        super(FXML);
        CourseList courseList = new CourseList();
        logger.info("Setting up Course List Panel...");
        header.setText("List of courses that we support.");
        header.setWrapText(true);
        courses.setText(courseList.toString());
        courses.setWrapText(true);

    }

}

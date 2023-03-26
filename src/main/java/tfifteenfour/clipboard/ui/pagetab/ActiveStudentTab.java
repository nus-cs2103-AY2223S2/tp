package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the student tab.
 */
public class ActiveStudentTab extends UiPart<Region> {

    private static final String FXML = "StudentTab.fxml";

    @FXML
    private Label name;

    public ActiveStudentTab() {
        super(FXML);
    }

}

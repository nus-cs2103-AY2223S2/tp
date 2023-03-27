package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the student tab.
 */
public class InactiveStudentTab extends UiPart<Region> {

    private static final String FXML = "InactiveTab.fxml";

    @FXML
    private Label name;

    /**
     * Creates an InactiveStudentTab
     */
    public InactiveStudentTab() {
        super(FXML);
        name.setText("Students");
    }

}

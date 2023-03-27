package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the task tab.
 */
public class InactiveTaskTab extends UiPart<Region> {

    private static final String FXML = "InactiveTab.fxml";

    @FXML
    private Label name;

    /**
     * Creates an InactiveTaskTab
     */
    public InactiveTaskTab() {
        super(FXML);
        name.setText("Tasks");
    }

}

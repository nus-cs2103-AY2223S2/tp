package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the group tab.
 */
public class InactiveGroupTab extends UiPart<Region> {

    private static final String FXML = "InactiveTab.fxml";

    @FXML
    private Label name;

    /**
     * Creates an InactiveGroupTab
     */
    public InactiveGroupTab() {
        super(FXML);
        name.setText("Groups");
    }

}

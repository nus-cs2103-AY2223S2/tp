package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the session tab.
 */
public class InactiveSessionTab extends UiPart<Region> {

    private static final String FXML = "InactiveTab.fxml";

    @FXML
    private Label name;

    /**
     * Creates an InactiveSessionTab
     */
    public InactiveSessionTab() {
        super(FXML);
        name.setText("Sessions");
    }

}

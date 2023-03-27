package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the task tab.
 */
public class ActiveTaskTab extends UiPart<Region> {

    private static final String FXML = "ActiveSubTab.fxml";

    @FXML
    private Label name;

    /**
     * Creates an ActiveTaskTab
     */
    public ActiveTaskTab() {
        super(FXML);
        name.setText("Tasks");
    }

}

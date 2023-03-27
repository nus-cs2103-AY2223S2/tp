package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the session tab.
 */
public class ActiveSessionTab extends UiPart<Region> {

    private static final String FXML = "ActiveSubTab.fxml";

    @FXML
    private Label name;

    public ActiveSessionTab() {
        super(FXML);
        name.setText("Sessions");
    }

}
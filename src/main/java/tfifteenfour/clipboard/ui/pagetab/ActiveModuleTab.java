package tfifteenfour.clipboard.ui.pagetab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI for the module tab.
 */
public class ActiveModuleTab extends UiPart<Region> {

    private static final String FXML = "ActiveTab.fxml";

    @FXML
    private Label name;

    public ActiveModuleTab() {
        super(FXML);
        name.setText("Modules");
    }

}

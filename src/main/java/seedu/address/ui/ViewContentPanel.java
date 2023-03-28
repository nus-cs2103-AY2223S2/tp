package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the detailed content of selected internship application.
 */
public class ViewContentPanel extends UiPart<Region> {

    private static final String FXML = "ViewContentPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewContentPanel.class);

    @FXML
    private VBox container;

    /**
     * Creates a {@code ViewContentPanel}.
     */
    public ViewContentPanel() {
        super(FXML);
    }

    public VBox getContainer() {
        return container;
    }

}

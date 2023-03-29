package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the summary of the displaying list.
 */
public class SummaryPanel extends UiPart<Region> {

    private static final String FXML = "SummaryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SummaryPanel.class);

    @FXML
    private VBox container;

    /**
     * Creates a {@code SummaryPanel}.
     */
    public SummaryPanel() {
        super(FXML);
    }

    public VBox getContainer() {
        return container;
    }
}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the detailed entity view elements.
 */
public class ViewModePanel extends UiPart<Region> {

    private static final String FXML = "ViewModePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewModePanel.class);

    @FXML
    private StackPane entityListPanelPlaceholder;

    @FXML
    private StackPane entityDetailsPlaceholder;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ViewModePanel(EntityListPanel entityListPanel, EntityDetailsPanel entityDetailsPanel) {
        super(FXML);
        entityListPanelPlaceholder.getChildren().add(entityListPanel.getRoot());
        entityDetailsPlaceholder.getChildren().add(entityDetailsPanel.getRoot());
    }

}

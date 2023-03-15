package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the detailed entity view elements.
 */
public class ListModePanel extends UiPart<Region> {

    private static final String FXML = "ListModePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListModePanel.class);

    @FXML
    private StackPane entityListPanelPlaceholder;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ListModePanel(EntityListPanel entityListPanel) {
        super(FXML);
        entityListPanelPlaceholder.getChildren().add(entityListPanel.getRoot());
    }

}

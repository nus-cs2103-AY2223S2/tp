package seedu.address.ui;

import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

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
    public ViewModePanel(EntityListPanel entityListPanel) {
        super(FXML);
        entityListPanelPlaceholder.getChildren().add(entityListPanel.getRoot());
    }

}

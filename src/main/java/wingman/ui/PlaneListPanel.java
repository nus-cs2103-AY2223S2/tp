package wingman.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import wingman.commons.core.LogsCenter;
import wingman.model.plane.Plane;

/**
 * A generic panel for displaying a list of planes.
 */
public class PlaneListPanel extends UiPart<Region> {

    private static final String FXML = "PlaneListPanel.fxml";

    private final Logger logger;

    @FXML
    private ListView<Plane> planeList;

    public PlaneListPanel(ObservableList<Plane> planeList) {
        this(LogsCenter.getLogger(PlaneListPanel.class), planeList);
    }

    /**
     * Creates a {@code PlaneListPanel} with the given {@code ObservableList}.
     *
     * @param logger     The logger to be used.
     * @param planeList The list of planes to be displayed.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public PlaneListPanel(
            Logger logger,
            ObservableList<Plane> planeList
    ) {
        super(FXML);
        this.logger = logger;
        this.planeList.setItems(planeList);
        this.planeList.setCellFactory(listView -> new PlaneListCell());
    }
}

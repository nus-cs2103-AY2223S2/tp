package wingman.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import wingman.commons.core.LogsCenter;
import wingman.model.location.Location;

/**
 * A generic panel for displaying a list of Locations.
 */
public class LocationListPanel extends UiPart<Region> {

    private static final String FXML = "LocationListPanel.fxml";

    private final Logger logger;

    @FXML
    private ListView<Location> locationList;

    public LocationListPanel(ObservableList<Location> locationList) {
        this(LogsCenter.getLogger(LocationListPanel.class), locationList);
    }

    /**
     * Creates a {@code LocationListPanel} with the given {@code ObservableList}.
     *
     * @param logger     The logger to be used.
     * @param locationList The list of locations to be displayed.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public LocationListPanel(
            Logger logger,
            ObservableList<Location> locationList
    ) {
        super(FXML);
        this.logger = logger;
        this.locationList.setItems(locationList);
        this.locationList.setCellFactory(listView -> new LocationListCell());
    }
}

package wingman.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import wingman.commons.core.LogsCenter;
import wingman.model.flight.Flight;

/**
 * A generic panel for displaying a list of flights.
 */
public class FlightListPanel extends UiPart<Region> {

    private static final String FXML = "FlightListPanel.fxml";

    private final Logger logger;

    @FXML
    private ListView<Flight> flightList;

    public FlightListPanel(ObservableList<Flight> flightList) {
        this(LogsCenter.getLogger(FlightListPanel.class), flightList);
    }

    /**
     * Creates a {@code FlightListPanel} with the given {@code ObservableList}.
     *
     * @param logger     The logger to be used.
     * @param flightList The list of flights to be displayed.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FlightListPanel(
            Logger logger,
            ObservableList<Flight> flightList
    ) {
        super(FXML);
        this.logger = logger;
        this.flightList.setItems(flightList);
        this.flightList.setCellFactory(listView -> new FlightListCell());
    }
}

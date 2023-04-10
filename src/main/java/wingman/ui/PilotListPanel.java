package wingman.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import wingman.commons.core.LogsCenter;
import wingman.model.pilot.Pilot;

/**
 * A generic panel for displaying a list of pilots.
 */
public class PilotListPanel extends UiPart<Region> {

    private static final String FXML = "PilotListPanel.fxml";

    private final Logger logger;

    @FXML
    private ListView<Pilot> pilotList;

    public PilotListPanel(ObservableList<Pilot> pilotList) {
        this(LogsCenter.getLogger(PilotListPanel.class), pilotList);
    }

    /**
     * Creates a {@code PilotListPanel} with the given {@code ObservableList}.
     *
     * @param logger     The logger to be used.
     * @param pilotList The list of pilots to be displayed.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public PilotListPanel(
            Logger logger,
            ObservableList<Pilot> pilotList
    ) {
        super(FXML);
        this.logger = logger;
        this.pilotList.setItems(pilotList);
        this.pilotList.setCellFactory(listView -> new PilotListCell());
    }
}

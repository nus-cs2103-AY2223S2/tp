package wingman.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import wingman.commons.core.LogsCenter;
import wingman.model.crew.Crew;

/**
 * A generic panel for displaying a list of crew.
 */
public class CrewListPanel extends UiPart<Region> {

    private static final String FXML = "CrewListPanel.fxml";

    private final Logger logger;

    @FXML
    private ListView<Crew> crewList;

    public CrewListPanel(ObservableList<Crew> crewList) {
        this(LogsCenter.getLogger(CrewListPanel.class), crewList);
    }

    /**
     * Creates a {@code CrewListPanel} with the given {@code ObservableList}.
     *
     * @param logger     The logger to be used.
     * @param crewList The list of Crews to be displayed.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public CrewListPanel(
            Logger logger,
            ObservableList<Crew> crewList
    ) {
        super(FXML);
        this.logger = logger;
        this.crewList.setItems(crewList);
        this.crewList.setCellFactory(listView -> new CrewListCell());
    }
}

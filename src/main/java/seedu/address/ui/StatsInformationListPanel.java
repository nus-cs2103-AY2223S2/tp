package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.statstics.StatsInformation;
import seedu.address.model.statstics.StatsManager;

/**
 * Panel containing the list of statistics information.
 */
public class StatsInformationListPanel extends UiPart<Region> {
    private static final String FXML = "StatsInformationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicationListPanel.class);

    private final StatsManager statsManager;

    @FXML
    private ListView<StatsInformation> statsInformationListView;

    @FXML
    private HBox container;

    /**
     * Creates a {@code ViewContentPanel} with the given {@code StatsManager}.
     */
    public StatsInformationListPanel(StatsManager statsManager) {
        super(FXML);
        this.statsManager = statsManager;
        statsInformationListView.setItems(statsManager.getFilteredStatsInformations());
        statsInformationListView.setCellFactory(listView -> new StatsInformationListViewCell());
    }

    public HBox getContainer() {
        return container;
    }

    public void updateDisplay() {
        statsManager.updateFilteredStatsInformationList();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code StatsInformation}
     * using a {@code StatsInformationCard}.
     */
    class StatsInformationListViewCell extends ListCell<StatsInformation> {
        @Override
        protected void updateItem(StatsInformation statsInformation, boolean empty) {
            super.updateItem(statsInformation, empty);

            if (empty || statsInformation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StatsInformationCard(statsInformation).getRoot());
            }
        }
    }

}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.StatsInformation;
import seedu.address.model.StatsManager;

/**
 * Panel containing the list of statistics information.
 */
public class StatsInformationListPanel extends UiPart<Region> {
    private static final String FXML = "StatsInformationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicationListPanel.class);

    @FXML
    private ListView<StatsInformation> statsInformationListView;

    @FXML
    private HBox container;

    /**
     * Creates a {@code ViewContentPanel} with the given {@code StatsManager}.
     */
    public StatsInformationListPanel(StatsManager statsManager) {
        super(FXML);
        statsInformationListView.setItems(statsManager.getStatsInformations());
        statsInformationListView.setCellFactory(listView -> new StatsInformationListViewCell());
    }

    public HBox getContainer() {
        return container;
    }

    public void updateDisplay() {
        ObservableList<StatsInformation> l = statsInformationListView.getItems();
        for (StatsInformation s: l) {
            s.updateStatsInformation();
        }
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

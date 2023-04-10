package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
     * Creates a {@code StatdsInformationListPanel} with the given {@code StatsManager}.
     */
    public StatsInformationListPanel(StatsManager statsManager) {
        super(FXML);
        this.statsManager = statsManager;
        statsInformationListView.setItems(statsManager.getFilteredStatsInformations());
        statsInformationListView.setCellFactory(listView -> new StatsInformationListViewCell());


        statsInformationListView.setMaxWidth(Region.USE_COMPUTED_SIZE);
    }

    public HBox getContainer() {
        return container;
    }

    /**
     * Updates the UI display of the list of statistics information.
     */
    public void updateDisplay() {
        ObservableList<StatsInformation> l = statsManager.getStatsInformations();
        ObservableList<StatsInformation> tempList = FXCollections.observableArrayList();
        int len = l.size();
        for (int i = 0; i < len; i++) {
            StatsInformation s = l.get(0);
            l.remove(0);
            tempList.add(s);
        }
        for (int i = 0; i < len; i++) {
            l.add(tempList.get(i));
        }
        statsInformationListView.setItems(statsManager.getFilteredStatsInformations());
        statsInformationListView.setCellFactory(listView -> new StatsInformationListViewCell());
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

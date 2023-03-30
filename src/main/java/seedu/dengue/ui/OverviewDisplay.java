package seedu.dengue.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.logic.analyst.DataBin;

/**
 * A ui for the current overview that is displayed on the right of the application.
 */
public class OverviewDisplay extends UiPart<Region> {
    private static final String FXML = "OverviewDisplay.fxml";

    private final Logger logger = LogsCenter.getLogger(OverviewDisplay.class);

    @FXML
    private ListView<DataBin> overviewContentView;

    /**
     * Constructs a new blank {@code OverviewDisplay} instance.
     */
    public OverviewDisplay(ObservableList<DataBin> dataBinList) {
        super(FXML);
        overviewContentView.setItems(dataBinList);
        overviewContentView.setCellFactory(listView -> new overviewBinCell());
    }

    public void updateOverviewDisplay(ObservableList<DataBin> dataBinList) {
        overviewContentView.setItems(dataBinList);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class overviewBinCell extends ListCell<DataBin> {
        @Override
        protected void updateItem(DataBin dataBin, boolean empty) {
            super.updateItem(dataBin, empty);

            if (empty || dataBin == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DataBinCard(dataBin, getIndex() + 1).getRoot());
            }
        }
    }
}

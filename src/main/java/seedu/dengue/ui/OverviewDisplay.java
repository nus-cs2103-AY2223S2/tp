package seedu.dengue.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.model.overview.Overview;

/**
 * A ui for the current overview that is displayed on the right of the application.
 */
public class OverviewDisplay extends UiPart<Region> {
    private static final String FXML = "OverviewDisplay.fxml";

    private final Logger logger = LogsCenter.getLogger(OverviewDisplay.class);

    @FXML
    private Label overviewTitle;
    @FXML
    private Label overviewSubtitle;
    @FXML
    private ListView<DataBin> overviewContentView;

    /**
     * Constructs a new blank {@code OverviewDisplay} instance.
     */
    public OverviewDisplay(Overview overview) {
        super(FXML);
        overviewTitle.setText(overview.getOverviewTitle());
        overviewSubtitle.setText(overview.getOverviewSubtitle());

        overviewContentView.setItems(overview.getOverviewContent());
        overviewContentView.setCellFactory(listView -> new OverviewBinCell());
    }

    private void setOverviewTitle(String title) {
        requireNonNull(title);
        overviewTitle.setText(title);
    }

    private void setOverviewSubtitle(String subtitle) {
        requireNonNull(subtitle);
        overviewSubtitle.setText(subtitle);
    }

    private void setOverviewContent(ObservableList<DataBin> dataBins) {
        requireNonNull(dataBins);
        overviewContentView.setItems(dataBins);
    }

    /**
     * Sets the title, subtitle and content of the {@code OverviewDisplay}
     * to match the given {@code overview} input.
     *
     * @param overview The overview instance to update the GUI with.
     */
    public void updateOverviewDisplay(Overview overview) {
        requireNonNull(overview);
        setOverviewTitle(overview.getOverviewTitle());
        setOverviewSubtitle(overview.getOverviewSubtitle());
        setOverviewContent(overview.getOverviewContent());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class OverviewBinCell extends ListCell<DataBin> {
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

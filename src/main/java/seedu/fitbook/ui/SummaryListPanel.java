package seedu.fitbook.ui;

import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;

/**
 * Panel containing the client's summary.
 */
public class SummaryListPanel extends UiPart<Region> {
    private static final String FXML = "SummaryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);

    @javafx.fxml.FXML
    private ListView<Client> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public SummaryListPanel(ObservableList<Client> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new SummaryListPanel.SummaryListCell());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            clientListView.refresh();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class SummaryListCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SummaryListCard(client, getIndex() + 1).getRoot());
            }
        }
    }
}

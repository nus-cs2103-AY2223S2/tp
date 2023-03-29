package seedu.fitbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;

public class GraphPanel extends UiPart<Region> {
    private static final String FXML = "GraphPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GraphPanel.class);

    @javafx.fxml.FXML
    private ListView<Client> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public GraphPanel(ObservableList<Client> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new GraphPanel.GraphListCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class GraphListCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GraphCard(client, getIndex() + 1).getRoot());
            }
        }
    }
}

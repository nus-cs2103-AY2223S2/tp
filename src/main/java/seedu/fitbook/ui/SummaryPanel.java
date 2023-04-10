package seedu.fitbook.ui;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code SummaryCard}.
 */
public class SummaryPanel extends UiPart<Region> {
    private static final String FXML = "SummaryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SummaryPanel.class);

    @javafx.fxml.FXML
    private ListView<Client> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public SummaryPanel(ObservableList<Client> clientList, Client clientToView) {
        super(FXML);
        clientListView.setItems(clientList.filtered(new Predicate<Client>() {
            @Override
            public boolean test(Client client) {
                return client.isSameClient(clientToView);
            }
        }));
        clientListView.setCellFactory(listView -> new SummaryPanel.SummaryViewCell());

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class SummaryViewCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SummaryCard(client, getIndex() + 1).getRoot());
            }
        }
    }
}

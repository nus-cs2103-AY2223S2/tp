package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of persons.
 */
public class TransactionListPanel extends UiPart<Region> {
    private static final String FXML = "TransactionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransactionListPanel.class);

    @FXML
    private ListView<Transaction> transactionListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TransactionListPanel(ObservableList<Transaction> txnList) {
        super(FXML);
        transactionListView.setItems(txnList);
        transactionListView.setCellFactory(listView -> new TransactionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TransactionListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction txn, boolean empty) {
            super.updateItem(txn, empty);

            if (empty || txn == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionCard(txn, getIndex() + 1).getRoot());
            }
        }
    }

}

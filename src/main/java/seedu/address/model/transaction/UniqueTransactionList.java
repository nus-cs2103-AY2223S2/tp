package seedu.address.model.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Stores a global list of transactions.
 */
public class UniqueTransactionList {
    private final ObservableList<Transaction> internalList = FXCollections.observableArrayList();
    private final ObservableList<Transaction> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
}

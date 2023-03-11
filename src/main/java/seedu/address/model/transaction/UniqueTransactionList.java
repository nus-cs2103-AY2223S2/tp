package seedu.address.model.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;


/**
 * Stores a global list of transactions. Adapted from UniquePersonList implementation from AB3.
 */
public class UniqueTransactionList {
    private final ObservableList<Transaction> internalList = FXCollections.observableArrayList();
    private final ObservableList<Transaction> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Transaction toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTransaction);
    }




    public void add(Transaction t) {
    }

    public ObservableList<Transaction> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
}

package seedu.address.model.transaction;

import java.util.Comparator;

public class UpdatedTransactionComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getStatus().getLastUpdate()
                .compareTo(t2.getStatus().getLastUpdate());
    }
}
package seedu.address.model.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


import seedu.address.model.person.Person;

/**
 * Stores and manages the relationship between Person and Transactions.
 * Note that a transaction can only be mapped to at most one Person, while a Person can have many Transactions
 */
public class PersonTransactionMap {

    private final Map<Person, Transaction> PersonToTransactionMap = new HashMap<>();
    private final Map<Transaction, Person> TransactionToPersonMap = new TreeMap<>(new UpdatedTransactionComparator());

    public void addRelation(Transaction t, Person owner) {

    }
}

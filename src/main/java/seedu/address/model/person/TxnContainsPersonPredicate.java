package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.transaction.Transaction;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TxnContainsPersonPredicate implements Predicate<Transaction> {
    private final String keywords;

    public TxnContainsPersonPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getOwner().toString().trim().equalsIgnoreCase(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TxnContainsPersonPredicate // instanceof handles nulls
                        && keywords.equals(((TxnContainsPersonPredicate) other).keywords)); // state check
    }

}

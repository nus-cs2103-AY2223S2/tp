package seedu.address.model.person;

/**
 * Represents a Person's business size in the address book.
 * Guarantees: is valid as declared in {@link #isValidTransactionCount(String)} (String)}
 */
public class TransactionCount {


    public static final String MESSAGE_CONSTRAINTS =
            "Transaction Count will start and zero and be increment to a value >= 0";

    public final String value;

    /**
     * Constructs an {@code TransactionCount}.
     *
     * @param transactionCount The initial number of transactions between user and the client
     */
    public TransactionCount(String transactionCount) {
        if (transactionCount != null) {
            this.value = String.valueOf(transactionCount);
        } else {
            this.value = String.valueOf(0);
        }

    }

    public int getNumericValue() {
        return Integer.valueOf(this.value);
    }

    public static boolean isValidTransactionCount(String test) {
        if (Integer.valueOf(test) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Returns integer representation of business size value
     * @return Integer
     */
    public Integer getIntValue() {
        try {
            return Integer.valueOf(this.value);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TransactionCount // instanceof handles nulls
                && value.equals(((TransactionCount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

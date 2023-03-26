package seedu.address.model.transaction.status;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.StatusName;

/**
 * Valid status names for transaction statuses, and mappings to their symbols.
 */
public enum TxnStatusName implements StatusName {
    CLOSED("Closed", "C"),
    OPEN("Open", "O");

    public static final Map<String, TxnStatusName> SYMBOL_TXN_STATUS_MAP = new HashMap<>();
    public static final Map<String, TxnStatusName> STRING_TXN_STATUS_NAME_MAP = new HashMap<>();

    static {
        SYMBOL_TXN_STATUS_MAP.put(CLOSED.symbol, CLOSED);
        SYMBOL_TXN_STATUS_MAP.put(OPEN.symbol, OPEN);

        STRING_TXN_STATUS_NAME_MAP.put(CLOSED.label, CLOSED);
        STRING_TXN_STATUS_NAME_MAP.put(OPEN.label, OPEN);
    }

    private final String label;
    private final String symbol;

    TxnStatusName(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    /**
     * Returns true if a given String key maps to a valid TxnStatusName.
     * For example, a valid key can be "C" or "Closed" for the TxnStatus of "Closed"
     */
    public static boolean isValidStatusName(String test) {
        return SYMBOL_TXN_STATUS_MAP.containsKey(test)
                || STRING_TXN_STATUS_NAME_MAP.containsKey(test);
    }

    /**
     * Retrieves a TxnStatusName given a String, if it is valid.
     * @param name the search key for a TxnStatusName. Can be "C" or "Closed" for the TxnStatus of "Closed"
     * @return a valid TxnStatusName
     */
    public static TxnStatusName get(String name) {
        checkArgument(isValidStatusName(name), TxnStatus.MESSAGE_CONSTRAINTS);
        if (STRING_TXN_STATUS_NAME_MAP.containsKey(name)) {
            return STRING_TXN_STATUS_NAME_MAP.get(name);
        } else {
            assert SYMBOL_TXN_STATUS_MAP.containsKey(name);
            return SYMBOL_TXN_STATUS_MAP.get(name);
        }
    }

    /**
     * @return the display name for a transaction status
     */
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return symbol + " - " + label;
    }
}

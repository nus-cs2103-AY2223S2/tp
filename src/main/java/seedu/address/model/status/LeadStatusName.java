package seedu.address.model.status;

import java.util.HashMap;
import java.util.Map;

/**
 * Valid status names for lead statuses, and mappings to their symbols
 */
public enum LeadStatusName {
    UNCONTACTED("Uncontacted", "U"),
    WORKING("Working", "W"),
    QUALIFIED("Qualified", "Q"),
    UNQUALIFIED("Unqualified", "X");

    private final String label;
    private final String symbol;

    LeadStatusName(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public static final Map<String, LeadStatusName> SYMBOL_LEAD_STATUS_MAP = new HashMap<>();
    static {
        SYMBOL_LEAD_STATUS_MAP.put("U", UNCONTACTED);
        SYMBOL_LEAD_STATUS_MAP.put("W", WORKING);
        SYMBOL_LEAD_STATUS_MAP.put("Q", QUALIFIED);
        SYMBOL_LEAD_STATUS_MAP.put("X", UNQUALIFIED);
    }

    public static final Map<String, LeadStatusName> STRING_LEAD_STATUS_NAME_MAP = new HashMap<>();

    public static boolean isValidLeadStatus(String test) {
        return SYMBOL_LEAD_STATUS_MAP.containsKey(test)
                || STRING_LEAD_STATUS_NAME_MAP.containsKey(test.toUpperCase());
    }

    @Override
    public String toString() {
        return symbol + " - " + label;
    }
}

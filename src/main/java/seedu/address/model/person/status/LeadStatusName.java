package seedu.address.model.person.status;

import java.util.HashMap;
import java.util.Map;

import static seedu.address.commons.util.AppUtil.checkArgument;

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
        SYMBOL_LEAD_STATUS_MAP.put(UNCONTACTED.symbol, UNCONTACTED);
        SYMBOL_LEAD_STATUS_MAP.put(WORKING.symbol, WORKING);
        SYMBOL_LEAD_STATUS_MAP.put(QUALIFIED.symbol, QUALIFIED);
        SYMBOL_LEAD_STATUS_MAP.put(UNQUALIFIED.symbol, UNQUALIFIED);
    }

    public static final Map<String, LeadStatusName> STRING_LEAD_STATUS_NAME_MAP = new HashMap<>();

    static {
        STRING_LEAD_STATUS_NAME_MAP.put(UNCONTACTED.label, UNCONTACTED);
        STRING_LEAD_STATUS_NAME_MAP.put(WORKING.label, WORKING);
        STRING_LEAD_STATUS_NAME_MAP.put(QUALIFIED.label, QUALIFIED);
        STRING_LEAD_STATUS_NAME_MAP.put(UNQUALIFIED.label, UNQUALIFIED);
    }

    public static boolean isValidLeadStatus(String test) {
        return SYMBOL_LEAD_STATUS_MAP.containsKey(test)
                || STRING_LEAD_STATUS_NAME_MAP.containsKey(test);
    }

    public static LeadStatusName get(String name) {
        checkArgument(isValidLeadStatus(name), LeadStatus.MESSAGE_CONSTRAINTS);
        if (STRING_LEAD_STATUS_NAME_MAP.containsKey(name)) {
            return STRING_LEAD_STATUS_NAME_MAP.get(name);
        } else {
            assert SYMBOL_LEAD_STATUS_MAP.containsKey(name);
            return SYMBOL_LEAD_STATUS_MAP.get(name);
        }
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return symbol + " - " + label;
    }
}

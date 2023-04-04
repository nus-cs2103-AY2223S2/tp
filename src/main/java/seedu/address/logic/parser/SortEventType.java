package seedu.address.logic.parser;

/**
 * Contains valid user inputs to sort events and their descriptions in String.
 */
public enum SortEventType {
    SORT_BY_NAME_ASC("a", "event names in ascending order"),
    SORT_BY_NAME_DESC("b", "event names in descending order"),
    SORT_BY_START_DATE_TIME("c", "start date times in ascending order"),
    SORT_BY_END_DATE_TIME("d", "end date times in ascending order");

    private final String sortEventType;
    private final String description;

    SortEventType(String sortEventType, String description) {
        this.sortEventType = sortEventType;
        this.description = description;
    }

    public static SortEventType getSortEventType(String str) {
        for (SortEventType type : SortEventType.values()) {
            if (type.sortEventType.equals(str)) {
                return type;
            }
        }
        return null;
    }

    public String getSortEventTypeString() {
        return sortEventType;
    }

    public String getDescription() {
        return String.format(description);
    }
}

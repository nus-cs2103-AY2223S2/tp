package seedu.address.logic.parser;

/**
 * Contains valid user inputs to sort events and their descriptions in String.
 */
public enum SortEventKey {
    SORT_BY_NAME_ASC("a", "event names in ascending order"),
    SORT_BY_NAME_DESC("b", "event names in descending order"),
    SORT_BY_START_DATE_TIME("c", "start date times in ascending order"),
    SORT_BY_END_DATE_TIME("d", "end date times in ascending order");

    private final String sortEventKey;
    private final String description;

    SortEventKey(String sortEventKey, String description) {
        this.sortEventKey = sortEventKey;
        this.description = description;
    }

    public static SortEventKey getSortEventKey(String str) {
        for (SortEventKey type : SortEventKey.values()) {
            if (type.sortEventKey.equals(str)) {
                return type;
            }
        }
        return null;
    }

    public String getSortEventKeyString() {
        return sortEventKey;
    }

    public String getDescription() {
        return String.format(description);
    }
}

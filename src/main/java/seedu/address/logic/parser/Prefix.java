package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public enum Prefix {
    BLANK("", ""),
    NAME("n/", "Name"),
    STATION("s/", "Station"),
    PHONE("p/", "Phone"),
    TELEGRAM_HANDLE("t/", "Telegram Handle"),
    EMAIL("e/", "Email"),
    GROUP_TAG("g/", "Group Tags"),
    MODULE_TAG("m/", "Module Tags"),

    DAY("d/", "Day"),
    TIME("T/", "Time Period"),
    LOCATION("l/", "Location");
    private final String prefix;
    private final String description;

    Prefix(String prefix, String description) {
        this.prefix = prefix;
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return getPrefix();
    }
}

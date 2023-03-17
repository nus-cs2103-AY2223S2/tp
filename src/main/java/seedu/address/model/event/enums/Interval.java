package seedu.address.model.event.enums;

/**
 * Contains list of Intervals to be specified in the Calendar.
 */
public enum Interval {

    NONE("none"),
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    YEARLY("yearly");

    private final String value;

    private Interval(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

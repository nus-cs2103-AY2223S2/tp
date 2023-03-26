package seedu.address.logic.commands;

/**
 * Represents the possible types of Results that AutoM8 has produced.
 * This helps to identify which UI component to update based on ResultCommand's ResultType
 */
public enum ResultType {
    LISTED_CUSTOMERS,
    LISTED_VEHICLES,
    LISTED_SERVICES,
    VIEW_VEHICLE,
    SORT_CUSTOMERS,
    SORT_VEHICLES,
    SORT_SERVICES,
    SORT_TECHNICIANS,
    SORT_APPOINTMENTS,
    OTHERS;

    @Override
    public String toString() {
        return name();
    }
}

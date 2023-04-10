package seedu.address.model.event;

/**
 * Represents the list of {@code Event} that each {@code Person} has
 */
public class PersonEventList {
    private final IsolatedEventList isolatedEventList = new IsolatedEventList();
    private final RecurringEventList recurringEventList = new RecurringEventList();

    @Override
    public String toString() {
        return "One-off Events\n" + isolatedEventList
                + "\nRecurring Events\n" + recurringEventList;
    }
}

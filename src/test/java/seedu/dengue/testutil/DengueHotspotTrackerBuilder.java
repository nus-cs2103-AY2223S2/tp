package seedu.dengue.testutil;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.person.Person;

/**
 * A utility class to help with building DengueHotspotTracker objects.
 * Example usage: <br>
 *     {@code DengueHotspotTracker ab = new DengueHotspotTrackerBuilder().withPerson("John", "Doe").build();}
 */
public class DengueHotspotTrackerBuilder {

    private DengueHotspotTracker dengueHotspotTracker;

    public DengueHotspotTrackerBuilder() {
        dengueHotspotTracker = new DengueHotspotTracker();
    }

    public DengueHotspotTrackerBuilder(DengueHotspotTracker dengueHotspotTracker) {
        this.dengueHotspotTracker = dengueHotspotTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code DengueHotspotTracker} that we are building.
     */
    public DengueHotspotTrackerBuilder withPerson(Person person) {
        dengueHotspotTracker.addPerson(person);
        return this;
    }

    public DengueHotspotTracker build() {
        return dengueHotspotTracker;
    }
}

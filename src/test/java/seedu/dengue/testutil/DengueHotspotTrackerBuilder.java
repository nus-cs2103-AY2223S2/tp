package seedu.dengue.testutil;

import java.util.Random;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.exceptions.DuplicatePersonException;

/**
 * A utility class to help with building DengueHotspotTracker objects.
 * Example usage: <br>
 *     {@code DengueHotspotTracker ab = new DengueHotspotTrackerBuilder().withPerson("John", "Doe").build();}
 */
public class DengueHotspotTrackerBuilder {
    private static final int BOUND = 15;
    private static final Random RANDOM = new Random();

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

    /**
     * Adds a random number of generated {@code Person} to the {@code DengueHotspotTracker}.
     * @return A DengueHotspotTracker with a random number of random persons.
     */
    public DengueHotspotTracker buildRandom() {
        dengueHotspotTracker = new DengueHotspotTracker();
        for (int i = 0; i < RANDOM.nextInt(BOUND); i++) {
            try {
                dengueHotspotTracker.addPerson(new PersonBuilder().buildRandom());
            } catch (DuplicatePersonException err) {
                // This will almost never happen. If it happens, ignore.
            }
        }
        return dengueHotspotTracker.generateDeepCopy();
    }

    /**
     * Builds a {@link DengueHotspotTracker}.
     * @return A copy of {@code dengueHotspotTracker}.
     */
    public DengueHotspotTracker build() {
        return dengueHotspotTracker.generateDeepCopy();
    }

    /**
     * Resets {@code dengueHotspotTracker} and returns it.
     * @return
     */
    public DengueHotspotTracker buildNew() {
        this.dengueHotspotTracker = new DengueHotspotTracker();
        return new DengueHotspotTracker();
    }

}

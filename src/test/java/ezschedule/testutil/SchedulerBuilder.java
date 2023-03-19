package ezschedule.testutil;

import ezschedule.model.Scheduler;
import ezschedule.model.event.Event;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class SchedulerBuilder {

    private Scheduler scheduler;

    public SchedulerBuilder() {
        scheduler = new Scheduler();
    }

    public SchedulerBuilder(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public SchedulerBuilder withEvent(Event event) {
        scheduler.addEvent(event);
        return this;
    }

    public Scheduler build() {
        return scheduler;
    }
}

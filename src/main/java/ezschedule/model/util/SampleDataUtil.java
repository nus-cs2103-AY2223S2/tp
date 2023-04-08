package ezschedule.model.util;

import ezschedule.model.ReadOnlyScheduler;
import ezschedule.model.Scheduler;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.model.event.Time;

/**
 * Contains utility methods for populating {@code Scheduler} with sample data.
 */
public class SampleDataUtil {

    public static Event[] getSampleEvents() {
        return new Event[]{
            new Event(new Name("Tennis"), new Date("2024-01-01"), new Time("18:00"), new Time("20:00")),
            new Event(new Name("Basketball"), new Date("2024-02-02"), new Time("18:00"), new Time("20:00")),
            new Event(new Name("Bowling"), new Date("2024-03-03"), new Time("18:00"), new Time("20:00"))
        };
    }

    public static ReadOnlyScheduler getSampleScheduler() {
        Scheduler sampleSc = new Scheduler();
        for (Event sampleEvent : getSampleEvents()) {
            sampleSc.addEvent(sampleEvent);
        }
        return sampleSc;
    }
}

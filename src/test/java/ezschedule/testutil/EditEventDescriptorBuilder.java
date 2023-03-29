package ezschedule.testutil;

import ezschedule.logic.commands.EditCommand.EditEventDescriptor;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.model.event.Time;

/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setDate(event.getDate());
        descriptor.setStartTime(event.getStartTime());
        descriptor.setEndTime(event.getEndTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }


    /**
     * Sets the {@code StartTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withStartTime(String time) {
        descriptor.setStartTime(new Time(time));
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEndTime(String time) {
        descriptor.setEndTime(new Time(time));
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}

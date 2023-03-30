package ezschedule.testutil;

import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;

/**
 * A utility class to help with building FindEventDescriptor objects.
 */
public class FindEventDescriptorBuilder {

    private FindEventDescriptor descriptor;

    public FindEventDescriptorBuilder() {
        descriptor = new FindEventDescriptor();
    }

    public FindEventDescriptorBuilder(FindEventDescriptor descriptor) {
        this.descriptor = new FindEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code FindEventDescriptor} with fields containing {@code event}'s details
     */
    public FindEventDescriptorBuilder(Event event) {
        descriptor = new FindEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setDate(event.getDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public FindEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditEventDescriptor} that we are building.
     */
    public FindEventDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    public FindEventDescriptor build() {
        return descriptor;
    }
}

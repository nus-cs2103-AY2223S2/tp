package seedu.event.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.event.logic.commands.EditCommand.EditEventDescriptor;
import seedu.event.model.event.Address;
import seedu.event.model.event.Event;
import seedu.event.model.event.Mark;
import seedu.event.model.event.Name;
import seedu.event.model.event.Rate;
import seedu.event.model.event.Time;
import seedu.event.model.tag.Tag;

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
        descriptor.setRate(event.getRate());
        descriptor.setAddress(event.getAddress());
        descriptor.setStartTime(event.getStartTime());
        descriptor.setEndTime(event.getEndTime());
        descriptor.setMark(event.getMark());
        descriptor.setTags(event.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withPhone(String rate) {
        descriptor.setRate(new Rate(rate));
        return this;
    }
    /**
     * Sets the {@code Address} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the start time of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(new Time(startTime));
        return this;
    }

    /**
     * Sets the end time of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEndTime(String endTime) {
        descriptor.setEndTime(new Time(endTime));
        return this;
    }

    /**
     * Sets the {@code Mark} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withMark(String mark) {
        Mark temp = new Mark();
        if (mark.equals("[X]")) {
            temp.setDone();
        }
        descriptor.setMark(temp);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEventDescriptor}
     * that we are building.
     */
    public EditEventDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}

package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Event;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Timing;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Event person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setRate(person.getRate());
        descriptor.setAddress(person.getAddress());
        descriptor.setTiming(person.getTiming());
        descriptor.setMark(person.getMark());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String rate) {
        descriptor.setRate(new Rate(rate));
        return this;
    }
    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code TIMING} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTiming(String startTime, String endTime) {
        descriptor.setTiming(new Timing(startTime, endTime));
        return this;
    }

    /**
     * Sets the {@code Mark} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMark(String mark) {
        Mark temp = new Mark();
        if (mark.equals("[X]")) {
            temp.setDone();
        }
        descriptor.setMark(temp);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

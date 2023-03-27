package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.logic.commands.UpdateMeetingCommand;
import seedu.address.model.person.Meeting;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private UpdateMeetingCommand.EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new UpdateMeetingCommand.EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(UpdateMeetingCommand.EditMeetingDescriptor descriptor) {
        this.descriptor = new UpdateMeetingCommand.EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new UpdateMeetingCommand.EditMeetingDescriptor();
        descriptor.setDescription(meeting.getDescription());
        descriptor.setStart(meeting.getStart());
        descriptor.setEnd(meeting.getEnd());
    }

    /**
     * Sets the {@code Description} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    /**
     * Sets the {@code Start} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withStart(LocalDateTime start) {
        descriptor.setStart(start);
        return this;
    }

    /**
     * Sets the {@code End} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withEnd(LocalDateTime end) {
        descriptor.setEnd(end);
        return this;
    }

    public UpdateMeetingCommand.EditMeetingDescriptor build() {
        return descriptor;
    }
}

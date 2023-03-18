package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.logic.commands.EditIsolatedEventCommand;
import seedu.address.logic.commands.EditRecurringEventCommand;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditEventDescriptorBuilder {
    private EditIsolatedEventCommand.EditEventDescriptor descriptor;
    private EditRecurringEventCommand.EditEventDescriptor recurringDescriptor;

    /**
     * Event builder for recurring events
     */
    public EditEventDescriptorBuilder() {
        descriptor = new EditIsolatedEventCommand.EditEventDescriptor();
        recurringDescriptor = new EditRecurringEventCommand.EditEventDescriptor();
    }

    /**
     * Event builder for isolated events
     */
    public EditEventDescriptorBuilder(EditIsolatedEventCommand.EditEventDescriptor descriptor) {
        this.descriptor = new EditIsolatedEventCommand.EditEventDescriptor(descriptor);
    }

    public EditEventDescriptorBuilder(EditRecurringEventCommand.EditEventDescriptor descriptor) {
        this.recurringDescriptor = new EditRecurringEventCommand.EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditEventDescriptorBuilder(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        descriptor = new EditIsolatedEventCommand.EditEventDescriptor();
        descriptor.setEventName(eventName);
        descriptor.setEndDate(endDate);
        descriptor.setStartDate(startDate);
    }

    /**
     * eturns an {@code EditPersonDescriptor} with fields containing {@code person}'s details for recurring list
     * @param eventName
     * @param dayOfWeek
     * @param startTime
     * @param endTime
     */
    public EditEventDescriptorBuilder(String eventName, DayOfWeek dayOfWeek, LocalTime startTime,
                                      LocalTime endTime) {
        recurringDescriptor = new EditRecurringEventCommand.EditEventDescriptor();
        recurringDescriptor.setEventName(eventName);
        recurringDescriptor.setDayOfWeek(dayOfWeek);
        recurringDescriptor.setStartTime(startTime);
        recurringDescriptor.setEndTime(endTime);
    }

    public EditIsolatedEventCommand.EditEventDescriptor build() {
        return descriptor;
    }

    public EditRecurringEventCommand.EditEventDescriptor recurringbuild() {
        return recurringDescriptor;
    }

}


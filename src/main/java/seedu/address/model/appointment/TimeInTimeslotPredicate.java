package seedu.address.model.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Appointment}'s {@code Name} matches any of the keywords given.
 */
public class TimeInTimeslotPredicate implements Predicate<Appointment> {
    private final LocalDateTime time;

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns an FindAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimeInTimeslotPredicate(String timeStr) throws ParseException {
        if (!Timeslot.isValidDatetime(timeStr)) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        this.time = LocalDateTime.parse(timeStr, Timeslot.getDateTimeFormatter());
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.duringTime(this.time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TimeInTimeslotPredicate // instanceof handles nulls
            && time.equals(((TimeInTimeslotPredicate) other).time)); // state check
    }

}

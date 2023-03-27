package seedu.address.model.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Appointment}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentDuringTimePredicate implements Predicate<Appointment> {
    private final Timeslot timeslot;

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns an FindAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentDuringTimePredicate(String timeStr) throws ParseException {
        String[] splitTimeStr = timeStr.split(" ");
        String startTimeStr = splitTimeStr[0] + " " + splitTimeStr[1];
        String endTimeStr = splitTimeStr[2] + " " + splitTimeStr[3];
        if (!Timeslot.isValidDatetime(startTimeStr) || !Timeslot.isValidDatetime(endTimeStr)) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        this.timeslot = new Timeslot(startTimeStr + "," + endTimeStr);
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.hasOverlap(this.timeslot);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentDuringTimePredicate // instanceof handles nulls
            && timeslot.equals(((AppointmentDuringTimePredicate) other).timeslot)); // state check
    }

}

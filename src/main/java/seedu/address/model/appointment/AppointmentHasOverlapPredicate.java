package seedu.address.model.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Appointment}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentHasOverlapPredicate implements Predicate<Appointment> {
    private final Timeslot timeslot;

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns an FindAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentHasOverlapPredicate(String timeStr) throws ParseException {
        String[] splitTimeStr = timeStr.split(" ");
        String startTimeStr = splitTimeStr[0] + " " + splitTimeStr[1];
        String endTimeStr = splitTimeStr[2] + " " + splitTimeStr[3];
        if (!Timeslot.isValidDatetime(startTimeStr) || !Timeslot.isValidDatetime(endTimeStr)) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        Timeslot result;
        try {
            result = new Timeslot(startTimeStr + "," + endTimeStr);
        } catch (IllegalArgumentException iae) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        this.timeslot = result;
    }

    private AppointmentHasOverlapPredicate(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Returns a predicate that filters out appointments that are not happening on the current day.
     *
     * @return Predicate with a timeslot covering the current day.
     */
    public static AppointmentHasOverlapPredicate todayPredicate() {
        // Adapted from
        // https://stackoverflow.com/questions/6850874/
        // how-to-create-a-java-date-object-of-midnight-today-and-midnight-tomorrow
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        return new AppointmentHasOverlapPredicate(new Timeslot(todayMidnight, tomorrowMidnight));
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.hasOverlap(this.timeslot);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentHasOverlapPredicate // instanceof handles nulls
            && timeslot.equals(((AppointmentHasOverlapPredicate) other).timeslot)); // state check
    }

}

package arb.model.project;

import static arb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

/**
 * Represents a Project's deadline date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline must be in a recognisable format, e.g. DD/MM/YYYY or \'3pm tomorrow\'.";

    public final LocalDateTime dueDate;

    /**
     * Constructs a {@code Deadline}.
     * @param date A valid due date.
     */
    public Deadline(String date) {
        requireNonNull(date);
        checkArgument(isValidDeadline(date), MESSAGE_CONSTRAINTS);
        dueDate = parseDate(date);
    }

    // Reused from https://stackoverflow.com/questions/21242110/convert-java-util-date-to-java-time-localdate
    // with minor modifications
    private LocalDateTime parseDate(String dateString) {
        Date date = getPossibleDatesFromDateString(dateString).get(0);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Returns true if a given string is a valid deadline.
     * @param test String to test.
     * @return True if valid.
     */
    public static boolean isValidDeadline(String test) {
        List<Date> dates = getPossibleDatesFromDateString(test);
        return dates.size() != 0;
    }

    private static List<Date> getPossibleDatesFromDateString(String date) {
        return new PrettyTimeParser().parse(date);
    }

    @Override
    public String toString() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline) // handles null
                && dueDate.equals(((Deadline) other).dueDate); // checks date
    }

    @Override
    public int hashCode() {
        return dueDate.hashCode();
    }
}

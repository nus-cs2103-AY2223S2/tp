package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "";
    public static final String VALIDATION_REGEX ="";

    public final LocalDateTime dateTime;
    public final String value;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs an {@code DateTime}.
     *
     * @param date A valid date.
     */
    public DateTime(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        value = dateTimeFormatter.format(this.dateTime);
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && dateTime.equals(((DateTime) other).dateTime)); // state check
    }

}

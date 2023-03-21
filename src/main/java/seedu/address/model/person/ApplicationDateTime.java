package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;



/**
 * Represents a Person's interview date and time in the address book.
 */
public class ApplicationDateTime {
    private final LocalDateTime applicationDateTime;

    /**
     * Constructor for ApplicationDateTime
     * @param applicationDateTime LocalDateTime of the current time and date
     */
    public ApplicationDateTime(LocalDateTime applicationDateTime) {
        requireNonNull(applicationDateTime);

        this.applicationDateTime = applicationDateTime;
    }

    /**
     * @return LocalDateTime object of the application date and time.
     */
    public LocalDateTime getApplicationDateTime() {
        return this.applicationDateTime;
    }

    @Override
    public String toString() {
        return DateTimeParser.datetimeFormatter(applicationDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationDateTime // instanceof handles nulls
                && applicationDateTime.equals(((ApplicationDateTime) other).applicationDateTime)); // state check
    }
}


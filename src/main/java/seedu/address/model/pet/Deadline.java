package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Deadline object with fields (String description, LocalDateTime time)
 *  Represents a deadline for reminder
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline description can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String description;
    public final LocalDateTime deadline;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param description A valid deadline description.
     * @param deadline A valid deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        requireNonNull(description);
        //checkArgument(isValidDeadline(description, deadline), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns true if a given description is valid and that deadline is in the future.
     */
    public static boolean isValidDeadline(String test, LocalDateTime d) {

        return test.matches(VALIDATION_REGEX) && d.isAfter(LocalDateTime.now());
    }

    public LocalDateTime getDate() {
        return deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return description + " by " + deadline.format(customFormatter) + "";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof Deadline // instanceof handles nulls
                && description.equals(((Deadline) other).description))
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}

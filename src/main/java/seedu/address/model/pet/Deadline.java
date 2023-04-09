package seedu.address.model.pet;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *  Deadline object with fields (String description, LocalDateTime time)
 *  Represents a deadline for reminder
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in the future and time should be in the format of yyyy-MM-dd HH:mm:ss";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String description;
    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param description A valid deadline description.
     * @param deadline A valid deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        requireNonNull(description);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns true if a deadline is in the future.
     */
    public static boolean isValidDeadline(LocalDateTime d) {

        return isNull(d) || d.isAfter(LocalDateTime.now());
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
        return Objects.hashCode(description);
    }

}

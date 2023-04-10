package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * StatusUpdate that encapsulates a StatusValue and a date.
 * These updates form a Status of an Order.
 */
public class StatusUpdate implements Comparable<StatusUpdate> {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public final StatusValue statusValue;
    public final LocalDate date;

    /**
     * Constructs a {@code StatusUpdate}.
     *
     * @param statusValue Status Value associated with this update.
     * @param date A valid date.
     */
    public StatusUpdate(StatusValue statusValue, LocalDate date) {
        requireNonNull(statusValue);
        requireNonNull(date);
        this.statusValue = statusValue;
        this.date = date;
    }

    /**
     * Returns a new {@code StatusUpdate} which StatusValue is the next logical stage
     * with the given date.
     * @param date A valid date.
     * @return A new StatusUpdate.
     */
    public StatusUpdate nextStatusUpdate(LocalDate date) {
        // completed or cancelled should be the last stage
        if (this.statusValue.equals(StatusValue.COMPLETED)
                || this.statusValue.equals(StatusValue.CANCELLED)) {
            throw new IllegalStateException();
        }
        return new StatusUpdate(this.statusValue.nextValue(), date);
    }

    /**
     * Returns a new {@code StatusUpdate} which StatusValue is the next logical stage
     * with the current date.
     * @return A new StatusUpdate.
     */
    public StatusUpdate nextStatusUpdate() {
        return nextStatusUpdate(LocalDate.now());
    }

    public LocalDate getDate() {
        return this.date;
    }

    public StatusValue getStatusValue() {
        return this.statusValue;
    }

    @Override
    public int compareTo(StatusUpdate o) {
        return this.statusValue.compareTo(o.statusValue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusUpdate // instanceof handles nulls
                && statusValue.equals(((StatusUpdate) other).statusValue)
                && date.equals(((StatusUpdate) other).date)); // state check
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", statusValue, date.format(DATE_FORMATTER));
    }
}

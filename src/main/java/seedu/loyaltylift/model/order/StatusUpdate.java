package seedu.loyaltylift.model.order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

public class StatusUpdate implements Comparable<StatusUpdate> {

    public final StatusValue statusValue;
    public final LocalDate date;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public StatusUpdate(StatusValue statusValue, LocalDate date) {
        requireNonNull(statusValue);
        requireNonNull(date);
        this.statusValue = statusValue;
        this.date = date;
    }

    public StatusUpdate nextStatusUpdate(LocalDate date) {
        // completed or cancelled should be the last stage
        if (this.statusValue.equals(StatusValue.COMPLETED) ||
            this.statusValue.equals(StatusValue.CANCELLED)) {
            throw new IllegalStateException();
        }
        return new StatusUpdate(this.statusValue.nextValue(), date);
    }

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
                || (other instanceof StatusUpdate  // instanceof handles nulls
                && statusValue.equals(((StatusUpdate) other).statusValue)
                && date.equals(((StatusUpdate) other).date)); // state check
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", statusValue, date.format(DATE_FORMATTER));
    }
}

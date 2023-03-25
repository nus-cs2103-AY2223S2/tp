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
        return new StatusUpdate(this.statusValue.nextValue(), date);
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
    public String toString() {
        return String.format("[%s] %s", statusValue, date.format(DATE_FORMATTER));
    }
}

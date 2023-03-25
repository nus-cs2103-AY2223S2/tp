package seedu.loyaltylift.model.order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

public class StatusUpdate {

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
}

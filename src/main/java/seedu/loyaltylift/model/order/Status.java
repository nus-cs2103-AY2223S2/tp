package seedu.loyaltylift.model.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Represents the collection of {@code StatusUpdate} of an Order.
 */
public class Status {

    public final List<StatusUpdate> statusUpdates;

    public Status(List<StatusUpdate> statusUpdates) {
        requireNonNull(statusUpdates);
        this.statusUpdates = List.copyOf(statusUpdates.stream().sorted().collect(Collectors.toList()));
    }

    public Status() {
        this.statusUpdates = List.of(
                new StatusUpdate(StatusValue.PENDING, LocalDate.now())
        );
    }

    public Status newStatusWithNewUpdate(LocalDate date) {
        StatusUpdate latestStatusUpdate = getLatestStatus();
        List<StatusUpdate> newStatusUpdates = List.copyOf(this.statusUpdates);
        newStatusUpdates.add(latestStatusUpdate.nextStatusUpdate(date));

        return new Status(newStatusUpdates);
    }

    public Status newStatusWithRemoveLatest() {
        List<StatusUpdate> newStatusUpdates = List.copyOf(this.statusUpdates);
        newStatusUpdates.remove(newStatusUpdates.size() - 1);

        return new Status(newStatusUpdates);
    }

    public List<StatusUpdate> getStatusUpdates() {
        return List.copyOf(statusUpdates);
    }

    public StatusUpdate getLatestStatus() {
        return statusUpdates.get(statusUpdates.size() - 1);
    }

    @Override
    public String toString() {
        return statusUpdates.toString();
    }
}

package seedu.loyaltylift.model.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.util.AppUtil.checkArgument;

/**
 * Represents the collection of {@code StatusUpdate} of an Order.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status updates should not be empty";
    public final List<StatusUpdate> statusUpdates;

    public Status(List<StatusUpdate> statusUpdates) {
        requireNonNull(statusUpdates);
        checkArgument(isValidStatus(statusUpdates), MESSAGE_CONSTRAINTS);
        this.statusUpdates = List.copyOf(statusUpdates.stream().sorted().collect(Collectors.toList()));
    }

    public Status() {
        this.statusUpdates = List.of(
                new StatusUpdate(StatusValue.PENDING, LocalDate.now())
        );
    }

    /**
     * Returns true if a given list is a valid Status.
     */
    public static boolean isValidStatus(List<StatusUpdate> statusUpdates) {
        return statusUpdates.size() > 0;
    }

    public Status newStatusWithNewUpdate(LocalDate date) {
        if (getLatestStatus().statusValue.equals(StatusValue.COMPLETED) ||
            getLatestStatus().statusValue.equals(StatusValue.CANCELLED)) {
            throw new IllegalStateException();
        }

        StatusUpdate latestStatusUpdate = getLatestStatus();
        ArrayList<StatusUpdate> newStatusUpdates = new ArrayList<>(this.statusUpdates);
        newStatusUpdates.add(latestStatusUpdate.nextStatusUpdate(date));

        return new Status(newStatusUpdates);
    }

    public Status newStatusWithRemoveLatest() {
        if (getLatestStatus().statusValue.equals(StatusValue.PENDING)) {
            throw new IllegalStateException();
        }

        ArrayList<StatusUpdate> newStatusUpdates = new ArrayList<>(this.statusUpdates);
        newStatusUpdates.remove(newStatusUpdates.size() - 1);

        return new Status(newStatusUpdates);
    }

    public List<StatusUpdate> getStatusUpdates() {
        return List.copyOf(statusUpdates);
    }

    public StatusUpdate getLatestStatus() {
        return statusUpdates.get(statusUpdates.size() - 1);
    }

    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status  // instanceof handles nulls
                && statusUpdates.equals(((Status) other).statusUpdates)); // state check
    }

    @Override
    public String toString() {
        return statusUpdates.toString();
    }
}

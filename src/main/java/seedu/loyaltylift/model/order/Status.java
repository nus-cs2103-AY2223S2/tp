package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the collection of {@code StatusUpdate} of an Order.
 */
public class Status implements Comparable<Status> {

    public static final String MESSAGE_CONSTRAINTS = "Status updates should not be empty";
    private final List<StatusUpdate> statusUpdates;

    /**
     * Constructs a {@code Status} from a list of {@code StatusUpdate}.
     * An unmodifiable copy of the status updates is created prevent accidental modification.
     *
     * @param statusUpdates A list of status updates.
     */
    public Status(List<StatusUpdate> statusUpdates) {
        requireNonNull(statusUpdates);
        checkArgument(isValidStatus(statusUpdates), MESSAGE_CONSTRAINTS);
        this.statusUpdates = List.copyOf(statusUpdates.stream().sorted().collect(Collectors.toList()));
    }

    /**
     * Constructs a {@code Status}.
     * Creates a single status update with its date set to the current time.
     */
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

    /**
     * Returns a new {@code Status} with a copy of this StatusUpdates and
     * a new StatusUpdate that has the next logical StatusValue and given date.
     * Should not be called when Status is already at completed or cancelled stage.
     *
     * @param date Date of the next StatusUpdate.
     */
    public Status newStatusWithNewUpdate(LocalDate date) {
        if (getLatestStatus().statusValue.equals(StatusValue.COMPLETED)
                || getLatestStatus().statusValue.equals(StatusValue.CANCELLED)) {
            throw new IllegalStateException();
        }

        StatusUpdate latestStatusUpdate = getLatestStatus();
        ArrayList<StatusUpdate> newStatusUpdates = new ArrayList<>(this.statusUpdates);
        newStatusUpdates.add(latestStatusUpdate.nextStatusUpdate(date));

        return new Status(newStatusUpdates);
    }

    /**
     * Returns a new {@code Status} with a copy of this StatusUpdate with
     * a new StatusUpdate with a "Cancelled" StatusValue as the next StatusValue.
     * Should not be called when Status is at Completed stage.
     */
    public Status newStatusForCancelledOrder(LocalDate date) {
        if (getLatestStatus().statusValue.equals(StatusValue.COMPLETED)) {
            throw new IllegalStateException();
        }

        ArrayList<StatusUpdate> newStatusUpdates = new ArrayList<>(this.statusUpdates);
        StatusUpdate statusUpdateCancelled = new StatusUpdate(StatusValue.CANCELLED, LocalDate.now());
        newStatusUpdates.add(statusUpdateCancelled);

        return new Status(newStatusUpdates);
    }

    /**
     * Returns a new {@code Status} with a copy of this StatusUpdate with the
     * last StatusUpdate removed.
     * Should not be called when Status is at Pending stage.
     */
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

    /**
     * 2 {@code Status} are equal if all of its {@code StatusUpdate} are equal.
     * @param other The object to compare to.
     * @return True if the 2 Status are the same, False otherwise.
     */
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && statusUpdates.equals(((Status) other).statusUpdates)); // state check
    }

    @Override
    public String toString() {
        return statusUpdates.toString();
    }

    @Override
    public int compareTo(Status o) {
        return getLatestStatus().compareTo(o.getLatestStatus());
    }

}

package seedu.internship.model.internship;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;

/**
 * Calculates Statistics based on current Internship List and Event List.
 */
public class Statistics {

    public static final Statistics EMPTY_STATISTICS = new Statistics();

    private Datapoint totalInternships;
    private Datapoint numInterested;
    private Datapoint numApplied;
    private Datapoint numOffered;
    private Datapoint numRejected;
    private final ObservableList<Datapoint> allDatapoints;

    private Statistics() {
        this.allDatapoints = FXCollections.observableArrayList();
        totalInternships = new Datapoint("Total Internships", 0);
        numInterested = new Datapoint("Interested", 0);
        numApplied = new Datapoint("Applied", 0);
        numOffered = new Datapoint("Offered", 0);
        numRejected = new Datapoint("Rejected", 0);
        allDatapoints.addAll(totalInternships, numInterested, numApplied, numOffered, numRejected);
    }

    /**
     * Creates Statistics based on current internships and events
     *
     * @param internships Current List of Internships
     * @param events Current List of Events
     */
    public Statistics(ObservableList<Internship> internships, ObservableList<Event> events) {
        this();
        parseInternshipList(internships);
        parseEventList(events);
    }

    private void parseInternshipList(ObservableList<Internship> internships) {
        for (Internship internship : internships) {
            switch (internship.getStatusId()) {
            case 0:
                numInterested.incrementValue(1);
                break;
            case 1:
                numApplied.incrementValue(1);
                break;
            case 2:
                numOffered.incrementValue(1);
                break;
            case 3:
                numRejected.incrementValue(1);
                break;
            default:
                break;
            }
            totalInternships.incrementValue(1);
        }
    }

    private void parseEventList(ObservableList<Event> events) {
        allDatapoints.add(new Datapoint("Total Events", events.size()));
    }

    public Datapoint getTotalInternships() {
        return totalInternships;
    }

    public Datapoint getNumInterested() {
        return numInterested;
    }

    public Datapoint getNumApplied() {
        return numApplied;
    }

    public Datapoint getNumRejected() {
        return numRejected;
    }

    public Datapoint getNumOffered() {
        return numOffered;
    }

    public ObservableList<Datapoint> getAllDatapoints() {
        return allDatapoints;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Statistics)) {
            return false;
        }

        Statistics otherStatistics = (Statistics) other;

        return totalInternships == otherStatistics.totalInternships
                && numApplied == otherStatistics.numApplied
                && numOffered == otherStatistics.numOffered
                && numRejected == otherStatistics.numRejected;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalInternships, numApplied, numOffered, numRejected);
    }
}

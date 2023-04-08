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

    public static final String TOTAL_INTERNSHIPS_NAME = "Total Internships";
    public static final String NUM_INTERESTED_NAME = "Interested";
    public static final String NUM_APPLIED_NAME = "Applied";
    public static final String NUM_OFFERED_NAME = "Offered";
    public static final String NUM_REJECTED_NAME = "Rejected";
    public static final String NUM_EVENTS_NAME = "Total Events";

    private final Datapoint totalInternships;
    private final Datapoint numInterested;
    private final Datapoint numApplied;
    private final Datapoint numOffered;
    private final Datapoint numRejected;
    private final Datapoint numEvents;
    private final ObservableList<Datapoint> allDatapoints;

    /**
     * Private constructor that initialises all Datapoint fields to value .
     */
    private Statistics() {
        allDatapoints = FXCollections.observableArrayList();
        totalInternships = new Datapoint(TOTAL_INTERNSHIPS_NAME);
        numInterested = new Datapoint(NUM_INTERESTED_NAME);
        numApplied = new Datapoint(NUM_APPLIED_NAME);
        numOffered = new Datapoint(NUM_OFFERED_NAME);
        numRejected = new Datapoint(NUM_REJECTED_NAME);
        numEvents = new Datapoint(NUM_EVENTS_NAME);
        allDatapoints.addAll(totalInternships, numInterested, numApplied, numOffered, numRejected, numEvents);
    }

    /**
     * Constructs a Statistics instance, populating the Datapoint fields based on the data from the internship and
     * event list given.
     * @param internships List of internships.
     * @param events List of events.
     */
    public Statistics(ObservableList<Internship> internships, ObservableList<Event> events) {
        this();
        parseInternshipList(internships);
        parseEventList(events);
    }

    /**
     * Constructs a Statistics instance, populating the Datapoint fields directly with given integer values.
     * @param totalInternships Integer value to pass to totalInternships Datapoint.
     * @param numInterested Integer value to pass to numInterested Datapoint.
     * @param numApplied Integer value to pass to numApplied Datapoint.
     * @param numOffered Integer value to pass to numOffered Datapoint.
     * @param numRejected Integer value to pass to numRejected Datapoint.
     * @param numEvents Integer value to pass to numEvents Datapoint.
     */
    public Statistics(int totalInternships, int numInterested, int numApplied, int numOffered, int numRejected,
                      int numEvents) {
        this();
        this.totalInternships.incrementValue(totalInternships);
        this.numInterested.incrementValue(numInterested);
        this.numApplied.incrementValue(numApplied);
        this.numOffered.incrementValue(numOffered);
        this.numRejected.incrementValue(numRejected);
        this.numEvents.incrementValue(numEvents);
    }

    /**
     * Extracts internship related data and passes the values to internship related Datapoints.
     * @param internships Internship list with data to parse.
     */
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

    /**
     * Extract event related data and passes the values to event related Datapoints.
     * @param events Event list with data to parse.
     */
    private void parseEventList(ObservableList<Event> events) {
        numEvents.incrementValue(events.size());
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

        return allDatapoints.equals(otherStatistics.allDatapoints)
                && totalInternships.equals(otherStatistics.totalInternships)
                && numApplied.equals(otherStatistics.numApplied)
                && numOffered.equals(otherStatistics.numOffered)
                && numRejected.equals(otherStatistics.numRejected)
                && numEvents.equals(otherStatistics.numEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalInternships, numApplied, numOffered, numRejected);
    }
}

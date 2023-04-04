package seedu.internship.model.internship;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;

import java.util.Objects;

public class Statistics {

    public static final Statistics EMPTY_STATISTICS = new Statistics();

    private final Datapoint totalInternships;
    private final Datapoint numInterested;
    private final Datapoint numApplied;
    private final Datapoint numOffered;
    private final Datapoint numRejected;
    private final Datapoint numEvents;

    public static String TOTAL_INTERNSHIPS_NAME = "Total Internships";
    public static String NUM_INTERESTED_NAME = "Interested";
    public static String NUM_APPLIED_NAME = "Applied";
    public static String NUM_OFFERED_NAME = "Offered";
    public static String NUM_REJECTED_NAME = "Rejected";
    public static String NUM_EVENTS_NAME = "Total Events";

    private final ObservableList<Datapoint> allDatapoints;

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

    public Statistics(ObservableList<Internship> internships, ObservableList<Event> events) {
        this();
        parseInternshipList(internships);
        parseEventList(events);
    }

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
            }
            totalInternships.incrementValue(1);
        }
    }

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

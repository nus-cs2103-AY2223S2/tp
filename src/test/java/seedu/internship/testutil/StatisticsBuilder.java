package seedu.internship.testutil;

import seedu.internship.model.internship.Datapoint;
import seedu.internship.model.internship.Statistics;

public class StatisticsBuilder {

    private Datapoint totalInternships;
    private Datapoint numInterested;
    private Datapoint numApplied;
    private Datapoint numOffered;
    private Datapoint numRejected;
    private Datapoint numEvents;

    public StatisticsBuilder() {
        totalInternships = new Datapoint(Statistics.TOTAL_INTERNSHIPS_NAME);
        numInterested = new Datapoint(Statistics.NUM_INTERESTED_NAME);
        numApplied = new Datapoint(Statistics.NUM_APPLIED_NAME);
        numOffered = new Datapoint(Statistics.NUM_OFFERED_NAME);
        numRejected = new Datapoint(Statistics.NUM_REJECTED_NAME);
        numEvents = new Datapoint(Statistics.NUM_EVENTS_NAME);
    }

    public StatisticsBuilder withTotalInternships(int value) {
        this.totalInternships = new Datapoint(Statistics.TOTAL_INTERNSHIPS_NAME, value);
        return this;
    }

    public StatisticsBuilder withNumInterested(int value) {
        this.numInterested = new Datapoint(Statistics.NUM_INTERESTED_NAME, value);
        return this;
    }

    public StatisticsBuilder withNumApplied(int value) {
        this.numApplied = new Datapoint(Statistics.NUM_APPLIED_NAME, value);
        return this;
    }

    public StatisticsBuilder withNumOffered(int value) {
        this.numOffered = new Datapoint(Statistics.NUM_OFFERED_NAME, value);
        return this;
    }

    public StatisticsBuilder withNumRejected(int value) {
        this.numRejected = new Datapoint(Statistics.NUM_REJECTED_NAME, value);
        return this;
    }

    public StatisticsBuilder withNumEvents(int value) {
        this.numEvents = new Datapoint(Statistics.NUM_EVENTS_NAME, value);
        return this;
    }

    public Statistics build() {
        return(new Statistics(totalInternships.getValue(), numInterested.getValue(),
                numApplied.getValue(), numOffered.getValue(), numRejected.getValue(), numEvents.getValue()));
    }
}

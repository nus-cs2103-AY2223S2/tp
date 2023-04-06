package seedu.internship.testutil;

import seedu.internship.model.internship.Datapoint;
import seedu.internship.model.internship.Statistics;

/**
 * Responsible for building statistics for Testing
 */
public class StatisticsBuilder {

    private Datapoint totalInternships;
    private Datapoint numInterested;
    private Datapoint numApplied;
    private Datapoint numOffered;
    private Datapoint numRejected;
    private Datapoint numEvents;

    /**
     * Constructor for StatisticsBuilder
     */
    public StatisticsBuilder() {
        totalInternships = new Datapoint(Statistics.TOTAL_INTERNSHIPS_NAME);
        numInterested = new Datapoint(Statistics.NUM_INTERESTED_NAME);
        numApplied = new Datapoint(Statistics.NUM_APPLIED_NAME);
        numOffered = new Datapoint(Statistics.NUM_OFFERED_NAME);
        numRejected = new Datapoint(Statistics.NUM_REJECTED_NAME);
        numEvents = new Datapoint(Statistics.NUM_EVENTS_NAME);
    }

    /**
     * Return a StatisticsBuilder with Total Internship Datapoints
     * @param value
     * @return StatisticsBuilder
     */
    public StatisticsBuilder withTotalInternships(int value) {
        this.totalInternships = new Datapoint(Statistics.TOTAL_INTERNSHIPS_NAME, value);
        return this;
    }

    /**
     * Return a StatisticsBuilder with Number of Internship Interested Datapoints
     * @param value
     * @return StatisticsBuilder
     */
    public StatisticsBuilder withNumInterested(int value) {
        this.numInterested = new Datapoint(Statistics.NUM_INTERESTED_NAME, value);
        return this;
    }

    /**
     * Return a StatisticsBuilder with Number of Internship Applied Datapoints
     * @param value
     * @return StatisticsBuilder
     */
    public StatisticsBuilder withNumApplied(int value) {
        this.numApplied = new Datapoint(Statistics.NUM_APPLIED_NAME, value);
        return this;
    }

    /**
     * Return a StatisticsBuilder with Number of Internship Offered Datapoints
     * @param value
     * @return StatisticsBuilder
     */
    public StatisticsBuilder withNumOffered(int value) {
        this.numOffered = new Datapoint(Statistics.NUM_OFFERED_NAME, value);
        return this;
    }

    /**
     * Return a StatisticsBuilder with Number of Internship Rejected Datapoints
     * @param value
     * @return StatisticsBuilder
     */
    public StatisticsBuilder withNumRejected(int value) {
        this.numRejected = new Datapoint(Statistics.NUM_REJECTED_NAME, value);
        return this;
    }

    /**
     * Return a StatisticsBuilder with Number of Internship Events Datapoints
     * @param value
     * @return StatisticsBuilder
     */
    public StatisticsBuilder withNumEvents(int value) {
        this.numEvents = new Datapoint(Statistics.NUM_EVENTS_NAME, value);
        return this;
    }

    /**
     * Builds the Statistics
     * @return Statistics
     */
    public Statistics build() {
        return (new Statistics(totalInternships.getValue(), numInterested.getValue(),
                numApplied.getValue(), numOffered.getValue(), numRejected.getValue(), numEvents.getValue()));
    }
}

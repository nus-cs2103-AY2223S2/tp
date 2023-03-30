package seedu.internship.model.internship;

import javafx.collections.ObservableList;

import java.util.Objects;

public class Statistics {

    public static final Statistics EMPTY_STATISTICS = new Statistics();

    private int totalInternships;
    private int numApplied;
    private int numOffered;
    private int numRejected;

    private Statistics() {
        totalInternships = 0;
        numApplied = 0;
        numOffered = 0;
        numRejected = 0;
    }

    public Statistics(ObservableList<Internship> internships) {
        this();
        parseInternshipList(internships);
    }

    private void parseInternshipList(ObservableList<Internship> internships) {
        for (Internship internship : internships) {
            switch (internship.getStatusId()) {
                case 1:
                    // applied
                    numApplied += 1;
                    break;
                case 2:
                    numOffered += 1;
                    numApplied += 1;
                    break;
                case 3:
                    numRejected += 1;
                    numApplied += 1;
                    break;
            }

            totalInternships += 1;
        }
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

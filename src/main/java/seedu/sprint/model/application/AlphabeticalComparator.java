package seedu.sprint.model.application;

import java.util.Comparator;

import seedu.sprint.logic.parser.SortCommandParser.SortingSequence;

/**
 * Compares two applications based on alphabetical order of company name and role.
 */
public class AlphabeticalComparator implements Comparator<Application> {

    private final SortingSequence sortingSequence;

    public AlphabeticalComparator(SortingSequence sortingSequence) {
        this.sortingSequence = sortingSequence;
    }

    /**
     * Compares two applications such that the application ahead in terms
     * of alphabetical order for the role will be considered smaller
     * than the other application.
     * @param appOne the first application to be compared.
     * @param appTwo the second application, to be compared to the first one.
     * @return an integer that represents whether the first application is smaller
     *      than the second.
     */
    public int compareAscending(Application appOne, Application appTwo) {
        if (appOne.getRole().toString().compareToIgnoreCase(appTwo.getRole().toString()) > 0) {
            return 1;
        } else if (appOne.getRole().toString().compareToIgnoreCase(appTwo.getRole().toString()) < 0) {
            return -1;
        } else {
            // use company name as tiebreaker
            return Integer.compare(appOne.getCompanyName().toString()
                    .compareToIgnoreCase(appTwo.getCompanyName().toString()), 0);
        }
    }

    @Override
    public int compare(Application appOne, Application appTwo) {
        if (sortingSequence.equals(SortingSequence.ASCENDING)) {
            return compareAscending(appOne, appTwo);
        } else {
            return -compareAscending(appOne, appTwo);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AlphabeticalComparator // instanceof handles nulls
                && sortingSequence.equals(((AlphabeticalComparator) other).sortingSequence)); // sequence check
    }
}

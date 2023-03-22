package seedu.address.model.application;

import java.util.Comparator;

/**
 * Compares two applications based on alphabetical order of company name.
 */
public class AlphabeticalComparator implements Comparator<Application> {

    @Override
    public int compare(Application appOne, Application appTwo) {
        if (appOne.getCompanyName().toString().compareToIgnoreCase(appTwo.getCompanyName().toString()) > 0) {
            return 1;
        } else if (appOne.getCompanyName().toString().compareToIgnoreCase(appTwo.getCompanyName().toString()) < 0) {
            return -1;
        } else {
            // use role as tiebreaker
            return Integer.compare(appOne.getRole().toString().compareToIgnoreCase(appTwo.getRole().toString()), 0);
        }
    }
}

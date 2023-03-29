package seedu.address.model.statstics;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.InternshipApplication;

/**
 *
 */
public class TotalStatsInformation extends StatsInformation {
    private final Model model;
    private final String description = "Total";
    private int numberOfInternshipApplication;

    /**
     *
     * @param model
     */
    public TotalStatsInformation(Model model) {
        this.model = model;
        this.numberOfInternshipApplication = getTotalNumberOfApplication();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getStatsInformation() {
        return numberOfInternshipApplication;
    }

    /**
     *
     * @return
     */
    public int getTotalNumberOfApplication() {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<InternshipApplication> internshipApplications = addressBook.getInternshipList();
        return internshipApplications.size();
    }

    @Override
    public void updateStatsInformation() {
        this.numberOfInternshipApplication = getTotalNumberOfApplication();
    }

    @Override
    public String toString() {
        return "[StatsInformation] " + this.description + ": " + numberOfInternshipApplication;
    }

    /**
     * Returns true if both internship applications have the same description. The number of internship application
     * is not compared for equality check.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TotalStatsInformation // instanceof handles nulls
                && description.equals(((TotalStatsInformation) other).description) // state check
                && numberOfInternshipApplication == ((TotalStatsInformation) other).numberOfInternshipApplication);
    }
}

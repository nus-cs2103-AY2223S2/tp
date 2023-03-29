package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.StatusPredicate;

public class StatusStatsInformation extends StatsInformation {
    private final Model model;
    private final InternshipStatus status;
    private final String description;
    private int numberOfInternshipApplication;

    public StatusStatsInformation(Model model, InternshipStatus status) {
        this.model = model;
        this.status = status;
        this.description = status.toString();
        this.numberOfInternshipApplication = getNumberOfApplicationWithStatus(
            InternshipStatus.valueOf(description));
    }

    @Override
    public String getDescription() {
        return description;
    }

    public int getNumberOfApplicationWithStatus(InternshipStatus status) {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<InternshipApplication> internshipApplications = addressBook.getInternshipList();
        FilteredList<InternshipApplication> filteredList =
                internshipApplications.filtered(new StatusPredicate(status));
        return filteredList.size();
    }

    @Override
    public Integer getStatsInformation() {
        return numberOfInternshipApplication;
    }

    @Override
    public void updateStatsInformation() {
        this.numberOfInternshipApplication = getNumberOfApplicationWithStatus(status);
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
                || (other instanceof StatusStatsInformation // instanceof handles nulls
                && description.equals(((StatusStatsInformation) other).description)); // state check
    }
}

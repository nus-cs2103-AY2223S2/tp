package seedu.address.model.statstics;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipStatus;

/**
 * The main manager of statistics of the internship application status.
 */
public class StatsManager {
    private final Model model;
    private final ObservableList<StatsInformation> statsInformations;
    private final FilteredList<StatsInformation> filteredStatsInformations;

    /**
     * Creates a new StatsManager.
     *
     * @param model The model from which we get the statistics information.
     */
    public StatsManager(Model model) {
        requireNonNull(model);
        this.model = model;
        this.statsInformations = FXCollections.observableArrayList();
        this.filteredStatsInformations = new FilteredList<>(statsInformations);
        this.initStatsInformation();
    }

    /**
     * Initializes the StatsManager.
     */
    private void initStatsInformation() {
        List<InternshipStatus> l = Arrays.asList(InternshipStatus.values());
        StatsInformation total = new TotalStatsInformation(model);
        statsInformations.add(total);
        for (InternshipStatus s: l) {
            statsInformations.add(new StatusStatsInformation(model, s));
        }
    }

    /**
     * Update current statistics information.
     */
    public void updateAllStatsInformation() {
        for (StatsInformation s: statsInformations) {
            s.updateStatsInformation();
        }
    }

    /**
     * Update underlying list of statistics information.
     */
    public void updateFilteredStatsInformationList() {
        filteredStatsInformations.setPredicate(p -> true);
    }

    /**
     * Returns the list of statistics information.
     *
     * @return The list of statistics information.
     */
    public ObservableList<StatsInformation> getStatsInformations() {
        return statsInformations;
    }

    /**
     * Returns the filtered list of statistics information.
     *
     * @return The filtered list of statistics information.
     */
    public FilteredList<StatsInformation> getFilteredStatsInformations() {
        return filteredStatsInformations;
    }
}

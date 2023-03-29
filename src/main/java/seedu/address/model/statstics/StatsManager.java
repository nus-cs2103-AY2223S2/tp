package seedu.address.model.statstics;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipStatus;

public class StatsManager {
    private final Model model;
    private final ObservableList<StatsInformation> statsInformations;
    private final FilteredList<StatsInformation> filteredStatsInformations;

    public StatsManager(Model model) {
        requireNonNull(model);
        this.model = model;
        this.statsInformations = FXCollections.observableArrayList();
        this.filteredStatsInformations = new FilteredList<>(statsInformations);
        this.initStatsInformation();
    }

    private void initStatsInformation() {
        List<InternshipStatus> l = Arrays.asList(InternshipStatus.values());
        StatsInformation total = new TotalStatsInformation(model);
        statsInformations.add(total);
        for (InternshipStatus s: l) {
            statsInformations.add(new StatusStatsInformation(model, s));
        }
    }

    public void updateAllStatsInformation() {
        for (StatsInformation s: statsInformations) {
            s.updateStatsInformation();
        }
    }

    public void updateFilteredStatsInformationList() {
        filteredStatsInformations.setPredicate(p -> true);
    }

    public FilteredList<StatsInformation> getFilteredStatsInformations() {
        return filteredStatsInformations;
    }
}

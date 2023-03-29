package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.InternshipStatus;

public class StatsManager {
    private final Model model;
    private final ObservableList<StatsInformation> statsInformations;

    public StatsManager(Model model) {
        requireNonNull(model);
        this.model = model;
        this.statsInformations = FXCollections.observableArrayList();
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

    public ObservableList<StatsInformation> getStatsInformations() {
        return statsInformations;
    }
}

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.InternshipStatus;

public class StatsManager {
    private final Model model;
    private List<StatsInformation> statsInformations;

    public StatsManager(Model model) {
        requireNonNull(model);
        this.model = model;
        this.statsInformations = new ArrayList<>();
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
}

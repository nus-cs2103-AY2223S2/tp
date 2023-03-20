package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.Summary;
import seedu.address.logic.aggregatefunction.Count;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * Summarises the information of all elderly, volunteers and pairs in FriendlyLink.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";
    public static final String ELDERLY_COUNT = "Elderly count";
    public static final String UNPAIRED_ELDERLY_COUNT = "Unpaired elderly count";
    public static final String VOLUNTEER_COUNT = "Volunteer count";
    public static final String UNPAIRED_VOLUNTEER_COUNT = "Unpaired volunteer count";
    public static final String PAIR_COUNT = "Pair count";

    private final Summary summary = new Summary();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        summariseElderlyStatistics(model);
        summariseVolunteerStatistics(model);
        summarisePairStatistics(model);

        return new CommandResult(summary.toString());
    }

    private void summariseElderlyStatistics(Model model) {
        summary.describe(new Count<>(model.getFilteredElderlyList(), ELDERLY_COUNT));

        List<Elderly> pairElderly = model.getFilteredPairList().stream()
                .map(pair -> pair.getElderly()).collect(Collectors.toList());
        summary.describe(new Count<>(model.getFilteredElderlyList(), UNPAIRED_ELDERLY_COUNT)
                .with(elderly -> !pairElderly.contains(elderly)));
    }

    private void summariseVolunteerStatistics(Model model) {
        summary.describe(new Count<>(model.getFilteredVolunteerList(), VOLUNTEER_COUNT));

        List<Volunteer> pairVolunteers = model.getFilteredPairList().stream()
                .map(pair -> pair.getVolunteer()).collect(Collectors.toList());
        summary.describe(new Count<>(model.getFilteredVolunteerList(), UNPAIRED_VOLUNTEER_COUNT)
                .with(volunteer -> !pairVolunteers.contains(volunteer)));

    }

    private void summarisePairStatistics(Model model) {
        summary.describe(new Count<>(model.getFilteredPairList(), PAIR_COUNT));
    }
}

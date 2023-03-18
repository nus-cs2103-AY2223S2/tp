package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Summary;
import seedu.address.logic.aggregatefunction.Count;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * Summarises the information of all elderly, volunteers and pairs in FriendlyLink.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    private final Summary summary = new Summary();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        summariseElderlyStatistics(model);
        summariseVolunteerStatistics(model);
        summarisePairStatistics(model);

        /*model.updateFilteredElderlyList((Predicate<Elderly>) PREDICATE_SHOW_ALL);
        model.updateFilteredVolunteerList((Predicate<Volunteer>) PREDICATE_SHOW_ALL);
        model.updateFilteredPairList((Predicate<Pair>) PREDICATE_SHOW_ALL);*/
        return new CommandResult(summary.toString());
    }

    private void summariseElderlyStatistics(Model model) {
        summary.describe(new Count<>(model.getFilteredElderlyList(), Elderly.class));
    }

    private void summariseVolunteerStatistics(Model model) {
        summary.describe(new Count<>(model.getFilteredVolunteerList(), Volunteer.class));
    }

    private void summarisePairStatistics(Model model) {
        summary.describe(new Count<>(model.getFilteredPairList(), Pair.class));
    }
}

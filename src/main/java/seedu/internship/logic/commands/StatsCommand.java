package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.internship.model.Model;
import seedu.internship.model.internship.Statistics;

/**
 * Generates useful statistics on current internship application progress for display.
 */

public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display useful statistics on application progress.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_STATS_MESSAGE = "Showing statistics.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Statistics statistics = new Statistics(model.getInternshipCatalogue().getInternshipList(),
                model.getEventCatalogue().getEventList());
        model.updateSelectedInternship(null);
        return new CommandResult(SHOWING_STATS_MESSAGE, ResultType.STATS, statistics);
    }
}

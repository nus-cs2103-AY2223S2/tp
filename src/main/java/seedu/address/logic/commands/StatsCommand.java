package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Summarises the information of all elderly, volunteers and pairs in FriendlyLink.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Statistics:\n%1$s\n%2$s\n%3$s";
    public static final String ELDERLY_STATISTICS = "Total elderly: %1$d\n";
    public static final String VOLUNTEER_STATISTICS = "Total volunteers: %1$d\n";
    public static final String PAIR_STATISTICS = "Total pairs: %1$d\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, getElderlyStatistics(model),
                getVolunteerStatistics(model), getPairStatistics(model)));
    }

    private String getElderlyStatistics(Model model) {
        return String.format(ELDERLY_STATISTICS, model.getFilteredElderlyList().size());
    }

    private String getVolunteerStatistics(Model model) {
        return String.format(VOLUNTEER_STATISTICS, model.getFilteredVolunteerList().size());
    }

    private String getPairStatistics(Model model) {
        return String.format(PAIR_STATISTICS, model.getFilteredPairList().size());
    }
}

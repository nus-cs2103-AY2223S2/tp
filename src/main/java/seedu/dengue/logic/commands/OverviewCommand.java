package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dengue.model.Model;
import seedu.dengue.model.overview.Overview;

/**
 * Changes the overview type of the Dengue Hotspot Tracker.
 */
public class OverviewCommand extends Command {

    public static final String COMMAND_WORD = "overview";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the overview type to one of the following: "
            + "POSTAL (default), "
            + "AGE, or "
            + "VARIANT. "
            + "Example: " + COMMAND_WORD + " age ";

    public static final String MESSAGE_SUCCESS = "Changed overview type to: %s";

    private final Overview overview;
    private final String type;

    public OverviewCommand(Overview blankOverview, String type) {
        requireNonNull(blankOverview);
        this.overview = blankOverview;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setOverview(this.overview);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.type));
    }
}

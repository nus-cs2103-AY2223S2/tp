package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import seedu.dengue.model.Model;
import seedu.dengue.model.overview.Overview;

/**
 * Changes the overview type of the Dengue Hotspot Tracker.
 */
public class OverviewCommand extends Command {

    public static final String COMMAND_WORD = "overview";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the overview type to one of the following: "
            + "'" + PREFIX_POSTAL + "' for Postal (default), "
            + "'" + PREFIX_AGE + "' for Age, or "
            + "'" + PREFIX_VARIANT + "' for Variant. "
            + "Example: " + COMMAND_WORD + " a/ ";

    public static final String MESSAGE_SUCCESS = "Changed overview type to: %s";

    private final Overview overview;
    private final String type;

    /**
     * Creates an {@code OverviewCommand} to change the overview to the given type.
     *
     * @param blankOverview An empty {@code Overview}.
     * @param type The type of the overview.
     */
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof OverviewCommand)) {
            return false;
        }

        // state check
        OverviewCommand other = (OverviewCommand) obj;
        return type.equals(other.type);
    }
}

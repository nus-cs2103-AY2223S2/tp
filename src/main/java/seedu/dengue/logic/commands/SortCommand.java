package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.overview.Overview;

/**
 * Sorts the Dengue Hotspot Tracker.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the view by one of the following: "
            + "'" + PREFIX_POSTAL + "' for Postal, "
            + "'" + PREFIX_AGE + "' for Age, "
            + "'" + PREFIX_DATE + "' for Date, or "
            + "'" + PREFIX_VARIANT + "' for Variant. "
            + "Example: " + COMMAND_WORD + " d/ ";

    public static final String MESSAGE_SUCCESS = "Sorted by: %s";

    private final String type;

    /**
     * Creates a {@code SortCommand} to sort the list by the given parameter.
     *
     * @param type The type of sort.
     */
    public SortCommand(String type) {
        requireNonNull(type);
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sort(this.type);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.type));
    }
}

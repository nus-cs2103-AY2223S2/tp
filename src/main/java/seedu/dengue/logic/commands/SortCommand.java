package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.person.Person;

/**
 * Sorts the Dengue Hotspot Tracker.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the view by one of the following: "
            + "'" + PREFIX_NAME + "' for Name, "
            + "'" + PREFIX_AGE + "' for Age, "
            + "'" + PREFIX_POSTAL + "' for Postal, or "
            + "'" + PREFIX_DATE + "' for Date."
            + "Example: " + COMMAND_WORD + " d/ ";

    public static final String MESSAGE_SUCCESS = "Sorted by: %s";
    private final Comparator<Person> comparator;

    private final String type;

    /**
     * Creates a {@code SortCommand} to sort the list by the given parameter.
     *
     * @param type The type of sort.
     */
    public SortCommand(Comparator<Person> comparator, String type) {
        requireNonNull(type);
        this.comparator = comparator;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(comparator);
        model.sort(toSort);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.type));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand s = (SortCommand) other;
        return type.equals(s.type);
    }
}

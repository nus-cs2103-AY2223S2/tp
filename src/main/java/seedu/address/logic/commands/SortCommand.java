package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons according to a specified field "
            + "(case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: asc [desc, name, priority, trans]...\n"
            + "Example: " + COMMAND_WORD + " trans \n"
            + "Note that only 2 arguments may be specified at one time. One for ";


    private final String subCommand;
    private final String direction;

    private final boolean ascending;


    public SortCommand(String subCommand, String direction) {
        //TODO assert subcommand and direction is correct
        this.subCommand = subCommand;
        this.direction = direction;
        this.ascending = direction.equals("asc");
    }

    public static final String MESSAGE_SUCCESS = "sorted by: ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(subCommand, ascending);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS + this.subCommand + " " + this.direction);
    }
}

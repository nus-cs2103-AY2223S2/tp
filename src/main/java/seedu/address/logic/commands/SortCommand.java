package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons according to a specified field "
            + "(case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [size, name, priority, trans] [asc, desc]...\n"
            + "Example: " + COMMAND_WORD + " trans \n"
            + "Note that only 2 arguments may be specified at one time. "
            + "One specifying the field, the other, direction.";

    public static final String MESSAGE_SUCCESS = "Sorted by: ";
    private final String subCommand;
    private final String direction;
    private final boolean ascending;

    /**
     * Creates an SortCommand to sort the list {@code Person}
     */
    public SortCommand(String subCommand, String direction) {

        this.subCommand = subCommand.toLowerCase();
        this.direction = direction.toLowerCase();
        ArrayList<String> subCommands = new ArrayList<>(List.of("name", "size", "trans", "priority"));
        ArrayList<String> directions = new ArrayList<>(List.of("asc", "desc"));
        assert subCommands.contains(this.subCommand) : "no such field to be sorted";
        assert directions.contains(this.direction) : "no such direction available";
        this.ascending = direction.equalsIgnoreCase("asc");
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(subCommand, ascending);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS + this.subCommand + " " + this.direction);
    }
}

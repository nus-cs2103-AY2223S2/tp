package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
public class SortStudentCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Listed all persons in sorted order";

    public static String group;
    public static String metric;
    public static boolean increasingOrder;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all address book students.\n "
            + "Parameters: The order you want (either 'reverse' or 'nonreverse' "
            + PREFIX_SORT + "[SORT]\n"
            + "Example: " + COMMAND_WORD + " reverse "
            + PREFIX_SORT + "Likes to swim.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Sort command not implemented yet";

    public SortStudentCommand(String group, String metric, boolean increasingOrder) {
        this.group = group;
        this.metric = metric;
        this.increasingOrder = increasingOrder;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

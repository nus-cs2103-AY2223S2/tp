package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.logic.parser.OrderParser;
import seedu.techtrack.model.Model;


/**
 * Sort a role based on the salary in asc/desc order.
 */
public class SalaryCommand extends Command {

    public static final String COMMAND_WORD = "salary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the salary in asc/desc order.\n \n"
            + "Parameters: ORDER (must be either asc OR desc)\n \n"
            + "Example: " + COMMAND_WORD + " desc";
    private OrderParser orderParser;

    public SalaryCommand(OrderParser orderParser) {
        this.orderParser = orderParser;
    }

    @Override
    public CommandResult<String> execute(Model model) throws CommandException {
        requireNonNull(model);
        model.displaySortedSalaryList(this.orderParser);
        return new CommandResult<>(String.format(Messages.MESSAGE_SALARY_COMMAND_FORMAT, this.orderParser.toString()));
    }
}

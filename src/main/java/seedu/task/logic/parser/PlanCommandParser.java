package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.task.logic.commands.PlanCommand;
import seedu.task.logic.parser.exceptions.ParseException;

/**
 * Parses arguments and creates a new PlanCommand Object.
 */
public class PlanCommandParser implements Parser<PlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PlanCommand
     * and returns a PlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PlanCommand parse(String args) throws ParseException {
        try {
            int workload = ParserUtil.parseWorkload(args);
            return new PlanCommand(workload);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlanCommand.MESSAGE_USAGE), pe);
        }
    }
}

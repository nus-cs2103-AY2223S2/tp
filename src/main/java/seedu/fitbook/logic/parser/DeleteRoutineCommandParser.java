package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.DeleteRoutineCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRoutineCommand object
 */
public class DeleteRoutineCommandParser implements Parser<DeleteRoutineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRoutineCommand
     * and returns a DeleteRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteRoutineCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteRoutineCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRoutineCommand.MESSAGE_USAGE), pe);
        }
    }

}

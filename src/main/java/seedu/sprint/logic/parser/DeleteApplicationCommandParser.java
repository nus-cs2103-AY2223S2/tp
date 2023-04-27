package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.commands.DeleteApplicationCommand;
import seedu.sprint.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteApplicationCommand object.
 */
public class DeleteApplicationCommandParser implements Parser<DeleteApplicationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicationCommand
     * and returns a DeleteApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteApplicationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteApplicationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicationCommand.MESSAGE_USAGE), pe);
        }
    }
}

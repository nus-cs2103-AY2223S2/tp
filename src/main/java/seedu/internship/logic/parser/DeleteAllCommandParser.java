package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.internship.logic.commands.DeleteAllCommand;
import seedu.internship.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteAllCommand object.
 */
public class DeleteAllCommandParser implements Parser<DeleteAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAllCommand
     * and returns a DeleteAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteAllCommand parse(String args) throws ParseException {
        String code = args.trim();
        if (code.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAllCommand.MESSAGE_USAGE));
        }
        return new DeleteAllCommand(code);
    }

}

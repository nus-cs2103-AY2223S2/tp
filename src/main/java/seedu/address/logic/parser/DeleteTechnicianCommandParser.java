package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteTechnicianCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTechnicianCommand object
 */
public class DeleteTechnicianCommandParser implements Parser<DeleteTechnicianCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCustomerCommand
     * and returns a DeleteCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTechnicianCommand parse(String args) throws ParseException {
        try {
            int id = ParserUtil.parseInt(args);
            return new DeleteTechnicianCommand(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTechnicianCommand.MESSAGE_USAGE), pe);
        }
    }

}

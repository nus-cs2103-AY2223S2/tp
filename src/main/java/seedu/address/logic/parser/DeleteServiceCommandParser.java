package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteVehicleCommand object
 */
public class DeleteServiceCommandParser implements Parser<DeleteServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteVehicleCommand
     * and returns a DeleteVehicleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteServiceCommand parse(String args) throws ParseException {
        try {
            int id = ParserUtil.parseInt(args);
            return new DeleteServiceCommand(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE), pe);
        }
    }

}

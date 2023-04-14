package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetPictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.EmployeeId;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SetPictureCommandParser implements Parser<SetPictureCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetPictureCommand parse(String args) throws ParseException {
        try {
            EmployeeId index = ParserUtil.parseEmployeeId(args);
            return new SetPictureCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPictureCommand.MESSAGE_USAGE), pe);
        }
    }

}

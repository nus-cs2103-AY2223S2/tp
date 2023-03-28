package seedu.patientist.logic.parser;

import seedu.patientist.logic.commands.DeleteStaffCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.IdNumber;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteStaffCommand object
 */
public class DeleteStaffCommandParser implements Parser<DeleteStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStaffCommand
     * and returns a DeleteStaffCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteStaffCommand parse(String userInput) throws ParseException {
        try {
            IdNumber idNumber = ParserUtil.parseId(userInput);
            return new DeleteStaffCommand(idNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE), pe);
        }
    }
}

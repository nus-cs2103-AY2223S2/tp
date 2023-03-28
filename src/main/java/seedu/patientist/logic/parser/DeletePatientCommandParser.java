package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.patientist.logic.commands.DeletePatientCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.IdNumber;

/**
 * Parses input arguments and creates a new DeletePatientCommand object
 */
public class DeletePatientCommandParser implements Parser<DeletePatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePatientCommand
     * and returns a DeletePatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeletePatientCommand parse(String userInput) throws ParseException {
        try {
            IdNumber idNumber = ParserUtil.parseId(userInput);
            return new DeletePatientCommand(idNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE), pe);
        }
    }
}

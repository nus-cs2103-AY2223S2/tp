package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectPatientCommand object
 */
public class SelectPatientCommandParser implements Parser<SelectPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectPatientCommand
     * and returns a SelectPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectPatientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectPatientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectPatientCommand.getCommandUsage()), pe);
        }
    }

}

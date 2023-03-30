package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.patientist.logic.commands.ListWardPatientsCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.ward.Ward;

/**
 * Finds and lists all patients in patientist book whose ward contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ListWardPatientsCommandParser implements Parser<ListWardPatientsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListWardPatientsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !Ward.isValidWardName(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListWardPatientsCommand.MESSAGE_USAGE));
        }

        return new ListWardPatientsCommand(trimmedArgs);
    }
}

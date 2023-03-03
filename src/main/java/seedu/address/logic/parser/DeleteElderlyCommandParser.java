package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.information.Nric.VALIDATION_REGEX;

import seedu.address.logic.commands.DeleteElderlyCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteElderlyCommand object
 */
public class DeleteElderlyCommandParser implements Parser<DeleteElderlyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteElderlyCommand
     * and returns a DeleteElderlyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteElderlyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || (!trimmedArgs.matches(VALIDATION_REGEX))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new DeleteElderlyCommand(trimmedArgs);
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.information.Nric.VALIDATION_REGEX;

import seedu.address.logic.commands.DeleteElderlyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Nric;

/**
 * Parses input arguments and creates a new DeleteElderlyCommand object.
 */
public class DeleteElderlyCommandParser implements Parser<DeleteElderlyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteElderlyCommand
     * and returns a DeleteElderlyCommand object for execution.
     *
     * @param args Arguments.
     * @return {@code DeleteElderlyCommand} for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteElderlyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || (!trimmedArgs.matches(VALIDATION_REGEX))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteElderlyCommand.MESSAGE_USAGE));
        }
        Nric nric = new Nric(trimmedArgs);
        return new DeleteElderlyCommand(nric);
    }

    public static boolean validate(ArgumentMultimap map) {
        return !(map.getPreamble().split(" ").length > 1);
    }
}

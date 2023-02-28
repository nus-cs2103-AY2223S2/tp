package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;

import seedu.address.logic.commands.DeleteVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Nric;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteVolunteerCommandParser implements Parser<DeleteVolunteerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteVolunteerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC_VOLUNTEER);
        if (argMultimap.getValue(PREFIX_NRIC_VOLUNTEER).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteVolunteerCommand.MESSAGE_USAGE));
        }
        Nric nric = new Nric(argMultimap.getValue(PREFIX_NRIC_VOLUNTEER).get());
        return new DeleteVolunteerCommand(nric);
    }

}

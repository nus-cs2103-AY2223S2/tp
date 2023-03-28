package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.patientist.logic.commands.AddWardCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.ward.Ward;

/**
 * Parses input arguments and creates a new AddWardCommand object
 */
public class AddWardCommandParser implements Parser<AddWardCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddWardCommand and returns an AddCommand
     * object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddWardCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWardCommand.MESSAGE_USAGE));
        }

        Ward ward = ParserUtil.parseWard(argMultimap.getValue(PREFIX_NAME).get());

        return new AddWardCommand(ward);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

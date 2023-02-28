package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.AddPairCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Nric;

/**
 * Parses input arguments and creates a new AddPairCommand object
 */
public class AddPairCommandParser implements Parser<AddPairCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPairCommand
     * and returns an AddPairCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPairCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC_ELDERLY, PREFIX_NRIC_VOLUNTEER);

        if (!PrefixUtil.arePrefixesPresent(argMultimap, PREFIX_NRIC_ELDERLY, PREFIX_NRIC_VOLUNTEER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPairCommand.MESSAGE_USAGE));
        }

        Nric elderlyNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC_ELDERLY).get());
        Nric volunteerNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC_VOLUNTEER).get());

        return new AddPairCommand(elderlyNric, volunteerNric);
    }

}

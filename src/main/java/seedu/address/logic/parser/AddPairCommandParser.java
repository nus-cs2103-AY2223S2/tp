package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;

import java.util.List;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.AddPairCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Nric;

/**
 * Parses input arguments and creates a new AddPairCommand object.
 */
public class AddPairCommandParser implements Parser<AddPairCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPairCommand
     * and returns an AddPairCommand object for execution.
     *
     * @param args Arguments.
     * @return {@code AddPairCommand} for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AddPairCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC_ELDERLY, PREFIX_NRIC_VOLUNTEER);

        if (!PrefixUtil.arePrefixesPresent(argMultimap, PREFIX_NRIC_ELDERLY, PREFIX_NRIC_VOLUNTEER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPairCommand.MESSAGE_USAGE));
        }

        Nric elderlyNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC_ELDERLY).orElse(""));
        Nric volunteerNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC_VOLUNTEER).orElse(""));

        return new AddPairCommand(elderlyNric, volunteerNric);
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) {
        return !(map.getArrayValue(PREFIX_NRIC_VOLUNTEER).orElse(List.of()).size() > 1)
                && !(map.getArrayValue(PREFIX_NRIC_ELDERLY).orElse(List.of()).size() > 1);
    }

}

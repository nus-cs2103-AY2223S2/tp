package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_BOTH_INVALID_NRIC;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.model.person.information.Nric.MESSAGE_CONSTRAINTS;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.AddPairCommand;
import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Nric;

/**
 * Parses input arguments and creates a new AddPairCommand object.
 */
public class AddPairCommandParser implements Parser<AddPairCommand> {

    private static final Prefix[] availablePrefixes = { PREFIX_NRIC_ELDERLY, PREFIX_NRIC_VOLUNTEER };

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
                ArgumentTokenizer.tokenize(args, availablePrefixes);

        if (!PrefixUtil.arePrefixesPresent(argMultimap, availablePrefixes)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPairCommand.MESSAGE_USAGE));
        }

        String elderlyNric = argMultimap.getValue(PREFIX_NRIC_ELDERLY).orElse("");
        String volunteerNric = argMultimap.getValue(PREFIX_NRIC_VOLUNTEER).orElse("");
        if (!Nric.isValidNric(elderlyNric) && !Nric.isValidNric(volunteerNric)) {
            throw new ParseException(
                    String.format(MESSAGE_BOTH_INVALID_NRIC, MESSAGE_CONSTRAINTS));
        } else if (!Nric.isValidNric(elderlyNric)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_PERSON_NRIC, "elderly", MESSAGE_CONSTRAINTS));
        } else if (!Nric.isValidNric(volunteerNric)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_PERSON_NRIC, "volunteer", MESSAGE_CONSTRAINTS));
        }

        return new AddPairCommand(new Nric(elderlyNric), new Nric(volunteerNric));
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                AddPairCommand.COMMAND_WORD,
                AddPairCommand.COMMAND_PROMPTS,
                AddPairCommandParser::validate);
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) throws RecommendationException {
        if (PrefixUtil.checkIfContainsInvalidPrefixes(map)) {
            throw new RecommendationException("Invalid prefix.");
        }
        return true;
    }

}

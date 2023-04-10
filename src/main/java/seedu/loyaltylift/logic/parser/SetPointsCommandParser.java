package seedu.loyaltylift.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;

import java.util.stream.Stream;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.SetPointsCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.customer.Points;

/**
 * Parses input arguments and creates a new SetPointsCommand object
 */
public class SetPointsCommandParser implements Parser<SetPointsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SetPointsCommand
     * and returns a SetPointsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetPointsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POINTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_POINTS)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetPointsCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Points points = ParserUtil.parsePoints((argMultimap.getValue(PREFIX_POINTS).get()));

        return new SetPointsCommand(index, points);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

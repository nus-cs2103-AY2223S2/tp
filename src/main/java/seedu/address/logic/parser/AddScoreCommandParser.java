package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_VALUE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreValue;


/**
 * Parses input arguments and creates a new AddScoreCommand object
 */
public class AddScoreCommandParser implements Parser<AddScoreCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddScoreCommand
     * and returns an AddScoreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScoreCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SCORE_LABEL, PREFIX_SCORE_VALUE, PREFIX_SCORE_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_SCORE_LABEL, PREFIX_SCORE_VALUE, PREFIX_SCORE_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE));
        }

        Label label = ParserUtil.parseScoreLabel(argMultimap.getValue(PREFIX_SCORE_LABEL).get());
        ScoreValue value = ParserUtil.parseScoreValue(argMultimap.getValue(PREFIX_SCORE_VALUE).get());
        Date date = ParserUtil.parseScoreDate(argMultimap.getValue(PREFIX_SCORE_DATE).get());

        Score score = new Score(label, value, date);
        return new AddScoreCommand(index, score);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

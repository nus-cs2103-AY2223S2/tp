package seedu.loyaltylift.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.commons.exceptions.IllegalValueException;
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

        Index index;
        Integer points;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            points = Integer.valueOf(argMultimap.getValue(PREFIX_POINTS).orElse(""));
        } catch (IllegalValueException | NumberFormatException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetPointsCommand.MESSAGE_USAGE), ive);
        }

        return new SetPointsCommand(index, new Points(points));
    }
}

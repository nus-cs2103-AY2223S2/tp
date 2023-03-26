package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.stream.Stream;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.AddWeightCommand;
import seedu.fitbook.logic.commands.EditCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Date;
import seedu.fitbook.model.client.Weight;

/**
 * Parses input arguments and creates a new AddRoutineCommand object
 */
public class AddWeightCommandParser implements Parser<AddWeightCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoutineCommand
     * and returns an AddRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddWeightCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEIGHT, PREFIX_DATE);
        if (!arePrefixesPresent(argMultimap, PREFIX_WEIGHT, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeightCommand.MESSAGE_USAGE));
        }
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeightCommand.MESSAGE_USAGE), pe);
        }

        //handle invalid input here
        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        return new AddWeightCommand(index, weight, date);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

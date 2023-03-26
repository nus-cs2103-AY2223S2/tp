package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;

import java.util.stream.Stream;

import seedu.fitbook.logic.commands.AddWeightCommand;
import seedu.fitbook.logic.commands.GraphCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddRoutineCommand object
 */
public class GraphCommandParser implements Parser<GraphCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoutineCommand
     * and returns an AddRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GraphCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_INDEX);
        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeightCommand.MESSAGE_USAGE));
        }

        //handle invalid input here
        int clientIndex = Integer.parseInt(argMultimap.getValue(PREFIX_CLIENT_INDEX).get().trim());

        return new GraphCommand(clientIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.parser;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReviewCommand;
import seedu.address.logic.commands.ReviewTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

/**
 * Parses input arguments and creates a new ReviewTaskCommand object
 */
public class ReviewTaskCommandParser implements Parser<ReviewTaskCommand>{
    /**
     * Parses the given {@code String} of arguments in the context of the ReviewTaskCommand
     * and returns an ReviewTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReviewTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviewTaskCommand.MESSAGE_USAGE));
        }

        try {
            Index taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
            return new ReviewTaskCommand(taskIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviewTaskCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

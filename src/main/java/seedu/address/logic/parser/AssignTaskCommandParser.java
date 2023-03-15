package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AssignTask object
 */
public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskCommand
     * and returns an AssignTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_INDEX, PREFIX_PERSON_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_INDEX, PREFIX_PERSON_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));
        }

        Index taskIndex = ParserUtil.parseTaskIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
        Index personIndex = ParserUtil.parsePersonIndex(argMultimap.getValue(PREFIX_PERSON_INDEX).get());
        return new AssignTaskCommand(taskIndex, personIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}

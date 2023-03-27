package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_SUBTASK_INDEX;

import java.util.stream.Stream;

import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.DeleteSubtaskCommand;
import seedu.task.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class DeleteSubtaskParser implements Parser<DeleteSubtaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution with one or more tasks.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSubtaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_SUBTASK_INDEX);


        if (!arePrefixesPresent(argMultimap, PREFIX_SUBTASK_INDEX)
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteSubtaskCommand.MESSAGE_USAGE));
        }

        Index index;
        Index subtaskIndex;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            subtaskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SUBTASK_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteSubtaskCommand.MESSAGE_USAGE), pe);
        }


        return new DeleteSubtaskCommand(index, subtaskIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


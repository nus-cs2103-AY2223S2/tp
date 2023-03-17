package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Comment;


/**
 * Parses input arguments and creates a new AssignTask object
 */
public class CommentCommandParser implements Parser<CommentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskCommand
     * and returns an AssignTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_INDEX, PREFIX_COMMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_INDEX, PREFIX_COMMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE));
        }

        Index taskIndex = ParserUtil.parseTaskIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
        Comment comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get());
        return new CommentCommand(taskIndex, comment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}


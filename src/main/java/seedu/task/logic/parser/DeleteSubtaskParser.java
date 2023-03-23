package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_EFFORT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_SUBTASK_INDEX;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.DeleteSubtaskCommand;
import seedu.task.logic.commands.EditCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Event;
import seedu.task.model.task.Name;
import seedu.task.model.task.SimpleTask;
import seedu.task.model.task.Subtask;
import seedu.task.model.task.Task;


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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSubtaskCommand.MESSAGE_USAGE));
        }

        Index index;
        Index subtaskIndex;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            subtaskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SUBTASK_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSubtaskCommand.MESSAGE_USAGE), pe);
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


package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_DEADLINE_INCORRECT_FORMAT;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_EVENT_INCORRECT_FORMAT;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_TASK_TYPE_MISMATCH;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_TODO_INCORRECT_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKTYPE;
import static seedu.address.logic.parser.ParserUtil.parseDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASKTYPE, PREFIX_TASK, PREFIX_COMMENT,
                        PREFIX_DATE, PREFIX_START_DATE, PREFIX_END_DATE);

        Index index;
        String taskType;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
            if (!argumentMultimap.getValue(PREFIX_TASKTYPE).isPresent()) {
                throw new ParseException("");
            }
            taskType = ParserUtil.parseTaskType(argumentMultimap.getValue(PREFIX_TASKTYPE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();

        editTaskDescriptor.setTaskType(ParserUtil.parseTaskType(argumentMultimap.getValue(PREFIX_TASKTYPE).get()));

        if (argumentMultimap.getValue(PREFIX_TASK).isPresent()) {
            editTaskDescriptor.setTaskDescription(ParserUtil
                    .parseTaskDescription(argumentMultimap.getValue(PREFIX_TASK).get()));
        }
        if (argumentMultimap.getValue(PREFIX_COMMENT).isPresent()) {
            editTaskDescriptor.setComment(ParserUtil.parseComment(argumentMultimap.getValue(PREFIX_COMMENT).get()));
        }
        if (taskType.equals("T")) {
            if (argumentMultimap.getValue(PREFIX_DATE).isPresent()
                    || argumentMultimap.getValue(PREFIX_START_DATE).isPresent()
                    || argumentMultimap.getValue(PREFIX_END_DATE).isPresent()) {
                throw new ParseException(String.format(MESSAGE_TASK_TYPE_MISMATCH + MESSAGE_TODO_INCORRECT_FORMAT));
            }
        }
        if (taskType.equals("D")) {
            if (argumentMultimap.getValue(PREFIX_START_DATE).isPresent()
                    || argumentMultimap.getValue(PREFIX_END_DATE).isPresent()) {
                throw new ParseException(String.format(MESSAGE_TASK_TYPE_MISMATCH));
            } else {
                if (argumentMultimap.getValue(PREFIX_DATE).isPresent()) {
                    editTaskDescriptor.setDeadline(parseDate(argumentMultimap.getValue(PREFIX_DATE).get()).toString());
                } else {
                    throw new ParseException(MESSAGE_TASK_TYPE_MISMATCH + MESSAGE_DEADLINE_INCORRECT_FORMAT);
                }
            }
        }
        if (taskType.equals("E")) {
            if (argumentMultimap.getValue(PREFIX_DATE).isPresent()) {
                throw new ParseException(String.format(MESSAGE_TASK_TYPE_MISMATCH));
            } else {
                if (!argumentMultimap.getValue(PREFIX_START_DATE).isPresent()
                        && !argumentMultimap.getValue(PREFIX_END_DATE).isPresent()) {
                    throw new ParseException(MESSAGE_TASK_TYPE_MISMATCH + MESSAGE_EVENT_INCORRECT_FORMAT);
                }
                if (argumentMultimap.getValue(PREFIX_START_DATE).isPresent()) {
                    editTaskDescriptor.setStartDate(parseDate(argumentMultimap.getValue(PREFIX_START_DATE).get()));
                }
                if (argumentMultimap.getValue(PREFIX_END_DATE).isPresent()) {
                    editTaskDescriptor.setEndDate(parseDate(argumentMultimap.getValue(PREFIX_END_DATE).get()));
                }
            }
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}

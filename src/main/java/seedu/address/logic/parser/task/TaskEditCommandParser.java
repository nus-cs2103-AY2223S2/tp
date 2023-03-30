package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TaskEditCommand;
import seedu.address.logic.commands.task.TaskEditCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TaskEditCommandParser object
 */
public class TaskEditCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskEditCommand
     * and returns an TaskEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_TANK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_TANK).isPresent()) {
            editTaskDescriptor.setTankIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TANK)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editTaskDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY)
                    .get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(TaskEditCommand.MESSAGE_NOT_EDITED);
        }

        return new TaskEditCommand(index, editTaskDescriptor);

    }
}

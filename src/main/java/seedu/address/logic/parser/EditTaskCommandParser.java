package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.ParseIndexException;


/**
 * Parses input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_CONTENT, PREFIX_STATUS,
                PREFIX_TASK_DEADLINE);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseIndexException pie) {
            throw new ParseIndexException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, pie);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editTaskDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTENT).isPresent()) {
            editTaskDescriptor.setContent(ParserUtil.parseContent(argMultimap.getValue(PREFIX_CONTENT).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editTaskDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_TASK_DEADLINE).get()));
        }
        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}

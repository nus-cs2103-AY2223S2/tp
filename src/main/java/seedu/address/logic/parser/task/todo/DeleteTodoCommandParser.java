package seedu.address.logic.parser.task.todo;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.task.todo.DeleteTodoCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteTodoCommand object
 */
public class DeleteTodoCommandParser implements Parser<DeleteTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTodoCommand
     * and returns a DeleteTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTodoCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTodoCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTodoCommand.MESSAGE_USAGE), pe);
        }
    }

}

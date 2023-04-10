package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.task.note.AddNoteCommand;
import seedu.address.logic.commands.task.note.ClearNoteCommand;
import seedu.address.logic.commands.task.note.DeleteNoteCommand;
import seedu.address.logic.commands.task.note.ListNoteCommand;
import seedu.address.logic.commands.task.todo.AddTodoCommand;
import seedu.address.logic.commands.task.todo.ClearTodoCommand;
import seedu.address.logic.commands.task.todo.DeleteTodoCommand;
import seedu.address.logic.commands.task.todo.EditDeadlineCommand;
import seedu.address.logic.commands.task.todo.EditNoteContentCommand;
import seedu.address.logic.commands.task.todo.ListTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.task.note.AddNoteCommandParser;
import seedu.address.logic.parser.task.note.DeleteNoteCommandParser;
import seedu.address.logic.parser.task.todo.AddTodoCommandParser;
import seedu.address.logic.parser.task.todo.DeleteTodoCommandParser;
import seedu.address.logic.parser.task.todo.EditContentCommandParser;
import seedu.address.logic.parser.task.todo.EditDeadlineCommandParser;

/**
 * Parses user inputs related to the task package.
 */
public class TaskParser {

    /**
     * Parses user input into task related command according to the {@code commandWord} with the {@code arguments} for
     * execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseTaskCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case AddTodoCommand.COMMAND_WORD:
        case ListTodoCommand.COMMAND_WORD:
        case EditDeadlineCommand.COMMAND_WORD:
        case EditNoteContentCommand.COMMAND_WORD:
        case DeleteTodoCommand.COMMAND_WORD:
        case ClearTodoCommand.COMMAND_WORD:
            return parseTodoCommand(commandWord, arguments);

        case AddNoteCommand.COMMAND_WORD:
        case ListNoteCommand.COMMAND_WORD:
        case DeleteNoteCommand.COMMAND_WORD:
        case ClearNoteCommand.COMMAND_WORD:
            return parseNoteCommand(commandWord, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseTodoCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddTodoCommand.COMMAND_WORD:
            return new AddTodoCommandParser().parse(arguments);

        case ListTodoCommand.COMMAND_WORD:
            return new ListTodoCommand();

        case EditDeadlineCommand.COMMAND_WORD:
            return new EditDeadlineCommandParser().parse(arguments);

        case EditNoteContentCommand.COMMAND_WORD:
            return new EditContentCommandParser().parse(arguments);

        case DeleteTodoCommand.COMMAND_WORD:
            return new DeleteTodoCommandParser().parse(arguments);

        case ClearTodoCommand.COMMAND_WORD:
            return new ClearTodoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseNoteCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case ListNoteCommand.COMMAND_WORD:
            return new ListNoteCommand();

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case ClearNoteCommand.COMMAND_WORD:
            return new ClearNoteCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

package seedu.address.logic.parser.contact;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.contact.AddContactCommand;
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
import seedu.address.logic.parser.task.FindTaskCommandParser;
import seedu.address.logic.parser.task.note.DeleteNoteCommandParser;
import seedu.address.logic.parser.task.note.NoteCommandParser;
import seedu.address.logic.parser.task.todo.DeleteTodoCommandParser;
import seedu.address.logic.parser.task.todo.EditContentCommandParser;
import seedu.address.logic.parser.task.todo.EditDeadlineCommandParser;
import seedu.address.logic.parser.task.todo.TodoCommandParser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

/**
 * Parses user input related to task package.
 */
public class ContactParser {

    /**
     * Parses user input into task related command for execution.
     *
     * @param commandWord command word in user input
     * @param arguments command argument related to specified parameter types
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseContactCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

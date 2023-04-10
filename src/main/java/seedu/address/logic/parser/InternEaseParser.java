package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddInterviewDateCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.ClearByCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditStatusCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListArchivedCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.commands.RevertAllCommand;
import seedu.address.logic.commands.RevertCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UnarchiveCommand;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.commands.documents.AddDocumentsCommand;
import seedu.address.logic.commands.documents.DeleteDocumentsCommand;
import seedu.address.logic.commands.documents.EditDocumentsCommand;
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
import seedu.address.logic.parser.contact.ContactParser;
import seedu.address.logic.parser.documents.DocumentsParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.task.TaskParser;

/**
 * Parses user input.
 */
public class InternEaseParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private final TaskParser taskParser;
    private final DocumentsParser documentsParser;
    private final ContactParser contactParser;

    /**
     * Creates a TaskParser instance for every InternEase parser object.
     */
    public InternEaseParser() {
        taskParser = new TaskParser();
        documentsParser = new DocumentsParser();
        contactParser = new ContactParser();
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemindCommand.COMMAND_WORD:
            return new RemindCommand();

        case EditStatusCommand.COMMAND_WORD:
            return new EditStatusCommandParser().parse(arguments);

        case AddContactCommand.COMMAND_WORD:
        case EditContactCommand.COMMAND_WORD:
        case DeleteContactCommand.COMMAND_WORD:
            return contactParser.parseContactCommand(commandWord, arguments);

        case ClearByCommand.COMMAND_WORD:
            return new ClearByCommandParser().parse(arguments);

        case ArchiveCommand.COMMAND_WORD:
            return new ArchiveCommandParser().parse(arguments);

        case UnarchiveCommand.COMMAND_WORD:
            return new UnarchiveCommandParser().parse(arguments);

        case ListArchivedCommand.COMMAND_WORD:
            return new ListArchivedCommand();

        case AddInterviewDateCommand.COMMAND_WORD:
            return new AddInterviewDateCommandParser().parse(arguments);

        case RevertCommand.COMMAND_WORD:
            return new RevertCommand();

        case RevertAllCommand.COMMAND_WORD:
            return new RevertAllCommand();

        case ListTaskCommand.COMMAND_WORD:
        case FindTaskCommand.COMMAND_WORD:
        case AddTodoCommand.COMMAND_WORD:
        case ListTodoCommand.COMMAND_WORD:
        case EditDeadlineCommand.COMMAND_WORD:
        case EditNoteContentCommand.COMMAND_WORD:
        case DeleteTodoCommand.COMMAND_WORD:
        case ClearTodoCommand.COMMAND_WORD:
        case AddNoteCommand.COMMAND_WORD:
        case ListNoteCommand.COMMAND_WORD:
        case DeleteNoteCommand.COMMAND_WORD:
        case ClearNoteCommand.COMMAND_WORD:
            return taskParser.parseTaskCommand(commandWord, arguments);

        case AddDocumentsCommand.COMMAND_WORD:
        case EditDocumentsCommand.COMMAND_WORD:
        case DeleteDocumentsCommand.COMMAND_WORD:
            return documentsParser.parseDocumentsCommand(commandWord, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

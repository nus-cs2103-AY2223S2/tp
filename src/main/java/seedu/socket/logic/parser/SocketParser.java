package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.socket.logic.commands.AddCommand;
import seedu.socket.logic.commands.AddProjectCommand;
import seedu.socket.logic.commands.AssignCommand;
import seedu.socket.logic.commands.ClearCommand;
import seedu.socket.logic.commands.ClearProjectCommand;
import seedu.socket.logic.commands.Command;
import seedu.socket.logic.commands.DeleteCommand;
import seedu.socket.logic.commands.DeleteProjectCommand;
import seedu.socket.logic.commands.EditCommand;
import seedu.socket.logic.commands.EditProjectCommand;
import seedu.socket.logic.commands.ExitCommand;
import seedu.socket.logic.commands.FindCommand;
import seedu.socket.logic.commands.HelpCommand;
import seedu.socket.logic.commands.ListCommand;
import seedu.socket.logic.commands.RedoCommand;
import seedu.socket.logic.commands.RemoveCommand;
import seedu.socket.logic.commands.RemoveProjectCommand;
import seedu.socket.logic.commands.SortCommand;
import seedu.socket.logic.commands.SortProjectCommand;
import seedu.socket.logic.commands.UnassignCommand;
import seedu.socket.logic.commands.UndoCommand;
import seedu.socket.logic.commands.ViewCommand;
import seedu.socket.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SocketParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

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
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);
        case AddProjectCommand.COMMAND_WORD:
            return new AddProjectCommandParser().parse(arguments);
        case EditProjectCommand.COMMAND_WORD:
            return new EditProjectCommandParser().parse(arguments);
        case DeleteProjectCommand.COMMAND_WORD:
            return new DeleteProjectCommandParser().parse(arguments);

        case SortProjectCommand.COMMAND_WORD:
            return new SortProjectCommandParser().parse(arguments);
        case RemoveProjectCommand.COMMAND_WORD:
            return new RemoveProjectCommandParser().parse(arguments);
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ClearProjectCommand.COMMAND_WORD:
            return new ClearProjectCommand();

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case UnassignCommand.COMMAND_WORD:
            return new UnassignCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.sprint.logic.commands.AddApplicationCommand;
import seedu.sprint.logic.commands.AddTaskCommand;
import seedu.sprint.logic.commands.ClearCommand;
import seedu.sprint.logic.commands.Command;
import seedu.sprint.logic.commands.DeleteApplicationCommand;
import seedu.sprint.logic.commands.DeleteTaskCommand;
import seedu.sprint.logic.commands.EditApplicationCommand;
import seedu.sprint.logic.commands.EditTaskCommand;
import seedu.sprint.logic.commands.ExitCommand;
import seedu.sprint.logic.commands.FindCommand;
import seedu.sprint.logic.commands.HelpCommand;
import seedu.sprint.logic.commands.ListCommand;
import seedu.sprint.logic.commands.RedoCommand;
import seedu.sprint.logic.commands.SortCommand;
import seedu.sprint.logic.commands.UndoCommand;
import seedu.sprint.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InternshipBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());


        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        //=========== Miscellaneous Commands =========================================================================
        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        //=========== Application Commands ===========================================================================
        case AddApplicationCommand.COMMAND_WORD:
            return new AddApplicationCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case DeleteApplicationCommand.COMMAND_WORD:
            return new DeleteApplicationCommandParser().parse(arguments);

        case EditApplicationCommand.COMMAND_WORD:
            return new EditApplicationCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        //=========== Task Commands ==================================================================================
        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

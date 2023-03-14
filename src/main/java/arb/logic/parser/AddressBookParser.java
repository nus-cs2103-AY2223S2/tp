package arb.logic.parser;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arb.logic.commands.Command;
import arb.logic.commands.ExitCommand;
import arb.logic.commands.HelpCommand;
import arb.logic.commands.client.AddClientCommand;
import arb.logic.commands.client.ClearClientCommand;
import arb.logic.commands.client.DeleteClientCommand;
import arb.logic.commands.client.EditClientCommand;
import arb.logic.commands.client.FindClientCommand;
import arb.logic.commands.client.ListClientCommand;
import arb.logic.commands.client.SortClientCommand;
import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.ClearProjectCommand;
import arb.logic.commands.project.DeleteProjectCommand;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.FindProjectCommand;
import arb.logic.commands.project.ListProjectCommand;
import arb.logic.commands.project.MarkProjectCommand;
import arb.logic.commands.project.SortProjectCommand;
import arb.logic.commands.project.UnmarkProjectCommand;
import arb.logic.parser.client.AddClientCommandParser;
import arb.logic.parser.client.DeleteClientCommandParser;
import arb.logic.parser.client.EditClientCommandParser;
import arb.logic.parser.client.FindClientCommandParser;
import arb.logic.parser.exceptions.ParseException;
import arb.logic.parser.project.AddProjectCommandParser;
import arb.logic.parser.project.DeleteProjectCommandParser;
import arb.logic.parser.project.EditProjectCommandParser;
import arb.logic.parser.project.FindProjectCommandParser;
import arb.logic.parser.project.MarkProjectCommandParser;
import arb.logic.parser.project.SortProjectCommandParser;
import arb.logic.parser.project.UnmarkProjectCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

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

        case AddClientCommand.COMMAND_WORD:
            return new AddClientCommandParser().parse(arguments);

        case AddProjectCommand.COMMAND_WORD:
            return new AddProjectCommandParser().parse(arguments);

        case MarkProjectCommand.COMMAND_WORD:
            return new MarkProjectCommandParser().parse(arguments);

        case UnmarkProjectCommand.COMMAND_WORD:
            return new UnmarkProjectCommandParser().parse(arguments);

        case EditClientCommand.COMMAND_WORD:
            return new EditClientCommandParser().parse(arguments);

        case EditProjectCommand.COMMAND_WORD:
            return new EditProjectCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_WORD:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteProjectCommand.COMMAND_WORD:
            return new DeleteProjectCommandParser().parse(arguments);

        case ClearClientCommand.COMMAND_WORD:
            return new ClearClientCommand();

        case ClearProjectCommand.COMMAND_WORD:
            return new ClearProjectCommand();

        case FindClientCommand.COMMAND_WORD:
            return new FindClientCommandParser().parse(arguments);

        case FindProjectCommand.COMMAND_WORD:
            return new FindProjectCommandParser().parse(arguments);

        case ListClientCommand.COMMAND_WORD:
            return new ListClientCommand();

        case ListProjectCommand.COMMAND_WORD:
            return new ListProjectCommand();

        case SortClientCommand.COMMAND_WORD:
            return new SortClientCommand();

        case SortProjectCommand.COMMAND_WORD:
            return new SortProjectCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
